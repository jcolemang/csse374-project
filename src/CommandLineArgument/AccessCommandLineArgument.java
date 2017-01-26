package CommandLineArgument;

import java.util.ArrayList;
import java.util.List;

import projectFile.DOMGraph;

public class AccessCommandLineArgument implements ICommandLineArgument {
	
	private Configuration config;
	private DOMGraph g;
	
	public AccessCommandLineArgument(DOMGraph g) {
		this.g = g;
		this.config = Configuration.getInstance();
	}

	@Override
	public List<String> execute(List<String> args) {
		List<String> unusedArgs = new ArrayList<>();
		String access = "";
		
		for (String a : args) {
			switch (a) {
			case "-private":
				access = "private";
				
				break;
			case "-protected":
				access = "protected";
				break;
			case "-public":
				access = "public";
				break;
			default: 
				unusedArgs.add(a);
			}
		}
		
		if (!access.equals("")) {
			this.g.setDefaultAccessLevel(access);
		}
		
		return unusedArgs;
	}

}
