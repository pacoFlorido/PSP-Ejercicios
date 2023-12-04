package ExecutorTema5;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.concurrent.*;

public class Schedule {
    public static void main(String[] args) {
        // Formatear un DateTime
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(
                FormatStyle.MEDIUM,
                FormatStyle.LONG
        );
        // TimeUnit.SECONDS.sleep(2);
        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(ZonedDateTime.now().format(dtf) + "   11111");
        };
        Runnable task2 = () -> {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(ZonedDateTime.now().format(dtf) + "   22222");
        };
        // Quiero ejecutar esa tarea concurrentemente 4 veces al mismo tiempo
        ExecutorService executorS = Executors.newSingleThreadExecutor();
        ExecutorService executor = Executors.newFixedThreadPool(4);
        ExecutorService executorService = Executors.newCachedThreadPool();


        /*// Lista de tareas
        List<Callable<ZonedDateTime>> taskList = List.of(task,task,task,task);

        try {
            System.out.println("Ahora sin Schedule: " + ZonedDateTime.now().format(dtf));

            List<Future<ZonedDateTime>> results = executor.invokeAll(taskList);
            for (Future<ZonedDateTime> r: results){
                System.out.println(r.get().format(dtf));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

         */



        System.out.println("Ahora con Schedule: " + ZonedDateTime.now().format(dtf));


        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);
        ScheduledFuture<?> result = scheduled.scheduleAtFixedRate(task,5L,2L,TimeUnit.SECONDS);
        ScheduledFuture<?> rr = scheduled.scheduleWithFixedDelay(task2,5L,2L,TimeUnit.SECONDS);
        try {
            System.out.println(result.get());
            System.out.println(rr.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        scheduled.shutdown();



        /*
        try {
            for (int i = 0; i<4; i++){
                ScheduledFuture<ZonedDateTime> resultsSchedule = scheduled.schedule(
                        () -> ZonedDateTime.now(),
                        2,
                        TimeUnit.SECONDS
                );
                System.out.println(resultsSchedule.get().format(dtf));
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            scheduled.shutdown();
        }
         */
    }
}
