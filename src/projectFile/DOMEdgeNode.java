package projectFile;

public class DOMEdgeNode implements IDOMNode{
		
	public enum EdgeType {
		EXTENDS,
		IMPLEMENTS,
		HAS_A,
		DEPENDS_ON
	}
		
	String color;
	EdgeType type;
	
	public void setEdgeType(EdgeType type) {
		this.type = type;
	}

}
