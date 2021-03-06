package analyzers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import CommandLineArgument.Configuration;
import DOMNodes.IDOMClassNode;
import graphNodes.AbstractClassVertex;
import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import graphNodes.RegularClassVertex;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;
import projectFile.MethodData;

public class DecoratorDetector extends AbstractAnalyzer {
	
	//If B extends A (A is B's superclass) and has an instance of A, then B decorates A.
	//Also go up one more from the core abstract class
	//to the interface

	private Map<String, Boolean> abstractDecoratorMap = new HashMap<>();
	private Map<String, Boolean> extendsAbstract = new HashMap<>();

	@Override
	public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
		
	    if (v.getCorrespondingDOMNode() == null) {
	        return;
        }
	    
	    if (v.getTitle().equals("java.lang.Object")){
	    	return;
	    }
	   
        // if extends something and that thing is abstract
        if (extendsAbstractDecorator(v)) {
            makeGreenAndAddToTitle(v, "\\n<<Decorator>>");
            if (isBadDecorator(v)) {
                v.getCorrespondingDOMNode().addAttribute("color", "red");
            }
		}

		// if it is abstract
		if (isAbstractDecorator(v)) {
			if (v instanceof RegularClassVertex) {
        		makeGreenAndAddToTitle(v, "\\n<<Decorator>>");        		
        	} else if (v instanceof AbstractClassVertex) {
        		makeGreenAndAddToTitle(v, "<<Decorator>>");
        	}
			List<IClassEdge> interfsForAbs = v.getImplementsEdges();
			if (v.getSuperclassEdge() != null) {
				interfsForAbs.add(v.getSuperclassEdge());
			}
            for (IClassEdge e : interfsForAbs) {
                this.setVisited(e.getTo());
                addArrowTag(e);
                makeGreenAndAddToTitle(e.getTo(), "<<Component>>");
            }
        }
	}


	private boolean isAbstractDecorator(IClassVertex v) {

		if (this.abstractDecoratorMap.getOrDefault(v.getTitle(), false)) {
			return true;
		}

		if (this.extendsAbstractDecorator(v)) {
			return false;
		}

		// getting all possible superclasses
        List<IClassVertex> potentialSupers = new LinkedList<>();
		if (v.getSuperclassEdge() != null
                && !v.getSuperclassEdge().getTo().getTitle().equals("java.lang.Object")) {
            potentialSupers.add(v.getSuperclassEdge().getTo());
		}
		for (IClassEdge edge : v.getImplementsEdges()) {
			potentialSupers.add(edge.getTo());
		}

		if (potentialSupers.size() == 0) {
//			System.out.println("No supers");
		    return false;
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
//		System.out.println("Failed here");
		return false;
	}


	public boolean extendsAbstractDecorator(IClassVertex v) {
	    return getAbstractDecorator(v) != null;
//		// checking if this has been visited
//		if (this.extendsAbstract.containsKey(v.getTitle())) {
//			return this.extendsAbstract.get(v.getTitle());
//		}
//
//		// getting the supertype
//		IClassEdge superTypeEdge = v.getSuperclassEdge();
//		if (superTypeEdge == null) {
//            this.extendsAbstract.put(v.getTitle(), false);
//			return false;
//		}
//		IClassVertex superType = v.getSuperclassEdge().getTo();
//
//		// do I extend an abstract decorator, or does my supertype extend an
//		// abstract decorator?
//		if (this.isAbstractDecorator(superType) ||
//				this.extendsAbstractDecorator(superType)) {
//		    this.extendsAbstract.put(v.getTitle(), true);
//		    return true;
//		}
//
//		this.extendsAbstract.put(v.getTitle(), false);
//		return false;
	}


	public IClassVertex getAbstractDecorator(IClassVertex v) {

        // getting the supertype
        IClassEdge superTypeEdge = v.getSuperclassEdge();
        if (superTypeEdge == null) {
            return null;
        }

        IClassVertex superType = v.getSuperclassEdge().getTo();
        if (this.isAbstractDecorator(superType)) {
            return superType;
        }

        return getAbstractDecorator(superType);
    }


	public void makeGreenAndAddToTitle(IClassVertex v, String tag) {
		IDOMClassNode n = v.getCorrespondingDOMNode();
		if (n != null) {
			n.addAttribute("style", "filled");
			n.addAttribute("fillcolor", "green");
			n.setTitleAdditions(tag);
		}
	}


	public void addArrowTag(IClassEdge e) {
		if (e.getCorrespondingDOMNode() != null) {
			e.getCorrespondingDOMNode().addAttribute("label", "\"  \\<\\<decorates\\>\\>\"");
		}
	}


	public boolean isBadDecorator(IClassVertex curr) {
        IClassVertex decor = getAbstractDecorator(curr);
        if (decor == null) {
            return true;
        }

        for (MethodData data : decor.getMethods()) {
            if (!curr.getMethods().contains(data)) {
                return true;
            }
        }

        return false;
    }


}
