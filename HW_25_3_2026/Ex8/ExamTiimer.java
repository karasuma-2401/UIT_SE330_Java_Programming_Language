package Ex8;

import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class ExamTiimer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=> Press enter to start the exam timer");
        sc.nextLine();

        Instant startTime = Instant.now();
        System.out.println("Exam timer started. Press enter to stop the timer.");

        sc.nextLine();
        Instant endTime = Instant.now();
        System.out.println("Exam timer stopped.");

        Duration timeElapsed = Duration.between(startTime, endTime);
        long totalSeconds = timeElapsed.getSeconds();

        long hourse = totalSeconds/ 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;

        System.out.println("Total time taken: " + hourse + " hours, " + minutes + " minutes, " + seconds + " seconds.");
    }
}
