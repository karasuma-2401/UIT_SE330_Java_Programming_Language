import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class Ex7 {
    public static String getDayOfWeek (int day, int month, int year) {
        LocalDate date = LocalDate.of(year, month, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        switch (dayOfWeek) {
            case MONDAY: return "Thu hai";
            case TUESDAY: return "Thu ba";
            case WEDNESDAY: return "Thu tu";
            case THURSDAY: return "Thu nam";
            case FRIDAY: return "Thu sau";
            case SATURDAY: return "Thu bay";
            case SUNDAY: return "Chu nhat";
            default: return "";
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap vao ngay");
        int day = Integer.parseInt(sc.nextLine());
        System.out.println("Nhap vao thang");
        int month = Integer.parseInt(sc.nextLine());
        System.out.println("Nhap vao nam: ");
        int year = Integer.parseInt(sc.nextLine());
        
        String dayOfWeek = getDayOfWeek(day, month, year);
        System.out.printf("Ngay %02d/%02d/%d la %s", day, month, year, dayOfWeek);
    }
}
