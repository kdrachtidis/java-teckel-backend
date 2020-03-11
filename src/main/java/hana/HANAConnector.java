package hana;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class HANAConnector {
	public static String tableName = "\"ACME\".\"TECKEL\"";
	public String executeQuery(String query) {
		InitialContext ctx = null;
    	DataSource ds = null;
    	Connection conn = null;
    	ResultSet rs  = null;
    	String value = null;

    	//establish connection with a HANA database using a JDBC DataSource

    	try {
    		ctx = new InitialContext();

    	    ds = (DataSource) ctx.lookup("java:comp/env/default");
    	    conn = ds.getConnection();

    	    if (!conn.isClosed()) {

    	    	PreparedStatement stmt = conn.prepareStatement(query);
    	    	rs = stmt.executeQuery();
    	         
    	        while(rs.next()){
    	        	value = rs.getString("VALUE");
    		    }
    	    }
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {

    	    if (conn != null) {    
    	    	try {
    	    		conn.close();
    	        }
    	        catch (SQLException e) {
    	        	e.printStackTrace();
    	        }
    	    }
    	}
    	return value;
	}
}
