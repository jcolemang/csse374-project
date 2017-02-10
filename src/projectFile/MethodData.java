package projectFile;

import java.util.*;

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
	private List<CodeData> codeDatas;
	
	
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
		this.codeDatas = new LinkedList<>();
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


	public void addCodeData(CodeData data) {
		this.codeDatas.add(data);
	}


	public List<CodeData> getCodeData() {
		return this.codeDatas;
	}


	public boolean isAnInitializer() {
		return this.getMethodName().startsWith("<");
	}


	@Override
	public boolean equals(Object other) {
		if (!(other instanceof MethodData)) {
			return false;
		}

		MethodData otherData = (MethodData) other;
		if (!otherData.getMethodName().equals(this.getMethodName())) {
		    System.out.println(this.getMethodName());
			return false;
		}

		if (this.getParams().size() != otherData.getParams().size()) {
			return false;
		}

		for (int i = 0; i < this.getParams().size(); i++) {
			if (!this.getParams().get(i).equals(otherData.getParams().get(i))) {
				return false;
			}
		}

		return true;
	}
	

}
