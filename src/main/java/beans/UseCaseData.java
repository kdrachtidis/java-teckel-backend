package beans;

public class UseCaseData{
	private UsabilityTestDetails UsabilityTestDetails;
	private String UseCaseAutoFlow;
	private UseCase[] UseCases;
	
	public UsabilityTestDetails getUsabilityTestDetails() {
		return UsabilityTestDetails;
	}
	public void setUsabilityTestDetails(UsabilityTestDetails usabilityTestDetails) {
		this.UsabilityTestDetails = usabilityTestDetails;
	}
	public String isUseCaseAutoFlow() {
		return UseCaseAutoFlow;
	}
	public void setUseCaseAutoFlow(String useCaseAutoFlow) {
		this.UseCaseAutoFlow = useCaseAutoFlow;
	}
	public UseCase[] getUseCases() {
		return UseCases;
	}
	public void setUseCases(UseCase[] useCases) {
		this.UseCases = useCases;
	}
	
	
}