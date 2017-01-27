package analyzers;

import java.util.ArrayList;
import java.util.List;
import CommandLineArgument.Configuration;
import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;


public class ClassNodeTraverser {
	private Configuration config = Configuration.getInstance();
	private List<IClassVertex> nodeList;
	private List<IAnalyzer> analyzers;
	private ClassNodeGraph graph;
	private DOMGraph dom;
	
	public ClassNodeTraverser(ClassNodeGraph g, DOMGraph d) {
		this.analyzers = new ArrayList<IAnalyzer>();
		this.nodeList = g.getVertices();
		this.graph = g;
		this.dom = d;
	}
	
	public void addAnalyzer(IAnalyzer an) {
		this.analyzers.add(an);
	}
	
	/**
	 * Analyze the nodeList with a single IAnalyzer
	 * @param an
	 */
	private void analyze(IAnalyzer an) {
		for (IClassVertex v : this.nodeList) {
			if(!an.wasVisited(v)) {
				an.analyze(v, this.graph, this.dom);
			}
		}
	}
	
	/**
	 * Run all analyzers on the nodeList
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void analyzeAll() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		for(String a : config.getAnalyzers()) {
			System.out.println(a);
			analyze((IAnalyzer)Class.forName(a).newInstance());
		}
	}
}