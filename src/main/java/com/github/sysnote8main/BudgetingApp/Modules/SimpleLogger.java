package com.github.sysnote8main.BudgetingApp.Modules;

public class SimpleLogger {
	private static String name = "Logger";
	private static String prefix;
	public SimpleLogger(String name) {
		if(!name.equals("")) {
			this.name = name;
		}
		prefix = "["+name+"] ";
		log("Logger was initialized!");
	}
	public void log(String message) {
		System.out.println(prefix+message);
	}
	public void error(String message) {
		throw new Error(message);
	}
}
