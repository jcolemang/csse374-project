package projectFile;

import java.io.IOException;
import java.util.ArrayList;

import org.objectweb.asm.ClassReader;

public class MainClass {

	public static void main(String[] args) throws IOException {
		GraphParser gp = new GraphParser();
		ArrayList<String> strs = new ArrayList<String>();
		for(String str: args) {
			strs.add(str);
		 }
		ClassNodeGraph nodeGraph = gp.parse(strs);
		
		DOMGraph dom = new DOMGraph();
		dom.setDisplayAll(true);
		dom.setClassesToDisplay(strs);
		dom.generateDOMTree(nodeGraph);

		TextAggregator generator = new TextAggregator();
		generator.writeFile("out.dot", dom);
		
	}

}
