package projectFile;

public class DOMClassNode implements IDOMNode{
	
	public enum EdgeType {
		EXTENDS,
		IMPLEMENTS,
		HAS_A,
		DEPENDS_ON
	}
		
	String OutlineColor = "black";
	String BGColor = "white";
	String Font = "SansSerif";
	String classTitle = "";
	String color;
	EdgeType type;
	
	
	public void setTitle(String title) {
		this.classTitle = title;
	}
	
	public void setOutlineColor(String color) {
		this.OutlineColor=color;
	}
	
	public void setBGColor(String color) {
		this.BGColor=color;
	}
	
	public void setFont(String font) {
		this.Font=font;
	}
	
	public String getClassName() {
		return this.classTitle;
	}
	
	public String getClassType() {
		return "";
	}
	
	public void setEdgeType(EdgeType type) {
		this.type = type;
	}
	
}
