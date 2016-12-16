package projectFile;

public interface IDOMEdgeNode extends IDOMNode {
	IDOMClassNode getTail();
	IDOMClassNode getHead();
	void set(IDOMClassNode head, IDOMClassNode tail);
}
