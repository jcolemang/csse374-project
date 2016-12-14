package projectFile;

public class FieldData {
	private String accessLevel;
	private String fieldName;
	private String fieldType;
	
	public FieldData(String accessLevel, String fieldName, String fieldType2) {
		this.accessLevel = accessLevel;
		this.fieldName = fieldName;
		this.fieldType = fieldType2;
	}
	
	
	public String getAccessLevel() {
		return this.accessLevel;
	}

	public String getFieldName() {
		return this.fieldName;
	}

	public String getFieldType() {
		return this.fieldType;
	}
	
	// TODO: Maybe add methods to get/set each of FieldData's fields
	
}
