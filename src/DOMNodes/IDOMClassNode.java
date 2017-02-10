package DOMNodes;

import java.util.List;

import projectFile.FieldData;
import projectFile.MethodData;

public interface IDOMClassNode extends IDOMNode {
	String getTitle();
	void setTitle(String title);
	void setTitleAdditions(String add);
	String getTitleAdditions();
	String getDOTTitle();
	void setFields(List<FieldData> data);
	void setMethods(List<MethodData> data);
	void setAccessLevel(String access);
}
