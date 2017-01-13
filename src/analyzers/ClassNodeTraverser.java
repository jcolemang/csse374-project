package analyzers;

import java.util.List;

import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;

public class ClassNodeTraverser {
	private List<IClassVertex> nodeList;
	private List<IAnalyzer> analyzers;
	
	public ClassNodeTraverser(ClassNodeGraph g) {
		this.nodeList = g.getVertices();
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
				an.analyze(v);
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