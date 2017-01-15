package analyzers;

import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;

public class CardinalityAdderAnalyzer extends AbstractAnalyzer{
	
	@Override
	public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
		
		for (IClassEdge e : v.getEdges()) {
			
		}
	}

}
