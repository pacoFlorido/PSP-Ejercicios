package Executor;

import ExplicacionHilos.ThreadColor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

class ColorThreadFactory implements ThreadFactory{

    private String nameThread;

    public ColorThreadFactory(String nameThread) {
        this.nameThread = nameThread;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName(nameThread);
        return t;
    }
}

public class MainExecutor {
    public static void countDown(){
        for (int i = 20; i>=0; i--){
            System.out.println(Thread.currentThread().getName() + " Hilo " + i);
        }
    }

    public static void main(String[] args) {
        ExecutorService blue = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_BLUE));
        blue.execute(MainExecutor::countDown);
        blue.shutdown(); // Cunado el hilo acaba sus tareas se apagará, no está esperando nada.

        try {
            if (blue.awaitTermination(1000, TimeUnit.MILLISECONDS)){
                ExecutorService green = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_GREEN));
                green.execute(MainExecutor::countDown);
                green.shutdown();

                ExecutorService red = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_RED));
                red.execute(MainExecutor::countDown);
                red.shutdown();
            }
        } catch (InterruptedException e) {

        }
    }
}
