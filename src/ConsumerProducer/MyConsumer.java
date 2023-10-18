package ConsumerProducer;

import java.util.List;

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

    public MyConsumer(List<String> buffer, String color) {
        this.buffer = buffer;
        this.color = color;
    }

    @Override
    public void run() {
        while (true){
            if (buffer.isEmpty()){
                continue;
            } else if(buffer.get(0).equals("-1")){
                System.out.println(color + "Final de Fichero");
                break;
            } else {
                for (String n: buffer){
                    System.out.println(color + "Mostrando..." + n);
                    buffer.remove(n);
                }
            }
        }
    }
}
