package programacion_avanzada;
/*
Problema
Una programadora de computadoras vive en una calle con casas numeradas consecutivamente (desde 1) por un lado de la calle. Cada noche ella sale a pasear a su perro dejando su casa, yendo al azar a la izquierda o a la derecha, camina hasta el final de la calle y vuelve.
Una noche suma los números de las casas que pasan (excluyendo la suya). La siguiente vez que camina, comienza por el otro lado repitiendo la suma y encuentra, para su asombro, que las dos sumas son iguales. Aunque esto se determina en parte por su número de casa y en parte por el número de casas en la calle, ella sin embargo siente que esta es una propiedad deseable para su casa y decide que todas sus casas subsecuentes tendrán esa propiedad.  
Escribe un programa para encontrar los pares de números que satisfagan esta condición. Para comenzar su lista los dos primeros pares son: (número de casa, último número):
6, 8
35, 49
204, 288
1189, 1681
6930, 9800
40391, 57121
235416, 332928
1372105, 1940449
7997214, 11309768
46611179, 65918161
 */

public class StreetNumbers {
    // O(N^2)
    public static int cuadratica(int n) {
        if (n < 3)
            return -1;
        for (int i = 2; i <= n; i++) {
            long sumaIzq = 0;
            for (int j = 1; j < i; j++)
                sumaIzq += j;
            long sumaDer = 0;
            for (int k = i + 1; k <= n; k++)
                sumaDer += k;
            if (sumaDer == sumaIzq)
                return i;
        }
        return -1;
    }

    // O(N)
    public static int lineal(int n) {
        if (n < 3)
            return -1;
        for (int i = 1; i <= n; i++) {
            long sumaIzq = (long) i * (i - 1) / 2;
            long sumaDer = ((long) n * (n + 1) / 2) - ((long) i * (i + 1) / 2);
            if (sumaDer == sumaIzq)
                return i;
        }
        return -1;
    }

    // O(1)
    public static int constante(int n) {
        double r = Math.sqrt(((long) n * (n + 1)) / 2.0);
        return r == (int) r ? (int) r : -1;
    }
}
