package analyzers;

import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;

public interface IAnalyzer {
	
	boolean wasVisited(IClassVertex v);
	void analyze (IClassVertex v);
	void analyze(IClassVertex v, ClassNodeGraph g);
	void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d);
	void setVisited(IClassVertex v);

}
