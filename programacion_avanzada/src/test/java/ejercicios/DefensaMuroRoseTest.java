package ejercicios;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class DefensaMuroRoseTest {

    @Test
    public void testExampleFromSpecification() {
        DefensaMuroRose dm = new DefensaMuroRose();

        // Cañones del ejemplo
        dm.agregarCannon(dm.new Cannon(1, 2, 3));
        dm.agregarCannon(dm.new Cannon(2, 7, 5));
        dm.agregarCannon(dm.new Cannon(3, 14, 4));
        dm.agregarCannon(dm.new Cannon(4, 17, 6));
        dm.agregarCannon(dm.new Cannon(5, 20, 2));
        dm.agregarCannon(dm.new Cannon(6, 5, 1));

        // Capturar salida
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));

        dm.defenderMuro(0, 20);

        System.out.flush();
        System.setOut(originalOut);

        String output = out.toString().trim();
        String[] lines = output.split("\\r?\\n");

        // Esperamos exactamente 3 cañones: C1, C2, C4 (en ese orden)
        assertEquals(3, lines.length);
        assertEquals("C1", lines[0].trim());
        assertEquals("C2", lines[1].trim());
        assertEquals("C4", lines[2].trim());
    }

    @Test
    public void testSingleCannonCoversRange() {
        DefensaMuroRose dm = new DefensaMuroRose();
        dm.agregarCannon(dm.new Cannon(1, 10, 10)); // cubre [0,20]

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));

        dm.defenderMuro(0, 20);

        System.out.flush();
        System.setOut(originalOut);

        String output = out.toString().trim();
        String[] lines = output.split("\\r?\\n");

        assertEquals(1, lines.length);
        assertEquals("C1", lines[0].trim());
    }

    @Test(expected = Error.class)
    public void testUncoverableRangeThrows() {
        DefensaMuroRose dm = new DefensaMuroRose();
        // Cañones que no alcanzan a cubrir el tramo [0, 20]
        dm.agregarCannon(dm.new Cannon(1, 1, 1));
        dm.agregarCannon(dm.new Cannon(2, 5, 1));
        dm.agregarCannon(dm.new Cannon(3, 9, 1));

        dm.defenderMuro(0, 20);
    }
}
