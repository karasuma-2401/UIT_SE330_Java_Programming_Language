import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.zone.ZoneRulesException;
import java.util.Scanner;

public class Ex8 {
    public static ZonedDateTime convert(LocalDateTime ldt, String target) throws ZoneRulesException {
        ZoneId lz = ZoneId.systemDefault();
        ZonedDateTime lzt = ZonedDateTime.of(ldt, lz);
        ZoneId tzId = ZoneId.of(target);
        return lzt.withZoneSameInstant(tzId);
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.print("Nhap vao ngay: ");
        int d = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap vao thang: ");
        int m = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap vao nam: ");
        int y = Integer.parseInt(sc.nextLine());
        
        System.out.print("Nhap gio: ");
        int h = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap phut: ");
        int min = Integer.parseInt(sc.nextLine());
        System.out.print("Nhap giay: ");
        int s = Integer.parseInt(sc.nextLine());

        LocalDateTime ldt = LocalDateTime.of(y, m, d, h, min, s);
        
        System.out.println("Local time: " + ldt.format(fmt) + " (" + ZoneId.systemDefault() + ")");

        System.out.println("Nhap vao mui gio");
        System.out.print("Mui gio: ");
        String tz = sc.nextLine().trim();

        ZonedDateTime res = convert(ldt, tz);

        System.out.println("Thoi gian tai [" + tz + "] la: " + res.format(fmt));
    }
}