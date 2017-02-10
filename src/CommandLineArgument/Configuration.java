package CommandLineArgument;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Configuration {
	
	private static Configuration soleInstance;
	
	private String packageName = "";
	private List<String> whitelist = new ArrayList<>();
	private List<String> blacklist = new ArrayList<>();
	private List<String> analyzers = new ArrayList<>();
	private boolean synthdisplay;
	private String graphColor;
	private String graphBGColor;
	
	private boolean recursivelyParse;
	private String access;
	private int fontSize;
	private String fontColor;
	
	private final String defaultPath = "input_output/FooBarTest.txt";

	private Map<String, String> properties;
	
	private Configuration() {
	    this.properties = new HashMap<>();
	}
	
	public static Configuration getInstance() {
		if (soleInstance == null) {
			soleInstance = new Configuration();		
		}
		
		return soleInstance;
	}
	
	public void readFromSettingsFile() {
		readFromSettingsFile(this.defaultPath);
	}
	
	public void readFromSettingsFile(String settingsFile) {

		try (BufferedReader br = new BufferedReader(new FileReader(settingsFile))) {
			
			String line = "";
		    
		    while ((line = br.readLine()) != null) {
		    	String[] lineSplit = line.split(": ");
		    	String lineHeader = lineSplit[0];
		    	switch (lineHeader) {
			    	case "include":
			    		this.populate(lineSplit[1].split(" "), this.whitelist);
			    		System.out.println("WHITELIST: " + this.whitelist);
			    		break;
			       case "disclude": 
			    		this.populate(lineSplit[1].split(" "), this.blacklist);
			    		System.out.println("BLACKLIST: " + this.blacklist);
			    		break;
			       case "synthdisplay":
			    	   if (lineSplit[1].equals("off")) {
			    		   this.synthdisplay = false;
			    	   } else {
			    		   this.synthdisplay = true;
			    	   } break;
			       case "analyzers": 
			    		this.populate(lineSplit[1].split(" "), this.analyzers);
			    		break;
			       case "access":
			    	   this.access = lineSplit[1];
			    	   break;
			       case "recursive":
			    	   if (lineSplit[1].equals("off")) {
			    		   this.recursivelyParse = false;
			    	   } else {
			    		   this.recursivelyParse = true;
			    	   } break;
			       case "graphColor":
			    	   this.graphColor = lineSplit[1];
			    	   break;
			       case "graphBGColor":
			    	   this.graphBGColor = lineSplit[1];
			    	   break;
			       case "fontColor":
			    	   this.fontColor = lineSplit[1];
			    	   break;
			       case "fontSize":
			    	   this.fontSize = Integer.parseInt(lineSplit[1]);
			    	   break;
					default:
						String prop = "";
						for (int i = 1; i < lineSplit.length; i++) {
							prop += lineSplit[i];
						}
						this.setProperty(lineSplit[1], prop);
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
	
	public String getGraphColor() {
		return this.graphColor;
	}
	
	public String getGraphBGColor() {
		return this.graphBGColor;
	}
	
	public String getFontColor() {
		return this.fontColor;
	}
	
	public boolean getRecursivelyParse() {
		return this.recursivelyParse;
	}
	
	public String getAccess() {
		return this.access;
	}
	
	public int getFontSize() {
		return this.fontSize;
	}

	public String getProperty(String name) {
		return this.properties.get(name);
	}
	
	
	// Start of setters
	public void setPackageName(String name) {
		this.packageName = name;
	}
	
	public void setSynthDisplay(boolean yesOrNo) {
		this.synthdisplay = yesOrNo;
	}
	
	public void setAnalyzers(List<String> list) {
		this.analyzers = list;
	}
	
	public void setRecursivelyParse(boolean val) {
		this.recursivelyParse = val;
	}

	public void setProperty(String name, String value) {
		this.properties.put(name, value);
	}
	
	
	public void setGraphColor(String color) {
		this.graphColor = color;
	}
	
	public void setAccess(String acc) {
		this.access = acc;
	}
	
	public void setFontSize(int size) {
		this.fontSize = size;
	}
	
	public void addToWhitelist(String name) {
		this.whitelist.add(name);
	}
	
	public void addToBlacklist(String name) {
		this.blacklist.add(name);
	}
	
	private void populate(String[] things, List<String> into) {
		for (int i = 0; i < things.length; i++) {
			into.add(things[i]);
		}
	}
	
	public boolean isBlacklisted(String title) {
		for (String yep : this.whitelist) {
			if (title.equals(yep)) {
			    System.out.println("Whitelisted: " + title);
				return false;
			}
		}

		for (String nope : this.blacklist) {
			if (title.startsWith(nope)) {
//				System.out.println("Blacklisted: " + title);
				return true;
			}
		}

		System.out.println("Whitelisted: " + title);
		return false;
	}

}
