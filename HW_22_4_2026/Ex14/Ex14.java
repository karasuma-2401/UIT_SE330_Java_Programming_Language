package Ex14;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Ex14 {
    private static final String URL_PATTERN = "^(https?://)?(www\\.)?[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,})+(/[\\w- ./?%&=]*)?$";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter website address: ");
        String url = scanner.nextLine();

        try {
            validateWebAddress(url);
            System.out.println("Valid web address: " + url);
        } catch (WebAddressException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    public static void validateWebAddress(String url) throws WebAddressException {
        if (url == null || url.trim().isEmpty()) {
            throw new WebAddressException("Web address cannot be empty.");
        }

        if (!Pattern.matches(URL_PATTERN, url)) {
            throw new WebAddressException("The format '" + url + "' is not a valid website address.");
        }
    }
}