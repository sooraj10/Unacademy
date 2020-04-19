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
interface Redis {
    
    public String get(String key);
    
    public String set(String key, String value);
    
    public void expire(Date date, String key);
    
    public void Zadd(String key, ArrayList<Object> value);
    
    public int Zrank(String key,String value);
    
    public ArrayList<Object> Zrange(String key, int start, int end);
            
            
    
}
