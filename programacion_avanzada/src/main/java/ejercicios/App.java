package ejercicios;

/**
 */
public class App {
    public static void main(String[] args) {
        DefensaMuroRose dmr = new DefensaMuroRose();

        /*
         * 1 | 2 | 3 |
         * | 2 | 7 | 5 |
         * | 3 | 14 | 4 |
         * | 4 | 17 | 6 |
         * | 5 | 20 | 2 |
         * | 6 | 5 | 1 |
         */

        dmr.agregarCannon(dmr.new Cannon(1, 2, 3));
        dmr.agregarCannon(dmr.new Cannon(2, 7, 5));
        dmr.agregarCannon(dmr.new Cannon(3, 14, 4));
        dmr.agregarCannon(dmr.new Cannon(4, 17, 6));
        dmr.agregarCannon(dmr.new Cannon(5, 20, 2));
        dmr.agregarCannon(dmr.new Cannon(6, 5, 1));

        dmr.defenderMuro(0, 20);
    }
}
