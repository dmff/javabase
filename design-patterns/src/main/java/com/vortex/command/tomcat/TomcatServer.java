package com.vortex.command.tomcat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TomcatServer {
	/**
	 * 每一个槽位代表一个Servlet，并且每一个Servlet对应一个映射
	 * urlMappings为映射表
	 * 
	 * 平滑的关闭socket；启用另一个线程监听，并且需要解除socket阻塞
	 */
	private static Map<String, Class<? extends Servlet>> urlMappings = new HashMap<String, Class<? extends Servlet>>();
	
	static{
		Properties properties = new Properties();
		
		try {
			//初始化配置，读取配置信息，并且填充到映射表中
			properties.load(TomcatServer.class.getResourceAsStream("url-mappings.properties"));
			Enumeration<?> urlName = properties.propertyNames();
			while (urlName.hasMoreElements()) {
				String url = (String) urlName.nextElement();
				String servletName = properties.getProperty(url);
				
				@SuppressWarnings("unchecked")
				Class<? extends Servlet> clazz = (Class<? extends Servlet>) Class.forName(servletName);
				urlMappings.put(url, clazz);
			}
		} catch (IOException e) {
			throw new RuntimeException("classpath下面url-mappings.properties文件缺失！", e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	//存放第一次使用时加载的servlet
	private Map<String, Servlet> urlMappings2 = new HashMap<String,Servlet>();
	private ServerSocket serverSocket = null;
	//用来平滑的关闭服务器
	private volatile boolean on = true;
	
	public void setOff() {
		this.on = false;
	}
	
	public void startServer() throws IOException{
		serverSocket = new ServerSocket(60000);
	}
	
	public void service() throws IOException, InstantiationException, IllegalAccessException{
		//服务器启动时做的事情
		while(on){
			Socket socket = serverSocket.accept();
			InputStream in = socket.getInputStream();
			OutputStream out = socket.getOutputStream();
			DataInputStream din = new DataInputStream(in);
			DataOutputStream dout = new DataOutputStream(out);
			
			String url = din.readUTF();
			Servlet servlet = urlMappings2.get(url);
			if (servlet == null) {
				Class<? extends Servlet> clazz = urlMappings.get(url);
				if(clazz == null){
					dout.writeUTF("404");
				}else{
					servlet = clazz.newInstance();
					urlMappings2.put(url, servlet);
					String str = servlet.service();
					dout.writeUTF(str);
				}
			}else {  //第二次可以直接访问这个url
				String str = servlet.service();
				dout.writeUTF(str);  //写个客户端
			}
			dout.flush();
			dout.close();
			din.close();
			socket.close();
		}
		serverSocket.close();
	}
	
	public static void main(String[] args) throws Exception {
		TomcatServer server = new TomcatServer();
		server.startServer();
		
		//启动另一个线程监听键盘输入
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				try {
					do {
						System.out.println("----如果想关闭服务器，请输入exit:");
					} while (!reader.readLine().equals("exit"));
					
					closeServer();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			private void closeServer() throws UnknownHostException, IOException{
				server.setOff();
				
				//解除服务端的阻塞
				Socket socket = new Socket("localhost", 60000);
				DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
				dout.writeUTF("exit");
				socket.close();
			}
		}).start();
		
		//启动服务
		server.service();
	}
}
