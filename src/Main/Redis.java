
package Main;

/**
 *
 * @author sooraj10
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
