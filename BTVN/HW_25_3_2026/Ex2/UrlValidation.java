package Ex2;

public class UrlValidation {
    public static boolean isValidUrl (String url) {
        if (url == null || url.isEmpty()) {
            return false;
        }

        String urlRegex = "^https?://[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(/.*)?$";
        return url.matches(urlRegex);
    }
}
