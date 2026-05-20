package programacion_avanzada;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EscaleraPalabrasTest {

    private EscaleraPalabras escalera;
    private Set<String> diccionario;

    @Before
    public void setUp() throws Exception {
        escalera = new EscaleraPalabras();

        java.lang.reflect.Field diccionarioField = EscaleraPalabras.class.getDeclaredField("diccionario");
        diccionarioField.setAccessible(true);
        diccionario = (Set<String>) diccionarioField.get(escalera);
    }

    // ==================== Pruebas para cargarDiccionario ====================

    @Test
    public void testCargarDiccionarioConPalabrasValidas() throws Exception {
        String[] palabras = { "hit", "hot", "dot", "dog" };

        Method cargarDiccionario = EscaleraPalabras.class.getDeclaredMethod("cargarDiccionario", String[].class);
        cargarDiccionario.setAccessible(true);
        cargarDiccionario.invoke(escalera, (Object) palabras);

        assertEquals("El diccionario debería tener 4 palabras", 4, diccionario.size());
        assertTrue("'hit' debería estar en el diccionario", diccionario.contains("hit"));
        assertTrue("'hot' debería estar en el diccionario", diccionario.contains("hot"));
        assertTrue("'dot' debería estar en el diccionario", diccionario.contains("dot"));
        assertTrue("'dog' debería estar en el diccionario", diccionario.contains("dog"));
    }

    @Test
    public void testCargarDiccionarioConPalabrasUnicas() throws Exception {
        String[] palabras = { "red", "ted", "tex" };

        Method cargarDiccionario = EscaleraPalabras.class.getDeclaredMethod("cargarDiccionario", String[].class);
        cargarDiccionario.setAccessible(true);
        cargarDiccionario.invoke(escalera, (Object) palabras);

        assertTrue("'red' debería estar en el diccionario", diccionario.contains("red"));
        assertTrue("'ted' debería estar en el diccionario", diccionario.contains("ted"));
        assertTrue("'tex' debería estar en el diccionario", diccionario.contains("tex"));
    }

    @Test
    public void testCargarDiccionarioVacio() throws Exception {
        String[] palabras = {};

        Method cargarDiccionario = EscaleraPalabras.class.getDeclaredMethod("cargarDiccionario", String[].class);
        cargarDiccionario.setAccessible(true);
        cargarDiccionario.invoke(escalera, (Object) palabras);

        assertEquals("El diccionario debería estar vacío", 0, diccionario.size());
    }

    @Test
    public void testCargarDiccionarioConUnaPalabra() throws Exception {
        String[] palabras = { "hello" };

        Method cargarDiccionario = EscaleraPalabras.class.getDeclaredMethod("cargarDiccionario", String[].class);
        cargarDiccionario.setAccessible(true);
        cargarDiccionario.invoke(escalera, (Object) palabras);

        assertEquals("El diccionario debería tener 1 palabra", 1, diccionario.size());
        assertTrue("'hello' debería estar en el diccionario", diccionario.contains("hello"));
    }

    // ==================== Pruebas para generarCombinaciones ====================

    @Test
    public void testGenerarCombinacionesConPalabraCuatroLetras() throws Exception {
        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        ArrayList<String> combinaciones = (ArrayList<String>) generarCombinaciones.invoke(escalera, "hit");

        assertNotNull("Debería haber generado combinaciones para 'hit'", combinaciones);
        assertTrue("Debería haber combinaciones", combinaciones.size() > 0);

        assertEquals("Debería haber 75 combinaciones para una palabra de 3 letras",
                75, combinaciones.size());
    }

    @Test
    public void testGenerarCombinacionesLongitudPalabra() throws Exception {
        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        ArrayList<String> combinaciones = (ArrayList<String>) generarCombinaciones.invoke(escalera, "ab");

        assertEquals("Debería haber 50 combinaciones para una palabra de 2 letras",
                50, combinaciones.size());
    }

    @Test
    public void testGenerarCombinacionesContieneVariacionesDePrimeraLetra() throws Exception {
        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        ArrayList<String> combinaciones = (ArrayList<String>) generarCombinaciones.invoke(escalera, "aaa");

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
        ArrayList<String> combinaciones = (ArrayList<String>) generarCombinaciones.invoke(escalera, "cat");

        // No debería contener la palabra original "cat"
        assertFalse("Las combinaciones no deberían incluir la palabra original",
                combinaciones.contains("cat"));
    }

    @Test
    public void testGenerarCombinacionesPalabraUnLetra() throws Exception {
        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        ArrayList<String> combinaciones = (ArrayList<String>) generarCombinaciones.invoke(escalera, "a");

        assertEquals("Debería haber 25 combinaciones para una palabra de 1 letra",
                25, combinaciones.size());
    }

    // ==================== Pruebas para obtenerVecinos ====================

    @Test
    public void testObtenerVecinosConVecinosExistentes() throws Exception {
        // Preparar datos
        String[] palabras = { "hot", "dot", "dog", "cog" };
        Method cargarDiccionario = EscaleraPalabras.class.getDeclaredMethod("cargarDiccionario", String[].class);
        cargarDiccionario.setAccessible(true);
        cargarDiccionario.invoke(escalera, (Object) palabras);

        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        ArrayList<String> combinaciones = (ArrayList<String>) generarCombinaciones.invoke(escalera, "hit");

        Method obtenerVecinos = EscaleraPalabras.class.getDeclaredMethod("obtenerVecinos", String.class,
                List.class);
        obtenerVecinos.setAccessible(true);
        List<String> vecinos = (List<String>) obtenerVecinos.invoke(escalera, "hit", combinaciones);

        assertTrue("Debería haber vecinos para 'hit'", vecinos.size() > 0);

        // Verificar que todos los vecinos están en el diccionario
        for (String vecino : vecinos) {
            assertTrue("El vecino '" + vecino + "' debería estar en diccionario",
                    diccionario.contains(vecino));
        }
    }

    @Test
    public void testObtenerVecinosFiltraCorrectamente() throws Exception {
        // Preparar datos
        String[] palabras = { "hot" };
        Method cargarDiccionario = EscaleraPalabras.class.getDeclaredMethod("cargarDiccionario", String[].class);
        cargarDiccionario.setAccessible(true);
        cargarDiccionario.invoke(escalera, (Object) palabras);

        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        ArrayList<String> combinaciones = (ArrayList<String>) generarCombinaciones.invoke(escalera, "hit");

        Method obtenerVecinos = EscaleraPalabras.class.getDeclaredMethod("obtenerVecinos", String.class,
                List.class);
        obtenerVecinos.setAccessible(true);
        List<String> vecinos = (List<String>) obtenerVecinos.invoke(escalera, "hit", combinaciones);

        // Solo "hot" difiere en una letra de "hit", por lo que debería ser el único
        // vecino
        assertTrue("Debería contener a 'hot'", vecinos.contains("hot"));
    }

    @Test
    public void testObtenerVecinosSinVecinosEnDiccionario() throws Exception {
        // Preparar datos sin agregar vecinos al diccionario
        String[] palabras = { "xyz" };
        Method cargarDiccionario = EscaleraPalabras.class.getDeclaredMethod("cargarDiccionario", String[].class);
        cargarDiccionario.setAccessible(true);
        cargarDiccionario.invoke(escalera, (Object) palabras);

        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        ArrayList<String> combinaciones = (ArrayList<String>) generarCombinaciones.invoke(escalera, "abc");

        Method obtenerVecinos = EscaleraPalabras.class.getDeclaredMethod("obtenerVecinos", String.class,
                List.class);
        obtenerVecinos.setAccessible(true);
        List<String> vecinos = (List<String>) obtenerVecinos.invoke(escalera, "abc", combinaciones);

        // No debería haber vecinos porque ninguna combinación está en el diccionario
        assertEquals("No debería haber vecinos en el diccionario", 0, vecinos.size());
    }

    @Test
    public void testObtenerVecinosParaPalabraSinCombinacionesDevuelveVacio() throws Exception {
        Method obtenerVecinos = EscaleraPalabras.class.getDeclaredMethod("obtenerVecinos", String.class,
                List.class);
        obtenerVecinos.setAccessible(true);

        List<String> vecinos = (List<String>) obtenerVecinos.invoke(escalera, "unknown", new ArrayList<String>());
        assertTrue("Sin combinaciones debería devolver una lista vacía", vecinos.isEmpty());
    }

    @Test
    public void testObtenerVecinosMultiples() throws Exception {
        // Preparar datos
        String[] palabras = { "hot", "hit", "hat" };
        Method cargarDiccionario = EscaleraPalabras.class.getDeclaredMethod("cargarDiccionario", String[].class);
        cargarDiccionario.setAccessible(true);
        cargarDiccionario.invoke(escalera, (Object) palabras);

        Method generarCombinaciones = EscaleraPalabras.class.getDeclaredMethod("generarCombinaciones", String.class);
        generarCombinaciones.setAccessible(true);
        ArrayList<String> combinaciones = (ArrayList<String>) generarCombinaciones.invoke(escalera, "hit");

        Method obtenerVecinos = EscaleraPalabras.class.getDeclaredMethod("obtenerVecinos", String.class,
                List.class);
        obtenerVecinos.setAccessible(true);
        List<String> vecinos = (List<String>) obtenerVecinos.invoke(escalera, "hit", combinaciones);

        assertTrue("Debería contener a 'hot'", vecinos.contains("hot"));
        assertTrue("Debería contener a 'hat'", vecinos.contains("hat"));
        assertEquals("Debería tener 2 vecinos", 2, vecinos.size());
    }
}
