package analyzers;

import java.util.HashMap;

import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;

public abstract class AbstractAnalyzer implements IAnalyzer{
	private HashMap<IClassVertex, Boolean> trackVisited;
	
	public AbstractAnalyzer() {
		this.trackVisited = new HashMap<>();
	}

	@Override
	public boolean wasVisited(IClassVertex v) {
		return this.trackVisited.getOrDefault(v, false);
	}

	@Override
	public void setVisited(IClassVertex v) {
		this.trackVisited.put(v, true);
		
	}

}
