package analyzers;

import java.util.ArrayList;
import java.util.List;

import DOMNodes.IDOMClassNode;
import graphNodes.AbstractClassVertex;
import graphNodes.ExtendsEdge;
import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import graphNodes.InterfaceVertex;
import graphNodes.RegularClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;
import projectFile.FieldData;

public class DecoratorDetector extends AbstractAnalyzer {
	
	//If B extends A (A is B's superclass) and has an instance of A, then B decorates A.
	//Also go up one more from the core abstract class
	//to the interface

	@Override
	public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {

		IDOMClassNode dn;
		this.setVisited(v);
		
		if (v instanceof AbstractClassVertex) {
			dn = v.getCorrespondingDOMNode();
			dn.addAttribute("color", "\"green\"");
//			if(((AbstractClassVertex) v).getImplementsEdges() != null) {
//				
//			}
		} else if (v instanceof InterfaceVertex) {
			
		} else if (v instanceof RegularClassVertex) {
			IClassVertex abs = v.getSuperclassEdge().getTo();
			if (abs != null
					&& extendsAbstract(v)) { //if extends something and that thing is abstract
				for (FieldData f : v.getFields()) {
					if (f.getFieldType().getTitle().equals(abs.getTitle())) { //ewwwww
						
					}
				}
			}
		}
		
	}
	
	public boolean extendsAbstract(IClassVertex v) {
		for (IClassEdge e : v.getEdges()) {
			if (e.getTo() instanceof AbstractClassVertex) {
				return true;
			}
		}
		return false;
	}
	
	
}
