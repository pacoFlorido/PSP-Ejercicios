package ExplicacionHilos;

public class MainThread {
    public static void main(String[] args) {
        //Tres formas de crear hilos.

        //Primera forma: crear una clase que herede de Thread, sobreescribo el método run.
        Thread hilo = new HiloSecundario();
        hilo.setName("*HILO SECUNDARIO*");
        hilo.start();

        //Segunda forma: clase anónima, sobreescribo el método run.
        new Thread() {
            @Override
            public void run() {
                System.out.println(ThreadColor.ANSI_GREEN + "Soy el hilo de clase anónima.");
            }
        }.start();

        //Tercera forma: utilizando la interfaz Runnable.
        Thread runHilo = new Thread(new HiloRunnable());
        runHilo.start();

        //Con lambda.
        Thread lambaR = new Thread(() -> System.out.println(ThreadColor.ANSI_BLACK + "Soy el hilo Runnable con lambda"));
        lambaR.start();

        System.out.println(ThreadColor.ANSI_RESET + "Soy el hilo principal");
    }
}