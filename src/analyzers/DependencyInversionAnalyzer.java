package analyzers;

import graphNodes.AbstractClassVertex;
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
        // TODO this isn't quite right
        v.getFields().stream()
                .map((FieldData f) -> {
                    return f.getFieldType();
                }).filter((IClassVertex f) -> {
                    return hasA(v, f)
                            && f.getCorrespondingDOMNode() != null
                            && v instanceof RegularClassVertex
                            && (v.getSuperclassEdge().getTo() instanceof AbstractClassVertex
                                || v.getImplementsEdges().size() > 0);
                }).forEach((IClassVertex f) -> {
                    v.getCorrespondingDOMNode().addAttribute("fillcolor", "red");
                    v.getCorrespondingDOMNode().addAttribute("style", "filled");
                });
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

