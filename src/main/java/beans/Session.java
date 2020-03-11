package beans;

public class Session {
	private String name;
	private Overview[] overview;
	private Detail[] details;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Overview[] getOverview() {
		return overview;
	}
	public void setOverview(Overview[] overview) {
		this.overview = overview;
	}
	public Detail[] getDetails() {
		return details;
	}
	public void setDetails(Detail[] details) {
		this.details = details;
	}
}
