package api;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;

public class DirectedWeightedGraphImpl implements DirectedWeightedGraph {
    /**
     *
     */
    private HashMap<Integer, NodeData> Vertices;
    private HashMap<String, EdgeData> Edges;

    public DirectedWeightedGraphImpl(JSON_Operation json) {
        json.init_Graph();
        this.Vertices = new HashMap<>();
        this.Edges = new HashMap<>();
        for (NodeData vertex : json.getInitVertices()) {
            this.Vertices.put(vertex.getKey(), vertex);
        }
        for (EdgeData edge : json.getInitEdges()) {
            this.Edges.put("" + edge.getSrc() + edge.getDest(), edge);
        }
    }

    @Override
    public NodeData getNode(int key) {
        return Vertices.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return Edges.get("" + src + dest);
    }

    @Override
    public void addNode(NodeData n) {
        Vertices.put(n.getKey(), n);
    }

    @Override
    public void connect(int src, int dest, double w) {
        EdgeData connectedEdge = new EdgeDataImpl(src, dest, Color.BLUE.getRGB(), w, "Test");
        Edges.put("" + src + dest, connectedEdge);
    }

    @Override
    public Iterator<NodeData> nodeIter() { ///maybe don't work we need to check that. if no use this "Vertices.values().iterator()"
        return Vertices.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return Edges.values().iterator();
    }

    /**
     * NOTICE: IF YOU CHANGE THE VALUES OF THE HASHMAP COPY'S OBJECTS, IT CHANGES ALSO THE VALUES OF THE REAL HASHMAP.
     * EXAMINE DEEP COPY FOR EACH OBJECT IN THE HASHMAP
     *
     * @param node_id
     * @return
     */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        HashMap<String, EdgeData> Vertices_copy = new HashMap<>(Edges);
        for (int i = 0; i < Vertices_copy.size(); i++) {
            String currKey = "" + node_id + i;
            Vertices_copy.remove(currKey);
        }
        return Vertices_copy.values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        return Vertices.remove(key);
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        return Edges.remove("" + src + dest);
    }

    @Override
    public int nodeSize() {
        return Vertices.size();
    }

    @Override
    public int edgeSize() {
        return Edges.size();
    }

    @Override
    public int getMC() {
        return Vertices.size();
    }

    public HashMap<Integer, NodeData> getVertices() {
        return Vertices;
    }

    public void setVertices(HashMap<Integer, NodeData> vertices) {
        Vertices = vertices;
    }

    public HashMap<String, EdgeData> getEdges() {
        return Edges;
    }

    public void setEdges(HashMap<String, EdgeData> edges) {
        Edges = edges;
    }
    public static void main(String[] args) {
        System.out.println("" + 0 + 15);
        HashMap<Integer, NodeData> v = new HashMap<>();
        v.put(0, new NodeDataImpl(0, 2, "", 1.54545, new GeoLocationImpl(3.0, 4.0, 5.0)));
        HashMap<Integer, NodeData> v_copy = new HashMap<>(v);
        v_copy.remove(0);
        System.out.println(v.get(0).getWeight());
    }
}
