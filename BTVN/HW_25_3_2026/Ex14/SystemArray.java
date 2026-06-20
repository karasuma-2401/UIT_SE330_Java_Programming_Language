package Ex14;

import java.util.Arrays;
import java.util.Scanner;

public class SystemArray {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String name = sc.nextLine();

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        if (n < 5) {
            System.out.println("=> Array size < 5. Exiting...");
            System.exit(0);
        }

        int[] arr = new int[n];
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[] copyArr = new int[n];
        System.arraycopy(arr, 0, copyArr, 0, n);

        System.setProperty("custom.user.name", name);
        String getName = System.getProperty("custom.user.name");

        System.out.println("=> Property Username: " + getName);
        System.out.println("=> Original Array: " + Arrays.toString(arr));
        System.out.println("=> Copied Array: " + Arrays.toString(copyArr));

        sc.close();
    }
}
