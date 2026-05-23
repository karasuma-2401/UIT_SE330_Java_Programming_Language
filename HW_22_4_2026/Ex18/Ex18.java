package Ex18;

import java.util.Scanner;

public class Ex18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter side A: ");
            double a = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter side B: ");
            double b = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter side C: ");
            double c = Double.parseDouble(scanner.nextLine());

            validateTriangle(a, b, c);
            System.out.println("Valid triangle edges: " + a + ", " + b + ", " + c);

        } catch (TriangleEdgesException e) {
            System.err.println("Geometry Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Input Error: Please enter valid numeric values for edges.");
        } finally {
            scanner.close();
        }
    }

    public static void validateTriangle(double a, double b, double c) throws TriangleEdgesException {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new TriangleEdgesException("All edges must be greater than zero.");
        }

        if ((a + b <= c) || (a + c <= b) || (b + c <= a)) {
            throw new TriangleEdgesException("The sum of any two edges must be greater than the third edge.");
        }
    }
}