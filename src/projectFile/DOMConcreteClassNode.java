package projectFile;

import java.util.ArrayList;
import java.util.List;

public class DOMConcreteClassNode extends DOMAbstractBoxNode {

	@Override
	public String getTextRepresentation() {

		// Set the name of the node
		String title = this.getClassName();

		// Set the fields string
		String fieldsString = "\n";
		for (String s : this.fields) {
			fieldsString += s + "\\l";
		}

		// Set the method fields
		String methodFields = "";
		for (String s : this.methods) {
			methodFields += s + "\\l";
		}

		// Compile the text representation of the class to
		// be used as the class's DOT representation's label
		return this.sanitize(this.sanitizedTitle() + "[\n" +
				"label = \"{" + title + "|" + fieldsString + "|"
				+ methodFields + "}\"\n]");

	}

}
