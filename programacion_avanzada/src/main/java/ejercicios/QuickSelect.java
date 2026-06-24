package ejercicios;

public class QuickSelect {
    /**
     * Devuelve el k-ésimo mínimo (1-based) en el arreglo usando QuickSelect.
     * Wrapper público que llama a la versión que opera sobre un rango.
     */
    public static int encontrarKMinimo(int[] arr, int k) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("Array vacío");
        if (k < 1 || k > arr.length)
            throw new IllegalArgumentException("k fuera de rango");
        return encontrarKMinimo(arr, k, 0, arr.length - 1);
    }

    // k es 1-based respecto al subarreglo [izq..der]
    private static int encontrarKMinimo(int[] arr, int k, int izq, int der) {
        if (izq == der)
            return arr[izq];

        int pivotIndex = elegirPivote(izq, der);
        int pivot = arr[pivotIndex];
        intercambiar(arr, pivotIndex, der); // mover pivote al final

        int storeIndex = izq;
        for (int i = izq; i < der; i++) {
            if (arr[i] <= pivot) {
                intercambiar(arr, i, storeIndex);
                storeIndex++;
            }
        }
        intercambiar(arr, storeIndex, der); // colocar pivote en su posición final

        int rank = storeIndex - izq + 1; // posición del pivote en orden dentro del subarreglo
        if (k == rank) {
            return arr[storeIndex];
        } else if (k < rank) {
            return encontrarKMinimo(arr, k, izq, storeIndex - 1);
        } else {
            return encontrarKMinimo(arr, k - rank, storeIndex + 1, der);
        }
    }

    private static int elegirPivote(int izq, int der) {
        return izq + (der - izq) / 2;
    }

    private static void intercambiar(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}