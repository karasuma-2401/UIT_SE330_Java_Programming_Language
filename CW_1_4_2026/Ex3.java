import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Ex3 {
    static class Product {
        private String id;
        private String name;
        private double price;
        private int quantity;

        public Product(String id, String name, double price, int quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getId() { return id; }
        public String getName() { return name; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }

        @Override
        public String toString() {
            return "Ma: " + id + " | Ten: " + name + " | Gia: " + price + " | So luong: " + quantity;
        }
    }

    public static class StoreManagement {
        private List<Product> productList = new ArrayList<>();
        private Scanner scanner = new Scanner(System.in);

        public void addProduct() {
            System.out.print("Nhap vao ma san pham: ");
            String id = scanner.nextLine();

            for (Product p : productList) {
                if (p.getId().equalsIgnoreCase(id)) {
                    System.out.println("Ma san pham da ton tai!");
                    return;
                }
            }

            System.out.print("Nhap vao ten san pham: ");
            String name = scanner.nextLine();
            System.out.print("Nhap vao gia san pham: ");
            double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Nhap so luong hang hoa: ");
            int quantity = Integer.parseInt(scanner.nextLine());

            productList.add(new Product(id, name, price, quantity));
            System.out.println("San pham da duoc them thanh cong");
        }

        public void displayList() {
            if (productList.isEmpty()) {
                System.out.println("Danh sach hien dang trong");
                return;
            }
            for (Product p : productList) {
                System.out.println(p);
            }
        }

        public void deleteProduct() {
            System.out.print("Nhap vao ma cua san pham muon xoa: ");
            String id = scanner.nextLine();
            
            boolean removed = productList.removeIf(p -> p.getId().equalsIgnoreCase(id));
            if (removed) {
                System.out.println("San pham da bi xoa");
            } else {
                System.out.println("Khong tim thay san pham voi ma: " + id);
            }
        }

        public void updateProduct() {
            System.out.print("Nhap vao id cua san pham muon cap nhat: ");
            String id = scanner.nextLine();

            for (Product p : productList) {
                if (p.getId().equalsIgnoreCase(id)) {
                    System.out.print("Chon thong tin muon cap nhat (1: Gia, 2: So luong): ");
                    String choice = scanner.nextLine();
                    if (choice.equals("1")) {
                        System.out.print("Nhap gia moi: ");
                        p.setPrice(Double.parseDouble(scanner.nextLine()));
                        System.out.println("Gia duoc cap nhat thanh cong!");
                    } else if (choice.equals("2")) {
                        System.out.print("Nhap vao so luong ton kho: ");
                        p.setQuantity(Integer.parseInt(scanner.nextLine()));
                        System.out.println("So luong duoc cap nhat thanh cong!");
                    } else {
                        System.out.println("Lua chon khong hop le");
                    }
                    return;
                }
            }
            System.out.println("Khong tim thay san pham voi ma: " + id);
        }

        public void searchProduct() {
            System.out.print("Nhap vao tu khoa san pham: ");
            String keyword = scanner.nextLine().toLowerCase();
            boolean found = false;
            for (Product p : productList) {
                if (p.getName().toLowerCase().contains(keyword)) {
                    System.out.println(p);
                    found = true;
                }
            }
            if (!found) System.out.println("Khong tim thay san pham nao!");
        }

        public void sortList() {
            System.out.print("1. Gia tang dan  2. So luong giam dan: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                productList.sort(Comparator.comparingDouble(Product::getPrice));
                System.out.println("Sap xep theo gia san pham tang dan!");
            } else if (choice.equals("2")) {
                productList.sort((p1, p2) -> Integer.compare(p2.getQuantity(), p1.getQuantity()));
                System.out.println("Sap xep theo so luong giam dan");
            } else {
                System.out.println("Lua chon khong hop le");
            }
        }

        public void showStatistics() {
            int totalStock = 0;
            double totalValue = 0;

            for (Product p : productList) {
                totalStock += p.getQuantity();
                totalValue += (p.getPrice() * p.getQuantity());
            }
            System.out.println("Tong loai san pham: " + productList.size());
            System.out.println("Tong san pham trong kho: " + totalStock);
            System.out.println("Tong gia tri hang ton kho: " + totalValue);
        }

        public void showMenu() {
            while (true) {
                System.out.println("\n=== QUAN LY SAN PHAM ===");
                System.out.println("1. Them san pham");
                System.out.println("2. Hien thi tat ca cac san pham");
                System.out.println("3. Xoa san pham theo ma");
                System.out.println("4. Cap nhat gia hoac so luong");
                System.out.println("5. Tim kiem theo tu khoa");
                System.out.println("6. Sap xep danh sach san pham");
                System.out.println("7. Hien thi thong ke");
                System.out.println("0. Thoat");
                System.out.print("Lua chon tinh nang: ");
                
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1": addProduct(); break;
                    case "2": displayList(); break;
                    case "3": deleteProduct(); break;
                    case "4": updateProduct(); break;
                    case "5": searchProduct(); break;
                    case "6": sortList(); break;
                    case "7": showStatistics(); break;
                    case "0": System.out.println("Goodbye!"); return;
                    default: System.out.println("Hay chon lua chon co trong menu.");
                }
            }
        }
    }

    public static void main(String[] args) {
        StoreManagement sm = new StoreManagement();
        sm.showMenu();
    }
}