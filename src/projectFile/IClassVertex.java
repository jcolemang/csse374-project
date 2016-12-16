package projectFile;

import java.util.List;

import org.objectweb.asm.tree.FieldNode;

public interface IClassVertex {
	public DOMClassNode getCorrespondingDOMNode();
	public DOMClassNode setCorrespondingDOMNode();
	public String getTitle();
	
	public void addMethodData(MethodData data);
	public void removeMethodData(MethodData data);
	public List<MethodData> getMethods();
	
	public List<IClassEdge> getEdges();
	
	public List<FieldData> getFields();
	public void addFieldData(FieldData data);
	public void removeFieldData(FieldData data);
	
	
}
