package frequentSequences;
/* -------------------------------------------------------------------------- */
/*                                                                            */
/*                     TFP ARM ALGORITHM GUI CONTROL CLASS                    */
/*                               Frans Coenen                                 */
/*                            Sunday 27 May 2006                              */
/*                                                                            */
/* -------------------------------------------------------------------------- */

/* LUCS-KDD Total From Partial (TFP) ARM algorithm demonstrator. */

import java.awt.*;
import java.awt.event.*;
//import javax.swing.*;
import java.io.*;
import java.util.*;

public class AprioriTFPcontrol {

    /* ------ FIELDS ------ */

    // CONSTANTS
    /** Minimum threshold value. */
    final static double MIN_THOLD = 0.0;
    /** Minimum threshold value. */
    final static double MAX_THOLD = 100.0;
    /** Maximum number of T-tree nodes that can be "drawn". */
    final static int MAX_NODES_IN_TREE_GRAPH = 1000;

    // OBJECTS
    /** Instance of Total Support Tree. */
    private TotalSupportTree newAprioriT = null;
    /** Instance of Partial Support Tree. */
    private PartialSupportTree newAprioriTFP = null;
    /** Instance of FPTree. */
    private FPtree newFPtree = null;

    
    
    // FLAGS
    /** Has support threshold flag. */
    private boolean hasSupportThold = false;
    /** Has confidence threshold flag. */
    private boolean hasConfThold = false;
    /** Has data flag. */
    private boolean hasDataFlag = false;
    /** Has P-tree flag. */
    private boolean hasPtree = false;
    /** Is sorted flag. */
    private boolean isSortedFlag = false;
    /** Has output schema flag. */
    private boolean hasOutputSchema = false;
    /** Has frequent sets flag. */
    private boolean hasFrequentSetsFlag = false;
    /** Has FP tree flag. */
    private boolean hasFPtreeFlag = false;
    /** Has ARs flag. */
    private boolean hasAssocRules = false;
    /** Using support-confidence frame work. */
    private boolean usingSCfWork = false;
    /** Using support-lift frame work. */
    private boolean usingSLfWork = false;

    // OTHER FIELDS
    /** Input data file name. */
    private File outputFileName;
    /** Flag indicating that P-tree statistics output is desired. */
    private boolean outputPtreeStatsFlag = false;
    /** Flag indicating that P-tree (as text) output is desired. */
    public boolean outputPtreeFlag = false;
    /** Flag indicating that P-tree grpah output is desired. */
    private boolean outputPtreeGraphFlag = false;

    /* --------------------------------------------------- */
    /*                                                     */
    /*                    CONSTRUCTORS                     */
    /*                                                     */
    /* --------------------------------------------------- */

    /** One argument constructor to create the TFP GUI.
    @param newnewAprioriT new isntance of <TT>TotalSupportTree</TT> class.  */

    public AprioriTFPcontrol(TotalSupportTree newNewAprioriT) {
        // Set fields
        newAprioriT = newNewAprioriT;
        
	}

    
    /* -------------------------------------- */
    /*                                        */
    /*              LOAD DATA                 */
    /*                                        */
    /* -------------------------------------- */

    /** Reads input data from file specified in command line argument and
    places data in inputDataArray.*/

    protected void loadData(short [][] data, int numActions, int numInstances) {
    	
    	newAprioriT.numCols = numActions;
    	newAprioriT.numRows = numInstances;
    	    	
    	newAprioriT.dataArray = new short [data.length][];
    	
    	for (int i = 0; i < data.length; i++){
    		newAprioriT.dataArray[i] = new short [data[i].length];
    		for (int j = 0; j < data[i].length; j++){
    			newAprioriT.dataArray[i][j] = Short.parseShort(Integer.toString(Integer.parseInt(Short.toString(data[i][j]))+1));
    		}
    	}
    	
    	hasDataFlag = true;
    	
    	//create testbed
    	// 1 2 3 6
    	// 1 4 5 7
    	// 1 3 4 6
    	// 1 2 6
    	// 1 2 3 4 5 7
    	/*newAprioriT.numCols = 7;
    	newAprioriT.numRows = 5;
    	
    	short tempData [][] = {{1,2,3,6},{1,4,5,7},{1,3,4,6},{1,2,6},{1,2,3,4,5,7}};
    	newAprioriT.dataArray = tempData;
    	hasDataFlag = true;*/
    	
        
    	/*for (int i = 0; i < newAprioriT.dataArray.length; i++){
    		for (int j = 0; j < newAprioriT.dataArray[i].length; j++){
    			System.out.print(newAprioriT.dataArray[i][j] + " ");
    		}
    		System.out.println();
    	}*/
    
        }
  
    /* ----------------------------------------------- */
    /*                                                 */
    /*         FP GROWTH (WITH T-TREE STORAGE)         */
    /*                                                 */
    /* ----------------------------------------------- */

    /** Comences FP Groeth algorithm. */

    protected void fpGrowth(int minSup) {
    	boolean okToProceed = true;
        newAprioriTFP       = null;
        hasPtree            = false;
        hasFPtreeFlag       = false;
        hasFrequentSetsFlag = false;

        // Check data
        if (!hasDataFlag) {
        	System.out.println("FP GROWTH ERROR: No data supplied");
            okToProceed = false;
            }
        
        newAprioriT.confidence = (double) minSup;
        newAprioriT.support = (double) minSup;
        newAprioriT.minSupport = (double) (newAprioriT.numRows * newAprioriT.support)/100;
        newAprioriT.numOneItemSets = newAprioriT.numCols;
        
        /*for (int i = 0; i < newAprioriT.dataArray.length; i++){
		for (int j = 0; j < newAprioriT.dataArray[i].length; j++){
			System.out.print(newAprioriT.dataArray[i][j] + " ");
		}
		System.out.println();
        }
        
        System.out.println("support " + newAprioriT.support);
    	System.out.println("confidence " + newAprioriT.confidence);
    	System.out.println("cols " + newAprioriT.numCols);
    	System.out.println("row " + newAprioriT.numRows);*/
                        
        // If OK to proceed apply FP Grpeth
        if (okToProceed) {
            // Start timer
            double time1 = (double) System.currentTimeMillis();    
            
            // Create instance of class FPTre
            newFPtree = new FPtree(newAprioriT);
          
            // Build initial FP-tree
            newFPtree.createFPtree();
            
            // Mine FP-tree
            newFPtree.startMining();
                        
            // Copy FPgrowth T-tree
            newAprioriT = newFPtree;
            int numFsets = newAprioriT.getNumFreqSets();
            
            // Set flags
            hasFPtreeFlag       = true;
            hasFrequentSetsFlag = true;
            }
        else {
            hasFrequentSetsFlag = false;
            }
        }

   
    /* ----------------------------------------------------- */
    /*                                                       */
    /*       OUTPUT FREQUENT SETS AS ATTRIBUTE NUMBERS       */
    /*                                                       */
    /* ----------------------------------------------------- */

    /** Outputs frequent sets using attribute number representation. */

    protected ArrayList<simpleSequence> outputFreqSetsAtts() {
    	ArrayList<simpleSequence> tempSetsOfActions = new ArrayList<simpleSequence>();
    		    
        // Check if ARM algorithm has been run.
        if (hasFrequentSetsFlag) newAprioriT.outputFrequentSets(tempSetsOfActions);
        else {
            System.out.println("There are no frequent sets");
            }
        
	    return tempSetsOfActions;
        }

   
    /* -------------------------------- */
    /*                                  */
    /*        OUTPUT UTILITIES          */
    /*                                  */
    /* -------------------------------- */

    /* TWO DECIMAL PLACES */

    /** Converts given real number to real number rounded up to two decimal
    places.
    @param number the given number.
    @return the number to two decimal places. */

    protected double twoDecPlaces(double number) {
    	int numInt = (int) ((number+0.005)*100.0);
	number = ((double) numInt)/100.0;
	return(number);
	}

	/* -------------------------------- */
	/*                                  */
	/*        MAXIMAL SEQUENCES         */
	/*                                  */
	/* -------------------------------- */

	protected ArrayList<simpleSequence> getMaximalSequences (ArrayList<simpleSequence> allSetsOfActions, int numInstances, int minSup, double lenSimilarity){
		ArrayList<simpleSequence> maximalSetsOfActions = new ArrayList<simpleSequence>();
		int [] maximal = new int[allSetsOfActions.size()];
		int numbersequences = allSetsOfActions.size();
		int numbermaximalsequences=0, indexmaximal=0;
		int maxlength = 0;
				
		for (int i = 0; i < allSetsOfActions.size(); i++){
			if (allSetsOfActions.get(i).getLength() > maxlength){
				maxlength = allSetsOfActions.get(i).getLength();
			}
		}
		
		int indexlength = maxlength;
		
		for (int i = 0; i < numbersequences; i++){
			maximal[i]=1;
		}
		
		for (int k = maxlength; k > 1; k--){ //k > 1 because we are looking for sequences with more actions than 1; if we are interested in sequences of only one action k > 0
			for (int i = 0; i < numbersequences; i++){
				if ((allSetsOfActions.get(i).length == k) && (maximal[i] !=0)){
					for (int j = 0; j < numbersequences; j++){
						if ((allSetsOfActions.get(j).length < allSetsOfActions.get(i).length) && (maximal[j]!= 0)){
							maximal[j] = checksequences (allSetsOfActions.get(i), allSetsOfActions.get(j), numInstances, minSup, lenSimilarity);
						}
					}
										
					if (maximal[i]==1){
						maximalSetsOfActions.add(new simpleSequence(allSetsOfActions.get(i)));
					}
				}
			}
		}
				
		return maximalSetsOfActions;
	}
	
//	-------------------------------------------------------------
//	Method Name: checksequences
//	Purpose    : check whether a sequence is into another one
//	Parameter  : two sequences
//	Return     : int value that indicates whether a sequence is into another one
//	-------------------------------------------------------------
	public int checksequences(simpleSequence slarge, simpleSequence sshort, int numInstances, int minSup, double lengthSimilarity) {

	int largeindex = 0, shortindex = 0;
	boolean shorton = true, found;

	/*System.out.print("slarge ");
	for (int i =0; i < slarge.sequence.length; i++){
		System.out.print (slarge.sequence[i] + " ");
	}
	System.out.println();*/


	/*System.out.print("sshort ");
	for (int i =0; i < sshort.sequence.length; i++){
		System.out.print (sshort.sequence[i] + " ");
	}
	System.out.println();*/
		
	while ((shortindex < sshort.length) && (shorton)){
		found = false;
		while ((!found)&&(largeindex < slarge.length)){
			if (sshort.sequence.get(shortindex)==slarge.sequence.get(largeindex)){
				found = true;
				//System.out.println ("found " + sshort.sequence[shortindex]);
			}
			else{
				largeindex++;
			}
		}
		if (found == false){
			//System.out.println("not found");
			shorton = false;
		}
		shortindex++;
		largeindex = 0;
	}
	
	
	if (shorton == true){
		if (((sshort.counter - slarge.counter) * 100)>=(minSup*numInstances)){//it is within the bigger sequence but (short counter - large counter) has enough instances to consider it as interesting
			//System.out.println("ooooo");
			return 1;
		}
		else{
			/*if ((sshort.length > (slarge.length * lengthSimilarity)) && (sshort.counter > slarge.counter)){ //in this case the instances of the short sequence (which are not included in the large sequence) will be included as well
				ArrayList<Integer> differentInstances = findDifferentInstances (slarge.instances,sshort.instances);
				for (int i = 0; i < differentInstances.size(); i++){
					if (findInstance(differentInstances.get(i),slarge.shortExtraInstances) == 0){
						slarge.shortExtraInstances.add(differentInstances.get(i));
					}
				}
				return 0; //short instances are added but it is not considered as frequent
			}
			else{
				return 0;
			}*/
			return 0;
		}
		
	}
	else{
		//System.out.println("not found return");
		return 1;
	}

	}
	
//	-------------------------------------------------------------
//	Method Name: findDifferentInstances(ArrayList<Integer>, ArrayList<Integer>)
//	Purpose    : find the extra instances contained by the shorter sequence
//	Parameters : two arraylist
//	Return     : arrayList which contains the extra instances
//	-------------------------------------------------------------
	public ArrayList<Integer> findDifferentInstances (ArrayList<Integer> largeSequence, ArrayList<Integer> shortSequence){

	ArrayList<Integer> differentInstances = new ArrayList<Integer>();
		
	for (int i = 0; i < shortSequence.size(); i++){
		if ((findInstance(shortSequence.get(i),largeSequence)) == 0){
			differentInstances.add(shortSequence.get(i));
		}
	}
		
	return differentInstances;
	}

//	-------------------------------------------------------------
//	Method Name: findInstance(int, ArrayList<Integer>)
//	Purpose    : find if an instance is within a set of instances
//	Parameters : instances, set of instances
//	Return     : it is within --> 1, it is not-->0
//	-------------------------------------------------------------
	private int findInstance (int instance, ArrayList<Integer> instances){

	for (int i = 0; i < instances.size(); i++){
		if (instance == instances.get(i)){
			return 1;
		}
	}
	return 0;
	}
	
//	-------------------------------------------------------------
//	Method Name: getInstances()
//	Purpose    : find the instances where the sequence is present
//	Parameters : sequence, initialInstances, lenSimilarity
//	Return     : the set of instances
//	-------------------------------------------------------------
	
	protected ArrayList<Integer> getInstances (simpleSequence sequence, ArrayList<Integer> [] initialInstances, double lenSimilarity){
		ArrayList<Integer> finalInstances = new ArrayList<Integer>();
		
		for (int i = 0; i < initialInstances.length; i++){
			int count = 0;
			for (int j = 0; j < sequence.getLength(); j++){
				if (initialInstances[i].get(sequence.getSequence().get(j)-1) == 1){
					count ++;
				}
			}
			if (count > (sequence.getLength() * lenSimilarity)){
				finalInstances.add(i);
			}
		}
		
		
		return finalInstances;
		
	}
	
}


