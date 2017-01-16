package DOMNodes;

public interface IDOMEdgeNode extends IDOMNode {
	IDOMClassNode getTail();
	IDOMClassNode getHead();
	void addAttribute(String dotAttrName, String property);
	String getAttribute(String graphvizKey);
	String attributeMapToString();
	void set(IDOMClassNode start, IDOMClassNode end);
}
