package CommandLineArgument;

import java.util.ArrayList;
import java.util.List;

import projectFile.DOMGraph;

public class AccessCommandLineArgument implements ICommandLineArgument {
	
	private DOMGraph g;
	
	public AccessCommandLineArgument(DOMGraph g) {
		this.g = g;
	}

	@Override
	public List<String> execute(List<String> args) {
		List<String> unusedArgs = new ArrayList<>();
		String access = "";
		
		System.out.println("Top of thing");
		System.out.println(args);
		
		for (String a : args) {
			System.out.println(a);
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
		
		System.out.println(unusedArgs);
		System.out.println("Bottom of thing");
		return unusedArgs;
	}

}
