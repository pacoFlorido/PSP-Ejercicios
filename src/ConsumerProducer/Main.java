package ConsumerProducer;

import ExplicacionHilos.ThreadColor;

import java.util.ArrayList;
import java.util.List;

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
public class Main {

    public static void main(String[] args) {
        List<String> buffer = new ArrayList<>();

        MyProducer p = new MyProducer(buffer, ThreadColor.ANSI_RED);
        MyConsumer c1 = new MyConsumer(buffer,ThreadColor.ANSI_BLUE);
        MyConsumer c2 = new MyConsumer(buffer,ThreadColor.ANSI_GREEN);

        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }
}
