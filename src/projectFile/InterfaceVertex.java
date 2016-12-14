package projectFile;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.tree.FieldNode;

public class InterfaceVertex implements IClassVertex {

	private String title;
	private List<MethodData> methods;
	private List<FieldData> fields;

	public InterfaceVertex(String title) {
		this.title = title;
		this.fields = new ArrayList<FieldData>();
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
	public ArrayList<FieldData> getFields() {
		// TODO Auto-generated method stub
		return new ArrayList<FieldData>();
	}
	
	@Override
	public List<MethodData> getMethods() {
		return this.methods;
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
		this.fields.remove(data);
	}

	@Override
	public void setFields() {
		throw new IllegalArgumentException("Interfaces don't have fields, do not attempt");
	}
	
	
	@Override
	public String toString() {
		return this.getTitle();
	}

}
