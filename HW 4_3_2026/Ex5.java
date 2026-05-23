/*
Khai báo lớp Shape là lớp trừu tượng, có phương thức tính diện tích và phương thức toString (xuất ra tên hình và diện tích). 
Xây dựng lớp Rectangle và Circle kế thừa lớp Shape để định nghĩa hình chữ nhật và hình tròn. 
Trong hàm main cho phép nhập vào các hình và đếm xem có bao nhiêu hình chữ nhật, hình tròn và xuất ra hình có diện tích lớn nhất.
*/

import java.util.Scanner;

public class Ex5 {
    public static abstract class Shape {
        protected String name;
        public Shape() {
            this.name = "";
        }
        public Shape (String name) {
            this.name = name;
        }
        public abstract void input(Scanner sc);
        public abstract double calculateArea() ;
        @Override
        public String toString() {
            return "Hinh: " + name + "| Dien tich: " + String.valueOf(calculateArea());
        }
    }
    public static class Rectangle extends Shape {
        private double length;
        private double width;
        
        public Rectangle () {
            super("Hinh chu nhat");
            this.length = 0;
            this.width = 0;
        }
        public Rectangle (String name, double length, double width) {
            super("Hinh chu nhat");
            this.length = length;
            this.width = width;
        }
        public void setLength (double length) {this.length = length;}
        public double getLength () {return this.length;}

        public void setWidth (double width) {this.width = width;}
        public double getWidth () {return this.width;}

        @Override
        public void input(Scanner sc) {
            System.out.println("Nhap vao chieu dai: ");
            this.length = sc.nextDouble();
            System.out.println("Nhap vao chieu rong: ");
            this.width = sc.nextDouble();
        }
        @Override 
        public double calculateArea() {
            return this.width * this.length;
        }
    }

    public static class Circle extends Shape {
        private double radius;
        public Circle () {
            super("Hinh tron");
            this.radius = 0;
        }
        public Circle(String name, double radius) {
            super("Hinh tron");
            this.radius = radius;
        }
        public void setRadius(double radius) {this.radius = radius;}
        public double getRadius () {return radius;}

        @Override
        public void input(Scanner sc) {
            System.out.println("Nhap vao ban kinh: ");
            this.radius = sc.nextDouble();
        }
        @Override 
        public double calculateArea() {
            return Math.PI * radius * radius;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao n: ");
        int n = sc.nextInt();
        Shape[] shapes = new Shape[n];

        int countRec = 0;
        int countCir = 0;
        Shape shapeWithMaxArea = null;

        for (int i = 0; i < n; i++) {
            System.out.println("Hinh thu " + (i + 1));
            System.out.println("Lua chon: 1.Hinh chu nhat || 2. Hinh tron");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    shapes[i] = new Rectangle();
                    shapes[i].input(sc);
                    countRec ++;
                    break;
                case 2:
                    shapes[i] = new Circle();
                    shapes[i].input(sc);
                    countCir++;
                    break;
                default:
                    System.out.println("Lua chon khong hop le. Hay chon lai!");
                    shapes[i] = new Rectangle();
                    break;
            }
            if (shapeWithMaxArea == null || shapeWithMaxArea.calculateArea() < shapes[i].calculateArea()) {
                shapeWithMaxArea = shapes[i];
            }
        }
        System.out.println("So hinh chu nhat la: " + String.valueOf(countRec));
        System.out.println("So hinh tron la: " + String.valueOf(countCir));
        System.out.println("HInh co dien tich lon nhat la: " + shapeWithMaxArea.toString());
    }
}
