package analyzers;

import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;
import projectFile.FieldData;

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

        // getting the superclass vertices
        List<IClassVertex> supers = new LinkedList<>();

        if (v.getSuperclassEdge() != null) {
            supers.add(v.getSuperclassEdge().getTo());
        }

        for (IClassEdge e : v.getImplementsEdges()) {
            supers.add(e.getTo());
        }

        // getting the "has a" vertices
        List<FieldData> fieldDatas = v.getFields();
        List<IClassVertex> fields = new LinkedList<>();
        for (FieldData fd : fieldDatas) {
            fields.add(fd.getFieldType());
        }

        // checking all pairs
        for (IClassVertex extnds : supers) {
            for (IClassVertex has : fields) {
                if (satisfiesCondition(v, extnds, has)) {

                }
            }
        }
    }


    private boolean satisfiesCondition(IClassVertex curr, IClassVertex extnds, IClassVertex has) {

        List<IClassVertex> maybeExtends = new LinkedList<>();

        return false;
    }
}
