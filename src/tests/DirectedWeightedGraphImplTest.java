package tests;

import api.*;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraphImplTest {
    private HashMap<Integer, NodeData> Vertices;
    private HashMap<String, EdgeData> Edges;
    private DirectedWeightedGraph randGraph;

    @Before
    public void init() {
        int randCapacityVerts = 1 + (int) (Math.random() * 30);
        this.Vertices = new HashMap<>(randCapacityVerts);
        int randCapacityEdges = 1 + (int) (Math.random() * (randCapacityVerts * (randCapacityVerts - 1)));
        this.Edges = new HashMap<>(randCapacityEdges);

        // Generates the edges and vertices by the randoms methods
        for (int i = 0; i < randCapacityVerts; i++) {
            Vertices.put(i, random_vertex_generator(i));
        }
        for (int i = 0; i < randCapacityEdges; i++) {
            EdgeData edge = random_edge_generator();
            Edges.put("" + edge.getSrc() + "-" + edge.getDest(), edge);
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
        double x = Math.random() * 40, y = Math.random() * 40, z = Math.random() * 40;
        GeoLocation Node = new GeoLocationImpl(x, y, z);
        String info = "Key:" + key + "\n" + "Tag:" + tag + "\n" + "Weight" + weight + "\n" + "GeoLocation:" + Node;
        return new NodeDataImpl(key, tag, info, weight, Node);
    }

    public EdgeData random_edge_generator() {
        int src = (int) (Math.random() * this.Vertices.size());
        int dest = (int) (Math.random() * this.Vertices.size());
        while (src == dest) {
            dest = (int) (Math.random() * this.Vertices.size());
        }
        int tag = 1 + (int) (Math.random() * 10);
        double weight = Math.random() * 20;
        String info = "Src:" + src + "\n" + "Dest:" + dest + "\n" + "Tag" + tag + "\n" + "Weight:" + weight;
        return new EdgeDataImpl(src, dest, tag, weight, info);
    }

    @Test
    void getNode() {
        init();
        int randKey = (int) (Math.random() * randGraph.nodeSize());
        System.out.println(randKey);
        String info = randGraph.getNode(randKey).getInfo();
        assertEquals(randGraph.getNode(randKey).getInfo(), info);
    }

    @Test
    void getEdge() {
        init();
        int src = (int) (Math.random() * randGraph.nodeSize());
        int dest = (int) (Math.random() * randGraph.nodeSize());
        System.out.println(src + "," + dest);
        while (randGraph.getEdge(src, dest) == null) {
            dest = (int) (Math.random() * randGraph.nodeSize());
        }
        String info = randGraph.getEdge(src, dest).getInfo();
        assertEquals(randGraph.getEdge(src, dest).getInfo(), info);
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