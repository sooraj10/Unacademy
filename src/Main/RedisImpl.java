/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author hp
 */
import database.*;
import java.util.*;
import java.sql.*;
import java.text.*;
public class RedisImpl implements Redis {
    
    public String get(String key)
    {
        String value = null;
        try{
              //checking whether the key is expired or not
              String query = "select expiryDate from expire where rd_ky = \"";
              query = query + key + "\"";
             
              SelectQuery selectQuery = new SelectQuery();
              
              java.sql.Date expiryDate = selectQuery.getDate(query);
              
              java.util.Date currDate = new java.util.Date();
              
              //if the key is not expired then fetch the value
               if (expiryDate.compareTo(currDate) >= 0) {
                   
                   query = "select rd_value from simple_redis where rd_ky = \"";
                   query = query + key + "\"";
    
                   SelectQuery selectquery = new SelectQuery();
      
                   value = selectquery.getValue(query);
                   
               }
                 
        }
         catch(Exception e)
         {
             // if key is not expired fetch the value
             String query = "select rd_value from simple_redis where rd_ky = \"";
             query = query + key + "\"";
    
             SelectQuery selectquery = new SelectQuery();
      
             value = selectquery.getValue(query);
             
         }
       
      
       return value;
    }
    
    
     public String set(String key, String value)
     {
         try{
             //deleting the previous value of key if exists
             String query = "delete from simple_redis where rd_ky = \"";
             query = query + key + "\"";
        
             UpdateQuery updateQuery = new UpdateQuery();
        
             updateQuery.delete(query);
                
             //updating the new value of key
             query = "insert into simple_redis (rd_ky,rd_value) values (?,?)";
        
             updateQuery.insert(query, key, value);
             
             // removing the expiry date of the key
             query = "delete from expire where rd_ky = \"";
             query = query + key + "\"";
             
             return "ok";
        
        }
         catch(Exception e)
         {
             System.err.println(e);
             return "NULL";
         }
        
     }
     
     public void expire(java.util.Date date,String key)
     {
         try{
             // deleting the previous expiry date of the key
             String query = "delete from expire where rd_ky = \"";
             query = query + key + "\"";
        
             UpdateQuery updateQuery = new UpdateQuery();
        
             updateQuery.delete(query);
        
             query = "insert into expire (rd_ky,expiryDate) values (?,?)";
             
             // updating the new expiry date
             updateQuery.insertExpiryDate(query, key, date);
                    
        }
         catch(Exception e)
         {
             System.err.println(e);
             
         }
         
     }
     
     public void Zadd(String key, ArrayList<Object> values)
     { 
         try{
             
             UpdateQuery updateQuery = new UpdateQuery();
        
             String query = "insert into z_redis (rd_ky,rd_value) values (?,?)";
             
             //inserting into database
             for(Object value : values)
                 updateQuery.insert(query, key, value);
             
             // deleting the previous expiry date of key
             query = "delete from expire where rd_ky = \"";
             query = query + key + "\"";
        
         
        }
         catch(Exception e)
         {
             System.err.println(e);
           
         }
     }
     
     public int Zrank(String key, String value)
     {
         //returns -1 if key does not exist
         int rank = -1;
         try{
             //checking whether key is expired or not
              String query = "select expiryDate from expire where rd_ky = \"";
              query = query + key + "\"";
             
              SelectQuery selectQuery = new SelectQuery();
              
              java.sql.Date expiryDate = selectQuery.getDate(query);
              
              java.util.Date currDate = new java.util.Date();
              
              //if the key is not expired
               if (expiryDate.compareTo(currDate) >= 0) {
                   
                   query = "select rd_value from z_redis where rd_ky = \"";
                   query = query + key + "\"";
         
                   SelectQuery selectquery = new SelectQuery();
         
                   TreeSet<Object> values = selectquery.getMultipleValue(query);
         
                   rank = values.headSet(value).size();
         
                   if(values.isEmpty())
                        rank = -1;
                   
               }
                 
        }
         catch(Exception e)
         {
             //if the key is not expired
             String query = "select rd_value from z_redis where rd_ky = \"";
             query = query + key + "\"";
         
             SelectQuery selectquery = new SelectQuery();
         
             TreeSet<Object> values = selectquery.getMultipleValue(query);
         
             rank = values.headSet(value).size();
         
             if(values.isEmpty())
                 rank = -1;
             
         }
         
         return rank; 
         
     }
     
     public ArrayList<Object> Zrange(String key, int start, int end)
     {
        ArrayList<Object> response = new ArrayList<>();
        
         try{
              //checking whether key is expired or not
              String query = "select expiryDate from expire where rd_ky = \"";
              query = query + key + "\"";
             
              SelectQuery selectQuery = new SelectQuery();
              
              java.sql.Date expiryDate = selectQuery.getDate(query);
              
              java.util.Date currDate = new java.util.Date();
              
              //if key is not expired
              if (expiryDate.compareTo(currDate) >= 0) {
                   
                   query = "select rd_values from z_redis where rd_ky = \"";
                   query = query + key + "\"";
         
                   SelectQuery selectquery = new SelectQuery();
                   
                   //sorted set for storing values
                   TreeSet<Object> values = selectquery.getMultipleValue(query);
         
                   Iterator<Object> itr = values.iterator();
        
                   Object currentElement = null;
                   int currentIndex = 0;
        
                   while(itr.hasNext()){
            
                          currentElement = itr.next();
            
                          if(currentIndex >= start && currentIndex <= end){
                                      response.add(currentElement);
                                }
            
                          currentIndex++; 
            
                         if(currentIndex > end)
                         break;
                    }
                   
               }
                 
        }
         catch(Exception e)
         {
             // if key is not expired
             String query = "select rd_values from z_redis where rd_ky = \"";
             query = query + key + "\"";
         
             SelectQuery selectquery = new SelectQuery();
             
             //sorted set for storing values
             TreeSet<Object> values = selectquery.getMultipleValue(query);
         
             Iterator<Object> itr = values.iterator();
        
             Object currentElement = null;
             int currentIndex = 0;
        
             while(itr.hasNext()){
            
             currentElement = itr.next();
            
             if(currentIndex >= start && currentIndex <= end){
                response.add(currentElement);
            }
            
            currentIndex++; 
            
            if(currentIndex > end)
                break;
            }
         }
        
        
         return response;
         
     }
}
