package beans;

public class UploadedFile {
	private String sessionId;
	private String sessionName;
	private String sessionRole;
	private String sessionExperience;
	private String uploadedOn;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public String getSessionRole() {
		return sessionRole;
	}
	public void setSessionRole(String sessionRole) {
		this.sessionRole = sessionRole;
	}
	public String getSessionExperience() {
		return sessionExperience;
	}
	public void setSessionExperience(String sessionExperience) {
		this.sessionExperience = sessionExperience;
	}
	public String getUploadedOn() {
		return uploadedOn;
	}
	public void setUploadedOn(String uploadedOn) {
		this.uploadedOn = uploadedOn;
	}
	
}
