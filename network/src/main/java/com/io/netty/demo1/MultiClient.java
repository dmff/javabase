package com.io.netty.demo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dmf
 * @date 2017/12/19
 *
 * 单客户端多个连接：可以使用对象池（线程不安全、阻塞）或者对象组（线程安全、不阻塞）
 * 可以断开自动重连，防止一个客户端掉线后不响应了
 */
public class MultiClient {

    private Bootstrap bootstrap;

    private List<Channel> channelList;

    /**
     * 引用计数
     */
    private final AtomicInteger index = new AtomicInteger();

    public void init(int count){
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 以("\n")为结尾分割的 解码器
                        socketChannel.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                        // 字符串解码 和 编码
                        socketChannel.pipeline().addLast("decoder", new StringDecoder());
                        socketChannel.pipeline().addLast("encoder", new StringEncoder());
                        socketChannel.pipeline().addLast("handler",new ClientHandler());
                    }
                });

        count = count>0 ? count :10;
        for(int i=0;i<count;i++){
            ChannelFuture channelFuture = bootstrap.connect("localhost", 9000);
            channelList.add(channelFuture.channel());
        }
    }

    /**
     * 获取会话
     * @return
     */
    public Channel nextChannel(){
        return getFirstActiveChannel(0);
    }

    private Channel getFirstActiveChannel(int count){
        Channel channel = channelList.get(Math.abs(index.getAndIncrement() % channelList.size()));
        if(!channel.isActive()){
            //重连
            reconnect(channel);
            if(count >= channelList.size()){
                throw new RuntimeException("no can use channel");
            }
            return getFirstActiveChannel(count + 1);
        }
        return channel;
    }

    /**
     * 重连
     * @param channel
     */
    private void reconnect(Channel channel){
        synchronized(channel){
            if(channelList.indexOf(channel) == -1){
                return ;
            }

            Channel newChannel = bootstrap.connect("localhost", 9000).channel();
            channelList.set(channelList.indexOf(channel), newChannel);
        }
    }
}
