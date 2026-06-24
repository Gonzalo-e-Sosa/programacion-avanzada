package ejercicios.torneo_coliseo_hierro;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.PriorityQueue;

public class GuerreroTest {

    @Test
    public void testPelearFuerzaMayor() {
        Guerrero g1 = new Guerrero(10, "A");
        Guerrero g2 = new Guerrero(3, "B");

        g1.pelear(g2);

        assertTrue(g1.estaVivo());
        assertEquals(7, g1.getFuerza());

        assertFalse(g2.estaVivo());
        assertEquals(0, g2.getFuerza());
    }

    @Test
    public void testPelearIgual() {
        Guerrero g1 = new Guerrero(5, "A");
        Guerrero g2 = new Guerrero(5, "B");

        g1.pelear(g2);

        assertFalse(g1.estaVivo());
        assertFalse(g2.estaVivo());
        assertEquals(0, g1.getFuerza());
        assertEquals(0, g2.getFuerza());
    }

    @Test
    public void testCompareToPriorityQueueOrder() {
        PriorityQueue<Guerrero> pq = new PriorityQueue<>();
        Guerrero low = new Guerrero(3, "Low");
        Guerrero high = new Guerrero(7, "High");
        pq.add(low);
        pq.add(high);

        Guerrero top = pq.poll();
        assertEquals("High", top.getNombre());
    }

}
