import java.util.Scanner;

class Node
{
    int tID; //Task ID
    String tName; //Task name;
    int tHours;
    int tPriority;
    double tETime;
    String tState;
    Node prev;
    Node next;
}
class LinkedList
{
    Node head;
    public LinkedList()
    {
        head = null;
    }
    public void insert(int id, String name, int priority, double etime,String status,int hours)
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
            while (current != null)
            {
                current = current.next;
            }
            Node n1 = new Node();
            n1.tID = id;
            n1.tName = name;
            n1.tPriority = priority;
            n1.tETime = etime;
            n1.tState = status;
            current.next = n1;
            head = n1;
            head.prev = current;
        }
    }
    public int taskID()
    {
        Node current = head;
        while (current != null)
        {
            int taskID = current.tID;
            current = current.next;
            return taskID;
        }
        return -1;
    }
    public String taskName()
    {
        Node current = head;
        while(current != null)
        {
            String taskName = current.tName;
            current = current.next;
            return  taskName;
        }
        return "No name";
    }
    public int totalHours()
    {
        Node current = head;
        while(current != null)
        {
            int tHours = current.tHours;
            current = current.next;
            return tHours;
        }
        return -1;
    }
    public void  display()
    {

    }

}
class sort
{
    public void sortPAsc()
    {

    }
    public void sortPDesc()
    {

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

        }
        else if(sOrder == "pdesc")
        {

        }
        else if(sOrder == "easc")
        {

        }
        else if(sOrder == "edesc")
        {

        }
        else
        {
            System.out.print("Enter valid answer: ");
        }
        sOrder = sc.next();
    }
}
