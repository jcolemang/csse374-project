package projectFile;

import java.util.ArrayList;
import java.util.List;

public class DOMClassNode implements IDOMNode {

	//TODO: maybe remove/move this because the setEdgeType method was removed?
	public enum EdgeType {
		EXTENDS, IMPLEMENTS, HAS_A, DEPENDS_ON
	}

	String OutlineColor = "black";
	String BGColor = "white";
	String Font = "SansSerif";
	String classTitle = "";
	String color;
	// EdgeType type;

	private List<String> fields;
	private List<String> methods;

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

	public String getClassType() {
		return "";
	}

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
	 * Returns the text representation for the entire class. The string returned
	 * is the label for the class's DOT node. The '\\l' means "newline and align left"
	 * and the '|' means "make a horizontal line through the box shape (to split 
	 * the class's name and its fields and its methods)"
	 * @return String (the text representation of the entire class)
	 */
	@Override
	public String getTextRepresentation() {

		// Set the name of the node
		String title = this.getClassName();

		// Set the fields string
		String fieldsString = "\n";
		for (String s : this.fields) {
			fieldsString += s + "\\l";
		}

		// Set the method fields
		String methodFields = "";
		for (String s : this.methods) {
			methodFields += s + "\\l";
		}

		// Compile the text representation of the class to
		// be used as the class's DOT representation's label
		return this.sanitize(title.replaceAll("\\.", "") + "[\n" +
				"label = \"{" + title + "|" + fieldsString + "|"
				+ methodFields + "}\"\n]");

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
	private String accessStringToSign(String access) {
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

	/**
	 * Helper method that replaces '<' and '>' with their ASCII representations
	 * so that DOT doesn't think that we're trying to use HTML tags.
	 * @param s
	 * @return the sanitized String
	 */
	private String sanitize(String s) {
		return s.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

}
