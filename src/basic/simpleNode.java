package basic;
import java.util.ArrayList;
import timeRelations.simpleTimeRelation;
import conditions.simpleCompleteCondition;
import topology.topologyStructure2;


public class simpleNode {
	
	private String node;
	
	private String typeNode;
	
	private ArrayList<String> componentsNode = new ArrayList<String> ();
		
	private ArrayList<String> previousActions = new ArrayList<String>();
	
	private ArrayList<Integer> previousActionsFrequency = new ArrayList<Integer>();
	
	private ArrayList<ArrayList<Integer>> indexSimpleEvents = new ArrayList<ArrayList<Integer>> ();
	
	private ArrayList<ArrayList<Integer>> indexSimpleEventsPrevious = new ArrayList<ArrayList<Integer>> ();
	
	private ArrayList<ArrayList<Integer>> indexSimpleEventsOfNext = new ArrayList<ArrayList<Integer>> ();
	
	private ArrayList<ArrayList<Integer>> indexSimpleEventsNextNext = new ArrayList<ArrayList<Integer>> ();
	
	private ArrayList<String> nextActions = new ArrayList<String>();
	
	private ArrayList<Integer> nextActionsFrequency = new ArrayList<Integer>();
	
	private ArrayList<ArrayList<simpleTimeRelation>> timeRelations = new ArrayList<ArrayList<simpleTimeRelation>>();
	
	private ArrayList<ArrayList<simpleCompleteCondition>> disjunctionConditions = new ArrayList<ArrayList<simpleCompleteCondition>>();
	
	public simpleNode (String name){
		this.node = name;
	}
	
	public simpleNode (topologyStructure2 structure2){
		this.node = structure2.getNode();
		this.typeNode = structure2.getTypeNode();
		this.componentsNode = structure2.getComponentsNode();
		this.previousActions = structure2.getPreviousActions();
		this.previousActionsFrequency = structure2.getPreviousActionsFrequency();
		this.indexSimpleEvents = structure2.getIndexSimpleEvents();
		this.indexSimpleEventsPrevious = structure2.getIndexSimpleEventsPrevious();
		this.indexSimpleEventsOfNext = structure2.getIndexSimpleEventsOfNext();
		this.indexSimpleEventsNextNext = structure2.getIndexSimpleEventsNextNext();
		this.nextActions = structure2.getNextActions();
		this.nextActionsFrequency = structure2.getNextActionsFrequency();
	}
	
	public simpleNode (simpleNode node){
		this.node = node.node;
		this.typeNode = node.typeNode;
		this.componentsNode = node.componentsNode;
		this.previousActions = node.previousActions;
		this.previousActionsFrequency = node.previousActionsFrequency;
		this.indexSimpleEvents = node.indexSimpleEvents;
		this.indexSimpleEventsPrevious = node.indexSimpleEventsPrevious;
		this.indexSimpleEventsOfNext = node.indexSimpleEventsOfNext;
		this.indexSimpleEventsNextNext = node.indexSimpleEventsNextNext;
		this.nextActions = node.nextActions;
		this.nextActionsFrequency = node.nextActionsFrequency;
		this.timeRelations = node.timeRelations;
		this.disjunctionConditions = node.disjunctionConditions;
	}
	
	public void setNode (String action){
		this.node = action;
	}
	
	public String getNode (){
		return this.node;
	}
	
	public void setTypeNode (String type){
		this.typeNode = type;
	}
	
	public String getTypeNode (){
		return this.typeNode;
	}
	
	public void setComponentsNode (ArrayList<String> componentsNode){
		this.componentsNode = componentsNode;
	}
	
	public ArrayList<String> getComponentsNode (){
		return this.componentsNode;
	}
	
	public void setPreviousActions (ArrayList<String> previousActions){
		this.previousActions = previousActions;
	}
	
	public void setParticularPreviousAction (String action, int frequency){
		this.previousActions.add(action);
		this.previousActionsFrequency.add(frequency);
	}
	
	public ArrayList<String> getPreviousActions (){
		return this.previousActions;
	}
	
	public void setPreviousActionsFrequency (ArrayList<Integer> previousActionsFrequency){
		this.previousActionsFrequency = previousActionsFrequency;
	}
	
	public void setPreviousActionsParticularFrequency(int index, int value){
		this.previousActionsFrequency.set(index, value);
	}
	
	public ArrayList<Integer> getPreviousActionsFrequency (){
		return this.previousActionsFrequency;
	}
	
	public void setTimeRelations (ArrayList<ArrayList<simpleTimeRelation>> timeRelations){
		this.timeRelations = timeRelations;
	}

	public ArrayList<ArrayList<simpleTimeRelation>> getTimeRelations (){
		return this.timeRelations;
	}
	
	public void setParticularTimeRelations (ArrayList<simpleTimeRelation> particularTimeRelations){
		this.timeRelations.add(particularTimeRelations);
	}
	
	public void setNextActions (ArrayList<String> nextActions){
		this.nextActions = nextActions;
	}
	
	public void setParticularNextAction (String action, int frequency){
		this.nextActions.add(action);
		this.nextActionsFrequency.add(frequency);
	}
	
	public ArrayList<String> getNextActions (){
		return this.nextActions;
	}
	
	public ArrayList<Integer> getNextActionsFrequency (){
		return this.nextActionsFrequency;
	}
	
	public void setIndexSimpleEvents (ArrayList<ArrayList<Integer>> indexSimpleEvents){
		this.indexSimpleEvents = indexSimpleEvents;
	}
	
	public ArrayList<ArrayList<Integer>> getIndexSimpleEvents (){
		return this.indexSimpleEvents;
	}
	
	public void setIndexSimpleEventsPrevious (ArrayList<ArrayList<Integer>> indexSimpleEventsPrevious){
		this.indexSimpleEventsPrevious = indexSimpleEventsPrevious;
	}
	
	public ArrayList<ArrayList<Integer>> getIndexSimpleEventsPrevious (){
		return this.indexSimpleEventsPrevious;
	}
	
	public void setIndexSimpleEventsOfNext (ArrayList<ArrayList<Integer>> indexSimpleEventsOfNext){
		this.indexSimpleEventsOfNext = indexSimpleEventsOfNext;
	}
	
	public ArrayList<ArrayList<Integer>> getIndexSimpleEventsOfNext (){
		return this.indexSimpleEventsOfNext;
	}
	
	public void setIndexSimpleEventsNextNext (ArrayList<ArrayList<Integer>> indexSimpleEventsNextNext){
		this.indexSimpleEventsNextNext = indexSimpleEventsNextNext;
	}
	
	public ArrayList<ArrayList<Integer>> getIndexSimpleEventsNextNext (){
		return this.indexSimpleEventsNextNext;
	}
	
	public void setDisjunctionConditions (ArrayList<ArrayList<simpleCompleteCondition>> disjunctionConditions){
		this.disjunctionConditions = disjunctionConditions;
	}

	public ArrayList<ArrayList<simpleCompleteCondition>> getDisjunctionConditions (){
		return this.disjunctionConditions;
	}
	
	public void setParticularDisjunctionConditions (ArrayList<simpleCompleteCondition> particularDisjunctionConditions){
		this.disjunctionConditions.add(particularDisjunctionConditions);
	}
	
}
