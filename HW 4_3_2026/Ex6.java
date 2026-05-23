/*
Viết chương trình quản lý sinh viên gồm các tính năng: 
Nhập danh sách sinh viên
Xem danh sách sinh viên
Sắp xếp và hiển thị danh sách sinh viên theo điểm trung bình tăng dần
Tìm kiếm sinh viên theo tên (tên cần tìm do người dùng nhập)
Xuất ra thông tin các sinh viên có họ là “Lê”
*/

import java.util.Scanner;

public class Ex6 {
    public static class Student {
        private int mssv;
        private String fullName;
        private int age;
        private double GPA;

        public Student() {
            this.mssv = 0;
            this.fullName = "";
            this.age = 0;
            this.GPA = 0;
        }
        public Student(int mssv, String fullName, int age, double GPA) {
            this.mssv = mssv;
            this.fullName = fullName;
            this.age = age;
            this.GPA = GPA;
        }

        public double getGPA() { return this.GPA; }
        public String getFullName() { return this.fullName; }

        public void input(Scanner sc) {
            System.out.println("Nhap vao mssv: ");
            this.mssv = sc.nextInt();
            sc.nextLine();
            System.out.println("Nhap vao ho va ten: ");
            this.fullName = sc.nextLine();
            System.out.println("Nhap vao age: ");
            this.age = sc.nextInt();
            sc.nextLine();
            System.out.println("Nhap vao diem trung binh: ");
            this.GPA = sc.nextDouble();
            sc.nextLine();
        }
        public String toString() {
            return "MSSV: " + this.mssv + "| Ho ten: " + fullName + "| Tuoi: " + age + "| GPA: " + GPA;
        }
    }
    public static class ListStudent {
        private int n;
        private Student[] list;
        
        public ListStudent() {
            this.n = 0;
            this.list = null;
        }
        public void input(Scanner sc) {
            System.out.println("Nhap vao n");
            n = sc.nextInt();
            sc.nextLine();
            list = new Student[n];
            for (int i = 0; i < n; i++) {
                System.out.println("Sinh vien thu "+ (i + 1));
                list[i] = new Student();
                list[i].input(sc);
            }
        }
        public void output() {
            for (int i = 0; i < n; i++) {
                System.out.println(list[i].toString());
            }
        }
        public void sortAscGPA () {
            for (int i = 0; i < n -1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (list[i].getGPA() > list[j].getGPA()) {
                        Student temp = list[i];
                        list[i] = list[j];
                        list[j] = temp;
                    }
                }
            }
        }
        public void searchStudentByName (String searchFullName) {
            boolean isFound = false;
            searchFullName = searchFullName.toLowerCase();
            for (int i = 0; i < n; i++ ){
                if (list[i].getFullName().toLowerCase().contains(searchFullName)) {
                    System.out.println(list[i].toString());
                    isFound = true;
                }
            }
            if (!isFound) {
                System.out.println("Khong tim thay sinh vien nao trong danh sach co chu " + searchFullName);
            }
        }
        public void searchStudentWithLe () {
            boolean isFound = false;
            for (int i = 0; i < n; i++ ){
                if (list[i].getFullName().toLowerCase().startsWith("le ")) {
                    System.out.println(list[i].toString());
                    isFound = true;
                }
            }
            if(!isFound)
                System.out.println("Khong tim thay sinh vien nao trong danh co ho Le");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListStudent list = new ListStudent();
        list.input(sc);
        System.out.println("Danh sach sinh vien truoc khi sap xep");
        list.output();
        System.out.println("Danh sach sinh vien sau khi sap xep theo DTB tang dan");
        list.sortAscGPA();
        list.output();

        System.out.println("Nhap ten sinh muon tim kiem");
        String searchFullName = sc.nextLine();
        list.searchStudentByName(searchFullName);

        System.out.println("Danh sach sinh vien co ho Le ");
        list.searchStudentWithLe();

    }
}
