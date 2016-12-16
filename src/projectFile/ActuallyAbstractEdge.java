package projectFile;

public abstract class ActuallyAbstractEdge implements IClassEdge {
	
	public IClassVertex head;
	public IClassVertex tail;

	@Override
	public IClassVertex getEnd() {
		return this.head;
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
