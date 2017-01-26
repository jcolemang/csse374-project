package projectFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;
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
	private IClassVertex makeInterfaceVertex(ClassNode classNode, ClassNodeGraph g) throws IOException {
		String name = Type.getObjectType(classNode.name).getClassName();
		IClassVertex iv = new InterfaceVertex(name);
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
	 * 
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
            if ((classNode.access & Opcodes.ACC_INTERFACE) != 0) {
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
		
		
		this.addVisit(className, classVertex, g);
		return classVertex;
	}
	

	/*
	 * 
	 */
	private IClassVertex makeAbstractVertex(ClassNode classNode, ClassNodeGraph g) throws IOException {
		String name = Type.getObjectType(classNode.name).getClassName();
		IClassVertex av = new AbstractClassVertex(name);
		this.addVisit(name, av, g);
		this.setFields(classNode, av, g);
		this.setMethods(classNode, av, g);
		
		return av;
	}
	
	
	/*
	 * 
	 */
	private IClassVertex makeVanillaVertex(ClassNode classNode, ClassNodeGraph g) throws IOException {
		String name = Type.getObjectType(classNode.name).getClassName();
		IClassVertex vv = new RegularClassVertex(name);
		this.addVisit(name, vv, g);
		this.setFields(classNode, vv, g);
		this.setMethods(classNode, vv, g);
		
		return vv;
	}
	
	
	/*
	 * Used as a placeholder for primitive types, even though they are not classes.
	 */
	private IClassVertex makePrimitiveVertex(String className, ClassNodeGraph g) {
		IClassVertex primitiveVertex = new PrimitiveVertex(className);
		this.addVisit(className, primitiveVertex, g);
		return primitiveVertex;
	}


	/*
	 * 
	 */
	public ClassNodeGraph parse(List<String> classNames) throws IOException{
		
		ClassNodeGraph graph = new ClassNodeGraph();
		
		// Need multiple starting points in case the vertices are not connected
		for (String className : classNames) {
			makeSingleNode(className, graph);
		}
		
		return graph;
	}
	

	/*
	 * 
	 */
	private void setFields(ClassNode classNode, IClassVertex cv, ClassNodeGraph g) throws IOException {
		@SuppressWarnings("unchecked")
		List<FieldNode> fields = classNode.fields;
		List<IClassVertex> verts;
		List<String> vertStrings;
		IClassVertex realType;
		int i;
		IClassVertex paramVert;
		
		for (FieldNode f : fields) {
			String name = f.name;
			String accessLevel = this.getAccessLevel(f.access);
			String fieldType = Type.getType(f.desc).getClassName();
			
			if (hasBeenVisited(fieldType)) {
				realType = this.visited.get(fieldType);
			} else {
				realType = this.makeSingleNode(fieldType, g);
			}
			
			vertStrings = getTypeStrings(f.signature);
			verts = new ArrayList<IClassVertex>();
			for (i = 1; i < vertStrings.size(); i++) {
				paramVert = this.makeSingleNode(vertStrings.get(i), g);
				verts.add(paramVert);
				this.addAssociationEdge(cv, paramVert, g);
			}
			
			cv.addFieldData(new FieldData(accessLevel, name, realType, f.signature, verts));
		}
		
	}
	
	
	private String getMethodParameterString(String sig) {
		if (sig == null) {
			return null;
		}
		Pattern p = Pattern.compile("^(?:<.*>)*\\((.*)\\).*$");
		Matcher m = p.matcher(sig);
		m.find();
		return m.group(1);
	}
	
	
	private String getMethodReturnTypeString(String sig) {
		if (sig == null) {
			return null;
		}
		Pattern p = Pattern.compile("^(?:<.*>)*\\(.*\\)(.*)$");
		Matcher m = p.matcher(sig);
		m.find();
		return m.group(1);
	}
	
	
	/*
	 * All hope abandon, ye who enter here
	 */
	private void setMethods(ClassNode classNode, IClassVertex cv, ClassNodeGraph g) throws IOException {
		
		// general use
		List<MethodNode> methods = classNode.methods;
		IClassVertex realReturnType;
		int i;

		// return type objects
		List<IClassVertex> returnTypeTypeParams;
		List<String> returnTypeTypeStrings;
		IClassVertex returnTypeTypeVertex;
		String returnTypeTypeDesc;
		
		// parameter type objects
		List<IClassVertex> paramTypeVertices;
		List<List<IClassVertex>> paramTypeTypeParams;
		List<String> allParamTypeStrings;
		
		
		for (MethodNode m : methods) {
			String name = m.name;
			String access = this.getAccessLevel(m.access);
			String returnType = Type.getReturnType(m.desc).getClassName();
			paramTypeVertices = new ArrayList<>();
			paramTypeTypeParams = new ArrayList<>();
			allParamTypeStrings = new ArrayList<>();
			
			// parsing the return type
			if (hasBeenVisited(returnType)) {
				realReturnType = this.visited.get(returnType);
			} else {
				realReturnType = this.makeSingleNode(returnType, g);
			}
			
			// adding edge for method return type dependency
			this.addDependsEdge(cv, realReturnType, g);

			// the return type of the method. Can be null for some reason.
			// I assume this is when there are no arguments
			returnTypeTypeDesc = getMethodReturnTypeString(m.signature);
			if (returnTypeTypeDesc == null) {
				returnTypeTypeDesc = m.desc;
			}

			returnTypeTypeParams = new ArrayList<IClassVertex>();
			returnTypeTypeStrings = getTypeStrings(returnTypeTypeDesc);
			for (i = 1; i < returnTypeTypeStrings.size(); i++) {
				returnTypeTypeVertex = makeSingleNode(returnTypeTypeStrings.get(i), g);
				returnTypeTypeParams.add(returnTypeTypeVertex);
				this.addDependsEdge(cv, returnTypeTypeVertex, g);
			}
			
			
			String currentTypeDesc;
			List<String> currentTypeStrings;
			String currentTypeString;
			IClassVertex currentTypeVertex;
			String paramTypeStr;
			IClassVertex param;
				
			// one hundred percent a hack.
			// this will not work getting the one to many arrows for parameter types
			List<String> parameterTypeStrings = getTypeStrings(this.getMethodParameterString((m.signature == null ? m.desc : m.signature)));
			
			for (String s : parameterTypeStrings) {
				IClassVertex v = this.makeSingleNode(s, g);
				this.addDependsEdge(cv, v, g);

			}
			
			for (Type t : Type.getArgumentTypes(m.desc)) {
				currentTypeDesc = t.getDescriptor();
				currentTypeStrings = getTypeStrings(currentTypeDesc);
				paramTypeStr = t.getClassName();
				param = this.makeSingleNode(paramTypeStr, g);
				this.addDependsEdge(cv, param, g);
				
				List<IClassVertex> parameterTypes = new ArrayList<>();
				for (i = 1; i < currentTypeStrings.size(); i++) {
					currentTypeString = currentTypeStrings.get(i);
					currentTypeVertex = makeSingleNode(currentTypeString, g);
					this.addDependsEdge(cv, currentTypeVertex, g);
					parameterTypes.add(currentTypeVertex);
				}
				
				allParamTypeStrings.add(currentTypeDesc);
				paramTypeTypeParams.add(parameterTypes);
				
				paramTypeVertices.add(param);
			}
			
			MethodData md = new MethodData(access, name,
					realReturnType, returnTypeTypeDesc, returnTypeTypeParams,
					paramTypeVertices, allParamTypeStrings, paramTypeTypeParams);

			cv.addMethodData(md);
		}
	}
	
	
	private List<String> getTypeStrings(String descriptor) {
		String[] res;
		List<String> stuff = new ArrayList<>();
		String s;

		if (descriptor == null) {
			return stuff;
		}

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
