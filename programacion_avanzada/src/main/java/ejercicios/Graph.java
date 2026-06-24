package ejercicios;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class Graph {
    private Map<String, LinkedList<Edge>> nodes = new HashMap<>();

    public void addVertex(String v) {
        nodes.putIfAbsent(v, new LinkedList<>());
    }

    public void addEdge(String v1, String v2, int weight) {
        addVertex(v1);
        addVertex(v2);

        LinkedList<Edge> list1 = nodes.get(v1);
        LinkedList<Edge> list2 = nodes.get(v2);

        list1.add(new Edge(v1, v2, weight));
        list2.add(new Edge(v2, v1, weight));
    }

    public LinkedList<Edge> neighboring(String v) {
        return nodes.getOrDefault(v, new LinkedList<>());
    }

    public HashSet<String> getEdges() {
        return new HashSet<>(nodes.keySet());
    }

    // Pair removed; adjacency uses Edge objects now

    public static class Edge implements Comparable<Edge> {
        public final String from;
        public final String to;
        public final int weight;

        public Edge(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
