package parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.Detail;
import beans.Overview;
import beans.Session;

public class SessionParser {
	public String parse(String oldValue, String name, String[][] file) {
		
		Session[] sessions = loadJSON(oldValue);
		
		Session newSession = new Session();
		
		//parse overview
		String tmpUC = "";
		int tmpStart = 0;
		int tmpStop = 0;
		int tmpDiff = 0;
		int tmpCount = 0;
		
		//read out number of use cases
		int numberUC = Integer.parseInt(file[file.length-1][5]) + 1;
		
		if(numberUC > 0){
			Overview[] overview = new Overview[numberUC];
			Overview uc = new Overview();
			
			//find spent time for each use case in test.csv and save information in java objects
			for(int i = 0; i<file.length; i++){
				if (file[i][4].contains("Begin Use Case")){
					tmpUC = (file[i][4]).substring(6);
					tmpStart = Integer.parseInt(file[i][2]);
				}
				if (file[i][4].contains("Complete Use Case") && file[i][4].contains(tmpUC)){
					tmpStop = Integer.parseInt(file[i][2]);
					tmpDiff = tmpStop - tmpStart;
					
					uc.setUseCase(tmpUC);
					uc.setDuration(tmpDiff);
					overview[tmpCount] = new Overview(uc);
					tmpCount++;
					
				}
			}
			
			newSession.setOverview(overview);
			
			//parse details
			tmpUC = "";
			int tmpUCStart = 0;
			int tmpUCStop = 0;
			int tmpAStart = 0;
			int tmpAStop = 0;
			tmpDiff = 0;
			tmpCount = 0;
			
			Detail[] details = new Detail[numberUC];
			Detail detail = new Detail();
			detail.setToNull();
			
			int maxActions = 1;
			
			//find spent time for each use case and action in test.csv and save information in java objects
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
					switch (file[i][4]) {
						case "Complete Action 1":
							detail.setTimeForAction1(tmpDiff);
							break;
						case "Complete Action 2":
							detail.setTimeForAction2(tmpDiff);
							maxActions = Math.max(maxActions, 2);
							break;
						case "Complete Action 3":
							detail.setTimeForAction3(tmpDiff);
							maxActions = Math.max(maxActions, 3);
							break;
						case "Complete Action 4":
							detail.setTimeForAction4(tmpDiff);
							maxActions = Math.max(maxActions, 4);
							break;
						case "Complete Action 5":
							detail.setTimeForAction5(tmpDiff);
							maxActions = Math.max(maxActions, 5);
							break;
						case "Complete Action 6":
							detail.setTimeForAction6(tmpDiff);
							maxActions = Math.max(maxActions, 6);
							break;
						case "Complete Action 7":
							detail.setTimeForAction7(tmpDiff);
							maxActions = Math.max(maxActions, 7);
							break;
						case "Complete Action 8":
							detail.setTimeForAction8(tmpDiff);
							maxActions = Math.max(maxActions, 8);
							break;
						case "Complete Action 9":
							detail.setTimeForAction9(tmpDiff);
							maxActions = Math.max(maxActions, 9);
							break;
						case "Complete Action 10":
							detail.setTimeForAction10(tmpDiff);
							maxActions = Math.max(maxActions, 10);
							break;
						default:
							break;
					}
				}
				if (file[i][4].contains("Complete Use Case") && file[i][4].contains(tmpUC)){
					tmpUCStop = Integer.parseInt(file[i][2]);
					tmpDiff = tmpUCStop - tmpUCStart;
					
					detail.setLabel(tmpUC);
					detail.setTotalProcessingTime(tmpDiff);
					details[tmpCount] = new Detail(detail);
					tmpCount++;
					detail.setToNull();
				}
				
			}
			
			newSession.setDetails(details);
			newSession.setName(name);
			
			Session[] newSessions = new Session[sessions.length + 1];
			for(int j=0; j < sessions.length; j++) {
				newSessions[j] = sessions[j];
			}
			newSessions[newSessions.length - 1] = newSession;
			
			return storeJSON(newSessions);
			
		}else{
			return oldValue;
		}
		
	}
	
	//Create empty general information json
	public String createStory() {
		
		return "[]";
	}
	
	//Write session information to JSON file
	public String storeJSON(Session[] sessions) {
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		String jsonInString = gson.toJson(sessions);
		
		return jsonInString;
	}
	
	//load existing JSON file containing information about how often which session appeared into Java Objects
	private Session[] loadJSON(String jsonString){
		
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	      
	    Gson gson = builder.create(); 
	    
	    Session[] sessions = gson.fromJson(jsonString, Session[].class);
		
		return sessions;
	}
}
