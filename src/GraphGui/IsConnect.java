package GraphGui;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithmsImpl;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class IsConnect extends JFrame implements ActionListener {
    private JButton showButton;
    private JButton cancelButton;
    private JPanel IsConnected;
    private FrameGraph frame;
    private PanelGraph panel;
    private DirectedWeightedGraphAlgorithmsImpl algo;
    private DirectedWeightedGraph graph;
    private Timer timer;
    private int delay = 10;
    private int cnt = 0;

    public IsConnect(DirectedWeightedGraph graph, FrameGraph frame, PanelGraph panel){
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\GraphGui\\Icons\\logo.png");
        this.setIconImage(icon);
        this.setContentPane(IsConnected);
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

    /**
     * The method wisely uses the timer, to represent more than one path,
     * with modulo of the updated path.
     */
    public void findOptimal(){
        ArrayList<Integer> nodes = new ArrayList<>();
        Iterator<NodeData> n = graph.nodeIter();
        while(n.hasNext()){nodes.add(n.next().getKey());}
        timer = new Timer(delay, new ActionListener() {

            int src = 0, dest = 0;
            ArrayList<NodeData> optPath = (ArrayList<NodeData>) algo.shortestPath(nodes.get(0), nodes.get(1));
            int optPathLentgh = optPath.size();
            @Override
            public void actionPerformed(ActionEvent e) {
                if (src == graph.nodeSize()-1){timer.stop(); new ConnectedResponse(); return;}
                if (src == dest) {
                    dest++;
                } else {
                    if (cnt % (optPathLentgh - 1) == 0 && cnt!=0) {
                        if (dest == graph.nodeSize()){src++;dest=0;}
                        optPath = (ArrayList<NodeData>) algo.shortestPath(nodes.get(src), nodes.get(dest));
                        optPathLentgh = optPath.size();
                        cnt=0;
                        dest++;
                    }
                    cnt++;
                    drawOptPath(src, dest, cnt, optPath);
                }
            }
        });
        timer.start();
    }


    public void drawOptPath(int src, int dest, int cnt, ArrayList<NodeData> optPath){
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

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == cancelButton){this.dispose();}
      if (e.getSource() == showButton){
          this.dispose();
          if (algo.isConnected()){
              findOptimal();
          }
          else{
            this.dispose();
            new NotConncetedResponse();
          }
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
