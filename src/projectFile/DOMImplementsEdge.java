package projectFile;

public class DOMImplementsEdge extends DOMActuallyAbstractEdgeNode {

	@Override
	public String getTextRepresentation() {
		return this.start.getDOTTitle() + "->" + this.end.getDOTTitle() +
				" [ arrowhead = empty, style = dashed, dir = forward ];\n";
	}

}
