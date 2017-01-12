package DOMNodes;

public interface IDOMEdgeNode extends IDOMNode {
	IDOMClassNode getEnd();
	IDOMClassNode getStart();
	String getHeadCardinality();
	String getTailCardinality();
	void set(IDOMClassNode start, IDOMClassNode end);
}
