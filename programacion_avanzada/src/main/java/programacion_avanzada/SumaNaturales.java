package programacion_avanzada;

public class SumaNaturales {
    static int iterativa(int n) {
        int suma = 0;
        for (int i = 1; i <= n; i++) {
            suma += i;
        }
        return suma;
    }

    static int recursiva(int n) {
        if (n <= 0)
            return 0;

        return n + recursiva(n - 1);
    }
}
