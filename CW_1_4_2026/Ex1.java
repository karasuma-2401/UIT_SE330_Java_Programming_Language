public class Ex1 {
    static class boilWater implements Runnable {
        @Override
        public void run () {
            System.out.println("[Dun nuoc] Bat dau bat bep dun nuoc...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("[Dun nuoc] Bi ngat");
            }
            System.out.println("[Dun nuoc] nuoc dang soi");
        }       
    }
    static class prepareIngredients implements Runnable {
        @Override
        public void run () {
            System.out.println("[chuan bi] Lay mi, them gia vi");
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                System.out.println("[Chuan bi] Bi ngat");
            }
            System.out.println("[Prepare] Nguyen lieu da chuan bi xong");
        }
    }
    public static void main(String[] args) {
        Thread boidWaterThread = new Thread(new boilWater());
        Thread prepareThread = new Thread(new prepareIngredients());

        boidWaterThread.start();
        prepareThread.start();

        try {
            boidWaterThread.join();
            prepareThread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Nuoc da soi va nguyen lieu da san sang");
        System.out.println("Tien hanh cho gia vi vao noi");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Mi dang chin");
        System.out.println("Nau mi xong");
    }
}
