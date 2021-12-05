package GraphGui;

import java.awt.*;
import java.awt.geom.Point2D;

public class GraphPoint {
    private String id;
    private Point2D point;
    private Color tag, tag_2;

    public GraphPoint(String id, Point2D point, Color tag, Color tag_2) {
        this.id = id;
        this.point = point;
        this.tag = tag;
        this.tag_2 = tag_2;
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

    public void setTag_2(Color tag_2) {
        this.tag_2 = tag_2;
    }

    public Color getTag_2() { return tag_2;}
}
