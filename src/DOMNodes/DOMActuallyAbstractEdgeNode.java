package DOMNodes;

import java.util.HashMap;
import java.util.Map;

/*
 * TODO We should be able to add new types of DOM nodes to
 * allow for things like adding implements vs has-a
 */
public abstract class DOMActuallyAbstractEdgeNode implements IDOMEdgeNode {
	
	protected IDOMClassNode start;
	protected IDOMClassNode end;
	protected String headCardinality = "";
	protected String tailCardinality = "";

	Map<String, String> aestheticAttributes = new HashMap<String, String>();

	@Override
	public IDOMClassNode getHead() {
		return this.start;
	}
	

	public String getHeadCardinality() {
		return this.headCardinality;
	}
	
	public String getTailCardinality() {
		return this.tailCardinality;
	}

	@Override
	public IDOMClassNode getTail() {
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
