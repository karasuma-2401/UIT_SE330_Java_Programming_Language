import java.util.Scanner;
import java.util.Comparator;

public class Ex10 {
    public static <T> T findMax(T[] arr, Comparator<T> cmp) {
        if (arr == null || arr.length == 0) {
            return null; 
        }

        T max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (cmp.compare(arr[i], max) > 0) {
                max = arr[i];
            }
        }
        return max;
    }
    public static void main(String[] args) {
        String[] words = {"apple", "banana", "cherry"};
        Comparator<String> cmp = (a, b) -> a.compareTo(b);
        System.out.println("Max string: " + findMax(words, cmp));

    }
}
