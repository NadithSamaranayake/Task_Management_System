import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;
import java.util.ArrayList;

class Node {
    int tID; //Task ID
    String tName; //Task name;
    int tPriority;  //Task priority number
    double tETime;  //Task execution time
    String tState;  //Task completion state (completed, failed, pending)
    Node prev; //Previous Node
    Node next; //Next Node
}

class LinkedList
{
    Node head;
    Node temp;

    public LinkedList()
    {
        head = null;
    }

    public void insert(int id, String name, int priority, double etime, String status)
    {
        if (head == null)
        {
            Node n1 = new Node();
            n1.tID = id;
            n1.tName = name;
            n1.tPriority = priority;
            n1.tETime = etime;
            n1.tState = status;
            head = n1;
        }
        else
        {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            Node n1 = new Node();
            n1.tID = id;
            n1.tName = name;
            n1.tPriority = priority;
            n1.tETime = etime;
            n1.tState = status;
            current.next = n1;
            head.prev = current;
            temp = head;
        }
    }

    public void priorityAsc()
    {
        boolean sorted = false;
        while (!sorted)
        {
            sorted = true;
            Node current = head;
            Node previous = null;
            while (current != null && current.next != null)
            {
                if (current.tPriority > current.next.tPriority)
                {
                    if (previous == null)
                    {
                        head = current.next;
                    }
                    else
                    {
                        previous.next = current.next;
                    }
                    Node temp = current.next;
                    current.next = temp.next;
                    temp.next = current;
                    previous = temp;
                    sorted = false;
                } else {
                    previous = current;
                    current = current.next;
                }
            }
        }
    }

    public void priorityDesc() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            Node current = head;
            Node previous = null;
            while (current != null && current.next != null) {
                if (current.tPriority < current.next.tPriority) {
                    if (previous == null) {
                        head = current.next;
                    } else {
                        previous.next = current.next;
                    }
                    Node temp = current.next;
                    current.next = temp.next;
                    temp.next = current;
                    previous = temp;
                    sorted = false;
                } else {
                    previous = current;
                    current = current.next;
                }
            }
        }
    }

    public void exectuionAsc() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            Node current = head;
            Node previous = null;
            while (current != null && current.next != null) {
                if (current.tETime > current.next.tETime) {
                    if (previous == null) {
                        head = current.next;
                    } else {
                        previous.next = current.next;
                    }
                    Node temp = current.next;
                    current.next = temp.next;
                    temp.next = current;
                    previous = temp;
                    sorted = false;
                } else {
                    previous = current;
                    current = current.next;
                }
            }
        }
    }

    public void executionDesc() {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            Node current = head;
            Node previous = null;
            while (current != null && current.next != null) {
                if (current.tETime < current.next.tETime) {
                    if (previous == null) {
                        head = current.next;
                    } else {
                        previous.next = current.next;
                    }
                    Node temp = current.next;
                    current.next = temp.next;
                    temp.next = current;
                    previous = temp;
                    sorted = false;
                } else {
                    previous = current;
                    current = current.next;
                }
            }
        }
    }

    public void displayCTasks(Node node)
    {
        System.out.println(node.tPriority);
        System.out.println(node.tName);
        System.out.println(node.tETime);
    }

    public void display()
    {
        Node current = head;
        while (current != null)
        {
            System.out.println(current.tName);
            current = current.next;
        }
        System.out.println("\nNo more values!!!");
    }
}



class AVLNodeByPriority //The Node class for the AVL Tree sorting with priority
{
    int tID; //Task ID
    String tName; //Task name;
    double tETime;  //Task execution time
    String tState;  //Task completion state (completed, failed, pending)
    int priority;
    Node data;
    int height;
    AVLNodeByPriority left;
    AVLNodeByPriority right;

    AVLNodeByPriority(Node data) {
        this.tID = data.tID;
        this.tName = data.tName;
        this.tETime = data.tETime;
        this.tState = data.tState;
        this.data = data;
        this.priority = data.tPriority;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}

class AVLTreeByPriority {
    AVLNodeByPriority root;

    AVLNodeByPriority rotateRight(AVLNodeByPriority y) {
        AVLNodeByPriority x = y.left;
        AVLNodeByPriority T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNodeByPriority rotateLeft(AVLNodeByPriority x)
    {
        AVLNodeByPriority y = x.right;
        AVLNodeByPriority T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int height(AVLNodeByPriority node)
    {
        if (node == null)
            return 0;
        return node.height;
    }

    int getBalance(AVLNodeByPriority node)
    {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    AVLNodeByPriority insert(AVLNodeByPriority node, Node data)
    {
        if (node == null)
            return new AVLNodeByPriority(data);

        if (data.tPriority <= node.priority)
            node.left = insert(node.left, data);
        else
            node.right = insert(node.right, data);

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && data.tPriority <= node.left.priority)
            return rotateRight(node);

        if (balance < -1 && data.tPriority > node.right.priority)
            return rotateLeft(node);

        if (balance > 1 && data.tPriority > node.left.priority)
        {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && data.tPriority <= node.right.priority)
        {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    void insert(Node data)
    {
        root = insert(root, data);
    }
    List<String> sortedValues = new ArrayList<>(); // Define a list to store sorted values
    void inorderTraversal(AVLNodeByPriority root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.println(root.data.tName + " " + root.data.tPriority);
            sortedValues.add(root.data.tName + " " + root.data.tPriority + " " + root.data.tETime + " " + root.data.tState); // Add sorted values to the list
            inorderTraversal(root.right);
        }
    }

    void inorder()
    {
        inorderTraversal(root);
    }
    void ETask() {
        System.out.println("Start");
        Scanner sc = new Scanner(System.in);
        ArrayList<AVLNodeByPriority> sortedTasks = new ArrayList<>();
        getSortedTasks(root, sortedTasks);
        for (AVLNodeByPriority node : sortedTasks) {
            System.out.println("Task: " + node.data.tName);
            double seconds = node.data.tETime * 60; // Convert minutes to seconds
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                double countdown = seconds;

                @Override
                public void run() {
                    System.out.print("\rTask in progress: " + node.data.tName);
                    System.out.print(" Time left: " + String.format("%.2f", countdown / 60) + " minutes");
                    countdown--;
                    if (countdown <= 0) {
                        System.out.println("\nTask completed: " + node.data.tName);
                        timer.cancel(); // Stop the timer
                    }
                }
            }, 0, 1000); // Schedule the task to run every second
            System.out.println("\nPress 'n' to move to the next task or 'q' to quit: ");
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("q")) {
                break;
            }
        }
        sc.close();
        display();
    }

    void getSortedTasks(AVLNodeByPriority node, ArrayList<AVLNodeByPriority> sortedTasks) {
        if (node != null) {
            getSortedTasks(node.left, sortedTasks);
            sortedTasks.add(node);
            getSortedTasks(node.right, sortedTasks);
        }
    }
    void display()
    {
        // Print the sorted values in a table
        System.out.println("+----------------------+------------+---------------+-------------------+");
        System.out.println("| Task Name            | Priority   | Execution Time| State             |");
        System.out.println("+----------------------+------------+---------------+-------------------+");
        for (String value : sortedValues) {
            String[] parts = value.split(" "); // Split the value by space
            String taskName = parts[0] + " " + parts[1]+" "+parts[2]; // Combine the first two parts as the task name
            String priority = parts[3]; // Get the priority
            String executionTime = parts[4]; // Get the execution time
            String state = parts[5]; // Get the state

            // Print the values in the table
            System.out.printf("| %-20s | %-10s | %-14s | %-15s |\n", taskName, priority, executionTime, state);
        }
        System.out.println("+----------------------+------------+---------------+-------------------+");
    }

}
class AVLNodeByTime {
    int tID; //Task ID
    String tName; //Task name;
    int tPriority;  //Task priority number
    String tState;  //Task completion state (completed, failed, pending)
    double tETime;
    Node data;
    int height;
    AVLNodeByTime left;
    AVLNodeByTime right;

    AVLNodeByTime(Node data)
    {
        this.tID = data.tID;
        this.tName = data.tName;
        this.tPriority = data.tPriority;
        this.tState = data.tState;
        this.data = data;
        this.tETime = data.tETime;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}

class AVLTreeByTime
{
    AVLNodeByTime root;

    AVLNodeByTime rotateRight(AVLNodeByTime y)
    {
        AVLNodeByTime x = y.left;
        AVLNodeByTime T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNodeByTime rotateLeft(AVLNodeByTime x)
    {
        AVLNodeByTime y = x.right;
        AVLNodeByTime T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    int height(AVLNodeByTime node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int getBalance(AVLNodeByTime node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    AVLNodeByTime insert(AVLNodeByTime node, Node data) {
        if (node == null)
            return new AVLNodeByTime(data);

        if (data.tETime <= node.tETime)
            node.left = insert(node.left, data);
        else
            node.right = insert(node.right, data);

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && data.tETime <= node.left.tETime)
            return rotateRight(node);

        if (balance < -1 && data.tETime > node.right.tETime)
            return rotateLeft(node);

        if (balance > 1 && data.tETime > node.left.tETime) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && data.tETime <= node.right.tETime) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    void insert(Node data)
    {
        root = insert(root, data);
    }

    void inorderTraversalLowToHigh(AVLNodeByTime root)
    {
        if (root != null)
        {
            inorderTraversalLowToHigh(root.left);
            System.out.println(root.data.tName + " " + root.data.tETime);
            inorderTraversalLowToHigh(root.right);
        }
    }

    void inorderLowToHigh()
    {
        inorderTraversalLowToHigh(root);
    }

    void inorderTraversalHighToLow(AVLNodeByTime root) {
        if (root != null) {
            inorderTraversalHighToLow(root.right);
            System.out.println(root.data.tName + " " + root.data.tETime);
            inorderTraversalHighToLow(root.left);
        }
    }

    void inorderHighToLow() {
        inorderTraversalHighToLow(root);
    }

    void ETask(boolean isAscending) {
        System.out.println("Start");
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        ArrayList<AVLNodeByTime> sortedTasks = new ArrayList<>();
        getSortedTasks(root, sortedTasks, isAscending);
        for (AVLNodeByTime node : sortedTasks) {
            System.out.println("Task: " + node.data.tName);
            double seconds = node.data.tETime * 60; // Convert minutes to seconds
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                double countdown = seconds;

                @Override
                public void run() {
                    System.out.print("\rTask in progress: " + node.data.tName);
                    System.out.print(" Time left: " + String.format("%.2f", countdown / 60) + " minutes");
                    countdown--;
                    if (countdown <= 0) {
                        System.out.println("\nTask completed: " + node.data.tName);
                        System.out.print("Did you complete the task (y/n): ");
                        node.data.tState = sc2.next();
                        timer.cancel(); // Stop the timer
                    }
                }
            }, 0, 1000); // Schedule the task to run every second
            System.out.println("\nPress 'n' to move to the next task or 'q' to quit: ");
            String choice = sc.next();
            if (choice.equalsIgnoreCase("q")) {
                break;
            }
        }
        sc.close();
    }

    void getSortedTasks(AVLNodeByTime node, ArrayList<AVLNodeByTime> sortedTasks, boolean isAscending) {
        if (node != null) {
            if (isAscending) {
                getSortedTasks(node.left, sortedTasks, isAscending);
                sortedTasks.add(node);
                getSortedTasks(node.right, sortedTasks, isAscending);
            } else {
                getSortedTasks(node.right, sortedTasks, isAscending);
                sortedTasks.add(node);
                getSortedTasks(node.left, sortedTasks, isAscending);
            }
        }
    }
}


class main2
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        LinkedList lst = new LinkedList();
        int tCount;
        String tname;
        int priority;
        double etime;
        String status;
        String sort;
        System.out.print("Enter number of tasks to be completed: ");
        tCount = sc.nextInt();
        for (int i = 0; i < tCount; i++)
        {
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.print("Enter task name: ");
            tname = sc.next();
            tname += sc.nextLine();
            System.out.println("The task priority ranges from 1 to 10, low value represents high priority.");
            System.out.print("Enter task priority: ");
            priority = sc.nextInt();
            System.out.print("Enter the required completion time in minutes for the task: ");
            etime = sc.nextDouble();
            status = "Pending";
            if (priority > 10 || priority < 1)
            {
                System.out.print("Enter valid priority value: ");
                priority = sc.nextInt();
            }
            lst.insert(i, tname, priority, etime, status);
        }
        AVLTreeByPriority avlTreeByPriority = new AVLTreeByPriority();
        Node current = lst.head;
        while (current != null)
        {
            avlTreeByPriority.insert(current);
            current = current.next;
        }

        AVLTreeByTime avlTreeByTime = new AVLTreeByTime();
        Node current2 = lst.head;
        while (current2 != null)
        {
            avlTreeByTime.insert(current2);
            current2 = current2.next;
        }

        System.out.println("If you want to execute the tasks according to their priority ascending order, type p");
        System.out.println("If you want to sort tasks by time (Highest time to lowest),type td");
        System.out.println("If you want to sort tasks by time (Lowest time to highest),type ta");
        sort = sc.next();
        switch (sort)
        {
            case "P":
                avlTreeByPriority.inorder();
                break;
            case "p":
                avlTreeByPriority.inorder();
                break;
            case "TD":
                avlTreeByTime.inorderHighToLow();
            case "td":
                avlTreeByTime.inorderHighToLow();
                break;
            case "TA":
                avlTreeByTime.inorderLowToHigh();
                break;
            case "ta":
                avlTreeByTime.inorderLowToHigh();
                break;
            default:
                System.out.print("Enter valid answer: ");
                break;
        }
        System.out.println("Would you wish to start completing the tasks in the sorted order (y/n): ");
        String conf = sc.next();
        switch (conf) {
            case "y":
            case "Y":
                if (sort.equalsIgnoreCase("p")) {
                    avlTreeByPriority.ETask();
                } else if (sort.equalsIgnoreCase("th")) {
                    avlTreeByTime.ETask(false); // For time high to low
                } else if (sort.equalsIgnoreCase("tl")) {
                    avlTreeByTime.ETask(true); // For time low to high
                }
                break;
            case "n":
                break;
            case "N":
                break;
            default:
                System.out.println("Enter valid answer: ");
                break;
        }
    }
}
