package projectFile;

import java.io.IOException;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public class GraphParser {
	
	private ClassNodeGraph graph;
	private boolean recursivelyParse;
	public enum PrivacyLevel {
		PRIVATE,
		PROTECTED,
		PUBLIC
	}
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
		
		if((classNode.access & Opcodes.ACC_INTERFACE) != 0) {
			//go to interface node handler
		} else if () { //if abstract
			//go to abstract node handler
		}
		
		return null;
		
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
}
