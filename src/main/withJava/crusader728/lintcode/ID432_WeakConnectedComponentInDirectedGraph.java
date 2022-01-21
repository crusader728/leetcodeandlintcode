package withJava.crusader728.lintcode;

import java.util.ArrayList;
import java.util.List;

public class ID432_WeakConnectedComponentInDirectedGraph {

    private static class DirectedGraphNode {
      int label;
      List<DirectedGraphNode> neighbors;
      DirectedGraphNode(int x) {
          label = x;
          neighbors = new ArrayList<DirectedGraphNode>();
      }
    }

    public List<List<Integer>> connectedSet2(ArrayList<DirectedGraphNode> nodes) {
        return null;
    }

}
