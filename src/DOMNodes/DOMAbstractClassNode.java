package DOMNodes;

public class DOMAbstractClassNode extends DOMAbstractBoxNode {

	@Override
	public String getTextRepresentation() {
		String title = this.getClassName();
		String fields = "";
		String methods = "";
		
		for (String s : this.fields) {
			fields += s + "\\l";
		}

		for (String s : this.methods) {
			methods += s + "\\l";
		}
		
		return this.getDOTTitle() + "[\n" + this.sanitize(this.attributeMapToString()) +
				"label = <{<I>" +
				this.sanitize(title) + "</I><br/>" + this.sanitize(this.getTitleAdditions()).replaceAll("(\\+n)", "<br/>") + "|" +
				this.sanitize(fields).replaceAll("\\\\l", "<br align=\"left\"/>") +
				"\n|\n" +
				this.sanitize(methods).replaceAll("\\\\l", "<br align=\"left\"/>") +
				"}>\n]";
		
	}

}
