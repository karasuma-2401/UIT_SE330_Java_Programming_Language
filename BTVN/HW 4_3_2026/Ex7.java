/*
Viết chương trình tạo và sử dụng lớp số phức Complex gồm 2 thuộc tính: thực và ảo và các phương thức: 
khởi tạo, cộng, trừ, nhân, chia hai số phức, hiển thị thông tin số phức theo dạng: thực + ảo * i.
*/

import java.util.Scanner;

public class Ex7 {
    public static class Complex {
        private double real;
        private double imagine;

        public Complex () {
            this.real = 0;
            this.imagine = 0;
        }
        public Complex(double real, double imagine) {
            this.real = real;
            this.imagine = imagine;
        }
        public double getReal() {return real;}
        public double getImagine() {return imagine;}

        public void input(Scanner sc) {
            System.out.println("Nhap vao phan thuc");
            this.real = sc.nextDouble();
            System.out.println("Nhap vao phan ao");
            this.imagine = sc.nextDouble();
        }
        public String toString() {
            if (imagine == 0) 
                return String.valueOf(this.real);
            else if (real == 0) 
                return this.imagine + " * i";
            else if (imagine < 0) 
                return this.real + " - " + Math.abs(this.imagine) + " * i";
            return this.real + " + " + this.imagine + " * i";
        }
        public Complex plus (Complex c) {
            double newReal = this.real + c.real;
            double newImagine = this.imagine + c.imagine;
            return new Complex(newReal, newImagine);
        }
        public Complex minus (Complex c) {
            double newReal = this.real - c.real;
            double newImagine = this.imagine - c.imagine;
            return new Complex(newReal, newImagine);
        }
        public Complex times (Complex c) {
            double newReal = this.real * c.real - this.imagine * c.imagine;
            double newImagine = this.real * c.imagine + this.imagine * c.real;
            return new Complex(newReal, newImagine);
        }
        public Complex divide (Complex c) {
            if (c.real == 0 && c.imagine == 0) {
                System.out.println("Phep chia khong hop le vui long! Vui long thu lai");
                return new Complex();
            }
            double newReal = (this.real * c.real + this.imagine * c.imagine) / (c.real * c.real + c.imagine * c.imagine);
            double newImagine = (this.imagine * c.real - this.real * c.imagine) / (c.real * c.real + c.imagine * c.imagine);
            return new Complex(newReal, newImagine);
        }
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        Complex c1 = new Complex();
        Complex c2 = new Complex();
        System.out.println("Nhap vao so phuc thu nhat: ");
        c1.input(sc);
        System.out.println("Nhap vao so phuc thu hai: ");
        c2.input(sc);

        System.out.println("Tong hai so phuc la: " + c1.plus(c2).toString());
        System.out.println("Hieu hai so phuc la: "+ c1.minus(c2).toString());
        System.out.println("Tich hai so phuc la: " + c1.times(c2).toString());
        System.out.println("Chia hai so phuc la: " + c1.divide(c2).toString());
    }
}
