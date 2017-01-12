package projectFile;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.MethodNode;

import graphNodes.IClassVertex;

public class MethodData {
	private String access;
	private String methodName;
	private IClassVertex returnType;
	private List<IClassVertex> params; // can result in a "Depends on" relationship
	
	/**
	 * Collect the information of each method in class, include access, name and return type.
	 * 
	 * @param access
	 * @param methodName
	 * @param returnType
	 */
	public MethodData(String access, String methodName, IClassVertex returnType) { //wb params??
		this.access = access;
		this.methodName = methodName;
		this.returnType = returnType;
		this.params = new ArrayList<IClassVertex>();
	}
	/**
	 * Return the access of method as String.
	 * @return
	 */
	public String getAccessLevel() {
		return this.access;
	}
	
	/**
	 * Return the name of method as String.
	 * @return
	 */
	public String getMethodName() {
		return this.methodName;
	}
	
	/**
	 * Return the return type as the IClassVertex.
	 * @return
	 */
	public IClassVertex getReturnType() {
		return this.returnType;
	}
	
	/**
	 * Return parameters' types as a list.
	 * @return
	 */
	public List<IClassVertex> getParams() {
		return this.params;
	}
	
	/**
	 * add a parameter of the method into the list.
	 * @param param
	 */
	public void addParam(IClassVertex param) {
		this.params.add(param);
		
	}
	
}
