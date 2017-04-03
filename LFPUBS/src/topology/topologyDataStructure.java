package topology;

import java.util.ArrayList;

public class topologyDataStructure {
	
	//	topology
	int numFrequentSequences;
	double minimumAbsoluteOriginFrequency = 0.25;
	double minimumAbsoluteDestinyFrequency = 0.25;
	double minimumBalancedRelationFrequency = 0.5;
	int numberPreviousNextActions = 4; //1,2,3 or 4
	int howDefineCluster = 2; // 0 --> without defining the number of cluster
							  // 1 --> with maximum number of actions
							  // 2 --> with average number of actions
	int considerPreviousClusters = 1; //0 --> not to consider
									  //1 --> to consider
	defineTopology topology = new defineTopology();
	ArrayList<topologyStructure2[]> topologyAllSequences = new ArrayList();
	
	public topologyDataStructure(){
		
	}
	
	public int getNumFrequentSequences (){
		return numFrequentSequences;
	}
	
	public void setNumFrequentSequences (int numFrequentSequences){
		this.numFrequentSequences = numFrequentSequences;
	}
	
	public double getMinimumAbsoluteOriginFrequency (){
		return minimumAbsoluteOriginFrequency;
	}
	
	public void setMinimumAbsoluteOriginFrequency (double minimumAbsoluteOriginFrequency){
		this.minimumAbsoluteOriginFrequency = minimumAbsoluteOriginFrequency;
	}
	
	public double getMinimumAbsoluteDestinyFrequency (){
		return minimumAbsoluteDestinyFrequency;
	}
	
	public void setMinimumAbsoluteDestinyFrequency (double minimumAbsoluteDestinyFrequency){
		this.minimumAbsoluteDestinyFrequency = minimumAbsoluteDestinyFrequency;
	}
	
	public double getMinimumBalancedRelationFrequency (){
		return minimumBalancedRelationFrequency;
	}
	
	public void setMinimumBalancedRelationFrequency (double minimumBalancedRelationFrequency){
		this.minimumBalancedRelationFrequency = minimumBalancedRelationFrequency;
	}
	
	public int getNumberPreviousNextActions (){
		return numberPreviousNextActions;
	}
	
	public void setNumberPreviousNextActions (int numberPreviousNextActions){
		this.numberPreviousNextActions = numberPreviousNextActions;
	}
	
	public int getHowDefineCluster (){
		return howDefineCluster;
	}
	
	public void setHowDefineCluster (int howDefineCluster){
		this.howDefineCluster = howDefineCluster;
	}
	
	public int getConsiderPreviousClusters (){
		return considerPreviousClusters;
	}
	
	public void setConsiderPreviousClusters (int considerPreviousClusters){
		this.considerPreviousClusters = considerPreviousClusters;
	}
	
	public defineTopology getTopology (){
		return topology;
	}
	
	public void setTopology (defineTopology topology){
		this.topology = topology;
	}
	
	public ArrayList<topologyStructure2[]> getTopologyAllSequences (){
		return topologyAllSequences;
	}
	
	public void setTopologyAllSequences (ArrayList<topologyStructure2[]> topologyAllSequences){
		this.topologyAllSequences = topologyAllSequences;
	}

}
