package DOMNodes;

public class DOMExtendsEdge extends DOMActuallyAbstractEdgeNode {

	@Override
	public String getTextRepresentation() {
		return this.start.getDOTTitle() 
				+ "->" 
				+ this.end.getDOTTitle() 
				+ "[ " + attributeMapToString() + "]"
				+ ";\n";
	}

}