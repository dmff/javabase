package com.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dmf
 * @date 2017/12/18
 */
public class BioServer {


    public static void main(String[] args) throws IOException {
        init1();
    }
    
    public static void init1() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("服务器启动");
        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("连接了一个新的客户端");
            //采用单线程的方式处理客户端的连接和请求
            handler(socket);
        }
    }

    public static void init2() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        System.out.println("服务器启动");
        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("连接了一个新的客户端");
            //采用多线程的方式处理客户端的连接和请求
            new Thread(()->handler(socket)).start();
        }
    }

    public static void init3() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        ExecutorService service = Executors.newFixedThreadPool(5);
        System.out.println("服务器启动");
        while(true){
            Socket socket = serverSocket.accept();
            System.out.println("连接了一个新的客户端");
            //采用线程池的方式处理客户端的连接和请求
            service.execute(()->handler(socket));
        }
    }

    private static void handler(Socket socket)  {
        try {
            byte[] bytes = new byte[1024];
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            //写给客户端
            out.write("hello client...".getBytes());

            int read=0;
            while((read=in.read(bytes))!=-1){
                //阻塞的读取数据
                System.out.println(new String(bytes,0,read));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("socket关闭");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
