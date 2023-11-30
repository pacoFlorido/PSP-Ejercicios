package ExecutorTema5;

import ExplicacionHilos.ThreadColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Counter implements Runnable{
    @Override
    public void run() {
        for (int i = 10; i>=0; i--){
            System.out.println(Thread.currentThread().getName() + " Hilo " + i);
        }
    }
}

/**
 * ExecutorService executor = Executors.
 * - newSingleThreadExecutor()
 * - newFixedThreadPool(int numHilos)
 * - newCachedThreadPoll() --> Crea los hilos necesarios para ejecutar las tareas que les pasemos.
 *
 * --Métodos para ejecutar tareas
 * - execute(Runnable r) --> no devuelve nada
 * - T submit(Callable<T> c) --> devuelve un Future de tipo <T>
 * - invokeAll(List<Callable<T> taskList> --> devuelve una lista de Future<T> = List<Future<T>>
 * - invokeAny(List<Callable<T>> taskList) --> devuelve T
 *
 * ScheduledExecutorsService scheduled = Executors.
 * -newSingleThreadScheduledExecutor
 * -newScheduledThreadPool
 *
 *
 * --Métodos para ejecutar tareas
 * -scheduled: ejecuta una tarea una sola vez, delay
 * -scheduleWithFixedDelay: ejecuta la tarea pariodicamente, (initialDelay, delay)
 * -scheduleAtFixedRate: ejecuta la tarea periodicamente, (initialDelay, delay)
 */
class MainExecutor {
    public static void main(String[] args) {
        // Creación de un único hilo
        // ExecutorService executor = Executors.newSingleThreadExecutor();

        // Crea un número de hilos específico
        //ExecutorService executor = Executors.newFixedThreadPool(3);

        // Crea los hilos necesarios para realizar las tareas que les pases
        ExecutorService executor = Executors.newCachedThreadPool();
        // Usamos try catch para cerrar el executor con un finally en cualquier caso.
        try {
            // Tarea que realizará el hilo
            // Runnable task = new Counter();
            // Callable --> igual que Runnable pero devuelve algo.
            Callable<Integer> c = () -> {
                TimeUnit.SECONDS.sleep(5);
                return MainExecutor.sum(1, 199, 2, ThreadColor.ANSI_BLUE);
            };
            // Lista de tareas con Callable
            List<Callable<Integer>> taskList = new ArrayList<>();
            taskList.add(() -> MainExecutor.sum(1,100,1,ThreadColor.ANSI_GREEN));
            taskList.add(() -> MainExecutor.sum(5,100,1,ThreadColor.ANSI_GREEN));

            // submit() --> Tiene diferentes constructores para poder obtener resultados de la operación que realiza el hilo
            // devuelve un objeto Future del tipo que hallamos cread el Callable
            // Future<Integer> resultado = executor.submit(c);


            try {
                // invokeAll() --> devuelve una lista de Future<T> donde T será el valor que hayamos
                                // asignado a los Callable
                // invokeAny --> devuelve el resultado de la primera tarea en ejecutarse,
                              // en este caso un tipo Integer
                List<Future<Integer>> resultList = executor.invokeAll(taskList);
                for (Future<Integer> future : resultList){
                    System.out.println(future.get());
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            executor.shutdown();
        }

    }

    public static int sum (int start, int end, int increment, String color){
        int sum = 0;
        for (int i = start; i < end; i+=increment){
            sum+=i;
        }
        System.out.println(color + Thread.currentThread().getName() + " " + sum);
        return sum;
    }
}