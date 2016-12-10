package projectFile;

import java.util.List;

public interface IClassVertex {
	public DOMClassNode getCorrespondingDOMNode();
	public String getTitle();
	public List<String> getMethodNames();
}
