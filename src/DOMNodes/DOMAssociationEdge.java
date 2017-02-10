package DOMNodes;

public class DOMAssociationEdge extends DOMActuallyAbstractEdgeNode {
	

	public DOMAssociationEdge() {
		super();
		this.addAttribute("arrowhead", "vee");
		this.addAttribute("dir", "forward");
	}

	
	@Override
	public String getTextRepresentation() {
		return this.start.getDOTTitle() 
				+ " -> " 
				+ this.end.getDOTTitle()
				+ "["
				+ attributeMapToString()
				+ "];\n";
	}

}
