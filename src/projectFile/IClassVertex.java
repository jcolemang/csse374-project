package projectFile;

import java.util.List;

import org.objectweb.asm.tree.FieldNode;

public interface IClassVertex {
	public DOMClassNode getCorrespondingDOMNode();
	public DOMClassNode setCorrespondingDOMNode();
	public String getTitle();
	public String setTitle(String title);
	public List<String> getMethodNames();
	public void addMethodData(MethodData data);
	public void removeMethodData(MethodData data);
	public List<IClassEdge> getEdges();
	public List<FieldNode> getFields();
	public void addFieldData(FieldData data);
	public void removeFieldData(FieldData data);
	public void setFields();
	
}
