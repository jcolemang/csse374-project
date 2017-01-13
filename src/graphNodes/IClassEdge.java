package graphNodes;

/**
 * This is a interface for some kinds of ClassEdge like DependsOnEdge and ExtendsEdge
 * 
 * @author Administrator
 *
 */
public interface IClassEdge {
	IClassVertex getTail();
	IClassVertex getHead();
	String getHeadCardinality();
	String getTailCardinality();
	String getHeadTitle();
	String getTailTitle();
	void set(IClassVertex head, IClassVertex tail);
}
