package GraphGui;

import api.DirectedWeightedGraph;
import api.GeoLocationImpl;
import api.NodeDataImpl;

import javax.swing.*;
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

    public Vertex_UI_add(){
        this.setContentPane(VertexUI);
        //this.setPreferredSize(new Dimension(500,500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
        this.pack();
        this.setTitle("Vertex Editor"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        Cancel.addActionListener(this);
        Add.addActionListener(this);
    }
    public Vertex_UI_add(DirectedWeightedGraph graph, FrameGraph frame){
        this.setContentPane(VertexUI);
        this.graph = graph;
        this.frame = frame;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
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

        int id = Integer.parseInt(node_id.getText());
        double posX = Double.parseDouble(positionX.getText());
        double posy = Double.parseDouble(positionY.getText());
        System.out.println("id: "+id + "\n" + "pos x: "+posX+","+"\npos y: "+posy);
        String info = "id:"+id + "\n" + "pos:"+posX+","+posy+",0.0";
        graph.addNode(new NodeDataImpl(id, 0,info,0,new GeoLocationImpl(posX,posy,0.0)));
        this.dispose();
        frame.dispose();
        new FrameGraph(graph);
    }

    public static void main(String[] args) {new Vertex_UI_add();
    }
}
