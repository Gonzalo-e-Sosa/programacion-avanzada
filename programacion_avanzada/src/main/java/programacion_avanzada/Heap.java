package programacion_avanzada;

// Montícula (Árbol binario implementado con arreglo) - no se requiere para QuickSelect, pero es un tema relacionado
public class Heap {
    private static final int DEFAULT_HEAP_SIZE = 10;
    private int[] values = new int[DEFAULT_HEAP_SIZE]; // Inicializa con 0

    public Heap() {
        // Constructor por defecto: usa el arreglo inicializado `values`.
    }

    public Heap(int[] array) {
        this.values = new int[array.length + 1];

        for (int i = 0; i < array.length; i++) {
            insert(array[i]);
        }
    }

    private boolean isEmpty() {
        return values.length <= 1 || values[1] == 0;
    }

    // Busca el índice del último elemento no nulo en el arreglo interno
    private int findLastIndex() {
        for (int i = values.length - 1; i >= 1; i--) {
            if (values[i] != 0) {
                return i;
            }
        }
        return 0;
    }

    // Mueve el elemento en `last` a la raíz y borra la posición `last`
    private void replaceRootWithLast(int last) {
        values[1] = values[last];
        values[last] = 0;
    }

    // Sift-down a partir de `start` para restaurar la propiedad de montículo
    private void siftDown(int start) {
        int current = start;
        int smallest;
        while ((smallest = minChildIndex(current)) != current) {
            swap(current, smallest);
            current = smallest;
        }
    }

    // Devuelve el índice del hijo (izq o der) con el valor menor;
    // si no hay hijo menor que el padre devuelve `current`.
    private int minChildIndex(int current) {
        int left = current * 2;
        int right = left + 1;
        int smallest = current;

        if (left < values.length && values[left] != 0 && values[left] < values[smallest]) {
            smallest = left;
        }
        if (right < values.length && values[right] != 0 && values[right] < values[smallest]) {
            smallest = right;
        }
        return smallest;
    }

    private void swap(int a, int b) {
        int temp = values[a];
        values[a] = values[b];
        values[b] = temp;
    }

    private void incrementArraySize() {
        int[] cloned = this.values.clone();
        this.values = new int[this.values.length * 2]; // Pido el doble de espacio

        for (int i = 0; i < cloned.length; i++) {
            this.values[i] = cloned[i];
        }
    }

    // Sift-up: insertar un nuevo valor y mantener la propiedad del montículo
    public void insert(int value) {
        if (values.length <= 1) {
            this.values = new int[DEFAULT_HEAP_SIZE];
        }

        if (values[1] == 0) {
            values[1] = value;
            return;
        }

        int i = 2;
        while (i < values.length && values[i] != 0) {
            i++;
        }

        if (i >= values.length) {
            incrementArraySize();
        }

        values[i] = value;
        heapify(i);
    }

    private void heapify(int i) {
        int current = i;
        while (current > 1) {
            int fatherIndex = current / 2;
            if (values[fatherIndex] > values[current]) {
                swap(fatherIndex, current);
                current = fatherIndex;
            } else {
                break;
            }
        }
    }

    public int removeRoot() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Heap vacío");
        }

        int root = values[1];
        int last = findLastIndex();

        if (last == 1) {
            values[1] = 0;
            return root;
        }

        replaceRootWithLast(last);
        siftDown(1);
        return root;
    }

    /**
     * Devuelve el valor de la raíz sin removerlo.
     * Lanza IllegalStateException si el heap está vacío.
     */
    public int peek() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Heap vacío");
        }
        return values[1];
    }

    public int[] toArray() {
        return this.values.clone();
    }
}
