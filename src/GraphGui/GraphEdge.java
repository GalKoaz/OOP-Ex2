package GraphGui;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class GraphEdge {

    private ArrayList<GraphPoint> Points;
    private String weight;

    public GraphEdge(String weight, ArrayList<GraphPoint> Points) {
        this.Points = Points;
        this.weight = weight;
    }

    public String getWeight() {
        return weight;
    }

    public ArrayList<GraphPoint> getPoints() {
        return Points;
    }

}
