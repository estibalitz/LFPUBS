package conditions;

public class simpleBasicConditionNominal extends simpleBasicCondition {
	
	public String value;
	
	public simpleBasicConditionNominal(){
		
	}
	
	public simpleBasicConditionNominal (String name, String symbol, String value){
		this.name = name;
		this.symbol = symbol;
		this.value = value;
		this._class = "nominal";
	}
	
	public simpleBasicConditionNominal (simpleBasicConditionNominal temp){
		this.name = temp.name;
		this.symbol = temp.symbol;
		this.value = temp.value;
		this._class = "nominal";
	}

}
