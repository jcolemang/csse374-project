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
	
	private boolean displayAllNodes = false;
	private List<String> classesToDisplay = new ArrayList<String>();
	
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
		
		int c = 0;
		
		for (IClassVertex vert : vertices) {
			if (vert instanceof PrimitiveVertex) {
				continue;
			}
			if (!this.displayAllNodes && !this.classesToDisplay.contains(vert.getTitle())) {
				continue;
			}
			
			System.out.println("Displaying node: " + vert);
			c++;
            this.addDOMVertex(vert);
		}
		
		System.out.println("Showing " + c + " nodes.");
		
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
	 * Set to true if the DOMGraph should use every node from the ClassNodeGraph
	 * If this is false, it will only use the nodes given by setClassesToDisplay().
	 * Defaults to false.
	 */
	public void setDisplayAll(boolean shouldDisplayAll) {
		this.displayAllNodes = shouldDisplayAll;
	}
	
	
	public void setClassesToDisplay(List<String> classNames) {
		this.classesToDisplay = classNames;
	}
	

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
