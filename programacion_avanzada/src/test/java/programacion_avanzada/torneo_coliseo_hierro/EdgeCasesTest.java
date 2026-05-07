package programacion_avanzada.torneo_coliseo_hierro;

import org.junit.Test;
import static org.junit.Assert.*;

public class EdgeCasesTest {

    @Test
    public void testColiseoVacioDevuelveNull() {
        Coliseo c = new Coliseo();
        Guerrero ganador = c.comenzarTorneo();
        assertNull(ganador);
    }

    @Test
    public void testColiseoUnSoloGuerrero() {
        Coliseo c = new Coliseo();
        Guerrero g = new Guerrero(4, "Solo");
        c.agregarGuerrero(g);
        Guerrero ganador = c.comenzarTorneo();
        assertNotNull(ganador);
        assertEquals("Solo", ganador.getNombre());
        assertEquals(4, ganador.getFuerza());
    }

    @Test
    public void testTorneoPorEquiposEquipoSinGuerreros() {
        Coliseo c = new Coliseo();

        Equipo empty = new Equipo();
        empty.setNombre("Vacío");

        Equipo alive = new Equipo(new Guerrero(6, "Vivo"));
        alive.setNombre("Activo");

        // Añadimos ambos equipos al coliseo. Observamos comportamiento sin asumir
        // excepciones.
        c.agregarEquipo(empty);
        c.agregarEquipo(alive);

        Equipo ganador = c.comenzarTorneoPorEquipos();

        // Si el código maneja equipos sin guerreros, el ganador debe ser 'alive'.
        // Si hay un fallo interno, este test ayudará a detectarlo (fallará con
        // excepción).
        assertNotNull(ganador);
        assertEquals("Activo", ganador.toString().split(" - ")[0]);
    }

}
