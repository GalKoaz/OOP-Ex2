package api;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

public class JSON_Operation {

    public String path;
    private ArrayList<String> Edges, Vertices;
    private ArrayList<EdgeData> initEdges;
    private ArrayList<NodeData> initVertices;


    /**
     * @param path
     */
    public JSON_Operation(String path) {
        this.path = path;
        this.Edges = new ArrayList<>();
        this.Vertices = new ArrayList<>();
    }

    /**
     * The function goes over each line. If the function encountered a line that contains "src",
     * then it knows that we reached a new edge, so the three lines included the current line
     * are the edges' details. Therefore, we want to merge those strings into one string to
     * represent a new edge. This string is sent to the edges' list as a new edge.
     * In addition, the same process is done over the vertices, but for this case we
     * want to look at two lines: one contains the position, and the other contains the vertex's id.
     *
     * @throws IOException
     * @throws IOException
     */
    public void JSON_Reader() throws IOException {
        String data;
        FileReader fileReader = new FileReader(path);
        BufferedReader Reader = new BufferedReader(fileReader);
        while ((data = Reader.readLine()) != null) {
            String sec_word, third_word;
            if (data.contains("src")) {
                sec_word = Reader.readLine().strip().replaceAll(" ", "");
                third_word = Reader.readLine().strip().replaceAll(" ", "");
                this.Edges.add(data.strip().replaceAll(" ", "") + sec_word + third_word);
            }
            if (data.contains("pos")) {
                sec_word = Reader.readLine().strip().replaceAll(" ", "");
                this.Vertices.add(data.strip().replaceAll(" ", "") + sec_word);
            }
        }
        fileReader.close();
    }

    /**
     * @param graph
     * @throws IOException
     */
    public void JSON_Writer(DirectedWeightedGraph graph) {
/*        try {
            // create Gson instance
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            // create a writer
            Writer writer = Files.newBufferedWriter(Paths.get("book.json"));
            HashMap<String, Object> map = new HashMap<>();
            Iterator<EdgeData> l = graph.edgeIter();
            map.put("Edges", "[]");
            while(l.hasNext()){
                EdgeData curr = l.next();
                map.put("src", curr.getSrc());
                map.put("weight", curr.getWeight());
                map.put("dest", curr.getDest());
                // convert book object to JSON file
                gson.toJson(map, writer);
                l.remove();
            }

            // close writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("books.json"));
            ArrayList<EdgeData> jsonEdges = new ArrayList<>();
            ArrayList<NodeData> jsonNodes = new ArrayList<>();

            for (int i = 0; i < graph.nodeSize(); i++) {
                for (int j = 0; j < graph.nodeSize(); j++) {
                    if(i == j) {continue;}
                    if(graph.getEdge(i,j) != null) {
                        jsonEdges.add(graph.getEdge(i,j));
                    }
                }
            }

            for (int k = 0; k < graph.nodeSize(); k++) {
                jsonNodes.add(graph.getNode(k));
            }
            // create Gson instance
            Gson gson = new GsonBuilder().setPrettyPrinting().create();


            // convert books object to JSON file
            gson.toJson(jsonEdges, writer);

            // close writer
            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
/*        try {*/
            // create book object
            //String[] jsonEdges = new String[graph.edgeSize()];
            //String[] jsonNodes = new String[graph.nodeSize()];
/*            Writer writer = Files.newBufferedWriter(Paths.get("jsonEdges.json"));
            Gson gson = new Gson();
            for (int i = 0; i < graph.nodeSize(); i++) {
                for (int j = 0; j < graph.nodeSize(); j++) {
                    if(i == j) {continue;}
                    if(graph.getEdge(i,j) != null) {
                        EdgeData temp = graph.getEdge(i, j);
                        String json =  gson.toJson(temp);
                        gson.toJson(json, writer);
                        //jsonEdges[i] = graph.getEdge(i, j).getInfo();
                        //bw.append(graph.getEdge(i, j).getInfo());
                    }
                }
            }*/

            // convert book object to JSON
            //      Gson gson = new Gson().toJson(book);

            // print JSON string

            // convert book object to JSON file

            // close writer
/*            writer.close();


        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
/*        try {
            FileOutputStream os = new FileOutputStream(path + ".json", true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String[] jsonEdges = new String[graph.edgeSize()];
            String[] jsonNodes = new String[graph.nodeSize()];
            int m = 0;
            for (int i = 0; i < graph.nodeSize(); i++) {
                for (int j = 0; j < graph.nodeSize(); j++) {
                    if(i == j) {continue;}
                    if(graph.getEdge(i,j) != null) {
                        jsonEdges[m++] = graph.getEdge(i, j).getInfo();
                        bw.append(graph.getEdge(i, j).getInfo());
                    }
                }
            }
            for (int k = 0; k < jsonNodes.length; k++) {
                jsonNodes[k] = graph.getNode(k).getInfo();
            }
            String temp = gson.toJson("Tempss");
            bw.append(temp);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
    }

/*        FileWriter FileWriter = new FileWriter(path+".json");
        Gson gson = new GsonBuilder().create();
        String[] jsonEdges = new String[graph.edgeSize()];
        String[] jsonNodes = new String[graph.nodeSize()];
        for (int i = 0; i < graph.nodeSize(); i++) {
            for (int j = 0; j < graph.nodeSize(); j++) {
                if(i == j) {continue;}
                if(graph.getEdge(i,j) != null) {
                    jsonEdges[i] = graph.getEdge(i, j).getInfo();
                }
            }
        }
        for (int k = 0; k < jsonNodes.length; k++) {
            jsonNodes[k] = graph.getNode(k).getInfo();
        }
        gson.toJson(jsonEdges, FileWriter);
        gson.toJson(jsonNodes, FileWriter);*/

        /**
         *
         */
        public void init_Graph () {
            ArrayList<EdgeData> edges = updateEdges();
            ArrayList<NodeData> vertices = updateVertices();
            this.initEdges = edges;
            this.initVertices = vertices;
        }

        /**
         * @return
         */
        public ArrayList<EdgeData> updateEdges () {
            ArrayList<EdgeData> Edges = new ArrayList<>();
            for (String edge : this.Edges) {
                edge = edge.replaceAll(":", " ").replaceAll(",", " ");
                String[] values = edge.split(" ");
                int src = Integer.parseInt(values[1]);
                int dest = Integer.parseInt(values[5]);
                double w = Double.parseDouble(values[3]);
                String info = "src:" + src + "\n"  + "weight:" + w + "\n" + "dest:" + dest;
                EdgeData Edge = new EdgeDataImpl(src, dest, Color.BLACK.getRGB(), w, info);
                Edges.add(Edge);
            }
            return Edges;
        }

        /**
         * @return
         */
        public ArrayList<NodeData> updateVertices () {
            ArrayList<NodeData> Vertices = new ArrayList<>();
            for (String vertex : this.Vertices) {
                String[] id_temp = vertex.split(",");
                int id = Integer.parseInt(id_temp[id_temp.length - 1].substring(5));
                double pos_x = Double.parseDouble(id_temp[0].substring(7));
                double pos_y = Double.parseDouble(id_temp[1]);
                double pos_z = Double.parseDouble(id_temp[2].substring(0, id_temp[2].length() - 1));
                String info = "ID:" + id + "\n" + "POS:" + pos_x + "," + pos_y + "," + pos_z;
                GeoLocationImpl geoPoint = new GeoLocationImpl(pos_x, pos_y, pos_z);
                NodeData Node = new NodeDataImpl(id, Color.RED.getRGB(), info, Integer.MAX_VALUE, geoPoint);
                Vertices.add(Node);
            }
            return Vertices;
        }

        public ArrayList<String> getEdges () {
            return Edges;
        }

        public ArrayList<String> getNodes () {
            return Vertices;
        }

        public ArrayList<EdgeData> getInitEdges () {
            return initEdges;
        }

        public ArrayList<NodeData> getInitVertices () {
            return initVertices;
        }
    }

