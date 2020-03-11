package services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import hana.HANAConnector;
import parsers.ActionParser;
import parsers.BrowserParser;
import parsers.CookieParser;
import parsers.ExperienceParser;
import parsers.GeneralParser;
import parsers.JavaParser;
import parsers.OsParser;
import parsers.ProjectParser;
import parsers.RoleParser;
import parsers.ScreenSizeParser;
import parsers.SessionParser;
import parsers.TimelineParser;
import parsers.UploadedFilesParser;

@Path("tests")
public class StoryInformation extends Application{
	
	private ProjectParser projectParser;
	private HANAConnector hanaConnector;
	
	public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(MultiPartFeature.class);
        return resources;
    }
	
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getFiles() {
    	hanaConnector = new HANAConnector();
    	String value = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'Stories'");
    	if (value != null) {
    		return Response.ok(value).header("Access-Control-Allow-Origin", "*").build();
    	}else {
    		return Response.noContent().header("Acces-Control-Allow-Origin", "*").build();
    	}
    }
    
    @POST  
    @Consumes(MediaType.MULTIPART_FORM_DATA)  
    public Response uploadFile(  
    		@FormDataParam("__xmlview0--fileUploader") InputStream uploadedInputStream0,
            @FormDataParam("__xmlview1--fileUploader") InputStream uploadedInputStream1,
            @FormDataParam("__xmlview2--fileUploader") InputStream uploadedInputStream2,
            @FormDataParam("__xmlview3--fileUploader") InputStream uploadedInputStream3,
            @FormDataParam("__xmlview4--fileUploader") InputStream uploadedInputStream4,
            @FormDataParam("__xmlview5--fileUploader") InputStream uploadedInputStream5,
            @FormDataParam("__xmlview6--fileUploader") InputStream uploadedInputStream6,
            @FormDataParam("__xmlview7--fileUploader") InputStream uploadedInputStream7,
            @FormDataParam("__xmlview8--fileUploader") InputStream uploadedInputStream8,
            @FormDataParam("__xmlview9--fileUploader") InputStream uploadedInputStream9,
            @FormDataParam("__xmlview10--fileUploader") InputStream uploadedInputStream10,
            @FormDataParam("__xmlview11--fileUploader") InputStream uploadedInputStream11,
            @FormDataParam("eventName") String name,
            @FormDataParam("eventLocation") String location,
            @FormDataParam("eventForm") String form) {  
    		
    		String newProject;
    		if(uploadedInputStream0 != null) {
        		newProject = readPost(uploadedInputStream0);
        	}else if (uploadedInputStream1 != null) {
        		newProject = readPost(uploadedInputStream1);
        	}else if (uploadedInputStream2 != null) {
        		newProject = readPost(uploadedInputStream2);
        	}else if (uploadedInputStream3 != null) {
        		newProject = readPost(uploadedInputStream3);
        	}else if (uploadedInputStream4 != null) {
        		newProject = readPost(uploadedInputStream4);
        	}else if (uploadedInputStream5 != null) {
        		newProject = readPost(uploadedInputStream5);
        	}else if (uploadedInputStream6 != null) {
        		newProject = readPost(uploadedInputStream6);
        	}else if (uploadedInputStream7 != null) {
        		newProject = readPost(uploadedInputStream7);
        	}else if (uploadedInputStream8 != null) {
        		newProject = readPost(uploadedInputStream8);
        	}else if (uploadedInputStream9 != null) {
        		newProject = readPost(uploadedInputStream9);
        	}else if (uploadedInputStream10 != null) {
        		newProject = readPost(uploadedInputStream10);
        	}else if (uploadedInputStream11 != null) {
        		newProject = readPost(uploadedInputStream11);
        	}else {
        		return Response.status(400).entity("no data found").header("Access-Control-Allow-Origin", "*").build();
        	}
    		
    	try {
    		hanaConnector = new HANAConnector();
    		
    	 	projectParser = new ProjectParser();
	    	String oldValue = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'Stories'");
	    	String newValue;
	    	if (oldValue != null) {
	    		newValue = projectParser.parse(newProject, oldValue, form);
	    		hanaConnector.executeQuery("UPDATE " + HANAConnector.tableName + " SET VALUE = '" + newValue + "' WHERE ID = 'Stories'");
	    	} else {
	    		newValue = projectParser.parse(newProject, "{\"currentProjects\": []}", form);
	    		hanaConnector.executeQuery("INSERT INTO " + HANAConnector.tableName + " VALUES ('Stories','" + newValue + "','','GLOBAL')");
	    	}
	    		    	
	    	String story = projectParser.getTitle();
	    	
	    	GeneralParser generalParser = new GeneralParser();
	    	newValue = generalParser.createStory(name,location);
	    	hanaConnector.executeQuery("INSERT INTO " + HANAConnector.tableName + " VALUES ('General','" + newValue + "','" + story + "','GLOBAL')");
	    	
	    	BrowserParser browserParser = new BrowserParser();
	    	newValue = browserParser.createStory();
	    	hanaConnector.executeQuery("INSERT INTO " + HANAConnector.tableName + " VALUES ('Browser','" + newValue + "','" + story + "','GLOBAL')");
	    	
	    	CookieParser cookieParser = new CookieParser();
	    	newValue = cookieParser.createStory();
	    	hanaConnector.executeQuery("INSERT INTO " + HANAConnector.tableName + " VALUES ('Cookie','" + newValue + "','" + story + "','GLOBAL')");
	    	
	    	SessionParser sessionParser = new SessionParser();
	    	newValue = sessionParser.createStory();
	    	hanaConnector.executeQuery("INSERT INTO " + HANAConnector.tableName + " VALUES ('Sessions','" + newValue + "','" + story + "','GLOBAL')");
	    	
	    	ExperienceParser experienceParser = new ExperienceParser();
	    	newValue = experienceParser.createStory();
	    	hanaConnector.executeQuery("INSERT INTO " + HANAConnector.tableName + " VALUES ('Experience','" + newValue + "','" + story + "','GLOBAL')");
	    	
	    	JavaParser javaParser = new JavaParser();
	    	newValue = javaParser.createStory();
	    	hanaConnector.executeQuery("INSERT INTO " + HANAConnector.tableName + " VALUES ('Java','" + newValue + "','" + story + "','GLOBAL')");
	    	
	    	OsParser osParser = new OsParser();
	    	newValue = osParser.createStory();
	    	hanaConnector.executeQuery("INSERT INTO " + HANAConnector.tableName + " VALUES ('OS','" + newValue + "','" + story + "','GLOBAL')");
	    	
	    	RoleParser roleParser = new RoleParser();
	    	newValue = roleParser.createStory();
	    	hanaConnector.executeQuery("INSERT INTO " + HANAConnector.tableName + " VALUES ('Role','" + newValue + "','" + story + "','GLOBAL')");
	    	
	    	ScreenSizeParser screenSizeParser = new ScreenSizeParser();
	    	newValue = screenSizeParser.createStory();
	    	hanaConnector.executeQuery("INSERT INTO " + HANAConnector.tableName + " VALUES ('ScreenSize','" + newValue + "','" + story + "','GLOBAL')");
	    	
	    	UploadedFilesParser uploadedFilesParser = new UploadedFilesParser();
	    	newValue = uploadedFilesParser.createStory();
	    	hanaConnector.executeQuery("INSERT INTO " + HANAConnector.tableName + " VALUES ('UploadedFiles','" + newValue + "','" + story + "','GLOBAL')");
	    	
	    	TimelineParser timelineParser = new TimelineParser();
	    	newValue = timelineParser.createStory();
	    	hanaConnector.executeQuery("INSERT INTO " + HANAConnector.tableName + " VALUES ('Timeline','" + newValue + "','" + story + "','GLOBAL')");
	    	
	    	ActionParser actionParser = new ActionParser();
	    	newValue = actionParser.createStory(newProject);
	    	hanaConnector.executeQuery("INSERT INTO " + HANAConnector.tableName + " VALUES ('Actions','" + newValue + "','" + story + "','GLOBAL')");
	    	
	    	hanaConnector.executeQuery("INSERT INTO " + HANAConnector.tableName + " VALUES ('UseCaseData','" + newProject + "','" + story + "','GLOBAL')");
	    
    	}catch(Exception e) {
    		return Response.status(400).entity(e.getMessage()).header("Access-Control-Allow-Origin", "*").build(); 
    	}
    	
    	return Response.status(200).entity("'"+newProject+"'").header("Access-Control-Allow-Origin", "*").build(); 
    }  
    
    @DELETE
    public Response deleteFile(@QueryParam("title") String title) {
    	
    	projectParser = new ProjectParser();
    	hanaConnector = new HANAConnector();
    	String oldValue = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'Stories'");
    	String newValue = projectParser.delete(title, oldValue);
    	
    	hanaConnector.executeQuery("UPDATE " + HANAConnector.tableName + " SET VALUE = '" + newValue + "' WHERE ID = 'Stories'");
    	
    	hanaConnector.executeQuery("DELETE FROM " + HANAConnector.tableName + " WHERE STORY = '" + title + "'");
    	
    	return Response.status(200).entity(newValue).header("Access-Control-Allow-Origin", "*").build();
    }
    
    @OPTIONS
    public Response getOptions() {
    	return Response.ok()
    		  .header("Access-Control-Allow-Origin","*")
    		  .header("Access-Control-Allow-Methods","GET, POST, DELETE, OPTIONS")
    		  .header("Access-Control-Allow-Headers","Origin, Content-Type, X-Auth-Token").build();
    }
    
    private String readPost(InputStream stream) {
    	String result = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining("\n"));
        return result;
    }
}
