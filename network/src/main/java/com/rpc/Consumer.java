package com.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 服务的消费者
 * @author dmf
 *
 */
public class Consumer {

	public static void main(String[] args) throws IOException {
		Socket socket = null;
		try {
			//接口名称
			String interfaceName = HelloService.class.getName();
			//需要远程执行的方法
			Method method = HelloService.class.getMethod("sayHello", String.class);
			//需要传递到远端的参数
			Object[] arguments ={"hello"};
			
			socket = new Socket("127.0.0.1", 12345);
			//将方法名称和参数传递到远端
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			output.writeUTF(interfaceName);
			output.writeUTF(method.getName());
			output.writeObject(method.getParameterTypes());
			output.writeObject(arguments);
			
			//冲远端读取方法执行结构
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			Object result = input.readObject();
			System.out.println((String)result);
			
			input.close();
			output.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket !=null) {
				socket.close();
			}
		}
	}
}
