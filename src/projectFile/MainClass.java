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
		ClassNodeGraph NodeGraph = gp.parse(strs);
		System.out.println(NodeGraph);
	}

}
