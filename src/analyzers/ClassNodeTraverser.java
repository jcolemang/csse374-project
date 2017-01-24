package analyzers;

import java.util.ArrayList;
import java.util.List;

import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;

public class ClassNodeTraverser {
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
	public void analyze(IAnalyzer an) {
		for (IClassVertex v : this.nodeList) {
			if(!an.wasVisited(v)) {
				an.analyze(v, this.graph, this.dom);
			}
		}
	}
	
	/**
	 * Run all analyzers on the nodeList
	 */
	public void analyzeAll() {
		for(IAnalyzer a : this.analyzers) {
			analyze(a);
		}
	}
	
}