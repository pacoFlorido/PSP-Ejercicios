package ExplicacionHilos;

/**
 * Sincronización:
 * - Bloqueo intrínseco o instrinc lock o monitor
 * Todos los objetos tienen asociado un bloqueo intrínseco
 * */
class CountDown{
    private int i;

    public synchronized void doCountDown() {
        String color;
        switch (Thread.currentThread().getName()){
            case "Hilo 1":
                color = ThreadColor.ANSI_BLUE;
                break;
            case "Hilo 2":
                color = ThreadColor.ANSI_RED;
                break;
            default:
                color = ThreadColor.ANSI_RESET;
                break;
        }
        for (i = 10; i>0 ; i--){
            System.out.println(color +
                    Thread.currentThread().getName() +
                    " i=" + i);
        }
    }
}

class ThreadCount extends Thread {
    private CountDown countDown;
    public ThreadCount (CountDown countDown){
        this.countDown = countDown;
    }
}

public class MultiHilo {
    public static void main(String[] args) {

    }
}
