package DOMNodes;

public interface IDOMEdgeNode extends IDOMNode {
	IDOMClassNode getEnd();
	IDOMClassNode getStart();
	void set(IDOMClassNode start, IDOMClassNode end);
}
