package GraphGui;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class GraphEdge {

    private ArrayList<GraphPoint> Points;
    private String weight;
    private Color tag, tag_2;
    private int stroke;

    public GraphEdge(String weight, ArrayList<GraphPoint> Points, Color tag, int stroke, Color tag_2) {
        this.Points = Points;
        this.weight = weight;
        this.tag = tag;
        this.tag_2 = tag_2;
        this.stroke = stroke;
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

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public int getStroke() {
        return stroke;
    }

    public void setTag_2(Color tag_2) {
        this.tag_2 = tag_2;
    }

    public Color getTag_2() {
        return tag_2;
    }
}
