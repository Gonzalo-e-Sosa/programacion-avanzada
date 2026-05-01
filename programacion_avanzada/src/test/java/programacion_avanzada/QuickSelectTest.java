package programacion_avanzada;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class QuickSelectTest {

    @Test
    public void findsKthMinimumSimpleCases() {
        int[] a1 = { 3, 1, 2 };
        assertEquals(1, QuickSelect.encontrarKMinimo(a1.clone(), 1));
        assertEquals(2, QuickSelect.encontrarKMinimo(a1.clone(), 2));
        assertEquals(3, QuickSelect.encontrarKMinimo(a1.clone(), 3));

        int[] a2 = { 4, 2, 7, 5 };
        assertEquals(2, QuickSelect.encontrarKMinimo(a2.clone(), 1));
        assertEquals(7, QuickSelect.encontrarKMinimo(a2.clone(), 4));
    }

    @Test
    public void handlesDuplicatesCorrectly() {
        int[] arr = { 5, 3, 5, 2, 5 };
        // ordenado: 2,3,5,5,5
        assertEquals(2, QuickSelect.encontrarKMinimo(arr.clone(), 1));
        assertEquals(3, QuickSelect.encontrarKMinimo(arr.clone(), 2));
        assertEquals(5, QuickSelect.encontrarKMinimo(arr.clone(), 3));
        assertEquals(5, QuickSelect.encontrarKMinimo(arr.clone(), 5));
    }

    @Test
    public void matchesSortedOrderForFixedArray() {
        int[] arr = { 9, 1, 8, 3, 7, 2, 6, 4, 5 };
        int[] sorted = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        for (int k = 1; k <= sorted.length; k++) {
            assertEquals(sorted[k - 1], QuickSelect.encontrarKMinimo(arr.clone(), k));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsForInvalidKZero() {
        int[] arr = { 1, 2, 3 };
        QuickSelect.encontrarKMinimo(arr, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsForKGreaterThanLength() {
        int[] arr = { 1, 2, 3 };
        QuickSelect.encontrarKMinimo(arr, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsForNullArray() {
        QuickSelect.encontrarKMinimo(null, 1);
    }
}
