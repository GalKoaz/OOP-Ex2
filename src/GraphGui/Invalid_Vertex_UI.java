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
        ERROR = new ImageIcon("src\\GraphGui\\Icons\\ERROR.png");
        ERROR_MenuBar = Toolkit.getDefaultToolkit().getImage("src\\GraphGui\\Icons\\ERROR.png");
        this.setIconImage(ERROR_MenuBar);
        label.setIcon(ERROR);
        this.setContentPane(invalid_vertex_pane);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        centreWindow(this);
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
