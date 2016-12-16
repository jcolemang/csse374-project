package projectFile;

public class DOMImplementsEdge extends DOMActuallyAbstractEdgeNode {

	@Override
	public String getTextRepresentation() {

		System.out.println("IMPLEMENTS START: " + this.start.getDOTTitle());
		System.out.println("IMPLEMENTS END: " + this.end.getDOTTitle());
		return this.start.getDOTTitle() + "->" + this.end.getDOTTitle() +
				" [ arrowhead = empty, style = dashed, dir = forward ];\n";
	}

}
