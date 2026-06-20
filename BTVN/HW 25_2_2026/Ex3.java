import java.util.Arrays;
import java.util.Scanner;
// 3. Nhập n số nguyên. Hãy sắp xếp giá trị của các số nguyên này theo thứ tự tăng dần.
public class Ex3 {
    public static void PrintArray (int a[], int n) {
        for (int i = 0; i < n; i++) {
            System.err.print(a[i] + " ");
        }
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("Nhap vao so n");
        n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Nhap vao so nguyen thu " + (i + 1) + ": ");
            a[i] = sc.nextInt();
        }
        System.out.print("Mang truoc khi sap xep tang dan: ");
        PrintArray(a, n);
        Arrays.sort(a);
        System.out.println("");
        
        System.out.print("Mang sau khi sap xep tang dan: ");
        PrintArray(a, n);

        sc.close();
    }
}
