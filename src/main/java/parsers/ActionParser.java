package parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.ActionDetails;
import beans.Single;
import beans.UseCaseData;

public class ActionParser {
	public String parse(String oldValue, String name, String[][] file) {
		
		ActionDetails[] actions = loadJSON(oldValue);
		
		//read out number of use cases
		int numberUC = Integer.parseInt(file[file.length-1][5]) + 1;
		
		if(numberUC > 0){
					
			//parse
			String tmpUC = "";
			int tmpUCStart = 0;
			int tmpUCStop = 0;
			int tmpAStart = 0;
			int tmpAStop = 0;
			int tmpDiff = 0;
			int tmpUCNumber = 0;
			int tmpANumber = 0;
			
			//find spent time for each use case and action in test.csv and save information in java object
			for(int i = 0; i<file.length; i++){
				if (file[i][4].contains("Begin Use Case")){
					tmpUC = (file[i][4]).substring(6);
					tmpUCStart = Integer.parseInt(file[i][2]);
				}
				if (file[i][4].contains("Begin Action")) {
					tmpAStart = Integer.parseInt(file[i][2]);
				}
				if (file[i][4].contains("Complete Action")) {
					tmpAStop = Integer.parseInt(file[i][2]);
					tmpDiff = tmpAStop - tmpAStart;
					
					actions[tmpUCNumber].getSingle()[tmpANumber].setData(actions[tmpUCNumber].getSingle()[tmpANumber].getData() + name + "," + tmpDiff + "\n");
					
					tmpANumber++;
				}
				if (file[i][4].contains("Complete Use Case") && file[i][4].contains(tmpUC)){
					tmpUCStop = Integer.parseInt(file[i][2]);
					tmpDiff = tmpUCStop - tmpUCStart;
					
					actions[tmpUCNumber].setAll(actions[tmpUCNumber].getAll() + name + "," + tmpDiff + "\n");
					
					tmpUCNumber++;
					tmpANumber = 0;
				}
				
			}
			
			return storeJSON(actions);
			
		}else{
			return oldValue;
		}
		
	}
	
	//Create empty action json
	public String createStory(String newProject) {
		String emptyCSV = "session,duration\n";
		
		UseCaseData project = loadJSONnew(newProject);
		
		ActionDetails[] actions = new ActionDetails[project.getUseCases().length];
		Single[] singles;
		
		for(int i=0; i < project.getUseCases().length; i++) {
			actions[i] = new ActionDetails();
			actions[i].setName(project.getUseCases()[i].getUseCaseTitle());
			actions[i].setAll(emptyCSV);
			singles = new Single[project.getUseCases()[i].getUseCaseActions().length];
			for (int j=0; j < project.getUseCases()[i].getUseCaseActions().length; j++) {
				singles[j] = new Single();
				singles[j].setName(project.getUseCases()[i].getUseCaseActions()[j].getActionTitle());
				singles[j].setData(emptyCSV);
			}
			actions[i].setSingle(singles);
		}
		
		return storeJSON(actions);
	}
	
	//Write session information to JSON file
	public String storeJSON(ActionDetails[] actions) {
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		String jsonInString = gson.toJson(actions);
		
		return jsonInString;
	}
	
	//load existing JSON file containing information about how often which session appeared into Java Objects
	private ActionDetails[] loadJSON(String jsonString){
		
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	      
	    Gson gson = builder.create(); 
	    
	    ActionDetails[] actions = gson.fromJson(jsonString, ActionDetails[].class);
		
		return actions;
	}
	
	//load new Project String into Java Object
	private UseCaseData loadJSONnew(String newProject){
		
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	    
	    Gson gson = builder.create(); 
	    
	    UseCaseData structure = gson.fromJson(newProject, UseCaseData.class);
	    
	    return structure;
	}
}
