package projectFile;

import java.util.ArrayList;
import java.util.List;

public class PrimitiveVertex implements IClassVertex {
	
	private String className;
	
	public PrimitiveVertex(String className) {
		this.className = className;
	}

	@Override
	public DOMClassNode getCorrespondingDOMNode() {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public DOMClassNode setCorrespondingDOMNode() {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public String getTitle() {
		return this.className;
	}

	@Override
	public String setTitle(String title) {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public List<String> getMethodNames() {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public void addMethodData(MethodData data) {
		throw new RuntimeException("Not yet implemented");
		
	}

	@Override
	public void removeMethodData(MethodData data) {
		throw new RuntimeException("Not yet implemented");
		
	}

	@Override
	public List<IClassEdge> getEdges() {
		throw new RuntimeException("Not yet implemented");
	}

	@Override
	public List<FieldData> getFields() {
		return new ArrayList<FieldData>();
	}

	@Override
	public void addFieldData(FieldData data) {
		throw new RuntimeException("Not yet implemented");
		
	}

	@Override
	public void removeFieldData(FieldData data) {
		throw new RuntimeException("Not yet implemented");
		
	}

	@Override
	public void setFields() {
	}
	
	
	@Override
	public String toString() {
		return this.getTitle();
	}

	@Override
	public List<MethodData> getMethods() {
		// TODO Auto-generated method stub
		return null;
	}

}
