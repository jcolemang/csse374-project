package analyzers;

import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import graphNodes.RegularClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;
import projectFile.FieldData;

import java.util.logging.FileHandler;

public class DependencyInversionAnalyzer extends AbstractAnalyzer {

    public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
        this.setVisited(v);
        if (v.getCorrespondingDOMNode() == null) {
            return;
        }

        // This is Coleman and I am VERY proud of myself
        v.getFields().stream()
                .map((FieldData f) -> {
                    return f.getFieldType();
                }).filter((IClassVertex f) -> {
                    return hasA(v, f) && f.getCorrespondingDOMNode() != null;
                }).forEach((IClassVertex f) -> {
                    v.getCorrespondingDOMNode().addAttribute("fillcolor", "red");
                    v.getCorrespondingDOMNode().addAttribute("style", "filled");
                });
    }


    private boolean violates(IClassVertex v, IClassVertex b) {
        return b.getImplementsEdges().size() == 0 &&
                b instanceof RegularClassVertex;
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

