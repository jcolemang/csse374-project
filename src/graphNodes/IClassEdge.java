package graphNodes;

/**
 * This is a interface for some kinds of ClassEdge like DependsOnEdge and ExtendsEdge
 * 
 * @author Administrator
 *
 */
public interface IClassEdge {
	IClassVertex getEnd();
	IClassVertex getStart();
	String getHeadCardinality();
	String getTailCardinality();
	void set(IClassVertex head, IClassVertex tail);
}
