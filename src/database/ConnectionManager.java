/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author sooraj10
 */
import java.sql.*;

import java.util.*;
import java.io.*;

public class ConnectionManager {
    
    public Connection getConnection()
    {
        Connection conn = null;
          try
         {
            //fetching valyes from properties file which can be changed in future
             Properties   properties = new Properties();
             properties .load(new FileInputStream(new File("C:\\Users\\hp\\Documents\\"
                     + "NetBeansProjects\\Unacademy\\src\\credential.properties")));

             String username = properties.getProperty("username");
             String password = properties.getProperty("password");
             String myDriver = properties.getProperty("driver");
             String myUrl =    properties.getProperty("url");
             Class.forName(myDriver);
      
            conn = DriverManager.getConnection(myUrl, username, password);
      
    }
      catch (Exception e)
      {
      
           System.err.println(e);
      
      }
          
           return conn;  
    }
    
    
}
