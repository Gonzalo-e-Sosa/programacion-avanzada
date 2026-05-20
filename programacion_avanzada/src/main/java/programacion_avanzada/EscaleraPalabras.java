package programacion_avanzada;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
Consigna
Una secuencia de transformación de la palabra beginWord a la palabra endWord usando un diccionario wordList es una secuencia de palabras beginWord -> s1 -> s2 -> ... -> sk tal que:

Cada par de palabras adyacentes difiere en exactamente una letra.
Cada si para 1 <= i <= k debe estar en wordList. La palabra beginWord no necesita estar en wordList.
sk == endWord

Dadas dos palabras, beginWord y endWord, y un diccionario wordList, retorná la cantidad de palabras en la secuencia de transformación más corta de beginWord a endWord, o 0 si dicha secuencia no existe.

Ejemplo 1
Entrada: beginWord = "hit", endWord = "cog"
         wordList = ["hot","dot","dog","lot","log","cog"]
Salida: 5
Explicación: Una secuencia de transformación válida más corta es "hit" -> "hot" -> "dot" -> "dog" -> "cog", que tiene 5 palabras de largo.

Ejemplo 2
Entrada: beginWord = "hit", endWord = "cog"
         wordList = ["hot","dot","dog","lot","log"]
Salida: 0
Explicación: La palabra "cog" no está en wordList, por lo tanto no existe ninguna secuencia de transformación válida.

Restricciones
- 1 <= beginWord.length <= 10
- endWord tiene la misma longitud que beginWord
- 1 <= wordList.length <= 5000
- Todas las palabras de wordList tienen la misma longitud que beginWord
- beginWord, endWord y cada palabra de wordList contienen solo letras minúsculas del alfabeto inglés
- beginWord es distinta de endWord
- Todas las palabras en wordList son únicas
*/
public class EscaleraPalabras {
    private Set<String> diccionario = new HashSet<>();

    public int solucion(String[] wordList, String beginWord, String endWord) {
        return bfs(wordList, beginWord, endWord).size();
    }

    private List<String> bfs(String[] wordList, String beginWord, String endWord) {
        Queue<String> cola = new LinkedList<>();
        Set<String> visitados = new HashSet<>();
        Map<String, String> padre = new HashMap<>();

        cola.add(beginWord);
        visitados.add(beginWord);

        cargarDiccionario(wordList);

        if (!diccionario.contains(endWord)) {
            return new ArrayList<>(); // Sin camino posible
        }

        while (!cola.isEmpty()) {
            String v = cola.poll();
            ArrayList<String> combinaciones = generarCombinaciones(v);

            // Se puede quitar este break?
            if (v.equals(endWord)) {
                break;
            }

            for (String vecino : obtenerVecinos(v, combinaciones)) {
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    cola.add(vecino);
                    padre.put(vecino, v);
                }
            }
        }

        diccionario.clear();

        return reconstruirCamino(padre, endWord);
    }

    private void cargarDiccionario(String[] palabras) {
        for (int i = 0; i < palabras.length; i++) {
            diccionario.add(palabras[i]);
        }
    }

    private ArrayList<String> generarCombinaciones(String palabra) {
        char[] alfabeto = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
                'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

        ArrayList<String> combinaciones = new ArrayList<>();
        char[] palabraArray = palabra.toCharArray();

        for (int i = 0; i < palabraArray.length; i++) {
            char letraOriginal = palabraArray[i];
            for (int j = 0; j < alfabeto.length; j++) {
                if (alfabeto[j] != letraOriginal) {
                    palabraArray[i] = alfabeto[j];
                    String combinacion = new String(palabraArray);
                    combinaciones.add(combinacion);
                }
            }
            palabraArray[i] = letraOriginal;
        }

        return combinaciones;
    }

    private List<String> obtenerVecinos(String palabra, List<String> posiblesVecinos) {
        List<String> vecinos = new ArrayList<>();

        // Antes de retornar vecinos, filtrarlos para solo obtener aquellos presentes en
        // mi diccionario
        for (String posibleVecino : posiblesVecinos) {
            if (diccionario.contains(posibleVecino)) {
                vecinos.add(posibleVecino);
            }
        }

        return vecinos;
    }

    private List<String> reconstruirCamino(Map<String, String> m, String n) {
        ArrayList<String> camino = new ArrayList<>();
        String sgt = n;

        while (sgt != null) {
            camino.add(sgt);
            sgt = m.get(sgt);
        }

        return camino;
    }
}
