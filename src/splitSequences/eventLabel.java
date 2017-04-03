package splitSequences;

public class eventLabel {

	protected String device;
	protected String action;
	
	public eventLabel(String device, String action){
		this.device = device;
		this.action = action;
	}
	
	public String getDevice (){
		return this.device;
	}
	
	public String getAction (){
		return this.action;
	}
	
}
