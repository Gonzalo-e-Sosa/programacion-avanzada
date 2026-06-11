package pa.matrix;

/**
 * Neo debe localizar al Agente Smith en los registros de la Matrix. Los datos
 * están organizados en una matriz de enteros sin elementos repetidos, donde
 * cada fila está ordenada de menor a mayor, y cada columna también está
 * ordenada de menor a mayor.
 * 
 * Esta clase implementa un algoritmo de división y conquista recursivo para
 * buscar un valor objetivo en una matriz N×N ordenada por filas y columnas en
 * tiempo menor a O(n^2).
 * 
 * @author COMPLETAR
 * @version 1.0
 */
public class Neo {

    /**
     * Busca al Agente Smith en la matriz de datos de Matrix usando divide y
     * vencerás recursivo. La matriz debe estar ordenada: cada fila creciente y cada
     * columna creciente.
     * 
     * @param matrizDatos matriz ordenada que representa los registros de Matrix
     * @param codigoSmith código único del Agente Smith a buscar
     * @return array de 2 posiciones [fila, columna] donde está Smith, o [-1, -1] si
     *         no se encuentra
     */
    public int[] buscarAgenteSmith(int[][] matrizDatos, int codigoSmith) {
        for (int i = 0; i < matrizDatos.length; i++) {
            int pos = busquedaBinaria(matrizDatos[i], 0, matrizDatos[i].length - 1, codigoSmith);

            if (pos >= 0) {
                return new int[] { i, pos };
            }
        }

        return new int[] { -1, -1 };
    }

    // retorna posicion del array en la que se encontro el objetivo
    // retorna -1 si no existe el objetivo en el array, es decir, si no hay posicion
    // que lo contenga
    private int busquedaBinaria(int[] arrayDatos, int inicio, int fin, int objetivo) {
        if (inicio > fin)
            return -1;

        int medio = (inicio + fin) / 2;

        if (arrayDatos[medio] == objetivo)
            return medio;

        if (arrayDatos[medio] < objetivo)
            return busquedaBinaria(arrayDatos, medio + 1, fin, objetivo);

        return busquedaBinaria(arrayDatos, inicio, medio - 1, objetivo);
    }
}