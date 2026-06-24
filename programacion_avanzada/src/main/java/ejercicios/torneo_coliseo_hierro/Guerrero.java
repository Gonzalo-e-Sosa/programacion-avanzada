package ejercicios.torneo_coliseo_hierro;

public class Guerrero implements Comparable<Guerrero> {

    private int fuerza;
    private boolean estoyVivo;
    private String nombre;

    public Guerrero(int fuerza, String nombre) {
        this.fuerza = fuerza;
        this.estoyVivo = true;
        this.nombre = nombre;
    }

    public int getFuerza() {
        return this.fuerza;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void morir() {
        this.fuerza = 0;
        this.estoyVivo = false;
    }

    public boolean estaVivo() {
        return estoyVivo;
    }

    public void pelear(Guerrero o) {
        if (this.fuerza > o.getFuerza()) {
            this.fuerza -= o.getFuerza();
            o.morir();
        } else if (this.fuerza < o.getFuerza()) {
            o.fuerza -= this.fuerza;
            this.morir();
        } else {
            this.morir();
            o.morir();
        }
    }

    @Override
    public int compareTo(Guerrero o) {
        return o.getFuerza() - this.fuerza;
    }

    @Override
    public String toString() {
        return nombre + " (Fuerza: " + fuerza + ", Vivo: " + estoyVivo + ")";
    }

}