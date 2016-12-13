package projectFile;

import java.util.List;

public class InterfaceVertex implements IClassVertex {
	
	private String title;

	public InterfaceVertex(String title){
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

}
