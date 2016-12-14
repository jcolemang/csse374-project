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
	private List<String> methods;
	
	
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
		this.fields = new ArrayList<String>();
		for (FieldData f : data) {
			this.fields.add(this.accessStringToSign(f.getAccessLevel()) +
							f.getFieldName() +
							": " +
							f.getFieldType());
		}
	}

	public void setMethods(List<MethodData> data) {
		this.methods = new ArrayList<String>();
		
		for (MethodData m : data) {

			String paramInfo = "";
			for(IClassVertex f : m.getParams()) {
				paramInfo += f + ", ";
			}

			//chop off trialing ", "
			if (paramInfo.length() != 0) {
				paramInfo = paramInfo.substring(0, paramInfo.length() - 2); 
			}
				
			this.methods.add(this.accessStringToSign(m.getAccessLevel()) +
							m.getMethodName() + "(" + paramInfo + ")\n");	
		}
		
	}
	
	public void setEdgeType(EdgeType type) {
		this.type = type;
	}
	
	@Override
	public String getTextRepresentation() {
		
		// setting the name of the node
		String title = this.getClassName();
		
		// setting the fields string
		String fieldsString = "";
		for (String s : this.fields) {
			fieldsString += s + "\\l";
		}
		
		// setting the method fields
		String methodFields = "";
		for (String s : this.methods) {
			methodFields += s + "\\l";
		}
		
		return title.replaceAll("\\.", "") + "[\n" +
			"label = \"{" + title + "|" + fieldsString + "|" + methodFields + 
			"}\"\n]";
		
		
	}
	
	
	/*
	 * TODO What is the access level modifier of <clinit>?
	 */
	private String accessStringToSign(String access) {
		switch (access) {
		case "private":
			return "-";
		case "public":
			return "+";
		case "protected": 
			return "#";
		case "native":
			return "=";
		}
		throw new IllegalArgumentException("Illegal access type");
	}
	
}
