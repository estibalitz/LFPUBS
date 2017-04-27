package topology;
import weka.clusterers.*;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.OptionHandler;
import weka.core.Utils;
import weka.filters.Filter;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.FastVector;
import weka.core.Attribute;

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;
import java.math.*;


public class defineActionsClusters {
	
	//variables
	
	static int numDifferentActions = 0;
	static ArrayList<String> namesDifferentActions = new ArrayList<String>();
	static ArrayList<Integer> numMaximumActions = new ArrayList<Integer> ();
	static ArrayList<Integer> numMeanActions = new ArrayList<Integer>();
	static ArrayList<Integer> numTotalActions = new ArrayList<Integer> ();
	static ArrayList<Integer> numClustersForActions = new ArrayList<Integer> ();
	
	//constructors
	
	public defineActionsClusters (){
		
	}
	
	//functions
	
	public clusteredSequence[] setActionsClusters (ArrayList<String> [] initialSequences, int numberPreviousNextActions, int howDefineCluster, int considerPreviousClusters) throws Exception {
		
		clusteredSequence[] clusteredSequences = new clusteredSequence[initialSequences.length];
		
		//if there are more than one sequence
		namesDifferentActions.clear();
		numMaximumActions.clear();
		numMeanActions.clear();
		numTotalActions.clear();
		numClustersForActions.clear();
		
		this.numDifferentActions = getNumActionsNameNumMaximumActions (initialSequences,this.namesDifferentActions, this.numMaximumActions, this.numMeanActions, this.numTotalActions);
		
		/*for (int i = 0; i < this.numDifferentActions; i++){
			System.out.println(" name " + this.namesDifferentActions.get(i) + " maximum " + this.numMaximumActions.get(i) + " mean " + this.numMeanActions.get(i) + " total " + this.numTotalActions.get(i));
		}*/
		
		clusteredSequences = setClusteredSequences (initialSequences, namesDifferentActions, numMaximumActions, numMeanActions, numTotalActions, numberPreviousNextActions, howDefineCluster, considerPreviousClusters);
		
		/*for (int i = 0; i < clusteredSequences.length; i++){
			System.out.println(" sequence " + i + " length " + clusteredSequences[i].getNumActions());
			for (int j = 0; j < clusteredSequences[i].getNumActions(); j++){
				System.out.print("   action " + clusteredSequences[i].getSimpleSequence().get(j) + " cluster " + clusteredSequences[i].getSimpleSequenceCluster().get(j));
			}
			System.out.println();
		}*/
		
		return clusteredSequences;
		
	}
	
		public int getNumActionsNameNumMaximumActions (ArrayList<String> [] initialSequences, ArrayList<String> names, ArrayList<Integer> maximumActions, ArrayList<Integer> meanActions, ArrayList<Integer> totalActions){
			
			int numActions = 0;
			ArrayList<Integer> tempMaximumActions = new ArrayList<Integer> ();
			
			//define the actions and their names
			for (int i = 0; i < initialSequences.length; i++){
				for (int j = 0; j < initialSequences[i].size(); j++){
					if (names.contains(initialSequences[i].get(j))){						
					}
					else{
						names.add(initialSequences[i].get(j));
						numActions++;
					}
				}
			}
			
			//define the maximum number of actions of each action.
			int [] tempMaximumValues = new int[names.size()];
			int [] totalValues = new int [names.size()];
			int [] finalMaximumValues = new int [names.size()];
			int [] sumMeanValues = new int [names.size()];
			int numMeanValues = 0;
			int [] finalMeanValues = new int[names.size()];
			for (int i = 0; i < names.size(); i++){
				tempMaximumValues[i] = 0;
				finalMaximumValues[i] = 0;
				sumMeanValues[i] = 0;
				finalMeanValues[i] = 0;
			}
			
			numMeanValues = initialSequences.length;
				
			for (int i = 0; i < initialSequences.length; i++){
				for (int j = 0; j < initialSequences[i].size(); j++){
					for (int k = 0; k < names.size(); k++){
						if (initialSequences[i].get(j).hashCode() == names.get(k).hashCode()){
							totalValues[k]++;
							tempMaximumValues[k]++;
							sumMeanValues[k]++;
						}
					}
				}
				for (int j = 0; j < names.size(); j++){
					if (tempMaximumValues[j] > finalMaximumValues[j]){
						finalMaximumValues[j] = tempMaximumValues[j];
						tempMaximumValues[j] = 0;
					}
					else{
						tempMaximumValues[j] = 0;
					}
					finalMeanValues[j] = sumMeanValues[j] / numMeanValues;
				}
			}
			
			for (int i = 0; i < names.size(); i++){
				maximumActions.add(finalMaximumValues[i]);
				meanActions.add(finalMeanValues[i]);
				totalActions.add(totalValues[i]);
			}
			
			
			
			return numActions;
			
		}
		
		public clusteredSequence[] setClusteredSequences (ArrayList<String> [] initialSequences, ArrayList<String> names, ArrayList<Integer> maximumActions, ArrayList<Integer> meanActions, ArrayList<Integer> totalActions, int numberPreviousNextActions, int howDefineCluster, int considerPreviousClusters) throws Exception {
			
			clusteredSequence[] clusteredSequences = new clusteredSequence [initialSequences.length];
			int[] countAction = new int[names.size()];
			ArrayList<Integer> [] clusterBelonging = new ArrayList [names.size()];
			ArrayList<Integer> [] realClusterBelonging = new ArrayList [names.size()];
			ArrayList<String> [] tempInitialSequences = new ArrayList [initialSequences.length];
			for (int i = 0; i < initialSequences.length; i++){
				tempInitialSequences[i] = new ArrayList<String>();
				for (int j = 0; j < initialSequences[i].size(); j++){
					tempInitialSequences[i].add(initialSequences[i].get(j));
				}
			}
			
			ArrayList<String> tempNames = new ArrayList<String>();
			for (int i = 0; i < names.size(); i++){
				tempNames.add(names.get(i));
			}

			for (int i = 0; i < names.size(); i++){
				int possibleNumberClusters = 0;
				if (howDefineCluster != 2){
					possibleNumberClusters = maximumActions.get(i);
				}
				else{
					possibleNumberClusters = meanActions.get(i);
				}
				//if (maximumActions.get(i) != 1){
				//if (meanActions.get(i) > 1){
				if (possibleNumberClusters > 1){
					//System.out.println(" name of the actions " + this.namesDifferentActions.get(i));
					clusterBelonging[i] = calculateClusters(tempInitialSequences,names.get(i),maximumActions.get(i), meanActions.get(i), totalActions.get(i), tempNames, numberPreviousNextActions, howDefineCluster, considerPreviousClusters);
					realClusterBelonging[i] = new ArrayList<Integer> ();
					//because we've got duplicated action
					for (int j = 0; j < clusterBelonging[i].size(); j=j+2){
					//for (int j = 0; j < clusterBelonging[i].size(); j++){
						realClusterBelonging[i].add(clusterBelonging[i].get(j));
					}
				}
				else{
					numClustersForActions.add(1);
				}
				
				/*for (int j = 0; j < tempInitialSequences.length; j++){
					for (int k = 0; k < tempInitialSequences[j].size(); k++){
						System.out.print(tempInitialSequences[j].get(k) + "  ");
					}
					System.out.println();
				}*/
				
			}

			//fill the clusteredSequence
			for (int i = 0; i < countAction.length; i++){
				countAction[i] = 0;
			}
	
			for (int i = 0; i < initialSequences.length; i++){
				ArrayList<Integer> clusterBelongingSequence = new ArrayList<Integer>();
				for (int j = 0; j < initialSequences[i].size(); j++){
					int position = getPositionActions(initialSequences[i].get(j), names);
					//if (maximumActions.get(position) > 1){
					if (meanActions.get(position) > 1){
						clusterBelongingSequence.add(realClusterBelonging[position].get(countAction[position]));
					}
					else{
						clusterBelongingSequence.add(0);
					}
					countAction[position]++;
				}
				
				clusteredSequences[i] = new clusteredSequence (initialSequences[i],clusterBelongingSequence);
			}
			
			return clusteredSequences;
			
		}
		
			public ArrayList<Integer> calculateClusters (ArrayList<String> [] tempInitialSequences, String name, int maximumNumCluster, int meanNumCluster, int totalActions, ArrayList<String> tempNames, int numberPreviousNextActions, int howDefineCluster, int considerPreviousClusters) throws Exception {
				
				ArrayList<Integer> clusterBelonging = new ArrayList<Integer>();
				//int numAttributes = 9; //"previous4","previous3","previous2","previous1","next1","next2","next3","next4","last"
				int numAttributes = (2 * numberPreviousNextActions) + 1; //"previous x", "previous x-1", ... , "next x-1", "next x", "last"
				int numInstances = totalActions * 2;
				String[] options;
				ArrayList<Integer> [] positionList = new ArrayList [tempInitialSequences.length];
							
				switch (howDefineCluster){
				case 1: options = new String[4];
						options[0] = "-I";        
						options[1] = "100";
						options[2] = "-N"; //Define the number of clusters
						options[3] = Integer.toString(maximumNumCluster); //maximumNumCluster
						break;
				case 2: options = new String[4];
						options[0] = "-I";        
						options[1] = "100";
						options[2] = "-N"; //Define the number of clusters
						options[3] = Integer.toString(meanNumCluster); //meanNumCluster
						break;
				default: options = new String[2];
						options[0] = "-I";        
						options[1] = "100";
						break;
				}
				
			    /*String[] options = new String[4];
				options[0] = "-I";        
				options[1] = "100";
				options[2] = "-N"; //Define the number of clusters
				options[3] = Integer.toString(meanNumCluster); //maximumNumCluster*/
				
				String header = "labelCluster";
				String[] attributeNames = new String[numAttributes];
				int tempIndex = numAttributes/2;
				for (int i = 0; i < numAttributes/2; i++){
					String tempStr = "previous" + Integer.toString(tempIndex);
					attributeNames[i] = tempStr;
					tempIndex--;
				}
				for (int i = numAttributes/2; i < numAttributes - 1; i++){
					tempIndex++;
					String tempStr = "next" + Integer.toString(tempIndex);
					attributeNames[i] = tempStr;
				}
				attributeNames[numAttributes-1] = "last";
				//String[] attributeNames = {"previous4","previous3","previous2","previous1","next1","next2","next3","next4","last"};
				/*for (int i = 0; i < numAttributes; i++){
					System.out.println("action " + attributeNames[i]);
				}*/
				
				FastVector[] attributeValues = new FastVector[numAttributes];

				for (int i = 0; i < numAttributes; i++){
					  attributeValues[i] = new FastVector();
				}
				
				/*for (int i = 0; i < tempNames.size(); i++){
					System.out.println(" name " + tempNames.get(i));
				}*/
				
				for (int i = 0; i < numAttributes; i++){
					attributeValues[i].addElement("start");
					for (int j = 0; j < tempNames.size(); j++){
						attributeValues[i].addElement(tempNames.get(j));
					}
					attributeValues[i].addElement("end");
				}
				

				double [][] previousNextActions = calculatePreviousNextActions (tempInitialSequences, name, numAttributes, numInstances, tempNames, positionList);
				
				//System.out.println("positionList " + positionList.toString());
				
				/*for (int i = 0; i < previousNextActions.length; i++){
					System.out.println("instance: " + i);
					for (int j = 0; j < previousNextActions[i].length; j++){
						System.out.print( previousNextActions[i][j] + " , ");
					}
					System.out.println();
				}*/

				Instances data = new Instances(numAttributes,numInstances,header,attributeNames,attributeValues,previousNextActions); 

				data.setClassIndex(data.numAttributes() - 1);
				weka.filters.unsupervised.attribute.Remove filter = new weka.filters.unsupervised.attribute.Remove();
				filter.setAttributeIndices("" + (data.classIndex() + 1));
				filter.setInputFormat(data);
				Instances dataClusterer = Filter.useFilter(data, filter);
				EM clusterer = new EM();
				clusterer.setOptions(options);     // set the options
				clusterer.modifiedBuildClusterer(dataClusterer, numberPreviousNextActions);
				//System.out.println(clusterer.toString());
				ClusterEvaluation eval = new ClusterEvaluation();
				eval.setClusterer(clusterer);
				eval.evaluateClusterer(data);
				clusterBelonging = eval.getClusterAction();
				
				int indexPosition = 0;
				int indexCluster = 0;

				//to take into account the cluster of the previous actions
				if (considerPreviousClusters == 1){
					for (int i = 0; i < clusterer.getNumClustersFinal(); i++){
						String tempNameCluster = name + "_" + i;
						tempNames.add(tempNameCluster);
					}
					
					for (int i = 0; i < tempInitialSequences.length; i++){
						//System.out.println("position List " + positionList[i].toString());
						//System.out.println("clusters " + clusterBelonging.toString());
						for (int j = 0; j < positionList[i].size(); j++){
							String strName = name + "_" + clusterBelonging.get(indexCluster);
							indexCluster = indexCluster + 2;
							tempInitialSequences[i].set(positionList[i].get(j), strName);
						}
					}
				}
								
				//System.out.println(eval.clusterResultsToString());

				//System.out.println("number of clusters " + clusterer.getNumClustersFinal());
				numClustersForActions.add(clusterer.getNumClustersFinal());
				//System.out.println(" cluster " + clusterBelonging.toString());
				
				return clusterBelonging;
				
			}
			
				public double [][] calculatePreviousNextActions (ArrayList<String> [] tempInitialSequences, String name, int numAttributes, int numInstances, ArrayList<String> names, ArrayList<Integer> [] positionList){
					
					double [][] previousNextActions = new double [numInstances][numAttributes];
					int index = 0;
					
					
					for (int i = 0; i < tempInitialSequences.length; i++){
						positionList[i] = new ArrayList<Integer>();
						int lengthSequence = tempInitialSequences[i].size();
						for (int j = 0; j < tempInitialSequences[i].size(); j++){
							if (tempInitialSequences[i].get(j).hashCode() == name.hashCode()){
								positionList[i].add(j);
								//System.out.println("   position of the same action " + j);
								previousNextActions [index] = previousNext(tempInitialSequences[i], j, lengthSequence, names.size(), numAttributes, names);
								/*for (int l = 0; l < previousNextActions[index].length; l++){
									System.out.print("     output " + previousNextActions[index][l] + " position " + j);
								}
								System.out.println();*/
								index++;
								// put each one twice because otherwise sometimes we don't have the minimum number of instances (9)
								previousNextActions [index] = previousNext(tempInitialSequences[i], j, lengthSequence, names.size(), numAttributes, names);
								index++;
							}
						}
					}					
					return previousNextActions;
				}
				
					public double [] previousNext (ArrayList<String> sequence, int position, int length, int numActions, int numAttributes, ArrayList<String> names){
						
						double[] previousNextActions = new double [numAttributes];
						int halfNumAttributes = numAttributes/2;
						int index = 0;
						
						//previous
						for (int i = 0; i < halfNumAttributes; i++){
							if (position < (halfNumAttributes-i)){
								previousNextActions[index] = 0;
							}
							else{
								previousNextActions[index] = getNumAttributeAction (sequence.get(position - (halfNumAttributes-i)),names);
							}
							index++;
						}
						
						//next
						for (int i = 0; i < halfNumAttributes; i++){
							if(position > (length - (i+1) - 1)){
								previousNextActions[index] = names.size()+1;
							}
							else{
								previousNextActions[index] = getNumAttributeAction (sequence.get(position + (i+1)),names);

							}
							index++;
						}
						
						//System.out.println();
						
						//last
						previousNextActions[numAttributes-1] = names.size()+1;
						
						/*
						//previous2
						if ((position == 0) || (position == 1)){
							previousNextActions[0] = 0;
						}
						else{
							previousNextActions[0] = getNumAttributeAction (sequence.get(position-2), names);
						}
						
						//previous1
						if (position == 0){
							previousNextActions[1] = 0;
						}
						else{
							previousNextActions[1] = getNumAttributeAction (sequence.get(position-1), names);
						}
						
						//next1
						if (position == length-1){
							previousNextActions[2] = names.size()+1; //0 for "start", then the names and then "end"
						}
						else{
							previousNextActions[2] = getNumAttributeAction (sequence.get(position+1), names);
						}
						
						//next2
						if ((position == length-2) || (position == length-1)){
							previousNextActions[3] = names.size()+1;                    
						}
						else{
							previousNextActions[3] = getNumAttributeAction (sequence.get(position+2),names);
						}
						
						//last
						previousNextActions[4] = names.size()+1;*/
						
						return previousNextActions;
						
					}
					
						public int getNumAttributeAction (String action, ArrayList<String> names){
							for (int i = 0; i < names.size(); i++){
								if (action.hashCode() == names.get(i).hashCode()){
									return i+1; //because 0 is always for "start"
								}
							}
							return -1;
							
						}
						
			public int getPositionActions (String action, ArrayList<String> names){
				
				for (int i = 0; i < names.size(); i++){
					if (action.hashCode() == names.get(i).hashCode()){
						return i;
					}
				}
				return -1;
				
			}
		
	public static int getNumDifferentActions (){
		return numDifferentActions;
	}
	
	public static String [] getNames (){
		String [] names = new String [namesDifferentActions.size()];
		
		for (int i = 0; i < namesDifferentActions.size(); i++){
			names[i] = namesDifferentActions.get(i);
		}
		
		return names;

	}
	
	public static int [] getClusters (){
		int [] clusters = new int [numClustersForActions.size()];
		
		
		for (int i = 0; i < numClustersForActions.size(); i++){
			clusters[i] = numClustersForActions.get(i);
		}
		
		return clusters;
	}
	
  public static void main(String[] args) throws Exception {
		  
  }
}
