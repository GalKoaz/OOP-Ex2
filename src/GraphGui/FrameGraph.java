package GraphGui;

import api.DirectedWeightedGraph;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/*
   Note: add a logo.icon!!!!!!
*/
public class FrameGraph extends JFrame implements ActionListener {

    private DirectedWeightedGraph graph;
    private PanelGraph panel;
    private JMenuBar menuBar;
    private JMenuItem saveItem,exitItem, shortestPathMenu, isConnectedMenu, tspMenu, aboutMenu;
    private JMenu fileMenu, runMenu, editMenu,  helpMenu, loadMenu;
    private ImageIcon loadIcon, saveIcon, exitIcon, gitIcon;
    private Image main_menuBar;
    private JMenuItem loadFile, G1, G2, G3;
    private JMenuItem edgeItem, vertexItem;
    private File jsonFileSelected;


    public FrameGraph(DirectedWeightedGraph graph) { // get here a graph.

        this.panel = new PanelGraph(graph);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
        this.add(panel);
        this.pack();
        this.setTitle("Directed Weighted Graph by Gal & Amir"); // title
        this.setResizable(true); // prevent this to resize


        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        runMenu = new JMenu("Run");
        helpMenu = new JMenu("Help");


        loadMenu = new JMenu("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");


        fileMenu.add(loadMenu);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);


        /**
         * sub files to load initialization:
         */
        G1 = new JMenuItem("G1.json");
        G2 = new JMenuItem("G2.json");
        G3 = new JMenuItem("G3.json");
        loadFile = new JMenuItem("Select Json File");

        loadMenu.add(G1);
        loadMenu.add(G2);
        loadMenu.add(G3);
        loadMenu.add(loadFile);



        G1.addActionListener(this);
        G2.addActionListener(this);
        G3.addActionListener(this);
        loadFile.addActionListener(this);



        /*
         * Algorithm which belongs to the run menu
         */
        shortestPathMenu = new JMenuItem("ShortestPath");
        isConnectedMenu = new JMenuItem("IsConnected");
        tspMenu = new JMenuItem("TSP");


        runMenu.add(shortestPathMenu);
        runMenu.add(isConnectedMenu);
        runMenu.add(tspMenu);

        aboutMenu = new JMenuItem("About");
        helpMenu.add(aboutMenu);


        edgeItem = new JMenuItem("Edge");
        vertexItem = new JMenuItem("Vertex");
        editMenu.add(edgeItem);
        editMenu.add(vertexItem);


        /**
         * Adding icons:
         */
        loadIcon = new ImageIcon("OOP-Ex2\\src\\GraphGui\\Icons\\file_upload_icon.png");
        saveIcon = new ImageIcon("OOP-Ex2\\src\\GraphGui\\Icons\\file_save_icon.png");
        exitIcon = new ImageIcon("OOP-Ex2\\src\\GraphGui\\Icons\\file_exit_icon.png");
        gitIcon = new ImageIcon("OOP-Ex2\\src\\GraphGui\\Icons\\git.png");
        main_menuBar = Toolkit.getDefaultToolkit().getImage("OOP-Ex2\\src\\GraphGui\\Icons\\logo.png");

        this.setIconImage(main_menuBar);
        loadMenu.setIcon(loadIcon);
        saveItem.setIcon(saveIcon);
        exitItem.setIcon(exitIcon);
        aboutMenu.setIcon(gitIcon);

        /**
         *  Actions when pressed:
         */
        loadMenu.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        helpMenu.addActionListener(this);
        aboutMenu.addActionListener(this);
        edgeItem.addActionListener(this);
        vertexItem.addActionListener(this);
        /**
         * Keyboard shortcuts:
         */
        fileMenu.setMnemonic(KeyEvent.VK_F);
        loadMenu.setMnemonic(KeyEvent.VK_F1);
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

        if (e.getSource() == loadMenu ){
            System.out.println("Sssss");
        }
        if (e.getSource() == saveItem){
            System.out.println("Ssfsfsfsffs");
        }
        if(e.getSource() == exitItem){ System.exit(0);}

        if(e.getSource() == aboutMenu) {
            openWebPage("https://github.com/GalKoaz/OOP-Ex2/");
        }

        if (e.getSource() == G1){ }

        if (e.getSource() == G2){}

        if (e.getSource() == G3) {


        }

        if (e.getSource() == loadFile){
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("json","json");
            fileChooser.setFileFilter(filter);
            int response = fileChooser.showOpenDialog(null); //select json file to open.

            if (response == JFileChooser.APPROVE_OPTION){
                jsonFileSelected = new File(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }

        /**
         * Vertex -> add a vertex, remove vertex
         */
        if (e.getSource() == vertexItem){
            System.out.println("sdsddsds");
            Vertex_UI vertex = new Vertex_UI();

        }
        /**
         * Edge -> add a vertex, remove vertex
         */
        if (e.getSource() == edgeItem){
            System.out.println("SDsdsdsds");
            Edge_UI edge = new Edge_UI();

        }


    }

    public void openWebPage(String url){
        try{
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }







}
