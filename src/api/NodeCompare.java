package api;


class NodeCompare implements Comparable<NodeCompare> {
    private NodeData vertex;

    public NodeCompare(NodeData v) {

        this.vertex = v;

    }

    public NodeData getVertex() {
        return vertex;
    }

    @Override
    public int compareTo(NodeCompare o) {
        return Double.compare(this.vertex.getWeight(), o.getVertex().getWeight());

    }
}
