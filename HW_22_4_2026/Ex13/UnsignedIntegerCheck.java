package Ex13;
import java.util.Scanner;

public class UnsignedIntegerCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a positive integer: ");
        String input = scanner.nextLine();

        try {
            validateUnsignedInteger(input);
            System.out.println("Success! '" + input + "' is a valid unsigned integer.");
        } catch (UnsignedIntegerException e) {
            System.err.println("Custom Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Format Error: The input is not a valid numeric string.");
        } finally {
            scanner.close();
        }
    }
    public static void validateUnsignedInteger(String str) throws UnsignedIntegerException {
        int value = Integer.parseInt(str);
        if (value < 0) {
            throw new UnsignedIntegerException("Number '" + value + "' is negative. Only positive integers are allowed.");
        }
    }
}