package programacion_avanzada;

public class SumaNaturales {
    static int iterativa(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n debe ser un número positivo");

        int suma = 0;
        for (int i = 1; i <= n; i++) {
            suma += i;
        }
        return suma;
    }

    static int recursiva(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n debe ser un número positivo");

        if (n == 1)
            return 1;

        return n + recursiva(n - 1);
    }
}
