package tarea3;

public class ProductorConsumidor {

    public static void main(String[] args) {
        /*
        Contenedor<Integer> almacen = new Contenedor<>();
        Thread hprod = new Thread(new HiloProductor(almacen, "Prod"));
        Thread hcons = new Thread(new HiloConsumidor(almacen, "Consum"));
        hprod.start();
        hcons.start();

         */
        Contenedor<String> almacen = new Contenedor<>();
        Thread hprod = new Thread(new HiloProductor(almacen, "P1"));
        Thread hprod2 = new Thread(new HiloProductor(almacen, "P2"));
        Thread hcons = new Thread(new HiloConsumidor(almacen, "C1"));
        Thread hcons2 = new Thread(new HiloConsumidor(almacen, "C2"));
        hprod.start();
        hprod2.start();
        hcons.start();
        hcons2.start();
    }

}

/*
1. Open the ProducerConsumer program, it is the classic consumer and producer type as we have
done in class. There is a single consumer and a single producer, and the container can store a
single piece of data.
 */

/**
 * a. What happens when you run the program?
 * Program print one time the execute of producer thread, because skip loop inside here with a Contenedor.dato false.
 * Then print actual value of loop counter, and put the value inside Class contenedor.
 * Calling method put() set dato not null now and next time that the loop check for a value now is true.
 * This make that the application don finish.
 * The consumer thread cant do nothing because contenedor are not synchronized.
 *
 * b. Correct the program to avoid interference between threads and make it work correctly.
 *
 * c. Why doesn't the program end? Do something to make it end.
 * Program print one time the execute of producer thread, because skip loop inside here with a Contenedor.dato false.
 * Then print actual value of loop counter, and put the value inside Class contenedor.
 * Calling method put() set dato not null now and next time that the loop check for a value now is true.
 * This make that the application don finish.
 * We add a max value in HiloProductor for method run in for loop making it close.
 *
 * d. What happens if we change the main method to this new implementation?
 * The program doesn't work correctly because the program is blocked because the consumer thread didnt notify the
 * correct thread.
 *
 * e. Change the code to make it work as before
 * We changed the HiloConsumidor run method, replacing "notify()" method for "notifyALl()", hence we unlock all threads
 * and now access is free
 *
 * f. What would happen if we created a Container of another type than Integer? Do it in code.
 *
 */