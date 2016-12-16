package projectFile;

import java.util.List;

/**
 * This is an interface for some kinds of DOMNode, like DOMClassNode and DOMEdgeNode.
 * 
 * @author Administrator
 *
 */
public interface IDOMNode {
	
	/**
	 * Returns the text representation for the entire class. The string returned
	 * is the label for the class's DOT node. The '\\l' means "newline and align left"
	 * and the '|' means "make a horizontal line through the box shape (to split 
	 * the class's name and its fields and its methods)"
	 * @return String (the text representation of the entire class)
	 */
	String getTextRepresentation();
	
}
