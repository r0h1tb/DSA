import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();

        for ( String str : strs ){
            int[] freq = new int[26];

            for( char c : str.toCharArray()){
                freq[c-'a']++;
            }
            String key = Arrays.toString(freq);
            if (map.containsKey(key)){
                map.get(key).add(str); 
            }
            else{
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            }
            
        }
         return new ArrayList<>(map.values());
    }
}
