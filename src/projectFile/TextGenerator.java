package projectFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TextGenerator {
	
	private final String FOLDER_NAME = "./input_output/";

	public void writeFile(String fileName, DOMGraph domGraph) throws IOException {
		FileOutputStream out = new FileOutputStream(FOLDER_NAME + fileName);
		
		out.write("digraph AWESOMEGRAPH {\n".getBytes());
		out.write("rankdir = TB\n".getBytes());
		out.write(("fontsize = " + domGraph.getFontSize() + "\n").getBytes());

		String name;

		for (DOMClassNode node : domGraph) {
			name = node.getClassName();
			out.write((name + " [shape = box]").getBytes());
		}
		
		out.write("}".getBytes());
		
		out.close();
	}
	
	
	

	
	
}
