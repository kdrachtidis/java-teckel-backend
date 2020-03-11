package parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Browser;

public class BrowserParser {
	public String parse(String oldValue, String[][] file) {
		
		//Find browser type in CSV file
		String browser = "notFound";
		for (int i = 0; i < file.length; i++){
			if (file[i][0].equals("Browser Details")){
				for (int j = 1; j < file[i].length; j++){
					if (file[i][j].equals("Browser Version")){
						browser = file[i+1][j];
						if (browser.indexOf("OPR") != -1){
							browser = "Others";
						} else if (browser.indexOf("Edge") != -1){
							browser = "Edge";
						} else if (browser.indexOf("Firefox") != -1){
							browser = "Firefox";
						} else if (browser.indexOf("Chrome") != -1){
							browser = "Chrome";
						} else if (browser.indexOf("Safari") != -1){
							browser = "Safari";
						} else if (browser.indexOf("AppleWebKit") == -1){
							browser = "Internet Explorer";
						} else {
							browser = "Others";
						}
						break;
					}
				}
				break;
			}
		}
		
		//Load existing browser counts
		Browser[] browsers = loadJSON(oldValue);
		
		//Update particular browser value by adding 1
		int sum = 0;
		for(int j = 0; j < browsers.length; j++){
			if (browsers[j].getBrowser().equals(browser)){
				browsers[j].setCount( browsers[j].getCount() + 1 );
			}
			sum+= browsers[j].getCount();
		}
		
		//Update all percentage values
		double tmp = 0;
		for(int j = 0; j < browsers.length; j++){
			tmp = 100.0 * browsers[j].getCount()/sum;
			browsers[j].setPercentage(Math.round(100.0 * tmp)/100.0);
		}
		
		return storeJSON(browsers);
	}
	
	//Create empty general information json
	public String createStory() {
		Browser chrome = new Browser();
		chrome.setBrowser("Chrome");
		chrome.setCount(0);
		chrome.setPercentage(0);
		
		Browser ie = new Browser();
		ie.setBrowser("Internet Explorer");
		ie.setCount(0);
		ie.setPercentage(0);
		
		Browser firefox = new Browser();
		firefox.setBrowser("Firefox");
		firefox.setCount(0);
		firefox.setPercentage(0);
		
		Browser safari = new Browser();
		safari.setBrowser("Safari");
		safari.setCount(0);
		safari.setPercentage(0);
		
		Browser edge = new Browser();
		edge.setBrowser("Edge");
		edge.setCount(0);
		edge.setPercentage(0);
		
		Browser others = new Browser();
		others.setBrowser("Others");
		others.setCount(0);
		others.setPercentage(0);
		
		Browser[] browsers = {chrome, ie, firefox,safari, edge, others};
		
		return storeJSON(browsers);
	}
	
	//Write browser information to String
	public String storeJSON(Browser[] browser) {
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		String jsonInString = gson.toJson(browser);
		
		return jsonInString;
	}
	
	//load JSON-String containing information about how often which browser was used into Java Objects
	private Browser[] loadJSON(String oldValue){
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	      
	    Gson gson = builder.create(); 
	    
	    Browser[] browser = gson.fromJson(oldValue, Browser[].class);
		
		return browser;
	}

}
