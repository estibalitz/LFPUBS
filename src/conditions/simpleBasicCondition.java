package conditions;

public abstract class simpleBasicCondition {
	
	public String name;
	public String symbol;
	public String _class;
	
	public simpleBasicCondition (){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}
	
	

}
