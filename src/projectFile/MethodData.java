package projectFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.MethodNode;

import graphNodes.IClassVertex;

public class MethodData {
	private String access;
	private String methodName;
	private IClassVertex returnType;
	private String returnTypeString;
	private List<IClassVertex> params; // can result in a "Depends on" relationship
	private List<IClassVertex> returnTypeTypeParams;
	private List<List<IClassVertex>> paramTypeTypeParams;
	private List<String> paramTypeStrings;
	
	
	/**
	 * Collect the information of each method in class, include access, name and return type.
	 * 
	 * @param access
	 * @param methodName
	 * @param returnType
	 */
	public MethodData(String access, String methodName, 
			IClassVertex returnType, String returnSig, List<IClassVertex> returnTypeParams,
			List<IClassVertex> paramTypes, List<String> paramStringDescs, List<List<IClassVertex>> methodParamTypeParams) {
		this.access = access;
		this.methodName = methodName;
		this.returnType = returnType;
		this.params = paramTypes;
		this.returnTypeString = returnSig;
		this.returnTypeTypeParams = returnTypeParams;
		this.paramTypeTypeParams = methodParamTypeParams;
		this.paramTypeStrings = paramStringDescs;
	}
	
	
	@Override
	public String toString() {
		String s = "";
		s += "Name: " + this.methodName;
		s += "\n\tParams: " + this.params;
		s += "\n\tReturn type: " + this.returnTypeString;
		return s;
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
	
	
	public List<IClassVertex> getReturnTypeTypeParameters() {
		return this.returnTypeTypeParams;
	}
	
	
	public String getReturnTypeString() {
		return this.returnTypeString.matches("^(.*)V$") ? "void" : this.returnTypeString;
	}
	
	/**
	 * Return parameters' types as a list.
	 * @return
	 */
	public List<IClassVertex> getParams() {
		return this.params;
	}
	
	
	public List<String> getParamStrings() {
		return this.paramTypeStrings;
	}
	
	
	/**
	 * For your own sanity please never use this
	 * @return
	 */
	public List<List<IClassVertex>> getParamTypeParams() {
		return this.paramTypeTypeParams;
	}
	
	
	/**
	 * add a parameter of the method into the list.
	 * @param param
	 */
//	public void addParam(IClassVertex param, List<String> typeParams) {
//		this.params.add(param);
//		for (String s : typeParams) {
//			this.addTypeEffect(param.getTitle(), s);
//		}
//		
//	}
	
//	private void addTypeEffect(String thing, String thingEffected) { 
//		List<String> things;
//		if (this.getTypeEffects.containsKey(thing)) {
//			things = this.getTypeEffects.get(thing);
//			things.add(thingEffected);
//		} else {
//			things = new ArrayList<>();
//			this.getTypeEffects.put(thing, things);
//		}
//		
//		
//	}
	
}
