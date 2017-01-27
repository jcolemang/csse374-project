package analyzers;

import DOMNodes.IDOMNode;
import graphNodes.IClassVertex;
import graphNodes.RegularClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;

public class ViolatesCompositionOverInheritanceAnalyzer extends AbstractAnalyzer {

	//If the vertex extends a class
	//And that class is concrete AND that class is NOT Object AND not abstract
	//It violates the convention of "composition over inheritence"
	@Override
	public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
		this.setVisited(v);
		if (v.getTitle().equals("java.lang.Object") ||
				!(v instanceof RegularClassVertex)) {
			return;
		}
		
		IDOMNode superDomNode = v.getSuperclassEdge().getTo().getCorrespondingDOMNode();
		IDOMNode superDomEdge = v.getSuperclassEdge().getCorrespondingDOMNode();
		
		//tapdancing all over the Law of Demeter
		if (checkViolation(v) && superDomNode != null) {
			superDomNode.addAttribute("fillcolor", "\"orange\"");
			superDomNode.addAttribute("style", "\"filled\"");
//			System.out.println(superDomNode.getTextRepresentation());
//			superDomEdge.addAttribute("color", "\"orange\"");
		}
		
	}

	private boolean checkViolation(IClassVertex v) {
		return (v.getSuperclassEdge().getTo() instanceof RegularClassVertex
				&& !(v.getSuperclassEdge().getTo().getTitle().equals("java.lang.Object")));
	}

}
