package DOMNodes;

public interface IDOMEdgeNode extends IDOMNode {
	IDOMClassNode getFrom();
	IDOMClassNode getTo();
	void addAttribute(String dotAttrName, String property);
	String getAttribute(String graphvizKey);
	String attributeMapToString();
	void set(IDOMClassNode start, IDOMClassNode end);
}
