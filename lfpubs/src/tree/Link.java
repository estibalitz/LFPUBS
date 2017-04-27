package tree;
import java.util.ArrayList;

import conditions.simpleCompleteCondition;
public class Link {
	public Node previousNode;
	public double timeRelation;
	public int frequency; 
	private ArrayList<simpleCompleteCondition> disjunctionConditions = new ArrayList<simpleCompleteCondition>();

	public Link(Node previousNode, int timeRelation,int frequency){
		super();
		this.previousNode=previousNode;
		this.timeRelation=timeRelation;
		this.frequency=frequency;

	}
	public Link(){
		super();
	}

	public Node getPreviousNode(){
		return previousNode;
	}
	public void setPreviousNode(Node previousNode){
		this.previousNode=previousNode;
	}
	public int getFrequency(){
		return frequency;
	}
	public void setFrequency(int frequency){
		this.frequency=frequency;
	}
	public double getTimeRelation(){
		return timeRelation;
	}
	public void setTimeRelation(int timeRelation){
		this.timeRelation=timeRelation;
	}
	public void setDisjunctionConditions (ArrayList<simpleCompleteCondition> disjunctionConditions){
		this.disjunctionConditions = disjunctionConditions;
	}

	public ArrayList<simpleCompleteCondition> getDisjunctionConditions (){
		return this.disjunctionConditions;
	}
	
	/*public void setParticularDisjunctionConditions (ArrayList<simpleCompleteCondition> particularDisjunctionConditions){
		this.disjunctionConditions.add(particularDisjunctionConditions);
	}*/
	
}
