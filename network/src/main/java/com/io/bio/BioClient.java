package com.io.bio;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author dmf
 * @date 2017/12/18
 */
public class BioClient {

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        try {
            socket = new Socket("localhost",9000);
            InputStream in = socket.getInputStream();
            final OutputStream out = socket.getOutputStream();

            out.write("hello server".getBytes());
            byte[] bytes = new byte[1024];
            int read=0;
            while((read=in.read(bytes))!=-1){
                System.out.println(new String(bytes,0,read));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("socket 关闭");
            socket.close();
        }

    }
}
