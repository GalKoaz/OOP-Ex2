package tests.JSON_Operation;

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
            Vertices.put(i, new NodeDataImpl(i, i, "info: " + i, i * 0.89878, new GeoLocationImpl(i * 0.894, i * .2154, i * 1.454)));
        }
        for (int i = 0; i < CapacityEdges - 1; i++) {
            EdgeData edge = new EdgeDataImpl(i, i + 1, i, i * 1.156, "info: " + i);
            Edges.put("" + edge.getSrc() + "-" + edge.getDest(), edge);
        }
        // Initializes the random graph
        graph = new DirectedWeightedGraphImpl(Vertices, Edges);
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
    void JSON_Reader() {
    }

    /**
     * The method Writes the graph, and tests if a new file were created.
     * If so, it checks also whether the created file isn't empty.
     * Afterwards, the method deletes the file, for a re-check.
     */
    @Test
    void JSON_Writer() {
    }
}