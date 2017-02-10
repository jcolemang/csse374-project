package DOMNodes;

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
		return this.sanitize(this.getDOTTitle()) + "[\n" +
				this.sanitize(this.attributeMapToString()).replaceAll("(\\+n)", "<br/>") +
				"label = <{<I>" + this.sanitize(title) +
				"</I><br/>" + this.sanitize(this.getTitleAdditions()) + "|" +
				this.sanitize(methodFields).replaceAll("\\\\l", "<br align=\"left\"/>") + 
				"}>\n]";

	}


}
