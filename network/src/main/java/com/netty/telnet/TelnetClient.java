package com.netty.telnet;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author dmf
 */
public class TelnetClient {


    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast( new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()),
                                    new StringDecoder(),
                                    new StringEncoder(),
                                    new TelnetClientHandler());
                        }
                    });
            Channel channel = bootstrap.connect("127.0.0.1", 9000).sync().channel();

            ChannelFuture channelFuture = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for(;;){
                String line = in.readLine();
                if (line==null){
                    break;
                }
                channelFuture = channel.writeAndFlush(line+"\r\n");
                if ("bye".equalsIgnoreCase(line)){
                    channel.closeFuture().sync();
                    break;
                }

                if (channelFuture!=null){
                    channelFuture.sync();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
