package programacion_avanzada;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BusquedaTest {

    private static final int[] ORDENADO = { 1, 3, 5, 7, 9, 11, 13 };
    private static final int[] NO_ORDENADO = { 7, 1, 9, 3, 11, 5 };

    @Test
    public void returnsIndexWhenElementIsAtBeginningMiddleAndEnd() {
        assertEquals(0, Busqueda.binariaIterativa(ORDENADO, 1));
        assertEquals(3, Busqueda.binariaIterativa(ORDENADO, 7));
        assertEquals(6, Busqueda.binariaIterativa(ORDENADO, 13));

        assertEquals(0, Busqueda.binariaRecursiva(ORDENADO, 1));
        assertEquals(3, Busqueda.binariaRecursiva(ORDENADO, 7));
        assertEquals(6, Busqueda.binariaRecursiva(ORDENADO, 13));
    }

    @Test
    public void returnsMinusOneWhenElementIsNotPresent() {
        assertEquals(-1, Busqueda.binariaIterativa(ORDENADO, 0));
        assertEquals(-1, Busqueda.binariaIterativa(ORDENADO, 8));
        assertEquals(-1, Busqueda.binariaIterativa(ORDENADO, 20));

        assertEquals(-1, Busqueda.binariaRecursiva(ORDENADO, 0));
        assertEquals(-1, Busqueda.binariaRecursiva(ORDENADO, 8));
        assertEquals(-1, Busqueda.binariaRecursiva(ORDENADO, 20));
    }

    @Test
    public void returnsMinusOneForNullAndEmptyArray() {
        assertEquals(-1, Busqueda.binariaIterativa(null, 10));
        assertEquals(-1, Busqueda.binariaIterativa(new int[] {}, 10));

        assertEquals(-1, Busqueda.binariaRecursiva(null, 10));
        assertEquals(-1, Busqueda.binariaRecursiva(new int[] {}, 10));
    }

    @Test
    public void handlesSingleElementArray() {
        assertEquals(0, Busqueda.binariaIterativa(new int[] { 42 }, 42));
        assertEquals(-1, Busqueda.binariaIterativa(new int[] { 42 }, 99));

        assertEquals(0, Busqueda.binariaRecursiva(new int[] { 42 }, 42));
        assertEquals(-1, Busqueda.binariaRecursiva(new int[] { 42 }, 99));
    }

    @Test
    public void iterativeAndRecursiveBehaveTheSameForUnsortedArray() {
        int[] objetivos = { 7, 9, 3, 2, 12 };

        for (int objetivo : objetivos) {
            assertEquals(Busqueda.binariaIterativa(NO_ORDENADO, objetivo),
                    Busqueda.binariaRecursiva(NO_ORDENADO, objetivo));
        }
    }
}