import GraphGui.FrameGraph;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.DirectedWeightedGraphAlgorithmsImpl;
import api.DirectedWeightedGraphImpl;


/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraphAlgorithms ans = new DirectedWeightedGraphAlgorithmsImpl();
        ans.load(json_file);
        return ans.getGraph();
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = new DirectedWeightedGraphAlgorithmsImpl();
        ans.load(json_file);
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        new FrameGraph(alg.copy());
    }

    public static void main(String[] args) {
        DirectedWeightedGraphAlgorithms g = new DirectedWeightedGraphAlgorithmsImpl();
         g.load("100000.json");
        long start = System.currentTimeMillis();
        System.out.println(g.isConnected());
        long time = System.currentTimeMillis()-start;
        System.out.println("time in sec: "+ time*0.001);
//         //System.out.println(g.center().getKey());

 //       runGUI("G2.json");
          //runGUI("G2.json");
          //runGUI("1000Nodes.json");
//        String path = "data\\";
//        String file_name = args[0];
//        runGUI(path+file_name);

    }
}