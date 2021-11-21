package api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class JSON_Operation {

    public String path;
    private ArrayList<String> Edges;
    private ArrayList<String> Nodes;

    public JSON_Operation(String path) {
        this.path = path;
        this.Edges = new ArrayList<>();
        this.Nodes = new ArrayList<>();
    }
    /**
     * The function goes over each line. If the function encountered a line that contains "src",
     * then it knows that we reached a new edge, so the three lines included the current line
     * are the edges' details. Therefore, we want to merge those strings into one string to
     * represent a new edge. This string is sent to the edges' list as a new edge.
     * In addition, the same process is done over the vertices, but for this case we
     * want to look at two lines: one contains the position, and the other contains the vertex's id.
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
                sec_word = Reader.readLine().strip().replaceAll(" ",  "");
                third_word = Reader.readLine().strip().replaceAll(" ",  "");
                this.Edges.add(data.strip().replaceAll(" ",  "") + sec_word + third_word);
            }
            if (data.contains("pos")) {
                sec_word = Reader.readLine().strip().replaceAll(" ",  "");
                this.Nodes.add(data.strip().replaceAll(" ",  "") + sec_word);
            }
        }
        fileReader.close();
    }
    public ArrayList<String> getEdges() {
        return Edges;
    }

    public ArrayList<String> getNodes() {
        return Nodes;
    }
}
