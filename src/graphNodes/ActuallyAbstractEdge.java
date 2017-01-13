package graphNodes;

import DOMNodes.IDOMEdgeNode;

public abstract class ActuallyAbstractEdge implements IClassEdge {
	
	public IClassVertex head;
	public IClassVertex tail;
	public String headCardinality = "";
	public String tailCardinality = "";
	private IDOMEdgeNode domEdge;

	@Override
	public IClassVertex getTail() { //confusing we know
		return this.head; //could fix this if we manage to flip arrows in the image....
	}
	
	@Override
	public IClassVertex getHead() {
		return this.tail;
	}
	
	public void setHeadCardinality(String s) {
		this.headCardinality = s;
	}
	
	public void setTailCardinality(String s) {
		this.tailCardinality = s;
	}
	
	@Override
	public String getHeadCardinality() {
		return this.headCardinality;
	}
	
	@Override
	public String getTailCardinality() {
		return this.tailCardinality;
	}
	
	@Override
	public String getHeadTitle() {
		return this.head.getTitle();
	}
	
	@Override
	public String getTailTitle() {
		return this.tail.getTitle();
	}

	@Override
	public void set(IClassVertex from, IClassVertex to) {
		this.tail = from;
		this.head = to;
	}
	
	@Override
	public String toString() {
		return this.tail + " --> " + this.head;
	}
	
	@Override
	public void setCorrespondingDOMNode(IDOMEdgeNode edn) {
		this.domEdge = edn;
	}

	@Override
	public IDOMEdgeNode getCorrespondingDOMNode() {
		return this.domEdge;
	}

}
