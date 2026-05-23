package Ex15;

import java.util.Scanner;

public class Ex15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your age: ");
        String input = scanner.nextLine();

        try {
            int age = validateAge(input);
            System.out.println("Valid age: " + age);
        } catch (AgeException e) {
            System.err.println("Custom Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Format Error: Please enter a valid integer.");
        } finally {
            scanner.close();
        }
    }

    public static int validateAge(String input) throws AgeException {
        int age = Integer.parseInt(input);

        if (age < 0 || age > 150) {
            throw new AgeException("Age must be between 0 and 150. Input received: " + age);
        }
        
        return age;
    }
}