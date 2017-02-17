import DOMNodes.DOMActuallyAbstractEdgeNode;

/**
 * Created by coleman on 2/14/17.
 */
public class MatchingNameArrow extends DOMActuallyAbstractEdgeNode {

    public String getTextRepresentation() {
        return this.start.getDOTTitle() +
                " -> " +
                this.end.getDOTTitle() +
                "[" + this.attributeMapToString() + " ];\n";
    }
}
