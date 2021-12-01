package GraphGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Vertex_UI  extends JFrame implements ActionListener {
    private JButton Add;
    private JButton Cancel;
    private JPanel VertexUI;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    public Vertex_UI(){
        this.setContentPane(VertexUI);
        //this.setPreferredSize(new Dimension(500,500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
        this.pack();
        this.setTitle("Vertex Editor"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        Cancel.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Cancel){dispose();}

    }
    public static void main(String[] args) {
        Vertex_UI frame = new Vertex_UI();
    }
}
