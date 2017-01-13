package DOMNodes;

public interface IDOMEdgeNode extends IDOMNode {
	IDOMClassNode getTail();
	IDOMClassNode getHead();
	String getHeadCardinality();
	String getTailCardinality();
	void set(IDOMClassNode start, IDOMClassNode end);
}
