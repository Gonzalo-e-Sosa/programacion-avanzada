package ejercicios;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Prim {
    private HashSet<String> visited;
    private PriorityQueue<Graph.Edge> edges;
    private LinkedList<Graph.Edge> MST;
    private int totalWeight;

    LinkedList<Graph.Edge> getMST(Graph g, String initial) {
        visited = new HashSet<>();
        edges = new PriorityQueue<>();
        MST = new LinkedList<>();
        totalWeight = 0;

        visited.add(initial);
        addEdges(g, initial);

        while (!edges.isEmpty() && visited.size() < g.getEdges().size()) {
            Graph.Edge minEdge = edges.poll();

            String v = minEdge.to;

            if (!visited.contains(v)) {
                visited.add(v);
                MST.add(minEdge);
                totalWeight += minEdge.weight;

                LinkedList<Graph.Edge> neighbors = g.neighboring(v);
                for (Graph.Edge neighbor : neighbors) {
                    if (!visited.contains(neighbor.to)) {
                        edges.add(new Graph.Edge(v, neighbor.to, neighbor.weight));
                    }
                }
            }
        }

        return MST;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    private void addEdges(Graph g, String initial) {
        LinkedList<Graph.Edge> neighbours = g.neighboring(initial);

        for (Graph.Edge n : neighbours) {
            edges.add(new Graph.Edge(initial, n.to, n.weight));
        }
    }
}
