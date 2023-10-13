package ExplicacionHilos;

public class HiloSecundario extends Thread{
    @Override
    public void run() {
        System.out.println(ThreadColor.ANSI_BLUE + "Soy el hilo " + currentThread().getName());
    }
}
