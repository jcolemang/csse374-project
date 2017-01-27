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

		for (String a : args) {
			switch (a) {
			case "-private":
				this.config.setAccess(a);
				break;
			case "-protected":
				this.config.setAccess(a);
				break;
			case "-public":
				this.config.setAccess(a);
				break;
			default: 
				unusedArgs.add(a);
			}
		}
		
		return unusedArgs;
	}

}
