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
        static int iterativa(int arr[], int min, int max) {
            if (min > max)
                throw new IllegalArgumentException("Min no puede ser mayor a max.");

            int acum = 0;

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] >= min && arr[i] <= max)
                    acum++;
            }

            return acum;
        }

        static int recursiva(int arr[], int min, int max) {
            if (min > max)
                throw new IllegalArgumentException("Min no puede ser mayor a max.");

            if (arr.length == 0)
                return 0;

            return recursiva(arr, min, max, 0);
        }

        private static int recursiva(int arr[], int min, int max, int n) {
            if (n >= arr.length)
                return 0;

            if (arr[n] >= min && arr[n] <= max)
                return 1 + recursiva(arr, min, max, n + 1);

            return recursiva(arr, min, max, n + 1);
        }
    }

    static class Ordenado {
        static int iterativa(int arr[], int min, int max) {
            if (min > max)
                throw new IllegalArgumentException("Min no puede ser mayor a max.");

            int izq = 0, der = arr.length - 1;

            while (izq < der && (arr[izq] < min || arr[der] > max)) {
                if (arr[izq] < min)
                    izq++;

                if (arr[der] > max)
                    der--;
            }

            if (der - izq == 0)
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