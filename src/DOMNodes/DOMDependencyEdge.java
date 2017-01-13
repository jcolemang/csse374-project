package DOMNodes;

public class DOMDependencyEdge extends DOMActuallyAbstractEdgeNode {

	@Override
	public String getTextRepresentation() {
		return this.start.getDOTTitle() + "->" + this.end.getDOTTitle() +
				" [ arrowhead = vee, style = dashed, dir = forward, "
				+ attributeMapToString()
				+ "];\n";
	}

}
