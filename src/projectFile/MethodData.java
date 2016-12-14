package projectFile;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.MethodNode;

public class MethodData {
	private String access;
	private String methodName;
	private IClassVertex returnType;
	private List<IClassVertex> params; // can result in a "Depends on" relationship
	
	public MethodData(String access, String methodName, IClassVertex returnType) { //wb params??
		this.access = access;
		this.methodName = methodName;
		this.returnType = returnType;
		this.params = new ArrayList<IClassVertex>();
	}
	
	public String getAccessLevel() {
		return this.access;
	}
	
	public String getMethodName() {
		return this.methodName;
	}
	
	public IClassVertex getReturnType() {
		return this.returnType;
	}
	
	public List<IClassVertex> getParams() {
		return this.params;
	}

	public void addParam(IClassVertex param) {
		this.params.add(param);
		
	}
	
}
