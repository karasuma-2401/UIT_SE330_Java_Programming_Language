import java.util.*;

public class Ex6 {
    private static final List<Integer> primeList = new ArrayList<>();
    private static boolean isFinished = false;

    public static void main(String[] args) {
        Thread findThread = new Thread(() -> {
            for (int i = 1000; i <= 1000000; i++) {
                if (isPrime(i)) {
                    synchronized (primeList) {
                        primeList.add(i);
                    }
                }
            }
            synchronized (primeList) {
                isFinished = true;
                primeList.notify();
            }
            System.out.println("FindThread: Task completed.");
        });

        Thread sumThread = new Thread(() -> {
            long totalSum = 0;
            int lastProcessedIndex = 0;

            while (true) {
                synchronized (primeList) {
                    while (lastProcessedIndex >= primeList.size() && !isFinished) {
                        try {
                            primeList.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    while (lastProcessedIndex < primeList.size()) {
                        totalSum += primeList.get(lastProcessedIndex);
                        lastProcessedIndex++;
                    }

                    if (isFinished && lastProcessedIndex >= primeList.size()) {
                        break;
                    }
                }
            }
            System.out.println("SumThread: Total sum of primes is " + totalSum);
        });

        findThread.start();
        sumThread.start();
    }

    private static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}