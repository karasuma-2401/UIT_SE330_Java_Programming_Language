import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;
// 6. Viết chương trình xuất ra lịch của một năm (do người dùng nhập vào)
public class Ex6 {
    public static boolean leafYear (int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0); 
    }

    public static int DaysOfMonth (int month, int year) {
        int DaysOfFeb = leafYear(year) ? 29 : 28;
        int a[] = {31, DaysOfFeb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return a[month -1];
    }
    public static int translateInterger (DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case SUNDAY:
                return 0;
            case MONDAY: 
                return 1;
            case TUESDAY:
                return 2;
            case WEDNESDAY: 
                return 3;
            case THURSDAY:
                return 4;
            case FRIDAY: 
                return 5;
            case SATURDAY: 
                return 6;
        }
        return 0;
    }
    public static void printCalendar (int month, int year) {
        System.out.println("CN T2 T3 T4 T5 T6 T7");
        int days = DaysOfMonth(month, year);

        // return thu of first day of month
        LocalDate firstDay = LocalDate.of(year, month, 1);
        int firstDayOfWeek = translateInterger(firstDay.getDayOfWeek());
        
        for (int i = 0; i < firstDayOfWeek; i++) {
            System.out.print("   ");
        }
        for (int day = 1; day <= days; day++) {
            System.out.printf("%2d ", day);
            // when end week, jumb next line
            if ((day + firstDayOfWeek) % 7 == 0) 
                System.out.println();
        }
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int month, year;
        System.out.println("Nhap vao thang: ");
        month = sc.nextInt();
        System.out.println("Nhap vao nam: ");
        year = sc.nextInt();

        printCalendar(month, year);
        sc.close();
    }
}
