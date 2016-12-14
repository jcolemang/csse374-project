package projectFile;

import java.util.List;

import org.objectweb.asm.Type;
import org.objectweb.asm.tree.MethodNode;

public class MethodData {
	private String access;
	private String methodName;
	private String returnType;
	private List<MethodNode> params; // can result in a "Depends on" relationship
	
}
