package exemplu.common.models;

public class Attribute {

	private String value;
	private boolean isChanged;
	private AttributeAction action;
	
	public Attribute() {
		value="";
		isChanged=false;
		action=AttributeAction.NONE;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isChanged() {
		return isChanged;
	}
	public void setChanged(boolean isChanged) {
		this.isChanged = isChanged;
	}
	public AttributeAction getAction() {
		return action;
	}
	public void setAction(AttributeAction action) {
		this.action = action;
	} 
	
	@Override
	public String toString() {
		return value;
	}
}
