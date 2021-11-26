package api;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Spliterator;


public class DijkstraAlgorithm {

    private DirectedWeightedGraph g;
    private double[] dist;
    private ArrayList<ArrayList<NodeData>> adjacentVerts;
    private ArrayList<Integer> optimalPath, settled;
    private PriorityQueue<NodeCompare> dists;
    private ArrayList<ArrayList> Paths;

    private int src, dest; //the ID of the source and destination vertex

    public DijkstraAlgorithm(int src,int dest, DirectedWeightedGraph g) {
        this.src = src;
        this.dest =dest;
        this.g = g;
        this.dists = new PriorityQueue<>(this.g.nodeSize());
        this.optimalPath = new ArrayList<>();
        this.adjacentVerts = new ArrayList<>();
        this.settled = new ArrayList<>();
        this.dist = new double[g.nodeSize()];
        updateNeighbours();
        for (int i = 0; i < dist.length; i++) {
            if(i==src) continue;
            this.dist[i] = Double.MAX_VALUE;
        }
        g.getNode(src).setWeight(0);
        this.dists.add(new NodeCompare(g.getNode(src)));
    }

    public double findMinDist() {
        while (settled.size() != g.nodeSize()) { // while we didn't finish going over all vertices.
            if (dists.isEmpty()) return -1;
            int check = dists.remove().getVertex().getKey();
            if (settled.contains(check)) continue;
            settled.add(check);
            currentNeighbours(check);
        }
        NodeData temp = g.getNode(0);

        return dist[dest];
    }

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

    private void currentNeighbours(int check) {
        double edgeDistance = -1, newDistance = -1;
        for (int i = 0; i < adjacentVerts.get(check).size(); i++) {
            NodeData v = adjacentVerts.get(check).get(i);
            if (!settled.contains(v.getKey())) {
                edgeDistance = g.getEdge(check, v.getKey()).getWeight();
                newDistance = dist[check] + edgeDistance;
                if (newDistance < dist[v.getKey()])
                    dist[v.getKey()] = newDistance;
                g.getNode(v.getKey()).setWeight(dist[v.getKey()]);
                dists.add(new NodeCompare(g.getNode(v.getKey())));
            }
        }
    }
}
