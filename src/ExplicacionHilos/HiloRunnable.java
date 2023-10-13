package ExplicacionHilos;

public class HiloRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println(ThreadColor.ANSI_RED + "Soy el hilo Runnable");
    }
}
