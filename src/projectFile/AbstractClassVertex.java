package projectFile;

import java.util.List;

public class AbstractClassVertex implements IClassVertex {

	private String title;

	public AbstractClassVertex(String title){
		this.title=title;
	}
	
	@Override
	public DOMNode getCorrespondingDOMNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	@Override
	public List<String> getMethodNames() {
		// TODO Auto-generated method stub
		return null;
	}

}
