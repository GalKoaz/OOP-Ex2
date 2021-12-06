package GraphGui;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithmsImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TSP extends JFrame implements ActionListener {
    private JButton showButton;
    private JButton cancelButton;
    private JPanel TSP;
    private FrameGraph frame;
    private PanelGraph panel;
    private DirectedWeightedGraphAlgorithmsImpl algo;
    private DirectedWeightedGraph graph;

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
        if (e.getSource() == showButton){}
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
