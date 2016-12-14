package projectFile;

import java.util.List;

import org.objectweb.asm.tree.FieldNode;

/*
 * TODO make a "set corresponding DOM node" method
 */
public class RegularClassVertex implements IClassVertex {

	private String title;

	public RegularClassVertex(String title){
		this.title=title;
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
