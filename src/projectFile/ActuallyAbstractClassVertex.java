package projectFile;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class that contains all common methods for concrete instances of
 * IClassVertex. Also contains the common constructor, and all three common fields.
 * AbstractClassVertex, RegularClassVertex, and InterfaceVertex are all essentially
 * empty now.
 * 
 * @author wickersl
 *
 */

public abstract class ActuallyAbstractClassVertex implements IClassVertex {
	private String title;
	private List<FieldData> fields;
	private List<MethodData> methods;

	public ActuallyAbstractClassVertex(String title) {
		this.title = title;
		this.fields = new ArrayList<FieldData>();
		this.methods = new ArrayList<MethodData>();
	}

	@Override
	public DOMClassNode getCorrespondingDOMNode() {
		return null;
	}

	/**
	 * Returns the title of the class
	 * 
	 * @return String title
	 */
	@Override
	public String getTitle() {
		return this.title;
	}

	/**
	 * Sets the DOMNode that the class is associated with.
	 * 
	 * @return DOMClassNode
	 */
	@Override
	public DOMClassNode setCorrespondingDOMNode() {
		// TODO Implement this
		return null;
	}

	/**
	 * Returns a list of all edges attached to the class
	 * 
	 * @return List<IClassEdge>
	 */
	@Override
	public List<IClassEdge> getEdges() {
		// TODO Implement this
		return null;
	}

	/**
	 * Adds a new MethodData object into the class's List of personal methods
	 * 
	 * @param MethodData
	 *            data
	 */
	@Override
	public void addMethodData(MethodData data) {
		this.methods.add(data);
	}

	/**
	 * Removes the given MethodData object from the class's List of personal
	 * methods
	 * 
	 * @param MethodData
	 *            data
	 */
	@Override
	public void removeMethodData(MethodData data) {
		this.methods.remove(data);

	}

	/**
	 * Returns the list of the class's FieldData objects
	 * 
	 * @return List<FieldData>
	 */
	@Override
	public List<FieldData> getFields() {
		return this.fields;
	}

	/**
	 * Returns the List<MethodData> of the class's methods
	 * 
	 * @return List<MethodData>
	 */
	@Override
	public List<MethodData> getMethods() {
		return this.methods;
	}

	/**
	 * Adds a new FieldData object into the class's List<FieldData>
	 * 
	 * @param FieldData
	 *            data
	 */
	@Override
	public void addFieldData(FieldData data) {
		this.fields.add(data);

	}

	/**
	 * Removes the given FieldData object from the class's List of FieldData
	 * objects
	 * 
	 * @param FieldData
	 *            data
	 */
	@Override
	public void removeFieldData(FieldData data) {
		this.fields.remove(data);

	}

	/**
	 * A simple toString that returns the name of the class This name is
	 * currently the Java name and not the 'user-friendly name', e.x.,
	 * Java.lang.Math or Java.util.Treeset.
	 */
	@Override
	public String toString() {
		return this.getTitle();
	}

}