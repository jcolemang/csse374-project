package graphNodes;

import java.util.ArrayList;
import java.util.List;

import DOMNodes.IDOMClassNode;
import projectFile.CodeData;
import projectFile.FieldData;
import projectFile.MethodData;

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
	private List<CodeData> codeDatas;
	private List<IClassEdge> edges;
	
	private boolean test = false;
	
	private IDOMClassNode myDOMNode;

	public ActuallyAbstractClassVertex(String title) {
		this.title = title;
		this.fields = new ArrayList<FieldData>();
		this.methods = new ArrayList<MethodData>();
		this.edges = new ArrayList<IClassEdge>();
	}
	
	public IDOMClassNode getCorrespondingDOMNode() {
		return this.myDOMNode;
	}
	
	@Override
	public void setCorrespondingDOMNode(IDOMClassNode domNode) {
		this.test = true;
		this.myDOMNode = domNode;
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
	 * Returns a list of all edges attached to the class
	 * 
	 * @return List<IClassEdge>
	 */
	@Override
	public List<IClassEdge> getEdges() {
		return this.edges;
	}
	
	public void addEdge(IClassEdge edge) {
		this.edges.add(edge);
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


	@Override
	public void addCodeData(CodeData data) {
		this.codeDatas.add(data);
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


	@Override
	public List<CodeData> getCodeData() {
		return this.codeDatas;
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
	
	@Override
	public void removeEdge(IClassEdge edge){
		for (int i = 0; i<this.edges.size();i++){
			if(edge.equals(this.edges.get(i))){
				this.edges.remove(i);
			}
		}
	}
	
	/**
	 * Returns the edge that denotes the vertex's superclass
	 */
	public IClassEdge getSuperclassEdge() {
		
		IClassEdge superclassEdge = null;
		
		for (IClassEdge e : this.edges) {
			if (e instanceof ExtendsEdge) {
				superclassEdge = e;
			}
		}
		
		return superclassEdge;
	}
	
	/**
	 * Returns a list of edges that denote the vertex's inheritance from
	 * other classes
	 * @return
	 */
	
	public List<IClassEdge> getImplementsEdges() {
		List<IClassEdge> implementsEdges = new ArrayList<IClassEdge>();
		
		for (IClassEdge e : this.edges) {
			if (e instanceof ImplementsEdge) {
				implementsEdges.add(e);
			}
		}
		
		return implementsEdges;
	}


	@Override
	public boolean equals(Object other) {
		if (!(other instanceof IClassVertex)) {
			return false;
		}
		return ((IClassVertex)other).getTitle().equals(this.getTitle());
	}

}
