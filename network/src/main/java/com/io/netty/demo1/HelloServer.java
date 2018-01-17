package com.io.netty.demo1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author dmf
 * @date 2017/12/19
 */
public class HelloServer {
    /**
     * 1.	系统编解码框架-ByteToMessageCodec；
     * 2.	通用基于长度的半包解码器-LengthFieldBasedFrameDecoder;
     * 3.	码流日志打印Handler-LoggingHandler；
     * 4.	SSL安全认证Handler-SslHandler；
     * 5.	链路空闲检测Handler-IdleStateHandler；
     * 6.	流量整形Handler-ChannelTrafficShapingHandler;
     * 7.	Base64编解码-Base64Decoder和Base64Encoder。
     */
    public static void main(String[] args) {

        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(boss, work)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_RCVBUF, 2048)
                .option(ChannelOption.SO_SNDBUF, 2048)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 以("\n")为结尾分割的 解码器
                        socketChannel.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                        // 字符串解码 和 编码
                        socketChannel.pipeline().addLast("decoder", new StringDecoder());
                        socketChannel.pipeline().addLast("encoder", new StringEncoder());
                        socketChannel.pipeline().addLast("handler", new ServerHandler());
                    }
                });
        try {
            //绑定端口
            ChannelFuture cf = bootstrap.bind(9000).sync();
            //等待关闭
            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}
