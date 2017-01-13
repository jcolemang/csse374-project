package analyzers;

import java.util.List;

import graphNodes.AssociationEdge;
import graphNodes.DependencyEdge;
import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;

public class AssociationSupercedesDependencyAnalyzer extends AbstractAnalyzer{

	@Override
	public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
		List<IClassEdge> edges = v.getEdges();
		List<IClassEdge> sharedEdges;
		IClassVertex otherNode;
		
		for (IClassEdge e : edges) {
			//it's implied that for this analyzer to be relevant
			//the class MUST have both an association and dependency edge,
			//therefore it has an association edge
			if(e instanceof AssociationEdge) { 
				if(e.getHeadTitle().equals(v.getTitle())) {
					otherNode = e.getTail();
				} else {
					otherNode = e.getHead();					
				}
				
				sharedEdges = g.getEdgesOfTwoNodes(v, otherNode);
				
				for (IClassEdge shared : sharedEdges) {
					if (shared instanceof DependencyEdge) {
						d.removeNodeFromDOMTree(shared.getCorrespondingDOMNode());
					}
				}
			}
		}
		
	}
}
