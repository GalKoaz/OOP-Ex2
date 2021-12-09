package GraphGui;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.DirectedWeightedGraphAlgorithmsImpl;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Dijkstra extends JFrame implements ActionListener {
    private JButton cancel;
    private JButton OK;
    private JTextField src_node_id;
    private JTextField dest_node_id;
    private JPanel Dijkstra;
    private PanelGraph panel;
    private DirectedWeightedGraph graph;
    private DirectedWeightedGraphAlgorithms algo;
    private FrameGraph frame;
    private Timer timer;
    private int delay = 1000;
    private int cnt = 0;


    public Dijkstra(DirectedWeightedGraph graph, FrameGraph frame, PanelGraph panel) {
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\GraphGui\\Icons\\logo.png");
        this.setIconImage(icon);
        this.setContentPane(Dijkstra);
        this.graph = graph;
        this.frame = frame;
        this.panel = panel;
        this.algo = new DirectedWeightedGraphAlgorithmsImpl();
        this.algo.init(graph);
        centreWindow(this);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // nothing doing when clicking the exit button.
        this.pack();
        this.setTitle("Dijkstra's Algorithm - shortest path"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        cancel.addActionListener(this);
        OK.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cancel) {this.dispose();}

        if (e.getSource() == OK) {
            int src = Integer.parseInt(src_node_id.getText());
            int dest = Integer.parseInt(dest_node_id.getText());
            if (graph.getNode(src) == null || graph.getNode(dest) == null) {
                this.dispose();
                new Invalid_Vertex_UI();
            }
            ArrayList<NodeData> optPath = (ArrayList<NodeData>) algo.shortestPath(src,dest);
            int optPathLentgh = optPath.size();
            this.dispose();
            timer = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cnt++;
                    if (cnt == optPathLentgh-1){timer.stop();}
                    drawOptPath(src,dest, cnt, optPath);
                 }
            });
            timer.start();
            }
        }


    public void drawOptPath(int src, int dest, int cnt, ArrayList<NodeData>Path){
         int prevNode = Path.get(cnt-1).getKey();
         int curr = Path.get(cnt).getKey();
         if (graph.getEdge(curr,prevNode)!=null){
             panel.setEdgeColor(curr,prevNode,Color.green, frame.getBackground());
         }
         if (prevNode == src){
             panel.setPointColor(prevNode,Color.green, Color.green);
             panel.setPointColor(curr,Color.lightGray,Color.green);
             panel.setEdgeColor(prevNode,curr,Color.green,Color.green);

         }
         else if (curr == dest){
             panel.setPointColor(curr,Color.green,Color.green);
             panel.setPointColor(prevNode,Color.lightGray,Color.green);
             panel.setEdgeColor(prevNode,curr,Color.green,Color.green);
         }
         else{
             panel.setPointColor(curr,Color.darkGray,Color.green);
             panel.setPointColor(prevNode,Color.lightGray,Color.green);
             panel.setEdgeColor(prevNode,curr,Color.green,Color.green);
         }
        panel.repaint();
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
