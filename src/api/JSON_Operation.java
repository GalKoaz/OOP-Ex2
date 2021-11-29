package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


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
     * DirectedWeightedGraph graph
     *
     * @param graph
     * @throws IOException
     */
    public void JSON_Writer(DirectedWeightedGraph graph) {
        Iterator<NodeData> vertices = graph.nodeIter();
        JsonArrayBuilder nodes = Json.createArrayBuilder();

        while (vertices.hasNext()) {
            NodeData curr = vertices.next();
            nodes.add(Json.createObjectBuilder()
                    .add("pos", curr.getLocation().toString())
                    .add("id", curr.getKey()).build());
        }
        Iterator<EdgeData> Edges = graph.edgeIter();
        JsonArrayBuilder edges = Json.createArrayBuilder();
        while (Edges.hasNext()) {
            EdgeData curr = Edges.next();
            edges.add(Json.createObjectBuilder()
                    .add("src", curr.getSrc())
                    .add("w", curr.getWeight())
                    .add("dest", curr.getDest())
                    .build());
        }
        javax.json.JsonObject jsonObject = Json.createObjectBuilder()
                .add("Edges", edges)
                .add("Nodes", nodes).build();

        try {
            FileWriter file = new FileWriter(path);
            StringWriter sw = new StringWriter();
            HashMap<String, Boolean> config = new HashMap<>();
            JsonWriterFactory jwf = Json.createWriterFactory(config);
            JsonWriter jsonWriter = jwf.createWriter(sw);
            jsonWriter.writeObject(jsonObject);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement g = new JsonParser().parse(sw.toString());
            String prettyJsonString = gson.toJson(g);
            file.write(prettyJsonString);
            jsonWriter.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void init_Graph() {
        ArrayList<EdgeData> edges = updateEdges();
        ArrayList<NodeData> vertices = updateVertices();
        this.initEdges = edges;
        this.initVertices = vertices;
    }

    /**
     * @return
     */
    public ArrayList<EdgeData> updateEdges() {
        ArrayList<EdgeData> Edges = new ArrayList<>();
        for (String edge : this.Edges) {
            edge = edge.replaceAll(":", " ").replaceAll(",", " ");
            String[] values = edge.split(" ");
            int src = Integer.parseInt(values[1]);
            int dest = Integer.parseInt(values[5]);
            double w = Double.parseDouble(values[3]);
            String info = "src:" + src + "\n" + "weight:" + w + "\n" + "dest:" + dest;
            EdgeData Edge = new EdgeDataImpl(src, dest, Color.BLACK.getRGB(), w, info);
            Edges.add(Edge);
        }
        return Edges;
    }

    /**
     * @return
     */
    public ArrayList<NodeData> updateVertices() {
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

    public ArrayList<String> getEdges() {
        return Edges;
    }

    public ArrayList<String> getNodes() {
        return Vertices;
    }

    public ArrayList<EdgeData> getInitEdges() {
        return initEdges;
    }

    public ArrayList<NodeData> getInitVertices() {
        return initVertices;
    }

}



