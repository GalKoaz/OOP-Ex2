package api;

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
    /**
     * The method returns a deep copy of the initialized graph.
     * The documentations for this are located in DirectedWeightedGraphImpl class.
     * @return a deep of the initialized graph.
     */
    @Override
    public DirectedWeightedGraph copy() {
        return ((DirectedWeightedGraphImpl) graph).deepCopy(graph);
    }
    /**
     * Graph Theory. A directed graph is connected iff we can reach from any node to any other node that has chosen.
     * @apiNote all documentations of the using of DFS is located whithin the DFS class.
     * @return if connected by that definition.
     */
    @Override
    public boolean isConnected() { return new DFS((DirectedWeightedGraphImpl) graph).isConnected();}
    /**
     * The method uses Dijkstra's algorithm for finding the optimal path from src to destination.
     *
     * In terms of time complexity: the way we implemented the algorithm gives us time complexity
     * of O(|V|+|E|*log(|V|)), because the structure we have applied is priority queue, which arranges
     * the nodes by their current accumulated distance. This time complexity
     *
     * @param src start node.
     * @param dest end (target) node.
     * @return the optimal path's cost.
     */
    @Override
    public double shortestPathDist(int src, int dest) { return new DijkstraAlgorithm(src,dest,this.graph).getOptPath();}
    /**
     * This returns the shortestPath as a consecutive nodes: node(src),..., node(dest).
     * The OptimalPath property is set on DijkstraAlgorithm class' constructor, so
     * the documentations for that are located in this class.
     * @param src start node.
     * @param dest end (target) node.
     * @return the list of shortestPath as a consecutive nodes: {node(src),..., node(dest)}.
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {return new DijkstraAlgorithm(src,dest,this.graph).getOptimalPath();}
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
     * As being said the eccentricity defined as the following: e(v)=max{d(v,u) such that u in V(G) for each u in V(G)}.
     *
     * @param v the if of a vertex v.
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
     * @param cities the cities to go over.
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
     * The method saves the graph as a json file in the given path.
     * @apiNote Documentations of the json writer are located within JSON_Operation class.
     * @param file the file name (may include a relative path).
     * @return true if the method have successfully written the json file.
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
     * The method loads the given file with the JSON_Operation class.
     * Afterwards, the method initialize the graph with the json.
     * @apiNote  Documentations of the json reader are located within JSON_Operation class.
     * @param file file name of JSON file
     * @return true if the method successfully read the json file.
     */
    @Override
    public boolean load(String file) {
         this.json = new JSON_Operation(file);
        try {
            json.JSON_Reader();
            init(new DirectedWeightedGraphImpl(json));
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public JSON_Operation getJson() {
        return json;
    }
}
