package graphNodes;

import DOMNodes.IDOMEdgeNode;

/**
 * This is a interface for some kinds of ClassEdge like DependsOnEdge and ExtendsEdge
 * 
 * @author Administrator
 *
 */
public interface IClassEdge {
	IClassVertex getTo();
	IClassVertex getFrom();
	String getHeadCardinality();
	String getTailCardinality();
	void setHeadCardinality(String s);
	void setTailCardinality(String s);
	String getHeadTitle();
	String getTailTitle();
	void set(IClassVertex head, IClassVertex tail);
	IDOMEdgeNode getCorrespondingDOMNode();
	void setCorrespondingDOMNode(IDOMEdgeNode generatedDOMNode);
	void setOrigin(IClassVertex v);
	IClassVertex getOrigin();
}
