package programacion_avanzada;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ContarNumerosEnRangoTest {
    @Test(expected = IllegalArgumentException.class)
    public void iterativaThrowsExceptionWhenMaxIsLowerThanMin() {
        ContarNumerosEnRango.iterativaSinOrdenar(new int[] { 1, 2, 3 }, 5, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void recursivaThrowsExceptionWhenMaxIsLowerThanMin() {
        ContarNumerosEnRango.recursivaSinOrdenar(new int[] { 1, 2, 3 }, 5, 3);
    }

    @Test
    public void iterativaCountsNumbersInsideInclusiveRange() {
        assertEquals(3, ContarNumerosEnRango.iterativaSinOrdenar(new int[] { 1, 5, 3, 7, 9, 2, 8 }, 3, 7));
        assertEquals(2, ContarNumerosEnRango.iterativaSinOrdenar(new int[] { 10, 20, 30, 40, 50 }, 15, 35));
        assertEquals(4, ContarNumerosEnRango.iterativaSinOrdenar(new int[] { 3, 4, 5, 7, 8 }, 3, 7));
    }

    @Test
    public void recursivaCountsNumbersInsideInclusiveRange() {
        assertEquals(3, ContarNumerosEnRango.recursivaSinOrdenar(new int[] { 1, 5, 3, 7, 9, 2, 8 }, 3, 7));
        assertEquals(2, ContarNumerosEnRango.recursivaSinOrdenar(new int[] { 10, 20, 30, 40, 50 }, 15, 35));
        assertEquals(4, ContarNumerosEnRango.recursivaSinOrdenar(new int[] { 3, 4, 5, 7, 8 }, 3, 7));
    }

    @Test
    public void iterativaReturnsZeroForEmptyArray() {
        assertEquals(0, ContarNumerosEnRango.iterativaSinOrdenar(new int[] {}, 1, 10));
    }

    @Test
    public void recursivaReturnsZeroForEmptyArray() {
        assertEquals(0, ContarNumerosEnRango.recursivaSinOrdenar(new int[] {}, 1, 10));
    }

    @Test
    public void iterativaOrdenadoCountsNumbersInsideInclusiveRange() {
        assertEquals(3, ContarNumerosEnRango.iterativaOrdenado(new int[] { 1, 2, 3, 4, 5, 7 }, 3, 6));
        assertEquals(4, ContarNumerosEnRango.iterativaOrdenado(new int[] { 15, 20, 25, 25, 30, 35, 40, 45 }, 22, 37));
    }
}