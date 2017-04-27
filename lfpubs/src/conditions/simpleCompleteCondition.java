package conditions;

import java.util.ArrayList;

public class simpleCompleteCondition {
	
	public ArrayList<simpleBasicCondition> completeCondition;
	public String consequent;
	public int order;
	
	public simpleCompleteCondition (){
		
	}	
	
	public simpleCompleteCondition (ArrayList<simpleBasicCondition> conditions, String consequent, int order){
		this.completeCondition = conditions;
		this.consequent = consequent;
		this.order = order;
	}
	
	public simpleCompleteCondition (simpleCompleteCondition condition){
		this.completeCondition = condition.completeCondition;
		this.consequent = condition.consequent;
		this.order = condition.order;
	}
	
	public void setCompleteCondition (ArrayList<simpleBasicCondition> conditions){
		this.completeCondition = conditions;
	}
	
	public ArrayList<simpleBasicCondition> getCompleteCondition (){
		return this.completeCondition;
	}
	
	public void addSimpleBasicCondition(simpleBasicCondition simpleBasicCondition){
		if(completeCondition!=null){
			this.completeCondition.add(simpleBasicCondition);
		}
	}
	
	public void setConsequent(String consequent){
		this.consequent = consequent;
	}
	
	public String getConsequent (){
		return this.consequent;
	}
	
	public void setOrder(int order){
		this.order = order;
	}
	
	public int getOrder (){
		return this.order;
	}
	

}
