package DOMNodes;

import java.util.List;

import projectFile.FieldData;
import projectFile.MethodData;

public interface IDOMClassNode extends IDOMNode {
	String getTitle();
	String getDOTTitle();
	void setFields(List<FieldData> data);
	void setTitle(String title);
	void setMethods(List<MethodData> data);
	void setAccessLevel(String access);
}
