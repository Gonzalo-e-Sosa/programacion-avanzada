package ejercicios.torneo_coliseo_hierro;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColiseoTest {

    @Test
    public void testTorneoIndividualEjemplo() {
        Coliseo c = new Coliseo();
        c.agregarGuerrero(new Guerrero(7, "Ragnar"));
        c.agregarGuerrero(new Guerrero(4, "Kael"));
        c.agregarGuerrero(new Guerrero(9, "Darius"));
        c.agregarGuerrero(new Guerrero(3, "Ordan"));

        Guerrero ganador = c.comenzarTorneo();
        assertNotNull(ganador);
        assertEquals("Darius", ganador.getNombre());
        assertEquals(1, ganador.getFuerza());
    }

    @Test
    public void testTorneoIndividualTodosMueren() {
        Coliseo c = new Coliseo();
        c.agregarGuerrero(new Guerrero(5, "G1"));
        c.agregarGuerrero(new Guerrero(5, "G2"));

        Guerrero ganador = c.comenzarTorneo();
        assertNull(ganador);
    }

    @Test
    public void testTorneoPorEquiposSimple() {
        Coliseo c = new Coliseo();

        Equipo e1 = new Equipo(new Guerrero(5, "A1"));
        e1.setNombre("EquipoA");

        Equipo e2 = new Equipo(new Guerrero(3, "B1"));
        e2.setNombre("EquipoB");

        c.agregarEquipo(e1);
        c.agregarEquipo(e2);

        Equipo ganador = c.comenzarTorneoPorEquipos();
        assertNotNull(ganador);
        assertEquals(2, ganador.getGuerreroMasFuerte().getFuerza());
    }

}
