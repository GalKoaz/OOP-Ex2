package GraphGui;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Edge_UI_add extends JFrame implements ActionListener {
    private JButton Add;
    private JButton Cancel;
    private JPanel EdgeUI;
    private JTextField src_vertex_id;
    private JTextField dest_vertex_id;
    private JTextField edge_weight;
    private DirectedWeightedGraph graph;
    private FrameGraph frame;
    private PanelGraph panel;
    public Edge_UI_add(){
        this.setContentPane(EdgeUI);
        //this.setPreferredSize(new Dimension(500,500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
        this.pack();
        this.setTitle("Edge Editor"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        Cancel.addActionListener(this);
        Add.addActionListener(this);
    }

    public Edge_UI_add(DirectedWeightedGraph graph, FrameGraph frame){
        this.frame = frame;
        this.setContentPane(EdgeUI);
        this.graph = graph;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
        this.pack();
        this.setTitle("Edge Editor"); // title
        this.setResizable(false); // resize allowed
        this.setVisible(true);
        Cancel.addActionListener(this);
        Add.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == Cancel){dispose();}

      if (e.getSource() == Add){addEdge();}


    }
    public static void main(String[] args) {
        new Edge_UI_add();
    }


    public void addEdge(){
        int src = Integer.parseInt(src_vertex_id.getText());
        int dest = Integer.parseInt(dest_vertex_id.getText());
        double weight = Double.parseDouble(edge_weight.getText());
        System.out.println("connects: "+src+" --> "+dest);
        graph.connect(src,dest,weight);
        this.dispose();
        frame.dispose();
        new FrameGraph(graph);
    }
}
