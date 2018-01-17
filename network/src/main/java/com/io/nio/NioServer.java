package com.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author dmf
 * @date 2017/12/18
 */
public class NioServer {

    /**
     *  通道管理器
     */
    private Selector selector;

    public void initServer(int port) throws IOException {
        // 获得一个ServerSocket通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 设置通道为非阻塞
        serverChannel.configureBlocking(false);
        // 将该通道对应的ServerSocket绑定到port端口
        serverChannel.socket().bind(new InetSocketAddress(port));
        // 获得一个通道管理器
        this.selector = Selector.open();
        // 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
        // 当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
        serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
     */
    public void listener() throws IOException {
        System.out.println("服务器端启动...");
        // 轮询访问selector
        while(true) {
            // 选择一组键，并且相应的通道已经准备就绪
            if (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // 删除已选的key,以防重复处理
                    iterator.remove();
                    handler(selectionKey);
                }
            }
        }
    }

    /**
     *  处理客户端的请求
     */
    private void handler(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()){
            handlerAccept(selectionKey);
        }else if(selectionKey.isReadable()){
            handlerRead(selectionKey);
        }
    }

    /**
     * 处理连接请求
     */
    private void handlerAccept(SelectionKey selectionKey) throws IOException {
        // 获得和客户端连接的通道
        ServerSocketChannel socketChannel = (ServerSocketChannel) selectionKey.channel();
        socketChannel.configureBlocking(false);

        // 在这里可以给客户端发送信息
        System.out.println("新的客户端连接");
        // 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读的权限
        socketChannel.register(selector,SelectionKey.OP_READ);
    }

    private void handlerRead(SelectionKey selectionKey) throws IOException {
        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) selectionKey.channel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int read = channel.read(byteBuffer);
        if (read>0){
            byte[] bytes = byteBuffer.array();
            System.out.println("服务端收到消息："+new String(bytes).trim());

            ByteBuffer buffer = ByteBuffer.wrap("好的".getBytes());
            channel.write(buffer);
        } else {
            System.out.println("客户端通道关闭");
            selectionKey.cancel();
        }
    }

    public static void main(String[] args) throws IOException {
        NioServer server = new NioServer();
        server.initServer(9000);
        server.listener();
    }
}
