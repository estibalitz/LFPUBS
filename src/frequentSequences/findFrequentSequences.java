package frequentSequences;

import java.util.ArrayList;
import basic.DataStructure;

import splitSequences.fullSequences;
import splitSequences.simpleEvent;

public class findFrequentSequences {
	
	public findFrequentSequences(){
		
	}
	
	public ArrayList<simpleSequence> findOutFrequentSequences (int initialMinSup,int longerMinSup,double lengthSimilarity,ArrayList<fullSequences> allRawSequences){
		
		int initialNumSequences = allRawSequences.size();
		int totalActions = simpleEvent.getEventLabel().size();
		ArrayList<Integer> [] initialInstances = new ArrayList[initialNumSequences];
		ArrayList<simpleSequence> tempSequences = new ArrayList<simpleSequence>();
		ArrayList<Integer>[] tempFPGrowthRawSequences = new ArrayList [initialNumSequences];
		ArrayList<Integer>[] temp2FPGrowthRawSequences;
		ArrayList<Integer> tempRuledOutInstances = new ArrayList<Integer>();
		//short [][] finalFPGrowthRawSequences = new short [initialNumSequences][];
		short [][] finalFPGrowthRawSequences;
		
		//initialize the initialInstances
		for (int i = 0; i < initialNumSequences; i++)
		{
			//System.out.println("    sequence " + i);
			initialInstances[i] = new ArrayList<Integer>();
			for (int j = 0; j < totalActions; j++){
				initialInstances[i].add(0);
			}
			for (int k = 0; k < allRawSequences.get(i).getEventsOfSequence().size(); k++){
				initialInstances[i].set(allRawSequences.get(i).getEventsOfSequence().get(k).getEvent(), 1);
			}
		}
		
		//convert to FPGrowth style
		int numActions = 0;
		for (int i = 0; i < initialNumSequences; i++){
			tempFPGrowthRawSequences[i] = new ArrayList<Integer>();
			for (int j = 0; j < totalActions; j++){
				if (initialInstances[i].get(j) == 1){
					tempFPGrowthRawSequences[i].add(j);
				}
			}
			if (tempFPGrowthRawSequences[i].size() > 1){
				numActions++;
			}
		}
		
		
		temp2FPGrowthRawSequences = new ArrayList[numActions];
		int indexFPGrowth = 0;
		for (int i = 0; i < initialNumSequences; i++){
			if (tempFPGrowthRawSequences[i].size() > 1){
				temp2FPGrowthRawSequences[indexFPGrowth] = new ArrayList<Integer>();
				for (int j = 0; j < tempFPGrowthRawSequences[i].size(); j++){
					temp2FPGrowthRawSequences[indexFPGrowth].add(tempFPGrowthRawSequences[i].get(j));
				}
				indexFPGrowth++;
			}
			else{
				tempRuledOutInstances.add(i);
			}
		}
		
		DataStructure.getInstance().getFrequentSequencesDataStructure().setRuledOutInstances(tempRuledOutInstances);
		
		finalFPGrowthRawSequences = new short [numActions][];
		for (int i = 0; i < numActions; i++){
			finalFPGrowthRawSequences[i] = new short [temp2FPGrowthRawSequences[i].size()];
			for (int j = 0; j < temp2FPGrowthRawSequences[i].size(); j++){
				finalFPGrowthRawSequences[i][j] = Short.parseShort(Integer.toString(temp2FPGrowthRawSequences[i].get(j))) ;
			}
		}
		
			
		//visualize
		/*System.out.println("total Actions " + totalActions + " numSequences " + initialNumSequences);
		for (int i = 0; i < initialInstances.length; i++){
			for (int j = 0; j < initialInstances[i].size(); j++){
				System.out.print(initialInstances[i].get(j) + " ");
			}
			System.out.println();
		}*/
		
		//visualize FPGrowth style
		/*System.out.println("total Actions " + totalActions + " numSequences " + numActions);
		for (int i = 0; i < finalFPGrowthRawSequences.length; i++){
			for (int j = 0; j < finalFPGrowthRawSequences[i].length; j++){
				System.out.print(finalFPGrowthRawSequences[i][j] + " ");
			}
			System.out.println();
		}*/
		//System.out.println("Ruled Out Instances " + DataStructure.getInstance().getFrequentSequencesDataStructure().getRuledOutInstances().toString());
		
					
		//FPGrowth
		
		//find basic sequences
		tempSequences = FPGrowth.findFrequentSetsOfActions(initialInstances, finalFPGrowthRawSequences, totalActions, finalFPGrowthRawSequences.length, initialMinSup, lengthSimilarity);
		
	
		//find extra actions in sequences cases
		if (DataStructure.getInstance().getSequencesVSOneToOne() == 1){
			for (int i = 0; i < tempSequences.size(); i++){
				ArrayList<simpleSequence> tempExtraActionSequences = new ArrayList<simpleSequence>();
				//create the new instances
				ArrayList<Integer> [] tempInstances = new ArrayList[(int)tempSequences.get(i).getCounter()];
				short [][] finalFPGrowthExtraActionSequences = new short [(int)tempSequences.get(i).getCounter()][];
				for (int j = 0; j < tempSequences.get(i).counter; j++){
					tempInstances[j] = new ArrayList<Integer>();
					//copy the values of the instances
					for (int k = 0; k < totalActions; k++){
						tempInstances[j].add(initialInstances[tempSequences.get(i).instances.get(j)].get(k));
					}
					//put 0 in the original sequence's actions
					for (int l = 0; l < tempSequences.get(i).sequence.size(); l++){
						tempInstances[j].set(tempSequences.get(i).sequence.get(l),0);
					}
					//create finalFPGrowthExtraActionSequences (warning, to be able to use the FP Growth algorithm we need to get actions > 0, so we need to +1 all actions
					//count how many actions there are
					int count = 0;
					for (int k = 0; k < totalActions; k++){
						if (tempInstances[j].get(k) == 1){
							count++;
						}
					}
					finalFPGrowthExtraActionSequences[j] = new short [count];
					//parse into finalFPGrowthExtraActionSequences
					int index = 0;
					for (int k = 0; k < totalActions; k++){
						if (tempInstances[j].get(k) == 1){
							finalFPGrowthExtraActionSequences[j][index] = Short.parseShort(Integer.toString(k));
							index++;
						}
					}
				}
				
				//visualize
				/*System.out.println("tempInstances ");
				for (int j = 0; j < tempInstances.length; j++){
					for (int k = 0; k < tempInstances[j].size(); k++){
						System.out.print(tempInstances[j].get(k) + " ");
					}
					System.out.println();
					for (int k = 0; k < finalFPGrowthExtraActionSequences[j].length; k++){
						System.out.print(finalFPGrowthExtraActionSequences[j][k]);
					}
					System.out.println();
				}*/
				
				//run the FP Growth algorithm
				tempExtraActionSequences = FPGrowth.findFrequentSetsOfActions(tempInstances, finalFPGrowthExtraActionSequences, totalActions, finalFPGrowthExtraActionSequences.length, longerMinSup, lengthSimilarity);
				
				//find out if there is any extra action
				for (int j = 0; j < tempExtraActionSequences.size(); j++){
					for (int k = 0; k < tempExtraActionSequences.get(j).getSequence().size(); k++){
						if ((findInstance(tempExtraActionSequences.get(j).getSequence().get(k),tempSequences.get(i).getSequence())== 0)&&(findInstance(tempExtraActionSequences.get(j).getSequence().get(k),tempSequences.get(i).longExtraActions)==0)){
							tempSequences.get(i).longExtraActions.add(tempExtraActionSequences.get(j).sequence.get(k));
						}
					}
				}
			}
		}	
		
		
		//A-priori	
		/*
		//basic apriori
		System.out.println("Before apriori");
		tempSequences = aprioriProcess.findFrequentSequences(initialInstances, totalActions, initialInstances.length, initialMinSup, lengthSimilarity);
		//dic apriori
		tempSequences = aprioriDicProcess.findFrequentSequences(initialInstances, totalActions, initialInstances.length, initialMinSup, lengthSimilarity);
		System.out.println("after apriori");

		for (int i = 0; i < tempSequences.size(); i++){
			ArrayList<Integer> [] tempInstances = new ArrayList[(int)tempSequences.get(i).getCounter()];
			//create the new instances
			for (int j = 0; j < tempSequences.get(i).counter; j++){
				tempInstances[j] = new ArrayList<Integer>();
				for (int k = 0; k < totalActions; k++){
					tempInstances[j].add(initialInstances[tempSequences.get(i).instances.get(j)].get(k));
				}
				//if (tempSequences.get(i).sequence.size() >=1){
					for (int l = 1; l < tempSequences.get(i).sequence.size(); l++){
						tempInstances[j].set(tempSequences.get(i).sequence.get(l),0);
					}
				//}	
			}
			
			System.out.println("tempInstances ");
			for (int j = 0; j < tempInstances.length; j++){
				for (int k = 0; k < tempInstances[j].size(); k++){
					System.out.print(tempInstances[j].get(k) + " ");
				}
				System.out.println();
			}
			
			//find sequences that are frequent in the instances of the sequence
			ArrayList<simpleSequence> tempLongerSequences = aprioriProcess.findFrequentSequences(tempInstances, totalActions, tempInstances.length, longerMinSup, lengthSimilarity);
			//find out if there is any extra action
			for (int j = 0; j < tempLongerSequences.size(); j++){
				for (int k = 0; k < tempLongerSequences.get(j).length; k++){
					if ((findInstance(tempLongerSequences.get(j).sequence.get(k),tempSequences.get(i).sequence)== 0)&&(findInstance(tempLongerSequences.get(j).sequence.get(k),tempSequences.get(i).longExtraActions)==0)){
						tempSequences.get(i).longExtraActions.add(tempLongerSequences.get(j).sequence.get(k));
					}
				}
			}
		}*/
		
		return tempSequences;
		
	}
	
		//-------------------------------------------------------------
		//Method Name: findInstance(int, ArrayList<Integer>)
		//Purpose    : find if an instance is within a set of instances
		//Parameters : instances, set of instances
		//Return     : it is within --> 1, it is not-->0
		//-------------------------------------------------------------
	
		private static int findInstance (int instance, ArrayList<Integer> instances){
			
			for (int i = 0; i < instances.size(); i++){
				if (instance == instances.get(i)){
					return 1;
				}
			}
			return 0;
		}

}
