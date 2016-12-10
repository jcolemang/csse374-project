package projectFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DOMGraph implements Iterable<DOMClassNode>{
	
	public String interfaceColor;
	public String abstractClassColor;
	public String vanillaClassColor;
	private List<DOMClassNode> domNodes;
	private int fontSize;
	
	public void generateDOMTree(ClassNodeGraph g) {
		List<IClassVertex> vertices = g.getVertices();
		List<IClassEdge> edges = g.getEdges();
		
		for(IClassVertex vert : vertices) {
		}
		
		for(IClassEdge edge : edges) {
			DOMClassNode en = new DOMClassNode();
			domNodes.add(en);
		}
		
	}
	
	
	private void addDOMVertex(IClassVertex v) {
        DOMClassNode dn = new DOMClassNode();
        dn.setTitle(v.getTitle());
        domNodes.add(dn);
	}
	
	
	private void addDOMEdge(IClassEdge e) {
        DOMClassNode dn = new DOMClassNode();
        domNodes.add(dn);
	}
	
	
	public void setFontSize(int size) {
		this.fontSize = size;
	}
	
	
	public int getFontSize() {
		return this.fontSize;
	}
	

	@Override
	public Iterator iterator() {
		return new GraphIterator(this.domNodes);
	}
	
	private class GraphIterator implements Iterator {
		
		private List<DOMClassNode> nodes;
		private Iterator iter;
		
		public GraphIterator(List<DOMClassNode> nodes) {
			this.nodes = nodes;
			this.iter = this.nodes.iterator();
		}

		@Override
		public boolean hasNext() {
			return this.iter.hasNext();
		}

		@Override
		public Object next() {
			return this.iter.next();
		}
		
		
		
		
		
	}

}
