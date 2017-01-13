package analyzers;

import java.util.HashMap;

import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;

public abstract class AbstractAnalyzer implements IAnalyzer{
	private HashMap<IClassVertex, Boolean> trackVisited;

	@Override
	public boolean wasVisited(IClassVertex v) {
		return this.trackVisited.getOrDefault(v, false);
	}

	public void analyze (IClassVertex v) {
	}

	@Override
	public void analyze(IClassVertex v, ClassNodeGraph g) {
	}
	
	public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
		
	}
	

	@Override
	public void setVisited(IClassVertex v) {
		this.trackVisited.put(v, true);
		
	}

}
