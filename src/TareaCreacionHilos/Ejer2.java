package TareaCreacionHilos;

class HiloPrincipal extends Thread{
    @Override
    public void run() {
        System.out.println("Hilo principal");
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("EL hilo principal terminó");
    }
}

public class Ejer2 {
    public static void main(String[] args) {
        Thread hiloPrincipal = new HiloPrincipal();
        hiloPrincipal.start();

        //Interfaz Runnable
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    hiloPrincipal.join();
                    System.out.println("El hilo principal ha acabado y se ejecuta el hilo creado con interfaz runnable");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //Clase anónima
        new Thread() {
            @Override
            public void run() {
                try {
                    hiloPrincipal.join();
                    System.out.println("El hilo principal ha acabado y se ejecuta el hilo creado con clase anónima");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
