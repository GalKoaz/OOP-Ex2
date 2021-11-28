package api;

public class NodeDataImpl implements NodeData{

    private GeoLocation Node;
    private final int key;
    private int tag;
    private double weight;
    private String info;

    public NodeDataImpl(int key, int tag, String info, double weight, GeoLocation Node) {
        this.key = key;
        this.tag = tag;
        this.info = info;
        this.weight = weight;
        this.Node = Node;
    }
    // A copy constructor
    public NodeDataImpl(NodeData other){ this(other.getKey(), other.getTag(), other.getInfo(), other.getWeight(), other.getLocation());}

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public GeoLocation getLocation() {
        return Node;
    }

    @Override
    public void setLocation(GeoLocation p) {this.Node = new GeoLocationImpl(p);}

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {this.info = s;}

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}
