package projectFile;

import graphNodes.IClassVertex;

public class FieldData {
	private String accessLevel;
	private String fieldName;
	private IClassVertex fieldType;
	
	public FieldData(String accessLevel, String fieldName, IClassVertex fieldType) {
		this.accessLevel = accessLevel;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
	}
	
	
	public String getAccessLevel() {
		return this.accessLevel;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public IClassVertex getFieldType() {
		return this.fieldType;
	}
	
	// TODO: Maybe add methods to set each of FieldData's fields
	
}
