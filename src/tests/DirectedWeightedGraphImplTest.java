package tests;

import api.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraphImplTest {

    @Test
    void getNode() {
    }

    @Test
    void getEdge() {
    }

    @Test
    void addNode() {
        init();
        int size = randGraph.nodeSize();
        NodeData add = random_vertex_generator(randGraph.nodeSize());
        randGraph.addNode(add);
        assertEquals(size+1,randGraph.nodeSize());
    }

    @Test
    void connect() {
/*        init();
        int src = (int) (Math.random() * randGraph.nodeSize());
        int dest = (int) (Math.random() * randGraph.nodeSize());

        while (randGraph.getEdge(src, dest) == null) {
            dest = (int) (Math.random() * randGraph.nodeSize());
        }
        double weight = randGraph.getEdge(src,dest).getWeight();
        //assertNotEquals(randGraph.connect(src,dest,weight),randGraph.connect(src,dest,weight));*/
    }
    @Test
    void removeNode() {
        init();
        int size = randGraph.nodeSize();
        int randKey = (int) (Math.random() * randGraph.nodeSize());
        randGraph.removeNode(randKey);
        assertEquals(size-1,randGraph.nodeSize());
        assertNull(randGraph.getNode(randKey));
    }

    @Test
    void removeEdge() {
        init();
        int size = randGraph.edgeSize();
        int src = (int) (Math.random() * randGraph.nodeSize());
        int dest = (int) (Math.random() * randGraph.nodeSize());
        while (randGraph.getEdge(src, dest) == null) {
            dest = (int) (Math.random() * randGraph.nodeSize());
        }
        randGraph.removeEdge(src,dest);
        assertEquals(size-1,randGraph.edgeSize());
        assertNull(randGraph.getEdge(src,dest));
    }

    @Test
    void nodeSize() {
        init();
        int size = randGraph.nodeSize();
        assertEquals(randGraph.nodeSize(),size);
    }

    @Test
    void edgeSize() {
        init();
        int size = randGraph.edgeSize();
        assertEquals(randGraph.edgeSize(),size);
    }

    @Test
    void getMC() {
        init();
        int count = randGraph.getMC();
        assertEquals(randGraph.getMC(),count);
    }

    @Test
    void deepCopy() {
        init();
        DirectedWeightedGraphImpl copy = new DirectedWeightedGraphImpl(new HashMap<>(),new HashMap<>());
        DirectedWeightedGraph g = copy.deepCopy(randGraph);
        assertNotEquals(g,randGraph);
    }
}