package com.netty.timer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty是一个NIO框架，使用它可以简单快速地开发网络应用程序，比如客户端和服务端的协议
 * <p>
 * 对于channeloption.so_backlog的解释：
 * 服务器端tcp内核模块维护2个队列，A、B；客户端向服务端发起connect的时候，会发送SYN标志的包(第一次握手)
 * 服务器收到客户端发来的SYN时，向客户端发送SYN ACK确认(第二次握手)，此时tcp内核模块把客户端连接加入a队列中
 * 然后服务器接受客户端发来的ACK (第三次握手)，tcp内核模块把客户端连接从A队列移到B队列，连接完成，应用程序的accept会返回
 * <p>
 * 也就是说accept从B队列取出完成三次握手的连接，A、B队列的长度之和是backlog。当A,B队列的长度之和大于backlog时
 * 新的连接将会被tcp内核拒绝，所以，如果backlog过小，可能会出现accept速度跟不上，A，B队列满了，导致新的客户端无法连接
 * <p>
 * 要注意的是：backlog对程序支持的连接数是无影响的，backlog影响的只是还没有被accept取出的连接
 *
 * @author dmf
 */
public class TimerServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new TimeServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture future = bootstrap.bind(9000).sync();
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
