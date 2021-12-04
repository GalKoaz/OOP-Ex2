package GraphGui;

import api.DirectedWeightedGraph;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;

public class Dijkstra extends JFrame implements ActionListener {
    private JButton cancel;
    private JButton OK;
    private JTextField src_node_id;
    private JTextField dest_node_id;
    private JPanel Dijkstra;
    private PanelGraph panel;
    private DirectedWeightedGraph graph;
    private FrameGraph frame;
    private Timer timer;
    private int delay = 200;
    private int cnt = 0;

    public Dijkstra()  {
        this.setContentPane(Dijkstra);

        //this.setPreferredSize(new Dimension(500,500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
        this.pack();
        this.setTitle("Vertex Editor"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        this.timer = new Timer(10,null);
        cancel.addActionListener(this);
        OK.addActionListener(this);
    }

    public Dijkstra(DirectedWeightedGraph graph, FrameGraph frame, PanelGraph panel) {
        this.setContentPane(Dijkstra);
        this.graph = graph;
        this.frame = frame;
        this.panel = panel;
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
        System.out.println(cnt);
        cnt++;
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
                timer = new Timer(delay, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("sdsdsdsd");
                        if (cnt%2 == 0){
                             panel.setPointColor(0,Color.gray);
                             panel.repaint();
                        }
                        if (cnt%2 == 1){
                            panel.setPointColor(0,Color.blue);
                            panel.repaint();
                        }
                        cnt++;
                    }
                });

                timer.start();

            }
        }
    }


    public static void main(String[] args) {
        new Dijkstra();
    }
}
