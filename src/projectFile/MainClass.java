package projectFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import CommandLineArgument.AccessCommandLineArgument;
import CommandLineArgument.ICommandLineArgument;
import CommandLineArgument.RecursivelyParseCommandLine;
import DOMNodes.DOMAbstractClassNode;
import DOMNodes.DOMAssociationEdge;
import DOMNodes.DOMConcreteClassNode;
import DOMNodes.DOMDependencyEdge;
import DOMNodes.DOMExtendsEdge;
import DOMNodes.DOMImplementsEdge;
import DOMNodes.DOMInterfaceNode;
import analyzers.AssociationSupercedesDependencyAnalyzer;
import analyzers.BlacklistNodesAnalyzer;
import analyzers.ClassNodeTraverser;
import analyzers.IAnalyzer;
import analyzers.IsACollectionAndAddCardinalityAnalyzer;
import analyzers.MergeArrowAnalyzer;
import analyzers.SingletonDetector;
import analyzers.ViolatesCompositionOverInheritanceAnalyzer;
import dataFilters.SyntheticFilter;
import graphNodes.AbstractClassVertex;
import graphNodes.AssociationEdge;
import graphNodes.DependencyEdge;
import graphNodes.ExtendsEdge;
import graphNodes.ImplementsEdge;
import graphNodes.InterfaceVertex;
import graphNodes.RegularClassVertex;

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
		argParsers.add(new AccessCommandLineArgument(dom));
		
		for (ICommandLineArgument cla : argParsers) {
			strs = cla.execute(strs);
			System.out.println(strs);
		}
		
		ClassNodeGraph nodeGraph = gp.parse(strs);
		
		dom.addVertexToDOMNodeMapping(RegularClassVertex.class, DOMConcreteClassNode.class);
		dom.addVertexToDOMNodeMapping(AbstractClassVertex.class, DOMAbstractClassNode.class);
		dom.addVertexToDOMNodeMapping(InterfaceVertex.class, DOMInterfaceNode.class);
		
		dom.addEdgeToDOMEdgeMapping(ImplementsEdge.class, DOMImplementsEdge.class);
		dom.addEdgeToDOMEdgeMapping(ExtendsEdge.class, DOMExtendsEdge.class);
		dom.addEdgeToDOMEdgeMapping(AssociationEdge.class, DOMAssociationEdge.class);
		dom.addEdgeToDOMEdgeMapping(DependencyEdge.class, DOMDependencyEdge.class);
		
		// needs to be toggleable
		dom.addMethodDataFilter(SyntheticFilter.class);

		dom.setClassesToDisplay(strs);
		dom.generateDOMTree(nodeGraph);

		IAnalyzer AssociationSupercedesDependency = new AssociationSupercedesDependencyAnalyzer();
		ClassNodeTraverser traverser = new ClassNodeTraverser(nodeGraph, dom);
		traverser.addAnalyzer(AssociationSupercedesDependency);
		
		System.out.println("Checking our collection thing");
		IAnalyzer isCollectionAnalyzer = new IsACollectionAndAddCardinalityAnalyzer();
		traverser.addAnalyzer(isCollectionAnalyzer);
		
		System.out.println("Merging bidirectional arrows");
		IAnalyzer mergeArrowAnalyzer = new MergeArrowAnalyzer();
		traverser.addAnalyzer(mergeArrowAnalyzer);
		
		System.out.println("Highlighting violations of composition v. inheritance");
		IAnalyzer violationAnalyzer = new ViolatesCompositionOverInheritanceAnalyzer();
		traverser.addAnalyzer(violationAnalyzer);
		
		System.out.println("Highlighting the Singleton class");
		IAnalyzer singletonDetector = new SingletonDetector();
		traverser.addAnalyzer(singletonDetector);
		
		System.out.println("Blacklisting nodes via DOM removal");
		IAnalyzer blacklistAnalyzer = new BlacklistNodesAnalyzer();
		traverser.addAnalyzer(blacklistAnalyzer);
		
		traverser.analyzeAll(); //run all analyzers
		
		TextAggregator generator = new TextAggregator();
		generator.writeFile("out.dot", dom);
		
		System.out.println("DONE");
		
	}

}
