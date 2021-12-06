package GraphGui;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithmsImpl;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TSP extends JFrame implements ActionListener {
    private JButton showButton;
    private JButton cancelButton;
    private JPanel TSP;
    private FrameGraph frame;
    private PanelGraph panel;
    private DirectedWeightedGraphAlgorithmsImpl algo;
    private DirectedWeightedGraph graph;
    private Timer timer;
    private int delay = 200;
    private int cnt = 0;


    public TSP(DirectedWeightedGraph graph, FrameGraph frame, PanelGraph panel){
        Image icon = Toolkit.getDefaultToolkit().getImage("OOP-Ex2\\src\\GraphGui\\Icons\\logo.png");
        this.setIconImage(icon);
        this.setContentPane(TSP);
        this.graph = graph;
        this.frame = frame;
        this.panel = panel;
        this.algo = new DirectedWeightedGraphAlgorithmsImpl();
        this.algo.init(graph);
        centreWindow(this);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // nothing doing when clicking the exit button.
        this.pack();
        this.setTitle("Graph centre"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        cancelButton.addActionListener(this);
        showButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton){this.dispose();}
        if (e.getSource() == showButton){
            int src = 0;
            int dest = graph.nodeSize()-1;
            if (graph.getNode(src) == null || graph.getNode(dest) == null) {
                this.dispose();
                new Invalid_Vertex_UI();
            }
            ArrayList<NodeData> cities = new ArrayList<>();
            for (int v = src; v <= dest; v++){ cities.add(graph.getNode(v));}
            ArrayList<NodeData> Path = (ArrayList<NodeData>) algo.tsp(cities);
            int optPathLentgh = Path.size();
            this.dispose();
            timer = new Timer(delay, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cnt++;
                    if (cnt == optPathLentgh-1){timer.stop(); new TSPResponse();}
                    drawOptPath(src,dest, cnt, Path);
                }
            });
            timer.start();
        }
    }


    public void drawOptPath(int src, int dest, int cnt, ArrayList<NodeData>optPath){
        int prevNode = optPath.get(cnt-1).getKey();
        int curr = optPath.get(cnt).getKey();
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
