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

        MyProducer p1 = new MyProducer(buffer, ThreadColor.ANSI_RED);
        MyProducer p2 = new MyProducer(buffer,ThreadColor.ANSI_BLUE);
        MyConsumer c = new MyConsumer(buffer,ThreadColor.ANSI_GREEN);

        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(c);

        t1.start();
        t2.start();
        t3.start();

    }
}
