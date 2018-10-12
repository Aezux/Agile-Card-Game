/*
 * Code not created by myself. 
 * Located at https://stackoverflow.com/questions/36678896/how-to-display-seconds-of-the-timer-in-the-output-screen
 * Credit belongs to them
 */

package base;


import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

public class timer {
  Toolkit toolkit;
  Timer timer;
int t=10000,total;

public timer(int seconds) {
    timer = new Timer();
    timer.scheduleAtFixedRate(new RemindTask(seconds), 0, 1000);
}

class RemindTask extends TimerTask {
    private volatile int remainingTimeInSeconds;
    public RemindTask(int remainingTimeInSeconds) {
        this.remainingTimeInSeconds = remainingTimeInSeconds;
    }

    public void run() {
        if (remainingTimeInSeconds != 0) {
            System.out.println(remainingTimeInSeconds + " ...");
            remainingTimeInSeconds -= 1;
        }
    }
}

public static void main(String args[]) throws InterruptedException {
    timer t = new timer(5);
    System.out.println("Timer started");
    Thread.sleep(5000);
    t.end();
}

private void end() {
    this.timer.cancel();
}
}