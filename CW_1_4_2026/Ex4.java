import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Ex4 {
    static ArrayList<Integer> a = new ArrayList<>(); 
    static ArrayList<Integer> b = new ArrayList<>(); 
    static ArrayList<Integer> c = new ArrayList<>(); 
    static int m; 
    // static int n;
    static void Input(ArrayList<Integer> a , Scanner sc) 
    {
        String s = sc.nextLine(); 
        a.clear();
       a.addAll(Arrays.stream(s.split(" ")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new))); 

    } 
    public static void main(String args[]) 
    {
        Scanner sc = new Scanner(System.in); 
        m = sc.nextInt(); 
        sc.nextLine(); 
        Input(a , sc); 
        Input(b, sc ); 
        Input(c, sc); 
        HashMap<Integer, Integer> mp = new HashMap<>(); 
        HashMap<Integer , Integer> mp2 = new HashMap<>(); 
        HashMap<Integer, Integer> mp3 = new HashMap<>(); 
        for (Integer x : a) {
            mp.put(x , 1); 
        } 
        for (Integer x : b) mp2.put(x , 1); 
        for (Integer x : c) mp3.put(x , 1); 
        for (Integer x : a) {
            if (mp.containsKey(x) && mp2.containsKey(x) && mp3.containsKey(x)) continue; 
            else System.out.printf("%d ", x); 
        }
        System.out.println();
        for (Integer x : b) {
            if (mp.containsKey(x) && mp2.containsKey(x) && mp3.containsKey(x)) continue; 
            else System.out.printf("%d ", x); 
        }
        System.out.println();
        for (Integer x : c) {
            if (mp.containsKey(x) && mp2.containsKey(x) && mp3.containsKey(x)) continue; 
            else System.out.printf("%d ", x); 
        }
    }
    
}
