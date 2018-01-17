package com.demo1.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class Server implements Runnable {


    public static void main(String[] args) {
        new Thread(new Server(9090)).start();
    }

    //1.多路复用器，管理所有的通道
    private Selector selector;

    //2.建立缓冲区
    private ByteBuffer readBuf = ByteBuffer.allocate(1024);
    private ByteBuffer writeBuf = ByteBuffer.allocate(1024);

    public Server(int port) {
        try {
            //1.打开多路复用选择器
            selector = Selector.open();
            //2.打开服务器通道，并且设置为非阻塞
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            //3.在通道上绑定地址
            serverSocketChannel.bind(new InetSocketAddress(port));
            //4.把服务器通道注册到多路复用选择器上
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("server start,port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void run() {
        while (true) {
            try {
                //1.让多路复用选择器监听
                selector.select();
                //2.获取key的结果集
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();  //移除当前key
                    if (key.isValid()) {
                        if (key.isAcceptable()) {
                            accept(key);
                        }
                        if (key.isReadable()) {
                            read(key);
                        }
                        if (key.isWritable()) {
                            //write(key);
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void read(SelectionKey key) throws IOException {
        //1.清空缓存区
        readBuf.clear();
        //2 获取之前注册的socket通道对象
        SocketChannel sc = (SocketChannel) key.channel();
        int count = sc.read(readBuf);
        if (count == -1) {
            key.channel().close();
            key.cancel();
            return;
        }
        //5 有数据则进行读取 读取之前需要进行复位方法(把position 和limit进行复位)
        this.readBuf.flip();
        //6 根据缓冲区的数据长度创建相应大小的byte数组，接收缓冲区的数据
        byte[] bytes = new byte[this.readBuf.remaining()];
        //7 接收缓冲区数据
        this.readBuf.get(bytes);
        //8 打印结果
        String body = new String(bytes).trim();
        System.out.println("Server : " + body);

        // 9..可以写回给客户端数据 
    }

    private void accept(SelectionKey key) throws IOException {
        //获取通道
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        SocketChannel socket = channel.accept();
        socket.configureBlocking(false);
        //4 注册到多路复用器上，并设置读取标识
        socket.register(selector, SelectionKey.OP_READ);
    }

    private void write(SelectionKey key) throws ClosedChannelException {
        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
        channel.register(selector, SelectionKey.OP_WRITE);
    }


}
