package CommandLineArgument;

import java.util.List;

public interface ICommandLineArgument {

	/**
	 * Parses the command line arguments as needed and returns
	 * the unneeded command line arguments. This is to prevent 
	 * repeats and to make it simple to distinguish arguments from
	 * classes
	 * 
	 * @param args Potential command line arguments
	 * @return Unused command line arguments
	 */
	List<String> execute(List<String> args);
}
