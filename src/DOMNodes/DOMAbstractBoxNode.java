package DOMNodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import graphNodes.IClassVertex;
import projectFile.FieldData;
import projectFile.MethodData;

public abstract class DOMAbstractBoxNode implements IDOMClassNode {
	
	private static final int PRIVATE = 3;
	private static final int PROTECTED = 2;
	private static final int DEFAULT = 1;
	private static final int PUBLIC = 0;
	
	Map<String, String> aestheticAttributes = new HashMap<String, String>();
	
//	String OutlineColor = "black";
//	String BGColor = "white";
//	String Font = "SansSerif";
	String classTitle = "";
//	String color;
	private int accessLevel = 0;

	protected List<String> fields;
	protected List<String> methods;


	public void setTitle(String title) {
		this.classTitle = title;
	}

	public void setAccessLevel(String access) {
		this.accessLevel = this.translateAccessLevel(access);
	}
	
	@Override
	public void addAttribute(String dotAttrName, String property) {
		this.aestheticAttributes.put(dotAttrName, property);
	}

	@Override
	public String attributeMapToString() {
		String built = "";
		
		if(this.aestheticAttributes.isEmpty()) {
			return built;
		}
		
		for (String attr : this.aestheticAttributes.keySet()) {
			built += attr + " = " + this.aestheticAttributes.get(attr) + ", ";
		}
		
		return built.substring(0, built.length() - 2);
	}
	

	private int translateAccessLevel(String access) {
		switch (access) {
		case "public":
			return DOMAbstractBoxNode.PUBLIC;
		case "private":
			return DOMAbstractBoxNode.PRIVATE;
		case "protected":
			return DOMAbstractBoxNode.PROTECTED;
		case "default":
			return DOMAbstractBoxNode.DEFAULT;
		}
		throw new IllegalArgumentException("Illegal access level");
	}
	

	protected boolean shouldRender(String access) {
		int num = this.translateAccessLevel(access);
		return num <= this.accessLevel;
	}


	public String getClassName() {
		return this.classTitle;
	}
	

	public String getDOTTitle() {
		return this.classTitle.replaceAll("\\W", "");
	}
	
	
	
	/**
	 * Initializes the DOMClassNode's this.fields to be an array filled with
	 * string representations of each field in the class.
	 * @param data
	 */
	public void setFields(List<FieldData> data) {
		String typeString;
		this.fields = new ArrayList<String>();

		
		for (FieldData f : data) {
			if (this.shouldRender(f.getAccessLevel())) {
				if (f.getTypeParameterString().length() == 0) {
					typeString = f.getFieldType().toString();
				} else {
					typeString = f.getTypeParameterString();
				}

				this.fields.add(this.accessStringToSign(f.getAccessLevel()) +
						f.getFieldName() + ": " + typeString);
			}
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

			// testing the access level of the methods
			if (!this.shouldRender(m.getAccessLevel())) {
				continue;
			}
			
			for (IClassVertex f : m.getParams()) { // For every parameter of that method
				paramInfo += f + ", ";
			}

			if (paramInfo.length() != 0) { // chop off trialing ", "
				paramInfo = paramInfo.substring(0, paramInfo.length() - 2);
			}

			this.methods.add(this.accessStringToSign(m.getAccessLevel()) +
					m.getMethodName() + "(" + paramInfo + "): " + 
					this.sanitize(m.getReturnTypeString()) + "\n");
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
	
	
	@Override
	public String getTitle() {
		return this.classTitle;
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


	public abstract String getTextRepresentation();
}
