package GraphGui;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.DirectedWeightedGraphAlgorithmsImpl;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class FrameGraph extends JFrame implements ActionListener {

    private DirectedWeightedGraph graph, copyGraph;
    private DirectedWeightedGraphAlgorithms copyGraphAtBeginning;
    private PanelGraph panel;
    private JMenuBar menuBar;
    private JMenuItem saveItem,exitItem, shortestPathMenu, isConnectedMenu, centerMenu ,tspMenu, aboutMenu;
    private JMenu fileMenu, runMenu, editMenu,  helpMenu, loadMenu;
    private ImageIcon loadIcon, saveIcon, exitIcon, gitIcon;
    private Image main_menuBar;
    private JMenuItem loadFile, G1, G2, G3;
    private JMenu edgeItem, vertexItem;
    private JMenuItem clearItem, addEdgeItem, removeEdgeItem, addVertexItem, removeVertexItem;
    private File jsonFileSelected;


    public FrameGraph(DirectedWeightedGraph graph) { // get here a graph.
        this.graph = graph;
        this.panel = new PanelGraph(graph);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit the app
        centreWindow(this);
        this.add(panel);
        this.pack();
        this.setTitle("Directed Weighted Graph by Gal & Amir"); // title
        this.setResizable(true); // prevent this to resize
        this.copyGraphAtBeginning = new DirectedWeightedGraphAlgorithmsImpl();
        this.copyGraphAtBeginning.init(graph);
        this.copyGraph = copyGraphAtBeginning.copy();


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
        centerMenu = new JMenuItem("Center");
        tspMenu = new JMenuItem("TSP");


        runMenu.add(shortestPathMenu);
        runMenu.add(isConnectedMenu);
        runMenu.add(centerMenu);
        runMenu.add(tspMenu);


        aboutMenu = new JMenuItem("About");
        helpMenu.add(aboutMenu);

        /**
         * Belongs to the edit menu of the menu bar.
         */
        edgeItem = new JMenu("Edge");
        vertexItem = new JMenu("Vertex");
        clearItem = new JMenuItem("clear");
        editMenu.add(edgeItem);
        editMenu.add(vertexItem);
        editMenu.add(clearItem);
        addEdgeItem = new JMenuItem("add");
        removeEdgeItem = new JMenuItem("remove");
        addVertexItem = new JMenuItem("add");
        removeVertexItem = new JMenuItem("remove");
        edgeItem.add(addEdgeItem);
        edgeItem.add(removeEdgeItem);
        vertexItem.add(addVertexItem);
        vertexItem.add(removeVertexItem);
        clearItem.addActionListener(this);
        removeVertexItem.addActionListener(this);
        addVertexItem.addActionListener(this);
        addEdgeItem.addActionListener(this);
        removeEdgeItem.addActionListener(this);

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
        shortestPathMenu.addActionListener(this);
        isConnectedMenu.addActionListener(this);
        centerMenu.addActionListener(this);
        tspMenu.addActionListener(this);

        /**
         * Keyboard shortcuts:
         */
        fileMenu.setMnemonic(KeyEvent.VK_F);
        loadMenu.setMnemonic(KeyEvent.VK_F1);
        saveItem.setMnemonic(KeyEvent.VK_S);
        exitItem.setMnemonic(KeyEvent.VK_DELETE); //pressing delete will close the program.
        helpMenu.setMnemonic(KeyEvent.VK_H);



        G1.addActionListener(this);
        G2.addActionListener(this);
        G3.addActionListener(this);
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

        if (e.getSource() == G1){
            jsonFileSelected = new File("OOP-Ex2\\data\\G1.json");
            DirectedWeightedGraphAlgorithms g = new DirectedWeightedGraphAlgorithmsImpl();
            g.load(jsonFileSelected.getPath());
            this.dispose();
            this.setVisible(false);
            new FrameGraph(g.getGraph());
        }

        if (e.getSource() == G2){
            jsonFileSelected = new File("OOP-Ex2\\data\\G2.json");
            DirectedWeightedGraphAlgorithms g = new DirectedWeightedGraphAlgorithmsImpl();
            g.load(jsonFileSelected.getPath());
            this.dispose();
            this.setVisible(false);
            new FrameGraph(g.getGraph());
        }

        if (e.getSource() == G3) {
            jsonFileSelected = new File("OOP-Ex2\\data\\G3.json");
            DirectedWeightedGraphAlgorithms g = new DirectedWeightedGraphAlgorithmsImpl();
            g.load(jsonFileSelected.getPath());
            this.dispose();
            this.setVisible(false);
            new FrameGraph(g.getGraph());
        }

        if (e.getSource() == loadFile){
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("json","json");
            fileChooser.setFileFilter(filter);
            int response = fileChooser.showOpenDialog(null); //select json file to open.

            if (response == JFileChooser.APPROVE_OPTION){
                jsonFileSelected = new File(fileChooser.getSelectedFile().getAbsolutePath());
                DirectedWeightedGraphAlgorithms g = new DirectedWeightedGraphAlgorithmsImpl();
                g.load(jsonFileSelected.getAbsolutePath());
                this.dispose();
                new FrameGraph(g.getGraph());
            }
        }
        if (e.getSource() == saveItem) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("json","json");
            fileChooser.setFileFilter(filter);
            int response = fileChooser.showOpenDialog(null); //select json file to open.

            if (response == JFileChooser.APPROVE_OPTION){
                jsonFileSelected = new File(fileChooser.getSelectedFile().getAbsolutePath());
                DirectedWeightedGraphAlgorithms g = new DirectedWeightedGraphAlgorithmsImpl();
                g.init(graph);
                g.save(jsonFileSelected.getAbsolutePath());

            }

        }
        /**
         * Vertex -> add a vertex, remove vertex
         */
        if (e.getSource() == addVertexItem){new Vertex_UI_add(graph, this);}
        /**
         * Edge -> add a vertex, remove vertex
         */
        if (e.getSource() == removeVertexItem){ new Vertex_UI_remove(graph,this);}

        if (e.getSource() == addEdgeItem){ new Edge_UI_add(graph,this);}

        if (e.getSource() == removeEdgeItem) {new Edge_UI_remove(graph,this);}
        /**
         * complete..........
         */

        /**
         * algorithms:
         */
        if(e.getSource() == shortestPathMenu){new Dijkstra(graph,this,panel);}

        if (e.getSource() == isConnectedMenu){}

        if (e.getSource() == centerMenu){new Center(graph,this,panel);}

        if (e.getSource() == tspMenu) {}

        if (e.getSource() == clearItem) {
            this.dispose();
            new FrameGraph(copyGraph);
        }
    }

    /**
     * Opening a web page with the given url
     * @param url the url of the page
     */
    public void openWebPage(String url){
        try{
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    /**
     * This method centre the new window opening.
     * @param frame the frame to set its location.
     */
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 3.2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 5);
        frame.setLocation(x, y);
    }
}