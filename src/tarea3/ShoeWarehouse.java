package tarea3;

import ExplicacionHilos.ThreadColor;

import java.util.ArrayList;
import java.util.List;

public class ShoeWarehouse {
    private List<Order> orders = new ArrayList<>();
    private final int MAX_LIST = 200;


    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public int getMAX_LIST() {
        return MAX_LIST;
    }


    public synchronized List<Order> receiveOrder(Order order) {

            while (orders.size() == MAX_LIST) {
                try {

                    this.wait();

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (orders.size() < MAX_LIST) {
                orders.add(order);
                System.out.println(ThreadColor.ANSI_RESET + "Order " + order.id() + " has been added.");

                this.notify();
            }
            return orders;
    }

    public synchronized Order fulfillOrder(String color) {

        while (orders.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Order o = orders.remove(0);
        System.out.println(color + "Order " + o.id() + " has been deleted.");
        this.notify();
        return o;
    }
}
