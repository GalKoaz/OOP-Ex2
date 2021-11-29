package tests;

import api.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This class checks if the DFS operates properly, as well as the supportive methods
 * for the isConnect - main function.
 */
class DFSTest {
    private DirectedWeightedGraphImpl g,copy, g2, g3, g4;
    private DirectedWeightedGraphAlgorithmsImpl a;
    private DFS dfs, dfs_2, dfs_3, dfs_4;


    public DFSTest(){
        a = new DirectedWeightedGraphAlgorithmsImpl();
        a.load("OOP-Ex2\\src\\tests\\jsons\\G2.json");
        g = new DirectedWeightedGraphImpl(a.getJson());
        copy = (DirectedWeightedGraphImpl) a.copy();
        dfs = new DFS(g);

        // Building of a new graph for tests
        g2 = new DirectedWeightedGraphImpl(new HashMap<>(), new HashMap<>());
        g2.addNode(new NodeDataImpl(0, 1, "info_a", 0, new GeoLocationImpl(1, 2, 3)));
        g2.addNode(new NodeDataImpl(1, 2, "info_b", 0, new GeoLocationImpl(4, 5, 6)));
        g2.connect(0,1,2.54546);
        dfs_2 = new DFS(g2);

        // Building of a new graph for tests
        g3 = new DirectedWeightedGraphImpl(new HashMap<>(), new HashMap<>());
        g3.addNode(new NodeDataImpl(0, 1, "info_a", 0, new GeoLocationImpl(1, 2, 3)));
        g3.addNode(new NodeDataImpl(1, 2, "info_b", 0, new GeoLocationImpl(4, 5, 6)));
        g3.addNode(new NodeDataImpl(2, 3, "info_c", 0, new GeoLocationImpl(7, 8, 9)));
        g3.connect(0,1,2.54546);
        g3.connect(1,2,0.54546);
        g3.connect(2,0,1.497345);
        dfs_3 = new DFS(g3);

        // Building of a new graph for tests
        g4 = new DirectedWeightedGraphImpl(new HashMap<>(), new HashMap<>());
        g4.addNode(new NodeDataImpl(0, 1, "info_a", 0, new GeoLocationImpl(1, 2, 3)));
        g4.addNode(new NodeDataImpl(1, 2, "info_b", 0, new GeoLocationImpl(4, 5, 6)));
        g4.addNode(new NodeDataImpl(2, 3, "info_c", 0, new GeoLocationImpl(7, 8, 9)));
        g4.connect(0,1,2.54546);
        g4.connect(1,2,0.54546);
        dfs_4 = new DFS(g4);
    }

    @Test
    void isConnect() {
        assertTrue(dfs.isConnected());
        assertFalse(dfs_2.isConnected());
        assertTrue(dfs_3.isConnected());
        assertFalse(dfs_4.isConnected());
    }

    @Test
    void turnEdgesDirection() {
        // for the graph reads from json
        DirectedWeightedGraph r = dfs.turnEdgesDirection(g);
        Iterator<EdgeData> e =  copy.edgeIter();
        while (e.hasNext()){
            EdgeData curr = e.next();
            assertNotNull(r.getEdge(curr.getDest(),curr.getSrc()));
        }
        //new test:
        g2 = (DirectedWeightedGraphImpl) dfs_2.turnEdgesDirection(g2);
        assertNotNull(g2.getEdge(1,0));
        assertNull(g2.getEdge(0,1));
        //new test:
        g3 = (DirectedWeightedGraphImpl) dfs_3.turnEdgesDirection(g3);
        assertNotNull(g3.getEdge(1,0));
        assertNull(g3.getEdge(0,1));
        assertNotNull(g3.getEdge(2,1));
        assertNull(g3.getEdge(1,2));
        assertNotNull(g3.getEdge(0,2));
        assertNull(g3.getEdge(2,0));
        //new test:
        g4 = (DirectedWeightedGraphImpl) dfs_4.turnEdgesDirection(g4);
        assertNotNull(g3.getEdge(1,0));
        assertNull(g3.getEdge(0,1));
        assertNotNull(g3.getEdge(2,1));
        assertNull(g3.getEdge(1,2));
    }
}