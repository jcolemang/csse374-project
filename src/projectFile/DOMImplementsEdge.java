package projectFile;

public class DOMImplementsEdge extends DOMActuallyAbstractEdgeNode {

	@Override
	public String getTextRepresentation() {

		System.out.println("IMPLEMENTS HEAD: " + this.head.getDOTTitle());
		System.out.println("IMPLEMENTS TAIL: " + this.tail.getDOTTitle());
		return this.head.getDOTTitle() + "->" + this.tail.getDOTTitle() +
				"; [ arrowhead = empty, style = dashed, dirType = back ];\n";
	}

}
