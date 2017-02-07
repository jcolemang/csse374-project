package analyzers;

import java.util.List;

import graphNodes.AbstractClassVertex;
import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
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

		this.setVisited(v);
		
		if (v instanceof RegularClassVertex) {
			IClassVertex abs = v.getSuperclassEdge().getTo();
			if (abs != null
					&& extendsAbstract(v)) { //if extends something and that thing is abstract
				for (FieldData f : v.getFields()) {
					if (f.getFieldType().getTitle().equals(abs.getTitle())) { //ewwwww
						makeGreen(v);
						makeGreen(abs);
					}
				}
			}
			
			//Turns interface(s) green
			List<IClassEdge> intForAbs = abs.getImplementsEdges();
			if(intForAbs != null) {
				for (IClassEdge e : intForAbs) {
					makeGreen(e.getTo());
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
	
	public void makeGreen(IClassVertex v) {
		v.getCorrespondingDOMNode().addAttribute("color", "\"green\"");
	}
}
