import java.util.Scanner;

public class Ex4 {
/*
Tạo lớp Person gồm các thuộc tính: họ tên, tuổi và địa chỉ và các phương thức: khởi tạo, hiện thị thông tin. 
Tạo lớp NhanVien kế thừa từ lớp Nguoi và có thêm thuộc tính: lương cơ bản và hệ số có phương thức khởi tạo, tính lương (lương cơ bản * hệ số). 
Trong hàm main nhập vào danh sách các nhân viên và xuất ra tổng lương và nhân viên có lương cao nhất.
*/
    public static class Person {
        protected String fullName;
        protected int age;
        protected String address;

        public Person() {
            this.fullName = "";
            this.age = 0;
            this.address = "";
        }
        public Person (String fullname, int age, String address) {
            this.fullName = fullname;
            this.age = age;
            this. address = address;
        }
        public void input(Scanner sc) {
            System.out.println("Nhap vao ho va ten: ");
            this.fullName = sc.nextLine();
            System.out.println("Nhap vao tuoi: ");
            this.age = sc.nextInt();
            sc.nextLine();
            System.out.println("Nhap vao dia chi: ");
            this.address = sc.nextLine();
        }

        @Override
        public String toString () {
            return "Ho ten: " + this.fullName + ", tuoi: " + age + ", dia chi: " + address;
        }
    }
    public static class Employee extends Person {
        private double salary;
        private double coefficient;

        public Employee() {
            super();
            this.salary = 0;
            this.coefficient = 0;
        }
        public Employee (String fullName, int age, String address, long salary, int coefficient) {
            super(fullName, age, address);
            this.salary = salary;
            this.coefficient = coefficient;
        }
        public void setSalary (long salary) {this.salary = salary;}
        public double getSalary () {return this.salary;}

        public void setCoefficient (int coefficient) {this.coefficient = coefficient;}
        public double getCoefficient () {return this.coefficient;}

        @Override
        public void input(Scanner sc) {
            super.input(sc);
            System.out.println("Nhap vao luong: ");
            this.salary = sc.nextDouble();
            System.out.println("Nhap vao he so: ");
            this.coefficient = sc.nextDouble();
            sc.nextLine();
        }

        @Override 
        public String toString() {
            return super.toString() + "|| luong: " + String.valueOf(calculateSalary());
        }
        public double calculateSalary () {
            return this.salary * this.coefficient;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao so nhan vien: ");
        int n = sc.nextInt();
        sc.nextLine();
        Employee[] employees = new Employee[n];

        double sumOfSalary = 0;
        Employee employeeWithMaxSalary = new Employee();
        for (int i = 0; i < n; i ++) {
            System.out.println("Nhan vien thu " + (i +1));
            employees[i] = new Employee();
            employees[i].input(sc);
            sumOfSalary += employees[i].calculateSalary();

            if (employees[i].calculateSalary() > employeeWithMaxSalary.calculateSalary()) {
                employeeWithMaxSalary = employees[i];
            }
        }
        System.out.println("Tong luong cua nha vien la: " + String.valueOf(sumOfSalary));
        System.out.println("Nhan vien co luong cao nhat la: " + employeeWithMaxSalary.toString());
    }
}
