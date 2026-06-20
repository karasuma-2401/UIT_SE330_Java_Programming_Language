package Ex12;

import java.io.File;
import java.util.Scanner;

public class FileCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter file/directory path: ");
        String path = sc.nextLine();

        File f = new File(path);

        if (f.exists()) {
            System.out.println("=> EXISTS!");
            System.out.println("Absolute path: " + f.getAbsolutePath());
            System.out.println("Size (bytes): " + f.length());
            System.out.println("Readable: " + (f.canRead() ? "Yes" : "No"));
            System.out.println("Writable: " + (f.canWrite() ? "Yes" : "No"));
            System.out.println("Is Directory: " + (f.isDirectory() ? "Yes" : "No"));
        } else {
            System.out.println("=> DOES NOT EXIST.");
        }
        
        sc.close();
    }
}
