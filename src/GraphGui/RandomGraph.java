package GraphGui;

import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class RandomGraph extends JFrame implements ActionListener {
    private JButton createButton;
    private JButton cancelButton;
    private JTextField numOfVertices;
    private JPanel RandomGraph;
    private FrameGraph frame;
    private PanelGraph panel;
    private DirectedWeightedGraphAlgorithmsImpl algo;
    private DirectedWeightedGraph graph;
    private double EPSILON = .000000001;

    public RandomGraph(DirectedWeightedGraph graph, FrameGraph frame, PanelGraph panel) {
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\GraphGui\\Icons\\logo.png");
        this.setIconImage(icon);
        this.setContentPane(RandomGraph);
        this.graph = graph;
        this.frame = frame;
        this.panel = panel;
        centreWindow(this);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // nothing doing when clicking the exit button.
        this.pack();
        this.setTitle("Random Graph"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        cancelButton.addActionListener(this);
        createButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton){this.dispose();}

        if (e.getSource() == createButton){
            int num = Integer.parseInt(numOfVertices.getText());
            System.out.println(num);
            createRandomGraph(num);
            this.dispose();
        }
    }


    public void createRandomGraph(int num){
        DirectedWeightedGraph g = new DirectedWeightedGraphImpl(new HashMap<>(), new HashMap<>());
        for (int i = 0; i < num; i++) {
            System.out.println("node: " + i);
            int node_id = i;
            g.addNode(random_vertex_generator(node_id));
        }

        int edgesAmount = 20*num;

        for (int i = 0; i < edgesAmount; i++) {
            System.out.println("edge: "+i);
            EdgeData e = random_edge_generator(num);
            int src = e.getSrc();
            int dest = e.getDest();
            g.connect(src,dest,e.getWeight());
        }
        algo = new DirectedWeightedGraphAlgorithmsImpl();
        algo.init(g);
        algo.save(num+""+"Nodes.json");
        frame.dispose();
        new FrameGraph(g);
    }
    public NodeData random_vertex_generator(int key) {
        int tag = 1 + (int) (Math.random() * 10);
        double weight = Math.random() * 10;
        double x = 35 + Math.random() * (1-EPSILON), y = 32 + Math.random() * (1-EPSILON), z = 0.0;
        GeoLocation Node = new GeoLocationImpl(x, y, z);
        String info = "Key:" + key + "\n" + "Tag:" + tag + "\n" + "Weight" + weight + "\n" + "GeoLocation:" + Node;
        return new NodeDataImpl(key, tag, info, weight, Node);
    }


    public EdgeData random_edge_generator(int num) {
        //int[] srcDest = two_random_distinct_numbers(0, num - 1);
        int[] srcDest = two_random_numbers(0,num-1,num-1);
        int src = srcDest[0];
        int dest = srcDest[1];
        int tag = 1 + (int) (Math.random() * 10);
        double weight = Math.random() * 10;
        String info = "Src:" + src + "\n" + "Dest:" + dest + "\n" + "Tag" + tag + "\n" + "Weight:" + weight;
        return new EdgeDataImpl(src, dest, tag, weight, info);
    }

    /**
     * This method creates two unique/distinct random numbers in range: [0,max]
     * The advantage of it, is that the data structure can find the two distinct random numbers
     * very fast for large range.
     * @param Max the maximum number of the range.
     * @return two distinct random numbers of the range.
     */
    public int[] two_random_numbers(int a, int b, int Max){
        BitSet bs = new BitSet(Max);
        int cardinality = 0;
        while(cardinality < 2) {
            Random rand = new Random();
            int v = rand.nextInt(Max);
            if(!bs.get(v)) {
                bs.set(v);
                cardinality++;
            }
        }
        return bs.stream().toArray();
    }

    public int[] two_random_distinct_numbers(int a, int b) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = a; i < b; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return new int[]{numbers.get(0), numbers.get(1)};
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
