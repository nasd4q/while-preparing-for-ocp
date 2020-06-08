package cyclicbarrier;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class TestClass {
    private static class LionPenManager {
        private void removeAnimals() {
            System.out.println("Removing animals");
        }

        private void cleanPen() {
            System.out.println("Cleaning the pen");
        }

        private void addAnimals() {
            System.out.println("Adding animals");
        }


        private void performTask() {
            removeAnimals();
            cleanPen();
            addAnimals();
        }

        private void performTask(CyclicBarrier cb1, CyclicBarrier cb2) {
            try {
                removeAnimals();
                cb1.await();
                cleanPen();
                cb2.await();
                addAnimals();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public static void main(String[] args) {
            ExecutorService service = null;
            try {
                service = Executors.newFixedThreadPool(4);
                LionPenManager manager = new LionPenManager();
                for (int i = 0; i < 4; i++)
                    service.submit(() -> manager.performTask());
            } finally {
                if (service != null) service.shutdown();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        //System.out.println(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));//Exception
        // in thread "main" java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: Year


        System.out.println(LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME));

        final ExecutorService service = Executors.newFixedThreadPool(4);
        final LionPenManager lionPenManager = new LionPenManager();
        final CyclicBarrier cb1 = new CyclicBarrier(4, () -> System.out.println("ALL DONE - "
            + LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME)));

        try {
            int numberOfWorkers = 4;
            for (int i = 0; i < numberOfWorkers; i++) {
                service.submit(() -> lionPenManager.performTask(cb1, cb1));
            }
        } finally {
            if (service!=null)
                service.shutdown();
            System.out.println("Shutted down");
        }
    }

/*
    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(4);
            LionPenManager manager = new LionPenManager();
            CyclicBarrier c1 = new CyclicBarrier(4);
            CyclicBarrier c2 = new CyclicBarrier(4,
                    () -> System.out.println("*** Pen Cleaned!"));

            for (int i = 0; i < 4; i++)
                service.submit(() -> manager.performTask(c1, c1));
        } finally {
            if (service != null) service.shutdown();
        }
    }

 */
}


