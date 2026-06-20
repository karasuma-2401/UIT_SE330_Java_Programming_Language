public class Ex10 {
    private int number = 0;
    private boolean hasNewNumber = false;
    private final int MAX_LIMIT = 100;

    public static void main(String[] args) {
        Ex10 lock = new Ex10();

        Thread thread1 = new Thread(() -> lock.displayNumbers());
        Thread thread2 = new Thread(() -> lock.checkEvenOdd());

        thread1.start();
        thread2.start();
    }

    public synchronized void displayNumbers() {
        for (int i = 1; i <= MAX_LIMIT; i++) {
            while (hasNewNumber) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number = i;
            System.out.print("Thread 1: " + number + " -> ");
            hasNewNumber = true;
            notify();
        }
    }

    public synchronized void checkEvenOdd() {
        for (int i = 1; i <= MAX_LIMIT; i++) {
            while (!hasNewNumber) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (number % 2 == 0) {
                System.out.println("Thread 2: Even");
            } else {
                System.out.println("Thread 2: Odd");
            }
            hasNewNumber = false;
            notify();
        }
    }
}