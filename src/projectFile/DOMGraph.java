package projectFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class DOMGraph implements Iterable<IDOMNode>{
	
	private String interfaceColor;
	private String abstractClassColor;
	private String vanillaClassColor;
	private List<IDOMNode> domNodes;
	private int fontSize = 14;
	
	private boolean recursivelyParse = false;
	private List<String> classesToDisplay = new ArrayList<String>();
	
	// TODO Change this to IDOMNode some time in the future
	private Map<Class<? extends IClassVertex>, Class<? extends IDOMClassNode>> vertexToDOMNode = new HashMap<>();
	private Map<Class<? extends IClassEdge>, Class<? extends IDOMEdgeNode>> edgeToDOMEdge = new HashMap<>();
	
	
	/**
	 * 
	 * @param vClass
	 * @param domClass
	 */
	public void addVertexToDOMNodeMapping(Class<? extends IClassVertex> vClass, 
			Class<? extends IDOMClassNode> domClass) {
		this.vertexToDOMNode.put(vClass, domClass);
	}
	
	
	/**
	 * 
	 * @param eClass
	 * @param domClass
	 */
	public void addEdgeToDOMEdgeMapping(Class<? extends IClassEdge> eClass,
			Class<? extends IDOMEdgeNode> domClass) {
		this.edgeToDOMEdge.put(eClass, domClass);
	}

	
	/**
	 * Get nodes from ClassNodeGraph and generate them as DOMNodes.
	 * Store them into a list of IDOMNodes
	 * 
	 * @param g
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void generateDOMTree(ClassNodeGraph g) throws InstantiationException, IllegalAccessException {
		this.domNodes = new ArrayList<IDOMNode>();
		
		List<IClassVertex> classesToUse;
		List<IClassEdge> edgesToUse;
		List<IClassVertex> start = new LinkedList<>();
		
		// Getting a starting point for classes to display
		for (String name : this.classesToDisplay) {
			start.add(g.getVertex(name));
		}
		
		if (this.recursivelyParse) {
			classesToUse = new LinkedList<>();
			edgesToUse = new LinkedList<>();
			this.recursivelyGetClassesToUse(start, classesToUse, edgesToUse);
		} else {
			classesToUse = start;
			edgesToUse = this.getEdgesToUse(classesToUse);
		}
		
		int numClasses = 0;
		IDOMClassNode generatedDOMNode;
		
		for (IClassVertex vert : classesToUse) {
            generatedDOMNode = this.addDOMVertex(vert); // uuhhhhh
            vert.setCorrespondingDOMNode(generatedDOMNode); // weird circular dependency
			numClasses++;
		}
		
		for(IClassEdge edge : edgesToUse) {
			this.addDOMEdge(edge);
		}
		
	}
	
	
	/**
	 * 
	 * @param vertices
	 * @return
	 */
	private List<IClassEdge> getEdgesToUse(List<IClassVertex> vertices) {
		List<IClassEdge> edges = new LinkedList<>();
		for (IClassVertex v : vertices) {
			for (IClassEdge e : v.getEdges()) {
				if (vertices.contains(e.getEnd())) {
					edges.add(e);
				}
			}
		}
		return edges;
	}
	
	
	/**
	 * Used to discover classes which should be parsed from the given graph.
	 * Uses a list of initial starting points and recursively adds to this
	 * starting list from the connected edges. Needs the two extra arguments
	 * for the efficiency of not needing to redundantly discover the edges
	 * after this method runs
	 * 
	 * @param start The initial list of vertices
	 * @param result The resultant list of vertices, modified in place
	 * @param edges the resultant list of edges, modified in place
	 */
	private void recursivelyGetClassesToUse(List<IClassVertex> start, 
			List<IClassVertex> result, 
			List<IClassEdge> edges) {
		
		// base case
		if (start.size() == 0) {
			return;
		}

		// checking if I've been here before
		IClassVertex first = start.get(0);
		if (result.contains(first)) {
			this.recursivelyGetClassesToUse(start.subList(1, start.size()), result, edges);
			return;
		}

		// I haven't been here.
		// Get all connected elements and add them to the queue
		result.add(first);
		for (IClassEdge e : first.getEdges()) {
			edges.add(e);
			start.add(e.getEnd());
		}

		// and recurse!
		this.recursivelyGetClassesToUse(start.subList(1, start.size()), result, edges);
	}
	
	
	
	
	/*
	 * TODO We will need to fill out all of the DOM Node info
	 * from the vertex info
	 */
	/**
	 * Get some information from IClassVertex and fill them into a DOMClassNode.
	 * Add it into the DOMNode list.
	 * @param v
	 * @return 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private IDOMClassNode addDOMVertex(IClassVertex v) throws InstantiationException, IllegalAccessException {
        IDOMClassNode dn = this.vertexToDOMNode.get(v.getClass()).newInstance();
        dn.setTitle(v.getTitle());
        dn.setMethods(v.getMethods());
        dn.setFields(v.getFields());
        this.domNodes.add(dn);
        return dn;
	}
	
	/**
	 * Get some information from IClassEdge and fill them into a DOMEdgeNode.
	 * Add it into the DOMNode list.
	 * @param v
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private void addDOMEdge(IClassEdge e) throws InstantiationException, IllegalAccessException {
		IDOMEdgeNode domNode = this.edgeToDOMEdge.get(e.getClass()).newInstance();
		domNode.set(e.getStart().getCorrespondingDOMNode(), e.getEnd().getCorrespondingDOMNode());
		this.domNodes.add(domNode);
	}
	
	/**
	 * Set to true if the DOMGraph should use every node from the ClassNodeGraph
	 * If this is false, it will only use the nodes given by setClassesToDisplay().
	 * Defaults to false.
	 */
	public void setRecursivelyParse(boolean recursivelyParse) {
		this.recursivelyParse = recursivelyParse;
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
