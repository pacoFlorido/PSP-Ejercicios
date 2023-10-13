package TareaCreacionHilos;

import java.util.Scanner;

class HiloClaseThread extends Thread{
    static Scanner sc = new Scanner(System.in);
    @Override
    public void run() {
        int numero;
        System.out.println("Introduce el número máximo.");
        numero = sc.nextInt();
        int i = 1;
        int suma = 0;
        while (i<numero){
            suma+=i;
            i++;
        }
        System.out.println(suma);
    }
}
public class Ejer1 {
    public static void main(String[] args) {
        Thread hilo1 = new HiloClaseThread();
        hilo1.start();
        new Thread(() -> {
            try {
                hilo1.join();
                System.out.println("Suma realizada con éxito.");
            } catch (InterruptedException e) {
                System.out.println("Hilo2 interrumpido");
            }
        }).start();
    }
}
