package analyzers;

import graphNodes.IClassVertex;

public interface IAnalyzer {
	
	boolean wasVisited(IClassVertex v);
	void analyze(IClassVertex v);
	void setVisited(IClassVertex v);

}
