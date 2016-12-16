package projectFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class DOMGraph implements Iterable<IDOMNode>{
	
	private String interfaceColor;
	private String abstractClassColor;
	private String vanillaClassColor;
	private List<IDOMNode> domNodes;
	private int fontSize = 14;
	
	private boolean displayAllNodes = false;
	private List<String> classesToDisplay = new ArrayList<String>();
	
	// TODO Change this to IDOMNode some time in the future
	private Map<Class<? extends IClassVertex>, Class<? extends IDOMClassNode>> vertexToDOMNode = new HashMap<>();
	private Map<Class<? extends IClassEdge>, Class<? extends IDOMNode>> edgeToDOMEdge = new HashMap<>();
	
	/**
	 * Get nodes from ClassNodeGraph and generate them as DOMNodes.
	 * Store them into a list of IDOMNodes
	 * 
	 * @param g
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void generateDOMTree(ClassNodeGraph g) throws InstantiationException, IllegalAccessException {
		System.out.println("Generating DOM Graph");
		this.domNodes = new ArrayList<IDOMNode>();
		List<IClassVertex> vertices = g.getVertices();
		List<IClassEdge> edges = g.getEdges();
		
		int numClasses = 0;
		
		for (IClassVertex vert : vertices) {
			if (vert instanceof PrimitiveVertex) {
				continue;
			}
			if (!this.displayAllNodes && !this.classesToDisplay.contains(vert.getTitle())) {
				continue;
			}
			
			System.out.println("Displaying node: " + vert + ", " + vert.getTitle());
            this.addDOMVertex(vert);
            
            // 
			numClasses++;
		}
		
		System.out.println("Showing " + numClasses + " nodes.");
		
		for(IClassEdge edge : edges) {
			System.out.println(edge);
			this.addDOMEdge(edge);
		}
		
	}
	
	
	/*
	 * 
	 */
	public void addVertexToDOMNodeMapping(Class<? extends IClassVertex> vClass, 
			Class<? extends IDOMClassNode> domClass) {
		this.vertexToDOMNode.put(vClass, domClass);
	}
	
	
	public void addEdgeToDOMEdgeMapping(Class<? extends IClassEdge> eClass,
			Class<? extends IDOMNode> domClass) {
		this.edgeToDOMEdge.put(eClass, domClass);
	}
	
	
	/*
	 * TODO We will need to fill out all of the DOM Node info
	 * from the vertex info
	 */
	/**
	 * Get some information from IClassVertex and fill them into a DOMClassNode.
	 * Add it into the DOMNode list.
	 * @param v
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void addDOMVertex(IClassVertex v) throws InstantiationException, IllegalAccessException {
        IDOMClassNode dn = this.vertexToDOMNode.get(v.getClass()).newInstance();
        dn.setTitle(v.getTitle());
        dn.setMethods(v.getMethods());
        dn.setFields(v.getFields());
        domNodes.add(dn);
	}
	
	/**
	 * Get some information from IClassEdge and fill them into a DOMEdgeNode.
	 * Add it into the DOMNode list.
	 * @param v
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void addDOMEdge(IClassEdge e) throws InstantiationException, IllegalAccessException {
		IDOMNode domNode = this.edgeToDOMEdge.get(e.getClass()).newInstance();
		this.domNodes.add(domNode);
	}
	
	/**
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
