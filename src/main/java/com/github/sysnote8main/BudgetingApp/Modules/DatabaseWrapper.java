package com.github.sysnote8main.BudgetingApp.Modules;

import com.github.sysnote8main.BudgetingApp.Enums.CategoryTypes;
import com.github.sysnote8main.BudgetingApp.Enums.DatabaseColumns;
import com.github.sysnote8main.BudgetingApp.Enums.DayTypes;
import com.github.sysnote8main.BudgetingApp.Enums.SQLTypes;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseWrapper {
    private static DatabaseConnector database = null;
    private static SimpleLogger Logger = null;
    public DatabaseWrapper(SimpleLogger Logger) {
        this.database = new DatabaseConnector(Config.db_file,Logger);
        this.Logger = Logger;
        this.database.ConnectDB();
    }
    public void createTable() {
        HashMap<DatabaseColumns, SQLTypes> tableSchema = new HashMap<>(); // create HashMap
        tableSchema.put(DatabaseColumns.name, SQLTypes.text); // name: string
        tableSchema.put(DatabaseColumns.category, SQLTypes.text); // category: CategoryTypes(toString/TEXT)
        tableSchema.put(DatabaseColumns.summary, SQLTypes.text); // summary: string
        tableSchema.put(DatabaseColumns.deposit, SQLTypes.integer); // deposit: integer
        tableSchema.put(DatabaseColumns.withdraw, SQLTypes.integer); // withdraw: integer
        tableSchema.put(DatabaseColumns.year, SQLTypes.integer); // year: integer(Calendar.YEAR)
        tableSchema.put(DatabaseColumns.month, SQLTypes.integer); // month: integer(Calendar.MONTH)
        tableSchema.put(DatabaseColumns.day, SQLTypes.integer); // day: integer(Calendar.DAY)
        tableSchema.put(DatabaseColumns.day_of_month, SQLTypes.text); // day_of_month: string(Calendar.DAY_OF_MONTH)
        database.createTable(Config.table, tableSchema);
    }

    // Default Insert Method
    public void insert(String name, CategoryTypes category, String summary, int deposit, int withdraw, int year, int month, int day, DayTypes day_of_month) {
        HashMap<DatabaseColumns, String> insertMap = new HashMap<>();
        insertMap.put(DatabaseColumns.name, name);
        insertMap.put(DatabaseColumns.category, category.toString());
        insertMap.put(DatabaseColumns.summary, summary);
        insertMap.put(DatabaseColumns.deposit, String.valueOf(deposit));
        insertMap.put(DatabaseColumns.withdraw, String.valueOf(withdraw));
        insertMap.put(DatabaseColumns.year, String.valueOf(year));
        insertMap.put(DatabaseColumns.month, String.valueOf(month));
        insertMap.put(DatabaseColumns.day, String.valueOf(day));
        insertMap.put(DatabaseColumns.day_of_month, day_of_month.toString());
        database.insertData(Config.table,insertMap);
        Logger.log("Inserted!");
    }

    public void delete(int id) {
        HashMap<DatabaseColumns, String> deleteMap = new HashMap<>();
        deleteMap.put(DatabaseColumns.id,String.valueOf(id)); // id which used to select
        database.deleteData(Config.table,deleteMap);
    }

    public void getAll() {
        ArrayList<HashMap<DatabaseColumns,String>> records = database.runQuery("select * from "+Config.table+";");
        for (HashMap<DatabaseColumns, String> record : records) {
            System.out.println(record);
        }
    }
}
