package conditions;

public class simpleBasicConditionNumeric extends simpleBasicCondition {
	
	public double value;
	
	public simpleBasicConditionNumeric(){
		
	}
	
	public simpleBasicConditionNumeric (String name, String symbol, double value){
		this.name = name;
		this.symbol = symbol;
		this.value = value;
		this._class = "numeric";
	}
	
	public simpleBasicConditionNumeric (simpleBasicConditionNumeric temp){
		this.name = temp.name;
		this.symbol = temp.symbol;
		this.value = temp.value;
		this._class = "numeric";
	}

}
