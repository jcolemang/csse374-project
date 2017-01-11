package DOMNodes;

/*
 * TODO We should be able to add new types of DOM nodes to
 * allow for things like adding implements vs has-a
 */
public abstract class DOMActuallyAbstractEdgeNode implements IDOMEdgeNode {
	
	protected IDOMClassNode start;
	protected IDOMClassNode end;
	

	@Override
	public IDOMClassNode getStart() {
		return this.start;
	}
	

	@Override
	public IDOMClassNode getEnd() {
		return this.end;
	}


	@Override
	public void set(IDOMClassNode start, IDOMClassNode end) {
		this.start = start;
		this.end = end;
		if (this.end == null || this.start == null) {
			System.out.println("BAD BAD BAD!!!");
			System.exit(-1);
		}
	}

}
