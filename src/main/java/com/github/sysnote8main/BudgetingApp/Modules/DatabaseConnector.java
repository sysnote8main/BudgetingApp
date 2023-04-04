package com.github.sysnote8main.BudgetingApp.Modules;

import com.github.sysnote8main.BudgetingApp.Enums.DatabaseColumns;
import com.github.sysnote8main.BudgetingApp.Enums.SQLTypes;
import org.sqlite.jdbc4.JDBC4ResultSet;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.StringJoiner;

public class DatabaseConnector {
	private static String dbfile = "database";
	private static SimpleLogger Logger = null;
	private static Connection conn = null;
	public DatabaseConnector(String dbfile, SimpleLogger Logger) {
		this.dbfile = dbfile + ".db";
		this.Logger = Logger;
	}


    private static boolean isStringData(DatabaseColumns dc) {
        if(dc.equals(DatabaseColumns.name) || dc.equals(DatabaseColumns.category) || dc.equals(DatabaseColumns.summary) || dc.equals(DatabaseColumns.day_of_month)) {
            return true;
        } else {
            return false;
        }
    }
    public void DeleteDB() {
        try {
            new File(dbfile).delete();
            Logger.log("DB File deleted success.");
        } catch (Exception e) {
            Logger.log("Error while in deleting file " + e.getMessage());
            e.printStackTrace();
        }
    }

	public void ConnectDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:"+dbfile);
			Logger.log("Database was successfully to connect!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void DisconnectDB() {
		try {
			conn.close();
            Logger.log("Database was successfully to disconnect!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public void createTable(String newTableName, HashMap<DatabaseColumns, SQLTypes> tableSchema) {
        try {
            // get size of column
            int columnSize = tableSchema.size();
            StringJoiner sj = new StringJoiner(",");
            sj.add("id integer primary key autoincrement");
            for(DatabaseColumns r:tableSchema.keySet()) {
                sj.add(r.toString()+" "+tableSchema.get(r));
                Logger.log(String.valueOf(r));
                Logger.log(String.valueOf(tableSchema.get(r)));
            }
            // create Statement
            Statement stmt = conn.createStatement();
            String sql = "create table "+newTableName+"("+ sj +")";
            stmt.executeUpdate(sql);
            stmt.close();
            Logger.log("Successfully to create table (name: "+newTableName+")");
        } catch (Exception e) {
            Logger.log("Error while creating table " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void insertData(String targetTableName, HashMap<DatabaseColumns, String> insertData) {
        try {
            StringJoiner Name_joiner = new StringJoiner(",");
            StringJoiner Value_joiner = new StringJoiner(",");
            for(DatabaseColumns key:insertData.keySet()) {
                String value = insertData.get(key);
                if (isStringData(key)) {
                    value = "'" + value + "'";
                }
                Name_joiner.add(key.name());
                Value_joiner.add(value);
            }

            Statement stmt = conn.createStatement();
            String sql = "insert into " + targetTableName
                + "(" + Name_joiner + ")"
                + "VALUES (" + Value_joiner + ");";
            Logger.log("Data inserted! SQL: " + sql);
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            Logger.log("Error while inserting data " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int updateData(String targetTableName, HashMap<DatabaseColumns, String> updateData) {
        try {
            StringBuilder columnWithValues = new StringBuilder("");
            StringBuilder conditions = new StringBuilder("");
            for(int i=0;i<updateData.size();i++) {
                String columnName = updateData.keySet().toArray()[i].toString().toLowerCase();
                String columnValue = updateData.get(columnName);
                if(columnName.equals("id")) {
                    conditions.append("id=").append(columnValue).append(" ");
                } else {
                    columnWithValues.append(columnName).append("='").append(columnValue).append("' ");
                    if (i < (updateData.size() - 2)) {
                        columnWithValues.append(",");
                    }
                }
            }

            String query = "update "+targetTableName+" set "+columnWithValues+" where "+conditions+";";
            Logger.log(query);
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            Logger.log("Error while in updating data "+e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteData(String targetTableName, HashMap<DatabaseColumns,String> deleteData) {
        try {
            String query = "delete from "+targetTableName+" where id="+deleteData.get("id")+";";
            Logger.log(query);
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            Logger.log("Error while in deleting data "+ e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public int runUpdateOrDelete(String query) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(query);
        } catch (Exception e) {
            Logger.log("Error while in updating "+e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<HashMap<DatabaseColumns, String>> runQuery(String query) {
        ArrayList<HashMap<DatabaseColumns, String>> resultList = new ArrayList<>();
        try {
            String queryCheck = query.trim().toLowerCase();
            if(queryCheck.startsWith("update")) {
                Logger.log("Running update query...");
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(query);
                return null;
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String[] columnNames = ((JDBC4ResultSet) rs).cols;

            while(rs.next()) {
                HashMap<DatabaseColumns, String> resultData = new HashMap<>();
                for(int i=0;i<columnNames.length;i++) {
                    resultData.put(DatabaseColumns.valueOf(columnNames[i]),rs.getString(columnNames[i]));
                }
                resultList.add(resultData);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            Logger.log("Error while in running query "+e.getMessage());
            e.printStackTrace();
        }
        return resultList;
    }
}
