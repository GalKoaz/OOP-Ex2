package tests;

import api.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class DirectedWeightedGraphAlgorithmsImplTest {
    private final double EPSILON = .0001;
    private DirectedWeightedGraphAlgorithmsImpl a,y,x;
    private DirectedWeightedGraph graph,graph2,graph3;

    public DirectedWeightedGraphAlgorithmsImplTest(){

        this.y = new DirectedWeightedGraphAlgorithmsImpl();
        y.load("OOP-Ex2\\src\\tests\\jsons\\G1.json");
        this.graph2 = new DirectedWeightedGraphImpl(y.getJson());

        this.a = new DirectedWeightedGraphAlgorithmsImpl();
        a.load("OOP-Ex2\\src\\tests\\jsons\\G2.json");
        this.graph = new DirectedWeightedGraphImpl(a.getJson());

        this.x = new DirectedWeightedGraphAlgorithmsImpl();
        x.load("OOP-Ex2\\src\\tests\\jsons\\G3.json");
        this.graph3 = new DirectedWeightedGraphImpl(x.getJson());
    }

    @Test
    void init() {
        a.init(graph);
        assertNotNull(a.getGraph().edgeIter());
        assertNotNull(a.getGraph().nodeIter());
        assertTrue(a.getGraph().nodeSize()>0);
        assertTrue(a.getGraph().edgeSize()>0);

        y.init(graph2);
        assertNotNull(y.getGraph().edgeIter());
        assertNotNull(y.getGraph().nodeIter());
        assertTrue(y.getGraph().nodeSize()>0);
        assertTrue(y.getGraph().edgeSize()>0);

        x.init(graph3);
        assertNotNull(x.getGraph().edgeIter());
        assertNotNull(x.getGraph().nodeIter());
        assertTrue(x.getGraph().nodeSize()>0);
        assertTrue(x.getGraph().edgeSize()>0);
    }


    @Test
    void copy() {
        DirectedWeightedGraph copy = a.copy();
        assertNotEquals(copy,graph);

        DirectedWeightedGraph copy1 = x.copy();
        assertNotEquals(copy1,graph2);

        DirectedWeightedGraph copy2 = y.copy();
        assertNotEquals(copy2,graph3);
    }


    @Test
    void shortestPathDist() {
        double shortest_1 = a.shortestPathDist(10,30);
        assertEquals(shortest_1,3.7545, EPSILON);

        double shortest_2 = x.shortestPathDist(0,1);
        assertEquals(shortest_2,1.0286, EPSILON);

        double shortest_3 = y.shortestPathDist(11,13);
        assertEquals(shortest_3,3.4800, EPSILON);
    }

    @Test
    void shortestPath() {
        List<NodeData> path = a.shortestPath(10,30);
        assertEquals(graph.getNode(10).getInfo(),path.get(0).getInfo());
        assertEquals(graph.getNode(11).getInfo(),path.get(1).getInfo());
        assertEquals(graph.getNode(20).getInfo(),path.get(2).getInfo());
        assertEquals(graph.getNode(30).getInfo(),path.get(3).getInfo());

        List<NodeData> path1 = y.shortestPath(0,16);
        assertEquals(graph2.getNode(0).getInfo(),path1.get(0).getInfo());
        assertEquals(graph2.getNode(16).getInfo(),path1.get(1).getInfo());

        List<NodeData> path2 = x.shortestPath(0,31);
        assertEquals(graph3.getNode(0).getInfo(),path2.get(0).getInfo());
        assertEquals(graph3.getNode(2).getInfo(),path2.get(1).getInfo());
        assertEquals(graph3.getNode(3).getInfo(),path2.get(2).getInfo());
        assertEquals(graph3.getNode(13).getInfo(),path2.get(3).getInfo());
        assertEquals(graph3.getNode(14).getInfo(),path2.get(4).getInfo());
        assertEquals(graph3.getNode(29).getInfo(),path2.get(5).getInfo());
        assertEquals(graph3.getNode(30).getInfo(),path2.get(6).getInfo());
        assertEquals(graph3.getNode(31).getInfo(),path2.get(7).getInfo());
    }

    @Test
    void center() {
        NodeData center = a.center();
        double min = Double.MAX_VALUE; int index = 0;
        for (int i = 0; i < graph.nodeSize(); i++) {
            if (a.e(i)<min){min = a.e(i); index = i;}
        }

        assertEquals(center.getInfo(),graph.getNode(index).getInfo());
    }

    @Test
    void e() {
        assertEquals(a.e(0), 7.8199, EPSILON);
        assertEquals(a.e(1), 8.8542, EPSILON);
        assertEquals(a.e(2), 10.4327, EPSILON);
        assertEquals(a.e(3), 11.8733, EPSILON);
        assertEquals(a.e(4), 12.5607, EPSILON);
        assertEquals(a.e(5), 12.2560, EPSILON);

    }

    @Test
    void tsp() {
         ArrayList<NodeData> tsp = new ArrayList<>();
        tsp.add(graph.getNode(3));
        tsp.add(graph.getNode(8));
        tsp.add(graph.getNode(10));
        tsp.add(graph.getNode(11));
        ArrayList<NodeData> tsp1 = (ArrayList<NodeData>) a.tsp(tsp);
        assertTrue(tsp1.size() > 0);
    }

    @Test
    void minCycleFromCity() {
        ArrayList<NodeData> City;
        ArrayList<NodeData> minCity;

        City = new ArrayList<>();
        City.add(graph2.getNode(2));
        City.add(graph2.getNode(3));
        City.add(graph2.getNode(6));
        City.add(graph2.getNode(4));
        City.add(graph2.getNode(5));

        minCity = (ArrayList<NodeData>) y.tsp(City);
        assertTrue(minCity.size()>0);
    }

    @Test
    void pathCost() {
        ArrayList<NodeData> cost = new ArrayList<>();
        cost.add(graph.getNode(0));
        cost.add(graph.getNode(1));
        assertEquals(a.pathCost(cost),1.2320, EPSILON);

        ArrayList<NodeData> cost1 = new ArrayList<>();
        cost1.add(graph.getNode(10));
        cost1.add(graph.getNode(11));
        assertEquals(a.pathCost(cost1), 1.4962, EPSILON);

        ArrayList<NodeData> cost2 = new ArrayList<>();
        cost2.add(graph.getNode(10));
        cost2.add(graph.getNode(11));
        cost2.add(graph.getNode(20));
        cost2.add(graph.getNode(30));
        assertEquals(a.pathCost(cost2),3.7545, EPSILON);

        ArrayList<NodeData> cost3 = new ArrayList<>();
        cost3.add(graph.getNode(3));
        cost3.add(graph.getNode(4));
        cost3.add(graph.getNode(5));
        cost3.add(graph.getNode(28));
        cost3.add(graph.getNode(4));
        assertEquals(a.pathCost(cost3),4.7488, EPSILON);

        //--------------------------------------------- G1

        ArrayList<NodeData> cost4 = new ArrayList<>();
        cost4.add(graph2.getNode(0));
        cost4.add(graph2.getNode(16));
        assertEquals(y.pathCost(cost4),1.3118, EPSILON);

        ArrayList<NodeData> cost5 = new ArrayList<>();
        cost5.add(graph2.getNode(6));
        cost5.add(graph2.getNode(2));
        assertEquals(y.pathCost(cost5), 1.8474, EPSILON);

        ArrayList<NodeData> cost6 = new ArrayList<>();
        cost6.add(graph2.getNode(7));
        cost6.add(graph2.getNode(8));
        cost6.add(graph2.getNode(9));
        cost6.add(graph2.getNode(10));
        assertEquals(y.pathCost(cost6),4.1907, EPSILON);

        ArrayList<NodeData> cost7 = new ArrayList<>();
        cost7.add(graph.getNode(14));
        cost7.add(graph.getNode(15));
        cost7.add(graph.getNode(16));
        cost7.add(graph.getNode(0));
        cost7.add(graph.getNode(16));
        assertEquals(y.pathCost(cost7),5.74523, EPSILON);

        //--------------------------------------------- G3


        ArrayList<NodeData> cost8 = new ArrayList<>();
        cost8.add(graph3.getNode(2));
        cost8.add(graph3.getNode(9));
        assertEquals(x.pathCost(cost8),0.94849, EPSILON);

        ArrayList<NodeData> cost9 = new ArrayList<>();
        cost9.add(graph3.getNode(4));
        cost9.add(graph3.getNode(13));
        assertEquals(x.pathCost(cost9), 0.8688, EPSILON);

        ArrayList<NodeData> cost10 = new ArrayList<>();
        cost10.add(graph3.getNode(5));
        cost10.add(graph3.getNode(6));
        cost10.add(graph3.getNode(7));
        cost10.add(graph3.getNode(10));
        assertEquals(x.pathCost(cost10),4.3713, EPSILON);

        ArrayList<NodeData> cost11 = new ArrayList<>();
        cost11.add(graph3.getNode(9));
        cost11.add(graph3.getNode(0));
        cost11.add(graph3.getNode(8));
        cost11.add(graph3.getNode(10));
        cost11.add(graph3.getNode(7));
        assertEquals(x.pathCost(cost11),4.9801, EPSILON);

    }
}