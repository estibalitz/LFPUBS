package tree;
import java.util.ArrayList;

import conditions.simpleBasicCondition;
public class Conditions {
	public ArrayList<simpleBasicCondition> basicConditions;
	public int priority;
	
	
	public Conditions(ArrayList<simpleBasicCondition> basicCondition, int priority){
		super();
		this.basicConditions=basicCondition;
		this.priority=priority;
		
	}
	public Conditions(Conditions conditions){
		this.basicConditions=conditions.basicConditions;
		this.priority=conditions.priority;
	}
	public Conditions(){
		super();
		basicConditions=new ArrayList<>();
	}
	public ArrayList<simpleBasicCondition> getBasicConditions() {
		return basicConditions;
	}
	public void addBasicConditions(simpleBasicCondition basicConditions) {
		if(basicConditions!=null)
			this.basicConditions.add(basicConditions);
	}
	public void setBasicConditions(ArrayList<simpleBasicCondition>basicCondition){
		this.basicConditions=basicConditions;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}

	
	
}
