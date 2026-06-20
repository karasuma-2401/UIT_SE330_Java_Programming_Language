import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Ex2 {
    static class DongHo implements Runnable {
        @Override 
        public void run () {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime currentTime = LocalTime.now();

            while (true) {
                System.out.println("Thoi gian: " + currentTime.format(formatter));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Dong ho bi ngat");
                    break;
                }
                currentTime = currentTime.plusSeconds(1);
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Dong ho bat dau hoat dong");
        Thread clockThread = new Thread(new DongHo());
        clockThread.start();
    }
}
