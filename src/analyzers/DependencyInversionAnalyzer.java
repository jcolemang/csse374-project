package analyzers;

import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;
import projectFile.FieldData;

public class DependencyInversionAnalyzer extends AbstractAnalyzer {

    public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
        this.setVisited(v);


    }


    private boolean violates(IClassVertex v, IClassVertex b) {

        return hasA(v, b) &&
                b.get;
    }


    private boolean hasA(IClassVertex v, IClassVertex b) {
        for (FieldData fd : v.getFields()) {
            if (fd.getFieldType().equals(b)) {
                return true;
            }
        }
        return false;
    }

}

