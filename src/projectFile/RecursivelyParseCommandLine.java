package projectFile;

import java.util.ArrayList;
import java.util.List;

public class RecursivelyParseCommandLine implements ICommandLineArgument {

	private DOMGraph graph;
	
	public RecursivelyParseCommandLine(DOMGraph g){
		this.graph = g;
	}
	
	@Override
	public List<String> execute(List<String> args) {
		List<String> argsNotUsed = new ArrayList<>();

		for (String str: args){
			if (str.equals("-recursive")){
				this.graph.setRecursivelyParse(true);
			} else {
				argsNotUsed.add(str);
			}
		}
		
		return argsNotUsed;
			
	}

}
