package projectFile;

public class DOMClassNode implements IDOMNode{
	
	String OutlineColor = "black";
	String BGColor = "white";
	String Font = "SansSerif";
	String classTitle = "";
	
	
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
		return "";
	}
	
	public String getClassType() {
		return "";
	}
	
}
