package pa.dados;

import java.security.InvalidParameterException;

/**
 * Clase utilitaria para calcular combinaciones de resultados con dados de seis
 * caras (D6).
 * 
 * @author Gonzalo E. Sosa
 * @version 1.0
 */
public class Dados {

    /**
     * Calcula de cuántas maneras distintas se puede obtener una suma objetivo
     * lanzando una cantidad determinada de dados de seis caras (D6).
     * 
     * @param cantidadDados número de dados a lanzar
     * @param sumaObjetivo  suma total que se desea alcanzar
     * @return cantidad de combinaciones diferentes que suman exactamente el valor
     *         objetivo, o 0 si la suma es imposible de alcanzar
     */
    public static int calcularCombinaciones(int cantidadDados, int sumaObjetivo) {
        if (cantidadDados < 0 || sumaObjetivo < 0) {
            throw new InvalidParameterException("No se permiten valores negativos.");
        }

        int[][] dp = new int[cantidadDados + 1][sumaObjetivo + 1];

        // SOLO una forma de sumar 0 con 0 dados
        dp[0][0] = 1;

        for (int d = 1; d <= cantidadDados; d++) {
            for (int s = 0; s <= sumaObjetivo; s++) {
                // Probar con cada cara del dado (1-6)
                for (int cara = 1; cara <= 6; cara++) {
                    // Si la suma actual menos la cara es >= 0, entonces usar esa cara
                    if (s - cara >= 0) {
                        dp[d][s] += dp[d - 1][s - cara];
                    }
                }
            }
        }
        return dp[cantidadDados][sumaObjetivo];
    }

}