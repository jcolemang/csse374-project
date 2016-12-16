package projectFile;

import java.util.ArrayList;
import java.util.List;

public abstract class DOMAbstractBoxNode implements IDOMNode{
	
	String OutlineColor = "black";
	String BGColor = "white";
	String Font = "SansSerif";
	String classTitle = "";
	String color;
	// EdgeType type;

	protected List<String> fields;
	protected List<String> methods;

	public void setTitle(String title) {
		this.classTitle = title;
	}

	public void setOutlineColor(String color) {
		this.OutlineColor = color;
	}

	public void setBGColor(String color) {
		this.BGColor = color;
	}

	public void setFont(String font) {
		this.Font = font;
	}

	public String getClassName() {
		return this.classTitle;
	}
	
	public abstract String getTextRepresentation();
	
	/**
	 * Initializes the DOMClassNode's this.fields to be an array filled with
	 * string representations of each field in the class.
	 * @param data
	 */
	public void setFields(List<FieldData> data) {
		
		this.fields = new ArrayList<String>();
		
		for (FieldData f : data) {
			this.fields.add(this.accessStringToSign(f.getAccessLevel()) +
					f.getFieldName() + ": " + f.getFieldType());
		}
	}
	
	/**
	 * Initializes the DOMClassNode's this.methods to be an array filled with
	 * string representations of each method in the class.
	 * @param data
	 */
	//TODO: consider putting this into the IDOMNode interface
	public void setMethods(List<MethodData> data) {
		
		this.methods = new ArrayList<String>();

		for (MethodData m : data) { // For every method the class has
			String paramInfo = ""; // Reset parameter information for each method
			
			for (IClassVertex f : m.getParams()) { // For every parameter of that method
				paramInfo += f + ", ";
			}

			if (paramInfo.length() != 0) { // chop off trialing ", "
				paramInfo = paramInfo.substring(0, paramInfo.length() - 2);
			}

			this.methods.add(this.accessStringToSign(m.getAccessLevel()) +
					m.getMethodName() + "(" + paramInfo + ")\n");
		}
	}
	
	/**
	 * Helper method that returns the UML Access Level symbol
	 * associated with each string "private," "public," etc.
	 * @param access
	 * @return
	 */
	/*
	 * TODO What is the access level modifier of <clinit>?
	 */
	protected String accessStringToSign(String access) {
		switch (access) {
		case "private":
			return "-";
		case "public":
			return "+";
		case "protected":
			return "#";
		}
		return "";
	}
	

	/*
	 * 
	 */
	protected String sanitizedTitle() {
		return this.classTitle.replaceAll("\\W", "");
	}
	

	/**
	 * Helper method that replaces '<' and '>' with their ASCII representations
	 * so that DOT doesn't think that we're trying to use HTML tags.
	 * @param s
	 * @return the sanitized String
	 */
	protected String sanitize(String s) {
		return s.replaceAll("&", "&amp;")
				.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}

}
