package GraphGui;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class GraphEdge {

    private ArrayList<GraphPoint> Points;
    private String weight;
    private Color tag;

    public GraphEdge(String weight, ArrayList<GraphPoint> Points, Color tag) {
        this.Points = Points;
        this.weight = weight;
        this.tag = tag;
    }

    public String getWeight() {
        return weight;
    }

    public ArrayList<GraphPoint> getPoints() {
        return Points;
    }

    public Color getTag() {
        return tag;
    }

    public void setTag(Color tag) {
        this.tag = tag;
    }
}
