import java.util.Scanner;
public class Ex6 {
    public static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        String specialCharacters = "!@#$%^&*()_+={}::;'<>,.?/";
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (specialCharacters.contains(String.valueOf(c))) {
                hasSpecial = true;
            }
        }
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password;
        System.out.println("Yêu cầu mật khẩu:");
        System.out.println("- Ít nhất 8 ký tự.");
        System.out.println("- Chứa ít nhất 1 chữ HOA, 1 chữ thường, 1 chữ số.");
        System.out.println("- Chứa ít nhất 1 ký tự đặc biệt");

        while (true) {
            System.out.print("Nhap mat khau cua ban: ");
            password = scanner.nextLine();

            if (isValidPassword(password)) {
                System.out.println("Nat khau hop le");
                break; 
            } else {
                System.out.println("Mat khau khong hop le. Hay thu lai !");
            }
        }

        StringBuilder reversedPassword = new StringBuilder(password);
        reversedPassword.reverse();
        System.out.println("1. Dao nguoc mat khau: " + reversedPassword.toString());
        StringBuilder xPassword = new StringBuilder(password);
        for (int i = 1; i < xPassword.length() - 1; i++) {
            xPassword.setCharAt(i, '*');
        }
        System.out.println("2. Phiên bản ẩn danh: " + xPassword.toString());
        StringBuilder simpleEncrypted = new StringBuilder(password);
        for (int i = 0; i < simpleEncrypted.length(); i++) {
            if (Character.isDigit(simpleEncrypted.charAt(i))) {
                simpleEncrypted.setCharAt(i, 'X');
            }
        }
        System.out.println("3. Mã hóa đơn giản: " + simpleEncrypted.toString());
    }
}
