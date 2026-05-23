class MyRunnable implements Runnable {
    @Override
    public void run() {
        try {
            String name = Thread.currentThread().getName();
            int priority = Thread.currentThread().getPriority();
            for (int i = 1; i <= 10; i++) {
                System.out.println(name + " (Priority " + priority + "): " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted.");
        }
    }
}

public class Ex8 {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();

        Thread thread1 = new Thread(myRunnable, "Thread1");
        Thread thread2 = new Thread(myRunnable, "Thread2");

        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);

        thread1.start();
        thread2.start();
    }
}