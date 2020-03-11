package beans;

public class UseCase {
	private String UseCaseID;
	private String UseCaseTitle;
	private String UseCaseDescription;
	private Action[] UseCaseActions;
	
	public String getUseCaseID() {
		return UseCaseID;
	}
	public void setUseCaseID(String useCaseID) {
		UseCaseID = useCaseID;
	}
	public String getUseCaseTitle() {
		return UseCaseTitle;
	}
	public void setUseCaseTitle(String useCaseTitle) {
		UseCaseTitle = useCaseTitle;
	}
	public String getUseCaseDescription() {
		return UseCaseDescription;
	}
	public void setUseCaseDescription(String useCaseDescriptions) {
		UseCaseDescription = useCaseDescriptions;
	}
	public Action[] getUseCaseActions() {
		return UseCaseActions;
	}
	public void setUseCaseActions(Action[] useCaseActions) {
		UseCaseActions = useCaseActions;
	}
	
}
