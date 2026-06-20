/*
Xây dựng lớp Point2D biểu diễn điểm trong mặt phẳng 2 chiều và các phương thức khởi tạo, 
lấy và thiết lập giá trị các thuộc tính, di chuyển, tính khoảng cách giữa 2 điểm, nhập, xuất. 
Trong hàm main cho phép nhập vào 2 điểm và một vector (để di chuyển) 
và xuất ra kết quả của việc di chuyển các điểm và khoảng cách giữa 2 điểm
*/

import java.util.Scanner;

public class Ex2 {
    public static class Point2D {
        private double x;
        private double y;

        public Point2D() {
            this.x = 0.0;
            this.y = 0.0;
        }
        public Point2D (double x, double y) {
            this.x = x;
            this.y = y;
        }
        public void setX (double x) {this.x = x;}
        public double getX () {return x;}
        
        public void setY (double y) {this.y = y;}
        public double getY () {return y;}

        public void input(Scanner sc) {
            System.out.println("Nhap vao x");
            this.x = sc.nextDouble();
            System.out.println("Nhap vao y");
            this.y = sc.nextDouble();
        }
        @Override
        public String toString () {
            return "(" + this.x + ", " + this.y + ")";
        }
        public double distance2Point (Point2D point) {
            return Math.sqrt((this.x - point.x) * (this.x - point.x) + (this.y - point.y) * (this.y - point.y));
        }
        public Point2D shift (double vectorX, double vectorY) {
            this.x += vectorX;
            this.y += vectorY;
            return this;
        }
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Point2D point1 = new Point2D();
        Point2D point2 = new Point2D();
        Point2D vector = new Point2D();

        System.out.println("NHAP VAO 2 DIEM");
        point1.input(sc);
        point2.input(sc);

        double firstDistance = point1.distance2Point(point2);
        System.out.println("Khoang cach ban dau cua 2 diem: " + firstDistance);

        System.out.println("Nhap vao vector");
        vector.input(sc);

        Point2D p1Shift = point1.shift(vector.getX(),vector.getY());
        System.out.println("Diem 1 sau khi di chuyen:" + p1Shift.toString());

        Point2D p2Shift = point2.shift(vector.getX(),vector.getY());
        System.out.println("Diem 2 sau khi di chuyen:" + p2Shift.toString());

        double shiftDistance = p1Shift.distance2Point(p2Shift);
        System.out.println("Khoang cach sau khi di chuyen cua 2 diem: " + shiftDistance);

    }
}
