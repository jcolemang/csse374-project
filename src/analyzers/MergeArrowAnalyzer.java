package analyzers;

import java.util.List;

import DOMNodes.IDOMEdgeNode;
import graphNodes.AssociationEdge;
import graphNodes.DependencyEdge;
import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;

public class MergeArrowAnalyzer extends AbstractAnalyzer {

	private ClassNodeGraph cng;
	private DOMGraph dg;


	@Override
	public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
		this.cng = g;
		this.dg = d;
		List<IClassEdge> fromA = v.getEdges();
		
		for (IClassEdge e : fromA) {
			IClassVertex curVert = e.getHead(); //Maybe e.getTail();
			mergeBidirectional(v, curVert);
			this.setVisited(curVert);
		}		
	}
	
	public void mergeBidirectional(IClassVertex a, IClassVertex b) {
		List<IClassEdge> fromAtoB;
		List<IClassEdge> fromBtoA;
		
		fromAtoB = this.cng.getEdgesFromTo(a, b);
		fromBtoA = this.cng.getEdgesFromTo(b, a);
		
		if(fromBtoA.isEmpty() || fromBtoA == null) {
			return;
		}
		
		for (IClassEdge e1 : fromAtoB) {
			for (IClassEdge e2 : fromBtoA) {
				
				if(e1.getCorrespondingDOMNode() == null ||
						e2.getCorrespondingDOMNode() == null) {
					continue;
				}
				
				if (e1 instanceof AssociationEdge && e2 instanceof AssociationEdge) {
					merger (e1.getCorrespondingDOMNode(), e2.getCorrespondingDOMNode());
					
				} else if (e1 instanceof DependencyEdge && e2 instanceof DependencyEdge) {
					merger(e1.getCorrespondingDOMNode(), e2.getCorrespondingDOMNode());
				}
			}
		}
	}
	
	public void merger(IDOMEdgeNode e1, IDOMEdgeNode e2) {
		String savedCardinality = e2.getAttribute("headlabel");
		
		if (savedCardinality == null) {
			savedCardinality = "\"\"";
		}
		
		e1.addAttribute("dir", "\"both\"");
		e1.addAttribute("headlabel", savedCardinality);
		System.out.println("Attribute added! Attributes are: " + e1.attributeMapToString());
		
		this.dg.removeNodeFromDOMTree(e2);
		System.out.println("Extra arrow removed");
	}
}
