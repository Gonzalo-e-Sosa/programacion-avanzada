package programacion_avanzada;

public class Busqueda {
    // Solo funciona con arreglos ordenados
    static int binariaIterativa(int[] arreglo, int objetivo) {
        if (arreglo == null || arreglo.length == 0) {
            return -1;
        }

        int izquierda = 0;
        int derecha = arreglo.length - 1;

        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;

            if (arreglo[medio] == objetivo) {
                return medio;
            }

            if (arreglo[medio] < objetivo) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }

        return -1;
    }

    // Solo funciona con arreglos ordenados
    static int binariaRecursiva(int[] arreglo, int objetivo) {
        if (arreglo == null || arreglo.length == 0) {
            return -1;
        }

        return binariaRecursiva(arreglo, objetivo, 0, arreglo.length - 1);
    }

    private static int binariaRecursiva(int[] arreglo, int objetivo, int izquierda, int derecha) {
        if (izquierda > derecha) {
            return -1;
        }

        int medio = izquierda + (derecha - izquierda) / 2;

        if (arreglo[medio] == objetivo) {
            return medio;
        }

        if (arreglo[medio] < objetivo) {
            return binariaRecursiva(arreglo, objetivo, medio + 1, derecha);
        }

        return binariaRecursiva(arreglo, objetivo, izquierda, medio - 1);
    }

}
