package api;

import java.util.Iterator;

public class DirectedWeightedGraphImpl implements DirectedWeightedGraph {
    @Override
    public NodeData getNode(int key) {
        return Vertices.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return null;
    }

    @Override
    public void addNode(NodeData n) {

    }

    @Override
    public void connect(int src, int dest, double w) {

    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return null;
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
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        return null;
    }

    @Override
    public int nodeSize() {
        return 0;
    }

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int getMC() {
        return 0;
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
