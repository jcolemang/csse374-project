package analyzers;

import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by coleman on 2/9/17.
 */
public class AdapterAnalyzer extends AbstractAnalyzer {
    // If you extend A and have an instance of B and you
    // override all of A's methods and all of your methods use B


    @Override
    public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
        List<IClassVertex> interfaces = new LinkedList<>();
        for (IClassEdge e : v.getImplementsEdges()) {
            interfaces.add(e.getTo());
        }
    }
}
