package GraphGui;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Invalid_Edge_UI  extends JFrame implements ActionListener {
    private ImageIcon ERROR;
    private Image ERROR_MenuBar;

    private JButton close;
    private JPanel invalid_edge_pane;
    private JLabel label;

    public Invalid_Edge_UI(){

        this.setContentPane(invalid_edge_pane);
        ERROR = new ImageIcon("OOP-Ex2\\src\\GraphGui\\Icons\\ERROR.png");
        ERROR_MenuBar = Toolkit.getDefaultToolkit().getImage("OOP-Ex2\\src\\GraphGui\\Icons\\ERROR.png");
        this.setIconImage(ERROR_MenuBar);
        label.setIcon(ERROR);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
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

    public static void main(String[] args) {
        new Invalid_Edge_UI();
    }
}
