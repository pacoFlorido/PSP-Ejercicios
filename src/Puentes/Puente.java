package Puentes;

import java.util.Random;

/**
 * Simular un sistema que controla el paso de personas por un puente, siempre
 * en la misma dirección. Se tiene que cumplir una serie de restricciones.
 * No pueden pasar más de tres personas a la vez, y no puede haber más de 200kg.
 *
 * Para la simulación vamos a tener una clase persona que serán hilos.
 *
 * Definimos peso máximo y el número de personas máximas.
 * Tendremos también variables para el peso y num. Personas que hay en el puente actualmente.
 *
 * Tenemos un método autorizaciónPaso, le pasaremos una persona, y nos devolverá
 * si está autorizada a pasar o no.
 *
 * Otro método salirPuente, le pasamos una persona.
 */

public class Puente {
    private static final int PESO_MAX = 200;
    private static final int PERSONAS_MAX = 3;

    private int pesoActual;
    private int personasActuales;

    public synchronized boolean autorizacionPaso(Persona persona){
        if ((personasActuales) < PERSONAS_MAX && (persona.getPeso() + pesoActual) <= PESO_MAX){
            personasActuales++;
            pesoActual+=persona.getPeso();
            return true;
        }
        return false;
    }

    public void salirPuente(Persona persona){
        personasActuales--;
        pesoActual -= persona.getPeso();
    }

    public int getPesoActual() {
        return pesoActual;
    }

    public int getPersonasActuales() {
        return personasActuales;
    }

    @Override
    public String toString() {
        return "Puente{" +
                "pesoActual=" + pesoActual +
                ", personasActuales=" + personasActuales +
                '}';
    }
}

/**
 * Persona, Hilo. El tiempo de llegada de las personas es aleatorio, entre 1 y 30 segundos.
 * Y para atravesar el puente, entre 10 y 50 segundos.
 * Las personas tendrán un peso aleatorio entre 40 y 120 kg.
 *
 * - idPersona
 * - peso
 * - tMinPaso, tMaxPaso
 * - puente
 *
 * Constructor completo
 *
 * - Método run:
 * Imprimo un mensaje indicando quien soy, cuanto peso y lo que hay en el puente actualmente.
 * Bucle(mientras no está autorizado)
 *      boolean autorizado = método.
 *      Si no estoy autorizado --- informo de que no estoy autorizado.
 *      Si estoy autorizado sale del bucle
 *      --- informo de que voy a cruzar, calculo el tiempo que voy a tardar en cruzar el puente
 *      Llamar a salir del puente e informo de que he salido
 *
 * Clase PasoPorPuente - Clase Main
 * Bucle infinito
 *      Calculo el tiempo para que pase la siguiente persona.
 *      Creo el hilo de la Persona y lanzo la persona al puente.
 */
class Persona implements Runnable{
    private int peso, id, tMinPaso, tMaxPaso;
    private Puente puente;

    public Persona(int peso, int idPersona, int tMinPaso, int tMaxPaso, Puente puente) {
        this.peso = peso;
        this.id = idPersona;
        this.tMinPaso = tMinPaso;
        this.tMaxPaso = tMaxPaso;
        this.puente = puente;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public void run() {
        System.out.println("Soy la persona " + id + " y peso " + peso + " kg.");
        System.out.println("Estado del puente: " + this.puente);
        boolean autorizado = false;
        while (!autorizado) {
            synchronized (this.puente){
                autorizado = puente.autorizacionPaso(this);
                if (!autorizado) {
                    System.out.println("Debo esperar");
                    try {
                        this.puente.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
        System.out.println(id + " con peso " + peso + " puede cruzar. En el puente ahora hay ");

        Random random = new Random();
        int tiempoPaso = random.nextInt(tMinPaso, tMaxPaso);
        try {
            Thread.sleep(tiempoPaso);
        } catch (InterruptedException e) {
        }
        synchronized (this.puente) {
            puente.salirPuente(this);
            System.out.println("Persona " + id + " sale del puente, estado del puente " + this.puente);
            this.puente.notifyAll();
        }
    }
}
class PasoPorPuente {
    final static int MIN_TIEMPO_PASO = 1;
    final static int MAX_TIEMPO_PASO = 30;
    public static void main(String[] args) {
        final Puente puente = new Puente();
        Random random = new Random();
        int idPersona = 1;
        int tminpaso = 1000;
        int tmaxpaso = 50000;

        while (true){
            int tLlegadaPersonas = random.nextInt(1000, 3001);
            System.out.println("Siguiente persona llega en " + tLlegadaPersonas + "segundos.");
            try {
                Thread.sleep(tLlegadaPersonas);
            } catch (InterruptedException e) {
            }
            int peso = random.nextInt(40,121);

            new Thread(new Persona(peso, idPersona, tminpaso,tmaxpaso,puente)).start();

            idPersona++;
        }
    }
}

/**
 * Tema 3 -> Creación hilos, interrupt, join
 * Tema 4 -> Sincronización, reentrant lock (Teoria examen), wait, notify-notifyAll
 * Tema 5 -> ExecutorService
 */