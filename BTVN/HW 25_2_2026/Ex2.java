
import java.util.Scanner;

// 2. Viết chương trình giải phương trình bậc hai ax2 + bx + c = 0.
public class Ex2 {
    public static void giaiPhuongTrinhBacHai (double a, double b, double c) {
        // pt bac nhat
        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    System.out.println("Phuong trinh co vo so nghiem");
                }
                else {
                    System.out.println("Phuong trinh vo nghiem");
                }
            }
            else {
                System.out.println("Phuong trinh bac nhat co nghiem la: x = " + (double)-c/b );
            }
        }
        // pt bac hai
        else {
            //calculate delta
            double delta = b*b - 4*a*c;
            if (delta > 0) {
                double x1 = (-b + Math.sqrt(delta)) / (2*a);
                double x2= (-b - Math.sqrt(delta)) / (2*a);
                System.out.println("Phuong trinh co nghiem phan biet la:");
                System.out.println("x1= " + x1);
                System.out.println("x2= " + x2);
            }
            else if (delta == 0) {
                double x = -b/(2* a);
                System.out.println("Phuong trinh co nghiem kep la: x= " + x);
            }
            else {
                System.out.println("Phuong trinh vo nghiem");
            }
        }
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        double a, b, c;
        System.out.println("Nhap vao so a");
        a = sc.nextDouble();

        System.out.println("Nhap vao so b");
        b = sc.nextDouble();

        System.out.println("Nhap vao so c");
        c = sc.nextDouble();
        giaiPhuongTrinhBacHai(a, b, c);

        sc.close();
    }
}
