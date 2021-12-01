package GraphGui;

import api.DirectedWeightedGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

/*
   Note: add a logo.icon!!!!!!
*/
public class FrameGraph extends JFrame implements ActionListener {

    private DirectedWeightedGraph graph;
    private PanelGraph panel;
    private JMenuBar menuBar;
    private JMenuItem loadItem, saveItem,exitItem, shortestPathMenu, isConnectedMenu, tspMenu, aboutMenu;
    private JMenu fileMenu, runMenu, editMenu,  helpMenu;
    private ImageIcon loadIcon, saveIcon, exitIcon, gitIcon;
    private Image main_menuBar;

    public FrameGraph() { // get here a graph.
        this.panel = new PanelGraph();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
        this.add(panel);
        this.pack();
        this.setTitle("Directed Weighted Graph by Gal & Amir"); // title
        this.setResizable(false); // prevent this to resize


        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        runMenu = new JMenu("Run");
        helpMenu = new JMenu("Help");


        loadItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");


        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        /*
            Algorithms belongs to the run menu
         */
        shortestPathMenu = new JMenuItem("ShortestPath");
        isConnectedMenu = new JMenuItem("IsConnected");
        tspMenu = new JMenuItem("TSP");




        runMenu.add(shortestPathMenu);
        runMenu.add(isConnectedMenu);
        runMenu.add(tspMenu);

        aboutMenu = new JMenuItem("About");
        helpMenu.add(aboutMenu);

        loadIcon = new ImageIcon("OOP-Ex2\\src\\GraphGui\\Icons\\file_upload_icon.png");
        saveIcon = new ImageIcon("OOP-Ex2\\src\\GraphGui\\Icons\\file_save_icon.png");
        exitIcon = new ImageIcon("OOP-Ex2\\src\\GraphGui\\Icons\\file_exit_icon.png");
        gitIcon = new ImageIcon("OOP-Ex2\\src\\GraphGui\\Icons\\git.png");
        main_menuBar = Toolkit.getDefaultToolkit().getImage("OOP-Ex2\\src\\GraphGui\\Icons\\logo.png");

        this.setIconImage(main_menuBar);
        loadItem.setIcon(loadIcon);
        saveItem.setIcon(saveIcon);
        exitItem.setIcon(exitIcon);
        aboutMenu.setIcon(gitIcon);

        /**
         *  Actions when pressed:
         */
        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        helpMenu.addActionListener(this);
        aboutMenu.addActionListener(this);

        /**
         * Keyboard shortcuts:
         */
        fileMenu.setMnemonic(KeyEvent.VK_F);
        loadItem.setMnemonic(KeyEvent.VK_F1);
        saveItem.setMnemonic(KeyEvent.VK_S);
        exitItem.setMnemonic(KeyEvent.VK_DELETE); //pressing delete will close the program.
        helpMenu.setMnemonic(KeyEvent.VK_H);


        /**
         * add each menu to the menu bar.
         */
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(runMenu);
        menuBar.add(helpMenu);










        this.setJMenuBar(menuBar);

        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loadItem ){
            System.out.println("Sssss");
        }
        if (e.getSource() == saveItem){
            System.out.println("Ssfsfsfsffs");
        }
        if(e.getSource() == exitItem){ System.exit(0);}
        if(e.getSource() == aboutMenu) {
            System.out.println("Ssdsdsdsdsdss - -- - -- ");openWebPage("https://github.com/GalKoaz/OOP-Ex2/");}




    }

    public void openWebPage(String url){
        try{
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void main(String[] args) {
        new FrameGraph();
    }
}
