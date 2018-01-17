package com.vortex.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Prototype implements Cloneable,Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	
	private SerializableObject obj;
	
	/**浅复制*/
	@Override
	public Object clone() throws CloneNotSupportedException {
		Prototype proto = (Prototype) super.clone();  
		return proto;
	}
	
	/**深复制
	 * @throws IOException 
	 * @throws ClassNotFoundException */
	public Object deepClone() throws IOException, ClassNotFoundException{
		/**写入当前对象的二进制流*/
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(this);
		
		/**读出二进制流产生的对象*/
		 ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());  
	     ObjectInputStream ois = new ObjectInputStream(bis);  
	     return ois.readObject(); 
	}
	
	

	@Override
	public String toString() {
		return "Prototype [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SerializableObject getObj() {
		return obj;
	}

	public void setObj(SerializableObject obj) {
		this.obj = obj;
	}

	class SerializableObject implements Serializable {  
	    private static final long serialVersionUID = 1L;  
	} 
	
}
