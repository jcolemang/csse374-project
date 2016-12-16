package projectFile;

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
		
		return this.getDOTTitle() + "[\nlabel = <{<I>" +
			this.sanitize(title) + "</I>|" + 
			this.sanitize(fields).replaceAll("\\\\l", "<br align=\"left\"/>") +
			"\n|\n" +
			this.sanitize(methods).replaceAll("\\\\l", "<br align=\"left\"/>") 
			+ "}>\n]";
		
	}

}
