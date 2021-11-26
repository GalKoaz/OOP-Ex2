package api;

import java.util.ArrayList;
import java.util.List;

public class DirectedWeightedGraphAlgorithmsImpl implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;
    private JSON_Operation json;
    private ArrayList<Double> eccentricity;


    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
        this.eccentricity = new ArrayList<>();

    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return null;
    }

    @Override
    public boolean isConnected() {
        for (int i = 0; i < graph.nodeSize() ; i++) {
            for (int j = 0; j < graph.nodeSize(); j++) {
                if(i==j)continue;
                if (!isPath(i,j)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPath(int src, int dest){
        DijkstraAlgorithm algo = new DijkstraAlgorithm(src,dest,this.graph);
        return algo.findMinDist() != 0;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        DijkstraAlgorithm algo = new DijkstraAlgorithm(src,dest,this.graph);
        return algo.findMinDist();
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }


    @Override
    public NodeData center() {
        if (!isConnected()) return null; // As written in the interface, we suppose that the graph is connected in calculations.
        double min = Double.MAX_VALUE;
        int center = 0;
        for (int v = 0; v < graph.nodeSize(); v++) {
            eccentricity.add(e(v));
            if (e(v) < min) {
                min = e(v);
                center = v;
            }
        }
        return graph.getNode(center);
    } // e(v)=max{d(u,v)/u\inV(G)}:v\inV(G)

    /**
     * computes the eccentricity of the vertex v. e(v)=max{d(v,u)/u\inV(G)}:v\inV(G)
     * @param v
     * @return
     */
    public double e(int v){
        double max = 0;
        for (int u = 0; u < graph.nodeSize(); u++) {
            if (u==v) continue;
            double d = shortestPathDist(v,u);
            if (d > max)
                max = d;
        }
        return max;
    }
        //TODO: I solved it via dynamic programming in second exercise with (O(n * 2^n)) time complexity.
        // Greedy algorithm is O(n^2 * log(n)) time complexity, however the optimal answer doesn't guaranteed.
    /**
     * DEFINITION. TSP problem is by given a complete graph with weighted edges,
     * what is the Hamiltonian cycle (the path that visits all every node once) of minimum cost.
     * @param cities
     * @return
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        double cost = 0;
        for (int i = 0; i < cities.size()-1; i++) {
            EdgeData currPairEdge = graph.getEdge(i,i+1);
            cost+=currPairEdge.getWeight();
        }
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
         this.json = new JSON_Operation(file);
        try {
            json.JSON_Reader();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        DirectedWeightedGraphAlgorithmsImpl a = new DirectedWeightedGraphAlgorithmsImpl();
        a.load("G2.json");
        DirectedWeightedGraph temp = new DirectedWeightedGraphImpl(a.json);
        a.init(temp);
        System.out.println(a.shortestPathDist(10,30));
        System.out.println(a.isConnected());

    }

}
