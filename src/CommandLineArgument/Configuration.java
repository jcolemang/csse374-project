package CommandLineArgument;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Configuration {
	
	private static Configuration soleInstance;
	
	private String packageName = "";
	private List<String> whitelist;
	private List<String> blacklist;
	private boolean synthdisplay;
	private List<String> analyzers;
	private List<String> flags;
	private String graphColor;
	
	private Configuration() {
		readFromSettingsFile("../../input_output/defaultsettings.txt");
	}
	
	private Configuration(String settingsFile) {
		readFromSettingsFile(settingsFile);
	}
	
	public static Configuration getInstance() {
		if (soleInstance == null) {
			soleInstance = new Configuration();		
		}
		
		return soleInstance;
	}
	
	private void readFromSettingsFile(String settingsFile) {
		try (BufferedReader br = new BufferedReader(new FileReader(settingsFile))) {
			
		    String line = "";
		    String[] lineSplit = line.split(": ");
		    String lineHeader = lineSplit[0];
		    
		    while ((line = br.readLine()) != null) {
		       switch (lineHeader) {
			       case "packageName":
			    	   this.packageName = lineSplit[1];
			    	   break;
			       case "whitelist":
			    	   this.whitelist = Arrays.asList(lineSplit[1].split(" "));
			    	   break;
			       case "blacklist": 
			    	   this.blacklist = Arrays.asList(lineSplit[1].split(" "));
			    	   break;
			       case "synthdisplay":
			    	   if (lineSplit[1].equals("off")) {
			    		   this.synthdisplay = false;
			    	   } else {
			    		   this.synthdisplay = true;
			    	   } break;
			       case "analyzers": 
			    	   this.analyzers = Arrays.asList(lineSplit[1].split(" "));
			    	   break;
			       case "flags":
			    	   this.flags = Arrays.asList(lineSplit[1].split(" "));
			    	   break;
			       case "graphColor":
			    	   this.graphColor = lineSplit[1];
			    	   break;
		       }
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//Start of getters
	public String getPackageName() {
		return this.packageName;
	}
	
	public List<String> getWhitelist() {
		return this.whitelist;
	}
	
	public List<String> getBlacklist() {
		return this.blacklist;
	}
	
	public boolean getSynthDisplay() {
		return this.synthdisplay;
	}
	
	public List<String> getAnalyzers() {
		return this.analyzers;
	}
	
	public List<String> getFlags() {
		return this.flags;
	}
	
	public String getGraphColor() {
		return this.graphColor;
	}
	
	// Start of setters
	public void setPackageName(String name) {
		this.packageName = name;
	}
	
	public void setWhitelist(List<String> list) {
		this.whitelist = list;
	}
	
	public void setetBlacklist(List<String> list) {
		this.blacklist = list;
	}
	
	public void setetSynthDisplay(boolean yesOrNo) {
		this.synthdisplay = yesOrNo;
	}
	
	public void setetAnalyzers(List<String> list) {
		this.analyzers = list;
	}
	
	public void setetFlags(List<String> list) {
		this.flags = list;
	}
	
	public void setetGraphColor(String color) {
		this.graphColor = color;
	}

}
