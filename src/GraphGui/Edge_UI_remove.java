package GraphGui;

import api.DirectedWeightedGraph;
import api.EdgeData;

import javax.swing.*;
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

    public Edge_UI_remove(){
        this.setContentPane(Edge_UI_remove);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
        this.pack();
        this.setTitle("Edge Editor"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        Cancel.addActionListener(this);
    }
    public Edge_UI_remove(DirectedWeightedGraph graph, FrameGraph frame){
        this.setContentPane(Edge_UI_remove);
        this.graph = graph;
        this.frame = frame;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
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

    public static void main(String[] args) {
        new Edge_UI_remove();
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
}
