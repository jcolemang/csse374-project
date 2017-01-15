package analyzers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphNodes.ExtendsEdge;
import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import graphNodes.InterfaceVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;

public class IsACollectionAnalyzer extends AbstractAnalyzer {
	
	static String collectionString = "java.util.Collection";
	private Map<String, Boolean> extendsCollectionMap = new HashMap<>();
	
	public void setVertexToCheck() {
		
	}

	@Override
	public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
		
		if (this.extendsCollection(v, g)) {
			System.out.println(v + " extends collection");
		}
		
		if (v.getTitle().contains("HashMap")) {
			System.out.println(v.getTitle());
		}
	}
	
	public boolean extendsCollection(IClassVertex v, ClassNodeGraph g) {
		
		if (v.getTitle().equals(collectionString)) {
			this.extendsCollectionMap.put(v.getTitle(), true);
			return true;
		}
		
		if (this.extendsCollectionMap.getOrDefault(v.getTitle(), false)) {
			return true;
		}
		
//		if (this.wasVisited(v)) {
//			return false;
//		}
		
		// avoiding repeat computations
		this.setVisited(v);

		List<IClassEdge> edges = v.getEdges();
		for (IClassEdge e : edges) {
			if (e instanceof ExtendsEdge) {
				if (this.extendsCollection(e.getHead(), g)) {
					this.extendsCollectionMap.put(v.getTitle(), true);
					return true;
				}
					
			}
			
			if (e instanceof InterfaceVertex) {
				if (this.extendsCollection(e.getHead(), g)) {
					this.extendsCollectionMap.put(v.getTitle(), true);
					return true;
				}
			}
		}
		
		return false;
	}

}
