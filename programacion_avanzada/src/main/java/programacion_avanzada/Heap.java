package programacion_avanzada;

// Montícula (Árbol binario implementado con arreglo) - no se requiere para QuickSelect, pero es un tema relacionado
public class Heap {
    private static final int DEFAULT_HEAP_SIZE = 10;
    private int[] values = new int[DEFAULT_HEAP_SIZE]; // Inicializa con 0

    public Heap() {
        // Constructor por defecto: usa el arreglo inicializado `values`.
    }

    public Heap(int[] array) {
        this.values = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            insert(array[i]);
        }
    }

    // Sift-up: insertar un nuevo valor y mantener la propiedad del montículo
    // 1. Si no hay valores, insertar el nuevo valor en la raíz (posición 1)
    // 2. Si hay valores, insertar el nuevo valor completando el último nivel de
    // izquierda a derecha
    // 3. Luego, comparar el nuevo valor con su padre y si es menor, intercambiar y
    // repetir el proceso hasta que el nuevo valor esté en la posición correcta
    public void insert(int value) {
        if (values[1] == 0) {
            values[1] = value;
            return;
        }

        int i = 2;
        while (values[i] != 0 && i < values.length) {
            i++;
        }

        if (i == values.length - 1) {
            incrementArraySize();
        }

        values[i] = value;

        heapify(i);
    }

    // Comparar el valor con el padre y si es menor, intercambiar
    // El padre de i es i/2
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

    // Sift-down: eliminar la raíz y mantener la propiedad del montículo
    public int removeRoot() {
        int root = values[1]; // Guardar el valor de la raíz para retornarlo al final

        // 1. Quitar la raíz (posición 1)
        // 2. Reemplazar la raíz con el último valor del montículo
        // 3. Eliminar el último valor del montículo
        // 4. Comparar el nuevo valor de la raíz con sus hijos y si es mayor,
        // intercambiar
        // con el hijo menor y repetir el proceso hasta que el nuevo valor esté en la
        // posición correcta

        return root; // Retornar el valor eliminado (la raíz original)
    }
}
