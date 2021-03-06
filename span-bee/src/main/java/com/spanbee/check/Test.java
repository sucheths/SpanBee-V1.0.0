package com.spanbee.check;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author sucheth.s
 *
 */

public class Test {
  
  
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
  static final String DB_URL = "jdbc:mysql://208.91.198.55/shopu8qk_spanbee";

  //  Database credentials
  static final String USER = "shopu8qk_spanbee";
  static final String PASS = "spanbee";
  
  public static void main(String[] args) {
  Connection conn = null;
  Statement stmt = null;
  try{
     //STEP 2: Register JDBC driver
     Class.forName("com.mysql.jdbc.Driver");

     //STEP 3: Open a connection
     System.out.println("Connecting to database...");
     conn = DriverManager.getConnection(DB_URL,USER,PASS);

     //STEP 4: Execute a query
     System.out.println("Creating statement... connection object is :::" +conn);
     stmt = conn.createStatement();
     
     stmt.close();
     conn.close();
  }catch(SQLException se){
     //Handle errors for JDBC
     se.printStackTrace();
  }catch(Exception e){
     //Handle errors for Class.forName
     e.printStackTrace();
  }finally{
     //finally block used to close resources
     try{
        if(stmt!=null)
           stmt.close();
     }catch(SQLException se2){
     }// nothing we can do
     try{
        if(conn!=null)
           conn.close();
     }catch(SQLException se){
        se.printStackTrace();
     }//end finally try
  }//end try
  System.out.println("Goodbye!");
  }
}

