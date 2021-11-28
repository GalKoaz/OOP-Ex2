package api;


class NodeCompare implements Comparable<NodeCompare> {

    private NodeData vertex;

    public NodeCompare(NodeData v) {this.vertex = v;}

    public NodeData getVertex() {
        return vertex;
    }
    /**
     * This function helps to compare between two nodes, by their weights.
     * The lower, the better.
     * @param o - an object to compare with.
     * @return 1 if the curr vertex has lower weight than the given node.
     *         Otherwise, it returns -1.
     */
    @Override
    public int compareTo(NodeCompare o) {
        return Double.compare(this.vertex.getWeight(), o.getVertex().getWeight());
    }
}
