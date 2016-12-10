package projectFile;

import java.io.IOException;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;

public class GraphParser {
	
	public enum PrivacyLevel {
		PRIVATE,
		PROTECTED,
		PUBLIC
	}

	private boolean recursivelyParse;
	private PrivacyLevel privacyLevel;

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
		
		if((classNode.access & Opcodes.ACC_INTERFACE) != 0) {
			classVertex = this.makeInterfaceVertex(classNode);
		} else if ((classNode.access & Opcodes.ACC_ABSTRACT) != 0) {
			classVertex = this.makeAbstractVertex(classNode);
		} else {
			classVertex = this.makeVanillaVertex(classNode);
		}
		
		return classVertex;
	}
	

	public ClassNodeGraph parse(String packageName){
		return null;
	}
	

	public void setPrivacyLevel(PrivacyLevel p){
		this.privacyLevel = p;
	}
	

	public void setParseRecursively(boolean recursivelyParse){
		this.recursivelyParse = recursivelyParse;
	}
	
	
	private InterfaceVertex makeInterfaceVertex(ClassNode classNode) {
		String name = Type.getObjectType(classNode.name).getClassName();
		InterfaceVertex iv = new InterfaceVertex(name);
		return iv;
	}
	
	
	private AbstractClassVertex makeAbstractVertex(ClassNode classNode) {
		String name = Type.getObjectType(classNode.name).getClassName();
		AbstractClassVertex av =new AbstractClassVertex(name);
		return av;
	}
	
	
	private RegularClassVertex makeVanillaVertex(ClassNode classNode) {
		String name = Type.getObjectType(classNode.name).getClassName();
		RegularClassVertex vv =new RegularClassVertex(name);
		return vv;
	}
}
