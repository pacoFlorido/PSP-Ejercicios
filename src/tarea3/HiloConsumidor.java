package tarea3;

class HiloConsumidor implements Runnable {
    private Contenedor<String> contenedor;
    private final String nombre;
    public HiloConsumidor(Contenedor<String> contenedor, String nombre) {
        this.contenedor = contenedor;
        this.nombre = nombre;
    }
    @Override
    public void run() {
        while(true) {
            synchronized (contenedor) {
                while (!this.contenedor.isDatoDisponible()) {
                    try {
                        contenedor.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                String dato = this.contenedor.get();

                System.out.println("Hilo " + nombre + " consume el valor " + dato + ".\n");
                contenedor.notifyAll();
            }
        }
    }
}
