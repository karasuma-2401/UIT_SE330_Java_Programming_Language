import java.util.Scanner;

public class Ex9 {
    public static <T extends Comparable<T>> int countGreaterThan(T[] arr, T value) {
        int count = 0;
        for (T element : arr) {
            if (element.compareTo(value) > 0) {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao so n: ");
        int n = sc.nextInt();
        Integer[] numbers = new Integer[n];
        for (int i = 0; i < n; i++) {
            System.out.printf("Nhap vao so thu %d: ", i + 1);
            numbers[i] = sc.nextInt();
        }
        int value = sc.nextInt();
        System.out.println(countGreaterThan(numbers, value));
    }
}
