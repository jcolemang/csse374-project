package projectFile;

public class DOMInterfaceNode extends DOMAbstractBoxNode {

	@Override
	public String getTextRepresentation() {

		// Set the name of the node
		String title = this.getClassName();

		// Set the method fields
		String methodFields = "";
		for (String s : this.methods) {
			methodFields += s + "\\l";
		}

		// Compile the text representation of the class to
		// be used as the class's DOT representation's label
		return "<i>" + this.sanitizedTitle()+ "</i>" + this.sanitize("[\n" +
				"label = \"{" + title + "|" +
				methodFields + "}\"\n]");

	}


}
