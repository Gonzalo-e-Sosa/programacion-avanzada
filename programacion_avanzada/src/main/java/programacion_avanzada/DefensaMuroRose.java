package programacion_avanzada;

import java.util.ArrayList;
import java.util.Comparator;

/*
Consigna
Contexto
El cuerpo de Ingenieros de la Legión de Reconocimiento ha sido asignado a proteger el Muro Rose ante un inminente ataque de titanes.
A lo largo del muro se han instalado cañones de defensa. Cada cañón tiene un alcance limitado, que le permite cubrir una porción específica del muro.
Sin embargo, activar un cañón consume una gran cantidad de pólvora y recursos, por lo que solo deben encenderse los estrictamente necesarios para proteger toda la sección crítica del muro.
Tu tarea es diseñar un algoritmo de defensa ávido que determine qué cañones deben activarse para que toda la muralla quede bajo protección, minimizando el número de cañones encendidos.

Datos disponibles
El tramo de muro a defender está representado por el intervalo [L, R].
Cada cañón tiene: 
una posición x_i (la ubicación en el muro, medida en metros desde el punto 0),
un alcance r_i (en metros), que cubre el segmento [x_i - r_i, x_i + r_i].

Además, se asegura que:
Es posible cubrir todo el tramo [L, R] con las torres disponibles.
Todos los valores son positivos y reales.

Objetivo
Activar la menor cantidad posible de cañones de forma que cada punto del muro dentro del intervalo [L, R] esté protegido por al menos un cañón.
Debe decir:
El número mínimo de cañones activados.
La lista de cañones seleccionados (su índice o posición).

Ejemplo
Tramo a defender [L, R] = [0, 20].
Los ingenieros disponen de los siguientes cañones:
+-------+----------------+---------------+
| Cañón | Posición (x_i) | Alcance (r_i) |
+-------+----------------+---------------+
|     1 |              2 |             3 |
|     2 |              7 |             5 |
|     3 |             14 |             4 |
|     4 |             17 |             6 |
|     5 |             20 |             2 |
|     6 |              5 |             1 |
+-------+----------------+---------------+

Salida esperada
Cañones activados: C1, C2 y C4
Número mínimo: 3 cañones activados

C1: [-1, 5] 
C2: [2, 12]
C3: [10, 18]
C4: [11, 23]
C5: [18, 22]
C6: [4, 6]

Ordenar por alcance izquierda -> [C5, C4, C3, C6, C2, C1]

C5 es candidato pero como tiene parte negativa 20 - 22 -> -2 se busca otro candidato
C4 es mejor candidato ya que a pesar de tener parte negativa 20 - 23 -> -3 tiene un alcance izquierda mayor 

*/

public class DefensaMuroRose {
    ArrayList<Cannon> cannons = new ArrayList<>();

    // Ordenar por mayores alcances a la izquierda
    // Seleccionar el de mayor alcance izquierda
    // Fijarse las posiciones que faltan para rellenar
    public void defenderMuro(int L, int R /* [L, R] */) {
        ArrayList<Cannon> seleccionados = new ArrayList<>();

        ordenarAlcances();

        int actual = L;
        int j = 0;

        while (actual < R) {
            int mejorFin = actual;
            Cannon mejorCañon = null;

            while (j < cannons.size() && cannons.get(j).alcanceIzquierda() <= actual) {
                if (cannons.get(j).alcanceDerecha() > mejorFin) {
                    mejorFin = cannons.get(j).alcanceDerecha();
                    mejorCañon = cannons.get(j);
                }
                j++;
            }

            if (mejorCañon == null)
                throw new Error("No se puede cubrir el muro.");

            seleccionados.add(mejorCañon);
            actual = mejorFin;
        }

        seleccionados.forEach(c -> System.out.println(c));
    }

    public void agregarCannon(Cannon c) {
        this.cannons.add(c);
    }

    public void ordenarAlcances() {
        this.cannons.sort(Comparator.naturalOrder());
    }

    public void mostrarCannons() {
        this.cannons.forEach(c -> System.out.println(c));
    }

    public class Cannon implements Comparable<Cannon> {
        int numero;
        int posicion;
        int alcance;

        public Cannon(int numero, int posicion, int alcance) {
            this.numero = numero;
            this.posicion = posicion;
            this.alcance = alcance;
        }

        int alcanceIzquierda() {
            return this.posicion - alcance;
        }

        int alcanceDerecha() {
            return this.posicion + alcance;
        }

        @Override
        public int compareTo(Cannon arg0) {
            return this.alcanceIzquierda() - arg0.alcanceIzquierda();
        }

        @Override
        public String toString() {
            return "C" + this.numero;
        }
    }
}
