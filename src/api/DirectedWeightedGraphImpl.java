package api;

import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;

public class DirectedWeightedGraphImpl implements DirectedWeightedGraph {

    private HashMap<Integer, NodeData> Vertices;
    private HashMap<String, EdgeData> Edges;
    private HashMap<String, EdgeData> Edges_copy;

    /**
     *  This constructor gets a JSON_Operation object, to initialize the graph's properties (edges and vertices),
     *  from the json file which constructed in DirectedWeightedGraphAlgorithmsImpl class.
     *  The method also initializes a deep copy of the edges for the class' methods.
     *
     *
     *  Brief explanation of our implementation: we have taken a hashmap to support as the collection to have the graph's properties,
     *  because it is considered the best time complexity for collection's operations with keys (O(1) for the standard operations).
     *  In order to know where exactly a certain edge from src to dest located in the hashmap,
     *  we generate the key = "src + dest".
     *  E.g: given an edge from 0 to 1, then the key for this edge would be the following string "01".
     *  Additionally, the vertices are ordered with keys by their id.
     *
     * @param json - a JSON_Operation object.
     */
    public DirectedWeightedGraphImpl(JSON_Operation json) {
        json.init_Graph();
        this.Vertices = new HashMap<>();
        this.Edges = new HashMap<>();
        this.Edges_copy = new HashMap<>();
        for (NodeData vertex : json.getInitVertices()) {
            this.Vertices.put(vertex.getKey(), vertex);
        }
        for (EdgeData edge : json.getInitEdges()) {
            this.Edges.put("" + edge.getSrc() + edge.getDest(), edge);
            this.Edges_copy.put("" + edge.getSrc() + edge.getDest(), new EdgeDataImpl(edge));
        }
    }

    public DirectedWeightedGraphImpl(HashMap<Integer, NodeData> Vertices,HashMap<String,
            EdgeData> Edges, HashMap<String, EdgeData> Edges_copy) {
       this.Edges_copy = Edges_copy;
       this.Edges = Edges;
       this.Vertices = Vertices;
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
     * As the method "removeNode", this method removes, allegedly, the vertex.
     * It is actually removes all edges starts or ends with the given node.
     * However, instead of removing it from the graph, because it isn't what explicitly written in the interface,
     * it removes it from a copy already constructed in the constructor.
     * Then, returns the iterator for this copy edges collection.
     *
     * @param node_id - a given node's id.
     * @return an iterator of the edges after removing all edges start or end in this given vertex.
     */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {

        for (int i = 0; i < Vertices.size(); i++) {
            if (node_id == i) {continue;}
            String currKey = "" + node_id + i;
            String currKey2 = "" + i + node_id;
            Edges_copy.remove(currKey);
            Edges_copy.remove(currKey2);

        }
        return Edges_copy.values().iterator();
    }
    /**
     *
     * The method goes over all vertices and checks which vertex has an edge with the given node.
     * If it finds such vertex it removes the edge between them.
     * Finally, the method removes the vertex from the vertices' collection (Hashmap).
     *
     * In terms of time complexity, we here got O(V) rather than O(k).
     * The reason for that is that our implementation works the way in which an edge from src to dest,
     * could be found in the hash map of the edges under the key = "src + dest".
     * E.g: given an edge from 0 to 1, then the key for this edge would be the following string "01".
     * Therefore, we have to go through all strings which starts with the given key, or ends there,
     * and that's the reason why we need to go over all vertices.
     * The different between O(V) and O(k) is a bit of a pain, but with a further look
     * it makes no different if the graph is complete, or if it's a standard graph with
     * high amount of edges.
     *
     * @param key - a given id of a node.
     * @return the removed node.
     */
    @Override
    public NodeData removeNode(int key) {
        for (int i = 0; i < Vertices.size(); i++) {
            if (key == i) {continue;}
            String currKey = "" + key + i;
            String currKey2 = "" + i + key;
            Edges.remove(currKey);
            Edges.remove(currKey2);
        }
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
    /**
     * This method gets a graph - g and performs a deep copy.
     * @param g - a given directed weighted graph to (deep) copy.
     * @return the (deep) copy of the graph - g.
     */
    public DirectedWeightedGraph deepCopy(DirectedWeightedGraphImpl g) {
        HashMap<Integer, NodeData> Vertices = new HashMap<>();
        HashMap<String, EdgeData> Edges = new HashMap<>();
        HashMap<String, EdgeData> Edges_copy = new HashMap<>();

        for (int k = 0; k < g.Vertices.size(); k++) {
            Vertices.put(k, new NodeDataImpl(g.getNode(k)));
        }
        for (int src = 0; src < g.nodeSize(); src++) {
            for (int dest = 0; dest < g.nodeSize(); dest++) {
                if (g.getEdge(src, dest) != null) {
                    Edges.put("" + src + dest, new EdgeDataImpl(g.getEdge(src, dest)));
                    Edges_copy.put("" + src + dest, new EdgeDataImpl(g.getEdge(src, dest)));
                }
            }
        }
        return new DirectedWeightedGraphImpl(Vertices, Edges, Edges_copy);
    }



/*    public static void main(String[] args) {
        System.out.println("" + 0 + 15);
        HashMap<Integer, NodeData> v = new HashMap<>();
        v.put(0, new NodeDataImpl(0, 2, "", 1.54545, new GeoLocationImpl(3.0, 4.0, 5.0)));
        HashMap<Integer, NodeData> v_copy = new HashMap<>(v);
        v_copy.remove(0);
        System.out.println(v.get(0).getWeight());
    }*/
}
