package projectFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TextGenerator {
	
	private final String FOLDER_NAME = "./input_output/";

	public void writeFile(String fileName, DOMGraph domGraph) throws IOException {
		FileOutputStream out = new FileOutputStream(FOLDER_NAME + fileName);
		

		this.writeHeader(out, domGraph.getFontSize() + "");

		String name;
		for (DOMClassNode node : domGraph) {
			name = node.getClassName();
			out.write((name + " [shape = box]\n").getBytes());
			System.out.println(name);
		}
		
		this.writeFooter(out);
		
		out.close();
	}
	
	
	public void writeHeader(OutputStream out, String fontSize) throws IOException {
		out.write("digraph AWESOMEGRAPH {\n".getBytes());
		out.write("rankdir = TB\n".getBytes());
		out.write(("fontsize = " + fontSize + "\n").getBytes());
	}
	
	
	public void writeFooter(OutputStream out) throws IOException {
		out.write("}".getBytes());
	}
	
	
	

	
	
}
