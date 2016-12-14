package projectFile;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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
	
	private List<String> fields;
	
	
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
	
	public void setFields(List<FieldData> data) {
		System.out.println("Setting fields for " + this.getClassName());
		this.fields = new ArrayList<String>();
		for (FieldData f : data) {
			System.out.println(f);
			this.fields.add(this.accessStringToSign(f.getAccessLevel()) +
							f.getFieldName() +
							": " +
							f.getFieldType());
		}
	}
	
	public void setEdgeType(EdgeType type) {
		this.type = type;
	}

	@Override
	public String getTextRepresentation() {
		
		System.out.println("Getting text representation of " + this.getClassName());
		
		// setting the name of the node
		String title = this.getClassName();
		
		// setting the fields string
		String fields = "";
		for (String s : this.fields) {
			fields += s + "\\l";
			System.out.println(s);
		}
		
		// TODO set the methods fields
		
		return title.replaceAll("\\.", "") + "[\n" +
			"label = \"{" + title + "|" + fields + "|" +
			"METHODS GO HERE\\l" + 
			"}" + "\"\n]";
			
		
	}
	
	
	private String accessStringToSign(String access) {
		switch (access) {
		case "private":
			return "-";
		case "public":
			return "+";
		case "protected": 
			return "#";
		}
		throw new IllegalArgumentException("Illegal access type");
	}
	
}
