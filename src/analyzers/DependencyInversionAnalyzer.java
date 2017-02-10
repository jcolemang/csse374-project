package analyzers;

import graphNodes.AbstractClassVertex;
import graphNodes.IClassVertex;
import graphNodes.RegularClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;
import projectFile.FieldData;
import java.util.stream.Stream;

public class DependencyInversionAnalyzer extends AbstractAnalyzer {

    // Feeling very proud of myself
    public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
        this.setVisited(v);
        if (v.getCorrespondingDOMNode() == null ||
                !(v instanceof RegularClassVertex) ||
                !(v.getSuperclassEdge().getTo() instanceof AbstractClassVertex ||
                    v.getImplementsEdges().size() > 0)) {
            return;
        }

        Stream<IClassVertex> fieldStream =
                v.getFields()
                .stream()
                .map(fd -> fd.getFieldType());

        Stream<IClassVertex> methodStream =
                v.getMethods()
                .stream()
                .flatMap(method -> {
                    Stream.Builder<IClassVertex> builder = Stream.builder();
                    builder.accept(method.getReturnType());
                    method.getParams()
                            .stream()
                            .forEach(p -> builder.accept(p));
                    return builder.build();
                });

        Stream.concat(fieldStream, methodStream)
                .filter(f -> hasA(v, f))
                .filter(x -> x instanceof RegularClassVertex)
                .filter(f -> f.getCorrespondingDOMNode() != null)
                .forEach((IClassVertex f) -> {
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

