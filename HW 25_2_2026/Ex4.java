import java.util.Scanner;
// 4. Nhập vào tháng và năm. Cho biết tháng đó có bao nhiêu ngày.
public class Ex4 {
    public static boolean leafYear (int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0); 
    }

    public static int DaysOfMonth (int month, int year) {
        int DaysOfFeb = leafYear(year) ? 29 : 28;
        int a[] = {31, DaysOfFeb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return a[month -1];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int month, year;
        System.out.println("Nhap vao thang: ");
        month = sc.nextInt();
        System.out.println("Nhap vao nam: ");
        year = sc.nextInt();

        System.out.println("Thang "+ month + " Nam " + year + " co " + DaysOfMonth(month, year));
        sc.close();
    }
}
