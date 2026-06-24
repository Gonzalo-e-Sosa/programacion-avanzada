package ejercicios.torneo_coliseo_hierro;

import java.util.PriorityQueue;

/* 

Parte 1: El torneo

En el imponente Coliseo de Hierro, los guerreros más temidos del reino se reúnen para un torneo sin piedad. De este sangriento enfrentamiento surgirá el título de Campeón Eterno, o la arena quedará sembrada de cadáveres si ninguno sobrevive.

Reglas del Coliseo
1. Siempre se eligen a los dos guerreros con mayor fuerza para combatir.
2. Tras el choque de espadas, el más fuerte vence, pero queda debilitado, conservando solo la diferencia de fuerzas con respecto a su rival.
3. Si ambos poseen la misma fuerza, mueren en el acto, dejando la arena manchada de sangre.
4. El torneo continúa hasta que sólo quede un guerrero en pie… o ninguno.

Ejemplo de Torneo

Guerreros iniciales:
- Ragnar (7)
- Kael (4)
- Darius (9)
- Ordan (3)

Desarrollo del torneo:
1. Darius (9) vs Ragnar (7) → Gana Darius, pero queda con fuerza 2.
2. Ordan (3) vs Kael (4) → Gana Kael, pero queda con fuerza 1.
3. Kael (1) vs Darius (2) → Gana Darius, pero queda con fuerza 1.

Resultado final:
El Guerrero Campeón es Darius, con fuerza 1.

Parte 2: Torneo por Equipos

Ahora los torneos quieren jugarse con equipos, por lo que dos personas del mismo equipo no pueden pelear entre sí por más que sean los dos más fuertes del coliseo.
Los combates, de todos modos, siguen siendo de un luchador contra otro luchador, pero si los dos más fuertes pertenecen al mismo equipo, no lucharán entre sí.
El torneo continúa hasta que sólo quede un equipo en pie, con uno o más gladiadores.

*/

public class Coliseo {
    private PriorityQueue<Guerrero> heap;
    private PriorityQueue<Equipo> equipos;

    public Coliseo() {
        heap = new PriorityQueue<Guerrero>();
        equipos = new PriorityQueue<Equipo>();
    }

    public void agregarGuerrero(Guerrero g) {
        heap.add(g);
    }

    public void agregarEquipo(Equipo e) {
        equipos.add(e);
    }

    public Guerrero comenzarTorneo() {
        while (heap.size() > 1) {
            Guerrero g1 = heap.poll(); // Saco al más fuerte
            Guerrero g2 = heap.poll(); // Saco al segundo más fuerte

            g1.pelear(g2);

            if (g1.estaVivo()) {
                heap.add(g1);
            }
            if (g2.estaVivo()) {
                heap.add(g2);
            }
        }

        if (heap.isEmpty()) {
            return null;
        }

        return heap.poll(); // El último guerrero en pie, o null si no queda ninguno
    }

    public Equipo comenzarTorneoPorEquipos() {
        while (equipos.size() > 1) {
            Equipo e1 = equipos.poll(); // Saco al equipo más fuerte
            Equipo e2 = equipos.poll(); // Saco al segundo equipo más fuerte

            Guerrero g1 = e1.getGuerreroMasFuerte();
            Guerrero g2 = e2.getGuerreroMasFuerte();

            // Si alguno de los equipos no tiene guerreros vivos, lo dejamos fuera del
            // torneo
            if (g1 == null || g2 == null) {
                if (g1 != null) {
                    equipos.add(e1);
                }
                if (g2 != null) {
                    equipos.add(e2);
                }
            } else {
                g1.pelear(g2);

                if (e1.estaVivo()) {
                    equipos.add(e1);
                }
                if (e2.estaVivo()) {
                    equipos.add(e2);
                }
            }
        }

        if (equipos.isEmpty()) {
            return null;
        }

        return equipos.poll();
    }
}