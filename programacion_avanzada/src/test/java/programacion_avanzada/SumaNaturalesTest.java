package programacion_avanzada;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SumaNaturalesTest {

    @Test
    public void iterativaReturnsZeroForNonPositiveValues() {
        assertEquals(0, SumaNaturales.iterativa(0));
        assertEquals(0, SumaNaturales.iterativa(-5));
    }

    @Test
    public void recursivaReturnsZeroForNonPositiveValues() {
        assertEquals(0, SumaNaturales.recursiva(0));
        assertEquals(0, SumaNaturales.recursiva(-5));
    }

    @Test
    public void iterativaCalculatesSumOfFirstNaturalNumbers() {
        assertEquals(1, SumaNaturales.iterativa(1));
        assertEquals(6, SumaNaturales.iterativa(3));
        assertEquals(55, SumaNaturales.iterativa(10));
    }

    @Test
    public void recursivaCalculatesSumOfFirstNaturalNumbers() {
        assertEquals(1, SumaNaturales.recursiva(1));
        assertEquals(6, SumaNaturales.recursiva(3));
        assertEquals(55, SumaNaturales.recursiva(10));
    }
}