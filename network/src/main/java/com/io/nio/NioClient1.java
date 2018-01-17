package com.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author dmf
 * @date 2017/12/19
 */
public class NioClient1 {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

        //连接
        socketChannel.connect(new InetSocketAddress("localhost", 9000));
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            if (selector.select() > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
                while (keyIterator.hasNext()) {
                    //取出当前key
                    SelectionKey key = keyIterator.next();
                    //处理连接请求
                    if (key.isConnectable()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        //表示客户端连接上服务器端
                        if (client.isConnectionPending()) {
                            client.finishConnect();
                            buffer.clear();
                            buffer.put("服务端你好!!".getBytes());
                            buffer.flip();
                            client.write(buffer);
                        }
                        client.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable() && key.isValid()) {
                        //处理读的请求
                        SocketChannel client = (SocketChannel) key.channel();
                        buffer.clear();
                        int read = client.read(buffer);
                        if (read > 0) {
                            System.out.println("客户端接受服务器的应答:" + new String(buffer.array(), 0, read));
                            client.register(selector, SelectionKey.OP_WRITE);
                        }
                    } else if (key.isWritable() && key.isValid()) {
                        SocketChannel client = (SocketChannel) key.channel();
                        buffer.clear();
                        buffer.put("hello world".getBytes());
                        buffer.flip();
                        client.write(buffer);
                        client.register(selector, SelectionKey.OP_READ);
                    }
                }
                selectionKeys.clear();
            }
        }
    }
}
