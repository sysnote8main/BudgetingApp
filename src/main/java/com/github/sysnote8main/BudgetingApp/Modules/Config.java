package com.github.sysnote8main.BudgetingApp.Modules;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Config {
	public static String name = "家計簿アプリ";
	public static String author = "sysnote8main";
	public static String Logger_prefix = "BudgetApp";
	public static String db_file = "database";
	public static String table = "BUDGETTABLE";
    public static boolean isCreatedDBfile = Files.exists(Paths.get("./"+db_file+".db"));
}
