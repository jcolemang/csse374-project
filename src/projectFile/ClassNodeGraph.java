package projectFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClassNodeGraph {
	
	private List<IClassVertex> classVertices;
	private List<IClassEdge> classEdges;
	private Map<String, IClassVertex> nameToVertex;
	
	/**
	 * Create a graph of classnodes with classVertices and classEdges.
	 */
	public ClassNodeGraph() {
		this.classVertices = new ArrayList<IClassVertex>();
		this.classEdges = new ArrayList<IClassEdge>();
		this.nameToVertex = new HashMap<String, IClassVertex>();
	}
	
	/**
	 * Return all classEdges of ClassNodeGraph as a list of IClassEdge.
	 * @return
	 */
	public List<IClassEdge> getEdges() {
		return this.classEdges;
	}
	
	/**
	 * Return all classVertices of ClassNodeGraph as a list of IClassVertex.
	 * @return
	 */
	public List<IClassVertex> getVertices() {
		return this.classVertices;
	}
	
	
	public IClassVertex getVertex(String name) {
		return this.nameToVertex.get(name);
	}
	
	
	/**
	 * Add a IClassVertex in the list of classVertices
	 * @param vertex
	 */
	public void addClassVertex(IClassVertex vertex){
		this.classVertices.add(vertex);
		this.nameToVertex.put(vertex.getTitle(), vertex);
	}
	
	/**
	 * Add a IClassEdge in the list of classEdges.
	 * @param edge
	 */
	public void addClassEdge(IClassEdge edge){
		this.classEdges.add(edge);
	}
	
	/**
	 * Generate the ClassNodeGraph as String 
	 * with a list of Class title from each IClassVertex 
	 * in the list of classVertices
	 */
	public String toString(){
		String str = "";
		for (IClassVertex icv:classVertices){
			str += "Class title: " + icv.getTitle() + "\n";
		}
		
		return str;
	}
}
