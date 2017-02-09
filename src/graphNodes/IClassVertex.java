package graphNodes;

import java.util.List;

import org.objectweb.asm.tree.FieldNode;

import DOMNodes.IDOMClassNode;
import projectFile.CodeData;
import projectFile.FieldData;
import projectFile.MethodData;

/**
 * This is an interface for some kinds of ClassVertex, like InterfaceVertex, 
 * PrimitiveVertex, and RegularClassVertex.
 * 
 * @author Administrator
 *
 */
public interface IClassVertex {
	 IDOMClassNode getCorrespondingDOMNode();
	 void setCorrespondingDOMNode(IDOMClassNode generatedDOMNode);
	 String getTitle();
	
	 void addMethodData(MethodData data);
	 void removeMethodData(MethodData data);
	 List<MethodData> getMethods();
	
	 List<IClassEdge> getEdges();
	 void addEdge(IClassEdge edge);
	
	 List<FieldData> getFields();
	 void addFieldData(FieldData data);
	 void removeFieldData(FieldData data);
	 void removeEdge(IClassEdge edge);

	 void addCodeData(CodeData data);
	 List<CodeData> getCodeData();
	
	IClassEdge getSuperclassEdge();
	List<IClassEdge> getImplementsEdges();

}
