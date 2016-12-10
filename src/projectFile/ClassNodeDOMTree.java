package projectFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ClassNodeDOMTree implements Iterable<DOMClassNode>{
	
	public String interfaceColor;
	public String abstractClassColor;
	public String vanillaClassColor;
	private List<IDOMNode> domNodes;
	
	public void generateDOMTree(ClassNodeGraph g) {
		List<IClassVertex> vertices = g.getVertices();
		List<IClassEdge> edges = g.getEdges();
		
		for(IClassVertex vert : vertices) {
			IDOMNode dn = new DOMClassNode();
			((DOMClassNode) dn).setTitle(vert.getTitle());
			domNodes.add(dn);
		}
		
		for(IClassEdge edge : edges) {
			IDOMNode en = new DOMEdgeNode();
			domNodes.add(en);
		}
		
	}
	

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
