package projectFile;

import java.util.List;

import org.objectweb.asm.tree.FieldNode;

public class AbstractClassVertex implements IClassVertex {

	private String title;

	public AbstractClassVertex(String title){
		this.title=title;
	}
	
	@Override
	public DOMClassNode getCorrespondingDOMNode() {
		// TODO Implement this
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Implement this
		return this.title;
	}

	@Override
	public List<String> getMethodNames() {
		// TODO Implement this
		return null;
	}

	@Override
	public DOMClassNode setCorrespondingDOMNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IClassEdge> getEdges() {
		// TODO Auto-generated method stub
		return null;
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
	public List<FieldNode> getFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFieldData(FieldData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFieldData(FieldData data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFields() {
		// TODO Auto-generated method stub
		
	}

}
