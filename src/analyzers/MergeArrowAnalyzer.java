package analyzers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import graphNodes.IClassEdge;
import graphNodes.IClassVertex;

public class MergeArrowAnalyzer implements IAnalyzer {

	private HashMap<IClassVertex, Boolean> trackVisited;
	
	public MergeArrowAnalyzer(){
		this.trackVisited = new HashMap<IClassVertex, Boolean>();
	}
	
	@Override
	public boolean wasVisited(IClassVertex v) {
		// TODO Auto-generated method stub
		return this.trackVisited.getOrDefault(v, false);
	}

	@Override
	public void analyze(IClassVertex v) {
		// TODO Auto-generated method stub
		List<IClassVertex> heads = new ArrayList<>();
		for(IClassEdge e: v.getEdges()){
			heads.add(e.getHead());
		}
		for(IClassVertex temp: heads){
			for (IClassEdge e: temp.getEdges()){
				if (e.getTail()==v){
					
					MergeArray(v,temp);
				}
			}
		}
	}
	
	public void MergeArray(IClassVertex v, IClassVertex x){
	
	}

	@Override
	public void setVisited(IClassVertex v) {
		// TODO Auto-generated method stub
		this.trackVisited.put(v, true);
	}

}
