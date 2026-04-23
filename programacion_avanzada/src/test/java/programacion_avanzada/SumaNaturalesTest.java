package programacion_avanzada;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SumaNaturalesTest {
    @Test(expected = IllegalArgumentException.class)
    public void iterativaThrowsExceptionForNonPositiveValues() {
        SumaNaturales.iterativa(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void recursivaThrowsExceptionForNonPositiveValues() {
        SumaNaturales.recursiva(0);
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