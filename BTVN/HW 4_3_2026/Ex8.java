/* 
Xây dựng giao diện Measurable có phương thức Valuate có kiểu là số thực. 
Xây dựng lớp NhanVien (họ tên, mã nhân viên số giờ làm, giá theo giờ, lương = số giờ * giá theo giờ) hiện thực giao diện Measurable (Valuate trả về lương nhân viên).
Xây dựng lớp SinhVien (họ tên, mã SV, điểm toán, lý, hóa, ĐTB là trung bình của 3 môn) hiện thực giao diện Measurable (Valuate trả về điểm trung bình). 
Nhập vào n nhân viên, m sinh viên và tính lương trung bình của các nhân viên, điểm trung bình của các sinh viên vừa nhập. 
Yêu cầu: quản lý nhân viên và sinh viên chung 1 danh sách.
*/
import java.util.Scanner;

public class Ex8 {
    public interface  Measureable {
        public double Valuate ();
    }
    public static abstract class Person {
        protected String fullName;
        public Person() {
            fullName = "";
        }
        public Person(String fullName) {
            this.fullName = fullName;
        }
        public abstract void input(Scanner sc);
    }
    public static class NhanVien extends Person implements Measureable{
        private int maNV;
        private int workTime;
        private double costTimeWork;
        private double salary;
        
        public NhanVien() {
            super();
            this.maNV = 0;
            this.workTime = 0;
            this.costTimeWork = 0;
            this.salary = 0;
        }
        public NhanVien(String fullName, int maNV, int workTime, double costTimeWork) {
            super(fullName);
            this.maNV = maNV;
            this.workTime = workTime;
            this.costTimeWork = costTimeWork;
        }
        @Override
        public void input(Scanner sc) {
            System.out.println("Nhap vao ten nhan vien:");
            this.fullName = sc.nextLine();
            System.out.println("Nhap vao ma nhan vien: ");
            this.maNV = sc.nextInt();
            sc.nextLine();
            System.out.println("Nhap vao thoi gian lam");
            this.workTime = sc.nextInt();
            System.out.println("Nhap vao gia theo gio lam: ");
            this.costTimeWork = sc.nextDouble();
        }
        public String toString() {
            return "Nhan vine Ho ten: " + this.fullName + "| Ma NV: " + this.maNV + "| Thoi gian lam: " + workTime + "| Gia gio lam: " + costTimeWork +  "| Luong: " + Valuate();
        }   
        @Override
        public double Valuate() {
            this.salary = this.workTime * this.costTimeWork;
            return this.salary;
        }
    }
    public static class  SinhVien extends Person implements Measureable {
        private int mssv;
        private double math;
        private double physics;
        private double chemistry;
        private double GPA;

        public SinhVien() {
            super();
            this.mssv = 0;
            this.math = 0;
            this.physics = 0;
            this.chemistry = 0;
        }
        public SinhVien(String fullName, int mssv, double math, double physics, double chemistry) {
            super(fullName);
            this.mssv = mssv;
            this.math = math;
            this.physics = physics;
            this.chemistry = chemistry;
            this.GPA = 0;
        }
        @Override 
        public void input (Scanner sc) {
            System.out.println("Nhap vao ten sinh vien");
            this.fullName = sc.nextLine();
            System.out.println("Nhap vao Ma so sinh vien: ");
            this.mssv = sc.nextInt();
            System.out.println("Nhap vao diem mon toan");
            this.math = sc.nextDouble();
            System.out.println("Nhap vao diem mon ly: ");
            this.physics = sc.nextDouble();
            System.out.println("Nhap vao diem mon hoa: ");
            this.chemistry = sc.nextDouble();
        }
        @Override 
        public double Valuate() {
            this.GPA = (this.math + this.physics + this.chemistry) / 3;
            return this.GPA;
        }
        public String toString() {
            return "Sinh vien Ho ten: " + this.fullName + "| MSSV: " + this.mssv + " | Diem toan: " + this.math + " | Diem ly: " +  this.physics + " | Diem hoa: " + this.chemistry + "| GPA: " + this.Valuate();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao so nhan vien: ");
        int n = sc.nextInt();
        System.out.println("Nhao vao so sinh vien");
        int m = sc.nextInt();
        sc.nextLine();

        Person[] list = new Person[n + m];
        for (int i  = 0; i < n; i++) {
            System.out.println("Nhan vien thu " + (i + 1) + ": ");
            list[i] = new NhanVien();
            list[i].input(sc);
            sc.nextLine();
        }
        for (int i = 0; i < m; i++) {
            System.out.println("Sinh vien thu " + (i+1) + ": ");
            list[n + i] = new SinhVien();
            list[n + i].input(sc);
            sc.nextLine();
        }

        for(int i = 0; i < n+ m; i++) {
            System.out.println(list[i].toString());
        }

    }
}
