package analyzers;

import java.util.List;

import DOMNodes.IDOMClassNode;
import graphNodes.IClassVertex;
import projectFile.ClassNodeGraph;
import projectFile.DOMGraph;
import projectFile.MethodData;

public class SingletonDetector extends AbstractAnalyzer {

	@Override
	public void analyze(IClassVertex v, ClassNodeGraph g, DOMGraph d) {
		
		IDOMClassNode dn;
		this.setVisited(v);

		List<MethodData> methods = v.getMethods();
		for (MethodData md: methods){
			if ((md.getMethodName().equals("<init>"))&&(md.getAccessLevel().equals("private"))){
				if(v.getCorrespondingDOMNode()!=null){
					dn = v.getCorrespondingDOMNode();
                    dn.addAttribute("color", "\"blue\"");
                    String title = dn.getTitle()+"\\n\\<\\<Singleton\\>\\>";
                    dn.setTitle(title);
                    break;
				}
			}
		}
	}
}
