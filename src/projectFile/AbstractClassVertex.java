package projectFile;

import java.util.List;

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

}
