package GraphGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Edge_UI extends JFrame implements ActionListener {
    private JButton Add;
    private JButton Cancel;
    private JPanel EdgeUI;
    private JTextField textField1;


    public Edge_UI(){
        this.setContentPane(EdgeUI);
        //this.setPreferredSize(new Dimension(500,500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
        this.pack();
        this.setTitle("Edge Editor"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        Cancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() == Cancel){dispose();}

    }
    public static void main(String[] args) {
        Edge_UI frame = new Edge_UI();
    }
}
