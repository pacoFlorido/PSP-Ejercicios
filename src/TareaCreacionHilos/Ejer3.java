package TareaCreacionHilos;

public class Ejer3 {
    public static void main(String[] args) {
        Thread hiloEnEjecucion = new Thread(){
            @Override
            public void run() {
                System.out.println("Hilo principal");
                try {
                    Thread.sleep(5 * 1000);
                    System.out.println("El hilo principal despertó");
                } catch (InterruptedException e) {
                    System.out.println("El hilo principal se ha interrumpido");
                }
            }
        };
        hiloEnEjecucion.start();

        Thread hiloEspera = new Thread(){
            @Override
            public void run() {
                try {
                    hiloEnEjecucion.join();
                    System.out.println("HIlo en espera ejecutado.");
                } catch (InterruptedException e) {
                    System.out.println("Hilo en espera interrumpido");
                }
            }
        };
        hiloEspera.start();
        hiloEspera.interrupt();
    }
}
