package projectFile;

public class DOMExtendsEdge extends DOMActuallyAbstractEdgeNode {

	@Override
	public String getTextRepresentation() {
		System.out.println("EXTENDS HEAD: " + this.head);
		System.out.println("EXTENDS TAIL: " + this.tail);
		return this.tail.getDOTTitle() + "->" + this.head.getDOTTitle() +
				" [ arrowhead = empty ];\n";
	}

}
