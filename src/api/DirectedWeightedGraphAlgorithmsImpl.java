package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DirectedWeightedGraphAlgorithmsImpl implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph graph;
    private JSON_Operation json;

    @Override
    public void init(DirectedWeightedGraph g) { this.graph = g; }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return ((DirectedWeightedGraphImpl) graph).deepCopy(graph);
    }

    /**
     * Graph Theory. A directed graph is connected iff it has n*(n-1) edges, when n symbolizes the number of vertices.
     * @return if connected by that definition.
     */
    @Override
    public boolean isConnected() {
        return graph.edgeSize() == graph.nodeSize()*(graph.nodeSize()-1);
    }

    /**
     *
     * @param src - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        DijkstraAlgorithm algo = new DijkstraAlgorithm(src,dest,this.graph);
        return algo.findMinDist();
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {

        return null;
    }
    /**
     * This method finds the center of the graph.
     *
     * Graph Center DEFINITION. the graph center is the vertex to have the minimum eccentricity.
     *
     * In a defined formula: C = min{e(v) | for each v in G(V)}
     *
     * @return the center node of the graph.
     */
    @Override
    public NodeData center() {
        if (!isConnected()) {return null;} // As written in the interface, we suppose that the graph is connected in calculations.
        double min = Double.MAX_VALUE;
        int center = 0;
        for (int v = 0; v < graph.nodeSize(); v++) {
            if (e(v) < min) {
                min = e(v);
                center = v;
            }
        }
        return graph.getNode(center);
    }
    /**
     *
     * This method computes the eccentricity of a vertex v.
     *
     * Eccentricity DEFINITION. the eccentricity of a vertex v is the maximum distance between v towards
     * each one of the other vertices.
     *
     * Distance DEFINITION. the distance d(v,u) is the shortest path between v and u.
     *
     *
     * As being said the eccentricity defined as the following: e(v)=max{d(v,u) such that u in V(G) for each v in V(G)}.
     *
     * @param v - the if of a vertex v.
     * @return the eccentricity of v.
     */
    public double e(int v){
        double max = 0;
        for (int u = 0; u < graph.nodeSize(); u++) {
            if (u == v) {continue;}
            double d = shortestPathDist(v, u);
            if (d > max) { max = d;}
        }
        return max;
    }
    /**
     * DEFINITION TSP problem. By given a complete graph with weighted edges,
     * what is the Hamiltonian cycle (the path that visits all every node once) of minimum cost.
     *
     * For this problem we apply the greedy approach:
     * Greedy approach explanation. this approach always goes to the nearest (the lowest cost edge) vertex from the current vertex,
     * until it goes over all vertices.
     * Then, the approach doing the same procedure for each vertex, and finally takes the minimal cost cycle,
     * which of course goes over all cities.
     *
     *
     * Time complexity: this approach gives a significant low time complexity of O(n^2 * log(n)),
     * however, the greedy approach doesn't always provide the optimal solution. In contrast,
     * the dynamic approach always gives the optimal solution, but has a way higher cost,
     * in terms of time complexity which is O(n * 2^n). Therefore, for this project
     * we prefer the greedy approach.
     *
     *
     * @param cities - the cities to go over.
     * @return the optimal path.
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        ArrayList<NodeData> optPath = new ArrayList<>();
        ArrayList<NodeData> currPath = new ArrayList<>();
        double min = Double.MAX_VALUE;
        for (int city = 0; city < cities.size(); city++) {
            double optPathLength = pathCost(minCycleFromCity(cities,city, currPath,0));
            if (optPathLength < min){
                min = optPathLength;
                optPath = minCycleFromCity(cities,city, currPath,0);
            }

        }
        return optPath;
    }
    /**
     *
     * @param cities
     * @param city
     * @param optPath
     * @param cnt
     * @return
     */
    public ArrayList<NodeData> minCycleFromCity(List<NodeData> cities, int city, ArrayList<NodeData> optPath, int cnt){
        optPath.add(graph.getNode(city));
        if (cnt == cities.size()) {
            return optPath;
        }
        double minWeight = Double.MAX_VALUE;
        int minCity = 0, cityToCheck = 0;

        while(cityToCheck < cities.size()) {
            if (city == cityToCheck) {continue;}
            double currWeight = graph.getEdge(city,cityToCheck).getWeight();
            if (currWeight < minWeight) {
                minWeight = currWeight;
                minCity = cityToCheck;
            }
            cityToCheck++;
        }
        return minCycleFromCity(cities, minCity,optPath, cnt+1);
    }
    /**
     * The method calculates the path's cost, meaning that it goes over each pair
     * and add its weight to the total weight.
     * @param path a given path - list of vertices.
     * @return the path cost - total weight.
     */
    public double pathCost(ArrayList<NodeData> path){
        double totalPathCost = 0;
        for (int i = 0; i < path.size()-1; i++) {
            int currSrc = path.get(i).getKey();
            int currDest = path.get(i+1).getKey();
            totalPathCost += graph.getEdge(currSrc,currDest).getWeight();
        }
        return totalPathCost;
    }

    /**
     *
     * @param file - the file name (may include a relative path).
     * @return
     */
    @Override
    public boolean save(String file) {
        JSON_Operation Writer = new JSON_Operation(file);
        try {
            Writer.JSON_Writer(this.graph);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *
     * @param file - file name of JSON file
     * @return
     */
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
        a.load("G1.json");
        DirectedWeightedGraph temp = new DirectedWeightedGraphImpl(a.json);
        a.init(temp);
        //System.out.println(a.shortestPathDist(10,30));
        System.out.println(a.isConnected());
        a.save("G4.json");
    }
}
