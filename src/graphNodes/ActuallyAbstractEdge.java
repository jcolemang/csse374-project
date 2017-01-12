package graphNodes;

public abstract class ActuallyAbstractEdge implements IClassEdge {
	
	public IClassVertex head;
	public IClassVertex tail;
	public String headCardinality;
	public String tailCardinality;

	@Override
	public IClassVertex getEnd() {
		return this.head;
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
	public IClassVertex getStart() {
		return this.tail;
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

}
