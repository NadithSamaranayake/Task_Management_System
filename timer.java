import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class timer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter time: ");
        int seconds = sc.nextInt();
        //int seconds = 10; // Set the countdown time in seconds

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int countdown = seconds;

            @Override
            public void run() {
                System.out.print(countdown+"\r");

                if (countdown <= 0) {
                    System.out.println("Countdown complete!");
                    timer.cancel(); // Stop the timer
                }

                countdown--;
            }
        }, 0, 60000); // Schedule the task to run every 1000 milliseconds (1 second)
    }
}