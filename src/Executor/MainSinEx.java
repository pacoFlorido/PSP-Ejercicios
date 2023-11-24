package Executor;

import ExplicacionHilos.ThreadColor;

public class MainSinEx {

    public static void countDown(){
        for (int i = 20; i>=0; i--){
            System.out.println(Thread.currentThread().getName() + " Hilo " + i);
        }
    }

    public static void main(String[] args) {
        Thread blue = new Thread(MainSinEx::countDown, ThreadColor.ANSI_BLUE);
        Thread green = new Thread(MainSinEx::countDown, ThreadColor.ANSI_GREEN);
        Thread red = new Thread(MainSinEx::countDown, ThreadColor.ANSI_RED);

        blue.start();
        green.start();
        red.start();

        try {
            blue.join();
            green.join();
            red.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Fin");
    }
}
