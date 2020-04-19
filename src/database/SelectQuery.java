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

public class SelectQuery
{
     ConnectionManager connectionManager = new ConnectionManager();
     Connection conn = connectionManager.getConnection();
     public String getValue(String query)
     {
         String res = null;
         try
         {
             // create the java statement
             Statement st = conn.createStatement();
      
             // execute the query, and get a java resultset
             ResultSet rs = st.executeQuery(query);
      
             // iterate through the java resultset
             while (rs.next())
             {
                 res = rs.getString("rd_value");
             }
       
             st.close();
         }
       
       catch (Exception e)
       {
           System.err.println("Got an exception! ");
           System.err.println(e.getMessage());
       }
       finally
       {
            return res;
       }
   }
  
  
   public TreeSet<Object> getMultipleValue(String query)
   {
       TreeSet<Object> values = new TreeSet<>();
       try
         {
             // create the java statement
             Statement st = conn.createStatement();
      
             // execute the query, and get a java resultset
             ResultSet rs = st.executeQuery(query);
      
            // iterate through the java resultset
            while (rs.next())
            {
                Object res = rs.getString("rd_value");
                values.add(res);
            }
            st.close();
         }
       
       catch (Exception e)
       {
           System.err.println("Got an exception! ");
           System.err.println(e);
       }
       
       
       
       return values;
   }

   public java.sql.Date getDate(String query)
     {
         java.sql.Date res = null;
         try
         {
            // create the java statement
            Statement st = conn.createStatement();
      
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);
      
            // iterate through the java resultset
      
            while (rs.next())
            {
                res = rs.getDate("expiryDate");
            }
       
            st.close();
         }
         
         catch (Exception e)
        {
           
           System.err.println(e.getMessage());
        }
        finally
        {
            return res;
        }
   }
}