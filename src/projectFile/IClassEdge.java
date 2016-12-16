package projectFile;

/**
 * This is a interface for some kinds of ClassEdge like DependsOnEdge and ExtendsEdge
 * 
 * @author Administrator
 *
 */
public interface IClassEdge {
	IClassVertex getEnd();
	IClassVertex getStart();
	void set(IClassVertex head, IClassVertex tail);
}
