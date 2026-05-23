import java.util.Scanner;

public class Ex5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Nhap vao day du ho va ten cua khach hang: ");
        String fullName = sc.nextLine();
        System.out.println("Nhap ma dat cho: ");
        String bookCodeStr = sc.nextLine();

        fullName = fullName.trim().toUpperCase();

        StringBuffer bookingCode = new StringBuffer(bookCodeStr);
        while (bookingCode.length() < 6) {
            bookingCode.insert(0, "0");
        }
        for (int i = 0; i < bookingCode.length(); i++) {
            char c = bookingCode.charAt(i);

            if (Character.isLowerCase(c)) {
                bookingCode.replace(i, i + 1, String.valueOf(Character.toUpperCase(c)));
            }
            else if (!Character.isLetterOrDigit(c)) {
                bookingCode.replace(i, i + 1, "-");
            }
        }

        System.out.println("Ma dat cho sua khi xu ly: " + bookingCode);

        StringBuffer displayInfo = new StringBuffer();
        displayInfo.append("Hanh khach: ").append(fullName).append("\n");
        displayInfo.append("Ma dat cho: ").append(bookingCode);

        System.out.println(displayInfo.toString());
        System.out.println(displayInfo.reverse().toString());
    }
}
