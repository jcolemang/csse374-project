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
	

	private IClassVertex makeSingleNode(String className) throws IOException{
		ClassReader reader = new ClassReader(className);
		ClassNode classNode = new ClassNode();
		reader.accept(classNode, ClassReader.EXPAND_FRAMES);
		IClassVertex classVertex;
		
		// TODO: Consider turning this into a factory pattern ?
		if((classNode.access & Opcodes.ACC_INTERFACE) != 0) {
			classVertex = this.makeInterfaceVertex(classNode);
		} else if ((classNode.access & Opcodes.ACC_ABSTRACT) != 0) {
			classVertex = this.makeAbstractVertex(classNode);
		} else {
			classVertex = this.makeVanillaVertex(classNode);
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
		
		return iv;
	}
	

	private AbstractClassVertex makeAbstractVertex(ClassNode classNode) throws IOException {
		String name = Type.getObjectType(classNode.name).getClassName();
		AbstractClassVertex av = new AbstractClassVertex(name);
		this.addVisit(name, av);
		
		this.setFields(classNode, av);
		
		return av;
	}
	
	
	private RegularClassVertex makeVanillaVertex(ClassNode classNode) throws IOException {
		String name = Type.getObjectType(classNode.name).getClassName();
		RegularClassVertex vv = new RegularClassVertex(name);
		this.addVisit(name, vv);
		
		this.setFields(classNode, vv);
		
		return vv;
	}
	
	
	private void setFields(ClassNode classNode, IClassVertex cv) throws IOException {
		
		@SuppressWarnings("unchecked")
		List<FieldNode> fields = classNode.fields;
		IClassVertex realType;
		
		for(FieldNode f : fields) {
			String name = f.name;
			String accessLevel = this.getAccessLevel(f.access);
			String fieldType = Type.getType(f.desc).toString();
			
			if (hasBeenVisited(name)) {
				realType = this.visited.get(fieldType);
			} else {
				realType = this.makeSingleNode(fieldType);
			}
			cv.addFieldData(new FieldData(accessLevel, name, fieldType));
		}
		
		
	}
	
	private String getAccessLevel(int opcode) {
		
		if((opcode & Opcodes.ACC_PUBLIC) > 0) {
			return "public";
		} else if((opcode & Opcodes.ACC_PRIVATE) > 0) {
			return "private";
		} else if((opcode & Opcodes.ACC_PROTECTED) > 0) {
			return "protected";
		} else {
			throw new IllegalArgumentException("Access level not recognized");
		}
	}
	
	private boolean hasBeenVisited(String className) {
		return this.visited.containsKey(className);
	}
	
	private void addVisit(String className, IClassVertex vert) {
		this.visited.put(className, vert);
	}
}
