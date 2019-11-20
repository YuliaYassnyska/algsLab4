
package javaapplication5;

import java.io.*;
import java.util.*;

public class JavaApplication5 {
    
    public int getIndexIfContains(ArrayList<HashSet<Integer>> d, int a, int b) {
        for(int i=0; i<d.size(); ++i) {
            Set<Integer> set = d.get(i);
                if(set.contains(a) || set.contains(b)) 
                     return i;
        }          
       return -1;
    }
    
    public ArrayList<HashSet<Integer>> loadData(String file) {
      ArrayList<HashSet<Integer>> tribes = new ArrayList<HashSet<Integer>>();   
       try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            String []sm;
            
            in.readLine();
            while((line = in.readLine()) != null) {
                sm = line.split(" ");
                int a = Integer.parseInt(sm[0]);
                int b = Integer.parseInt(sm[1]);
                int index = getIndexIfContains(tribes, a, b);
                
                if(index == -1) {
                    HashSet<Integer> st = new HashSet<Integer>();
                    st.add(a);
                    st.add(b);
                    tribes.add(st);
                } else {
                    Set<Integer> set = tribes.get(index);
                    set.add(a);
                    set.add(b);
                }
           }
       } catch(IOException e) {
           e.printStackTrace();
       }
      return tribes;
    }
    
    public List<String> getPersonsPairs(Set<Integer> s1, Set<Integer> s2) {
        List<String> ls = new ArrayList<>();
        for(int a: s1){
            for(int b: s2){
                if((a % 2) != (b % 2)) {
                    ls.add(a + "/" + b);
                }
            }
        }
      return ls;
    }
    
    public final int calculate(String file) {
        List<String> result = new ArrayList<>();
        ArrayList<HashSet<Integer>> tribes = loadData(file);
        
        for(int i=0; i < tribes.size(); i++) {
            for(int j = i + 1; j < tribes.size(); ++j) {
                result.addAll(getPersonsPairs(tribes.get(i), tribes.get(j)));
            }
        }
        System.out.println(result);
       return result.size();
    }
    
    public JavaApplication5() {
        
        //* ********************************************************** */
        System.out.println("Count pairs: " + calculate("C:\\Users\\test\\eclipse-workspace\\algs4\\in.txt"));
    }
    
    public static void main(String[] args) {
        new JavaApplication5();      
    }
    
}
