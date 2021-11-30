package GraphGui;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class FrameGraph extends JFrame {
    private int Radius;
    private double Phi;
    private DirectedWeightedGraph graph;
    private PanelGraph panel;


    /*
     * add here a Graph From json loader.
     * */

    public FrameGraph(DirectedWeightedGraph graph) { // get here a graph.
        this.panel = new PanelGraph();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
        this.add(panel);
        this.pack();
        this.setTitle("Directed Weighted Graph by Gal & Amir"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        this.Radius = 4;
        this.Phi = Math.toRadians(40);
        this.graph = graph;
        /*
         * This Constructor get a graph, we take all the nodes and write the ID in each point in the graphics,
         * and we take all the Edges and make arrows with direction and weight.
         *
         *  */
    }

    /*
     * This function make a paint of all the Nodes and Edges in the directed weighted graph.
     * */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.blue);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*        Oval(g2,"0",250,100);
        Oval(g2,"0",500,400);
        Oval(g2,"0",30,30);
        Oval(g2,"0",60,90);*/
        for (int i = 0; i < graph.nodeSize(); i++) {
            Oval(g2, String.valueOf(graph.getNode(i).getKey()),(int)((graph.getNode(i).getLocation().x()-35)*1000),(int)((graph.getNode(i).getLocation().y()-32)*1000));
        }
/*        Oval(g2,"0",250,100);
        Oval(g2,"1",100,170);
        Oval(g2,"2",300,350);*/

/*        line(g2,"11.23",250,100,100,170);
        line(g2,"44.2",250,100,300,350);*/
    }

    private void drawArrowHead(Graphics2D g2, Point tip, Point tail, Color color) {
        int ArrowSize = 10;
        g2.setPaint(color);
        double dy = tip.y - tail.y;
        double dx = tip.x - tail.x;
        double theta = Math.atan2(dy, dx);
        double x, y, rho = theta + Phi;
        for (int j = 0; j < 2; j++) {
            x = tip.x - ArrowSize * Math.cos(rho);
            y = tip.y - ArrowSize * Math.sin(rho);
            g2.draw(new Line2D.Double(tip.x, tip.y, x, y));
            rho = theta - Phi;
        }
    }

    private void Oval(Graphics2D g2, String id, int locationX, int locationY) {
        AffineTransform old = g2.getTransform();
        g2.scale(4,4);
        g2.setPaint(Color.blue);
        g2.fillOval(locationX - Radius, locationY - Radius, Radius * 2, Radius * 2);
        g2.setPaint(Color.black);
        g2.drawString(id, locationX + Radius, locationY - Radius);
        g2.setTransform(old);
    }

    private void line(Graphics2D g2, String weight, int locationX, int locationY, int locationX2, int locationY2) {
        g2.setPaint(Color.red);
        Point start = new Point(locationX, locationY);
        Point end = new Point(locationX2, locationY2);
        g2.draw(new Line2D.Double(start, end));
        drawArrowHead(g2, start, end, Color.red);
        float center_point_x = (locationX2 + locationX) >> 1;
        float center_point_y = (locationY2 + locationY) >> 1;
        g2.setPaint(Color.black);
        g2.drawString(weight, center_point_x, center_point_y);
    }


}
