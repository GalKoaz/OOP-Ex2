package GraphGui;

import api.DirectedWeightedGraph;
import api.GeoLocationImpl;
import api.NodeDataImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vertex_UI_add extends JFrame implements ActionListener {
    private JButton Add;
    private JButton Cancel;
    private JPanel VertexUI;
    private JTextField node_id;
    private JTextField positionX;
    private JTextField positionY;
    private DirectedWeightedGraph graph;
    private FrameGraph frame;


    public Vertex_UI_add(DirectedWeightedGraph graph, FrameGraph frame){
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\GraphGui\\Icons\\logo.png");
        this.setIconImage(icon);
        this.setContentPane(VertexUI);
        this.graph = graph;
        this.frame = frame;
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        centreWindow(this);
        this.pack();
        this.setTitle("Vertex Editor"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        Cancel.addActionListener(this);
        Add.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Cancel){this.dispose();}

        if (e.getSource() == Add){ addVertex();}

    }

    public void addVertex(){

        int key = Integer.parseInt(node_id.getText());
        double posX = Double.parseDouble(positionX.getText());
        double posy = Double.parseDouble(positionY.getText());
        System.out.println("id: "+key + "\n" + "pos x: "+posX+","+"\npos y: "+posy);
        String info = "id:"+key + "\n" + "pos:"+posX+","+posy+",0.0";
        graph.addNode(new NodeDataImpl(key, 0,info,0,new GeoLocationImpl(posX,posy,0.0)));
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
