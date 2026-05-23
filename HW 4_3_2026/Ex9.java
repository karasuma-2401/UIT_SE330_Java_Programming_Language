/* 
Viết chương trình tạo và sử dụng lớp MyMath biểu diễn lớp toán học gồm các phương thức static:
Tìm ước chung lớn nhất của 2 số nguyên
Tìm giá trị lớn nhất của 3 số thực
Tìm giá trị nhỏ nhất của 3 số thực
Kiểm tra một số có là số nguyên tố
Tính tổng dãy từ 1 đến N
Tính trị tuyệt đối của 1 số nguyên
Làm tròn một số thực
*/

import java.util.Scanner;

public class Ex9 {
    public static class MyMath {
        public static int gcd (int a, int b) {
            a = absolute(a);
            b = absolute(b);
            while( b != 0) { 
                int module = a % b;
                a = b;
                b = module;
            }
            return a;
        }
        public static double largerValueOfTwoNumber (double a, double b) {
            return a >= b ? a : b;
        }
        public static double maxValueOfThreeNumber(double a, double b, double c) {
            return largerValueOfTwoNumber(a, largerValueOfTwoNumber(b, c)) ;
        } 
        public static double smallerValueOfTwoNumber (double a, double b) {
            return a <= b ? a : b;
        }
        public static double minValueOfThreeNumber (double a, double b, double c) {
            return smallerValueOfTwoNumber(a, smallerValueOfTwoNumber(b, c));
        }
        public static boolean isPrime (int n) {
            for (int i = 2; i*i <= n; i++) {
                if (n % i ==0)  
                    return false;
            }
            return n > 1;
        }
        public static int sumToN (int n ) {
            return n * (n + 1) /2;
        }
        public static int absolute(int n) {
            if (n < 0) return n * -1;
            return n;
        }
        public static long round(double n) {
            if (n >= 0) 
                return (long) (n + 0.5);
            else 
                return (long) (n - 0.5);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("1. UCLN cua hai so nguyen");
            System.out.println("2. Gia tri lon nhat cua 3 so thuc");
            System.out.println("3. Gia tri nho nhat cua 3 so thuc");
            System.out.println("4. Kiem so nguyen to");
            System.out.println("5. Tong tu 1 den N");
            System.out.println("6. gia tri tuyet doi cua 1 so nguyen");
            System.out.println("7. Lam tron 1 so thuc");
            System.out.println("Khac. Thoat");
                                
            System.out.println("Nhap vao lua chon cua ban: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Nhap vao hai so nguyen: ");
                    int a = sc.nextInt();
                    int b = sc.nextInt();
                    System.out.println("UCLN cua hai so " + a + " va " + b + " : " + MyMath.gcd(a, b));
                    break;
                case 2: 
                    System.out.println("Nhap vao 3 so thuc x, y, z");
                    double x = sc.nextDouble();
                    double y = sc.nextDouble();
                    double z = sc.nextDouble();
                    System.out.println("Gia tri lon nhat trong 3 so la: " + MyMath.maxValueOfThreeNumber(x, y, z));
                    break;
                case 3: 
                    System.out.println("Nhap vao 3 so thuc x, y, z");
                    double _x = sc.nextDouble();
                    double _y = sc.nextDouble();
                    double _z = sc.nextDouble();
                    System.out.println("Gia tri nho nhat trong 3 so la: " + MyMath.minValueOfThreeNumber(_x, _y, _z));
                    break;
                case 4: 
                    System.out.println("Nhap vao so n");
                    int n = sc.nextInt();
                    if (MyMath.isPrime(n)) {
                        System.out.println("So "+ n + " la so nguyen to ");
                    }
                    else {
                        System.out.println("So "+ n + " khong phai la so nguyen to ");
                    }
                    break;
                case 5: 
                    System.out.println("Nhap vao so N");
                    int N = sc.nextInt();
                    System.out.println("Tong day tu 1 den "+ N + " la: " + MyMath.sumToN(N));
                    break;
                case 6: 
                    System.out.println("Nhap vao so k");
                    int k = sc.nextInt();
                    System.out.println("Gia tri tuyet doi cua so "+ k + " la " + MyMath.absolute(k));
                    break;
                case 7: 
                    System.out.println("Nhap vao mot so thuc:");
                    double r = sc.nextDouble();
                    System.out.println("So thuc "+ r + " sau khi lam tron la: " + MyMath.round(r));
                    break;
                default:
                    return;
            }
            sc.close();
        }

    }
}
