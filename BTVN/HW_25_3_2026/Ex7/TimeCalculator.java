package Ex7;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class TimeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("Enter the first time (HH:mm:ss): ");
        LocalTime time1 = inputTime(sc, timeFormatter);
        System.out.println("Enter the second time (HH:mm:ss): ");
        LocalTime time2 = inputTime(sc, timeFormatter);
        Duration duration = Duration.between(time1, time2);
        long totalSeconds = Math.abs(duration.getSeconds());
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        System.out.printf("Time distance %s and %s is:\n", time1.format(timeFormatter), time2.format(timeFormatter));
        System.out.printf("Total duration: %02d:%02d:%02d%n", hours, minutes, seconds);
        
        sc.close();
    }
    private static LocalTime inputTime(Scanner sc, DateTimeFormatter formatter) {
        while (true) {
            String input = sc.nextLine();
            try {
                return LocalTime.parse(input, formatter);
            } catch (Exception e) {
                System.out.println("=> Invalid time format. Please enter time in HH:mm:ss format: ");
            }
        }
    }
}


