package api;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Spliterator;


public class DijkstraAlgorithm {

    private DirectedWeightedGraph g;
    private double[] dist;
    private ArrayList<ArrayList<NodeData>> adjacentVerts;
    private ArrayList<Integer> optimalPath, settled;
    private PriorityQueue<NodeData> dists;

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
        //this.dists.add(g.getNode(src));
    }

    public double findMinDist() {
        while (settled.size() != g.nodeSize()) { // while we didn't finish going over all vertices.
            if (dists.isEmpty()) return -1;
            int check = dists.remove().getKey();
            if (settled.contains(check)) continue;
            settled.add(check);
            currentNeighbours(check);
        }
        NodeData temp = g.getNode(0);

        return dist[dest];
    }


    /**
     * Creates a <em><a href="Spliterator.html#binding">late-binding</a></em>
     * and <em>fail-fast</em> {@link Spliterator} over the elements in this
     * queue. The spliterator does not traverse elements in any particular order
     * (the {@link Spliterator#ORDERED ORDERED} characteristic is not reported).
     *
     * <p>The {@code Spliterator} reports {@link Spliterator#SIZED},
     * {@link Spliterator#SUBSIZED}, and {@link Spliterator#NONNULL}.
     * Overriding implementations should document the reporting of additional
     * characteristic values.
     *
     * @return a {@code Spliterator} over the elements in this queue
     * @since 1.8
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
                dists.add(g.getNode(v.getKey()));
            }
        }
    }
}
