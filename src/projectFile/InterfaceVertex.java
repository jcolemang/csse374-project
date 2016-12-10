package projectFile;

public class InterfaceVertex implements IClassVertex {
	
	private String title = "";

	@Override
	public DOMNode getCorrespondingDOMNode() {

	}

	@Override
	public String getTitle() {
		return this.title;
	}

}
