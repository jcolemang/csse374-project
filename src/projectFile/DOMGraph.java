package projectFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DOMGraph implements Iterable<IDOMNode>{
	
	private String interfaceColor;
	private String abstractClassColor;
	private String vanillaClassColor;
	private List<IDOMNode> domNodes;
	private int fontSize = 14;
	
	public void generateDOMTree(ClassNodeGraph g) {
		System.out.println("Generating DOM Graph");
		this.domNodes = new ArrayList<IDOMNode>();
		List<IClassVertex> vertices = g.getVertices();
		List<IClassEdge> edges = g.getEdges();
		
		// TODO I think this should be moved elsewhere
		for (IClassVertex vert : vertices) {
			if (vert instanceof PrimitiveVertex) {
				continue;
			}
            System.out.println(vert);
            this.addDOMVertex(vert);
		}
		
		/*
		 * TODO This currently doesn't do anything
		 */
		for(IClassEdge edge : edges) {
			this.addDOMEdge(edge);
		}
		
	}
	
	
	/*
	 * TODO We will need to fill out all of the DOM Node info
	 * from the vertex info
	 */
	private void addDOMVertex(IClassVertex v) {
		System.out.println("Adding DOM Vertex");
        DOMClassNode dn = new DOMClassNode();
        dn.setTitle(v.getTitle());
        dn.setFields(v.getFields());
        dn.setMethods(v.getMethods());
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
	public Iterator<IDOMNode> iterator() {
		return new GraphIterator(this.domNodes);
	}
	
	private class GraphIterator implements Iterator<IDOMNode> {
		
		private List<IDOMNode> nodes;
		private Iterator<IDOMNode> iter;
		
		public GraphIterator(List<IDOMNode> nodes) {
			this.nodes = nodes;
			this.iter = this.nodes.iterator();
		}

		@Override
		public boolean hasNext() {
			return this.iter.hasNext();
		}

		@Override
		public IDOMNode next() {
			return this.iter.next();
		}
		
		
		
		
		
	}

}
