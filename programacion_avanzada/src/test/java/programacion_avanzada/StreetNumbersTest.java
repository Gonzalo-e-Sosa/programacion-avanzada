package programacion_avanzada;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StreetNumbersTest {

    private static final int[][] PROVIDED_CASES = {
            { 6, 8 },
            { 35, 49 },
            { 204, 288 },
            { 1189, 1681 },
            { 6930, 9800 },
            { 40391, 57121 },
            { 235416, 332928 },
            { 1372105, 1940449 },
            { 7997214, 11309768 },
            { 46611179, 65918161 }
    };

    @Test
    public void returnsMinusOneForInvalidStreetSizes() {
        assertEquals(-1, StreetNumbers.cuadratica(2));
        assertEquals(-1, StreetNumbers.lineal(2));
        assertEquals(-1, StreetNumbers.constante(2));
    }

    @Test
    public void constanteValidatesAllProvidedCases() {
        for (int[] testCase : PROVIDED_CASES) {
            int expected = testCase[0];
            int n = testCase[1];
            assertEquals(expected, StreetNumbers.constante(n));
        }
    }

    @Test
    public void returnsMinusOneWhenNoSolutionExists() {
        assertEquals(-1, StreetNumbers.cuadratica(7));
        assertEquals(-1, StreetNumbers.lineal(7));
        assertEquals(-1, StreetNumbers.constante(7));
    }

    @Test
    public void linealValidatesAllProvidedCases() {
        for (int[] testCase : PROVIDED_CASES) {
            int expected = testCase[0];
            int n = testCase[1];
            assertEquals(expected, StreetNumbers.lineal(n));
        }
    }

    @Test
    public void cuadraticaValidatesSmallProvidedCases() {
        for (int index = 0; index < 4; index++) {
            int expected = PROVIDED_CASES[index][0];
            int n = PROVIDED_CASES[index][1];
            assertEquals(expected, StreetNumbers.cuadratica(n));
        }
    }
}
