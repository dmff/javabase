package com.vortex.command.NBA;



public class ChineseNewYearShow {

	private Command[] commands = new Command[50];
	
	public void setCommands(Command[] commands) {
		this.commands = commands;
	}
	
	public void show(){
		for(int i=0;i<commands.length;i++){
			commands[i].excute();
		}
	}
}
