package tarea3;

class Contenedor<T> {
    private T dato;
    public T get() {
        T result = this.dato;
        this.dato = null;
        return result;
    }
    public synchronized void put(T valor) {
        this.dato = valor;
    }
    public synchronized boolean isDatoDisponible() {
        return (this.dato != null);
    }
}