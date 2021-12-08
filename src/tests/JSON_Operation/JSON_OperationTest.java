package tests.JSON_Operation;

import api.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class JSON_OperationTest {

    private final String path = "OOP-Ex2\\src\\tests\\JSON_Operation\\G4.json";
    private JSON_Operation json, json_2, json_3, json_writer;
    private HashMap<Integer, NodeData> Vertices;
    private HashMap<String, EdgeData> Edges;
    private DirectedWeightedGraph graph;

    public JSON_OperationTest() {
        this.json = new JSON_Operation("OOP-Ex2\\src\\tests\\JSON_Operation\\G1.json");
        this.json_2 = new JSON_Operation("OOP-Ex2\\src\\tests\\JSON_Operation\\G2.json");
        this.json_3 = new JSON_Operation("OOP-Ex2\\src\\tests\\JSON_Operation\\G3.json");
        this.json_writer = new JSON_Operation(path);
        graph = new DirectedWeightedGraphImpl(new HashMap<>(),new HashMap<>());
        createGraph();
    }

    /**
     * Creates a certain graph.
     */
    public void createGraph() {
        int CapacityVerts = 20, CapacityEdges = 25;
        this.Vertices = new HashMap<>(CapacityVerts);
        this.Edges = new HashMap<>(CapacityEdges);

        // Generates the edges and vertices by the randoms methods
        for (int i = 0; i < CapacityVerts; i++) {
            graph.addNode(new NodeDataImpl(i, i, "info: " + i, i * 0.89878, new GeoLocationImpl(i * 0.894, i * .2154, i * 1.454)));
        }
        for (int i = 0; i < CapacityEdges - 1; i++) {
            EdgeData edge = new EdgeDataImpl(i%CapacityVerts, (i + 1)%CapacityVerts, i, i * 1.156, "info: " + i);
            graph.connect(edge.getSrc(),edge.getDest(),edge.getWeight());
        }

    }

    /**
     * This method checking each one of JSON_Operation's methods, except from the actual writing.
     * The method starts reading each Boaz's jsons files, and initialize the graph.
     * Afterwards, it takes a specific edge and vertex from each, and checks if
     * it corresponds to the values of the original json file.
     * If so, then it means it read it properly.
     *
     * @throws IOException
     */
    @Test
    void JSON_Reader() throws IOException {
        // Checking reading of G1.json
        json.JSON_Reader();
        json.init_Graph();

        ArrayList<NodeData> vertices = json.getInitVertices();
        ArrayList<EdgeData> edges = json.getInitEdges();

        NodeData v = vertices.get(10);
        EdgeData e = edges.get(9);

        assertEquals(v.getLocation().toString(), "35.197400995964486,32.10510889579832,0.0");
        assertEquals(v.getKey(), 10);
        assertEquals(e.getWeight(), 1.8418222744214585);
        assertEquals(e.getSrc(), 4);
        assertEquals(e.getDest(), 3);
        assertEquals(vertices.size(), 17);
        assertEquals(edges.size(), 36);

        // Checking reading of G2.json
        json_2.JSON_Reader();
        json_2.init_Graph();

        vertices = json_2.getInitVertices();
        edges = json_2.getInitEdges();

        v = vertices.get(30);
        e = edges.get(79);

        assertEquals(v.getLocation().toString(), "35.19071455528652,32.106235628571426,0.0");
        assertEquals(v.getKey(), 30);
        assertEquals(e.getWeight(), 1.001001008098316);
        assertEquals(e.getSrc(), 30);
        assertEquals(e.getDest(), 13);
        assertEquals(vertices.size(), 31);
        assertEquals(edges.size(), 80);

        // Checking reading of G3.json
        json_3.JSON_Reader();
        json_3.init_Graph();

        vertices = json_3.getInitVertices();
        edges = json_3.getInitEdges();

        v = vertices.get(0);
        e = edges.get(1);

        assertEquals(v.getLocation().toString(), "35.212217299435025,32.106235628571426,0.0");
        assertEquals(v.getKey(), 0);
        assertEquals(e.getWeight(), 1.4195069847291193);
        assertEquals(e.getSrc(), 0);
        assertEquals(e.getDest(), 2);
        assertEquals(vertices.size(), 48);
        assertEquals(edges.size(), 166);
    }

    /**
     * The method Writes the graph, and tests if a new file were created.
     * If so, it checks also whether the created file isn't empty.
     * Afterwards, the method deletes the file, for a re-check.
     */
    @Test
    void JSON_Writer() {
        // Writing the graph, and testing if a new file were created.
        json_writer.JSON_Writer(graph);
        File testFile = new File(path);
        assertTrue(testFile.exists());
        assertNotNull(testFile.toString());
        testFile.deleteOnExit();
        if (testFile.delete()) {
            System.out.println("file deleted...");
        }
    }
}