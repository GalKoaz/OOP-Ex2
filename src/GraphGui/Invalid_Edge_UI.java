package GraphGui;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Invalid_Edge_UI extends JFrame implements ActionListener {
    private ImageIcon ERROR;
    private Image ERROR_MenuBar;

    private JButton close;
    private JPanel invalid_edge_pane;
    private JLabel label;

    public Invalid_Edge_UI(){

        this.setContentPane(invalid_edge_pane);
        ERROR = new ImageIcon("src\\GraphGui\\Icons\\ERROR.png");
        ERROR_MenuBar = Toolkit.getDefaultToolkit().getImage("src\\GraphGui\\Icons\\ERROR.png");
        this.setIconImage(ERROR_MenuBar);
        label.setIcon(ERROR);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        centreWindow(this);
        this.pack();
        this.setTitle("Invalid Edge Error"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        close.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == close){
            this.dispose();
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
