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

	Map<String, String> aestheticAttributes = new HashMap<String, String>();

	@Override
	public IDOMClassNode getHead() {
		return this.start;
	}

	@Override
	public IDOMClassNode getTail() {
		return this.end;
	}
	
	@Override
	public void addAttribute(String dotAttrName, String property) {
		this.aestheticAttributes.put(dotAttrName, property);
	}

	@Override
	public String attributeMapToString() {
		String built = "";
		
		if(this.aestheticAttributes.isEmpty()) {
			return built;
		}
		
		for (String attr : this.aestheticAttributes.keySet()) {
			built += attr + " = " + this.aestheticAttributes.get(attr) + ", ";
		}
		
		return built.substring(0, built.length() - 2);
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
