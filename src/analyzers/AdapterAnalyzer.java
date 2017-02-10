package analyzers;

import CommandLineArgument.Configuration;
import graphNodes.AbstractClassVertex;
import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import graphNodes.RegularClassVertex;
import projectFile.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by coleman on 2/9/17.
 */
public class AdapterAnalyzer extends AbstractAnalyzer {

    private float target;

    public AdapterAnalyzer() {
        String strTarget = Configuration.getInstance().getProperty(Configuration.BAD_ADAPTER_RATIO);
        if (strTarget == null) {
            strTarget = "1";
        }
        this.target = Float.parseFloat(strTarget);
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

//                    System.out.println(v + " adapts " + has + " to " + extnds);

                    v.getCorrespondingDOMNode().addAttribute("fillcolor", "maroon");
                    v.getCorrespondingDOMNode().addAttribute("style", "filled");
                    
                    if (v instanceof RegularClassVertex) {
                    	v.getCorrespondingDOMNode().setTitleAdditions("\\n<<Adapter>>");      		
                	} else if (v instanceof AbstractClassVertex) {
                		v.getCorrespondingDOMNode().setTitleAdditions("<<Adapter>>");
                	}

                    extnds.getCorrespondingDOMNode().addAttribute("fillcolor", "maroon");
                    extnds.getCorrespondingDOMNode().addAttribute("style", "filled");
                    extnds.getCorrespondingDOMNode().setTitleAdditions("<<Target>>");

                    has.getCorrespondingDOMNode().addAttribute("fillcolor", "maroon");
                    has.getCorrespondingDOMNode().addAttribute("style", "filled");
                    if (v instanceof RegularClassVertex) {
                    	v.getCorrespondingDOMNode().setTitleAdditions("\\n<<Adaptee>>");      		
                	} else if (v instanceof AbstractClassVertex) {
                		v.getCorrespondingDOMNode().setTitleAdditions("<<Adaptee>>");
                	}

                    g.getEdgesFromTo(v, has)
                            .stream()
                            .forEach(e -> {
                                e.getCorrespondingDOMNode()
                                        .addAttribute("label", "\"  \\<\\<adapts\\>\\>\"");
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

        if (extnds.getTitle().equals(has.getTitle())) {
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

        for (MethodData md : extnds.getMethods()) {
            if (md.isAnInitializer()) {
                continue;
            }

            if (!curr.getMethods().contains(md)) {
                return false;
            }
        }

        System.out.println(curr);

        // all your methods use B
        float count = 0, total = 0;
        for (MethodData md : curr.getMethods()) {

            if (md.isAnInitializer()) {
                continue;
            }

            List<CodeData> codeDatas = md.getCodeData();
            total++;
            for (CodeData codeData : codeDatas) {
                if (codeData.getClasses().contains(has)) {
                    count++;
                    break;
                }
            }
        }

        return (total > 0 && count / total >= this.target);
    }
}
