package api;

/**
 * This class supports a comparator of nodes weights comparison,
 * as well as parent pointers to know which node we last visit in
 * Dijkstra's algorithm.
 */
class NodeCompare implements Comparable<NodeCompare> {

    private NodeData vertex;
    private NodeCompare parent;

    public NodeCompare(NodeData v) {
        this.vertex = v;
        this.parent = null;
    }

    public NodeData getVertex() {
        return vertex;
    }
    /**
     * This function helps to compare between two nodes, by their weights.
     * The lower, the better.
     * @param o an object to compare with.
     * @return 1 if the curr vertex has lower weight than the given node.
     *         Otherwise, it returns -1.
     */
    @Override
    public int compareTo(NodeCompare o) {
        return Double.compare(this.vertex.getWeight(), o.getVertex().getWeight());
    }

    public NodeCompare getParent() {
        return parent;
    }

    public void setParent(NodeCompare parent) {
        this.parent = parent;
    }
}
