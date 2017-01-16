package projectFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphNodes.IClassEdge;
import graphNodes.IClassVertex;


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
	 * Add a IClassEdge in the list of classEdges.
	 * @param edge
	 */
	public void addClassEdge(IClassEdge edge){
		this.classEdges.add(edge);
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
	
	/**
	 * Return the single vertex with the given name
	 * @param name
	 * @return
	 */
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
	
	
	public List<IClassEdge> getEdgesFromTo(IClassVertex n1, IClassVertex n2) {
		List<IClassEdge> edges = new ArrayList<>();
		for (IClassEdge e : n1.getEdges()) {
			if (e.getTail().getTitle().equals(n2.getTitle())) {
				edges.add(e);
			}
		}
		return edges;
	}
	
	
	public List<IClassEdge> getEdgesOfTwoNodes(IClassVertex n1, IClassVertex n2) {
		List<IClassEdge> edges = new ArrayList<IClassEdge>();
		List<IClassEdge> potentiallySharedEdges = n1.getEdges();
		
		for (int i = 0; i < potentiallySharedEdges.size(); i++) {
			IClassEdge currentEdge = potentiallySharedEdges.get(i);
			if (currentEdge.getHeadTitle().equals(n2.getTitle()) && 
					currentEdge.getTailTitle().equals(n1.getTitle()) ||
					currentEdge.getTailTitle().equals(n2.getTitle()) &&
					currentEdge.getHeadTitle().equals(n1.getTitle())) {
				edges.add(currentEdge);
			}
		}
		
		return edges;
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
	
	
	public boolean containsEdgeType(IClassVertex from, IClassVertex to, Class<? extends IClassEdge> clazz) {
		List<IClassEdge> edges = this.getEdgesOfTwoNodes(from, to);
		for (IClassEdge e : edges) {
			if (clazz.equals(e.getClass())) {
				return true;
			}
		}
		return false;
	}
	
	
	public IClassEdge getEdgeWithType(IClassVertex from, IClassVertex to, Class<? extends IClassEdge> clazz) {
		for (IClassEdge e : this.classEdges) {
			if (e.getHead().getTitle().equals(from.getTitle())) {
                if (e.getTail().getTitle().equals(to.getTitle())) {
                	if (e.getClass().equals(clazz)) {
                		return e;
                	}
                }
				
			}
		}
		
		throw new IllegalArgumentException();
	}

}
