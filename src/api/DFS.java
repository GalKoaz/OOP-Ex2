package api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class brief explanation:
 * This class operates DFS for a given graph,
 * and checks whether the graph is connected.
 */
public class DFS {

    private DirectedWeightedGraph g;
    private ArrayList<ArrayList<NodeData>> adjacentVerts;
    private boolean[] visited;

    public DFS(DirectedWeightedGraphImpl g){
        this.g = g.deepCopy(g);
        this.adjacentVerts = new ArrayList<>();
        updateNeighbours();
        visited = new boolean[g.nodeSize()];
    }
    /**
     * The method does a DFS twice:
     * the first DFS is starting from zero, this will tell us that we can reach from zero to each one of the other nodes.
     * The second DFS also starts at zero, but with an opposite direction of the edges,
     * and this would tell us that the graph is connected, because if we can
     * reach to any other nodes from zero with G(E) and G'(E), then it means that the graph
     * can reach to any node from any node we choose.
     * @return if the graph is connected by definition.
     */
    public boolean isConnected(){
        DFS(0);
        for (int visit = 0; visit < visited.length; visit++) {
            if (!visited[visit]){ return false;}
        }
        visited = new boolean[g.nodeSize()];
        g = turnEdgesDirection((DirectedWeightedGraphImpl) g);
        updateNeighbours();
        DFS(0);
        for (int visit = 0; visit < visited.length; visit++) {
            if (!visited[visit]){ return false;}
        }
        return true;
    }
    /**
     * Performs DFS traversal which starts from the vertex v.
     * @param v a given vertex index.
     */
    public void DFS(int v){ DFS_Visit(v);}

    public void DFS_Visit(int u) {
        visited[u] = true;
        for (int v = 0; v < adjacentVerts.get(u).size(); v++) {
            NodeData curr = adjacentVerts.get(u).get(v);
            if (!visited[curr.getKey()]){
                DFS_Visit(curr.getKey());
            }
        }
    }
    /**
     * This method updates the neighbours of each vertex.
     * This method is well documented in DijkstraAlgorithm class.
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
     * This method goes over the graph's edges and turn the edge's direction,
     * just from creating a new edge when the src = dest and so for dest = src.
     * Then, the method returns a new graph with those new edges.
     * @param g a given graph.
     * @return a new graph with opposite edges direction.
     */
    public DirectedWeightedGraph turnEdgesDirection(DirectedWeightedGraphImpl g){
        HashMap<String, EdgeData> turnedEdges = new HashMap<>();
        Iterator<EdgeData> edges = g.edgeIter();
        while (edges.hasNext()){
            EdgeData curr = edges.next();
            EdgeData oppEdge = new EdgeDataImpl(curr.getDest(),curr.getSrc(),curr.getTag(),curr.getWeight(),curr.getInfo());
            turnedEdges.put(""+curr.getDest()+"-"+curr.getSrc(),oppEdge);
        }
        return new DirectedWeightedGraphImpl(g.getVertices(),turnedEdges);
    }
}
