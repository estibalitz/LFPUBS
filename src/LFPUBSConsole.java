import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import conditions.findDisjunctionConditions;
import conditions.simpleBasicConditionNominal;
import conditions.simpleBasicConditionNumeric;
import conditions.simpleCompleteCondition;

import basic.simpleNode;
import basic.simplePattern;
import basic.DataStructure;

import frequentSequences.findFrequentSequences;
import frequentSequences.simpleSequence;

import splitSequences.fullSequences;
import splitSequences.parseFileIntoSimpleEvents;
import splitSequences.simpleEvent;
import splitSequences.splitSingleEventsIntoSequences;
import splitSequences.eventLabel;
import timeRelations.simpleTimeRelation;
import topology.defineTopology;
import topology.topologyStructure2;
import GUI.GraphicalUserInterface;


public class LFPUBSConsole {
	
	//GUI
	//GraphicalUserInterface generalGUI = new GraphicalUserInterface();
	
	/*
	//simplePattern
	ArrayList<simplePattern> simplePatterns = new ArrayList<simplePattern>();
	
	//splitSequences
	String nameFile = new String("..\\files\\test.data");
	String nameResultFile = new String ("..\\result\\result.txt");
	int sequencesVSOneToOne = 1; //0 --> One to One patterns
								 //1 --> Sequences
	int timeGap = 20 * 60 * 1000; //10 minutes in milisecond
	ArrayList<eventLabel> criticalEvents = new ArrayList<eventLabel>();
	//criticalEvents.add(new eventLabel ("outside","on"));
	//criticalEvents.add(new eventLabel ("bedroom","on"));
	parseFileIntoSimpleEvents fileToSimpleEvent = new parseFileIntoSimpleEvents();
	ArrayList<simpleEvent> simpleEvents = new ArrayList();
	splitSingleEventsIntoSequences eventsToSequences = new splitSingleEventsIntoSequences();
	ArrayList<fullSequences> allRawSequences = new ArrayList();
	
	//frequentSequences
	int initialMinSup = 80; //in percentage
	int longerMinSup = 80;
	double lengthSimilarity = 0.75;
	findFrequentSequences findSequences = new findFrequentSequences();
	ArrayList<simpleSequence> allFoundSequences = new ArrayList();
	
	//topology
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
	
	//TimeRelations
	int selectedAlgorithm = 0; //0 --> EM algorithm
							   //1 --> basic algorithm
	double minimumFrequencyTimeRelation = 0.5;
	double deviationAverageTimeRelation = 0.75;
	
	//condition
	findDisjunctionConditions disjunctionConditions = new findDisjunctionConditions();
	int minimumDemandedFrequencyForConditions = 4; //it must be > 1
	int minimumRelationPercentage = 60;
	
	//GUI
	GraphicalUserInterface GUI = new GraphicalUserInterface();
	int minimumFrequency = 4;
	*/
	
	public void findOutFrequentSequences (String nameFile,String nameResultFile,int sequencesVSOneToOne, int timeGap, ArrayList<eventLabel> criticalEvents, int initialMinSup, int longerMinSup, double lengthSimilarity, double minimumAbsoluteOriginFrequency, double minimumAbsoluteDestinyFrequency, double minimumBalancedRelationFrequency, int numberPreviousNextActions, int howDefineCluster, int considerPreviousClusters, int selectedAlgorithm, double minimumFrequencyTimeRelation, double deviationAverageTimeRelation, int minimumDemandedFrequencyForConditions, int minimumRelationPercentage) throws IOException,ParseException, Exception {
		
	    Date d=new Date();
	    long s1,sTemp,s2;
	    d=new Date();
	    s1=d.getTime();
	    
	    //generalGUI.run();
	          	    
	    setParametersIntoDataStructure(nameFile,nameResultFile,sequencesVSOneToOne,timeGap,criticalEvents, initialMinSup, longerMinSup, lengthSimilarity, minimumAbsoluteOriginFrequency, minimumAbsoluteDestinyFrequency, minimumBalancedRelationFrequency, numberPreviousNextActions, howDefineCluster, considerPreviousClusters, selectedAlgorithm, minimumFrequencyTimeRelation, deviationAverageTimeRelation, minimumDemandedFrequencyForConditions, minimumRelationPercentage);
	    
	    
	    DataStructure.getInstance().getSplitSequencesDataStructure().setSimpleEvents(DataStructure.getInstance().getSplitSequencesDataStructure().getFileToSimpleEvent().parseIntoSimpleEvents(DataStructure.getInstance().getNameFile()));
	    if (DataStructure.getInstance().getSequencesVSOneToOne() == 0){
			DataStructure.getInstance().getSplitSequencesDataStructure().setAllRawSequences(DataStructure.getInstance().getSplitSequencesDataStructure().getEventsToSequences().singleEventsToSequencesOneToOne(DataStructure.getInstance().getSplitSequencesDataStructure().getSimpleEvents()));
		}
		else{
			DataStructure.getInstance().getSplitSequencesDataStructure().setAllRawSequences(DataStructure.getInstance().getSplitSequencesDataStructure().getEventsToSequences().singleEventsToSequencesSequences(DataStructure.getInstance().getSplitSequencesDataStructure().getSimpleEvents(), DataStructure.getInstance().getSplitSequencesDataStructure().getTimeGap(), DataStructure.getInstance().getSplitSequencesDataStructure().getCriticalEvents()));
		}
		DataStructure.getInstance().getFrequentSequencesDataStructure().setAllFoundSequences(DataStructure.getInstance().getFrequentSequencesDataStructure().getFindSequences().findOutFrequentSequences(initialMinSup, longerMinSup, lengthSimilarity, DataStructure.getInstance().getSplitSequencesDataStructure().getAllRawSequences()));
		//allFoundSequences = createArtificialSequences();
		//numFrequentSequences = DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size();
		d=new Date();
		sTemp=d.getTime();
		//System.out.println("Execution time of <find frequent sequences> " + (sTemp - s1) + "  miliseconds");
		
		for (int i = 0; i < DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size(); i++){
			//discover the topology
			DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().add(DataStructure.getInstance().getTopologyDataStructure().getTopology().defineTopologyOfSequence(DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().get(i), DataStructure.getInstance().getSplitSequencesDataStructure().getAllRawSequences(), DataStructure.getInstance().getTopologyDataStructure().getMinimumAbsoluteOriginFrequency(), DataStructure.getInstance().getTopologyDataStructure().getMinimumAbsoluteDestinyFrequency(), DataStructure.getInstance().getTopologyDataStructure().getMinimumBalancedRelationFrequency(), DataStructure.getInstance().getTopologyDataStructure().getNumberPreviousNextActions(), DataStructure.getInstance().getTopologyDataStructure().getHowDefineCluster(), DataStructure.getInstance().getTopologyDataStructure().getConsiderPreviousClusters()));
			//translate to simplePattern structure
			ArrayList<simpleNode> tempNodes = new ArrayList<simpleNode>();
			for (int j = 0; j < DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i).length; j++){
				tempNodes.add(new simpleNode(DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i)[j]));
			}
			double maximumValueFromStartToEnd = DataStructure.getInstance().getTopologyDataStructure().getTopology().getMaximumFrequencyFromStartToEnd(DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i));
			DataStructure.getInstance().getSimplePatterns().add(new simplePattern(tempNodes,maximumValueFromStartToEnd));
			
			//discover the time relations
			//System.out.println("pattern " + i);
			for (int j = 0; j < DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i).length; j++){
				//System.out.println("   activity " + j);
				for (int k = 0; k < DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i)[j].getPreviousActions().size(); k++){
					//System.out.println("      previous action " + k);
					if (DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i)[j].getPreviousActions().get(k).compareTo("start") != 0){
						ArrayList<Double> tempTimeDistances = DataStructure.getInstance().getTimeRelationsDataStructure().getSimpleTimeRelation().getTimeDistances(DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i)[j].getIndexSimpleEvents().get(k), DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i)[j].getIndexSimpleEventsPrevious().get(k), DataStructure.getInstance().getSplitSequencesDataStructure().getSimpleEvents());
						if (DataStructure.getInstance().getTimeRelationsDataStructure().getSelectedAlgorithm() == 0){ //EM algorithm
							DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j).setParticularTimeRelations(DataStructure.getInstance().getTimeRelationsDataStructure().getSimpleTimeRelation().findOutTimeRelationsEMAlgorithm(tempTimeDistances, DataStructure.getInstance().getTimeRelationsDataStructure().getMinimumFrequencyTimeRelation()));
							//System.out.println("       number of relations " + DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j).getTimeRelations().get(k).size());
						}
						else{ //basic algorithm
							DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j).setParticularTimeRelations(DataStructure.getInstance().getTimeRelationsDataStructure().getSimpleTimeRelation().findOutTimeRelationsBasicAlgorithm(tempTimeDistances, DataStructure.getInstance().getTimeRelationsDataStructure().getMinimumFrequencyTimeRelation(), DataStructure.getInstance().getTimeRelationsDataStructure().getDeviationAverageTimeRelation()));
						}
					}
					else{
						ArrayList<simpleTimeRelation> nullTimeRelation = new ArrayList<simpleTimeRelation>();
						DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j).setParticularTimeRelations(nullTimeRelation);
					}
				}
			}
			
			//discover the conditions
			
			// disjunction conditions
			//System.out.println("pattern " + i);
			for (int j = 0; j < DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i).length; j++){
				ArrayList<Integer> listActions = new ArrayList<Integer>();
				listActions = DataStructure.getInstance().getConditionsDataStructure().getDisjunctionConditions().needDisjunctionConditions(DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j), DataStructure.getInstance().getConditionsDataStructure().getMinimumDemandedFrequencyForConditions(), DataStructure.getInstance().getConditionsDataStructure().getMinimumRelationPercentage());
				if (listActions.size() > 0){
					try{
						DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j).setDisjunctionConditions(DataStructure.getInstance().getConditionsDataStructure().getDisjunctionConditions().findOutDisjunctionConditions(DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j), DataStructure.getInstance().getSplitSequencesDataStructure().getSimpleEvents(), listActions));
						DataStructure.getInstance().getConditionsDataStructure().getDisjunctionConditions().findOutDisjunctionConditions(DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j), DataStructure.getInstance().getSplitSequencesDataStructure().getSimpleEvents(), listActions);
					}
					catch (Exception exc){
						System.err.println("Caught Exception: " + exc.getMessage());
					}
				}
			}
		}
		
		createReport(DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences(), DataStructure.getInstance().getSimplePatterns());
		createReportLLFPUBS(DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences(), DataStructure.getInstance().getSimplePatterns());
	}
		
	public static void setParametersIntoDataStructure(String nameFile,String nameResultFile,int sequencesVSOneToOne, int timeGap, ArrayList<eventLabel> criticalEvents, int initialMinSup, int longerMinSup, double lengthSimilarity, double minimumAbsoluteOriginFrequency, double minimumAbsoluteDestinyFrequency, double minimumBalancedRelationFrequency, int numberPreviousNextActions, int howDefineCluster, int considerPreviousClusters, int selectedAlgorithm, double minimumFrequencyTimeRelation, double deviationAverageTimeRelation, int minimumDemandedFrequencyForConditions, int minimumRelationPercentage){
		DataStructure.getInstance().setNameFile(nameFile);
		DataStructure.getInstance().setNameResultFile(nameResultFile);
		DataStructure.getInstance().setSequencesVSOneToOne(sequencesVSOneToOne);
		DataStructure.getInstance().getSplitSequencesDataStructure().setTimeGap(timeGap);
		DataStructure.getInstance().getSplitSequencesDataStructure().setCriticalEvents(criticalEvents);
		DataStructure.getInstance().getFrequentSequencesDataStructure().setInitialMinSup(initialMinSup);
		DataStructure.getInstance().getFrequentSequencesDataStructure().setLongerMinSup(longerMinSup);
		DataStructure.getInstance().getFrequentSequencesDataStructure().setLengthSimilarity(lengthSimilarity);
		DataStructure.getInstance().getTopologyDataStructure().setMinimumAbsoluteOriginFrequency(minimumAbsoluteOriginFrequency);
		DataStructure.getInstance().getTopologyDataStructure().setMinimumAbsoluteDestinyFrequency(minimumAbsoluteDestinyFrequency);
		DataStructure.getInstance().getTopologyDataStructure().setMinimumBalancedRelationFrequency(minimumBalancedRelationFrequency);
		DataStructure.getInstance().getTopologyDataStructure().setNumberPreviousNextActions(numberPreviousNextActions);
		DataStructure.getInstance().getTopologyDataStructure().setHowDefineCluster(howDefineCluster);
		DataStructure.getInstance().getTopologyDataStructure().setConsiderPreviousClusters(considerPreviousClusters);
		DataStructure.getInstance().getTimeRelationsDataStructure().setSelctedAlgorithm(selectedAlgorithm);
		DataStructure.getInstance().getTimeRelationsDataStructure().setMinimumFrequencyTimeRelation(minimumFrequencyTimeRelation);
		DataStructure.getInstance().getTimeRelationsDataStructure().setDeviationAverageTimeRelation(deviationAverageTimeRelation);
		DataStructure.getInstance().getConditionsDataStructure().setMinimumDemandedFrequencyForConditions(minimumDemandedFrequencyForConditions);
		DataStructure.getInstance().getConditionsDataStructure().setMinimumRelationPercentage(minimumRelationPercentage);
		
	}
	
	public static void createReport (ArrayList<simpleSequence> sequences, ArrayList<simplePattern> simplePatterns){
		
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(DataStructure.getInstance().getResultNameFile()));
			PrintWriter writer = new PrintWriter(bw);
			
			writer.println();
			for (int i = 0; i < simpleEvent.getEventLabel().size(); i++){
				writer.println ("      " + simpleEvent.getEventLabel().get(i).getDevice() + ", " + simpleEvent.getEventLabel().get(i).getAction() + " -->" + i); 
			}
			
			writer.println();
			
			for (int i = 0; i < sequences.size(); i++){
				writer.println("Sequence " + i);
				writer.println("	Basic actions " + translateActionsToString(sequences.get(i).getSequence()).toString());
				writer.println("	Basic instances (" + sequences.get(i).getInstances().size() + "): " + sequences.get(i).getInstances().toString());
				writer.println(" 	Extra actions " + translateActionsToString(sequences.get(i).getLongExtraActions()).toString());
				writer.println("	Extra instances (" + sequences.get(i).getShortExtraInstances().size() + "): " +  sequences.get(i).getShortExtraInstances().toString());
				writeTopology(simplePatterns.get(i), writer);
			}
			writer.close();
			
		}
		catch(Exception error){
			
			System.out.println("Error Message: " + error.getMessage());
			
		}		
		}
		
		public static void writeTopology (simplePattern oneSimplePattern, PrintWriter writer){
			
			for (int index = 0; index < oneSimplePattern.getTopologyNodes().size(); index++){
				//writer.println("        action " + oneSimplePattern.getTopologyNodes().get(index).getNode() + " type of node " + oneSimplePattern.getTopologyNodes().get(index).getTypeNode() + " components " + oneSimplePattern.getTopologyNodes().get(index).getComponentsNode());
				writer.println("        Action: " + translateCompositeActionsToString(oneSimplePattern.getTopologyNodes().get(index).getNode()) + "; Type of node: " + oneSimplePattern.getTopologyNodes().get(index).getTypeNode() + "; Components " + translateComponentsToString(oneSimplePattern.getTopologyNodes().get(index).getComponentsNode()));
				for (int i = 0; i < oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().size(); i++){
					//writer.println("          previous action " + oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i) + " count " + oneSimplePattern.getTopologyNodes().get(index).getPreviousActionsFrequency().get(i) + " instances " + oneSimplePattern.getTopologyNodes().get(index).getIndexSimpleEvents().get(i).toString() + " and previous " + oneSimplePattern.getTopologyNodes().get(index).getIndexSimpleEventsPrevious().get(i).toString());
					writer.println("          Previous action " + translateCompositeActionsToString(oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i)) + " count " + oneSimplePattern.getTopologyNodes().get(index).getPreviousActionsFrequency().get(i));
					if (oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i).compareTo("start") != 0){
						for (int j = 0; j < oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).size(); j++){
							writer.println("               Time relation " + j + ": " + oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).get(j).getSimpleTimeRelation() + " Particular instances " + "(" + oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).get(j).getInstances().size() + "/" + oneSimplePattern.getTopologyNodes().get(index).getPreviousActionsFrequency().get(i) + ") : " + oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).get(j).getInstances().toString());
						}
					}				
				}
				for (int i = 0; i < oneSimplePattern.getTopologyNodes().get(index).getNextActions().size(); i++){
					//writer.println("          next action " + oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i) + " count " + oneSimplePattern.getTopologyNodes().get(index).getNextActionsFrequency().get(i) + " instances " + oneSimplePattern.getTopologyNodes().get(index).getIndexSimpleEventsOfNext().get(i).toString() + " and next " + oneSimplePattern.getTopologyNodes().get(index).getIndexSimpleEventsNextNext().get(i).toString());
					writer.println("          Next action " + translateCompositeActionsToString(oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i)) + " count " + oneSimplePattern.getTopologyNodes().get(index).getNextActionsFrequency().get(i));
					if (oneSimplePattern.getTopologyNodes().get(index).getDisjunctionConditions().size()!=0){
						writeConditions (oneSimplePattern.getTopologyNodes().get(index).getDisjunctionConditions().get(i), writer);
					}
				}
				writer.println();
			}
		}	
		
		public static void writeConditions (ArrayList<simpleCompleteCondition> conditions, PrintWriter writer){
			
			for (int i = 0; i < conditions.size(); i++){
				writer.println("                 Condition " + i);
				writer.print("                    ");
				for (int j = 0; j < conditions.get(i).getCompleteCondition().size(); j++){
					if (conditions.get(i).getCompleteCondition().get(j)._class.compareTo("numeric")==0){
						writeNumericCondition ((simpleBasicConditionNumeric)conditions.get(i).getCompleteCondition().get(j), writer);
					}
					else{
						writeNominalCondition ((simpleBasicConditionNominal)conditions.get(i).getCompleteCondition().get(j), writer);
					}
				}
				writer.println(" THEN " + translateCompositeActionsToString(conditions.get(i).getConsequent()) + "   ORDER (" + conditions.get(i).getOrder() + ")");
			}
		}
		
			public static void writeNumericCondition (simpleBasicConditionNumeric basicCondition, PrintWriter writer){
				
				writer.print(basicCondition.name + " " + basicCondition.symbol + " " + basicCondition.value + "   ");
			}
			
			public static void writeNominalCondition (simpleBasicConditionNominal basicCondition, PrintWriter writer){
				
				writer.print(basicCondition.name + " " + basicCondition.symbol + " " + basicCondition.value + "   ");
			}
			
		public static ArrayList<String> translateActionsToString (ArrayList<Integer> actionsInteger){
			
			ArrayList<String> actionsString = new ArrayList<String> ();
			
			for (int i = 0; i < actionsInteger.size(); i++){
				String device = simpleEvent.getEventLabel().get(actionsInteger.get(i)).getDevice();
				String action = simpleEvent.getEventLabel().get(actionsInteger.get(i)).getAction();
				String tempAction = device + " "+ action;
				actionsString.add(tempAction);
			}
			
			return actionsString;
			
		}
		
		public static String translateCompositeActionsToString (String compositeActionsString){
			String actionsString = new String ();
			
			if ((compositeActionsString.compareTo("start")== 0) || (compositeActionsString.compareTo("end")== 0)){
				return compositeActionsString;
			}
			
			int pos = compositeActionsString.indexOf("_",0);
			String tempAction = compositeActionsString.substring(0, pos);
			
			if (tempAction.compareTo("cluster") != 0){
				String device = simpleEvent.getEventLabel().get(Integer.parseInt(tempAction)).getDevice();
				String action = simpleEvent.getEventLabel().get(Integer.parseInt(tempAction)).getAction();
				actionsString = device + " "+ action;
			}
			else{
				actionsString = compositeActionsString;
			}
					
			return actionsString;		
		}
		
		public static ArrayList<String> translateComponentsToString (ArrayList<String> componentsString){
			
			ArrayList<String> stringsOfComponents = new ArrayList<String>();
			
			for (int i = 0; i < componentsString.size(); i++){
				String tempComponent = translateCompositeActionsToString(componentsString.get(i));
				if (isComponentWithinComponents(tempComponent, stringsOfComponents)){ //it exists previously
					
				}
				else{
					stringsOfComponents.add(tempComponent);
				}
				
			}
			
			return stringsOfComponents;
		}
		
			public static boolean isComponentWithinComponents(String tempComponent, ArrayList<String> stringsOfComponents){
				
				for (int i = 0; i < stringsOfComponents.size(); i++){
					if (tempComponent.compareTo(stringsOfComponents.get(i)) == 0){
						return true;
					}
				}
				
				return false;
				
			}
		
			private String findActionName(String name){
				String temp ="", last="";
				
				temp = name.substring(0, name.indexOf("_"));
				
				if (temp.compareTo("cluster") != 0){
					last = simpleEvent.getEventLabel().get(Integer.parseInt(temp)).getDevice() + " " + simpleEvent.getEventLabel().get(Integer.parseInt(temp)).getAction();
					return last;
				}
				
				
				return name;
			}
	
		public static void createReportLLFPUBS (ArrayList<simpleSequence> sequences, ArrayList<simplePattern> simplePatterns){
			
			try{
				BufferedWriter bw = new BufferedWriter(new FileWriter("result\\resultLLPUBS.txt"));
				PrintWriter writer = new PrintWriter(bw);
				
				
				writer.println();
				
				for (int i = 0; i < sequences.size(); i++){
					writer.println("Action Map " + i);
					writer.println();
					writer.println("(General Conditions)");
					writer.println();
					//writer.println("	Basic actions " + translateActionsToString(sequences.get(i).getSequence()).toString());
					//writer.println("	Basic instances (" + sequences.get(i).getInstances().size() + "): " + sequences.get(i).getInstances().toString());
					//writer.println(" 	Extra actions " + translateActionsToString(sequences.get(i).getLongExtraActions()).toString());
					//writer.println("	Extra instances (" + sequences.get(i).getShortExtraInstances().size() + "): " +  sequences.get(i).getShortExtraInstances().toString());
					writeTopologyLLFPUBS(simplePatterns.get(i), writer);
				}
				writer.close();
				
			}
			catch(Exception error){
				
				System.out.println("Error Message: " + error.getMessage());
				
			}		
		}
		
			public static void writeTopologyLLFPUBS (simplePattern oneSimplePattern, PrintWriter writer){
				
				int numActionPattern = 0;
				
				for (int index = 0; index < oneSimplePattern.getTopologyNodes().size(); index++){
					
					//writer.println("        Action: " + translateCompositeActionsToString(oneSimplePattern.getTopologyNodes().get(index).getNode()) + "; Type of node: " + oneSimplePattern.getTopologyNodes().get(index).getTypeNode() + "; Components " + translateComponentsToString(oneSimplePattern.getTopologyNodes().get(index).getComponentsNode()));
					for (int i = 0; i < oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().size(); i++){
						if (translateCompositeActionsToString(oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i)).compareTo("start") == 0){
							writer.println("(Action Pattern " + numActionPattern + ")");
							writer.println("ON occurs (start,--,t0) Frequency: " + oneSimplePattern.getTopologyNodes().get(index).getPreviousActionsFrequency().get(i));
							writer.println("IF context ()");
							writer.print("THEN do ");
							writeActionLLFPUBS (oneSimplePattern.getTopologyNodes().get(index), -1, writer);
							writer.println();
							numActionPattern++;
						}
						
						/*
						writer.println("          Previous action " + translateCompositeActionsToString(oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i)) + " count " + oneSimplePattern.getTopologyNodes().get(index).getPreviousActionsFrequency().get(i));
						if (oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i).compareTo("start") != 0){
							for (int j = 0; j < oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).size(); j++){
								writer.println("               Time relation " + j + ": " + oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).get(j).getSimpleTimeRelation() + " Particular instances " + "(" + oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).get(j).getInstances().size() + "/" + oneSimplePattern.getTopologyNodes().get(index).getPreviousActionsFrequency().get(i) + ") : " + oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).get(j).getInstances().toString());
							}
						}
						*/				
					}
					
					for (int i = 0; i < oneSimplePattern.getTopologyNodes().get(index).getNextActions().size(); i++){
						writer.println("(Action Pattern " + numActionPattern + ")");
						writer.print("ON occurs ");
						writeEventLLFPUBS(oneSimplePattern.getTopologyNodes().get(index), writer, oneSimplePattern.getTopologyNodes().get(index).getNextActionsFrequency().get(i));
						writer.print("IF context (");
						if (oneSimplePattern.getTopologyNodes().get(index).getDisjunctionConditions().size()!=0){
							writeConditionsLLFPUBS (oneSimplePattern.getTopologyNodes().get(index).getDisjunctionConditions().get(i), writer);
						}
						writer.println(")");
						writer.print("THEN do ");
						if (oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i).compareTo("end")==0){
							writer.print("(--,end,t) when --");
						}
						else{
							double timeRelation;
							timeRelation = findTimeRelations (oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i),oneSimplePattern.getTopologyNodes().get(index).getNode(), oneSimplePattern.getTopologyNodes());
							
							writeActionLLFPUBS (getTopologyNode(oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i), oneSimplePattern.getTopologyNodes()),timeRelation,writer);
						}
						/*
						writer.println("          Next action " + translateCompositeActionsToString(oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i)) + " count " + oneSimplePattern.getTopologyNodes().get(index).getNextActionsFrequency().get(i));
						if (oneSimplePattern.getTopologyNodes().get(index).getDisjunctionConditions().size()!=0){
							writeConditions (oneSimplePattern.getTopologyNodes().get(index).getDisjunctionConditions().get(i), writer);
						}
						*/
						writer.println();
						numActionPattern++;
					}
					writer.println();
				}
			}
			
				public static void writeActionLLFPUBS (simpleNode simpleNode, double timeRelation, PrintWriter writer){
					
					if (simpleNode.getTypeNode().compareTo("cluster") != 0){
						writer.print("(simple,(" + translateCompositeActionsToStringLLFPUBS(simpleNode.getNode()) + ") , t) when ");
					}
					else{
						writer.print("(unordered,(");
						for (int i = 0; i < simpleNode.getComponentsNode().size()-1; i++){
							writer.print("(" + translateCompositeActionsToStringLLFPUBS(simpleNode.getComponentsNode().get(i)) + ") & ");				
						}
						writer.print("(" + translateCompositeActionsToStringLLFPUBS(simpleNode.getComponentsNode().get(simpleNode.getComponentsNode().size() - 1)) + ")), t) when ");
					}
					
					if (timeRelation == -1){
						writer.print("--");
					}
					else if (timeRelation == -2){
						writer.print("t is after t0 ");
					}
					else{
						writer.print("t = t0 + " + timeRelation + " s.");
					}
					
					writer.println();		
				}
				
					public static String translateCompositeActionsToStringLLFPUBS (String compositeActionsString){
						String actionsString = new String ();
						
						if ((compositeActionsString.compareTo("start")== 0) || (compositeActionsString.compareTo("end")== 0)){
							return compositeActionsString;
						}
						
						int pos = compositeActionsString.indexOf("_",0);
						String tempAction = compositeActionsString.substring(0, pos);
						
						String device = simpleEvent.getEventLabel().get(Integer.parseInt(tempAction)).getDevice();
						String action = simpleEvent.getEventLabel().get(Integer.parseInt(tempAction)).getAction();
						actionsString = action + "," + device + " (" + compositeActionsString.substring(pos + 1, compositeActionsString.length()) + ")";
										
						return actionsString;		
					}
				
				public static void writeEventLLFPUBS (simpleNode simpleNode, PrintWriter writer, int frequency){
					
					if (simpleNode.getTypeNode().compareTo("cluster") != 0){
						writer.print("(simple,(" + translateCompositeActionsToStringLLFPUBS(simpleNode.getNode()) + ") , t0) Frequency: " + frequency);			
					}
					else{
						writer.print("(unordered,(");
						for (int i = 0; i < simpleNode.getComponentsNode().size()-1; i++){
							writer.print("(" + translateCompositeActionsToStringLLFPUBS(simpleNode.getComponentsNode().get(i)) + ") & ");				
						}
						writer.print("(" + translateCompositeActionsToStringLLFPUBS(simpleNode.getComponentsNode().get(simpleNode.getComponentsNode().size() - 1)) + ")), t0) Frequency: " + frequency);
					}
					writer.println();		
				}
				
				public static void writeConditionsLLFPUBS (ArrayList<simpleCompleteCondition> conditions, PrintWriter writer){
					
					for (int i = 0; i < conditions.size(); i++){
						writer.print("( ");
						for (int j = 0; j < conditions.get(i).getCompleteCondition().size(); j++){
							writer.print("(");
							if (conditions.get(i).getCompleteCondition().get(j)._class.compareTo("numeric")==0){
								writeNumericConditionLLFPUBS ((simpleBasicConditionNumeric)conditions.get(i).getCompleteCondition().get(j), writer);
							}
							else{
								writeNominalConditionLLFPUBS ((simpleBasicConditionNominal)conditions.get(i).getCompleteCondition().get(j), writer);
							}
							writer.print(")");
							if (j != conditions.get(i).getCompleteCondition().size()-1){
								writer.print(" AND ");
							}
						}
						writer.print(") (Priority: " + conditions.get(i).getOrder() + ")");
						if (i != conditions.size()-1){
							writer.print(" OR ");
						}
					}
				}
				
					public static void writeNumericConditionLLFPUBS (simpleBasicConditionNumeric basicCondition, PrintWriter writer){
						
						writer.print(basicCondition.name + "(" + basicCondition.symbol + "," + basicCondition.value + ")");
	
					}
					
					public static void writeNominalConditionLLFPUBS (simpleBasicConditionNominal basicCondition, PrintWriter writer){
						
						writer.print(basicCondition.name + "(" + basicCondition.symbol + "," + basicCondition.value + ")");
					}
					
				public static simpleNode getTopologyNode (String str, ArrayList<simpleNode> topologyNodes){
					
					for (int i = 0; i < topologyNodes.size(); i++){
						if (topologyNodes.get(i).getNode().compareTo(str) == 0){
							return topologyNodes.get(i);
						}
					}
					return null;
				}
					
				public static double findTimeRelations (String strNextAction, String strPreviousAction, ArrayList<simpleNode> topologyNodes){
					
					double tempTimeRelation;
					ArrayList<Double> tempTimeRelations = new ArrayList<Double> ();
					simpleNode tempNextAction = new simpleNode (getTopologyNode(strNextAction,topologyNodes));
					simpleNode tempPreviousAction = new simpleNode (getTopologyNode(strPreviousAction,topologyNodes));
					
					for (int i = 0; i < tempNextAction.getPreviousActions().size(); i++){
						if (tempNextAction.getPreviousActions().get(i).compareTo(tempPreviousAction.getNode()) == 0){
							for (int j = 0; j < tempNextAction.getTimeRelations().get(i).size(); j++){
								tempTimeRelations.add(tempNextAction.getTimeRelations().get(i).get(j).getSimpleTimeRelation());
							}
						}
					}
					
					if (tempTimeRelations.size() > 0){
						tempTimeRelation = tempTimeRelations.get(0);
					}
					else{
						tempTimeRelation = -2;
					}
					
					return tempTimeRelation;		
				}
	
	public static ArrayList<simpleSequence> createArtificialSequences(){
		ArrayList<simpleSequence> tempSequences = new ArrayList<simpleSequence>();
		simpleSequence tempSimpleSequence;
		
		int length = 19;
		int counter = 24;
		ArrayList<Integer> tempSequence = new ArrayList<Integer>();
		ArrayList<Integer> tempInstances = new ArrayList<Integer>();
		ArrayList<Integer> tempExtraActions = new ArrayList<Integer>();
		ArrayList<Integer> tempShorterInstances = new ArrayList<Integer>();
		
		tempSequence.add(0); tempSequence.add(1); tempSequence.add(2); tempSequence.add(3); tempSequence.add(4);
		tempSequence.add(5); tempSequence.add(7); tempSequence.add(8); tempSequence.add(9);	tempSequence.add(10); 
		tempSequence.add(11); tempSequence.add(12); tempSequence.add(13); tempSequence.add(15); tempSequence.add(17); 
		tempSequence.add(18); tempSequence.add(19); tempSequence.add(20); tempSequence.add(21);
		
		tempInstances.add(0); tempInstances.add(1); tempInstances.add(2); tempInstances.add(3); tempInstances.add(4);
		tempInstances.add(5); tempInstances.add(6); tempInstances.add(7); tempInstances.add(8); tempInstances.add(9);
		tempInstances.add(10); tempInstances.add(11); tempInstances.add(12); tempInstances.add(13); tempInstances.add(14);
		tempInstances.add(15); tempInstances.add(16); tempInstances.add(17); tempInstances.add(18); tempInstances.add(19);
		tempInstances.add(20); tempInstances.add(21); tempInstances.add(22); tempInstances.add(23);
		
		tempSimpleSequence = new simpleSequence(length, counter, tempSequence, tempInstances, tempExtraActions, tempShorterInstances);
		
		tempSequences.add(tempSimpleSequence);
		
		return tempSequences;

	}
		
	public static void main (String[] argv) throws Exception {
		
		String nameFile = new String (argv[0]);
		String nameResultFile = new String (argv[1]);
		int sequencesVSOneToOne = Integer.parseInt(argv[2]);
		int timeGap = Integer.parseInt(argv[3]);
		ArrayList<eventLabel> criticalEvents = setCriticalEvents(argv[4]);
		int initialMinSup = Integer.parseInt(argv[5]);
		int longerMinSup = Integer.parseInt(argv[6]);
		double lengthSimilarity = Double.parseDouble(argv[7]);
		double minimumAbsoluteOriginFrequency = Double.parseDouble(argv[8]);
		double minimumAbsoluteDestinyFrequency = Double.parseDouble(argv[9]);
		double minimumBalancedRelationFrequency = Double.parseDouble(argv[10]);
		int numberPreviousNextActions = Integer.parseInt(argv[11]);
		int howDefineCluster = Integer.parseInt(argv[12]);
		int considerPreviousClusters = Integer.parseInt(argv[13]);
		int selectedAlgorithm = Integer.parseInt(argv[14]);
		double minimumFrequencyTimeRelation = Double.parseDouble(argv[15]);
		double deviationAverageTimeRelation = Double.parseDouble(argv[16]);
		int minimumDemandedFrequencyForConditions = Integer.parseInt(argv[17]);
		int minimumRelationPercentage = Integer.parseInt(argv[18]);
		
		LFPUBSConsole sequences = new LFPUBSConsole ();
		sequences.findOutFrequentSequences(nameFile,nameResultFile,sequencesVSOneToOne,timeGap,criticalEvents, initialMinSup, longerMinSup, lengthSimilarity, minimumAbsoluteOriginFrequency, minimumAbsoluteDestinyFrequency, minimumBalancedRelationFrequency, numberPreviousNextActions, howDefineCluster, considerPreviousClusters, selectedAlgorithm, minimumFrequencyTimeRelation, deviationAverageTimeRelation, minimumDemandedFrequencyForConditions, minimumRelationPercentage);	
	}
	
	public static ArrayList<eventLabel> setCriticalEvents (String str){
		ArrayList<eventLabel> criticalEvents = new ArrayList<eventLabel>();
		
		if (str.compareTo("-")==0){
		}
		else{
			int pos = 0;
			int fin = 0;
			int temp = 0;
			fin = str.indexOf(";",0);
			while (fin != str.length()-1){
				temp = str.indexOf(",",pos);
				criticalEvents.add(new eventLabel(str.substring(pos, temp),str.substring(temp + 1, fin)));
				pos = fin + 1;
				fin = str.indexOf(";",fin + 1);
			}
			temp = str.indexOf(",",pos);
			criticalEvents.add(new eventLabel(str.substring(pos,temp),str.substring(temp+1,str.length()-1)));			
		}
		
		/*for (int i = 0; i < criticalEvents.size(); i++){
			System.out.println(criticalEvents.get(i).getDevice() + " " + criticalEvents.get(i).getAction());
		}*/
		
		return criticalEvents;
	}

}




