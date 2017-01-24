package analyzers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphNodes.AssociationEdge;
import graphNodes.DependencyEdge;
import graphNodes.ExtendsEdge;
import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import graphNodes.ImplementsEdge;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;
import projectFile.FieldData;
import projectFile.MethodData;

public class IsACollectionAndAddCardinalityAnalyzer extends AbstractAnalyzer {
	
	static String collectionString = "java.util.Collection";
	private Map<String, Boolean> extendsCollectionMap = new HashMap<>();

	@Override
	public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
		
		// callback hell has nothing on me
		for (FieldData fd : v.getFields()) {
			if (extendsCollection(fd.getFieldType(), g)) {
				for (IClassVertex param : fd.getTypeParameterVertices()) {
					if (param.getCorrespondingDOMNode() != null) {
                        for (IClassEdge e : g.getEdgesFromTo(v, param)) {
                        	if(e instanceof DependencyEdge || e instanceof AssociationEdge) {
                        		if (e.getCorrespondingDOMNode() != null) {
                        			e.getCorrespondingDOMNode().addAttribute("headlabel", "\"1..n\"");
                        		}
                            }
                        }
					}
				}
			}
		}
		
		List<IClassVertex> params;
		List<IClassVertex> paramTypeParams;
		for (MethodData md : v.getMethods()) {

			// check the return type
			if (extendsCollection(md.getReturnType(), g)) {
				for (IClassVertex returnTypeParam : md.getReturnTypeTypeParameters()) {
					if (returnTypeParam.getCorrespondingDOMNode() != null) {
						for (IClassEdge e : g.getEdgesFromTo(v, returnTypeParam)) {
							if(e instanceof DependencyEdge || e instanceof AssociationEdge) {
								if (e.getCorrespondingDOMNode() != null) {
									e.getCorrespondingDOMNode().addAttribute("headlabel", "\"1..n\"");
								}
							}
						}
					}
				}
			}
			
			// check the parameters
			params = md.getParams();
			for (int i = 0; i < params.size(); i++) {
				if (extendsCollection(params.get(i), g)) {
					paramTypeParams = md.getParamTypeParams().get(i);
					for (IClassVertex type : paramTypeParams) {
                        for (IClassEdge e : g.getEdgesFromTo(v, type)) {
                        	if(e instanceof DependencyEdge || e instanceof AssociationEdge) {
	                        	if (e.getCorrespondingDOMNode() != null) {
	                        		e.getCorrespondingDOMNode().addAttribute("headlabel", "\"1..n\"");
	                        	}
                        	}
                        }
					}
				}
			}
		}
	}
	
	
	public boolean extendsCollection(IClassVertex v, ClassNodeGraph g) {
		
		if (v.getTitle().equals(collectionString)) {
			return true;
		}
		
		if (this.extendsCollectionMap.containsKey(v.getTitle())) {
            if (this.extendsCollectionMap.get(v.getTitle())) {
                return true;
            }
            return false;
		}

		List<IClassEdge> edges = v.getEdges();
		for (IClassEdge e : edges) {
			if (e instanceof ExtendsEdge) {
				if (this.extendsCollection(e.getTo(), g)) {
					this.extendsCollectionMap.put(v.getTitle(), true);
					return true;
				}
					
			}
			
			if (e instanceof ImplementsEdge) {
				if (this.extendsCollection(e.getTo(), g)) {
					this.extendsCollectionMap.put(v.getTitle(), true);
					return true;
				}
			}
		}
		
		this.extendsCollectionMap.put(v.getTitle(), false);
		return false;
	}

}
