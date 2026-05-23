import java.util.Scanner;
/*
7. Viết chương trình nhập vào mảng phân số(n phần tử) và xuất ra phân số nhỏ nhất,
lớn nhất của mảng vừa nhập.
*/
public class Ex7 {
    static Scanner sc = new Scanner(System.in);
    static class Fraction {
        private int numerator;
        private int denominator;
        
        public int getNumerator() {
            return numerator;
        }

        public int getDenominator() {
            return denominator;
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
        public void Simplfy () {
            int gcd = GCD(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;
            
            if (denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }
        }
        public void inputFraction () {
            System.out.println("Nhap vao tu so: ");
            numerator = sc.nextInt();
            do {
                System.out.println("Nhap vao mau so: ");
                denominator = sc.nextInt();
            }
            while (denominator == 0); 
            // {
            //     System.out.println("Mau so phai khac 0. Vui long nhap lai !");
            // }
            Simplfy();
        }
        public void outputFraction () {
            if (numerator % denominator == 0) {
                System.out.println(numerator/denominator);
            }
            else {
                System.out.println(numerator + "/" + denominator);
            }
        }
    }
    public static boolean smallerFraction(Fraction a, Fraction b) {
        return a.getNumerator() * b.getDenominator()
            < b.getNumerator() * a.getDenominator();
    }

    public static boolean largerFraction(Fraction a, Fraction b) {
        return a.getNumerator() * b.getDenominator()
            > b.getNumerator() * a.getDenominator();
    }
    public static void main (String[] args) {
        // Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao so phan tu cua mang: ");
        int n;
        n = sc.nextInt();
        Fraction[] fractions = new Fraction[n];
        for (int i = 0; i < n; i++) {
            fractions[i] = new Fraction();
            System.out.println("Nhap vao phan so thu " + (i + 1));
            fractions[i].inputFraction();
        }
        System.out.println("Mang cac phan so la: ");
        for (int i = 0; i < n; i++) {
            System.out.print("Phan so thu " + (i+1) + ": ");
            fractions[i].outputFraction();
            System.out.println();
        }

        Fraction min = fractions[0];
        Fraction max = fractions[0];
        for (int i = 1; i < n; i++) {
            if (smallerFraction(fractions[i] , min))
                min = fractions[i];
            if (largerFraction(fractions[i], max))
                max = fractions[i];
        }
        System.out.println("Phan so lon nhat trong mang la: ");
        max.outputFraction();
        System.out.println("Pha so nho nhat trong mang la: ");
        min.outputFraction();

        sc.close();
    }
}
