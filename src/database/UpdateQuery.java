/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author hp
 */


import java.sql.*;
import java.util.Calendar;
import java.util.*;

public class UpdateQuery
{
     ConnectionManager connectionManager = new ConnectionManager();
     Connection conn = connectionManager.getConnection();
  
  public void insert(String query,String key, Object value)
  {
      try
    {
      // create a sql date object so we can use it in our INSERT statement
      Calendar calendar = Calendar.getInstance();
      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
      
      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setString (1, key);
      preparedStmt.setObject (2, value);
     
      // execute the preparedstatement
      preparedStmt.execute();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e);
    }
      
  }
  
  public void delete(String query)
  {
      try
    {
      // create a sql date object so we can use it in our INSERT statement
      Calendar calendar = Calendar.getInstance();
      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
      
      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
    
      // execute the preparedstatement
      preparedStmt.executeUpdate(query);
    }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e);
    }
  }
  
  
  public void Zadd(String query, String key, ArrayList<String> values)
  {
       try
    {
      // create a sql date object so we can use it in our INSERT statement
      Calendar calendar = Calendar.getInstance();
      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

      // the mysql insert statement
     
      // create the mysql insert preparedstatement
      for(String value : values)
      {
          PreparedStatement preparedStmt = conn.prepareStatement(query);
          preparedStmt.setString (1, key);
          preparedStmt.setString (2, value);
     
          // execute the preparedstatement
          preparedStmt.execute();
      }
    }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e);
    }
  }
  
   public void insertExpiryDate(String query, String key, java.util.Date date)
  {
      try
    {
      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      java.sql.Date sqlDate=new java.sql.Date(date.getTime());
      preparedStmt.setString (1, key);
      preparedStmt.setDate (2, sqlDate);
     
      // execute the preparedstatement
      preparedStmt.execute();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e);
    }
  }
}