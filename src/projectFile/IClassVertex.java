package projectFile;

import java.util.List;

public interface IClassVertex {
	public DOMNode getCorrespondingDOMNode();
	public String getTitle();
	public List<String> getMethodNames();
}
