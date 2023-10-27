package ConsumerProducer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Creamos una clase MyProducer, esta clase será un hilo, implementando Runnable.
 * Variables de instancia
 * -una lista de string llamada buffer
 * -string color
 * constructor parametrizado
 * -run:
 * Voy a cear un objeto Random.
 * Creo un array[] de Strings con "1","2","3","4","5"
 * luego itero sobre el array de strings, los muestro con el color del hilo
 * y los añado al buffer
 * Después duermo el hilo hasta 1 seg.
 * Cuando acabo el bucle, imprimo final de fichero, y añado al buffer "EOF" o "-1"
 */

public class MyProducer implements Runnable{
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyProducer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};

        for (String n : nums){
            System.out.println(color + "Ading..." + n);

            bufferLock.lock();
            try {
                buffer.add(n);
            } finally {
                bufferLock.unlock();
            }

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                System.out.println(color + "Procucer interrumpido" );
            }
        }
        System.out.println(color + "Final de fichero");

        bufferLock.lock();
        try {
            buffer.add("-1");
        } finally {
            bufferLock.unlock();
        }

        System.out.println(buffer);
    }
}
