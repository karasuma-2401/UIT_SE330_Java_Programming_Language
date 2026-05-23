import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/*
5. Viết chương trình quản lý một dãy số nguyên gồm các tính năng: nhập, xuất dãy; cho
phép thêm, xóa, sửa các số trong dãy; sắp xếp dãy số tăng dần; tính giá trị trung bình
của dãy và cho biết phần tử nào gần với giá trị trung bình nhất.
 */
public class Ex5 {
    static ArrayList<Integer> a = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Input array function
    public static void inputArray () {
        System.out.println("Nhap vao so phan tu cua day: ");
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
    // add element function
    public static void addElememt () {
        System.out.println("Nhap vao so muon them: ");
        int x = sc.nextInt();
        a.add(x);
    }
    // delete element function
    public static void deleteElement() {
        System.out.println("Nhap vao index muon xoa: ");
        int index = sc.nextInt();
        if (index >= 0 && index < a.size()){
            a.remove(index);
        }
        else {
            System.out.println("Index khong hop le");
        }
    }
    // Fix element function
    public static void fixElement () {
        System.out.println("Nhap vao index muon sua: ");
        int index = sc.nextInt();

        if (index >= 0 && index < a.size()){
            System.out.println("Nhap vao gia tri muon sua: ");
            a.set(index, sc.nextInt());
        }
        else {
            System.out.println("Index khong hop le");
        }
    }
    // sort ascend function 
    public static void sortASC() {
        Collections.sort(a);
        System.out.println("Mang sau khi sap xep tang dan la:");
        outputArray();
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
    // Element have value near average function
    public static void elementNearAverage () {
        double average = calculateAverage();
        int result = 0;
        double min = Math.abs(a.get(0) - average);

        for (int i = 0; i < a.size(); i++) {
            if (Math.abs(a.get(i) - average) <  min) {
                min = Math.abs(a.get(i) - average);
                result = a.get(i);
            }
        }
        System.out.println("Phan tu gan gia tri trung binh nhat la: " + result);
    }

    public static void main(String[] args) {
        int query;
        while (true) {
            System.out.println("1. Nhap cac phan tu cua mang");
            System.out.println("2. Xuat cac phan tu cua mang");
            System.out.println("3. Them phan tu moi");
            System.out.println("4. Xoa phan tu cua mang (theo index)");
            System.out.println("5. Sua phan tu cua mang (theo index)");
            System.out.println("6. Sap xep mang tang dan");
            System.out.println("7. Gia tri trung binh cua mang");
            System.out.println("8. Phan tu gan gia tri trung binh nhat");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            query = sc.nextInt();
            if (query ==0) break;
            switch (query) {
                case 1: inputArray(); 
                    break;
                case 2: outputArray(); 
                    break;
                case 3: addElememt(); 
                    break;
                case 4: deleteElement(); 
                    break;
                case 5: fixElement(); 
                    break;
                case 6: sortASC(); 
                    break;
                case 7: 
                    double average = calculateAverage();
                    System.out.println("Gia tri trung binh cua mang la: " + average); 
                    break;
                case 8: 
                    elementNearAverage();
                    break;
                default:
                    System.out.println("Lua chon khong hop le ! Hay chon lai");
                    break;
            }
        }
        sc.close();
    }
}
