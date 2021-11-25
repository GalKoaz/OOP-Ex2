package api;

import java.util.List;

public class DirectedWeightedGraphAlgorithmsImpl implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;
    private JSON_Operation json;

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
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
            for (int j = 0; j < graph.nodeSize() && j!=i; j++) {
                if (!isPath(i,j)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPath(int src, int dest){

        return false;
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
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
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
            System.out.println(json.getEdges().get(0));
            System.out.println(json.getNodes().get(0));
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
        System.out.println(a.shortestPathDist(0,25));
    }

}
