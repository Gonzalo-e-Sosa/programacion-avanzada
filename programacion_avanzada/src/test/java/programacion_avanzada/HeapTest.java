package programacion_avanzada;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;

import org.junit.Test;

public class HeapTest {

    @Test
    public void insertMaintainsHeapPropertyAndRootIsMinimum() throws Exception {
        Heap heap = new Heap();
        heap.insert(10);
        heap.insert(4);
        heap.insert(7);
        heap.insert(1);
        heap.insert(3);

        Field f = Heap.class.getDeclaredField("values");
        f.setAccessible(true);
        int[] vals = (int[]) f.get(heap);

        // La implementación usa índice 1 para la raíz, por lo que el mínimo debe estar
        // en vals[1]
        assertEquals(1, vals[1]);

        // Verificar propiedad de montículo: padre <= hijo para entradas no vacías
        for (int i = 2; i < vals.length; i++) {
            if (vals[i] == 0) {
                break;
            }
            int parent = i / 2;
            assertTrue("Parent must be <= child", vals[parent] <= vals[i]);
        }
    }

    @Test
    public void constructorBuildsHeapFromArray() throws Exception {
        int[] input = { 10, 4, 7, 1, 3 };
        Heap heap = new Heap(input);

        Field f = Heap.class.getDeclaredField("values");
        f.setAccessible(true);
        int[] vals = (int[]) f.get(heap);

        // El mínimo debe estar en la raíz (índice 1)
        assertEquals(1, vals[1]);

        // Verificar propiedad de montículo en el arreglo interno
        for (int i = 2; i < vals.length; i++) {
            if (vals[i] == 0)
                break;
            int parent = i / 2;
            assertTrue("Parent must be <= child", vals[parent] <= vals[i]);
        }
    }
}
