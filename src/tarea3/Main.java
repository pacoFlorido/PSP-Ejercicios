package tarea3;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ExplicacionHilos.ThreadColor.*;


public class Main {
    public static final String[] typesOfShoes = {"deportivas", "sandalias", "zapatos", "botas"};
    public static void main(String[] args) {

        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();
        Random r = new Random();

        Callable<Order> producer = () -> {
            List<Order> orders = new ArrayList<>();
            for (int i = 0; i < 200; i++) {
                Order o = new Order(r.nextInt(),
                        typesOfShoes[r.nextInt(0,4)],
                        r.nextInt());
                //System.out.println(o);
                orders = shoeWarehouse.receiveOrder(o);
            }
            return orders.get(orders.size()-1);
        };
        Callable<Order> consumer = () -> {
            Order o = null;
            for (int i = 0; i < 200; i++) {
                o = shoeWarehouse.fulfillOrder(ANSI_BLUE);
            }
            return o;
        };
        List<Callable<Order>> hilos = List.of(producer,consumer);

        try (ExecutorService executor = Executors.newCachedThreadPool()) {
            executor.invokeAll(hilos);
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido");
        }
    }
}

/*
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {

                Order o = new Order(r.nextInt(), typesOfShoes[r.nextInt(0,4)], r.nextInt());
                //System.out.println(o);
                shoeWarehouse.receiveOrder(o);

            }
        });
        producer.start();

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {

                shoeWarehouse.fulfillOrder( ANSI_RED);

            }
        });
        consumer.start();


        Thread consumer2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {

                shoeWarehouse.fulfillOrder(ANSI_GREEN);

            }
        });
        consumer2.start();
        */

/*
2. In this challenge, you’ll be creating your own Producer/Consumer example, for a Shoe Warehouse
Fulfillment Center.
The producer code should generate orders, and send them to the Shoe Warehouse to be
processed.
The consumer code should fulfill, or process the orders in a FIFO or first in, first out order.
You will be creating three classes for an Order, a Shoe Warehouse, and a Main executable class.
- Order class, should include an order id, a shoe type, and the quantity ordered. A record might
be a good fit for this type.
- Shoe Warehouse class: it should maintain a private list of orders. It should have two methods:
o receiveOrder: receives an order and adds it to the list. Gets called by a Producer
thread. It should loop checking the size of the list, but it should call wait if the list has
reached some maximum capacity.
o fulfillOrder: this method processes an order, meaning, removes the order from the list.
Gets called by a Consumer thread. It should wait if the list is empty and there is no
order to process.
- Main class with a main method, to execute. This class should have an array of strings public
static and final with type of shoes created. The main method should create and start a single
Producer thread. You can create the producer thread in the main method and overload run
method with a lambda expression. The producer should generate 10 sales orders, and call
receiveOrder on the Shoe Warehouse, for each. To create an order, use Random to generate
a random id, get randomly a type from the array and generate randomly a quantity.
In addition, you will create and start two Consumer threads in main method too. Each thread
needs to process 5 fulfillment orders, calling fulfillOrder on the Shoe Warehouse for each item.
Print the necessary messages to show that is working, test your Producer Consumer application,
and confirm your application fulfills all the 10 orders it receives
 */
/**
 *  a. Does the application work correctly? Why or why not?
 *  The application works correctly.
 *  Because when all the conditions dictated in the exercise are fulfilled,
 *  we must make the thread pause depending on whether the list is empty or full.
 *  Notifying this way to the rest of the threads so that they can work with it.
 *
 *
 *  b. Make comments about the output of the program.
 *
 *  Depending on the maximum number that we set for our list, the program will work in one way or another.
 *  For example if the MAX_LIST is 10, in my case the threads are executed in order,
 *  because it gives time to generate the different Orders and rarely the threads are mixed.
 *  However, if MAX_LIST is 2 or 3 I can see how the threads get mixed up.
 *  On the other hand, if we create more consuming threads without changing the conditions
 *  of how many they should consume, the program will stay running because a consuming thread
 *  will access the fulfillOrder() method and being the list empty it will be put on hold, by the wait().
 *  The same thing will happen if we create more Orders than will be consumed,
 *  there will come a point where the consumers have finished their work and the method reciveOrder()
 *  will be put on hold because the list is full.
 *
 */
