package services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import hana.HANAConnector;

@Path("java")
public class JavaInformation extends Application{
	
	private HANAConnector hanaConnector;
	
	public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();
        return resources;
    }
	
	@GET
    @Path("{story}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getFiles(@PathParam("story") String story) {
    	
    	hanaConnector = new HANAConnector();
    	String value = hanaConnector.executeQuery("SELECT * FROM " + HANAConnector.tableName + " WHERE ID = 'Java' AND STORY = '" + story + "'");
    	if (value != null) {
    		return Response.ok(value).header("Access-Control-Allow-Origin", "*").build();
    	}else {
    		return Response.noContent().header("Acces-Control-Allow-Origin", "*").build();
    	}
    	
    }
    
    @OPTIONS
    @Path("{story}")
    public Response getOptions() {
      return Response.ok()
    		  .header("Access-Control-Allow-Origin","*")
    		  .header("Access-Control-Allow-Methods","GET,OPTIONS")
    		  .header("Access-Control-Allow-Headers","Origin, Content-Type, X-Auth-Token").build();
    }
}
