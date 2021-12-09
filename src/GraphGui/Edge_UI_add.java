package GraphGui;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Edge_UI_add extends JFrame implements ActionListener {
    private JButton Add;
    private JButton Cancel;
    private JPanel Edge_UI_add;
    private JTextField src_vertex_id;
    private JTextField dest_vertex_id;
    private JTextField edge_weight;
    private JLabel Edge_Insertion;
    private DirectedWeightedGraph graph;
    private FrameGraph frame;



    public Edge_UI_add(DirectedWeightedGraph graph, FrameGraph frame){
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\GraphGui\\Icons\\logo.png");
        this.setIconImage(icon);
        this.frame = frame;
        this.setContentPane(Edge_UI_add);
        this.graph = graph;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        centreWindow(this);
        this.pack();
        this.setTitle("Edge Editor"); // title
        Edge_Insertion.setBounds(0,0,400,1400);
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
