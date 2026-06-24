package ejercicios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Arrays;

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

    @Test
    public void removeRootRemovesMinimumRepeatedly() {
        Heap heap = new Heap();
        heap.insert(10);
        heap.insert(4);
        heap.insert(7);
        heap.insert(1);
        heap.insert(3);

        int[] expected = { 1, 3, 4, 7, 10 };
        for (int v : expected) {
            assertEquals(v, heap.removeRoot());
        }
    }

    @Test(expected = IllegalStateException.class)
    public void removeRootOnEmptyThrows() {
        Heap heap = new Heap();
        heap.removeRoot();
    }

    @Test
    public void peekReturnsRootWithoutRemoving() {
        Heap heap = new Heap();
        heap.insert(10);
        heap.insert(4);
        heap.insert(7);
        heap.insert(1);

        assertEquals(1, heap.peek());
        assertEquals(1, heap.removeRoot());
        assertEquals(4, heap.removeRoot()); // next minimum is 4
    }

    @Test
    public void toArrayReturnsClone() throws Exception {
        Heap heap = new Heap();
        heap.insert(3);
        heap.insert(1);

        int[] arr = heap.toArray();
        arr[1] = 999; // mutate returned array

        // internal heap should be unchanged
        assertEquals(1, heap.peek());
    }

    @Test(expected = IllegalStateException.class)
    public void constructorWithEmptyArrayProducesEmptyHeap() {
        Heap heap = new Heap(new int[] {});
        heap.removeRoot();
    }

    @Test
    public void resizingAndBulkInsertProducesSortedOutput() {
        Heap heap = new Heap();
        int n = 100;
        for (int i = n; i >= 1; i--) {
            heap.insert(i);
        }

        int[] out = new int[n];
        for (int i = 0; i < n; i++) {
            out[i] = heap.removeRoot();
        }

        int[] expected = new int[n];
        for (int i = 0; i < n; i++)
            expected[i] = i + 1;
        assertTrue(Arrays.equals(expected, out));
    }

    @Test
    public void duplicatesProduceNonDecreasingSequence() {
        Heap heap = new Heap();
        int[] inputs = { 5, 1, 5, 3, 5, 1 };
        for (int v : inputs)
            heap.insert(v);

        int[] out = new int[inputs.length];
        for (int i = 0; i < out.length; i++)
            out[i] = heap.removeRoot();

        int[] expected = { 1, 1, 3, 5, 5, 5 };
        assertTrue(Arrays.equals(expected, out));
    }
}
