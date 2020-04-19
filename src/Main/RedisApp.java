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
import java.util.*;
import java.text.*;
public class RedisApp {

    
    public static void main(String[] args) {
       
        Redis redis = new RedisImpl();
       
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the operation id you want to perform in Redis ");
        System.out.println("1: GET     2: SET     3: EXPIRE      4: ZADD     5: ZRANK        6: ZRANGE");
        int option = sc.nextInt();
        
                
        switch(option)
        {
	 case 1:{
	   System.out.println("Enter the key, if found you will get value otherwise nil will be returned");
           String key = sc.next();
           String value = redis.get(key);
           System.out.println(value);
	   break;
         }
	 case 2:{
	   System.out.println("Enter the key and value");
           String key = sc.next();
           String value = sc.next();
           redis.set(key, value);
	   break;
         }
	 case 3:{
	   
           String str[] = {"year", "month", "day" };
           String date = "";

           for(int i=0; i<3; i++) {
                System.out.println("Enter " + str[i] + ": ");
                date = date + sc.next() + "/";
            }
            date = date.substring(0, date.length()-1);
           
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date parsedDate = null;

            try {
            parsedDate = dateFormat.parse(date);
            System.out.println("Enter thr key : ");
            String key = sc.next();
            redis.expire(parsedDate, key);
            }   
            catch (Exception e) {
        
            }
	   break;
         }
	 case 4:{
             System.out.println("Enter the key");
             String key = sc.next();
             System.out.println("Enter the ampunt of values you wanna addd into this key");
             int size = sc.nextInt();
             ArrayList<Object> values = new ArrayList<>();
             for(int i=0;i<size;i++)
             {
                 Object obj = sc.next();
                 values.add(obj);
             }
             redis.Zadd(key, values);
          
             break;
         }
         case 5: {
             System.out.println("Enter the key and corresponding value you wanna get rank for");
             System.out.println("Enter the key");
             String key = sc.next();
             String value = sc.next();
             int rank = redis.Zrank(key, value);
             System.out.println("Rank of " + value + ":" + rank);
             break;
         }
         case 6: {
             System.out.println("Enter the key and corresponding range you wanna get values of");
             System.out.println("Enter the key");
             String key = sc.next();
             
             System.out.println("Enter the start index");
             int start = sc.nextInt();
             
             System.out.println("Enter the end index");
             int end = sc.nextInt();
             
             ArrayList<Object> response = redis.Zrange(key, start, end);
             
             System.out.println("Response of your query");
             for(Object obj : response)
                 System.out.println(obj + " ");
             if(response.size() == 0)
                 System.out.println("null");
             break;
         }
	 default:
	   System.out.println("Invalid option, try again");
      }
        
        
        
    }
    
}
