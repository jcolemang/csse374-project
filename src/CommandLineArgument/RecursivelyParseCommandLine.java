package CommandLineArgument;

import java.util.ArrayList;
import java.util.List;

import projectFile.DOMGraph;

public class RecursivelyParseCommandLine implements ICommandLineArgument {

	private Configuration config;
	private DOMGraph graph;
	
	public RecursivelyParseCommandLine(DOMGraph g){
		this.graph = g;
		this.config = Configuration.getInstance();
	}
	
	@Override
	public List<String> execute(List<String> args) {
		List<String> argsNotUsed = new ArrayList<>();

		for (String str: args) {
			if (str.equals("-recursive")){
				this.graph.setRecursivelyParse(true);
			} else {
				argsNotUsed.add(str);
			}
		}
		
		return argsNotUsed;
			
	}

}
