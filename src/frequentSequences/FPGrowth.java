package frequentSequences;



import java.util.ArrayList;

public class FPGrowth {
	
	public static void main(String[] args){

	}
	
	public static ArrayList<simpleSequence> findFrequentSetsOfActions (ArrayList<Integer> [] initialInstances, short[][] instances, int numActions, int numInstances, int minSup, double lenSimilarity){
		ArrayList<simpleSequence> allSetsOfActions = new ArrayList<simpleSequence>();
		ArrayList<simpleSequence> maximalSetsOfActions = new ArrayList<simpleSequence>();
		
		
		//FPGrowth
		PartialSupportTree newAprioriTFP = new PartialSupportTree();
		AprioriTFPcontrol newAprioriTFPcontrol = new AprioriTFPcontrol(newAprioriTFP);
		
		//load data and support and confidence
		newAprioriTFPcontrol.loadData(instances, numActions, numInstances);
		
		//run FPGrowth algorithm
		newAprioriTFPcontrol.fpGrowth(minSup);
		
		//output frequent sets attribute
		allSetsOfActions = newAprioriTFPcontrol.outputFreqSetsAtts();
		
		//visualize allSetsOfActions
		/*System.out.println("All sets of actions");
		for (int i = 0; i < allSetsOfActions.size(); i++){
			System.out.print("index " + i + " length " + allSetsOfActions.get(i).getLength() + " counter " + allSetsOfActions.get(i).getCounter() + " instances ");
			for (int j = 0; j < allSetsOfActions.get(i).getSequence().size(); j++){
				System.out.print(allSetsOfActions.get(i).getSequence().get(j) + " ");
			}
			System.out.println();
		}*/
		
		//get maximal sequences
		maximalSetsOfActions = newAprioriTFPcontrol.getMaximalSequences(allSetsOfActions, numInstances, minSup, lenSimilarity);
		
		//get instances of maximal sequences
		for (int i = 0; i < maximalSetsOfActions.size(); i++){
			maximalSetsOfActions.get(i).setInstances(newAprioriTFPcontrol.getInstances(maximalSetsOfActions.get(i), initialInstances, lenSimilarity));
		}
		
		//set the actions of the sequences correctly (action = action - 1)
		for (int i = 0; i < maximalSetsOfActions.size(); i++){
			for (int j = 0; j < maximalSetsOfActions.get(i).getSequence().size(); j++){
				maximalSetsOfActions.get(i).getSequence().set(j, maximalSetsOfActions.get(i).getSequence().get(j)-1);
			}
		}
				
		//visualize maximal
		/*System.out.println();
		System.out.println("Maximal sets of actions");
		for (int i = 0; i < maximalSetsOfActions.size(); i++){
			System.out.print("index " + i + " length " + maximalSetsOfActions.get(i).getLength() + " counter " + maximalSetsOfActions.get(i).getCounter() + " actions ");
			for (int j = 0; j < maximalSetsOfActions.get(i).getSequence().size(); j++){
				System.out.print(maximalSetsOfActions.get(i).getSequence().get(j) + " ");
			}
			System.out.print(" instances ");
			for (int j = 0; j < maximalSetsOfActions.get(i).getInstances().size(); j++){
				System.out.print(maximalSetsOfActions.get(i).getInstances().get(j) + " ");
			}
			System.out.println();
		}*/
		
		return maximalSetsOfActions;
		
	}

}
