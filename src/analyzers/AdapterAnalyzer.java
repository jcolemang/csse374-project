package analyzers;

import CommandLineArgument.Configuration;
import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import projectFile.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by coleman on 2/9/17.
 */
public class AdapterAnalyzer extends AbstractAnalyzer {

    private float threshold;

    public AdapterAnalyzer() {
        String thresh = Configuration.getInstance().getProperty("DecoratorCodeThreshold");
        if (thresh == null) {
            thresh = "1";
        }

        this.threshold = Float.parseFloat(thresh);
    }


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
                    v.getCorrespondingDOMNode().addAttribute("fillcolor", "purple");
                    v.getCorrespondingDOMNode().addAttribute("style", "filled");
                    v.getCorrespondingDOMNode().setTitleAdditions("<<Adapter>>");

                    extnds.getCorrespondingDOMNode().addAttribute("fillcolor", "purple");
                    extnds.getCorrespondingDOMNode().addAttribute("style", "filled");
                    extnds.getCorrespondingDOMNode().setTitleAdditions("<<Target>>");

                    has.getCorrespondingDOMNode().addAttribute("fillcolor", "purple");
                    has.getCorrespondingDOMNode().addAttribute("style", "filled");
                    has.getCorrespondingDOMNode().setTitleAdditions("<<Adaptee>>");

                    g.getEdgesFromTo(v, has)
                            .stream()
                            .forEach(e -> {
                                e.getCorrespondingDOMNode()
                                        .addAttribute("label", "<<adapts>>");
                            });
                }
            }
        }
    }


    // If you extend A and have an instance of B and you
    // override all of A's methods and all of your methods use B
    private boolean satisfiesCondition(IClassVertex curr, IClassVertex extnds, IClassVertex has) {

        if (curr.getCorrespondingDOMNode() == null ||
                extnds.getCorrespondingDOMNode() == null ||
                has.getCorrespondingDOMNode() == null) {
            return false;
        }

        if (extnds.getClass().equals(has.getClass())) {
            return false;
        }

        // you extend A
        if (!curr.extendsOrImplements(extnds)) {
            return false;
        }

        // you have an instance of B
        if (!curr.containsField(has)) {
            return false;
        }

        // override all of A's methods
        int count = 0;
        int total = 0;
        for (MethodData md : curr.getMethods()) {

            if (md.isAnInitializer()) {
                continue;
            }

            if (!extnds.getMethods().contains(md)) {
                return false;
            }

            // all your methods use B
            List<CodeData> codeDatas = md.getCodeData();
            boolean codeMatch = true;
            for (CodeData codeData : codeDatas) {
                if (!codeData.getClasses().contains(has)) {
                    codeMatch = false;
                }
            }

            if (codeMatch) {
                count++;
            }
            total++;
        }

        if ((float)count / (float)total < this.threshold) {
            return false;
        }

        return true;
    }
}
