package DOMNodes;

public class DOMAssociationEdge extends DOMActuallyAbstractEdgeNode {

	@Override
	public String getTextRepresentation() {
		return this.start.getDOTTitle() + "->" + this.end.getDOTTitle() +
				" [ arrowhead = vee, dir = forward, "
				+ "headlabel = " + this.getHeadCardinality() + ", "
				+ "taillabel = " + this.getTailCardinality()
				+ "];\n";
	}

}
