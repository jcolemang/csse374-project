package projectFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		
		List<String> strs = new ArrayList<String>();
		for (String str: args) {
			strs.add(str);
		 }
		
		List<ICommandLineArgument> argParsers = new ArrayList<>();
		GraphParser gp = new GraphParser();
		DOMGraph dom = new DOMGraph();
		argParsers.add(new RecursivelyParseCommandLine(dom));
		
		for (ICommandLineArgument cla : argParsers) {
			strs = cla.execute(strs);
		}

		ClassNodeGraph nodeGraph = gp.parse(strs);
		
		dom.addVertexToDOMNodeMapping(RegularClassVertex.class, DOMConcreteClassNode.class);
		dom.addVertexToDOMNodeMapping(AbstractClassVertex.class, DOMConcreteClassNode.class);
		dom.addVertexToDOMNodeMapping(InterfaceVertex.class, DOMInterfaceNode.class);
		
		dom.addEdgeToDOMEdgeMapping(ImplementsEdge.class, DOMImplementsEdge.class);
		dom.addEdgeToDOMEdgeMapping(ExtendsEdge.class, DOMExtendsEdge.class);

		dom.setClassesToDisplay(strs);
		dom.generateDOMTree(nodeGraph);

		TextAggregator generator = new TextAggregator();
		generator.writeFile("out.dot", dom);
		
	}

}
