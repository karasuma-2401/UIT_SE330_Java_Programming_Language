import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/*
8. Viết hàm nhập, xuất 1 mảng gồm n phần tử (số nguyên) và thực hiện các yêu cầu
• Đếm số phần tử chẵn và lẻ
• Tính giá trị trung bình của mảng
• Tìm phần tử lớn nhất và nhỏ nhất
• Xuất mảng theo chiều ngược lại
• Sắp xếp mảng tăng dần và xuất ra kết quả
*/
public class Ex8 {
    static ArrayList<Integer> a = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Input array function
    public static void inputArray () {
        System.out.println("Nhap vao so phan tu cua mang: ");
        int n = sc.nextInt();
        a.clear();

        for (int i = 0; i < n; i++) {
            System.out.println("Phan tu thu " + i + " trong mang: ");
            a.add(sc.nextInt());
        }
    }
    // Output array function
    public static void outputArray () {
        for (int i = 0; i < a.size(); i++) {
            System.out.print(a.get(i) + " ");
        }
        System.out.println();
    }
    // sort ascend function 
    public static void sortASC() {
        Collections.sort(a);
        System.out.println("Mang sau khi sap xep tang dan la:");
        outputArray();
    }
    // count even and odd of array function
    public static void countEvenOddElement() {
        int countEven = 0;
        int countOdd = 0;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) % 2 == 0) 
                countEven++;
            else 
                countOdd++;
        }
        System.out.println("So phan tu chan trong mang la: " + countEven);
        System.out.println("So phan tu le trong mang la: " + countOdd);
    }
    // calculate DTB of array function
    public static double calculateAverage () {
        double average = 0;
        for (int x : a) {
            average += x;
        }
        average /= a.size();
        return average;
    }
    // find max and element in array function
    public static void findMaxMinElement() {
        int max = a.get(0);
        int min = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            if (a.get(i) < min)
                min = a.get(i);
            if (a.get(i) > max) 
                max = a.get(i);
        }
        System.out.println("Phan tu lon nhat trong mang la: " + max);
        System.out.println("Phan tu nho nhat trong mang la: " + min);
    }
    public static void reverseOutputArray () {
        for (int i = a.size() - 1; i >= 0; i--) {
            System.out.print(a.get(i) + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int query;
        while (true) {
            System.out.println("1. Nhap cac phan tu cua mang");
            System.out.println("2. Xuat cac phan tu cua mang");
            System.out.println("3. Dem so phan tu chan va le");
            System.out.println("4. Gia tri trung binh cua mang");
            System.out.println("5. Tim phan tu lon nhat va nho nhat");
            System.out.println("6. Xuat mang theo chieu nguoc lai");
            System.out.println("7. Sap xep mang tang dan va xuat ra ket qua");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            query = sc.nextInt();
            if (query == 0) break;
            switch (query) {
                case 1: inputArray(); 
                    break;
                case 2: outputArray(); 
                    break;
                case 3: countEvenOddElement();
                    break;
                case 4: 
                    double average = calculateAverage();
                    System.out.println("Gia tri trung binh cua mang la: " + average); 
                    break;
                case 5: findMaxMinElement();
                    break;
                case 6: reverseOutputArray();
                    break;
                case 7: 
                    sortASC();
                    break;
                default:
                    System.out.println("Lua chon khong hop le ! Hay chon lai");
                    break;
            }
        }
        sc.close();
    }
}
