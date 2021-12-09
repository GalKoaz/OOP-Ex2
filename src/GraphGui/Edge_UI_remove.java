package GraphGui;

import api.DirectedWeightedGraph;
import api.EdgeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Edge_UI_remove extends JFrame implements ActionListener {
    private JButton remove;
    private JButton Cancel;
    private JTextField src_vertex_id;
    private JPanel Edge_UI_remove;
    private JTextField dest_vertex_id;
    private DirectedWeightedGraph graph;
    private FrameGraph frame;


    public Edge_UI_remove(DirectedWeightedGraph graph, FrameGraph frame){
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\GraphGui\\Icons\\logo.png");
        this.setIconImage(icon);
        this.setContentPane(Edge_UI_remove);
        this.graph = graph;
        this.frame = frame;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        centreWindow(this);
        this.pack();
        this.setTitle("Edge Editor"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        Cancel.addActionListener(this);
        remove.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Cancel){dispose();}
        if (e.getSource() == remove){remove();}
    }

    public void remove() {
        int src = Integer.parseInt(src_vertex_id.getText());
        int dest = Integer.parseInt(dest_vertex_id.getText());
        System.out.println("disconnects: " + src + " --> " + dest);
        EdgeData removedEdge = graph.removeEdge(src, dest);
        if (removedEdge != null) {
            this.dispose();
            frame.dispose();
            new FrameGraph(graph);
        }
        else{
            new Invalid_Edge_UI();
        }
    }

    /**
     * This method centre the new window opening.
     * @param frame the frame to set its location.
     */
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2.6);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2.6);
        frame.setLocation(x, y);
    }
}
