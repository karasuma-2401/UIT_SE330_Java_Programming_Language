package Ex2;

import java.util.Scanner;

public class UrlManagement {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Website Management");

        String validUrl = "";
        while(true) {
            System.out.print("Enter a URL need to save:" );
            String inputUrl = sc.nextLine();
            if (UrlValidation.isValidUrl(inputUrl)) {
                validUrl = inputUrl;
                System.out.println("=> URL saved successfully.");
                break;
            } else {
                System.out.println("=> Invalid URL. Please try again.");
            }
        }
        sc.close();
    }
}
