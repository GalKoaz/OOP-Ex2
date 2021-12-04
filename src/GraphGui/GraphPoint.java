package GraphGui;

import java.awt.*;
import java.awt.geom.Point2D;

public class GraphPoint {
    private String id;
    private Point2D point;
    private Color tag;

    public GraphPoint(String id, Point2D point, Color tag) {
        this.id = id;
        this.point = point;
        this.tag = tag;
    }

    public String getId() {
        return id;
    }

    public Point2D getPoint() {
        return point;
    }

    public Color getTag() {
        return tag;
    }

    public void setTag(Color tag) {
        this.tag = tag;
    }
}
