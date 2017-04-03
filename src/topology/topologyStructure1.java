package topology;

import java.util.ArrayList;

public class topologyStructure1 {
	
	//static variables
	
	private static int numSequences;
	
	private static int numGeneralActions;
	
	private static String[] namesGeneralActions;
	
	private static int[] generalActionsClusters;
	
	private static int numGeneralTotalActions;
	
	//class variables
	
	private int numTotalActions; //a_0,a_1,a_2,b_0,c_0,d_0,e_0,f_0 --> 8
	
	private String action;
	
	private int cluster;
	
	private String actionCluster;
	
	private int totalPreviousActions;
	
	private int totalNextActions;
	
	private ArrayList<String> previousActions = new ArrayList<String>();
	
	private ArrayList<Integer> previousActionsFrequency = new ArrayList<Integer>();
	
	private ArrayList<ArrayList<Integer>> indexSimpleEvents = new ArrayList<ArrayList<Integer>> ();
	
	private ArrayList<ArrayList<Integer>> indexSimpleEventsPrevious = new ArrayList<ArrayList<Integer>> ();
	
	private ArrayList<ArrayList<Integer>> indexSimpleEventsOfNext = new ArrayList<ArrayList<Integer>> ();
	
	private ArrayList<ArrayList<Integer>> indexSimpleEventsNextNext = new ArrayList<ArrayList<Integer>> ();
		
	private ArrayList<String> nextActions = new ArrayList<String>();
	
	private ArrayList<Integer> nextActionsFrequency = new ArrayList<Integer>();
	
	public topologyStructure1 (){
	}
	
	public topologyStructure1 (String action, int cluster, int numTotalActions){
		this.action = action;
		this.cluster = cluster;
		actionCluster = action + "_" + cluster;
		this.setNumTotalActions(numTotalActions);
	}
	
	public void setNumTotalActions (int totalActions){
		this.numTotalActions = totalActions;
	}
	
	public void setActionCluster (String actionCluster){
		this.actionCluster = actionCluster;
	}
	
	public void setTotalPreviousActions (int totalPreviousActions){
		this.totalPreviousActions = totalPreviousActions;
	}
	
	public void setTotalNextActions (int totalNextActions){
		this.totalNextActions = totalNextActions;
	}
	
	public void setPreviousActions (ArrayList<String> previousActions){
		this.previousActions = previousActions;
	}
	
	public void setPreviousActionsFrequency (ArrayList<Integer> previousActionsFrequency){
		this.previousActionsFrequency = previousActionsFrequency;
	}
	
	public void setIndexFirstSimpleEvent (ArrayList<ArrayList<Integer>> indexFirstSimpleEvent){
		this.indexSimpleEvents = indexFirstSimpleEvent;
	}
	
	public void setIndexFirstSimpleEventPrevious (ArrayList<ArrayList<Integer>> indexFirstSimpleEventPrevious){
		this.indexSimpleEventsPrevious = indexFirstSimpleEventPrevious;
	}
	
	public void setNextActions (ArrayList<String> nextActions){
		this.nextActions = nextActions;
	}
	
	public void setNextActionsFrequency (ArrayList<Integer> nextActionsFrequency){
		this.nextActionsFrequency = nextActionsFrequency;
	}
	
	public void setIndexFirstSimpleEventOfNext (ArrayList<ArrayList<Integer>> indexFirstSimpleEventOfNext){
		this.indexSimpleEventsOfNext = indexFirstSimpleEventOfNext;
	}
	
	public void setIndexFirstSimpleEventNextNext (ArrayList<ArrayList<Integer>> indexFirstSimpleEventNextNext){
		this.indexSimpleEventsNextNext = indexFirstSimpleEventNextNext;
	}
	
	public String getActionCluster (){
		return this.actionCluster;
	}
	
	public int getTotalPreviousActions (){
		return this.totalPreviousActions;
	}
	
	public int getTotalNextActions (){
		return this.totalNextActions;
	}
	
	public ArrayList<String> getPreviousActions (){
		return this.previousActions;
	}
	
	public ArrayList<Integer> getPreviousActionsFrequency (){
		return this.previousActionsFrequency;
	}
	
	public ArrayList<ArrayList<Integer>> getIndexFirstSimpleEvent (){
		return this.indexSimpleEvents;
	}
	
	public ArrayList<ArrayList<Integer>> getIndexFirstSimpleEventPrevious (){
		return this.indexSimpleEventsPrevious;
	}
	
	public ArrayList<String> getNextActions (){
		return this.nextActions;
	}
	
	public ArrayList<Integer> getNextActionsFrequency (){
		return this.nextActionsFrequency;
	}
	
	public ArrayList<ArrayList<Integer>> getIndexFirstSimpleEventOfNext (){
		return this.indexSimpleEventsOfNext;
	}
	
	public ArrayList<ArrayList<Integer>> getIndexFirstSimpleEventNextNext (){
		return this.indexSimpleEventsNextNext;
	}
	
	public static void setInitialTopologyStructure1 (clusteredSequence[] clusteredSequences){
		
		numSequences = clusteredSequences.length;
		
		numGeneralActions = defineActionsClusters.getNumDifferentActions();
		
		namesGeneralActions = defineActionsClusters.getNames();
		
		generalActionsClusters = defineActionsClusters.getClusters();
		
		numGeneralTotalActions = setNumTotalActions(numGeneralActions,generalActionsClusters);
		
	}
	
		public static int setNumTotalActions (int numGeneralActions, int[] numClusterGeneralActions){
			int total = 0;
			
			for (int i = 0; i < numGeneralActions; i++){
				total += numClusterGeneralActions[i];
			}
					
			return total;
		}
			
	public static topologyStructure1[] setTopologyStructure1 (clusteredSequence[] clusteredSequences, ArrayList<Integer>[] initialSequencesIndexSimpleEvents){
		
		topologyStructure1[] structure1 = new topologyStructure1[numGeneralTotalActions];
		
		int index = 0;
		
		for (int i = 0; i < namesGeneralActions.length; i++){
			for (int j = 0; j < generalActionsClusters[i]; j++){
				structure1[index] = new topologyStructure1(namesGeneralActions[i],j,numGeneralTotalActions);
				index++;
			}
		}
		
		for (int i = 0; i < numGeneralTotalActions; i++){
			//System.out.println("action " + structure1[i].getActionCluster());
			setPreviousNextActionsStructure1 (structure1[i], clusteredSequences,initialSequencesIndexSimpleEvents);
			countPreviousNextActions (structure1[i]);
			//visualizeTopologyStructure1(structure1,i);
		}		
		
		return structure1;		
	}
	
		public static void setPreviousNextActionsStructure1 (topologyStructure1 structure, clusteredSequence[] sequences,ArrayList<Integer>[] initialSequencesIndexSimpleEvents){
			
			ArrayList<String> myArrPrevious = new ArrayList<String>();
			ArrayList<String> myArrNext = new ArrayList<String>();
			ArrayList<Integer> myArrPreviousCount = new ArrayList<Integer>();
			ArrayList<Integer> myArrNextCount = new ArrayList<Integer>();
			ArrayList<ArrayList<Integer>> myArrIndexSimpleEvents = new ArrayList<ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> myArrIndexSimpleEventsPrevious = new ArrayList<ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> myArrIndexSimpleEventsOfNext = new ArrayList<ArrayList<Integer>>();
			ArrayList<ArrayList<Integer>> myArrIndexSimpleEventsNextNext = new ArrayList<ArrayList<Integer>>();

			
			for (int i = 0; i < sequences.length; i++){
				ArrayList<Integer> hashSequence = sequences[i].getCodeSequence();
				for (int j = 0; j < sequences[i].getNumActions(); j++){
					if (structure.getActionCluster().hashCode() == hashSequence.get(j)){
						//System.out.println("    found sequence " + i + " action " + j);
						
						//previous
						String strPrevious;
						if (j != 0){
							strPrevious = sequences[i].getSimpleSequence().get(j-1) + "_" + sequences[i].getSimpleSequenceCluster().get(j-1);
						}
						else{
							strPrevious = "start";
						}
						int indexPrevious = getPositionAction(strPrevious,myArrPrevious);
						if (indexPrevious != -1){
							int previousValue = myArrPreviousCount.get(indexPrevious);
							previousValue++;
							myArrPreviousCount.set(indexPrevious, previousValue);
							myArrIndexSimpleEvents.get(indexPrevious).add(initialSequencesIndexSimpleEvents[i].get(j));
							if (j != 0){
								myArrIndexSimpleEventsPrevious.get(indexPrevious).add(initialSequencesIndexSimpleEvents[i].get(j-1));
							}
							else{
								myArrIndexSimpleEventsPrevious.get(indexPrevious).add(-1);
							}
						}
						else{
							myArrPrevious.add(strPrevious);
							myArrPreviousCount.add(1);
							ArrayList<Integer> temp = new ArrayList<Integer>();
							ArrayList<Integer> tempPrevious = new ArrayList<Integer>();
							temp.add(initialSequencesIndexSimpleEvents[i].get(j));
							if (j != 0){
								tempPrevious.add(initialSequencesIndexSimpleEvents[i].get(j-1));
							}
							else{
								tempPrevious.add(-1);
							}
							myArrIndexSimpleEvents.add(temp);
							myArrIndexSimpleEventsPrevious.add(tempPrevious);
						}
												
						//next
						String strNext;
						if (j != sequences[i].getNumActions()-1){
							strNext = sequences[i].getSimpleSequence().get(j+1) + "_" + sequences[i].getSimpleSequenceCluster().get(j+1);
						}
						else{
							strNext = "end";
						}
						
						int indexNext = getPositionAction(strNext,myArrNext);
						if (indexNext != -1){
							int nextValue = myArrNextCount.get(indexNext);
							nextValue++;
							myArrNextCount.set(indexNext, nextValue);
							myArrIndexSimpleEventsOfNext.get(indexNext).add(initialSequencesIndexSimpleEvents[i].get(j));
							if (j != sequences[i].getNumActions()-1){
								myArrIndexSimpleEventsNextNext.get(indexNext).add(initialSequencesIndexSimpleEvents[i].get(j+1));
							}
							else{
								myArrIndexSimpleEventsNextNext.get(indexNext).add(-1);
							}
						}
						else{
							myArrNext.add(strNext);
							myArrNextCount.add(1);
							ArrayList<Integer> tempOfNext = new ArrayList<Integer>();
							ArrayList<Integer> tempNextNext = new ArrayList<Integer>();
							tempOfNext.add(initialSequencesIndexSimpleEvents[i].get(j));
							if (j != sequences[i].getNumActions()-1){
								tempNextNext.add(initialSequencesIndexSimpleEvents[i].get(j+1));
							}
							else{
								tempNextNext.add(-1);
							}
							myArrIndexSimpleEventsOfNext.add(tempOfNext);
							myArrIndexSimpleEventsNextNext.add(tempNextNext);
						}
					}
				}
			}
			
			structure.setPreviousActions(myArrPrevious);
			structure.setPreviousActionsFrequency(myArrPreviousCount);
			structure.setNextActions(myArrNext);
			structure.setNextActionsFrequency(myArrNextCount);
			structure.setIndexFirstSimpleEvent(myArrIndexSimpleEvents);
			structure.setIndexFirstSimpleEventPrevious(myArrIndexSimpleEventsPrevious);
			structure.setIndexFirstSimpleEventOfNext(myArrIndexSimpleEventsOfNext);
			structure.setIndexFirstSimpleEventNextNext(myArrIndexSimpleEventsNextNext);
		}

		public static void countPreviousNextActions (topologyStructure1 structure){
			int previous = 0, next = 0;
			
			for (int i = 0; i < structure.getPreviousActions().size(); i++){
				previous += structure.getPreviousActionsFrequency().get(i);
			}
			
			for (int j = 0; j < structure.getNextActions().size(); j++){
				next += structure.getNextActionsFrequency().get(j);
			}
			structure.setTotalPreviousActions(previous);
			structure.setTotalNextActions(next);
		}
		
		public static void visualizeTopologyStructure1 (topologyStructure1[] structure1, int index){
			
			System.out.println("action " + structure1[index].getActionCluster());
			
			for (int i = 0; i < structure1[index].getPreviousActions().size(); i++){
				System.out.print("     previous action " + structure1[index].getPreviousActions().get(i) + " count " + structure1[index].getPreviousActionsFrequency().get(i));
				for (int j = 0; j < structure1[index].getIndexFirstSimpleEvent().get(i).size(); j++){
					System.out.print(" (" + structure1[index].getIndexFirstSimpleEvent().get(i).get(j)+ "/" + structure1[index].getIndexFirstSimpleEventPrevious().get(i).get(j) + ")");
				}
				System.out.println();
			}
			for (int i = 0; i < structure1[index].getNextActions().size(); i++){
				System.out.println("     next action " + structure1[index].getNextActions().get(i) + " count " + structure1[index].getNextActionsFrequency().get(i));
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

		public static int getNumGeneralTotalActions (){
			return numGeneralTotalActions;
		}

}
