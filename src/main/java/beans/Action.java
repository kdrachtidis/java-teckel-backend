package beans;

public class Action {
	private String ActionID;
	private String ActionTitle;
	private String ActionDescription;
	private ActionStep[] ActionSteps;
	
	public String getActionID() {
		return ActionID;
	}
	public void setActionID(String actionID) {
		ActionID = actionID;
	}
	public String getActionTitle() {
		return ActionTitle;
	}
	public void setActionTitle(String actionTitle) {
		ActionTitle = actionTitle;
	}
	
	public String getActionDescription() {
		return ActionDescription;
	}
	public void setActionDescription(String actionDescription) {
		ActionDescription = actionDescription;
	}
	public ActionStep[] getActionSteps() {
		return ActionSteps;
	}
	public void setActionSteps(ActionStep[] actionSteps) {
		ActionSteps = actionSteps;
	}
	
}
