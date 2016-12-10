package projectFile;

import java.util.ArrayList;
import java.util.List;


public class ClassNodeGraph {
	
//	private List<IAnalyzer> analyzers;
	private List<IClassVertex> classVertices;
	private List<IClassEdge> classEdges;
	
	public ClassNodeGraph(String[] classNames){
		
		this.classVertices = new ArrayList<IClassVertex>();
		this.classEdges = new ArrayList<IClassEdge>();
		
	}
	
	
	public void addClassVertex(IClassVertex vertex){
		this.classVertices.add(vertex);
	}
	
	public void addClassEdge(IClassEdge edge){
		this.classEdges.add(edge);
		
	}
}
