import java.util.Scanner;

class Node
{
    int tID; //Task ID
    String tName; //Task name;
    int tHours; //Total hours for the day
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
    public void insert(int id, String name, int priority, double etime, String status, int hours)
    {
        if(head == null)
        {
            Node n1 = new Node();
            n1.tID = id;
            n1.tName = name;
            n1.tPriority = priority;
            n1.tETime = etime;
            n1.tState = status;
            n1.tHours = hours;
            head = n1;
        }
        else
        {
            Node current = head;
            while (current.next != null)
            {
                current = current.next;
            }
            Node n1 = new Node();
            n1.tID = id;
            n1.tName = name;
            n1.tPriority = priority;
            n1.tETime = etime;
            n1.tState = status;
            n1.tHours = hours;
            current.next = n1;
            head.prev = current;
            temp = head;
        }
    }
    public void priorityAsc() {
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
                }
                else
                {
                    previous = current;
                    current = current.next;
                }
            }
        }
    }
    public void priorityDesc()
    {
        boolean sorted = false;
        while (!sorted)
        {
            sorted = true;
            Node current = head;
            Node previous = null;
            while (current != null && current.next != null)
            {
                if (current.tPriority < current.next.tPriority)
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
                }
                else
                {
                    previous = current;
                    current = current.next;
                }
            }
        }
    }
    public void exectuionAsc()
    {
        boolean sorted = false;
        while (!sorted)
        {
            sorted = true;
            Node current = head;
            Node previous = null;
            while (current != null && current.next != null)
            {
                if (current.tETime > current.next.tETime)
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
                }
                else
                {
                    previous = current;
                    current = current.next;
                }
            }
        }
    }
    public void executionDesc()
    {
        boolean sorted = false;
        while (!sorted)
        {
            sorted = true;
            Node current = head;
            Node previous = null;
            while (current != null && current.next != null)
            {
                if (current.tETime < current.next.tETime)
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
                }
                else
                {
                    previous = current;
                    current = current.next;
                }
            }
        }
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
public class main2
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        LinkedList lst = new LinkedList();
        int tCount,tHours;
        String tname;
        int priority;
        double etime;
        String status;
        String sOrder;
        System.out.print("Enter number of tasks to be completed: ");
        tCount = sc.nextInt();
        System.out.print("Enter number of working hours for the day: ");
        tHours = sc.nextInt();
        for(int i=0; i<tCount; i++)
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
            if(priority > 10 || priority < 1)
            {
                System.out.print("Enter valid priority value: ");
                priority = sc.nextInt();
            }
            lst.insert(i, tname, priority, etime, status,tHours);
        }
        System.out.println("If you want to execute the tasks according to their priority ascending order, type pasc");
        System.out.println("If you want to exectue the tasks according to their priority descending order, type pdesc");
        System.out.println("If you want to execute the tasks according to their execution time ascending order, type easc");
        System.out.println("If you want to execute the tasks according to their execution time descending order, type edesc");
        System.out.print("Type your answer: ");
        sOrder = sc.next();
        if(sOrder == "pasc")
        {
            lst.priorityAsc();
        }
        else if(sOrder == "pdesc")
        {
            lst.priorityDesc();
        }
        else if(sOrder == "easc")
        {
            lst.exectuionAsc();
        }
        else if(sOrder == "edesc")
        {
            lst.executionDesc();
        }
        else
        {
            System.out.print("Enter valid answer: ");
        }
       //lst.display();
    }
}
