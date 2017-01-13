package analyzers;

import java.util.HashMap;

import graphNodes.IClassVertex;
import graphNodes.InterfaceVertex;

public class IfInterfaceThenBlueAnalyzer implements IAnalyzer{
	private HashMap<IClassVertex, Boolean> trackVisited;
	
	public IfInterfaceThenBlueAnalyzer() {
		this.trackVisited = new HashMap<IClassVertex, Boolean>();
	}

	@Override
	public boolean wasVisited(IClassVertex v) {
		return this.trackVisited.getOrDefault(v, false);
	}

	@Override
	public void analyze(IClassVertex v) {
		// CHECK IF INTERFACE
		// IF IT IS,
		// CALL v.addAttribute("color", "blue")
		
		if(v.getCorrespondingDOMNode() != null) {
			if (v instanceof InterfaceVertex) {
				v.getCorrespondingDOMNode().addAttribute("color", "blue");
			}
			
		}
		
	}

	@Override
	public void setVisited(IClassVertex v) {
		this.trackVisited.put(v, true);
		
	}

}
