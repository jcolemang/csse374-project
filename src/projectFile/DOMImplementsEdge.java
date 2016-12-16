package projectFile;

public class DOMImplementsEdge extends DOMActuallyAbstractEdgeNode {

	@Override
	public String getTextRepresentation() {

		System.out.println("IMPLEMENTS HEAD: " + this.head.getDOTTitle());
		System.out.println("IMPLEMENTS TAIL: " + this.tail.getDOTTitle());
		return this.tail.getDOTTitle() + "->" + this.head.getDOTTitle() +
				" [ arrowhead = empty, style = dashed, dir = forward ];\n";
	}

}
