package com.vortex.state.mood;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Person {

	private Map<String,Mood> moods = new HashMap<String,Mood>();
	
	private Mood currentMood;
	
	public Mood getCurrentMood() {
		return currentMood;
	}
	
	public void addMood(String moodName,Mood mood){
		moods.put(moodName, mood);
	}
	
	
	public Person(){
		moods.put("default",new HappyMood());
	}
	
	public void perform() throws IOException{
		currentMood = moods.get(getMoodName());
		if (currentMood == null) {
			currentMood = moods.get("default");
		}
		Mood currentMood_ = currentMood;
		currentMood_.perform();
	}
	
	
	private String getMoodName() throws IOException{
		Properties properties = new Properties();
		properties.load(this.getClass().getResourceAsStream("mood.properties"));
		return properties.getProperty("mood");
	}
}
