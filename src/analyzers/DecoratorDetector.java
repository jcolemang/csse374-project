package analyzers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.xpath.internal.operations.Bool;

import DOMNodes.IDOMClassNode;
import DOMNodes.IDOMNode;
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

	private Map<String, Boolean> abstractDecoratorMap = new HashMap<>();
	private Map<String, Boolean> extendsAbstract = new HashMap<>();

	@Override
	public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {

		if (!(v instanceof RegularClassVertex)) {
			return;
		}

        if (v.getSuperclassEdge() == null) {
            return;
        }

        // if extends something and that thing is abstract
        if (extendsAbstractDecorator(v)) {
			makeGreenAndAddToTitle(v);
		}

		// if it is abstract
		if (isAbstractDecorator(v)) {
			makeGreenAndAddToTitle(v);
			List<IClassEdge> interfsForAbs = v.getImplementsEdges();
			if (v.getSuperclassEdge() != null) {
				interfsForAbs.add(v.getSuperclassEdge());
			}
            for (IClassEdge e : interfsForAbs) {
                this.setVisited(e.getTo());
                addArrowTag(v.getSuperclassEdge());
                makeGreenAndAddToTitle(e.getTo());
            }
        }

	}


	private boolean isAbstractDecorator(IClassVertex v) {

		if (this.abstractDecoratorMap.getOrDefault(v.getTitle(), false)) {
			return true;
		}

		if (!(v instanceof AbstractClassVertex)) {
			this.abstractDecoratorMap.put(v.getTitle(), false);
			return false;
		}

		// getting all possible superclasses
        List<IClassVertex> potentialSupers = new LinkedList<>();
		if (v.getSuperclassEdge() != null) {
			potentialSupers.add(v.getSuperclassEdge().getTo());
		}
		for (IClassEdge edge : v.getImplementsEdges()) {
			potentialSupers.add(edge.getTo());
		}

		// checking if, for any of the superclasses, the current class contains them as
		// a field and overrides all of their methods
        for (IClassVertex s : potentialSupers) {
			if (v.containsField(s)) {
				this.abstractDecoratorMap.put(v.getTitle(), true);
			    return true;
			}
		}

		this.abstractDecoratorMap.put(v.getTitle(), false);
		return false;
	}


	public boolean extendsAbstractDecorator(IClassVertex v) {

		// checking if this has been visited
		if (this.extendsAbstract.containsKey(v.getTitle())) {
			if (this.extendsAbstract.get(v.getTitle()))	{
				return true;
			}

			return false;
		}

		// getting the supertype
		IClassEdge superTypeEdge = v.getSuperclassEdge();
		if (superTypeEdge == null) {
			return false;
		}
		IClassVertex superType = v.getSuperclassEdge().getTo();

		// do I extend an abstract decorator, or does my supertype extend an
		// abstract decorator?
		if (this.isAbstractDecorator(superType) ||
				this.extendsAbstractDecorator(superType)) {
		    this.extendsAbstract.put(v.getTitle(), true);
		    return true;
		}

		this.extendsAbstract.put(v.getTitle(), false);
		return false;
	}


	public void makeGreenAndAddToTitle(IClassVertex v) {
		IDOMClassNode n = v.getCorrespondingDOMNode();
		if (n != null) {
			n.addAttribute("style", "filled");
			n.addAttribute("color", "green");
			n.setTitle(n.getTitle() + "\\n\\<\\<Decorator\\>\\>");
		}
	}
	
	public void addArrowTag(IClassEdge e) {
		if (e.getCorrespondingDOMNode() != null) {
			e.getCorrespondingDOMNode().addAttribute("label", "\"   \\<\\<decorates\\>\\>\"");;			
		}
	}
}
