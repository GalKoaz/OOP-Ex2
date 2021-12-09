package api;

import java.util.*;

/**
 * Class brief explanation:
 * This class operates DFS for a given graph,
 * and checks whether the graph is connected.
 */
public class BFS {

    private DirectedWeightedGraph g;
    private boolean[] visited;
    private Queue<Integer> q;


    public enum Tags{
        VISITED(1),
        UNVISITED(0);

        private final int value;

        Tags(int value) {this.value = value;}
    }

    public BFS(DirectedWeightedGraphImpl g){
        this.g = g.deepCopy(g);
        q = new ArrayDeque<>();
        setTags(Tags.UNVISITED.value);
    }


    /**
     * The method does a BFS twice:
     * the first BFS is starting from zero, this will tell us that we can reach from zero to each one of the other nodes.
     * The second BFS also starts at zero, but with an opposite direction of the edges,
     * and this would tell us that the graph is connected, because if we can
     * reach to any other nodes from zero with G(E) and G'(E), then it means that the graph
     * can reach to any node from any node we choose.
     * @return if the graph is connected by definition.
     */
    public boolean isConnected(){
        NodeData v = g.nodeIter().next();
        BFS(v);
        Iterator<NodeData> nodes = g.nodeIter();
        while (nodes.hasNext()){
            if (nodes.next().getTag() == Tags.UNVISITED.value){return false;}
        }
        setTags(Tags.UNVISITED.value);
        g = turnEdgesDirection((DirectedWeightedGraphImpl) g);
        BFS(v);
        Iterator<NodeData> invertedNodes = g.nodeIter();
        while (invertedNodes.hasNext()){
            if (invertedNodes.next().getTag() == Tags.UNVISITED.value){return false;}
        }
        return true;
    }
    /**
     * Performs BFS traversal which starts from the vertex v.
     * @param root a given vertex index.
     *
    // 1     BFS(root):
    // 2      label root as explored
    // 3      Q.enqueue(root)
    // 4          visited[u] ← true     ▹White vertex u has just been discovered.
    // 5      while Q is not empty do:
    // 6          v ← Q.dequeue()
    // 7          for each v ∈ Adj[u]  ▹Explore edge(u, v).
    // 8              if v is not labeled as explored then:
    // 9                  visited[u] ← true
    // 10                  Q.enqueue(w)
     */
    public void BFS(NodeData root){
        root.setTag(Tags.VISITED.value);
        q.add(root.getKey());
        while(!q.isEmpty()) {
            Integer curr = q.remove();
            Iterator<EdgeData> neighbours = g.edgeIter(curr);
            if (neighbours == null) {return;}
            while (neighbours.hasNext()) {
                EdgeData currEdge = neighbours.next();
                int curr_id = currEdge.getDest();
                NodeData currN = g.getNode(curr_id);
                if (currN.getTag() == Tags.UNVISITED.value){
                    currN.setTag(Tags.VISITED.value);
                    q.add(curr_id);
                }
            }
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
        HashMap<Integer,HashMap<Integer, EdgeData>> turnedEdges = new HashMap<>();
        Iterator<EdgeData> edges = g.edgeIter();
        while (edges.hasNext()) {
            EdgeData curr = edges.next();
            EdgeData oppEdge = new EdgeDataImpl(curr.getDest(), curr.getSrc(), curr.getTag(), curr.getWeight(), curr.getInfo());
            if (turnedEdges.containsKey(curr.getDest())) {
                turnedEdges.get(curr.getDest()).put(curr.getSrc(), oppEdge);
            }
            else{
                turnedEdges.put(curr.getDest(),new HashMap<>());
                turnedEdges.get(curr.getDest()).put(curr.getSrc(), oppEdge);
            }
        }
        return new DirectedWeightedGraphImpl(g.getVertices(),turnedEdges);
    }
    /**
     * This method sets the tags.
     * @param value a given value
     */
    public void setTags(int value){
        Iterator<NodeData> nodes = g.nodeIter();
        while(nodes.hasNext()){nodes.next().setTag(value);}
    }
}
