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

	@Override
	public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
		List<IClassEdge> fromA = v.getEdges();
		
		for (IClassEdge e : fromA) {
			IClassVertex curVert = e.getTo();
			mergeBidirectional(v, curVert, g, d);
			this.setVisited(curVert);
		}
		
		this.setVisited(v);
	}
	
	public void mergeBidirectional(IClassVertex a, IClassVertex b, ClassNodeGraph g, DOMGraph dg) {
		List<IClassEdge> fromAtoB;
		List<IClassEdge> fromBtoA;
		
		fromAtoB = g.getEdgesFromTo(a, b);
		fromBtoA = g.getEdgesFromTo(b, a);
		
		if(fromBtoA.isEmpty() || fromBtoA == null ||
				fromAtoB.isEmpty() || fromAtoB == null) {
			return;
		}
		
		for (IClassEdge e1 : fromAtoB) {
			for (IClassEdge e2 : fromBtoA) {
				if(e1.getCorrespondingDOMNode() == null ||
						e2.getCorrespondingDOMNode() == null) {
					continue;
				}
				if(e1==e2){
					continue;
				}
				if (e1 instanceof AssociationEdge && e2 instanceof AssociationEdge) {
					merger(e1.getCorrespondingDOMNode(), e2.getCorrespondingDOMNode(), dg);
					
				} else if (e1 instanceof DependencyEdge && e2 instanceof DependencyEdge) {
					merger(e1.getCorrespondingDOMNode(), e2.getCorrespondingDOMNode(), dg);
				}
			}
		}
	}
	
	public void merger(IDOMEdgeNode e1, IDOMEdgeNode e2, DOMGraph dg) {
		String savedCardinality = e2.getAttribute("headlabel");
		
		if (savedCardinality == null) {
			savedCardinality = "\"\"";
		}
		
		e1.addAttribute("dir", "\"both\"");
		e1.addAttribute("arrowtail", "\"vee\"");
		e1.addAttribute("arrowhead", "\"vee\"");
		e1.addAttribute("taillabel", savedCardinality);
		
		dg.removeNodeFromDOMTree(e2);
	}
}
