package ejercicios;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/*
Consigna
Un país tiene n ciudades. El gobierno ha decidido electrificar todas estas ciudades. 
Al principio, se construyen centrales eléctricas en k ciudades diferentes. 
Las demás ciudades deben estar conectadas con las centrales a través de líneas eléctricas. 
Para cualquier par de ciudades i, j es posible construir una línea eléctrica entre ellas en cij rublos.
El país está en crisis tras una guerra civil, por lo que el gobierno ha decidido construir sólo unas pocas líneas eléctricas. 
Por supuesto, desde cada ciudad debe haber un camino a lo largo de las líneas hasta alguna ciudad con una central eléctrica. 
Encuentra el mínimo coste posible para construir todas las líneas eléctricas necesarias.
 
Entrada
La primera línea contiene los enteros n y k (1 ≤ k ≤ n ≤ 100). 
La segunda línea contiene k enteros diferentes que son los números de las ciudades con centrales eléctricas. 
Las siguientes n líneas contienen una tabla n × n de enteros {cij} (0 ≤ cij ≤ 10^5). 
Se garantiza que cij = cji, cij > 0 para i ≠ j, cii = 0.
 
Salida
Una linea indicando el costo de electrificar las ciudades. 
A continuación, m lineas, donde cada una representa las lineas que componen el tendido resultante.

Ejemplo
Entrada
4 2
1 4
0 2 4 3
2 0 5 2
4 5 0 1
3 2 1 0

Salida
3
2 4
3 4
*/
public class PlanElectrificacion {
    public static void main(String[] args) {
        if (args.length > 0 && "demo".equalsIgnoreCase(args[0])) {
            runDemo();
            return;
        }

        try (Scanner sc = new Scanner(System.in)) {

            // Leer n y k
            if (!sc.hasNextInt())
                return;
            int n = sc.nextInt();
            int k = sc.nextInt();

            // Leer k centrales (1-based indices)
            List<String> centrals = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                int c = sc.nextInt();
                centrals.add(String.valueOf(c));
            }

            // Leer matriz de costes
            int[][] cost = new int[n + 1][n + 1]; // usar 1..n
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    cost[i][j] = sc.nextInt();
                }
            }

            Graph g = new Graph();

            // Añadir vértices y aristas completas (i<j)
            for (int i = 1; i <= n; i++) {
                g.addVertex(String.valueOf(i));
            }

            for (int i = 1; i <= n; i++) {
                for (int j = i + 1; j <= n; j++) {
                    g.addEdge(String.valueOf(i), String.valueOf(j), cost[i][j]);
                }
            }

            // Crear dummy conectado a todas las centrales con peso 0
            String dummy = "0";
            g.addVertex(dummy);
            for (String c : centrals) {
                g.addEdge(dummy, c, 0);
            }

            solution(g, dummy);
        }
    }

    private static void solution(Graph g, String dummy) {
        Prim prim = new Prim(); // Se usa Prim por grafo poco poblado
        LinkedList<Graph.Edge> mst = prim.getMST(g, dummy); // Obtener MST desde el dummy

        // Filtrar aristas que no se conecten al dummy
        List<int[]> resultEdges = filterEdges(mst, dummy);

        // Ordenar para salida estable: por menor primer vértice, luego segundo
        sortEdges(resultEdges);

        // Imprimir resultado
        printResult(resultEdges, prim.getTotalWeight());
    }

    private static List<int[]> filterEdges(LinkedList<Graph.Edge> mst, String dummy) {
        List<int[]> resultEdges = new ArrayList<>();
        for (Graph.Edge edge : mst) {
            if (!edge.from.equals(dummy) && !edge.to.equals(dummy)) {
                int a = Integer.parseInt(edge.from);
                int b = Integer.parseInt(edge.to);
                resultEdges.add(new int[] { a, b });
            }
        }
        return resultEdges;
    }

    private static void sortEdges(List<int[]> edges) {
        Collections.sort(edges, new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {
                if (x[0] != y[0])
                    return Integer.compare(x[0], y[0]);
                return Integer.compare(x[1], y[1]);
            }
        });
    }

    private static void printResult(List<int[]> edges, int totalCost) {
        System.out.println(totalCost);
        for (int[] edge : edges) {
            System.out.println(edge[0] + " " + edge[1]);
        }
    }

    private static void runDemo() {
        int n = 4;
        List<String> centrals = List.of("1", "4");
        int[][] cost = {
                { 0, 0, 0, 0, 0 },
                { 0, 0, 2, 4, 3 },
                { 0, 2, 0, 5, 2 },
                { 0, 4, 5, 0, 1 },
                { 0, 3, 2, 1, 0 }
        };

        Graph g = new Graph();

        // Añadir vértices y aristas completas (i<j)
        for (int i = 1; i <= n; i++) {
            g.addVertex(String.valueOf(i));
        }

        // Recorre solo la mitad superior de la matriz para evitar duplicados (i<j)
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                g.addEdge(String.valueOf(i), String.valueOf(j), cost[i][j]);
            }
        }

        String dummy = "0";
        g.addVertex(dummy);
        for (String c : centrals) {
            g.addEdge(dummy, c, 0);
        }

        solution(g, dummy);
    }
}
