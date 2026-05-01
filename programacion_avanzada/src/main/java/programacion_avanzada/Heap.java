package programacion_avanzada;

// Montícula (Árbol binario implementado con arreglo) - no se requiere para QuickSelect, pero es un tema relacionado
public class Heap {
    private static final int DEFAULT_SIZE = 10;
    private int[] values = createInitializedValues();

    private static int[] createInitializedValues() {
        int[] values = new int[DEFAULT_SIZE];
        values[0] = 0;
        for (int i = 1; i < values.length; i++) {
            values[i] = -1;
        }
        return values;
    }

    public Heap(int[] array) {
        this.values = convertToHeap(array);
    }

    int[] convertToHeap(int[] array) {
        // Asegurar que el arreglo interno tenga espacio (índice 0 guarda el tamaño)
        int needed = array.length + 1; // +1 porque usamos índice 1..n
        if (this.values == null || this.values.length < needed) {
            this.values = new int[needed];
        } else {
            // reiniciar a valores iniciales
            for (int i = 0; i < this.values.length; i++) {
                this.values[i] = 0;
            }
            // marcar posiciones vacías con -1 a partir de 1
            for (int i = 1; i < this.values.length; i++) {
                this.values[i] = -1;
            }
        }
        this.values[0] = 0; // tamaño inicial 0

        // Insertar cada elemento para construir el montículo
        for (int v : array) {
            insert(v);
        }

        return this.values;
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

    }

    void incrementArraySize() {
        int[] cloned = this.values.clone();
        this.values = new int[this.values.length * 2]; // Pido el doble de espacio

        for (int i = 0; i < cloned.length; i++) {
            this.values[i] = cloned[i];
        }
    }

    // Comparar el valor con el padre y si es menor, intercambiar
    // El padre de a es i/2 donde i es la posición de a en el arreglo
    void heapify(int[] array, int i, int a) {
        if (array[i / 2] > a) {
            int temp = array[i / 2];
            array[i / 2] = a;
            a = temp;
        }
    }

    // Sift-down: eliminar la raíz y mantener la propiedad del montículo
    public int removeRoot() {
        int root = values[0]; // Guardar el valor de la raíz para retornarlo al final

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
