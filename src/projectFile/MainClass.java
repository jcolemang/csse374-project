package projectFile;

import java.io.IOException;
import java.util.ArrayList;

import org.objectweb.asm.ClassReader;

public class MainClass {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		GraphParser gp = new GraphParser();
		ArrayList<String> strs = new ArrayList<String>();
		for(String str: args){
			strs.add(str);
		}
		ClassNodeGraph nodeGraph = gp.parse(strs);
		System.out.println(nodeGraph);
		
		DOMGraph dom = new DOMGraph();
		dom.generateDOMTree(nodeGraph);
		
		TextGenerator generator = new TextGenerator();
		generator.writeFile("out.dot", dom);
	}

}
