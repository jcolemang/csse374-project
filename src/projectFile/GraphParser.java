package projectFile;

import java.io.IOException;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public class GraphParser {
	
	public enum PrivacyLevel {
		PRIVATE,
		PROTECTED,
		PUBLIC
	}

	private ClassNodeGraph graph;
	private boolean recursivelyParse;
	private PrivacyLevel privacyLevel;

	public ClassNodeGraph parse(List<String> classNames) throws IOException{
		
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
		return graph;
	}
	

	public void setPrivacyLevel(PrivacyLevel p){
		this.privacyLevel = p;
	}
	

	public void setParseRecursively(boolean recursivelyParse){
		this.recursivelyParse = recursivelyParse;
	}
	
	
	private InterfaceVertex makeInterfaceVertex(ClassNode classNode) {
		InterfaceVertex iv = new InterfaceVertex();
		
		classNode.
		
		return iv;
	}
	
	
	private AbstractClassVertex makeAbstractVertex(ClassNode classNode) {
		return null;
	}
	
	
	private RegularClassVertex makeVanillaVertex(ClassNode classNode) {
		return null;
	}
}
