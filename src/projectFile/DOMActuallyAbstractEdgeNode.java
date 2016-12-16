package projectFile;

import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/*
 * TODO We should be able to add new types of DOM nodes to
 * allow for things like adding implements vs has-a
 */
public abstract class DOMActuallyAbstractEdgeNode implements IDOMEdgeNode {
	
	protected IDOMClassNode head;
	protected IDOMClassNode tail;
	

	@Override
	public IDOMClassNode getHead() {
		return this.head;
	}
	

	@Override
	public IDOMClassNode getTail() {
		return this.tail;
	}


	@Override
	public void set(IDOMClassNode head, IDOMClassNode tail) {
		this.head = head;
		this.tail = tail;
	}

}
