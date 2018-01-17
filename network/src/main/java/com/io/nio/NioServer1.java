package com.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * @author dmf
 * @date 2017/12/19
 */
public class NioServer1 {

    public static void main(String[] args) throws IOException {
        int port = 9000;

        Selector selector = Selector.open();
        //打开服务器通道
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        //设置为非阻塞
        socketChannel.configureBlocking(false);
        ServerSocket serverSocket = socketChannel.socket();

        //把服务绑定端口
        serverSocket.bind(new InetSocketAddress(port));
        //注册到selector上等待连接
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务器运行在 端口："+port);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while(true){
            if(selector.select()>0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()){
                    //取出当前key
                    SelectionKey key = keyIterator.next();
                    //处理连接请求
                    if (key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        //获取客户端的连接,通过通道写给客户端
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        buffer.clear();
                        buffer.put(("当前时间："+new Date()).getBytes());
                        buffer.flip();
                        client.write(buffer);
                        client.register(selector,SelectionKey.OP_READ);
                    } else if(key.isReadable() && key.isValid()){
                        //处理读的请求
                        SocketChannel client = (SocketChannel) key.channel();
                        buffer.clear();
                        int read = client.read(buffer);
                        if(read>0){
                            System.out.println("服务器接受客户端的请求:"+new String(buffer.array(),0,read));
                            client.register(selector,SelectionKey.OP_WRITE);
                        }
                    }else if(key.isWritable() && key.isValid()){
                        SocketChannel client = (SocketChannel) key.channel();
                        buffer.clear();
                        buffer.put("欢迎，你好".getBytes());
                        buffer.flip();
                        client.write(buffer);
                        client.register(selector,SelectionKey.OP_READ);
                    }
                }
                selectionKeys.clear();
            }
        }
    }
}
