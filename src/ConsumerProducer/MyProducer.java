package ConsumerProducer;

import java.util.List;
import java.util.Random;

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

    public MyProducer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] nums = {"1", "2", "3", "4", "5"};
        synchronized (buffer){
            for (String n : nums){
                System.out.println(color + "Ading..." + n);
                buffer.add(n);

                try {
                    Thread.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    System.out.println(color + "Procucer interrumpido" );
                }
            }
            System.out.println(color + "Final de fichero");

            buffer.add("-1");
            System.out.println(buffer);
        }

    }
}
