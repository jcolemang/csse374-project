package projectFile;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.tree.FieldNode;

public class InterfaceVertex implements IClassVertex {

	private String title;
	private List<FieldData> fields;

	public InterfaceVertex(String title) {
		this.fields = new ArrayList<FieldData>();
		this.title = title;
	}

	@Override
	public DOMClassNode getCorrespondingDOMNode() {
		return null;

	}

	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public List<String> getMethodNames() {
		return null;
	}

	@Override
	public DOMClassNode setCorrespondingDOMNode() {
		// TODO Implement this
		return null;
	}

	@Override
	public List<IClassEdge> getEdges() {
		// TODO Implement this
		return null;
	}

	@Override
	public List<FieldData> getFields() {
		// TODO Auto-generated method stub
		return this.fields;
	}

	@Override
	public String setTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMethodData(MethodData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMethodData(MethodData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFieldData(FieldData data) {
		this.fields.add(data);
	}

	@Override
	public void removeFieldData(FieldData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFields() {
		// TODO Auto-generated method stub
	}
	
	
	@Override
	public String toString() {
		return this.getTitle();
	}

}
