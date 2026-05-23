/* 
Viết chương trình quản lý nhân sự một công ty gồm các thành phần:
Giám đốc: gồm các thuộc tính họ tên, ngày sinh, hệ số lương, hệ số chức vụ
Quản lý: gồm các thuộc tính họ tên, ngày sinh, hệ số lương, số lương nhân viên quản lý
Nhân viên: gồm các thuộc tính họ tên, ngày sinh, hệ số lương, tên đơn vị (phòng/ban)
Chương trình thực hiện các yêu cầu sau:
Nhập vào danh sách gồm N nhân viên của công ty
Hiển thị thông tin các nhân viên
Xuất nhân viên có lương cao nhất (lương = (hệ số lương + hệ số chức vụ (nếu có)) * 1200000
Hiển thị các nhân viên sinh trong tháng 2
Xuất thông tin nhân viên thuộc phòng “Kế toán”
Đếm xem có bao nhiêu nhân viên tên là “An”.

*/
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ex10 {
    public static abstract class Member {
        protected String fullName;
        protected String dateBorn;
        protected double coefficientSalary; 
        public Member() {
            this.fullName = "";
            this.dateBorn = null;
            this.coefficientSalary = 0;
        }
        public Member(String fullName , String dateBorn, double coefficientSalary) {
            this.fullName = fullName;
            this.dateBorn = dateBorn;
            this.coefficientSalary = coefficientSalary;
        }
        public String getDateBorn () {return dateBorn;}
        public String getFullName () {return fullName;}
        public abstract double calculateSalary();

        public void input(Scanner sc) {
            System.out.println("Nhap vao ho ten: ");
            this.fullName = sc.nextLine();
            System.out.println("Nhap vao ngay sinh (yyyy-MM-dd): ");
            this.dateBorn = sc.nextLine();
            System.out.println("Nhap vao he so luong: ");
            this.coefficientSalary = sc.nextDouble();
            sc.nextLine();
        }
    }
    public static class Director extends Member {
        private double coefficientPos;
        public Director() {
            super();
            this.coefficientPos = 0;
        }
        public Director(String fullName, String dateBorn, int coefficientSalary, double coefficientPos) {
            super(fullName, dateBorn, coefficientSalary);
            this.coefficientPos = coefficientPos;
        }
        @Override
        public void input(Scanner sc) { 
            super.input(sc);
            System.out.println("Nhap he so chuc vu");
            this.coefficientPos = sc.nextDouble();
            sc.nextLine();
        }
        public String toString(){
            return "Giam doc: " + "Ho ten: " + this.fullName + "| Ngay Sinh: " + dateBorn + "| He so luong: " + this.coefficientSalary + "| He so chuc vu: " + this.coefficientPos;
        }
        public double calculateSalary() {
            return (this.coefficientSalary + this.coefficientPos) * 1200000;
        }
    }
    public static class Manager extends Member {
        private int numOfEmployees;
        public Manager() {
            super();
            this.numOfEmployees = 0;
        }
        public Manager(String fullName, String dateBorn, int coefficientSalary, int numOfEmployees) {
            super(fullName, dateBorn, coefficientSalary);
            this.numOfEmployees = numOfEmployees;
        }
        @Override
        public void input(Scanner sc) { 
            super.input(sc);
            System.out.println("Nhap so luong nhan vien quan ly: ");
            this.numOfEmployees  = sc.nextInt();
            sc.nextLine();
        }
        public String toString(){
            return "Quan ly: " + "Ho ten: " + this.fullName + "| Ngay Sinh: " + dateBorn + "| He so luong: " + this.coefficientSalary + "| So luong nhan vien quan ly: " + this.numOfEmployees;
        }
        public double calculateSalary() {
            return this.coefficientSalary * 1200000;
        }
    }
    public static class Employee extends Member {
        private String department;
        public Employee() {
            super();
            this.department = "";
        }
        public Employee(String fullName, String dateBorn, int coefficientSalary, String department) {
            super(fullName, dateBorn, coefficientSalary);
            this.department = department;
        }
        public String getDepartment () {return this.department;}

        @Override
        public void input(Scanner sc) { 
            super.input(sc);
            System.out.println("Nhap ten phong ban cua nhan vien: ");
            this.department = sc.nextLine();
        }
        public String toString(){
            return "Nhan vien: " + "Ho ten: " + this.fullName + "| Ngay Sinh: " + dateBorn + "| He so luong: " + this.coefficientSalary + "| Thuoc phong/ban: " + this.department;
        }
        public double calculateSalary() {
            return this.coefficientSalary * 1200000;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao so n: ");
        int n = sc.nextInt();
        sc.nextLine();

        Member[] list = new Member[n];
        for (int i = 0; i < n; i++) {
            System.out.println("1. Giam doc || 2. Quan ly || 3. Nhan vien");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    list[i] = new Director();
                    list[i].input(sc);
                    break;
                case 2: 
                    list[i] = new Manager();
                    list[i].input(sc);
                    break;
                case 3: 
                    list[i] = new Employee();
                    list[i].input(sc);
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai!");
                    i--;
                    break;
            }

        }
        System.out.println("---THONG TIN NHAN VIEN CUA CONG TY---");
        for (int i = 0; i < n; i++) {
            System.out.println(list[i].toString());
        }
        Member memberWithMaxSalary = list[0];
        for (int i = 0; i < n; i++) {
            if (memberWithMaxSalary.calculateSalary() < list[i].calculateSalary())
                memberWithMaxSalary = list[i];
        }
        System.out.println("Nhan vien co luong cao nhat la: " + memberWithMaxSalary.toString() + "|| Luong = " + memberWithMaxSalary.calculateSalary());
        System.out.println("--- NHAN VIEN SINH TRONG THANG 2---");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        for (int i = 0; i < n; i++) {
            LocalDate date = LocalDate.parse(list[i].getDateBorn(), formatter);
            if (date.getMonth() == Month.FEBRUARY) {
                System.out.println(list[i].toString());
            }
        }
        System.out.println("---NHAN VIEN THUOC PHONG KE TOAN---");
        for (int i = 0; i < n; i++) {
            if (list[i] instanceof Employee) {
                Employee employee = (Employee) list[i];
                if (employee.getDepartment().toLowerCase().contains("Ke toan"))
                    System.out.println(employee.toString());
            }
        }
        System.out.println("---NHAN VIEN CO TEN AN---");
        for (int i = 0; i < n; i++) {
            if (list[i].getFullName().toLowerCase().endsWith(" an")) {
                System.out.println(list[i].toString());
            }
        }
    }
}
