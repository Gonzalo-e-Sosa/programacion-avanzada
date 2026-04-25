package programacion_avanzada;

/*
Parte 1
Dado un array y un rango delimitado por min y max (incluidos ambos extremos), contar la cantidad de elementos del array que están en el rango.
Realizar al menos dos versiones del algoritmo: iterativo y recursivo.
Analizar la complejidad computacional en ambos casos.

Ejemplo 1
Array: [1, 5, 3, 7, 9, 2, 8], Rango: [3..7]
Salida: 3

Ejemplo 2
Array: [10, 20, 30, 40, 50], Rango: [15..35]
Salida: 2

Parte 2
¿Cómo cambiaría la solución si se supiera que los elementos de array están ordenados? Desarrollar el código y analizar la complejidad bajo esta premisa.

Ejemplo 1
Array: [1, 2, 3, 4, 5, 7], Rango: [3..6]
Salida: 3

Ejemplo 2
Array: [15, 20, 25, 25, 30, 35, 40, 45], Rango: [22..37]
Salida: 4
 */

public class ContarNumerosEnRango {
    static int iterativaSinOrdenar(int arr[], int min, int max) {
        return NoOrdenado.iterativa(arr, min, max);
    }

    static int recursivaSinOrdenar(int arr[], int min, int max) {
        return NoOrdenado.recursiva(arr, min, max);
    }

    static int iterativaOrdenado(int arr[], int min, int max) {
        return Ordenado.iterativa(arr, min, max);
    }

    static int recursivaOrdenado(int arr[], int min, int max) {
        return Ordenado.recursiva(arr, min, max);
    }

    static class NoOrdenado {
        // Complejidad temporal: O(n)
        // Complejidad espacial: O(1)
        static int iterativa(int arr[], int min, int max) {
            if (min > max) // O(1)
                throw new IllegalArgumentException("Min no puede ser mayor a max.");

            if (arr == null || arr.length == 0) // O(1)
                return 0;

            int acum = 0; // O(1)

            // O(n)
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] >= min && arr[i] <= max) // O(1)
                    acum++;
            }

            return acum; // O(1)
        } // T(n) = c + n = O(n)

        // Complejidad temporal: O(n)
        // Complejidad espacial: O(n) debido a la pila de llamadas recursivas
        static int recursiva(int arr[], int min, int max) {
            if (min > max) // O(1)
                throw new IllegalArgumentException("Min no puede ser mayor a max.");

            if (arr == null || arr.length == 0) // O(1)
                return 0;

            return recursiva(arr, min, max, arr.length - 1);
        }

        private static int recursiva(int arr[], int min, int max, int n) {
            if (n < 0) // O(1)
                return 0;

            if (arr[n] >= min && arr[n] <= max) // O(1)
                return 1 + recursiva(arr, min, max, n - 1); // O(1) + T(n - 1)

            return recursiva(arr, min, max, n - 1); // T(n - 1)
        } // T(n) = c + T(n - 1) = T(0) + nc = O(n)
    }

    static class Ordenado {
        static int iterativa(int arr[], int min, int max) {
            if (min > max)
                throw new IllegalArgumentException("Min no puede ser mayor a max.");

            if (arr == null || arr.length == 0)
                return 0;

            int izq = 0;
            while (izq < arr.length && arr[izq] < min)
                izq++;

            int der = arr.length - 1;
            while (der >= 0 && arr[der] > max)
                der--;

            if (izq > der)
                return 0;

            return der - izq + 1;
        }

        static int recursiva(int arr[], int min, int max) {
            if (min > max)
                throw new IllegalArgumentException("Min no puede ser mayor a max.");

            return recursiva(arr, min, max, 0, arr.length - 1);
        }

        private static int recursiva(int arr[], int min, int max, int izq, int der) {
            if (izq > der)
                return 0;

            if (arr[izq] < min)
                return recursiva(arr, min, max, izq + 1, der);

            if (arr[der] > max)
                return recursiva(arr, min, max, izq, der - 1);

            return der - izq + 1;
        }
    }

}