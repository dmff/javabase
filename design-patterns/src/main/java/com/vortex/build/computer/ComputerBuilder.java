package com.vortex.build.computer;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * builder设计模式解决复杂的构造对象的问题:
 * 1.可以给他设置默认值
 * 2.在set属性方法中返回当前对象可以链式编程
 * 3.设置条件，这样就可以控制那些属性可以设置，那些不可以设置
 * 
 * 如果不希望去直接创建computer对象，可以把computer这个类设置成它的静态内部类，构造方法私有化
 */
public class ComputerBuilder {

	
	public static final String DEFAULT_OS = "windows";
	public static final String DEFAULT_ADMIN = "admin";
	public static final String DEFAULT_PASSWORD = "admin";
	public static final String DEFAULT_BROWSER = "IE";
	public static final String DEFAULT_OFFICE = "Office2013";
	public static final String DEFAULT_CHAT = "msn";
	public static final String DEFAULT_LANGUAGE = "EN";
	public static final String DEFAULT_RESOLUTION = "1366*768";
	
	//使用静态块初始化数据
	public static final Map<String, List<String>> configurationMap = new HashMap<String,List<String>>();
	static{
		configurationMap.put("os", Arrays.asList("windows","linux"));
		configurationMap.put("language", Arrays.asList("EN","CH"));
		configurationMap.put("windows_browser", Arrays.asList("IE","FireFOx"));
		configurationMap.put("windows_office", Arrays.asList("Office2013","WPS"));
		configurationMap.put("windows_chat", Arrays.asList("msn","qq"));
		configurationMap.put("linux_browser", Arrays.asList("FireFOx"));
		configurationMap.put("linux_office", Arrays.asList("WPS"));
		
	}
	
	
	private String os;
	//private String network;
	private String admin;
	private String password;
	private String browser;
	private String office;
	private String chat;
	private String language;
	private String resolution;
	
	//设置创建的默认信息
	public ComputerBuilder() {
		os = DEFAULT_OS;
		admin = DEFAULT_ADMIN;
		password = DEFAULT_PASSWORD;
		browser = DEFAULT_BROWSER;
		office = DEFAULT_OFFICE;
		chat = DEFAULT_CHAT;
		language = DEFAULT_LANGUAGE;
		resolution = DEFAULT_RESOLUTION;
	}

	public String getOs() {
		return os;
	}

	public ComputerBuilder setOs(String os) {
		if(configurationMap.get("os").contains(os)){
			this.os = os;
			setBrowser(configurationMap.get(os + "_browser").get(0));
			setOffice(configurationMap.get(os + "_office").get(0));
			setChat(configurationMap.get(os + "_chat") == null ? null : configurationMap.get(os + "_chat").get(0));
		}
		return this;
	}

	public String getAdmin() {
		return admin;
	}

	public ComputerBuilder setAdmin(String admin) {
		this.admin = admin;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public ComputerBuilder setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getBrowser() {
		return browser;
	}

	public ComputerBuilder setBrowser(String browser) {
		String os = getOs();
		List<String> browers = configurationMap.get(os + "_browser");
		if(browers != null && browers.contains(browser)){
			this.browser = browser;
		}
		return this;
	}

	public String getOffice() {
		return office;
	}

	public ComputerBuilder setOffice(String office) {
		String os = getOs();
		List<String> offices = configurationMap.get(os + "_office");
		if(offices != null && offices.contains(office)){
			this.office = office;
		}
		return this;
	}

	public String getChat() {
		return chat;
	}

	public ComputerBuilder setChat(String chat) {
		String os = getOs();
		List<String> chats = configurationMap.get(os + "_chat");
		if(chats != null && chats.contains(chat)){
			this.chat = chat;
		}
		return this;
	}

	public String getLanguage() {
		return language;
	}

	public ComputerBuilder setLanguage(String language) {
		this.language = language;
		return this;
	}

	public String getResolution() {
		return resolution;
	}

	public ComputerBuilder setResolution(String resolution) {
		this.resolution = resolution;
		return this;
	}
	
	public Computer build(){
		Computer computer = new Computer();
		computer.setAdmin(admin);
		computer.setPassword(password);
		computer.setBrowser(browser);
		computer.setChat(chat);
		computer.setLanguage(language);
		//computer.setNetwork(network);
		computer.setOffice(office);
		computer.setOs(os);
		computer.setResolution(resolution);
		return computer;
	}

	public static class Computer {
		private String os;
		//private String network;
		private String admin;
		private String password;
		private String browser;
		private String office;
		private String chat;
		private String language;
		private String resolution;//分辨率
		
		//使用内部类，构造方法私有化
		private Computer(){}
		
		public String getOs() {
			return os;
		}
		public void setOs(String os) {
			this.os = os;
		}
		public String getAdmin() {
			return admin;
		}
		public void setAdmin(String admin) {
			this.admin = admin;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getBrowser() {
			return browser;
		}
		public void setBrowser(String browser) {
			this.browser = browser;
		}
		public String getOffice() {
			return office;
		}
		public void setOffice(String office) {
			this.office = office;
		}
		public String getChat() {
			return chat;
		}
		public void setChat(String chat) {
			this.chat = chat;
		}
		public String getLanguage() {
			return language;
		}
		public void setLanguage(String language) {
			this.language = language;
		}
		public String getResolution() {
			return resolution;
		}
		public void setResolution(String resolution) {
			this.resolution = resolution;
		}

		@Override
		public String toString() {
			return "Computer [os=" + os + ", admin=" + admin + ", password=" + password + ", browser=" + browser
					+ ", office=" + office + ", chat=" + chat + ", language=" + language + ", resolution=" + resolution
					+ "]";
		}
		
		
	}
	
}
