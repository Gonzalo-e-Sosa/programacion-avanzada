package programacion_avanzada;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EscaleraPalabrasTest {

    private EscaleraPalabras escalera;
    private Map<String, ArrayList<String>> listaAdyacencias;
    private Map<String, Integer> wordMap;

    @Before
    public void setUp() throws Exception {
        escalera = new EscaleraPalabras();

        // Usar reflexión para acceder a los atributos privados
        Field listaAdyacenciasField = EscaleraPalabras.class.getDeclaredField("listaAdyacencias");
        listaAdyacenciasField.setAccessible(true);
        listaAdyacencias = (Map<String, ArrayList<String>>) listaAdyacenciasField.get(escalera);

        Field wordMapField = EscaleraPalabras.class.getDeclaredField("wordMap");
        wordMapField.setAccessible(true);
        wordMap = (Map<String, Integer>) wordMapField.get(escalera);
    }

    // ==================== Pruebas para llenarMapa ====================

    @Test
    public void testLlenarMapaConPalabrasValidas() throws Exception {
        String[] palabras = { "hit", "hot", "dot", "dog" };

        Method llenarMapa = EscaleraPalabras.class.getDeclaredMethod("llenarMapa", String[].class);
        llenarMapa.setAccessible(true);
        llenarMapa.invoke(escalera, (Object) palabras);

        assertEquals("El mapa debería tener 4 palabras", 4, wordMap.size());
        assertTrue("'hit' debería estar en el mapa", wordMap.containsKey("hit"));
        assertTrue("'hot' debería estar en el mapa", wordMap.containsKey("hot"));
        assertTrue("'dot' debería estar en el mapa", wordMap.containsKey("dot"));
        assertTrue("'dog' debería estar en el mapa", wordMap.containsKey("dog"));
    }

    @Test
    public void testLlenarMapaConIndices() throws Exception {
        String[] palabras = { "red", "ted", "tex" };

        Method llenarMapa = EscaleraPalabras.class.getDeclaredMethod("llenarMapa", String[].class);
        llenarMapa.setAccessible(true);
        llenarMapa.invoke(escalera, (Object) palabras);

        assertEquals("'red' debería tener índice 0", 0, (int) wordMap.get("red"));
        assertEquals("'ted' debería tener índice 1", 1, (int) wordMap.get("ted"));
        assertEquals("'tex' debería tener índice 2", 2, (int) wordMap.get("tex"));
    }

    @Test
    public void testLlenarMapaVacio() throws Exception {
        String[] palabras = {};

        Method llenarMapa = EscaleraPalabras.class.getDeclaredMethod("llenarMapa", String[].class);
        llenarMapa.setAccessible(true);
        llenarMapa.invoke(escalera, (Object) palabras);

        assertEquals("El mapa debería estar vacío", 0, wordMap.size());
    }

    @Test
    public void testLlenarMapaConUnaPalabra() throws Exception {
        String[] palabras = { "hello" };

        Method llenarMapa = EscaleraPalabras.class.getDeclaredMethod("llenarMapa", String[].class);
        llenarMapa.setAccessible(true);
        llenarMapa.invoke(escalera, (Object) palabras);

        assertEquals("El mapa debería tener 1 palabra", 1, wordMap.size());
        assertEquals("'hello' debería tener índice 0", 0, (int) wordMap.get("hello"));
    }

    // ==================== Pruebas para generarCombinaciones ====================

    @Test
    public void testGenerarCombinacionesConPalabraCuatroLetras() throws Exception {
        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        generarCombinaciones.invoke(escalera, "hit");

        assertTrue("Debería haber generado combinaciones para 'hit'",
                listaAdyacencias.containsKey("hit"));

        ArrayList<String> combinaciones = listaAdyacencias.get("hit");
        assertTrue("Debería haber combinaciones", combinaciones.size() > 0);

        // Cada posición tiene 25 letras alternativas, para una palabra de 4 letras: 4 *
        // 25 = 100
        assertEquals("Debería haber 75 combinaciones para una palabra de 3 letras",
                75, combinaciones.size());
    }

    @Test
    public void testGenerarCombinacionesLongitudPalabra() throws Exception {
        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        generarCombinaciones.invoke(escalera, "ab");

        ArrayList<String> combinaciones = listaAdyacencias.get("ab");
        // 2 posiciones * 25 letras por posición = 50 combinaciones
        assertEquals("Debería haber 50 combinaciones para una palabra de 2 letras",
                50, combinaciones.size());
    }

    @Test
    public void testGenerarCombinacionesContieneVariacionesDePrimeraLetra() throws Exception {
        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        generarCombinaciones.invoke(escalera, "aaa");

        ArrayList<String> combinaciones = listaAdyacencias.get("aaa");

        // Buscar combinaciones donde solo la primera letra cambia
        boolean encontroVariacionesPrimeraLetra = false;
        for (String combinacion : combinaciones) {
            if (combinacion.contains("b") || combinacion.contains("c")) {
                encontroVariacionesPrimeraLetra = true;
                break;
            }
        }
        assertTrue("Debería haber variaciones de la primera letra", encontroVariacionesPrimeraLetra);
    }

    @Test
    public void testGenerarCombinacionesNoIncluiyeOriginales() throws Exception {
        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        generarCombinaciones.invoke(escalera, "cat");

        ArrayList<String> combinaciones = listaAdyacencias.get("cat");

        // No debería contener la palabra original "cat"
        assertFalse("Las combinaciones no deberían incluir la palabra original",
                combinaciones.contains("cat"));
    }

    @Test
    public void testGenerarCombinacionesPalabraUnLetra() throws Exception {
        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        generarCombinaciones.invoke(escalera, "a");

        ArrayList<String> combinaciones = listaAdyacencias.get("a");
        // 1 posición * 25 letras (excluye la 'a' original) = 25 combinaciones
        assertEquals("Debería haber 25 combinaciones para una palabra de 1 letra",
                25, combinaciones.size());
    }

    // ==================== Pruebas para obtenerVecinos ====================

    @Test
    public void testObtenerVecinosConVecinosExistentes() throws Exception {
        // Preparar datos
        String[] palabras = { "hot", "dot", "dog", "cog" };
        Method llenarMapa = EscaleraPalabras.class.getDeclaredMethod("llenarMapa", String[].class);
        llenarMapa.setAccessible(true);
        llenarMapa.invoke(escalera, (Object) palabras);

        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        generarCombinaciones.invoke(escalera, "hit");

        Method obtenerVecinos = EscaleraPalabras.class.getDeclaredMethod("obtenerVecinos", String.class);
        obtenerVecinos.setAccessible(true);
        List<String> vecinos = (List<String>) obtenerVecinos.invoke(escalera, "hit");

        assertTrue("Debería haber vecinos para 'hit'", vecinos.size() > 0);

        // Verificar que todos los vecinos están en el wordMap
        for (String vecino : vecinos) {
            assertTrue("El vecino '" + vecino + "' debería estar en wordMap",
                    wordMap.containsKey(vecino));
        }
    }

    @Test
    public void testObtenerVecinosFiltraCorrectamente() throws Exception {
        // Preparar datos
        String[] palabras = { "hot" };
        Method llenarMapa = EscaleraPalabras.class.getDeclaredMethod("llenarMapa", String[].class);
        llenarMapa.setAccessible(true);
        llenarMapa.invoke(escalera, (Object) palabras);

        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        generarCombinaciones.invoke(escalera, "hit");

        Method obtenerVecinos = EscaleraPalabras.class.getDeclaredMethod("obtenerVecinos", String.class);
        obtenerVecinos.setAccessible(true);
        List<String> vecinos = (List<String>) obtenerVecinos.invoke(escalera, "hit");

        // Solo "hot" difiere en una letra de "hit", por lo que debería ser el único
        // vecino
        assertTrue("Debería contener a 'hot'", vecinos.contains("hot"));
    }

    @Test
    public void testObtenerVecinosSinVecinosEnWordMap() throws Exception {
        // Preparar datos sin agregar vecinos al wordMap
        String[] palabras = { "xyz" };
        Method llenarMapa = EscaleraPalabras.class.getDeclaredMethod("llenarMapa", String[].class);
        llenarMapa.setAccessible(true);
        llenarMapa.invoke(escalera, (Object) palabras);

        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        generarCombinaciones.invoke(escalera, "abc");

        Method obtenerVecinos = EscaleraPalabras.class.getDeclaredMethod("obtenerVecinos", String.class);
        obtenerVecinos.setAccessible(true);
        List<String> vecinos = (List<String>) obtenerVecinos.invoke(escalera, "abc");

        // No debería haber vecinos porque ninguna combinación está en wordMap
        assertEquals("No debería haber vecinos en wordMap", 0, vecinos.size());
    }

    @Test
    public void testObtenerVecinosErrorParaPalabraSinCombinaciones() throws Exception {
        Method obtenerVecinos = EscaleraPalabras.class.getDeclaredMethod("obtenerVecinos", String.class);
        obtenerVecinos.setAccessible(true);

        // Intentar obtener vecinos para una palabra que no tiene combinaciones
        // generadas
        try {
            obtenerVecinos.invoke(escalera, "unknown");
            fail("Debería lanzar Error cuando no hay combinaciones para la palabra");
        } catch (InvocationTargetException e) {
            assertTrue("La causa debería ser Error", e.getCause() instanceof Error);
            assertEquals("No hay vecinos para: unknown", e.getCause().getMessage());
        }
    }

    @Test
    public void testObtenerVecinosMultiples() throws Exception {
        // Preparar datos
        String[] palabras = { "hot", "hit", "hat" };
        Method llenarMapa = EscaleraPalabras.class.getDeclaredMethod("llenarMapa", String[].class);
        llenarMapa.setAccessible(true);
        llenarMapa.invoke(escalera, (Object) palabras);

        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        generarCombinaciones.invoke(escalera, "hit");

        Method obtenerVecinos = EscaleraPalabras.class.getDeclaredMethod("obtenerVecinos", String.class);
        obtenerVecinos.setAccessible(true);
        List<String> vecinos = (List<String>) obtenerVecinos.invoke(escalera, "hit");

        assertTrue("Debería contener a 'hot'", vecinos.contains("hot"));
        assertTrue("Debería contener a 'hat'", vecinos.contains("hat"));
        assertEquals("Debería tener 2 vecinos", 2, vecinos.size());
    }
}
