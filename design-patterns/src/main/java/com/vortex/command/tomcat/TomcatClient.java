package com.vortex.command.tomcat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

public class TomcatClient {
	
	private Socket socket = null;
	
	public void startClient() throws UnknownHostException, IOException{
		socket = new Socket("localhost", 60000);
	}
	
	public void visitServer() throws IOException{
		OutputStream out = socket.getOutputStream();
		DataOutputStream dout = new DataOutputStream(out);
		InputStream in = socket.getInputStream();
		DataInputStream din = new DataInputStream(in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, Charset.forName("UTF-8")));
		String url = null;
		System.out.println("----请输入您要访问的url地址,退出请输入（exit）:");
		if(!(url = reader.readLine()).equals("exit")){
			dout.writeUTF(url);
			dout.flush();
			String response = din.readUTF();
			System.out.println(response);
		}
		reader.close();
		dout.close();
		din.close();
	}
	
	
	public void shutdownClient() throws IOException{
		socket.close();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		TomcatClient client = new TomcatClient();
		client.startClient();
		client.visitServer();
		client.shutdownClient();
	}
}
