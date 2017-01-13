package projectFile;

import java.util.List;

import graphNodes.IClassVertex;

public class FieldData {
	private String accessLevel;
	private String fieldName;
	private IClassVertex fieldType;
	private List<IClassVertex> typeParams;
	
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
	
}
