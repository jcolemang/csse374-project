package projectFile;

import java.io.IOException;
import java.util.ArrayList;

import org.objectweb.asm.ClassReader;

public class MainClass {

	/**
	 * This is our main method to make GraphParser of class and make DOMGraph 
	 * and generator the text we get from DOMGraph into .dot file.
	 * 
	 * @param args
	 * @throws IOException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException {
		GraphParser gp = new GraphParser();
		ArrayList<String> strs = new ArrayList<String>();
		for(String str: args) {
			strs.add(str);
		 }
		ClassNodeGraph nodeGraph = gp.parse(strs);
		
		DOMGraph dom = new DOMGraph();
		dom.addVertexToDOMNodeMapping(RegularClassVertex.class, DOMConcreteClassNode.class);
		dom.addVertexToDOMNodeMapping(AbstractClassVertex.class, DOMConcreteClassNode.class);
		dom.addVertexToDOMNodeMapping(InterfaceVertex.class, DOMInterfaceNode.class);
		
		dom.addEdgeToDOMEdgeMapping(ImplementsEdge.class, DOMImplementsEdge.class);
		dom.addEdgeToDOMEdgeMapping(ExtendsEdge.class, DOMExtendsEdge.class);

		dom.setDisplayAll(false);
		dom.setClassesToDisplay(strs);
		dom.generateDOMTree(nodeGraph);

		TextAggregator generator = new TextAggregator();
		generator.writeFile("out.dot", dom);
		
	}

}
