/*
    Playing with ScheduledExecutorService
 */

package schedule;

import java.util.concurrent.*;

public class TestClass {
    static int beeps = 0;
    public static void main(String[] args) throws InterruptedException {
        Executors.newSingleThreadExecutor()

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable beeper = ()->System.out.println("beep #" + ++beeps);

        ScheduledFuture<?> scheduledFuture =
                scheduler.scheduleAtFixedRate(beeper, 3000, 500, TimeUnit.MILLISECONDS);
        System.out.println("scheduled");

        scheduler.schedule(
                ()->{
                    scheduledFuture.cancel(false);
                },6, TimeUnit.SECONDS);
        scheduler.awaitTermination(10, TimeUnit.SECONDS);
        scheduler.shutdownNow();

    }
}

