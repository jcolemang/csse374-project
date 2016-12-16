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
	
	/**
	 * Get nodes from ClassNodeGraph and generate them as DOMNodes.
	 * Store them into a list of IDOMNodes
	 * 
	 * @param g
	 */
	public void generateDOMTree(ClassNodeGraph g) {
		System.out.println("Generating DOM Graph");
		this.domNodes = new ArrayList<IDOMNode>();
		List<IClassVertex> vertices = g.getVertices();
		List<IClassEdge> edges = g.getEdges();
		
		for (IClassVertex vert : vertices) {
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
	/**
	 * Get some information from IClassVertex and fill them into a DOMClassNode.
	 * Add it into the DOMNode list.
	 * @param v
	 */
	private void addDOMVertex(IClassVertex v) {
		System.out.println("Adding DOM Vertex");
        DOMClassNode dn = new DOMClassNode();
        dn.setTitle(v.getTitle());
        dn.setFields(v.getFields());
        dn.setMethods(v.getMethods());
        domNodes.add(dn);
	}
	
	/**
	 * Get some information from IClassEdge and fill them into a DOMEdgeNode.
	 * Add it into the DOMNode list.
	 * @param v
	 */
	private void addDOMEdge(IClassEdge e) {
        DOMClassNode dn = new DOMClassNode();
        domNodes.add(dn);
	}
	
	/**
	 * Set the fontsize.
	 * @param size
	 */
	public void setFontSize(int size) {
		this.fontSize = size;
	}
	
	/**
	 * Return the fontsize.
	 * @return
	 */
	public int getFontSize() {
		return this.fontSize;
	}
	

	/**
	 * Create a Iterator for IDOMNode.
	 */
	@Override
	public Iterator<IDOMNode> iterator() {
		return new GraphIterator(this.domNodes);
	}
	
	/**
	 * 
	 * @author Administrator
	 *
	 */
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
