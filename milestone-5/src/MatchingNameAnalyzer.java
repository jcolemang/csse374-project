import DOMNodes.IDOMClassNode;
import DOMNodes.IDOMEdgeNode;
import DOMNodes.IDOMNode;
import analyzers.AbstractAnalyzer;
import analyzers.IAnalyzer;
import com.sun.xml.internal.bind.v2.model.core.ID;
import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;

/**
 * Created by coleman on 2/14/17.
 */
public class MatchingNameAnalyzer extends AbstractAnalyzer {

    public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph dom) {

        NewDOMGraph seriousHack = (NewDOMGraph) dom;

        if (v.getCorrespondingDOMNode() == null) {
            return;
        }

        IDOMNode node = v.getCorrespondingDOMNode();

        g.getVertices()
                .stream()
                .filter(x -> x.getCorrespondingDOMNode() != null)
                .filter(x -> nameMatches(x, v))
                .filter(x -> !x.equals(v))
                .map(x -> x.getCorrespondingDOMNode())
                .forEach(end -> {
                    IDOMEdgeNode newDomNode = new MatchingNameArrow();
                    IDOMClassNode start = v.getCorrespondingDOMNode();
                    newDomNode.set(start, end);
                    seriousHack.addDOMNode(newDomNode);
                });
    }


    public boolean nameMatches(IClassVertex curr, IClassVertex other) {

        String myName = curr.getTitle();
        String otherName = other.getTitle();

        String[] myLs = myName.split(".");
        String[] otherLs = otherName.split(".");

        return myLs[myLs.length - 1].equals(otherLs[otherLs.length - 1]);
    }
}
