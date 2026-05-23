/*
1. Viết chương trình đăng ký tài khoản, cho phép người dùng nhập vào email và mật
khẩu, phải nhập đúng định dạng email dạng (example@domain.com) , mật khẩu cần ít
nhất 8 kí tự, ít nhất 1 chữ cái thường, 1 chữ cái hoa, có số và kí tự đặc biệt
*/
package Ex1;
import java.util.Scanner;

public class RegisterAccount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Register an account:");
        String validEmail = "";
        String validPassword = "";

        while (true) {
            System.out.print("Enter your email: ");
            String email = sc.nextLine();
            if (Validation.isValidEmail(email)) {
                validEmail = email;
                break;
            } else {
                System.out.println("=> Invalid email format. Please enter a valid email.");
            }
        }
        while (true) {
            System.out.print("Enter your password: ");
            String password = sc.nextLine();
            String passwordError = Validation.getPasswordErrorMessage(password);
            if (passwordError == null || passwordError.isEmpty()) {
                validPassword = password;
                System.out.println("Password is valid.");
                break;
            } else {
                System.out.println("=> Error: " + passwordError);
            }
        }
        Account account = new Account(validEmail, validPassword);
        System.out.println("Account registered successfully!");
        sc.close();
    }
}
