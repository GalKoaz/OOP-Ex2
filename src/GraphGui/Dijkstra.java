package GraphGui;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dijkstra extends JFrame implements ActionListener {
    private JButton cancel;
    private JButton OK;
    private JTextField src_node_id;
    private JTextField dest_node_id;
    private JPanel Dijkstra;
    private PanelGraph pane;
    private DirectedWeightedGraph graph;
    private FrameGraph frame;
    private Timer timer;

    public Dijkstra()  {
        this.setContentPane(Dijkstra);

        //this.setPreferredSize(new Dimension(500,500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
        this.pack();
        this.setTitle("Vertex Editor"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        this.timer = new Timer(100,null);
        cancel.addActionListener(this);
        OK.addActionListener(this);
    }

    public Dijkstra(DirectedWeightedGraph graph, FrameGraph frame, PanelGraph pane) {
        this.setContentPane(Dijkstra);
        this.graph = graph;
        this.frame = frame;
        this.pane = pane;
        this.timer = new Timer(100,null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
        this.pack();
        this.setTitle("Vertex Editor"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        cancel.addActionListener(this);
        OK.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            this.dispose();
        }

        if (e.getSource() == OK) {
            int src = Integer.parseInt(src_node_id.getText());
            int dest = Integer.parseInt(dest_node_id.getText());
            if (graph.getNode(src) == null || graph.getNode(dest) == null){
                new Invalid_Vertex_UI();
            }
            else{

            }
        }
    }


    public static void main(String[] args) {
        new Dijkstra();
    }
}
