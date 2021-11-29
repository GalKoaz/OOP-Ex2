package api;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {

    private DirectedWeightedGraph g;
    private double[] dist;
    private ArrayList<ArrayList<NodeData>> adjacentVerts;
    private ArrayList<Integer> visited;
    private PriorityQueue<NodeCompare> dists;
    private int[] parent;
    private ArrayList<NodeData> optimalPath;
    private NodeCompare destNode;
    private int src, dest; //the ID of the source and destination vertex.
    private double optPath;

    /**
     *
     * @param src - a given source index.
     * @param dest - a given destination index.
     * @param g - a given graph.
     */
    public DijkstraAlgorithm(int src,int dest, DirectedWeightedGraph g) {
        this.src = src;
        this.dest =dest;
        this.g = g;
        this.dists = new PriorityQueue<>(this.g.nodeSize());
        this.optimalPath = new ArrayList<>();
        this.adjacentVerts = new ArrayList<>();
        this.visited = new ArrayList<>();
        this.dist = new double[g.nodeSize()];
        this.parent = new int[this.g.nodeSize()];

        updateNeighbours();

        for (int i = 0; i < dist.length; i++) {
            if(i==src) continue;
            this.dist[i] = Double.MAX_VALUE;
        }
        this.parent[src] = -1;
        g.getNode(src).setWeight(0);
        this.dists.add(new NodeCompare(g.getNode(src)));

        this.optPath = findMinDist();
        setOptimalPath();
    }
    /**
     *
     * @return the optimal path's cost.
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
     * In addition, we update the current node's parent to be the checkNode.
     *
     * @param check - an index of the current node we visited and want to check.
     * @param checkNode - the nodeCompare object of the current check.
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
     *
     */
    public void setOptimalPath(){
        NodeCompare curr = destNode;
        optimalPath.add(curr.getVertex());
        while (curr.getParent()!=null){ optimalPath.add(curr.getParent().getVertex()); curr = curr.getParent();}
    }
    /**
     *
     * @param parent - the array of the parents nodes' id.
     * @param src - the source index.
     * @param dest - the destination index.
     */
    public void printDijkstraOptimalPath(int[] parent, int src, int dest){
        System.out.println("Dijkstra Algorithm: (With src-dest path)");
        System.out.print(" " + src + " --> " +  dest + ": distance = " + dist[dest] + "  Path : ");
        printOptimalPathUtil(parent, dest);
        System.out.println();
    }
    /**
     * prints all paths from src to each of the other nodes.
     * @param parent - the parent array of id's.
     * @param src - the source index.
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
     *
     * @param parent - the parent array of id's.
     * @param dest - the destination index.
     */
    public void printOptimalPathUtil(int[] parent, int dest){
        if(parent[dest] == -1) { System.out.print(src + " "); return;}
        printOptimalPathUtil(parent, parent[dest]);
        System.out.print(dest + " ");
    }

    public ArrayList<NodeData> getOptimalPath() {
        return optimalPath;
    }

    public int[] getParent() {
        return parent;
    }

    public double getOptPath() {
        return optPath;
    }
}
