/*
Viết chương trình tạo lớp Time biểu diễn thời gian gồm: giờ, phút, giây và các phương thức: khởi tạo, 
lấy và thiết lập giá trị các thuộc tính, tăng giây lên 1 đơn vị, so sánh 2 đối tượng thời gian. 
Trong hàm main, nhập vào 2 thời gian, xuất ra thời gian lớn hơn và gọi hàm tăng thời gian (thứ nhất) mỗi giây.
*/

import java.util.Scanner;

public class Ex3 {
    public static class Time {
        private int hour;
        private int minute;
        private int second;

        public Time () {
            this.hour = 0;
            this.minute = 0;
            this.second = 0;
        }
        public Time (int hour, int minute, int second) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }

        public void setHour (int hour) {this.hour = hour;}
        public int getHour () {return this.hour;}
        public void setMinute (int minute) {this.minute = minute;}
        public int getMinute () {return this.minute;}
        public void setSecond (int second) {this.second = second;}
        public int getSecond () {return this.second;}

        public void inuput (Scanner sc) {
            System.out.println("Nhap vao gio");
            this.hour = sc.nextInt();
            System.out.println("Nhap vao phut");
            this.minute = sc.nextInt();
            System.out.println("Nhap vao giay");
            this.second = sc.nextInt();
        }
        @Override
        public String toString () {
            return this.hour + "h : " + this.minute + "m : " + this.second + "s";
        }
        public int compare (Time T) {
            int time1 = this.hour * 3600 + this.minute * 60+ this.second;
            int time2 = T.hour * 3600 + T.minute * 60 + T.second;
            if (time1 > time2) return 1;
            else if (time1 == time2) return 0;
            return -1;
        }
        public Time increaseOneSecond () {
            int time = this.hour*3600 + this.minute * 60 + this.second + 1;
            this.hour = (time / 3600);
            this.minute = (time % 3600) / 60;
            this.second = time % 60;
            return this;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Time t1 = new Time();
        Time t2 = new Time();

        System.out.println("Nhap vao thoi gian thu nhat: ");
        t1.inuput(sc);
        System.out.println("Nhap vao thoi gian thu hai: ");
        t2.inuput(sc);

        if (t1.compare(t2) > 0) {
            System.out.println("Thoi gian thu nhat lon hon thoi gian thu hai: " + t1.toString());
        }
        else if (t1.compare(t2) < 0) {
            System.out.println("Thoi gian thu hia lon hon thoi gian thu nhat: " + t2.toString());
        }
        else {
            System.out.println("Hai thoi gian bang: " + t1.toString());
        }

        System.out.println("Thời gian 1 sau khi tang: " + t1.increaseOneSecond().toString());
    }
}