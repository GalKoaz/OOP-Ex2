package GraphGui;

import api.DirectedWeightedGraph;
import api.GeoLocation;
import api.NodeData;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class VertexTable extends JFrame implements ActionListener{
    private JPanel panel;
    private JButton closeButton;
    private JTable nodeTable;
    private DirectedWeightedGraph graph;
    private Object[][] data;
    private String[] columnNames;
    private JScrollPane scrollPane;

    public VertexTable(DirectedWeightedGraph graph){
        Image icon = Toolkit.getDefaultToolkit().getImage("src\\GraphGui\\Icons\\logo.png");
        this.setIconImage(icon);
        this.setContentPane(panel);
        this.graph = graph;
        setLayout(new FlowLayout());
        this.columnNames = new String[]{"ID", "x","y","z"};
        this.data = new Object[graph.nodeSize()][4];
        insert();
        this.nodeTable = new JTable(data, columnNames);
        nodeTable.setPreferredScrollableViewportSize(new Dimension(550,300));
        this.scrollPane = new JScrollPane(nodeTable);
        centreWindow(this);
        this.add(scrollPane);
        this.add(closeButton);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setSize(600,400);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("Edges panel");
        closeButton.addActionListener(this);
        nodeTable.setDefaultEditor(Object.class, null); //make the table's cells not editable.
        setColumnValuesCenter();
    }
    public void insert(){
        Iterator<NodeData> nodes = graph.nodeIter();
        int m = 0;
        while(nodes.hasNext()){
            NodeData curr = nodes.next();
            int node_id = curr.getKey();
            GeoLocation pos = curr.getLocation();
            data[m][0] = node_id; data[m][1] = pos.x();  data[m][2] = pos.y(); data[m++][3] = pos.z();
        }
    }


    /**
     * This method centre the new window opening.
     * @param frame the frame to set its location.
     */
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 3.2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 3.2);
        frame.setLocation(x, y);
    }

    /**
     * This method puts the Column values to be located at the center.
     */
    public void setColumnValuesCenter(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        nodeTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        nodeTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        nodeTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        nodeTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton){this.dispose();}
    }
}
