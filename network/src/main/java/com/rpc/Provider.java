package com.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 服务的提供类
 * @author dmf
 *
 */
public class Provider {

	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Map<String,Object> services = new HashMap<>();
		services.put(HelloService.class.getName(), new HelloServiceImpl());
		
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(12345);
		while(true){
			Socket socket = serverSocket.accept();
			
			//读取服务信息
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            String interfaceName = input.readUTF();
            String methodName = input.readUTF();
            @SuppressWarnings("rawtypes")
			Class[] parameterTypes = (Class[]) input.readObject();
            Object[] arguments = (Object[]) input.readObject();
            
            //执行调用
            Class<?> clazz = Class.forName(interfaceName);
            Object service = services.get(interfaceName);
            
            //获取调用的方法
            Method method = clazz.getMethod(methodName, parameterTypes);
            
            Object result = method.invoke(service, arguments);
            
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(result);
		}
		
	}
}
