package parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import beans.CurrentProjects;
import beans.Project;
import beans.UseCaseData;


public class ProjectParser {
	private String title;
	
	public String parse(String newProject, String currentProjects, String form) {
		String icon = "";
		switch (form) {
		case "On Site":
			icon = "decision";
			break;
		case "Remote - Guided":
			icon = "headset";
			break;
		case "Remote - Non-Guided":
			icon = "sound-off";
			break;
		default:
			break;
		}
		UseCaseData project = loadJSONnew(newProject);
		CurrentProjects allProjects = loadJSONall(currentProjects);
		
		Project[] newProjects = new Project[allProjects.getCurrentProjects().length + 1];
		for (int i = 0; i < allProjects.getCurrentProjects().length; i++){
			newProjects[i] = allProjects.getCurrentProjects()[i];
		}
		newProjects[newProjects.length-1] = createNew(project.getUsabilityTestDetails().getUsabilityTestTitle(),project.getUsabilityTestDetails().getUsabilityTestDescription(),"depreciated",icon);
		
		allProjects.setCurrentProjects(newProjects);
		
		this.title = project.getUsabilityTestDetails().getUsabilityTestTitle();
		
		return updateStorage(allProjects);
	}
	
	public String getTitle() {
		return title;
	}
	
	public String delete(String title, String currentProjects) {
		CurrentProjects allProjects = loadJSONall(currentProjects);
		
		Project[] newProjects = new Project[allProjects.getCurrentProjects().length - 1];
		int j = 0;
		for (int i = 0; i < allProjects.getCurrentProjects().length; i++){
			if(!allProjects.getCurrentProjects()[i].getTitle().equals(title)) {
				newProjects[j] = allProjects.getCurrentProjects()[i];
				j++;
			}
		}
		
		allProjects.setCurrentProjects(newProjects);
		return updateStorage(allProjects);
	}
	
	//return updated project String
	public String updateStorage(CurrentProjects newProjects) {
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting();
		
		Gson gson = builder.create();
		String jsonInString = gson.toJson(newProjects);
		
		return jsonInString;
	}
	
	//load existing projects String into Java Objects
	private CurrentProjects loadJSONall(String currentProjects){
		
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	      
	    Gson gson = builder.create(); 
	   
	    CurrentProjects projects = gson.fromJson(currentProjects, CurrentProjects.class);
		
		return projects;
	}
	
	//load new Project String into Java Object
	private UseCaseData loadJSONnew(String newProject){
		
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	    
	    Gson gson = builder.create(); 
	    
	    UseCaseData structure = gson.fromJson(newProject, UseCaseData.class);
	    
	    return structure;
	}
	
	//create a new project
	private Project createNew(String title, String description, String link, String icon){
		
		Project project = new Project();
		
		project.setTitle(title);
		project.setDescription(description);
		project.setLink(link);
		project.setIcon(icon);
		
		return project;
	}
}
