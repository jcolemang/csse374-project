package projectFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import CommandLineArgument.Configuration;
import DOMNodes.IDOMClassNode;
import DOMNodes.IDOMEdgeNode;
import DOMNodes.IDOMNode;
import dataFilters.FieldDataFilter;
import dataFilters.MethodDataFilter;
import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import graphNodes.PrimitiveVertex;


public class DOMGraph implements Iterable<IDOMNode> {
	
	private Configuration config = Configuration.getInstance();
	private List<IDOMNode> domNodes;
	private Map<Class<? extends IClassVertex>, Class<? extends IDOMClassNode>> vertexToDOMNode = new HashMap<>();
	private Map<Class<? extends IClassEdge>, Class<? extends IDOMEdgeNode>> edgeToDOMEdge = new HashMap<>();
	
	private List<Class<? extends MethodDataFilter>> methodDataFilters = new ArrayList<>();
	private List<Class<? extends FieldDataFilter>> fieldDataFilters = new ArrayList<>();
	
	
	/**
	 * Used for mapping between IClassVertices and IDOMClassNodes. Given an IClassVertex
	 * you should be able to create a IDOMClassNode instance using this method and
	 * the newInstance() method
	 * 
	 * @param vClass the given IClassVertex class
	 * @param domClass the resultant IDOMClassNode class
	 */
	public void addVertexToDOMNodeMapping(Class<? extends IClassVertex> vClass, 
			Class<? extends IDOMClassNode> domClass) {
		this.vertexToDOMNode.put(vClass, domClass);
	}
	
	
	/**
	 * Used for mapping between IClassEdges and IDOMEdgeNodes. Given an IClassEdge
	 * class this method should be able to produce an IDOMEdgeNode instance using
	 * the newInstance() method
	 * 
	 * @param eClass the given IClassEdge class
	 * @param domClass the resultant IDOMEdgeNode class
	 */
	public void addEdgeToDOMEdgeMapping(Class<? extends IClassEdge> eClass,
			Class<? extends IDOMEdgeNode> domClass) {
		this.edgeToDOMEdge.put(eClass, domClass);
	}

	public void removeNodeFromDOMTree(IDOMNode n) {
		this.domNodes.remove(n);
		
	}
	
	/**
	 * Get nodes from ClassNodeGraph and generate them as DOMNodes.
	 * Store them into a list of IDOMNodes
	 * 
	 * @param g the graph to generate a DOMGraph from
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void generateDOMTree(ClassNodeGraph g) throws InstantiationException, IllegalAccessException {
		this.domNodes = new ArrayList<IDOMNode>();
		
		List<IClassVertex> classesToUse;
		List<IClassEdge> edgesToUse;
		List<IClassVertex> start = new LinkedList<>();
		
		// Getting a starting point for classes to display
		for (String name : this.config.getWhitelist()) {
			start.add(g.getVertex(name));
		}

		// getting the dom nodes
		if (this.config.getRecursivelyParse()) {
			classesToUse = g.getVertices();
			edgesToUse = g.getEdges();
		} else {
			classesToUse = start;
			edgesToUse = this.getEdgesToUse(classesToUse);
		}

		// filtering out the blacklisted classes
		IDOMClassNode generatedDOMNode;
		for (IClassVertex vert : classesToUse) {
            if (vert instanceof PrimitiveVertex ||
            		config.isBlacklisted(vert.getTitle())) {
            	continue;
            }

            generatedDOMNode = this.addDOMVertex(vert);
            vert.setCorrespondingDOMNode(generatedDOMNode);
		}

		IDOMEdgeNode generatedDOMEdge;
		for(IClassEdge edge : edgesToUse) {
			
			if (edge.getFrom() instanceof PrimitiveVertex || 
					edge.getTo() instanceof PrimitiveVertex ||
					config.isBlacklisted(edge.getHeadTitle()) ||
					config.isBlacklisted(edge.getTailTitle())) {
				continue;
			}
			
			generatedDOMEdge = this.addDOMEdge(edge);
			edge.setCorrespondingDOMNode(generatedDOMEdge);
		}

		System.out.println("Number of DOM nodes: " + this.domNodes.size());
	}
	
	
	/**
	 * Gets the edges without recursively parsing the graph. An edge will be
	 * added to the output list if the edge both starts and ends in a vertex from
	 * the given list
	 * 
	 * @param vertices
	 * @return all edges starting and ended at vertices from the given list
	 */
	private List<IClassEdge> getEdgesToUse(List<IClassVertex> vertices) {
		List<IClassEdge> edges = new LinkedList<>();
		for (IClassVertex v : vertices) {
			for (IClassEdge e : v.getEdges()) {
				if (vertices.contains(e.getTo())) {
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
	@Deprecated
	private void recursivelyGetClassesToUse(List<IClassVertex> start, 
			                                List<IClassVertex> result,
                                            List<IClassEdge> edges,
			                                Set<String> visited) {
		
		// checking if I've been here before
		IClassVertex first;
		do {
            if (start.size() == 0) {
                return;
            }
			first = start.get(0);
			start = start.subList(1, start.size());
		}
		while (visited.contains(first.getTitle()));
		
		visited.add(first.getTitle());
		boolean shouldDisplay = !config.isBlacklisted(first.getTitle());

		// I haven't been here.
		// Get all connected elements and add them to the queue
		
		if (shouldDisplay || !shouldDisplay) {
		    System.out.println("Should display: " + first);
			result.add(first);
		}
		
		for (IClassEdge e : first.getEdges()) {
			if (shouldDisplay) edges.add(e);
			if (!visited.contains(e.getTo().getTitle())) start.add(e.getTo());
		}

		// and recurse!
		this.recursivelyGetClassesToUse(start, result, edges, visited);
	}
	
	
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
        dn.setAccessLevel(this.config.getAccess());
        dn.setTitle(v.getTitle());

        List<FieldData> fields = v.getFields();
        for (Class<? extends FieldDataFilter> f : this.fieldDataFilters) {
        	fields = f.newInstance().filter(fields);
        }
        
        dn.setFields(fields);
        
        List<MethodData> methods = v.getMethods();
        for (Class<? extends MethodDataFilter> f : this.methodDataFilters) {
        	methods = f.newInstance().filter(methods);
        }
        dn.setMethods(methods);
        this.domNodes.add(dn);
        return dn;
	}
	
	
	public void addMethodDataFilter(Class<? extends MethodDataFilter> f) {
		this.methodDataFilters.add(f);
	}
	
	
	public void addFieldDataFilter(Class<? extends FieldDataFilter> f) {
		this.fieldDataFilters.add(f);
	}
	

	/**
	 * Get some information from IClassEdge and fill them into a DOMEdgeNode.
	 * Add it into the DOMNode list.
	 * @param e
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private IDOMEdgeNode addDOMEdge(IClassEdge e) throws InstantiationException, IllegalAccessException {
		IDOMEdgeNode domNode = this.edgeToDOMEdge.get(e.getClass()).newInstance();
		domNode.set(e.getFrom().getCorrespondingDOMNode(), e.getTo().getCorrespondingDOMNode());
		this.domNodes.add(domNode);
		return domNode;
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
