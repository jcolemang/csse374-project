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
		// TODO Auto-generated method stub
		return null;
	}

}
