package ConsumerProducer;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase MyCOnsumer
 * mismas variables que la clase MyProducer
 *
 * Contructor parametrizado
 *
 * Método run:
 * Crearemos un bucle infinito.
 * Dentro, si el buffer está vacío, continue.
 * Si no esta vacío, compruebo que lo primero
 * que hay en el buffer sea -1, si lo es, imprimo EOF y salgo del bucle.
 * Si no es -1, los muestro y los elimino de la lista.
 * Todo lo que imprima en el color del hilo.
 */
public class MyConsumer implements Runnable{
    private List<String> buffer;
    private String color;
    private ReentrantLock bufferLock;

    public MyConsumer(List<String> buffer, String color, ReentrantLock bufferLock) {
        this.buffer = buffer;
        this.color = color;
        this.bufferLock = bufferLock;
    }

    @Override
    public void run() {
        while (true){
            bufferLock.lock();
            try {
                if (buffer.isEmpty()) {
                    continue;
                }
                if (buffer.get(0).equals("-1")) {
                    System.out.println(color + "Final de Fichero");
                    break;
                } else {
                    System.out.println(color + "Mostrando y eliminando..." + buffer.remove(0));
                }
            } finally {
                bufferLock.unlock();
            }
        }
    }
}
