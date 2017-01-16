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
		this.params = new ArrayList<IClassVertex>();
		this.returnTypeString = returnSig;

        if (this.returnTypeString.contains("Foo") || 
                this.returnTypeString.contains("Bar")) {
            System.out.println("In the method" + this.returnTypeString);
        }
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
	
	public String getReturnTypeString() {
		return this.returnTypeString.equals("()V") ? "void" : this.returnTypeString;
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
