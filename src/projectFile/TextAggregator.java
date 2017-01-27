package projectFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import CommandLineArgument.Configuration;
import DOMNodes.IDOMNode;

public class TextAggregator {
	
	private final String FOLDER_NAME = "./input_output/";
	private Configuration config = Configuration.getInstance();

	/**
	 * Create the dot file and write the text we get from each DOMNode of DOMGraph in it.
	 * 
	 * @param fileName
	 * @param domGraph
	 * @throws IOException
	 */
	public void writeFile(String fileName, DOMGraph domGraph) throws IOException {
		FileOutputStream out = new FileOutputStream(FOLDER_NAME + fileName);
		
		this.writeHeader(out, config.getFontSize() + "");

		// Will take care of the nodes and edges
		for (IDOMNode node : domGraph) {
			out.write(node.getTextRepresentation().getBytes());
		}
		
		this.writeFooter(out);
		
		out.close();
	}
	
	/**
	 * Setting the head for each .dot document going to be made by following the dot language.
	 * Including the name, rankdir, fontsize and shape.
	 * 
	 * @param out
	 * @param fontSize
	 * @throws IOException
	 */
	public void writeHeader(OutputStream out, String fontSize) throws IOException {
		out.write("digraph AWESOMEGRAPH {\n".getBytes());
		out.write("rankdir = BT\n".getBytes());
		out.write(("fontsize = " + fontSize + "\n").getBytes());
		out.write(("color = \"" + config.getGraphColor() + "\"\n").getBytes());
		out.write(("bgcolor = \"" + config.getGraphBGColor() + "\"\n").getBytes());
		out.write(("fontcolor = \"" + config.getFontColor() + "\"\n").getBytes());
		out.write("node [ shape = record ]\n".getBytes());
	}
	
	/**
	 * Add the last character '}' in the end of each document we made.
	 * 
	 * @param out
	 * @throws IOException
	 */
	public void writeFooter(OutputStream out) throws IOException {
		out.write("}".getBytes());
	}
	
	
	

	
	
}
