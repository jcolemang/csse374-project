package projectFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class GraphParser {

	private String privacyLevel;
	private Map<String, IClassVertex> visited = new HashMap<>();

	public ClassNodeGraph parse(List<String> classNames) throws IOException{
		
		ClassNodeGraph graph = new ClassNodeGraph();
		
		for (String className : classNames) {
			IClassVertex newVertex = makeSingleNode(className);
			graph.addClassVertex(newVertex);
		}
		return graph;
	}
	

	private IClassVertex makeSingleNode(String className) {
		IClassVertex classVertex;
		
		try {
			ClassReader reader = new ClassReader(className);
			ClassNode classNode = new ClassNode();
			reader.accept(classNode, ClassReader.EXPAND_FRAMES);
            // TODO: Consider turning this into a factory pattern ?
            if((classNode.access & Opcodes.ACC_INTERFACE) != 0) {
                classVertex = this.makeInterfaceVertex(classNode);
            } else if ((classNode.access & Opcodes.ACC_ABSTRACT) != 0) {
                classVertex = this.makeAbstractVertex(classNode);
            } else {
                classVertex = this.makeVanillaVertex(classNode);
            }
		} catch (IOException e) {
			classVertex = this.makePrimitiveVertex(className);
		}
		
		
		visited.put(className, classVertex);
		
		return classVertex;
	}
	

	public ClassNodeGraph parse(String packageName){
		return null;
	}
	

	public void setPrivacyLevel(String p){
		this.privacyLevel = p;
	}	
	
	private InterfaceVertex makeInterfaceVertex(ClassNode classNode) throws IOException {
		String name = Type.getObjectType(classNode.name).getClassName();
		InterfaceVertex iv = new InterfaceVertex(name);
		this.addVisit(name, iv);
		this.setFields(classNode, iv);
		this.setMethods(classNode, iv);
		
		return iv;
	}
	

	private AbstractClassVertex makeAbstractVertex(ClassNode classNode) throws IOException {
		String name = Type.getObjectType(classNode.name).getClassName();
		AbstractClassVertex av = new AbstractClassVertex(name);
		this.addVisit(name, av);
		this.setFields(classNode, av);
		this.setMethods(classNode, av);
		
		return av;
	}
	
	
	private RegularClassVertex makeVanillaVertex(ClassNode classNode) throws IOException {
		String name = Type.getObjectType(classNode.name).getClassName();
		RegularClassVertex vv = new RegularClassVertex(name);
		this.addVisit(name, vv);
		this.setFields(classNode, vv);
		this.setMethods(classNode, vv);
		
		return vv;
	}
	
	
	private PrimitiveVertex makePrimitiveVertex(String className) {
		PrimitiveVertex primitiveVertex = new PrimitiveVertex(className);
		this.addVisit(className, primitiveVertex);
		return primitiveVertex;
	}
	
	
	private void setFields(ClassNode classNode, IClassVertex cv) throws IOException {
		@SuppressWarnings("unchecked")
		List<FieldNode> fields = classNode.fields;
		IClassVertex realType;
		
		for (FieldNode f : fields) {
			String name = f.name;
			String accessLevel = this.getAccessLevel(f.access);
			String fieldType = Type.getType(f.desc).getClassName();
			
			if (hasBeenVisited(fieldType)) {
				realType = this.visited.get(fieldType);
			} else {
				realType = this.makeSingleNode(fieldType);
			}
			cv.addFieldData(new FieldData(accessLevel, name, realType));
		}
		
	}
	
	private void setMethods(ClassNode classNode, IClassVertex cv) throws IOException {
		List<MethodNode> methods = classNode.methods;
		IClassVertex realReturnType;
		
		for (MethodNode m : methods) {
			String name = m.name;
			String access = this.getAccessLevel(m.access);
			String returnType = Type.getReturnType(m.desc).getClassName();
			IClassVertex param;
			String paramName;
			
			if (hasBeenVisited(returnType)) {
				realReturnType = this.visited.get(returnType);
			} else {
				realReturnType = this.makeSingleNode(returnType);
			}
			
			MethodData mdToAdd = new MethodData(access, name, realReturnType);
			
			for (Type paramType : Type.getArgumentTypes(m.desc)) {
				paramName = paramType.getClassName();
				if (hasBeenVisited(paramName)) {
					param = this.visited.get(paramName);
				} else {
					param = this.makeSingleNode(paramName);
				}
				
				mdToAdd.addParam(param);

			}
			
			cv.addMethodData(mdToAdd);
			
		}
	}
	
	/*
	 * TODO Should <clinit> and <init> be handled differently?
	 */
	private String getAccessLevel(int opcode) {
		
		if((opcode & Opcodes.ACC_PUBLIC) > 0) {
			return "public";
		} else if((opcode & Opcodes.ACC_PRIVATE) > 0) {
			return "private";
		} else if((opcode & Opcodes.ACC_PROTECTED) > 0) {
			return "protected";
		} else {
			return "native";
		} 
	}
	

	private boolean hasBeenVisited(String className) {
		return this.visited.containsKey(className);
	}
	

	private void addVisit(String className, IClassVertex vert) {
		this.visited.put(className, vert);
	}
}
