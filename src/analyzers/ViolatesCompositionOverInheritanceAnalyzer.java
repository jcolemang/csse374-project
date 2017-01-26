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
		if (v.toString().contains("Foo")) {
			System.out.println("I am here!!!");
			System.out.println(v);
		}
		
		//tapdancing all over the Law of Demeter
		if (checkViolation(v) && superDomNode != null) {
			superDomNode.addAttribute("fillcolor", "\"orange\"");
			superDomNode.addAttribute("style", "\"filled\"");
			superDomEdge.addAttribute("color", "\"orange\"");
		}
		
	}

	private boolean checkViolation(IClassVertex v) {
		
		System.out.println(v.getTitle());
		
		return (v.getSuperclassEdge().getTo() instanceof RegularClassVertex
				&& !(v.getSuperclassEdge().getTo().getTitle().equals("java.lang.Object")));
	}

}
