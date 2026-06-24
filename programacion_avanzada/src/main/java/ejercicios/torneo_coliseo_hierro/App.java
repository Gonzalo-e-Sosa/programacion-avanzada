package ejercicios.torneo_coliseo_hierro;

public class App {
    public static void main(String[] args) {
        Coliseo coliseo = new Coliseo();

        coliseo.agregarGuerrero(new Guerrero(7, "Ragnar"));
        coliseo.agregarGuerrero(new Guerrero(4, "Kael"));
        coliseo.agregarGuerrero(new Guerrero(9, "Darius"));
        coliseo.agregarGuerrero(new Guerrero(3, "Ordan"));

        Equipo e1 = new Equipo(new Guerrero(7, "Ragnar"), new Guerrero(4, "Kael"));
        e1.setNombre("Equipo 1");
        Equipo e2 = new Equipo(new Guerrero(9, "Darius"), new Guerrero(3, "Ordan"));
        e2.setNombre("Equipo 2");
        Equipo e3 = new Equipo(new Guerrero(5, "Thorin"), new Guerrero(6, "Gromm"));
        e3.setNombre("Equipo 3");
        coliseo.agregarEquipo(e1);
        coliseo.agregarEquipo(e2);
        coliseo.agregarEquipo(e3);

        Guerrero campeon = coliseo.comenzarTorneo();

        if (campeon != null) {
            System.out.println("El Guerrero Campeón es " + campeon.getNombre() + ", con fuerza " + campeon.getFuerza());
        } else {
            System.out.println("No hay campeón, la arena está manchada de sangre.");
        }

        Equipo equipoCampeon = coliseo.comenzarTorneoPorEquipos();
        if (equipoCampeon != null) {
            System.out.println(
                    "El Equipo Campeón es " + equipoCampeon);
        } else {
            System.out.println("No hay equipo campeón, la arena está manchada de sangre.");
        }
    }
}
