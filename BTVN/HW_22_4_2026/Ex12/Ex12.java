import java.util.Scanner;
public class Ex12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an integer string: ");
        String inputString = scanner.nextLine();

        try {
            int number = Integer.parseInt(inputString);
            System.out.println("Conversion successful! The number is: " + number);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: The input string '" + inputString + "' is not a valid integer format.");
        } finally {
            scanner.close();
            System.out.println("Program finished.");
        }
    }
}