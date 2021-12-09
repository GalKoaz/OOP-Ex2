package GraphGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TSPResponse extends JFrame implements ActionListener {
    private JPanel ConnectedResponse;
    private JButton closeButton;
    private JLabel label;
    private ImageIcon icon;
    private Image icon_MenuBar;


    public TSPResponse(){
        this.setContentPane(ConnectedResponse);
        icon = new ImageIcon("src\\GraphGui\\Icons\\green_v.png");
        icon_MenuBar = Toolkit.getDefaultToolkit().getImage("src\\GraphGui\\Icons\\ExclamationCircle.png");
        this.setIconImage(icon_MenuBar);
        label.setIcon(icon);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        centreWindow(this);
        this.pack();
        this.setTitle("Graph Connected"); // title
        this.setResizable(false); // prevent this to resize
        this.setVisible(true);
        closeButton.addActionListener(this);
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
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) {
            this.dispose();
        }
    }
}
