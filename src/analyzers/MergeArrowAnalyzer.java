package analyzers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import graphNodes.AssociationBidirectionalEdge;
import graphNodes.AssociationEdge;
import graphNodes.DependencyEdge;
import graphNodes.DependsBidirectionalEdge;
import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;

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
				if (e.getTail().equals(v)){
					MergeArray(v,temp,e);
				}
			}
		}
	}
	
	public void MergeArray(IClassVertex v, IClassVertex x, IClassEdge e){
		v.removeEdge(e);
		x.removeEdge(e);
		if (e.equals(AssociationEdge.class)){
			v.addEdge(new AssociationBidirectionalEdge());
		}
		if (e.equals(DependencyEdge.class)){
			v.addEdge(new DependsBidirectionalEdge());
		}
	}

	@Override
	public void analyze(IClassVertex v, ClassNodeGraph g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVisited(IClassVertex v) {
		// TODO Auto-generated method stub
		
	}


}
