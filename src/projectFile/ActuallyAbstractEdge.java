package projectFile;

public abstract class ActuallyAbstractEdge implements IClassEdge{
	
	public IClassVertex head;
	public IClassVertex tail;
	
	public ActuallyAbstractEdge(IClassVertex head, IClassVertex tail) {
		this.head = head;
		this.tail = tail;
	}
	
	@Override
	public IClassVertex getHead() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IClassVertex getTail() {
		// TODO Auto-generated method stub
		return null;
	}

}
