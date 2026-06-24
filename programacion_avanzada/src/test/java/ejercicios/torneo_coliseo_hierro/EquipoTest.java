package ejercicios.torneo_coliseo_hierro;

import org.junit.Test;

import static org.junit.Assert.*;

public class EquipoTest {

    @Test
    public void testGetGuerreroMasFuerte() {
        Guerrero g1 = new Guerrero(3, "A");
        Guerrero g2 = new Guerrero(8, "B");
        Equipo e = new Equipo(g1, g2);

        Guerrero mas = e.getGuerreroMasFuerte();
        assertNotNull(mas);
        assertEquals("B", mas.getNombre());
    }

    @Test
    public void testEstaVivoYTodosMueren() {
        Guerrero g1 = new Guerrero(3, "A");
        Guerrero g2 = new Guerrero(2, "B");
        Equipo e = new Equipo(g1, g2);

        assertTrue(e.estaVivo());

        g1.morir();
        g2.morir();

        assertFalse(e.estaVivo());
    }

    @Test
    public void testCompareTo() {
        Equipo e1 = new Equipo(new Guerrero(5, "G1"));
        Equipo e2 = new Equipo(new Guerrero(7, "G2"));

        assertTrue(e1.compareTo(e2) > 0);
        assertTrue(e2.compareTo(e1) < 0);
    }

}
