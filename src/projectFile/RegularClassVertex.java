package projectFile;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.tree.FieldNode;

/*
 * TODO make a "set corresponding DOM node" method
 */
public class RegularClassVertex implements IClassVertex {

	private String title;
	private List<FieldData> fields;
	private List<MethodData> methods;

	public RegularClassVertex(String title){
		this.title = title;
		this.fields = new ArrayList<FieldData>();
		this.methods = new ArrayList<MethodData>();
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
	public String setTitle(String title) {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public void addMethodData(MethodData data) {
		throw new RuntimeException("Not implemented yet");
		
	}

	@Override
	public void removeMethodData(MethodData data) {
		throw new RuntimeException("Not implemented yet");
		
	}

	@Override
	public List<FieldData> getFields() {
		return this.fields;
	}
	
	@Override
	public List<MethodData> getMethods() {
		return this.methods;
	}

	@Override
	public void addFieldData(FieldData data) {
		this.fields.add(data);
		
	}

	@Override
	public void removeFieldData(FieldData data) {
		throw new RuntimeException("Not implemented yet");
		
	}

	@Override
	public void setFields() {
		throw new RuntimeException("Not implemented yet");
		
	}
	
	
	@Override
	public String toString() {
		return this.getTitle();
	}

}
