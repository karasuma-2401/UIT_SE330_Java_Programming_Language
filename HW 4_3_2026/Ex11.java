/*
Comparable là một interface có sẵn trong Java nhằm hỗ trợ xây dựng các lớp có thể so sánh được (ví dụ hỗ trợ sắp xếp) với khai báo như sau:
public interface Comparable <T> {
public int compareTo (T o); //1, 0, -1
}
class SinhVien implements Comparable <SinhVien>
class SinhVien implements Comparable
Xây dựng lớp sinh viên hiện thực interface trên. Thông tin sinh viên gồm họ tên (chuỗi) và điểm trung bình (số thực). 
Hai đối tượng sinh viên so sánh với nhau dựa trên điểm trung bình. 
Ngoài ra xây dựng lại phương thức toString() (public String toString()) trả về chuỗi gồm: [họ tên] + “_” + điểm trung bình.
Viết chương trình nhập vào một mảng sinh viên và xuất ra danh sách sinh viên có điểm trung bình tăng dần. 
Gợi ý: dùng lớp Arrays.sort(Object []), Arrays.toString(Object[])

*/

import java.util.Arrays;
import java.util.Scanner;

public class Ex11 {
    public static class SinhVien implements Comparable<SinhVien> {
        private String fullName;
        private double GPA;

        public SinhVien() {
            this.fullName = "";
            this.GPA = 0;
        }
        public SinhVien(String fullName, double GPA) {
            this.fullName = fullName;
            this.GPA = GPA;
        }
        public double getGPA () {return this.GPA;}
        public void input(Scanner sc) {
            System.out.println("Nhap ho ten sinh vien: ");
            this.fullName = sc.nextLine();
            System.out.println("Nhap vao GPA cua sinh vien");
            this.GPA = sc.nextDouble();
            sc.nextLine();
        }
        @Override 
        public String toString() {
            return "[" + this.fullName + "]" + "_" + this.GPA; 
        }
        @Override 
        public int compareTo (SinhVien sv) {
            return Double.compare(this.getGPA(), sv.getGPA());
        }
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao so n: ");
        int n = sc.nextInt();
        sc.nextLine();
        SinhVien[] list = new SinhVien[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Sinh vien thu " + (i + 1) + ": ");
            list[i] = new SinhVien();
            list[i].input(sc);
        }
        Arrays.sort(list);
        for (int i = 0; i < n; i++) {
            System.out.println(list[i].toString());
        }
    }
}

