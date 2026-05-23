package Ex17;

import java.util.Scanner;

public class Ex17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your phone number: ");
        String input = scanner.nextLine();

        try {
            validatePhoneNumber(input);
            System.out.println("Phone number accepted: " + input);
        } catch (PhoneException e) {
            System.err.println("Invalid Phone Number: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static void validatePhoneNumber(String phone) throws PhoneException {
        if (phone == null || phone.isEmpty()) {
            throw new PhoneException("Phone number cannot be empty.");
        }
        if (!phone.matches("^0\\d{9}$")) {
            throw new PhoneException("Phone number must start with '0' and contain exactly 10 digits.");
        }
    }
}