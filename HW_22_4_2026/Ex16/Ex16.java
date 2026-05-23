package Ex16;

import java.util.Scanner;

public class Ex16 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your 4-digit PIN: ");
        String input = scanner.nextLine();

        try {
            validatePIN(input);
            System.out.println("PIN accepted: " + input);
        } catch (PINException e) {
            System.err.println("Invalid PIN: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static void validatePIN(String pin) throws PINException {
        if (pin == null || pin.length() != 4) {
            throw new PINException("PIN must be exactly 4 digits long.");
        }

        if (!pin.matches("\\d{4}")) {
            throw new PINException("PIN must contain only numeric digits.");
        }
    }
}