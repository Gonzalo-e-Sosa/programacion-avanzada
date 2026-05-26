package programacion_avanzada;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

public class PrimTest {

    @Test
    public void mstSimpleGraphHasCorrectWeightAndVertices() {
        Graph g = new Graph();

        g.addEdge("A", "B", 1);
        g.addEdge("A", "C", 3);
        g.addEdge("B", "C", 1);
        g.addEdge("B", "D", 4);
        g.addEdge("C", "D", 1);

        Prim prim = new Prim();
        java.util.LinkedList<Graph.Edge> mst = prim.getMST(g, "A");

        // MST for 4 vertices should have 3 edges
        assertEquals(3, mst.size());

        // weight should be 3 (A-B 1, B-C 1, C-D 1)
        assertEquals(3, prim.getTotalWeight());

        HashSet<String> expected = new HashSet<>();
        expected.add("A");
        expected.add("B");
        expected.add("C");
        expected.add("D");

        HashSet<String> got = new HashSet<>();
        got.add("A");
        for (Graph.Edge e : mst) {
            got.add(e.from);
            got.add(e.to);
        }

        assertTrue(expected.containsAll(got));
    }

    @Test
    public void singleVertexGraph() {
        Graph g = new Graph();
        g.addVertex("X");

        Prim prim = new Prim();
        java.util.LinkedList<Graph.Edge> mst = prim.getMST(g, "X");

        // no edges in MST when single vertex
        assertEquals(0, mst.size());
        assertEquals(0, prim.getTotalWeight());
    }

    @Test
    public void disconnectedGraphVisitsOnlyComponent() {
        Graph g = new Graph();

        // component 1
        g.addEdge("A", "B", 2);
        g.addEdge("B", "C", 2);

        // component 2
        g.addEdge("X", "Y", 5);

        Prim prim = new Prim();
        java.util.LinkedList<Graph.Edge> mst = prim.getMST(g, "A");

        // component A-B-C has 3 vertices -> 2 edges in MST
        assertEquals(2, mst.size());
        assertEquals(4, prim.getTotalWeight());
    }
}
