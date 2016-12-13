package projectFile;

import java.util.List;

public interface IClassVertex {
	public DOMClassNode getCorrespondingDOMNode();
	public DOMClassNode setCorrespondingDOMNode();
	public String getTitle();
	public List<String> getMethodNames();
	public List<IClassEdge> getEdges();
}
