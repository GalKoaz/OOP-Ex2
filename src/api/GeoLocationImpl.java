package api;

public class GeoLocationImpl implements GeoLocation {
    private double x,y,z;

    public GeoLocationImpl(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    // A copy constructor.
    public GeoLocationImpl(GeoLocation other){this(other.x(), other.y(), other.z());}

    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public double z() {return z;}

    /**
     * The function follows the following formula to calculate the distance between two 3D points:
     *   given (x1,y1,z1) and (x2,y2,z2) -> distance = sqrt((x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2)
     * @param g - a given 3D point.
     * @return the distance between the two 3D points.
     */
    @Override
    public double distance(GeoLocation g) {
        return Math.sqrt(Math.pow(g.x()-x,2)+Math.pow(g.y()-y,2)+Math.pow(g.z()-z,2));
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public String toString(){ return x + "," + y + "," + z;}
}
