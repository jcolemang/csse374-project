package graphNodes;

import DOMNodes.IDOMEdgeNode;

public abstract class ActuallyAbstractEdge implements IClassEdge {
	
	public IClassVertex head;
	public IClassVertex tail;
	public String headCardinality = "";
	public String tailCardinality = "";
	private IDOMEdgeNode domEdge;
	
	private IClassVertex cameFrom;

	@Override
	public IClassVertex getTo() { //aka the other end
		return this.head;
	}
	
	@Override
	public IClassVertex getFrom() { //aka the closest end if
		return this.tail;			//accessing edge from a node
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
	
	public void setOrigin(IClassVertex v) {
		this.cameFrom = v;
	}
	
	public IClassVertex getOrigin() {
		return this.cameFrom;
	}

}
