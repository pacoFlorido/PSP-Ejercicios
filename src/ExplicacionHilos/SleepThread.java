package ExplicacionHilos;

import static ExplicacionHilos.ThreadColor.*;

class UnHilo extends Thread{
    @Override
    public void run() {
        System.out.println(ANSI_BLUE +
                currentThread().getName());
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            //2 Formas para ver que un hilo se ha interrumpido
            //1. Salta la excepción.
            //2. Método isInterrupted()

            //Si un hilo quiere interrumpir a otro necesita la referencia del hilo que quiere interrumpir.
            System.out.println(ANSI_BLUE +
                    "El hilo ha sido interrumpido");
            //Terminar el hilo explicitamente
            return;
        }
        System.out.println(ANSI_BLUE +
                "Ha pasado el tiempo y estoy despierto");
    }
}

public class SleepThread {
    public static void main(String[] args) {
        Thread hilo = new UnHilo();
        hilo.setName("Hilo secundario 1.");
        hilo.start();

        new Thread(() -> {
            System.out.println(ANSI_RED +
                    "Hilo Runnable con lambda");
            try {
                //Función join, espera a que el primer hilo acabe para ejecutarse.
                hilo.join();
                System.out.println("El hilo a acabado o time out y me ejecuto");
            } catch (InterruptedException e) {
                System.out.println("interrumpido");
            }
        }).start();

        System.out.println(ANSI_BLACK +
                "Hilo principal");
    }
}
