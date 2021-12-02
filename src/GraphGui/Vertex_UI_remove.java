package GraphGui;

import api.DirectedWeightedGraph;
import api.NodeData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vertex_UI_remove extends JFrame implements ActionListener {
    private JTextField node_id;
    private JButton Add;
    private JButton Cancel;
    private JPanel remove_vertex;
    private DirectedWeightedGraph graph;
    private FrameGraph frame;

    public Vertex_UI_remove() {
        this.setContentPane(remove_vertex);

        //this.setPreferredSize(new Dimension(500,500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
        this.pack();
        this.setTitle("Vertex Editor"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        Add.addActionListener(this);
    }

    public Vertex_UI_remove(DirectedWeightedGraph graph, FrameGraph frame) {
        this.setContentPane(remove_vertex);
        this.graph = graph;
        this.frame = frame;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
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

    public static void main(String[] args) {new Vertex_UI_remove();}
}