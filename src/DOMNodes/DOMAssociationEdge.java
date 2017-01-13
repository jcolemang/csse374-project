package DOMNodes;

public class DOMAssociationEdge extends DOMActuallyAbstractEdgeNode {
	
	public void setHeadCardinality(String s) {
		this.headCardinality = s;
	}
	
	public void setTailCardinality(String s) {
		this.tailCardinality = s;
	}


	@Override
	public String getTextRepresentation() {
		return this.start.getDOTTitle() + "->" + this.end.getDOTTitle() +
				" [ arrowhead = vee, dir = forward, "
				+ "headlabel = " + this.getHeadCardinality() + ", "
				+ "taillabel = " + this.getTailCardinality()
				+ "];\n";
	}

}
