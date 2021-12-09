package GraphGui;

import api.DirectedWeightedGraph;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vertex_UI_remove extends JFrame implements ActionListener {
    private JTextField node_id;
    private JButton Add;
    private JButton Cancel;
    private JPanel remove_vertex;
    private DirectedWeightedGraph graph;
    private FrameGraph frame;


    public Vertex_UI_remove(DirectedWeightedGraph graph, FrameGraph frame) {
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\GraphGui\\Icons\\logo.png");
        this.setIconImage(icon);
        this.setContentPane(remove_vertex);
        this.graph = graph;
        this.frame = frame;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        centreWindow(this);
        this.pack();
        this.setTitle("Vertex Editor"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        Cancel.addActionListener(this);
        Add.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Cancel) {
            this.dispose();
        }

        if (e.getSource() == Add) {
            removeVertex();
        }
    }

    public void removeVertex() {
        int key = Integer.parseInt(node_id.getText());
        NodeData removedNode = this.graph.removeNode(key);

        if (removedNode != null) {
            this.dispose();
            frame.dispose();
            new FrameGraph(this.graph);
        } else {
            new Invalid_Vertex_UI();
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