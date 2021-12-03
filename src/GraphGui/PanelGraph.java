package GraphGui;

import api.DirectedWeightedGraph;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class PanelGraph extends JPanel {
    private DirectedWeightedGraph graph;
    private java.util.List<GraphPoint> points;
    private List<GraphEdge> edges;
    private int radius = 10;
    private double Phi = Math.toRadians(40);
    private double virtualScale = 1.0;

    private Point2D minRange;
    private Point2D maxRange;

    PanelGraph(DirectedWeightedGraph graph) {
        this.setPreferredSize(new Dimension(1000, 1000));
        this.points = new ArrayList<>();
        this.graph = graph;
        pointInit();
        EdgeInit();
        setMinMaxRange();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        FontMetrics fm = g2d.getFontMetrics();
        double insets = fm.getHeight() + radius;

        for (GraphPoint gp : points) {
            paintPoint(g2d, gp, insets);
        }

        for (GraphEdge ed : edges) {
            ArrayList<GraphPoint> p = ed.getPoints();
            paintLine(g2d,p.get(0),p.get(1),insets,ed.getWeight());
        }

        g2d.dispose();
    }

    private void paintLine(Graphics2D g2d, GraphPoint from, GraphPoint to, double insets, String weight) {
        Point2D fromPoint = translate(from, insets);
        Point2D toPoint = translate(to, insets);
        g2d.setColor(Color.RED);
        g2d.draw(new Line2D.Double(fromPoint, toPoint));
        drawArrowHead(g2d, toPoint, fromPoint, Color.WHITE);
        StringWeight(g2d, weight, to, from, insets);
    }

    private void StringWeight(Graphics2D g2d, String weight, GraphPoint to, GraphPoint from, double insets) {
        Point2D translated = translate(from, insets);
        Point2D translated2 = translate(to, insets);

        double xPos = translated.getX();
        double yPos = translated.getY();

        double xPos2 = translated2.getX();
        double yPos2 = translated2.getY();

        double m_Segment = (yPos2-yPos)/(xPos2-xPos);


        double x_center = (xPos + xPos2) / 2;
        double y_center = (yPos + (yPos2)) / 2;
        g2d.setPaint(Color.black);
        if (weight.length() > 13) weight = weight.substring(0, weight.length() - 12);
        //g2d.drawString(weight, (float) x_center, (float) y_center);
    }

    private void paintPoint(Graphics2D g2d, GraphPoint gp, double insets) {
        Graphics2D g2 = (Graphics2D) g2d.create();

        Point2D translated = translate(gp, insets);

        double xPos = translated.getX();
        double yPos = translated.getY();

        double offset = radius;

        g2.translate(xPos - offset, yPos - offset);
        g2.setPaint(Color.blue);
        g2.fill(new Ellipse2D.Double(0, 0, offset * 2, offset * 2));

        FontMetrics fm = g2d.getFontMetrics();
        String text = gp.getId();
        double x = xPos - (fm.stringWidth(text) / 2);
        double y = (yPos - radius - fm.getHeight()) + fm.getAscent();
        g2d.setPaint(Color.black);
        g2d.drawString(text, (float)x, (float)y);

        g2.dispose();
    }
    private void drawArrowHead(Graphics2D g2, Point2D tip, Point2D tail, Color color) {
        int ArrowSize = 10;
        g2.setPaint(color);
        double dy = tip.getY() - tail.getY();
        double dx = tip.getX() - tail.getX();
        double theta = Math.atan2(dy, dx);
        double x, y, rho = theta + Phi;
        for (int j = 0; j < 2; j++) {
            x = (tip.getX() - ArrowSize * Math.cos(rho));
            y = (tip.getY() - ArrowSize * Math.sin(rho));
            g2.draw(new Line2D.Double(tip.getX(), tip.getY(), x, y));
            rho = theta - Phi;
        }
    }

    protected Point2D translate(GraphPoint gp, double insets) {
        double xRange = maxRange.getX() - minRange.getX();
        double yRange = maxRange.getY() - minRange.getY();

        double offset = insets;
        double width = getWidth() - (offset * 2);
        double height = getHeight() - (offset * 2);

        double xScale = width / xRange;
        double yScale = height / yRange;

        Point2D original = gp.getPoint();

        double x = offset + ((original.getX() - minRange.getX()) * xScale);
        double y = offset + ((original.getY() - minRange.getY()) * yScale);

        System.out.println(gp.getId() + " " + x + " x " + y);

        return new Point2D.Double(x, y);
    }
    /*
     * the for loop is init all the edges existed in the graph and take each point already exists,
     * make a new arraylist of points and know to the correct edges between them.
     * TODO: need to make a arrow direction to the line, take it from the last edit in my project
     * */
    public void EdgeInit() {
        edges = new ArrayList<>();
        for (int i = 0; i < graph.nodeSize(); i++) {
            for (int j = 0; j < graph.nodeSize(); j++) {
                if (i == j) continue;
                ArrayList<GraphPoint> temp = new ArrayList<>();
                if (graph.getEdge(i, j) != null) {
                    temp.add(new GraphPoint(String.valueOf(graph.getNode(graph.getEdge(i,j).getSrc()).getKey()),new Point2D.Double(graph.getNode(graph.getEdge(i, j).getSrc()).getLocation().x(), graph.getNode(graph.getEdge(i, j).getSrc()).getLocation().y())));
                    temp.add(new GraphPoint(String.valueOf(graph.getNode(graph.getEdge(i,j).getDest()).getKey()),new Point2D.Double(graph.getNode(graph.getEdge(i, j).getDest()).getLocation().x(), graph.getNode(graph.getEdge(i, j).getDest()).getLocation().y())));
                    edges.add(new GraphEdge(String.valueOf(graph.getEdge(i, j).getWeight()), temp));
                }
            }
        }
    }
    /*
     * the for loop is init all the points in the graph.
     * By given a name of the vertex and the positions x,y.
     * */
    public void pointInit(){
        for (int i = 0; i < graph.nodeSize(); i++) {
            NodeData curr = graph.getNode(i);
            if (curr != null) {
                String currKey = String.valueOf(graph.getNode(i).getKey());
                double currPosX = graph.getNode(i).getLocation().x();
                double currPosY = graph.getNode(i).getLocation().y();
                points.add(new GraphPoint(currKey, new Point2D.Double(currPosX, currPosY)));
            }
        }
    }
    /*
     * search if the point is equal to the string id  and get the point from the points objects.
     * draw each line have edges.
     */
    public void setMinMaxRange(){

        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;

        for (GraphPoint gp : points) {
            minX = Math.min(minX, gp.getPoint().getX());
            maxX = Math.max(maxX, gp.getPoint().getX());
            minY = Math.min(minY, gp.getPoint().getY());
            maxY = Math.max(maxY, gp.getPoint().getY());
        }

        minRange = new Point2D.Double(minX, minY);
        maxRange = new Point2D.Double(maxX, maxY);
    }
}



