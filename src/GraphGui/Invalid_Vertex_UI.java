package GraphGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Invalid_Vertex_UI extends JFrame implements ActionListener {
    private JButton close;
    private JPanel invalid_vertex_pane;
    private JLabel label;
    private ImageIcon ERROR;
    private Image ERROR_MenuBar;


    public Invalid_Vertex_UI(){
        ERROR = new ImageIcon("OOP-Ex2\\src\\GraphGui\\Icons\\ERROR.png");
        ERROR_MenuBar = Toolkit.getDefaultToolkit().getImage("OOP-Ex2\\src\\GraphGui\\Icons\\ERROR.png");
        this.setIconImage(ERROR_MenuBar);
        label.setIcon(ERROR);
        this.setContentPane(invalid_vertex_pane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
        this.pack();
        this.setTitle("Invalid Vertex Error"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        close.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == close) {
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new Invalid_Vertex_UI();
    }
}
