package Ex4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class TopStudentSearch {
    public static void main(String[] args) {
        String fileName = "students.txt";
        Student topStudent = null;
        double maxGpa = -1.0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue; 
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.err.println("Invalid format at line " + lineNumber + ": " + line);
                    continue; 
                }
            
                String id = parts[0].trim();
                String name = parts[1].trim();
                String gpaString = parts[2].trim();

                try {
                    double gpa = Double.parseDouble(gpaString);
                    if (gpa < 0.0 || gpa > 4.0) {
                        System.err.println("Invalid GPA value at line " + lineNumber + ": " + gpaString);
                        continue;
                    }
                    Student student = new Student(id, name, gpa);
                    if (gpa > maxGpa) {
                        maxGpa = gpa;
                        topStudent = student;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid GPA format at line " + lineNumber + ": " + gpaString);
                }
            }
            if(topStudent != null) {
                System.out.println("Top Student with highest GPA: " + topStudent);
            } else {
                System.out.println("No valid student data found.");
            }

        } catch (IOException e) {
            System.err.println("=> Error reading file: " + e.getMessage());
        }
    }
}