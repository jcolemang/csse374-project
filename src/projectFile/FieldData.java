package projectFile;

import java.util.List;

import graphNodes.IClassVertex;

public class FieldData {
	private String accessLevel;
	private String fieldName;
	private IClassVertex fieldType;
	private String typeParamStr;
	private List<IClassVertex> typeParamVertices;
	
	public FieldData(String accessLevel, String fieldName, IClassVertex fieldType, 
			String typeParamStr, List<IClassVertex> typeParamVertices) {
		this.accessLevel = accessLevel;
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.typeParamStr = typeParamStr;
		this.typeParamVertices = typeParamVertices;
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
	
	public String getTypeParameterString() {
		return this.typeParamStr == null ? "" : this.typeParamStr;
	}
	
	public List<IClassVertex> getTypeParameterVertices() {
		return this.typeParamVertices;
	}
	
}
