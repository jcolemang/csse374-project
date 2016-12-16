package projectFile;

public class RecursivelyParseCommandLine implements ICommandLineArgument {

	private DOMGraph graph;
	
	public RecursivelyParseCommandLine(DOMGraph g){
		this.graph = g;
	}
	
	@Override
	public void execute(String[] args) {
			
		for (String str: args){
			if(str.equals("-recursive")){
				this.graph.setRecursivelyParse(true);
			}
		}
			
	}

}
