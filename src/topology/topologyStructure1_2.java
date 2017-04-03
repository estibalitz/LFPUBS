package topology;

import java.util.ArrayList;

public class topologyStructure1_2 {
	
	private String actionCluster;
	
	private ArrayList<String> unorderedActions = new ArrayList<String>();
	
	private ArrayList<Integer> origenActionsFrequency = new ArrayList<Integer>();
	
	private ArrayList<Integer> destinyActionsFrequency = new ArrayList<Integer>();
	
	private ArrayList<Double> originRelativeFrequency = new ArrayList<Double> ();
	
	private ArrayList<Double> destinyRelativeFrequency = new ArrayList<Double> ();
	
	private ArrayList<Double> balancedRelationFrequency = new ArrayList<Double> ();
	
	private ArrayList<Double> totalStrengthRelation = new ArrayList<Double> ();
	
	public topologyStructure1_2 (){
	}
	
	public topologyStructure1_2 (String name){
		this.actionCluster = name;
	}
	
	public void setActionCluster (String name){
		this.actionCluster = name;
	}
	
	public String getActionCluster (){
		return this.actionCluster;
	}
			
	public void setUnordereActions (ArrayList<String> unorderedActions){
		this.unorderedActions = unorderedActions;
	}
	
	public void setOriginActionsFrequency (ArrayList<Integer> originActionsFrequency){
		this.origenActionsFrequency = originActionsFrequency;
	}
	
	public void setDestinyActionsFrequency (ArrayList<Integer> destinyActionsFrequency){
		this.destinyActionsFrequency = destinyActionsFrequency;
	}
	
	public void setOriginRelativeFrequency (ArrayList<Double> originRelativeFrequency){
		this.originRelativeFrequency = originRelativeFrequency;
	}
	
	public void setDestinyRelativeFrequency (ArrayList<Double> destinyRelativeFrequency){
		this.destinyRelativeFrequency = destinyRelativeFrequency;
	}
	
	public void setBalancedRelationFrequency (ArrayList<Double> balancedRelationFrequency){
		this.balancedRelationFrequency = balancedRelationFrequency;
	}
	
	public ArrayList<String> getUnorderedActions (){
		return this.unorderedActions;
	}
	
	public ArrayList<Integer> getOriginActionsFrequency(){
		return this.origenActionsFrequency;
	}
	
	public ArrayList<Integer> getDestinyActionsFrequency(){
		return this.destinyActionsFrequency;
	}
	
	public ArrayList<Double> getOriginRelativeFrequency(){
		return this.originRelativeFrequency;
	}
	
	public ArrayList<Double> getDestinyRelativeFrequency(){
		return this.destinyRelativeFrequency;
	}
	
	public ArrayList<Double> getBalancedRelationFrequency(){
		return this.balancedRelationFrequency;
	}
	
	public void setTotalStrenghtRelation (ArrayList<Double> totalStrenghtRelation){
		this.totalStrengthRelation = totalStrenghtRelation;
	}
	
	public ArrayList<Double> getTotalStrenghtRelation (){
		return this.totalStrengthRelation;
	}
	
	public static topologyStructure1_2[] setTopologyStructure1_2 (topologyStructure1[] structure1){
		
		int numTotalActions = topologyStructure1.getNumGeneralTotalActions();
		
		topologyStructure1_2[] structure1_2 = new topologyStructure1_2[numTotalActions];
		
		for (int i = 0; i < numTotalActions; i++){
			structure1_2[i] = new topologyStructure1_2(structure1[i].getActionCluster());
			setPossibleUnorderedActions(structure1[i],structure1_2[i],structure1_2[i].getActionCluster());
			//structure1_2[i].setUnordereActions(setPossibleUnorderedActions(structure1[i]));
			/*System.out.println("action " + structure1_2[i].getActionCluster());
			for (int j = 0; j < structure1_2[i].getUnorderedActions().size(); j++){
				System.out.println("    possible unordered " + structure1_2[i].getUnorderedActions().get(j) + " frequencies " + structure1_2[i].getOriginActionsFrequency().get(j) + " / " + structure1_2[i].getDestinyActionsFrequency().get(j));
				System.out.println("       origin absolute: " + structure1_2[i].getOriginRelativeFrequency().get(j) + " destiny absolute: " + structure1_2[i].getDestinyRelativeFrequency().get(j) + " balanced: " + structure1_2[i].getBalancedRelationFrequency().get(j) + " total strength: " + structure1_2[i].getTotalStrenghtRelation().get(j));
			}*/
		}
		return structure1_2;
	}
	
		public static void setPossibleUnorderedActions(topologyStructure1 structure1, topologyStructure1_2 structure1_2, String actionCluster){
			
			ArrayList<String> unorderedActions = new ArrayList<String> ();
			ArrayList<Integer> originActionsFrequency = new ArrayList<Integer> ();
			ArrayList<Integer> destinyActionsFrequency = new ArrayList<Integer> ();
			ArrayList<Double> originRelativeFrequency = new ArrayList<Double> ();
			ArrayList<Double> destinyRelativeFrequency = new ArrayList<Double> ();
			ArrayList<Double> balancedRelationFrequency = new ArrayList<Double> ();
			ArrayList<Double> totalStrengthRelation = new ArrayList<Double> ();
						
			for (int i = 0; i < structure1.getPreviousActions().size(); i++){ //without taking into account the frequency of the previous actions
				if (structure1.getPreviousActions().get(i).hashCode() != actionCluster.hashCode()){
					int hashCodePreviousAction = structure1.getPreviousActions().get(i).hashCode();
					for (int j = 0; j < structure1.getNextActions().size(); j++){ //without taking into account the frequency of the next actions
						if (hashCodePreviousAction == structure1.getNextActions().get(j).hashCode()){
							double originValue = structure1.getNextActionsFrequency().get(j);
							double destinyValue = structure1.getPreviousActionsFrequency().get(i);
							double originRelative = originValue / structure1.getTotalNextActions();
							double destinyRelative = destinyValue / structure1.getTotalPreviousActions();
							double balancedValue = 1 - (Math.abs(originValue - destinyValue) / (originValue + destinyValue));
							double totalStrength = ((0.25 * originRelative) + (0.25 * destinyRelative) + (0.5 * balancedValue));
							
							if ((originRelative >= defineTopology.getminimumAbsoluteOriginFrequency()) && (destinyRelative >= defineTopology.getminimumAbsoluteDestinyFrequency()) && (balancedValue >= defineTopology.getminimumBalancedRelationFrequency())){
								//System.out.println("the relationship is strong enough to consider it like a cluster");
								unorderedActions.add(structure1.getNextActions().get(j));
								destinyActionsFrequency.add(structure1.getPreviousActionsFrequency().get(i));
								originActionsFrequency.add(structure1.getNextActionsFrequency().get(j));
								originRelativeFrequency.add(originRelative);
								destinyRelativeFrequency.add(destinyRelative);
								balancedRelationFrequency.add(balancedValue);
								totalStrengthRelation.add(totalStrength);
							}
							else{
								//System.out.println("the relationship is not strong enough to consider it like a cluster");
							}
						}
					}		
				}	
			}
			
			structure1_2.setUnordereActions(unorderedActions);
			structure1_2.setOriginActionsFrequency(originActionsFrequency);
			structure1_2.setDestinyActionsFrequency(destinyActionsFrequency);
			structure1_2.setOriginRelativeFrequency(originRelativeFrequency);
			structure1_2.setDestinyRelativeFrequency(destinyRelativeFrequency);
			structure1_2.setBalancedRelationFrequency(balancedRelationFrequency);
			structure1_2.setTotalStrenghtRelation(totalStrengthRelation);
			
		}
	
}

