package analyzers;

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
		
		//tapdancing all over the Law of Demeter
		if(checkViolation(v) && v.getSuperclassEdge().getTo().getCorrespondingDOMNode() != null) {
			v.getCorrespondingDOMNode().addAttribute("color", "\"orange\"");
			v.getSuperclassEdge().getTo().getCorrespondingDOMNode().addAttribute("color", "\"orange\"");
		}
		
		this.setVisited(v);
		
	}

	private boolean checkViolation(IClassVertex v) {
		
		System.out.println(v.getTitle());
		
		return (v instanceof RegularClassVertex
				&& !(v.getTitle().equals("java.lang.Object"))
				&& v.getSuperclassEdge().getTo() instanceof RegularClassVertex
				&& !(v.getSuperclassEdge().getTo().getTitle().equals("java.lang.Object")));
	}

}
