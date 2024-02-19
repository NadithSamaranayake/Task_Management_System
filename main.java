import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

class Task
{
    LocalDateTime createDate;
    LocalDateTime targetTime;
    String taskName;
    String taskData;
    Task prevTask;
    Task nextTask;
    Boolean taskStatus;
    int taskPriority;
}

class List
{
    Task head;
    String name;

    public List(String text)
    {
        name = text;
        head = null;
    }

    public void addTask() {
        Task t1 = new Task();
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the task name : ");
        t1.taskName = scan.nextLine();

        System.out.print("Enter task information : ");
        t1.taskData = scan.nextLine();

        System.out.print("Enter the task priority (1 - 10): ");
        t1.taskPriority = scan.nextInt();
        scan.nextLine();

        System.out.print("Enter the task target date and time : ");
        String input = scan.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        t1.targetTime = LocalDateTime.parse(input, formatter);

        t1.taskStatus = false;

        t1.createDate = LocalDateTime.now();

        if (head == null) {
            t1.prevTask = null;
            t1.nextTask = null;
            head = t1;
        } else {
            Task current = head;
            while (current.nextTask != null) {
                current = current.nextTask;
            }
            current.nextTask = t1;
            t1.prevTask = current;
            t1.nextTask = null;
        }
    }

    public void markTaskCompleted(String taskName) {
        Task current = head;
        while (current != null) {
            if (current.taskName.equals(taskName)) {
                current.taskStatus = true;
                System.out.println("Task '" + taskName + "' marked as completed.");
                return;
            }
            current = current.nextTask;
        }
        System.out.println("Task '" + taskName + "' not found in the list.");
    }

    public void deleteTask(String taskName) {
        Task current = head;
        while (current != null) {
            if (current.taskName.equals(taskName)) {
                if (current.prevTask != null) {
                    current.prevTask.nextTask = current.nextTask;
                } else {
                    head = current.nextTask;
                }
                if (current.nextTask != null) {
                    current.nextTask.prevTask = current.prevTask;
                }
                System.out.println("Task '" + taskName + "' has been deleted.");
                return;
            }
            current = current.nextTask;
        }
        System.out.println("Task '" + taskName + "' not found in the list.");
    }

    public void displayTasks() {
        Task current = head;
        if (current == null) {
            System.out.println("No tasks in the list.");
            return;
        }
        System.out.println("\nTasks in the list:");
        int count = 1;
        while (current != null) {
            System.out.println("Task " + count + ": " +
                    "Name: " + current.taskName + "\t" +
                    "Info: " + current.taskData + "\t" +
                    "Priority: " + current.taskPriority + "\t" +
                    "Target Time: " + current.targetTime + "\t" +
                    "Status: " + (current.taskStatus ? "Completed" : "Incomplete") + "\t" +
                    "Create Date: " + current.createDate +"\n");
            current = current.nextTask;
            count++;
        }
    }
}

public class main {
    public static void main(String[] args) {
        List l1 = new List("School");
        l1.addTask();
        l1.addTask();
        l1.displayTasks();
        l1.markTaskCompleted("Blah");
        l1.deleteTask("Go to hospital");
    }
}