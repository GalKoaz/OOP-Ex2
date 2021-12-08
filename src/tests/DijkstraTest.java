package tests;

import api.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DijkstraTest {
    private DirectedWeightedGraphImpl g, g2, g3, g4;
    private DirectedWeightedGraphAlgorithmsImpl a;
    private DijkstraAlgorithm d;
    public DijkstraTest(){
        g3 = new DirectedWeightedGraphImpl(new HashMap<>(), new HashMap<>());
        g3.addNode(new NodeDataImpl(0, 1, "info_a", 0, new GeoLocationImpl(1, 2, 3)));
        g3.addNode(new NodeDataImpl(1, 2, "info_b", 0, new GeoLocationImpl(4, 5, 6)));
        g3.addNode(new NodeDataImpl(2, 3, "info_c", 0, new GeoLocationImpl(7, 8, 9)));
        g3.connect(0,1,2.54546);
        g3.connect(1,2,0.54546);
        g3.connect(2,0,1.497345);
        //d =  new DijkstraAlgorithm(0,2,g3);
    }
    @Test
    void findMinDist() {

       // double ans = a.shortestPathDist(0,2);
        //double pp = d.findMinDist();
      //  assertEquals(ans,pp);
    }

    @Test
    void updateNeighbours() {

    }
}