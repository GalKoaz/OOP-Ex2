package GraphGui;

import java.awt.geom.Point2D;

public class GraphPoint {
    private String id;
    private Point2D point;

    public GraphPoint(String id, Point2D point) {
        this.id = id;
        this.point = point;
    }

    public String getId() {
        return id;
    }

    public Point2D getPoint() {
        return point;
    }

}
