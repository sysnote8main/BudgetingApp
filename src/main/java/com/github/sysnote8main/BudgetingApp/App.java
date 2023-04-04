package com.github.sysnote8main.BudgetingApp;
import com.github.sysnote8main.BudgetingApp.Enums.CategoryTypes;
import com.github.sysnote8main.BudgetingApp.Enums.DayTypes;
import com.github.sysnote8main.BudgetingApp.Modules.*;
import com.github.sysnote8main.BudgetingApp.Swings.NewDataForm;
import com.github.sysnote8main.BudgetingApp.Swings.Startup;

/**
 * Author : sysnote8main
 *
 */
public class App 
{
	private static final SimpleLogger Logger = new SimpleLogger(Config.Logger_prefix);
    private static final DatabaseWrapper db = new DatabaseWrapper(Logger);
    public static final JapaneseLang lang = new JapaneseLang();
    private static final Startup Main_window = new Startup();
    public static void insertData(String name, CategoryTypes category, String summary, int deposit, int withdraw, int year, int month, int day, DayTypes day_of_month) {
        db.insert(name, category, summary, deposit, withdraw, year, month, day, day_of_month);
    }
    public static void main( String[] args ) {
		if(Config.isCreatedDBfile) {
			Logger.log("Database table was already created!");
		} else {
			Logger.log("Database table was not found.");
			db.createTable();
			Logger.log("Finished to create database table.");
		}
        Main_window.Enable();
    }
}
