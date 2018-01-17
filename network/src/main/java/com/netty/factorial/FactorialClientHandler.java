package com.netty.factorial;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author dmf
 * @date 2017/12/23
 */
public class FactorialClientHandler extends SimpleChannelInboundHandler<BigInteger> {

    private int next = 1;
    private int receivedMessages;
    final BlockingQueue<BigInteger> answer = new LinkedBlockingQueue<BigInteger>();

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, BigInteger bigInteger) throws Exception {
        receivedMessages++;
        if (receivedMessages==1000){
            channelHandlerContext.close().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    boolean offered = answer.offer(bigInteger);
                    assert offered;
                }
            });
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelFuture future=null;
        for (int i=0;i<4096 && next<=1000;i++){
            future = ctx.write(Integer.valueOf(next));
            next++;
        }

        if (next<=1000){
            assert future!=null;
            future.addListener(new ChannelFutureListener() {

                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()){
                        channelActive(ctx);
                    }else {
                        channelFuture.cause().printStackTrace();;
                        channelFuture.channel().close();
                    }
                }
            });
        }

        ctx.flush();
    }
}
