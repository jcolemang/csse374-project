package projectFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeAnnotationNode;

import graphNodes.AbstractClassVertex;
import graphNodes.AssociationEdge;
import graphNodes.DependencyEdge;
import graphNodes.ExtendsEdge;
import graphNodes.IClassEdge;
import graphNodes.IClassVertex;
import graphNodes.ImplementsEdge;
import graphNodes.InterfaceVertex;
import graphNodes.PrimitiveVertex;
import graphNodes.RegularClassVertex;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GraphParser {

	private Map<String, IClassVertex> visited;

	public GraphParser() {
		this.visited = new HashMap<>();
	}
	

	/*
	 * Adds the vertex to our map of names to vertices
	 */
	private void addVisit(String className, IClassVertex vert, ClassNodeGraph g) {
		if (!hasBeenVisited(className)) {
            this.visited.put(className, vert);
            g.addClassVertex(vert);
		}
	}

	
	/*
	 * 
	 */
	private String getAccessLevel(int opcode) {
		
		if((opcode & Opcodes.ACC_PUBLIC) > 0) {
			return "public";
		} else if((opcode & Opcodes.ACC_PRIVATE) > 0) {
			return "private";
		} else if((opcode & Opcodes.ACC_PROTECTED) > 0) {
			return "protected";
		} else {
			return "default";
		} 
	}
	

	/*
	 * Returns true if and only if the vertex has been visited and added to our
	 * map of names to vertex objects
	 */
	private boolean hasBeenVisited(String className) {
		return this.visited.containsKey(className);
	}
	

	/*
	 * 
	 */
	private InterfaceVertex makeInterfaceVertex(ClassNode classNode, ClassNodeGraph g) throws IOException {
		String name = Type.getObjectType(classNode.name).getClassName();
		InterfaceVertex iv = new InterfaceVertex(name);
		this.addVisit(name, iv, g);
		this.setFields(classNode, iv, g);
		this.setMethods(classNode, iv, g);
		
		return iv;
	}
	
	
	private IClassEdge addDependsEdge(IClassVertex from, IClassVertex to, ClassNodeGraph g) {
		if (g.containsEdgeType(from, to, DependencyEdge.class)) {
			return g.getEdgeWithType(from, to, DependencyEdge.class);
		}
        IClassEdge dependsEdge = new DependencyEdge();
        dependsEdge.set(from, to);
        g.addClassEdge(dependsEdge);
        from.addEdge(dependsEdge);
        return dependsEdge;
	}
	
	
	private IClassEdge addAssociationEdge(IClassVertex from, IClassVertex to, ClassNodeGraph g) {
		if (g.containsEdgeType(from, to, AssociationEdge.class)) {
			return g.getEdgeWithType(from, to, AssociationEdge.class);
		}
        IClassEdge edge = new AssociationEdge();
        edge.set(from, to);
        g.addClassEdge(edge);
        from.addEdge(edge);
        return edge;
	}
	

	/*
	 * TODO: Consider turning this into a factory pattern?
	 * This "if this then make one of these" pattern tells me 
	 * that is something we should do.
	 */
	private IClassVertex makeSingleNode(String className, ClassNodeGraph g) {
		
		// taking care of array nonsense
		// hardcore external coupling
		// unavoidable as far as I can tell
		className = className.replaceAll("\\[\\]", "")
				.replaceAll("/", "\\.");
		
		if (hasBeenVisited(className)) {
			return this.visited.get(className);
		}
		
		
		IClassVertex classVertex;
		IClassVertex superClassVertex;
		IClassVertex interfaceVertex;
		IClassVertex fieldVertex;
		ClassNode classNode = new ClassNode();
		ClassReader reader;
		IClassEdge edge;
		
		// parsing the class itself
		try {
			// ASM setup code
			reader = new ClassReader(className);
			reader.accept(classNode, ClassReader.EXPAND_FRAMES);
			
			// checking the types and delegating to separate methods
            if((classNode.access & Opcodes.ACC_INTERFACE) != 0) {
                classVertex = this.makeInterfaceVertex(classNode, g);
            } else if ((classNode.access & Opcodes.ACC_ABSTRACT) != 0) {
                classVertex = this.makeAbstractVertex(classNode, g);
            } else {
                classVertex = this.makeVanillaVertex(classNode, g);
            }

		} catch (IOException classException) {
			System.out.println("Could not find class: " + className);
			System.out.println("Considering class as a primitive");
			classVertex = this.makePrimitiveVertex(className, g);
		}
		
		
        // parsing the superclass
        String superClassName = classNode.superName;
        if (superClassName != null) {
        	superClassVertex = this.makeSingleNode(superClassName, g);
        	edge = new ExtendsEdge();
        	edge.set(classVertex, superClassVertex);
        	classVertex.addEdge(edge);
        	g.addClassEdge(edge);
        }
		
		// parsing the interfaces
		for (Object inter: classNode.interfaces) {
			interfaceVertex = this.makeSingleNode(inter.toString(), g);
			edge = new ImplementsEdge();
			edge.set(classVertex, interfaceVertex);
			classVertex.addEdge(edge);
        	g.addClassEdge(edge);
		}
		
		// parsing the instance variables
		// TODO go through here
		List<String> classes;
		for (Object f : classNode.fields) {
			FieldNode field = (FieldNode)f;
			classes = this.getTypeStrings(Type.getType(field.desc).toString());
			
			for (String clazz : classes) {
                fieldVertex = this.makeSingleNode(clazz, g);
                this.addAssociationEdge(classVertex, fieldVertex, g);
			}
		}
		
		// parsing the lines of code
		
		
		this.addVisit(className, classVertex, g);
		return classVertex;
	}
	

	/*
	 * 
	 */
	private AbstractClassVertex makeAbstractVertex(ClassNode classNode, ClassNodeGraph g) throws IOException {
		String name = Type.getObjectType(classNode.name).getClassName();
		AbstractClassVertex av = new AbstractClassVertex(name);
		this.addVisit(name, av, g);
		this.setFields(classNode, av, g);
		this.setMethods(classNode, av, g);
		
		return av;
	}
	
	
	/*
	 * 
	 */
	private RegularClassVertex makeVanillaVertex(ClassNode classNode, ClassNodeGraph g) throws IOException {
		String name = Type.getObjectType(classNode.name).getClassName();
		RegularClassVertex vv = new RegularClassVertex(name);
		this.addVisit(name, vv, g);
		this.setFields(classNode, vv, g);
		this.setMethods(classNode, vv, g);
		
		return vv;
	}
	
	
	/*
	 * Used as a placeholder for primitive types, even though they are not classes.
	 */
	private PrimitiveVertex makePrimitiveVertex(String className, ClassNodeGraph g) {
		PrimitiveVertex primitiveVertex = new PrimitiveVertex(className);
		this.addVisit(className, primitiveVertex, g);
		return primitiveVertex;
	}


	/*
	 * 
	 */
	public ClassNodeGraph parse(List<String> classNames) throws IOException{
		
		ClassNodeGraph graph = new ClassNodeGraph();
		IClassVertex vertex;
		
		// Need multiple starting points in case the vertices are not connected
		for (String className : classNames) {
			vertex = makeSingleNode(className, graph);
		}
		
		return graph;
	}
	

	/*
	 * Will take a Java package and will parse each of the classes in it
	 * TODO Implement this
	 */
	public ClassNodeGraph parse(String packageName){
		throw new NotImplementedException();
	}
	
	
	/*
	 * 
	 */
	private void setFields(ClassNode classNode, IClassVertex cv, ClassNodeGraph g) throws IOException {
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
				realType = this.makeSingleNode(fieldType, g);
			}
			cv.addFieldData(new FieldData(accessLevel, name, realType));
		}
		
	}
	
	
	/*
	 * 
	 */
	private void setMethods(ClassNode classNode, IClassVertex cv, ClassNodeGraph g) throws IOException {
		List<MethodNode> methods = classNode.methods;
		IClassVertex realReturnType;
		IClassEdge dependsEdge;
		
		for (MethodNode m : methods) {
			String name = m.name;
			String access = this.getAccessLevel(m.access);
			String returnType = Type.getReturnType(m.desc).getClassName();
			IClassVertex param;
			String paramName;
			
			if (hasBeenVisited(returnType)) {
				realReturnType = this.visited.get(returnType);
			} else {
				realReturnType = this.makeSingleNode(returnType, g);
			}
			
			// adding edge for method return type dependency
			this.addDependsEdge(cv, realReturnType, g);
			
			MethodData mdToAdd = new MethodData(access, name, realReturnType);
			
			for (Type paramType : Type.getArgumentTypes(m.desc)) {
				paramName = paramType.getClassName();
				if (hasBeenVisited(paramName)) {
					param = this.visited.get(paramName);
				} else {
					param = this.makeSingleNode(paramName, g);
				}

				// adding edge for method parameter dependency
				dependsEdge = this.addDependsEdge(cv, param, g);
				mdToAdd.addParam(param);
			}
			
			cv.addMethodData(mdToAdd);
			
		}
	}
	
	
	private List<String> getTypeStrings(String descriptor) {
		String[] res;
		List<String> stuff = new ArrayList<>();
		String s;
		res = descriptor.split("[^\\w./\\$]");
		for (int i = 0; i < res.length; i++) {
			s = res[i];
			if (s.startsWith("L")) {
				stuff.add(s.substring(1));
			}
		}
		return stuff;
	}
}
