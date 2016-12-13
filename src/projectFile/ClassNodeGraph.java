package projectFile;

import java.util.ArrayList;
import java.util.List;


public class ClassNodeGraph {
	
	private List<IClassVertex> classVertices;
	private List<IClassEdge> classEdges;
	
	public ClassNodeGraph() {
		
		this.classVertices = new ArrayList<IClassVertex>();
		this.classEdges = new ArrayList<IClassEdge>();
		
	}
	
	public List<IClassEdge> getEdges() {
		return this.classEdges;
	}
	
	public List<IClassVertex> getVertices() {
		return this.classVertices;
	}
	
	public void addClassVertex(IClassVertex vertex){
		this.classVertices.add(vertex);
	}
	
	public void addClassEdge(IClassEdge edge){
		this.classEdges.add(edge);
		
	}
	
	public String toString(){
		String str = "";
		for (IClassVertex icv:classVertices){
			str += "Class title: " + icv.getTitle() + "\n";
		}
		
		return str;
	}
}
