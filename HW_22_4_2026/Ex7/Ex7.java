import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ex7 {
    private final String fileName = "Number.txt";
    private boolean hasNewData = false;
    private boolean isFinished = false;
    private int lastReadValue = 0;

    public static void main(String[] args) {
        Ex7 lock = new Ex7();
        
        Thread writerThread = new Thread(() -> lock.writePrimes());
        Thread readerThread = new Thread(() -> lock.readPrimes());

        writerThread.start();
        readerThread.start();
    }

    public synchronized void writePrimes() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 1000; i <= 10000; i++) {
                if (isPrime(i)) {
                    while (hasNewData) {
                        wait();
                    }
                    writer.println(i);
                    writer.flush();
                    hasNewData = true;
                    notify();
                }
            }
            while (hasNewData) {
                wait();
            }
            isFinished = true;
            notify();
            System.out.println("WriterThread: Finished writing all primes.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void readPrimes() {
        try {
            while (!isFinished) {
                while (!hasNewData && !isFinished) {
                    wait();
                }
                
                if (hasNewData) {
                    readLastLine();
                    hasNewData = false;
                    notify();
                }
            }
            System.out.println("ReaderThread: Finished reading.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void readLastLine() {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            int currentVal = 0;
            while (scanner.hasNextInt()) {
                currentVal = scanner.nextInt();
            }
            System.out.println("ReaderThread read: " + currentVal);
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }
    }

    private boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}