package DOMNodes;

public class DOMDependencyEdge extends DOMActuallyAbstractEdgeNode {
	
	public DOMDependencyEdge() {
		super();
		this.addAttribute("arrowhead", "vee");
		this.addAttribute("style", "dashed");
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
