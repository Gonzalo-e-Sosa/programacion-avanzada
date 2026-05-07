package programacion_avanzada.torneo_coliseo_hierro;

import java.util.ArrayList;
import java.util.List;

public class Equipo implements Comparable<Equipo> {
    private List<Guerrero> guerreros = new ArrayList<Guerrero>();
    private String nombre;

    public Equipo(Guerrero... guerreros) {
        for (Guerrero g : guerreros) {
            this.guerreros.add(g);
        }

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre + " - " + guerreros.toString();
    }

    public void agregarGuerrero(Guerrero g) {
        guerreros.add(g);
    }

    @Override
    public int compareTo(Equipo e) {
        Guerrero m1 = this.getGuerreroMasFuerte();
        Guerrero m2 = e.getGuerreroMasFuerte();

        if (m1 == null && m2 == null) {
            return 0;
        }
        if (m1 == null) {
            return 1;
        }
        if (m2 == null) {
            return -1;
        }

        return m2.getFuerza() - m1.getFuerza();
    }

    public Guerrero getGuerreroMasFuerte() {
        Guerrero masFuerte = null;
        for (Guerrero g : guerreros) {
            if (g.estaVivo() && (masFuerte == null || g.getFuerza() > masFuerte.getFuerza())) {
                masFuerte = g;
            }
        }
        return masFuerte;
    }

    public boolean estaVivo() {
        for (Guerrero g : guerreros) {
            if (g.estaVivo()) {
                return true;
            }
        }
        return false;
    }
}
