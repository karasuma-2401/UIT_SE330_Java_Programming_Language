import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex11 {
    public static void main(String[] args) {
        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        System.out.println("Starting search for primes from 1 to 1,000,000 using " + numberOfThreads + " threads...");

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= 1000000; i++) {
            final int number = i;
            executor.execute(() -> {
                if (isPrime(number)) {
                    System.out.println(number);
                }
            });
        }
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.HOURS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Finished in: " + (endTime - startTime) + " ms");
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return n > 1;
    }
}