package DOMNodes;

public class DOMDependencyEdge extends DOMActuallyAbstractEdgeNode {
	
	public void setHeadCardinality(String s) {
		this.headCardinality = s;
	}
	
	public void setTailCardinality(String s) {
		this.tailCardinality = s;
	}

	@Override
	public String getTextRepresentation() {
		return this.start.getDOTTitle() + "->" + this.end.getDOTTitle() +
				" [ arrowhead = vee, style = dashed, dir = forward, "
				+ "headlabel = " + this.getHeadCardinality() + ", "
				+ "taillabel = " + this.getTailCardinality()
				+ "];\n";
	}

}
