package CommandLineArgument;

import java.util.ArrayList;
import java.util.List;

import projectFile.DOMGraph;

public class SetSettingsCommandLineArgument implements ICommandLineArgument{
	private Configuration config;
	private DOMGraph g;
	
	public SetSettingsCommandLineArgument(DOMGraph g) {
		this.g = g;
		this.config = Configuration.getInstance();
	}

	@Override
	public List<String> execute(List<String> args) {
		List<String> unusedArgs = new ArrayList<>();
	
		for (int i = 0; i < args.size(); i++) {
			if (args.get(i).equals("-path")) {
				String path = args.get(i + 1);
				
				this.config.readFromSettingsFile("C:/Users/wickersl/Desktop/PROJ-374/csse374-project/input_output/defaultsettings.txt");
			} else if ((i - 1 > 0) && args.get(i - 1).equals("-path")){
				//skip this
			} else {
				unusedArgs.add(args.get(i));
			}
		}
		return unusedArgs;
	}

}
