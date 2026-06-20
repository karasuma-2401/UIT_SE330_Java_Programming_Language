/*
Khai báo lớp PhanSo có các thuộc tính: tử số, mẫu số (số nguyên) và các phương thức: constructor (0, 1, 2 tham số), nhập, xuất, rút gọn, cộng, trừ, nhân, chia, so sánh (với một phân số khác). 
Sau đó viết chương trình cho phép nhập vào dãy n phân số, xuất ra:
Các phân số vừa nhập
Tổng các phân số
Xuất danh sách phân số theo thứ tự tăng dần
*/
import java.util.*;
public class Ex1 {
    public static class Fraction {
        private int numerator;
        private int denominator;

        public int getNumerator() {return numerator;}
        public int getDenominator() {return denominator;};

        public Fraction() {
            this.numerator = 0;
            this.denominator = 1;
        }
        public Fraction(int numerator) {
            this.numerator = numerator;
            this.denominator = 1;
        }
        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public int GCD (int a, int b) {
            a = Math.abs(a);
            b = Math.abs(b);

            while (b != 0) {
                int module = a % b;
                a = b;
                b = module;
            }
            return a;
        }
        public void simplfy () {
            int gcd = GCD(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;
            
            if (denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }
        }
        public void inputFraction (Scanner sc) {
            System.out.println("Nhap vao tu so: ");
            this.numerator = sc.nextInt();
            do {
                System.out.println("Nhap vao mau so: ");
                this.denominator = sc.nextInt();
                if (this.denominator == 0) {
                    System.out.println("Mau so phai khac 0. Hay nhap lai!");
                }
            }
            while (denominator == 0); 
            simplfy();
        }
        public void outputFraction () {
            if (this.numerator % this.denominator == 0) {
                System.out.print(this.numerator/ this.denominator);
            }
            else {
                System.out.print(this.numerator + "/" + this.denominator);
            }
        }
        public Fraction plus (Fraction f) {
            int newNumerator = this.numerator * f.denominator + f.numerator * this.denominator;
            int newDenominator = this.denominator * f.denominator;
            return new Fraction(newNumerator, newDenominator);
        }
        public Fraction minus (Fraction f) {
            int newNumerator = this.numerator * f.denominator - f.numerator * this.denominator;
            int newDenominator = this.denominator * f.denominator;
            return new Fraction(newNumerator, newDenominator);
        }
        public Fraction times (Fraction f) {
            int newNumerator = this.numerator * f.numerator;
            int newDenominator = this.denominator * f.denominator;
            return new Fraction(newNumerator, newDenominator);
        }
        public Fraction divide (Fraction f) {
            if (f.numerator == 0) {
                System.out.println ("Loi: Khong the thuc hien phep chia 0");
                return new Fraction();
            }
            int newNumerator = this.numerator * f.denominator;
            int newDenominator = this.denominator * f.numerator;
            return new Fraction(newNumerator, newDenominator);
        }
        public int compare (Fraction f) {
            double num1 = (double) this.numerator / this.denominator;
            double num2 = (double) f.numerator / f.denominator;
            return Double.compare(num1, num2);
        }
    }
    public static class FractionArray {
        private int n;
        private Fraction[] aFractions;

        public void input(Scanner sc) {
            System.out.println("Nhap vao n");
            n = sc.nextInt();
            aFractions = new Fraction[n];
            for (int i = 0; i < n; i++) {
                System.out.println("Phan so thu " + (i + 1) + ":");
                aFractions[i] = new Fraction();
                aFractions[i].inputFraction(sc);
            }
        }
        public void output (Scanner sc) {
            for (int i = 0; i < n; i++) {
                aFractions[i].outputFraction();
                System.out.print(" ");
            }
        }
        public Fraction calculateSum () {
            Fraction sum = new Fraction();
            for (int i = 0; i < n; i++) {
                sum = sum.plus(aFractions[i]);
            }
            sum.simplfy();
            return sum;
        }
        public void sortFraction () {
            for (int i = 0; i < n -1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (aFractions[i].compare(aFractions[j]) > 0) {
                        Fraction temp = aFractions[i];
                        aFractions[i] = aFractions[j];
                        aFractions[j] = temp;
                    }
                }
            }
        }
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        FractionArray list = new FractionArray();

        System.out.println("---INPUT FRACTION ARRAY---");
        list.input(sc);

        System.out.println("---OUTPUT FRACTION ARRAAY---");
        list.output(sc);
        System.out.println();

        Fraction sum = list.calculateSum();
        System.out.print("Tong cac phan so: ");
        sum.outputFraction();
        System.out.println();

        System.out.println("---THE LIST SORT FRACTION ARRAY---");
        list.sortFraction();
        list.output(sc);
    }
}