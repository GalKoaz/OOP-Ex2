package api;

import java.util.ArrayList;
import java.util.PriorityQueue;


/**
 * Class explanation:
 *
 * This class supports the implementation of Dijkstra's algorithm to
 * find the shortest path from src vertex towards destination vertex.
 *
 * The implementation's structure applied is a priority queue of nodes
 * of the class NodeCompare, which supports a comparator of nodes weights
 * comparison.
 *
 * Time complexity: in terms of time complexity this implementation of a
 * priority queue by weights, guarantees time complexity of O(|V|+|E|*log(|V|)).
 *
 */
public class DijkstraAlgorithm {

    private DirectedWeightedGraph g;
    private double[] dist;
    private ArrayList<ArrayList<NodeData>> adjacentVerts;
    private ArrayList<Integer> visited;
    private PriorityQueue<NodeCompare> dists;
    private int[] parent;
    private ArrayList<NodeData> optimalPath;
    private NodeCompare destNode;
    private int src, dest;
    private double optPath;


    public DijkstraAlgorithm(int src,int dest, DirectedWeightedGraph g) {
        // Properties initialization //
        this.src = src;
        this.dest =dest;
        this.g = g;
        this.dists = new PriorityQueue<>(g.nodeSize());
        this.optimalPath = new ArrayList<>();
        this.adjacentVerts = new ArrayList<>();
        this.visited = new ArrayList<>();
        this.dist = new double[g.nodeSize()];
        this.parent = new int[g.nodeSize()];

        // Updates the adj list
        updateNeighbours();

        // Initializes the dist array to be infinite except the source node - first check
        for (int i = 0; i < dist.length; i++) {
            if(i==src) continue;
            this.dist[i] = Double.MAX_VALUE;
        }
        // Updates source vertex related properties
        this.parent[src] = -1;
        g.getNode(src).setWeight(0);
        this.dists.add(new NodeCompare(g.getNode(src)));

        // Updates the optimal path's cost property, and sets optimalPath nodes list
        this.optPath = findMinDist();
        setOptimalPath();
    }
    /**
     * The method here visits all vertices to find the optimal path's cost, and return it.
     * The method starts with the source vertex, if it is the destination vertex, then it saves the pointer,
     * for the setOptimalPath method.
     *
     * In addition, if the current check node hasn't visited yet, then it adds the vertex's id
     * to the visited nodes list, and also check for his neighbours with the currentNeighbours,
     * to see which one of the neighbours is the optimal one (exactly as doing in greedy approach).
     *
     * @return the optimal path's cost from src to dest.
     */
    public double findMinDist() {
        while (visited.size() != g.nodeSize()) { // while we didn't finish going over all vertices.
            if (dists.isEmpty()) return -1;
            NodeCompare check = dists.remove();
            int check_id = check.getVertex().getKey();
            if (check_id == dest){destNode = check;}
            if (visited.contains(check_id)) {continue;}
            visited.add(check_id);
            currentNeighbours(check_id, check);
        }
        return dist[dest];
    }
    /**
     * The function goes over each node and checks for the adjacent nodes that he has an edge with.
     * For each such edge, the method adds the edge to the adjacent node list.
     * The main list (adj) is constructed as following: adj = {{n1,n2,...},{n11,n22,...},...}
     * where adj in a place i, resembles the node with id = i.
     */
    public void updateNeighbours() {
        for (int i = 0; i < g.nodeSize(); i++) {
            ArrayList<NodeData> adj = new ArrayList<>();
            for (int j = 0; j < g.nodeSize(); j++) {
                if (i != j) {
                    if (g.getEdge(i, j) != null) {
                        adj.add(g.getNode(j));
                    }
                }
            }
            this.adjacentVerts.add(i, adj);
        }
    }
    /**
     * The method goes over all the adjacent nodes to the one we check,
     * and then if the node hasn't visited yet, then we check if the optimal distance plus the edge's weight
     * from check node to the current vertex, is lower in cost than the current optimal distance for the current node.
     * If so, we update the current optimal distance for the current node with that lower cost.
     *
     * In addition, we update the current node's parent to be the checkNode,
     * and of course update the node's weight property in order to ease the implementation.
     *
     * @param check an index of the current node we visited and want to check.
     * @param checkNode the nodeCompare object of the current check.
     */
    private void currentNeighbours(int check, NodeCompare checkNode) {
        double currWeight;
        for (int i = 0; i < adjacentVerts.get(check).size(); i++) {
            NodeData v = adjacentVerts.get(check).get(i);
            NodeCompare curr = new NodeCompare(g.getNode(v.getKey()));
            if (!visited.contains(v.getKey())) {
                currWeight = dist[check] + g.getEdge(check, v.getKey()).getWeight();
                if (currWeight < dist[v.getKey()]) { dist[v.getKey()] = currWeight;}
                g.getNode(v.getKey()).setWeight(dist[v.getKey()]);
                dists.add(curr);
                parent[v.getKey()] = check;
                curr.setParent(checkNode);
            }
        }
    }
    /**
     * The method sets the optimal path, using the parent property of each nodeCompare.
     * The method sort of going back to the linked parent of the node, until it gets
     * to the ancestor (root), and for each nodeCompare the method adds at the BEGINNING the NodeData
     * object which belongs to the current nodeCompare.
     *
     * @implNote At the end, we will get the objects list with the following order: {src, ..., dest}.
     */
    public void setOptimalPath(){
        NodeCompare curr = destNode;
        optimalPath.add(curr.getVertex());
        while (curr.getParent()!=null){ optimalPath.add(0,curr.getParent().getVertex()); curr = curr.getParent();}
    }
    /**
     * Prints all paths from src to a specific dest vertex.
     * @param parent the array of the parents nodes' id.
     * @param src the source index.
     * @param dest the destination index.
     */
    public void printDijkstraOptimalPath(int[] parent, int src, int dest){
        System.out.println("Dijkstra Algorithm: (With src-dest path)");
        System.out.print(" " + src + " --> " +  dest + ": distance = " + dist[dest] + "  Path : ");
        printOptimalPathUtil(parent, dest);
        System.out.println();
    }
    /**
     * Prints all paths from src to each of the other nodes.
     * @param parent the parent array of id's.
     * @param src the source index.
     */
    public void printDijkstraAllPaths(int[] parent, int src){
        System.out.println("Dijkstra Algorithm: (With src-dests paths)");
        for (int dest = 0; dest < dist.length; dest++) {
            System.out.print(" " + src + " --> " + dest + ": distance = " + dist[dest] + "  Path : ");
            printOptimalPathUtil(parent, dest);
            System.out.println();
        }
    }
    /**
     * This utility helps with recursion to go back in the hierarchy, and print
     * by the original order the vertices' id.
     * @param parent the parent array of id's.
     * @param dest the destination index.
     */
    public void printOptimalPathUtil(int[] parent, int dest){
        if(parent[dest] == -1) { System.out.print(src + " "); return;}
        printOptimalPathUtil(parent, parent[dest]);
        System.out.print(dest + " ");
    }

    public ArrayList<NodeData> getOptimalPath() {
        return optimalPath;
    }

    public double getOptPath() {
        return optPath;
    }
}
