package com.demo1.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ExecutorService service;

    private AsynchronousChannelGroup threadGroup;

    public AsynchronousServerSocketChannel serverSocketChannel;

    public Server(int port) {
        try {
            service = Executors.newCachedThreadPool();
            threadGroup = AsynchronousChannelGroup.withCachedThreadPool(service,1);
            serverSocketChannel = AsynchronousServerSocketChannel.open(threadGroup);

            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.accept();

            //一直阻塞 不让服务器停止
            Thread.sleep(Integer.MAX_VALUE);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(9090);
    }
}
