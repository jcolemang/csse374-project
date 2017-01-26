package analyzers;

import java.util.List;

import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;
import projectFile.MethodData;

public class SingletonDetector extends AbstractAnalyzer {

	@Override
	public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
		// TODO Auto-generated method stub
		
		this.setVisited(v);
		List<MethodData> methods = v.getMethods();
		for (MethodData md: methods){
			if ((md.getMethodName().equals("<init>"))&&(md.getAccessLevel().equals("private"))){
				if(v.getCorrespondingDOMNode()!=null){
				v.getCorrespondingDOMNode().addAttribute("color", "\"blue\"");
				String title = v.getTitle()+"\n\\<\\<Singleton\\>\\>";
				System.out.println(title);
		 //	v.getCorrespondingDOMNode().addAttribute("label", title);
				break;
				}
			}
		}
		

	}

}
