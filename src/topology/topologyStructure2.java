package topology;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

public class topologyStructure2 {
	
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
	
	private ArrayList<String> unorderedActions = new ArrayList<String>();
		
	private ArrayList<Integer> origenActionsFrequency = new ArrayList<Integer>();
	
	private ArrayList<Integer> destinyActionsFrequency = new ArrayList<Integer>();
	
	private ArrayList<Double> originRelativeFrequency = new ArrayList<Double> ();
	
	private ArrayList<Double> destinyRelativeFrequency = new ArrayList<Double> ();
	
	private ArrayList<Double> balancedRelationFrequency = new ArrayList<Double> ();
	
	private ArrayList<Double> totalStrengthRelation = new ArrayList<Double> ();
	
	public topologyStructure2 (){
	}
	
	public topologyStructure2 (String name){
		this.node = name;
	}
	
	public topologyStructure2 (topologyStructure2 structure2){
		this.node = structure2.node;
		this.typeNode = structure2.typeNode;
		this.componentsNode = structure2.componentsNode;
		this.previousActions = structure2.previousActions;
		this.previousActionsFrequency = structure2.previousActionsFrequency;
		this.indexSimpleEvents = structure2.indexSimpleEvents;
		this.indexSimpleEventsPrevious = structure2.indexSimpleEventsPrevious;
		this.indexSimpleEventsOfNext = structure2.indexSimpleEventsOfNext;
		this.indexSimpleEventsNextNext = structure2.indexSimpleEventsNextNext;
		this.nextActions = structure2.nextActions;
		this.nextActionsFrequency = structure2.nextActionsFrequency;
		this.unorderedActions = structure2.unorderedActions;
		this.origenActionsFrequency = structure2.origenActionsFrequency;
		this.destinyActionsFrequency = structure2.destinyActionsFrequency;
		this.originRelativeFrequency = structure2.originRelativeFrequency;
		this.destinyRelativeFrequency = structure2.destinyRelativeFrequency;
		this.balancedRelationFrequency = structure2.balancedRelationFrequency;
		this.totalStrengthRelation = structure2.totalStrengthRelation;		
	}
	
	/*public topologyStructure2 (String name, String type, ArrayList<String> components){
		this.node = name;
		this.typeNode = type;
		this.componentsNode = components;
	}*/
	
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
	
	public void setNextActionsFrequency (ArrayList<Integer> nextActionsFrequency){
		this.nextActionsFrequency = nextActionsFrequency;
	}
	
	public void setNextActionsParticularFrequency(int index, int value){
		this.nextActionsFrequency.set(index, value);
	}
	
	public ArrayList<Integer> getNextActionsFrequency (){
		return this.nextActionsFrequency;
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
	
	public void setUnordereActions (ArrayList<String> unorderedActions){
		this.unorderedActions = unorderedActions;
	}
	
	public ArrayList<String> getUnorderedActions (){
		return this.unorderedActions;
	}
	
	public void setOriginActionsFrequency (ArrayList<Integer> originActionsFrequency){
		this.origenActionsFrequency = originActionsFrequency;
	}
	
	public ArrayList<Integer> getOriginActionsFrequency(){
		return this.origenActionsFrequency;
	}
	
	public void setDestinyActionsFrequency (ArrayList<Integer> destinyActionsFrequency){
		this.destinyActionsFrequency = destinyActionsFrequency;
	}
	
	public ArrayList<Integer> getDestinyActionsFrequency(){
		return this.destinyActionsFrequency;
	}
	
	public void setOriginRelativeFrequency (ArrayList<Double> originRelativeFrequency){
		this.originRelativeFrequency = originRelativeFrequency;
	}
	
	public ArrayList<Double> getOriginRelativeFrequency(){
		return this.originRelativeFrequency;
	}
	
	public void setDestinyRelativeFrequency (ArrayList<Double> destinyRelativeFrequency){
		this.destinyRelativeFrequency = destinyRelativeFrequency;
	}
	
	public ArrayList<Double> getDestinyRelativeFrequency(){
		return this.destinyRelativeFrequency;
	}
	
	public void setBalancedRelationFrequency (ArrayList<Double> balancedRelationFrequency){
		this.balancedRelationFrequency = balancedRelationFrequency;
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
	
	public static topologyStructure2[] setTopologyStructure2 (topologyStructure1[] structure1, topologyStructure1_2[] structure1_2){
		
		int numTotalActions = topologyStructure1.getNumGeneralTotalActions();
		
		topologyStructure2 [] structure2Initial = new topologyStructure2[numTotalActions];
		topologyStructure2 [] structure2;
		
		structure2Initial = setTopologyStructure2Initial (numTotalActions,structure1, structure1_2);
		
		structure2 = setTopologyStructure2 (structure2Initial);
				
		return structure2;
		
	}
	
		private static topologyStructure2[] setTopologyStructure2Initial (int numTotalActions, topologyStructure1[] structure1, topologyStructure1_2[] structure1_2){
			
			topologyStructure2[] structure2_Initial = new topologyStructure2[numTotalActions];
			
			for (int i = 0; i < numTotalActions; i++){
				structure2_Initial[i] = new topologyStructure2 (structure1[i].getActionCluster());
				structure2_Initial[i].setTypeNode("simple");
				structure2_Initial[i].setPreviousActions(structure1[i].getPreviousActions());
				structure2_Initial[i].setPreviousActionsFrequency(structure1[i].getPreviousActionsFrequency());
				structure2_Initial[i].setIndexSimpleEvents(structure1[i].getIndexFirstSimpleEvent());
				structure2_Initial[i].setIndexSimpleEventsPrevious(structure1[i].getIndexFirstSimpleEventPrevious());
				structure2_Initial[i].setNextActions(structure1[i].getNextActions());
				structure2_Initial[i].setNextActionsFrequency(structure1[i].getNextActionsFrequency());
				structure2_Initial[i].setIndexSimpleEventsOfNext(structure1[i].getIndexFirstSimpleEventOfNext());
				structure2_Initial[i].setIndexSimpleEventsNextNext(structure1[i].getIndexFirstSimpleEventNextNext());
				structure2_Initial[i].setUnordereActions(structure1_2[i].getUnorderedActions());
				structure2_Initial[i].setOriginActionsFrequency(structure1_2[i].getOriginActionsFrequency());
				structure2_Initial[i].setDestinyActionsFrequency(structure1_2[i].getDestinyActionsFrequency());
				structure2_Initial[i].setOriginRelativeFrequency(structure1_2[i].getOriginRelativeFrequency());
				structure2_Initial[i].setDestinyRelativeFrequency(structure1_2[i].getDestinyRelativeFrequency());
				structure2_Initial[i].setBalancedRelationFrequency(structure1_2[i].getBalancedRelationFrequency());
				structure2_Initial[i].setTotalStrenghtRelation(structure1_2[i].getTotalStrenghtRelation());			
			}
			//visualizeTopologyStructure2(structure2_Initial);
			
			return structure2_Initial;
			
		}
	
		public static topologyStructure2[] setTopologyStructure2 (topologyStructure2[] structure2_Initial){
			
			ArrayList<String> clusterNodes = new ArrayList<String> ();
			boolean existsMoreClusters;
			topologyStructure2[] tempStructure2 = new topologyStructure2[structure2_Initial.length];
			int clusterNum = -1;
			
			for (int i = 0; i < structure2_Initial.length; i++){
				tempStructure2[i] = new topologyStructure2(structure2_Initial[i]);
			}
			
			clusterNodes = existsCluster (structure2_Initial);
			if (clusterNodes.size() != 0){
				existsMoreClusters = true;
			}
			else existsMoreClusters = false;
			
			while (existsMoreClusters){
			    clusterNum++;
			    /*System.out.println("=============================================");
			    System.out.println("           Step : " + clusterNum);
			    System.out.println("=============================================");
			    System.out.println();*/
				tempStructure2 = updateStructure2(tempStructure2,clusterNodes,clusterNum);
				updateUnorderedActionsStructure2(tempStructure2);
				//visualizeTopologyStructure2(tempStructure2);
				clusterNodes = existsCluster (tempStructure2);
				if (clusterNodes.size() != 0){
					existsMoreClusters = true;
				}
				else existsMoreClusters = false;
			}
									
			return tempStructure2;
		}
		
			public static ArrayList<String> existsCluster (topologyStructure2[] structure2){
				
				ArrayList<String> clusterNodes = new ArrayList<String> ();
				double maximumStrength = 0;
				
				for (int i = 0; i < structure2.length; i++){
					if (structure2[i].getUnorderedActions().size() != 0){
						for (int j = 0; j < structure2[i].getUnorderedActions().size(); j++){
							if (structure2[i].getTotalStrenghtRelation().get(j) > maximumStrength){
								clusterNodes.clear();
								clusterNodes.add(structure2[i].getNode());
								clusterNodes.add(structure2[i].getUnorderedActions().get(j));
								maximumStrength = structure2[i].getTotalStrenghtRelation().get(j);
							}
						}
					}
				}
				
				return clusterNodes;
			}
		
			public static topologyStructure2[] updateStructure2 (topologyStructure2[] structure2, ArrayList<String> clusterNodes, int clusterNum){
				
				topologyStructure2[] newTopologyStructure2 = new topologyStructure2[structure2.length - 1];
				int clusterCreated = 0;
				ArrayList<String> tempClusterNodes = new ArrayList<String> ();
				
				int indexNewNode = -1;
				for (int h = 0; h < clusterNodes.size(); h++){
				}
				for (int i = 0; i < structure2.length; i++){
					int found = findNodeInClusterNodes (structure2[i].getNode(),clusterNodes);
					if (found != -1){
						if (clusterCreated == 0){
							//create a new cluster and create the relations
							indexNewNode++;
							newTopologyStructure2[indexNewNode] = new topologyStructure2("cluster_" + clusterNum);
							newTopologyStructure2[indexNewNode].setTypeNode("cluster");
							tempClusterNodes.clear();
							for (int j = 0; j < clusterNodes.size(); j++){
								findComponentsOfNode(structure2,clusterNodes.get(j),tempClusterNodes);
							}
							newTopologyStructure2[indexNewNode].setComponentsNode(tempClusterNodes);
							setPreviousNextActionsNewClusterStructure2(newTopologyStructure2[indexNewNode],structure2[i],clusterNodes);
							clusterCreated = 1;
						}
						else{
							int positionClusterCreated = findNodeInCreatedNodes (newTopologyStructure2,"cluster_" + clusterNum,indexNewNode);
							addPreviousNextActionsNewClusterStructure2(newTopologyStructure2[positionClusterCreated],structure2[i],clusterNodes);
						}
					}
					else{
						//copy the node but change the relations, considering the new cluster
						indexNewNode++;
						newTopologyStructure2[indexNewNode] = new topologyStructure2(structure2[i].getNode());
						newTopologyStructure2[indexNewNode].setTypeNode(structure2[i].getTypeNode());
						if (structure2[i].getTypeNode().compareTo("cluster") == 0){
							newTopologyStructure2[indexNewNode].setComponentsNode(structure2[i].getComponentsNode());
						}
						setPreviousNextActionsOldActionStructure2(newTopologyStructure2[indexNewNode],structure2[i],clusterNodes,clusterNum);
					}
					
				}
				
				return newTopologyStructure2;
				
			}
			
				public static int findNodeInClusterNodes (String node, ArrayList<String> clusterNodes){
					int i = 0;
					
					while (i < clusterNodes.size()){
						if (node.hashCode() == clusterNodes.get(i).hashCode()){
							return i;
						}
						i++;
					}
					
					return -1;
				}
			
				public static void setPreviousNextActionsNewClusterStructure2 (topologyStructure2 newStructure, topologyStructure2 oldStructure, ArrayList<String> clusterNodes){
					
					ArrayList<String> myArrPrevious = new ArrayList<String>();
					ArrayList<String> myArrNext = new ArrayList<String>();
					ArrayList<ArrayList<Integer>> myArrIndexSimpleEvents = new ArrayList<ArrayList<Integer>> ();
					ArrayList<ArrayList<Integer>> myArrIndexSimpleEventsPrevious = new ArrayList<ArrayList<Integer>> ();
					ArrayList<ArrayList<Integer>> myArrIndexSimpleEventsOfNext = new ArrayList<ArrayList<Integer>> ();
					ArrayList<ArrayList<Integer>> myArrIndexSimpleEventsNextNext = new ArrayList<ArrayList<Integer>> ();
					ArrayList<Integer> myArrPreviousCount = new ArrayList<Integer>();
					ArrayList<Integer> myArrNextCount = new ArrayList<Integer>();
					
					//previous
					for (int i = 0; i < oldStructure.getPreviousActions().size(); i++){
						int isNodeOfCluster = findNodeInClusterNodes (oldStructure.getPreviousActions().get(i),clusterNodes);
						if (isNodeOfCluster == -1){ //it is not part of the cluster
							myArrPrevious.add(oldStructure.getPreviousActions().get(i));
							myArrPreviousCount.add(oldStructure.getPreviousActionsFrequency().get(i));
							myArrIndexSimpleEvents.add(oldStructure.getIndexSimpleEvents().get(i));
							myArrIndexSimpleEventsPrevious.add(oldStructure.getIndexSimpleEventsPrevious().get(i));
						}	
					}
					
					//next
					for (int i = 0; i < oldStructure.getNextActions().size(); i++){
						int isNodeOfCluster = findNodeInClusterNodes (oldStructure.getNextActions().get(i),clusterNodes);
						if (isNodeOfCluster == -1){ //it is not part of the cluster
							myArrNext.add(oldStructure.getNextActions().get(i));
							myArrNextCount.add(oldStructure.getNextActionsFrequency().get(i));
							myArrIndexSimpleEventsOfNext.add(oldStructure.getIndexSimpleEventsOfNext().get(i));
							myArrIndexSimpleEventsNextNext.add(oldStructure.getIndexSimpleEventsNextNext().get(i));
						}	
					}
					
					newStructure.setPreviousActions(myArrPrevious);
					newStructure.setPreviousActionsFrequency(myArrPreviousCount);
					newStructure.setIndexSimpleEvents(myArrIndexSimpleEvents);
					newStructure.setIndexSimpleEventsPrevious(myArrIndexSimpleEventsPrevious);
					newStructure.setNextActions(myArrNext);
					newStructure.setNextActionsFrequency(myArrNextCount);
					newStructure.setIndexSimpleEventsOfNext(myArrIndexSimpleEventsOfNext);
					newStructure.setIndexSimpleEventsNextNext(myArrIndexSimpleEventsNextNext);
				}
				
				public static void addPreviousNextActionsNewClusterStructure2 (topologyStructure2 newStructure, topologyStructure2 oldStructure, ArrayList<String> clusterNodes){
					
					//previous
					for (int i = 0; i < oldStructure.getPreviousActions().size(); i++){
						int position = getPositionAction (oldStructure.getPreviousActions().get(i),newStructure.getPreviousActions());
						if (position != -1){
							int newValue = newStructure.getPreviousActionsFrequency().get(position) + oldStructure.getPreviousActionsFrequency().get(i);
							for (int j = 0; j < oldStructure.getIndexSimpleEvents().get(i).size(); j++){
								newStructure.getIndexSimpleEvents().get(position).add(oldStructure.getIndexSimpleEvents().get(i).get(j));
								newStructure.getIndexSimpleEventsPrevious().get(position).add(oldStructure.getIndexSimpleEventsPrevious().get(i).get(j));
							}
							newStructure.setPreviousActionsParticularFrequency(position, newValue);
						}
						else{
							int isNodeOfCluster = findNodeInClusterNodes (oldStructure.getPreviousActions().get(i),clusterNodes);
							if (isNodeOfCluster == -1){//it is not part of the cluster
								newStructure.setParticularPreviousAction(oldStructure.getPreviousActions().get(i), oldStructure.getPreviousActionsFrequency().get(i));
								newStructure.getIndexSimpleEvents().add(oldStructure.getIndexSimpleEvents().get(i));
								newStructure.getIndexSimpleEventsPrevious().add(oldStructure.getIndexSimpleEventsPrevious().get(i));
							}

							
						}
					}
					
					//next
					for (int i = 0; i < oldStructure.getNextActions().size(); i++){
						int position = getPositionAction (oldStructure.getNextActions().get(i),newStructure.getNextActions());
						if (position != -1){
							int newValue = newStructure.getNextActionsFrequency().get(position) + oldStructure.getNextActionsFrequency().get(i);
							for (int j = 0; j < oldStructure.getIndexSimpleEventsOfNext().get(i).size(); j++){
								newStructure.getIndexSimpleEventsOfNext().get(position).add(oldStructure.getIndexSimpleEventsOfNext().get(i).get(j));
								newStructure.getIndexSimpleEventsNextNext().get(position).add(oldStructure.getIndexSimpleEventsNextNext().get(i).get(j));
							}
							newStructure.setNextActionsParticularFrequency(position, newValue);
						}
						else{
							int isNodeOfCluster = findNodeInClusterNodes (oldStructure.getNextActions().get(i),clusterNodes);
							if (isNodeOfCluster == -1){//it is not part of the cluster
								newStructure.setParticularNextAction(oldStructure.getNextActions().get(i), oldStructure.getNextActionsFrequency().get(i));
								newStructure.getIndexSimpleEventsOfNext().add(oldStructure.getIndexSimpleEventsOfNext().get(i));
								newStructure.getIndexSimpleEventsNextNext().add(oldStructure.getIndexSimpleEventsNextNext().get(i));
							}						
						}
					}
					
				}
				
					public static int getPositionAction(String str, ArrayList<String> array){
						
						for (int i = 0; i < array.size(); i++){
							if (str.compareTo(array.get(i))==0){
								return i;
							}
						}
						return -1;
						
					}
				
				public static void setPreviousNextActionsOldActionStructure2(topologyStructure2 newStructure, topologyStructure2 oldStructure, ArrayList<String> clusterNodes, int clusterNum){
					
					ArrayList<String> myArrPrevious = new ArrayList<String>();
					ArrayList<String> myArrNext = new ArrayList<String>();
					ArrayList<ArrayList<Integer>> myArrIndexSimpleEvents = new ArrayList<ArrayList<Integer>> ();
					ArrayList<ArrayList<Integer>> myArrIndexSimpleEventsPrevious = new ArrayList<ArrayList<Integer>> ();
					ArrayList<Integer> myArrPreviousCount = new ArrayList<Integer>();
					ArrayList<Integer> myArrNextCount = new ArrayList<Integer>();
					ArrayList<ArrayList<Integer>> myArrIndexSimpleEventsOfNext = new ArrayList<ArrayList<Integer>> ();
					ArrayList<ArrayList<Integer>> myArrIndexSimpleEventsNextNext = new ArrayList<ArrayList<Integer>> ();
					
					//previous
					int previousClusterCreated = 0;
					for (int i = 0; i < oldStructure.getPreviousActions().size(); i++){
						int isNodeOfCluster = findNodeInClusterNodes (oldStructure.getPreviousActions().get(i),clusterNodes);
						if (isNodeOfCluster == -1){ //it is not part of the cluster
							myArrPrevious.add(oldStructure.getPreviousActions().get(i));
							myArrPreviousCount.add(oldStructure.getPreviousActionsFrequency().get(i));
							myArrIndexSimpleEvents.add(oldStructure.getIndexSimpleEvents().get(i));
							myArrIndexSimpleEventsPrevious.add(oldStructure.getIndexSimpleEventsPrevious().get(i));
						}
						else{
							if (previousClusterCreated == 0){
								myArrPrevious.add("cluster_" + clusterNum);
								myArrPreviousCount.add(oldStructure.getPreviousActionsFrequency().get(i));
								previousClusterCreated = 1;
								myArrIndexSimpleEvents.add(oldStructure.getIndexSimpleEvents().get(i));
								myArrIndexSimpleEventsPrevious.add(oldStructure.getIndexSimpleEventsPrevious().get(i));
							}
							else{
								int clusterPosition = getPositionAction ("cluster_" + clusterNum, myArrPrevious);
								if (clusterPosition != -1){
									int newValue = myArrPreviousCount.get(clusterPosition) + oldStructure.getPreviousActionsFrequency().get(i);
									myArrPreviousCount.set(clusterPosition, newValue);
									for (int j = 0; j < oldStructure.getIndexSimpleEvents().get(i).size(); j++){
										myArrIndexSimpleEvents.get(clusterPosition).add(oldStructure.getIndexSimpleEvents().get(i).get(j));
										myArrIndexSimpleEventsPrevious.get(clusterPosition).add(oldStructure.getIndexSimpleEventsPrevious().get(i).get(j));
									}
								}
							}
						}

					}
					
					//next
					int nextClusterCreated = 0;
					for (int i = 0; i < oldStructure.getNextActions().size(); i++){
						int isNodeOfCluster = findNodeInClusterNodes (oldStructure.getNextActions().get(i),clusterNodes);
						if (isNodeOfCluster == -1){ //it is not part of the cluster
							myArrNext.add(oldStructure.getNextActions().get(i));
							myArrNextCount.add(oldStructure.getNextActionsFrequency().get(i));
							myArrIndexSimpleEventsOfNext.add(oldStructure.getIndexSimpleEventsOfNext().get(i));
							myArrIndexSimpleEventsNextNext.add(oldStructure.getIndexSimpleEventsNextNext().get(i));
						}
						else{
							if (nextClusterCreated == 0){
								myArrNext.add("cluster_" + clusterNum);
								myArrNextCount.add(oldStructure.getNextActionsFrequency().get(i));
								nextClusterCreated = 1;
								myArrIndexSimpleEventsOfNext.add(oldStructure.getIndexSimpleEventsOfNext().get(i));
								myArrIndexSimpleEventsNextNext.add(oldStructure.getIndexSimpleEventsNextNext().get(i));
							}
							else{
								int clusterPosition = getPositionAction ("cluster_" + clusterNum, myArrNext);
								if (clusterPosition != -1){
									int newValue = myArrNextCount.get(clusterPosition) + oldStructure.getNextActionsFrequency().get(i);
									myArrNextCount.set(clusterPosition, newValue);
									for (int j = 0; j < oldStructure.getIndexSimpleEventsOfNext().get(i).size(); j++){
										myArrIndexSimpleEventsOfNext.get(clusterPosition).add(oldStructure.getIndexSimpleEventsOfNext().get(i).get(j));
										myArrIndexSimpleEventsNextNext.get(clusterPosition).add(oldStructure.getIndexSimpleEventsNextNext().get(i).get(j));
									}
								}
							}
						}

					}
					
					newStructure.setPreviousActions(myArrPrevious);
					newStructure.setPreviousActionsFrequency(myArrPreviousCount);
					newStructure.setIndexSimpleEvents(myArrIndexSimpleEvents);
					newStructure.setIndexSimpleEventsPrevious(myArrIndexSimpleEventsPrevious);
					newStructure.setNextActions(myArrNext);
					newStructure.setNextActionsFrequency(myArrNextCount);
					newStructure.setIndexSimpleEventsOfNext(myArrIndexSimpleEventsOfNext);
					newStructure.setIndexSimpleEventsNextNext(myArrIndexSimpleEventsNextNext);					
				}
				
				public static int findNodeInCreatedNodes(topologyStructure2[] structure2, String node, int totalNewNodes){
					int i = 0;
					
					while (i <= totalNewNodes){
						if (structure2[i].getNode().hashCode() == node.hashCode()){
							return i;
						}
						else i++;
					}
					
					return -1;
				}
				
				public static void findComponentsOfNode (topologyStructure2[] structure2, String node, ArrayList<String> listNodes){
					
					int index = 0, found = 0;
					
					while ((found != 1) && (index < structure2.length)){
						if (node.hashCode() == structure2[index].getNode().hashCode()){
							if (structure2[index].getTypeNode().compareTo("cluster")==0){
								for (int i = 0; i < structure2[index].getComponentsNode().size(); i++){
									listNodes.add(structure2[index].getComponentsNode().get(i));
								}
							}
							else{
								listNodes.add(structure2[index].getNode());
							}
							found = 1;
						}
						else{
							index++;
						}
					}
					
				}
				
			public static void updateUnorderedActionsStructure2(topologyStructure2[] structure2){
				
				for (int i = 0; i < structure2.length; i++){
					ArrayList<String> unorderedActions = new ArrayList<String> ();
					ArrayList<Integer> originActionsFrequency = new ArrayList<Integer> ();
					ArrayList<Integer> destinyActionsFrequency = new ArrayList<Integer> ();
					ArrayList<Double> originRelativeFrequency = new ArrayList<Double> ();
					ArrayList<Double> destinyRelativeFrequency = new ArrayList<Double> ();
					ArrayList<Double> balancedRelationFrequency = new ArrayList<Double> ();
					ArrayList<Double> totalStrengthRelation = new ArrayList<Double> ();
					
					for (int j = 0; j < structure2[i].getPreviousActions().size(); j++){
						if(structure2[i].getPreviousActions().get(j).hashCode() != structure2[i].getNode().hashCode()){
							int hashCodePreviousAction = structure2[i].getPreviousActions().get(j).hashCode();
							for (int k = 0; k < structure2[i].getNextActions().size(); k++){ //without taking into account the frequency of the next actions
								if (hashCodePreviousAction == structure2[i].getNextActions().get(k).hashCode()){
									double originValue = structure2[i].getNextActionsFrequency().get(k);
									double destinyValue = structure2[i].getPreviousActionsFrequency().get(j);
									double originRelative = originValue / numTotalActionsInGroupActions(structure2[i].getNextActionsFrequency());
									double destinyRelative = destinyValue / numTotalActionsInGroupActions(structure2[i].getPreviousActionsFrequency());
									double balancedValue = 1 - (Math.abs(originValue - destinyValue) / (originValue + destinyValue));
									double totalStrength = ((0.25 * originRelative) + (0.25 * destinyRelative) + (0.5 * balancedValue));
									
									if ((originRelative >= defineTopology.getminimumAbsoluteOriginFrequency()) && (destinyRelative >= defineTopology.getminimumAbsoluteDestinyFrequency()) && (balancedValue >= defineTopology.getminimumBalancedRelationFrequency())){
										unorderedActions.add(structure2[i].getNextActions().get(k));
										destinyActionsFrequency.add(structure2[i].getPreviousActionsFrequency().get(j));
										originActionsFrequency.add(structure2[i].getNextActionsFrequency().get(k));
										originRelativeFrequency.add(originRelative);
										destinyRelativeFrequency.add(destinyRelative);
										balancedRelationFrequency.add(balancedValue);
										totalStrengthRelation.add(totalStrength);
									}					
								}
							}
						}
					}
					
					structure2[i].setUnordereActions(unorderedActions);
					structure2[i].setOriginActionsFrequency(originActionsFrequency);
					structure2[i].setDestinyActionsFrequency(destinyActionsFrequency);
					structure2[i].setOriginRelativeFrequency(originRelativeFrequency);
					structure2[i].setDestinyRelativeFrequency(destinyRelativeFrequency);
					structure2[i].setBalancedRelationFrequency(balancedRelationFrequency);
					structure2[i].setTotalStrenghtRelation(totalStrengthRelation);			
					
				}
			}
			
				public static int numTotalActionsInGroupActions (ArrayList<Integer> array){
					
					int total = 0;
					
					for (int i = 0; i < array.size(); i++){
						total = total + array.get(i);
					}
					
					return total;
					
				}
				
			
		public static void visualizeTopologyStructure2 (topologyStructure2[] structure2){
			
			for (int index = 0; index < structure2.length; index++){
				System.out.println("action " + structure2[index].getNode() + " type of node " + structure2[index].getTypeNode() + " components " + structure2[index].getComponentsNode());
				for (int i = 0; i < structure2[index].getPreviousActions().size(); i++){
					System.out.print("     previous action " + structure2[index].getPreviousActions().get(i) + " count " + structure2[index].getPreviousActionsFrequency().get(i));
					for (int j = 0; j < structure2[index].getIndexSimpleEvents().get(i).size(); j++){
						System.out.print(" (" + structure2[index].getIndexSimpleEvents().get(i).get(j)+ "/" + structure2[index].getIndexSimpleEventsPrevious().get(i).get(j) + ")");
					}
					System.out.println();
				}
				for (int i = 0; i < structure2[index].getNextActions().size(); i++){
					System.out.println("     next action " + structure2[index].getNextActions().get(i) + " count " + structure2[index].getNextActionsFrequency().get(i));
				}
				
				System.out.println();
				
				for (int j = 0; j < structure2[index].getUnorderedActions().size(); j++){
					System.out.println("    possible unordered " + structure2[index].getUnorderedActions().get(j) + " frequencies " + structure2[index].getOriginActionsFrequency().get(j) + " / " + structure2[index].getDestinyActionsFrequency().get(j));
					System.out.println("       origin absolute: " + structure2[index].getOriginRelativeFrequency().get(j) + " destiny absolute: " + structure2[index].getDestinyRelativeFrequency().get(j) + " balanced: " + structure2[index].getBalancedRelationFrequency().get(j) + " total strength: " + structure2[index].getTotalStrenghtRelation().get(j));
				}
				
			}
		}

}

