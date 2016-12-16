package projectFile;

public class DOMExtendsEdge extends DOMActuallyAbstractEdgeNode {

	@Override
	public String getTextRepresentation() {
		System.out.println("EXTENDS START: " + this.start);
		System.out.println("EXTENDS END: " + this.end);
		return this.start.getDOTTitle() + "->" + this.end.getDOTTitle() +
				" [ arrowhead = empty ];\n";
	}

}
