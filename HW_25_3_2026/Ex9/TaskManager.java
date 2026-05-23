package Ex9;

import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;

public class TaskManager {
    public static void main(String[] args) {
        Queue<String> taskQueue = new LinkedList<>();
        Scanner sc = new Scanner(System.in);
        int query = 0;

        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. Handle Task");
            System.out.println("3. Display Tasks in queue");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                query = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("=> Invalid input. Please enter a number.");
                continue;
            }

            switch (query) {
                case 1:
                    System.out.print("Enter task name: ");
                    String taskName = sc.nextLine();
                    if (!taskName.trim().isEmpty()) {
                        taskQueue.offer(taskName);
                        System.out.println("=> Task added to queue: " + taskName);
                    } else {
                        System.out.println("=> Task name cannot be empty.");
                    }
                    break;
                case 2:
                    if (!taskQueue.isEmpty()) {
                        System.out.println("=> Handling task: " + taskQueue.poll());
                    } else {
                        System.out.println("=> Task queue is empty. No tasks to handle.");
                    }
                    break;
                case 3:
                    if (!taskQueue.isEmpty()) {
                        System.out.println("=> Tasks in queue:");
                        int index = 1;
                        for (String task : taskQueue) {
                            System.out.println(index++ + ". " + task);
                        }
                    } else {
                        System.out.println("=> Task queue is empty.");
                    }
                    break;
                case 4:
                    System.out.println("=> Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
