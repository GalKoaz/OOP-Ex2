package tests;

import api.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraphImplTest {

    private HashMap<Integer, NodeData> Vertices;
    private HashMap<Integer,HashMap<Integer, EdgeData>> Edges;
    private DirectedWeightedGraph randGraph;

    public DirectedWeightedGraphImplTest() {
        int randCapacityVerts = 3 + (int) (Math.random() * 30);
        this.Vertices = new HashMap<>(randCapacityVerts);
        int randCapacityEdges = 1 + (int) (Math.random() * (randCapacityVerts * (randCapacityVerts - 1)));
        this.Edges = new HashMap<>(randCapacityEdges);

        // Generates the edges and vertices by the randoms methods
        for (int i = 0; i < randCapacityVerts; i++) {
            Vertices.put(i, random_vertex_generator(i));
        }
        for (int i = 0; i < randCapacityEdges; i++) {
            EdgeData edge = random_edge_generator();
            int src = edge.getSrc();
            int dest = edge.getDest();
            //double w = edge.getWeight();
            if (!Edges.containsKey(src)) {
                Edges.put(src,new HashMap<>());
                Edges.get(src).put(dest, edge);
            } else {
                Edges.get(src).put(dest, edge);
            }
        }
        // Initializes the random graph
        randGraph = new DirectedWeightedGraphImpl(Vertices, Edges);
    }


    /**
     * This method creates a random node, when random node defined to have random values.
     *
     * @return the random node.
     */
    public NodeData random_vertex_generator(int key) {
        int tag = 1 + (int) (Math.random() * 10);
        double weight = Math.random() * 10;
        double x = Math.random() * 10, y = Math.random() * 10, z = Math.random() * 10;
        GeoLocation Node = new GeoLocationImpl(x, y, z);
        String info = "Key:" + key + "\n" + "Tag:" + tag + "\n" + "Weight" + weight + "\n" + "GeoLocation:" + Node;
        return new NodeDataImpl(key, tag, info, weight, Node);
    }

    public EdgeData random_edge_generator() {
        int[] srcDest = two_random_distinct_numbers(0, Vertices.size() - 1);
        int src = srcDest[0];
        int dest = srcDest[1];
        int tag = 1 + (int) (Math.random() * 10);
        double weight = Math.random() * 10;
        String info = "Src:" + src + "\n" + "Dest:" + dest + "\n" + "Tag" + tag + "\n" + "Weight:" + weight;
        return new EdgeDataImpl(src, dest, tag, weight, info);
    }

    public int[] two_random_distinct_numbers(int a, int b) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = a; i < b; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return new int[]{numbers.get(0), numbers.get(1)};
    }


    @Test
    void getNode() {
        int randKey = (int) (Math.random() * (randGraph.nodeSize() - 1));
        String info = randGraph.getNode(randKey).getInfo();
        assertEquals(randGraph.getNode(randKey).getInfo(), info);
    }

    @Test
    void getEdge() {
        int[] srcDest = two_random_distinct_numbers(0, randGraph.nodeSize() - 1);
        int src = srcDest[0];
        int dest = srcDest[1];
        randGraph.connect(src, dest, Math.random() * 10);
        String info = randGraph.getEdge(src, dest).getInfo();
        assertEquals(randGraph.getEdge(src, dest).getInfo(), info);
    }

    @Test
    void addNode() {
        int size = randGraph.nodeSize();
        NodeData add = random_vertex_generator(randGraph.nodeSize());
        randGraph.addNode(add);
        assertEquals(size + 1, randGraph.nodeSize());
    }

    @Test
    void connect() {
        int size = randGraph.edgeSize();
        EdgeData edge = random_edge_generator();
        if (randGraph.getEdge(edge.getSrc(), edge.getDest()) == null) {
            randGraph.connect(edge.getSrc(), edge.getDest(), edge.getWeight());
            assertEquals(size + 1, randGraph.edgeSize());
        } else {
            assertEquals(size, randGraph.edgeSize());
        }
    }
    @Test
    void removeNode() {
        int size = randGraph.nodeSize();
        int randKey = (int) (Math.random() * (randGraph.nodeSize()-1));
        randGraph.removeNode(randKey);
        assertEquals(size - 1, randGraph.nodeSize());
        assertNull(randGraph.getNode(randKey));
    }

    @Test
    void removeEdge() {
        int size = randGraph.edgeSize();
        int[] srcDest = two_random_distinct_numbers(0, randGraph.nodeSize() - 1);
        int src = srcDest[0];
        int dest = srcDest[1];
        if (randGraph.getEdge(src, dest) == null) {
            assertEquals(size, randGraph.edgeSize());
        }
        else{
            randGraph.removeEdge(src, dest);
            assertEquals(size - 1, randGraph.edgeSize());
            assertNull(randGraph.getEdge(src, dest));
        }
}

    @Test
    void nodeSize() {
        int size = randGraph.nodeSize();
        assertEquals(randGraph.nodeSize(),size);
    }

    @Test
    void edgeSize() {
        int size = randGraph.edgeSize();
        assertEquals(randGraph.edgeSize(),size);
    }

    @Test
    void getMC() {
        int count = randGraph.getMC();
        assertEquals(randGraph.getMC(),count);
    }

    @Test
    void deepCopy() {
        DirectedWeightedGraphImpl copy = new DirectedWeightedGraphImpl(new HashMap<>(),new HashMap<>());
        DirectedWeightedGraph g = copy.deepCopy(randGraph);
        assertNotEquals(g,randGraph);
    }
}