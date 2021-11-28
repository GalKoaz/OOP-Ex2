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
    private HashMap<String, EdgeData> Edges_copy;

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
     * NOTICE: IF YOU CHANGE THE VALUES OF THE HASHMAP COPY'S OBJECTS, IT CHANGES ALSO THE VALUES OF THE REAL HASHMAP.
     * EXAMINE DEEP COPY FOR EACH OBJECT IN THE HASHMAP
     *
     * @param node_id
     * @return
     */
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {

        for (int i = 0; i < Vertices.size(); i++) {
            if (node_id == i) {continue;}
            String currKey = "" + node_id + i;
            String currKey2 = "" + i + node_id;
            Edges.remove(currKey);
            Edges.remove(currKey2);

        }
        return Edges.values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        for (int i = 0; i < Vertices.size(); i++) {
            if (key == i) {continue;}
            String currKey = "" + key + i;
            String currKey2 = "" + i + key;
            Edges_copy.remove(currKey);
            Edges_copy.remove(currKey2);
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
     * performs a deep copy of a graph g
     * @param g
     * @return
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
