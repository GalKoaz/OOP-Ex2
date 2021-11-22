package api;

public class NodeDataImpl implements NodeData {

    private GeoLocationImpl Node;
    private int key, tag;
    private double weight;
    private String info;


    public NodeDataImpl(int key, int tag, String info, double weight, GeoLocationImpl Node){
        this.info = info;
        this.Node = Node;
        this.tag = tag;
        this.weight = weight;
        this.key = key;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public GeoLocation getLocation() {
        return Node;
    }

    @Override
    public void setLocation(GeoLocation p) {
        double newX = p.x();
        double newY = p.y();
        double newZ = p.z();
        this.Node.setX(newX);
        this.Node.setY(newY);
        this.Node.setZ(newZ);
    }

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
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}
