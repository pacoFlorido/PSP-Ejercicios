package ConsumerProducer;

import ExplicacionHilos.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Creo un arraylist vacío llamado buffer.
 * Elegir color diferente para cada hilo.
 * Me creo dos hilo producer y un hilo consumer.
 * MyProducer p = new My...
 *
 * Creo 3 hilos y los inicio
 *
 * Estudio lo que pasa e intentamos corregirlo.
 */

/**
 * Inconvenientes de Synchronized
 * 1º. Los demas hilos se quedan bloqueados esperando al synchronized y no se pueden INTERRUMPIR
 * 2º. No hay información de si el objeto esta disponible, quien tiene el bloqueo.
 * 3º. No es FCFS, no hay orden para obtener el bloqueo, es decir, los hilos que están esperando al bloqueo se
 *     ejecutarán dependiendo de factores externos.
 */
public class Main {

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();
        ReentrantLock bufferLock = new ReentrantLock();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        MyProducer p = new MyProducer(buffer, ThreadColor.ANSI_RED, bufferLock);
        MyConsumer c1 = new MyConsumer(buffer,ThreadColor.ANSI_BLUE, bufferLock);
        MyConsumer c2 = new MyConsumer(buffer,ThreadColor.ANSI_GREEN, bufferLock);

        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();

        executorService.execute(p);
        executorService.execute(c1);
        executorService.execute(c2);

        //Espera a que finalicen los hilos
        executorService.shutdown();
    }
}
