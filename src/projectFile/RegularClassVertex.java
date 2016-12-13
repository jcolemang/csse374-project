package projectFile;

import java.util.List;

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

}
