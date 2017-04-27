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


public class LFPUBS {
	
	//GUI
	GraphicalUserInterface generalGUI = new GraphicalUserInterface();
	
	/*
	//simplePattern
	ArrayList<simplePattern> simplePatterns = new ArrayList<simplePattern>();
	
	//splitSequences
	int timeGap = 10 * 60 * 1000; //10 minutes in milisecond
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
	
	public void findOutFrequentSequences () throws IOException,ParseException, Exception {
		
	    Date d=new Date();
	    long s1,sTemp,s2;
	    d=new Date();
	    s1=d.getTime();
	    
	    generalGUI.run();
	    
	    /*
		simpleEvents = fileToSimpleEvent.parseIntoSimpleEvents("files\\all.data");
		allRawSequences = eventsToSequences.singleEventsToSequences(simpleEvents, timeGap, criticalEvents);
		//allFoundSequences = findSequences.findOutFrequentSequences(initialMinSup, longerMinSup, lengthSimilarity, allRawSequences);
		allFoundSequences = createArtificialSequences();
		numFrequentSequences = allFoundSequences.size();
		d=new Date();
		sTemp=d.getTime();
		System.out.println("Execution time of <find frequent sequences> " + (sTemp - s1) + "  miliseconds");
		
		for (int i = 0; i < numFrequentSequences; i++){
			//discover the topology
			topologyAllSequences.add(topology.defineTopologyOfSequence(allFoundSequences.get(i), allRawSequences,minimumAbsoluteOriginFrequency,minimumAbsoluteDestinyFrequency,minimumBalancedRelationFrequency,numberPreviousNextActions,howDefineCluster,considerPreviousClusters));
			//translate to simplePattern structure			
			ArrayList<simpleNode> tempNodes = new ArrayList<simpleNode>();
			for (int j = 0; j < topologyAllSequences.get(i).length; j++){
				tempNodes.add(new simpleNode(topologyAllSequences.get(i)[j]));
			}
			simplePatterns.add(new simplePattern(tempNodes));
			
			//discover the time relations
			//System.out.println("pattern " + i);
			for (int j = 0; j < topologyAllSequences.get(i).length; j++){
				//System.out.println("     action " + topologyAllSequences.get(i)[j].getNode());
				for (int k = 0; k < topologyAllSequences.get(i)[j].getPreviousActions().size(); k++){
					//System.out.println("                other action " + topologyAllSequences.get(i)[j].getPreviousActions().get(k));
					if (topologyAllSequences.get(i)[j].getPreviousActions().get(k).compareTo("start") != 0){
						ArrayList<Double> tempTimeDistances = simpleTimeRelation.getTimeDistances(topologyAllSequences.get(i)[j].getIndexSimpleEvents().get(k), topologyAllSequences.get(i)[j].getIndexSimpleEventsPrevious().get(k), simpleEvents);
						simplePatterns.get(i).getTopologyNodes().get(j).setParticularTimeRelations(simpleTimeRelation.findOutTimeRelations(tempTimeDistances,minimumFrequencyTimeRelation, deviationAverageTimeRelation));
					}
					else{
						//the previous action is "start"
						ArrayList<simpleTimeRelation> nullTimeRelation = new ArrayList<simpleTimeRelation>();
						simplePatterns.get(i).getTopologyNodes().get(j).setParticularTimeRelations(nullTimeRelation);
					}
				}
			}
			
			//discover the conditions
			
			// disjunction conditions
			//System.out.println("pattern " + i);
			for (int j = 0; j < topologyAllSequences.get(i).length; j++){
				ArrayList<Integer> listActions = new ArrayList<Integer>();
				System.out.println("     action " + topologyAllSequences.get(i)[j].getNode());
				for (int k = 0; k < topologyAllSequences.get(i)[j].getNextActions().size(); k++){
					System.out.println("        next actions " + simplePatterns.get(i).getTopologyNodes().get(j).getNextActions().get(k).toString() + " instances " + simplePatterns.get(i).getTopologyNodes().get(j).getIndexSimpleEventsOfNext().get(k).toString());
				}
				listActions = disjunctionConditions.needDisjunctionConditions(simplePatterns.get(i).getTopologyNodes().get(j),minimumDemandedFrequencyForConditions, minimumRelationPercentage);
				if (listActions.size() > 0){
					//find disjunction conditions
					//System.out.println("yes, it needs conditions, size" + listActions.toString());
					simplePatterns.get(i).getTopologyNodes().get(j).setDisjunctionConditions(disjunctionConditions.findOutDisjunctionConditions(simplePatterns.get(i).getTopologyNodes().get(j), simpleEvents, listActions));
					disjunctionConditions.findOutDisjunctionConditions(simplePatterns.get(i).getTopologyNodes().get(j), simpleEvents, listActions);
				}
			
			}
		}
		
		//createReport(allFoundSequences, simplePatterns, GUI, minimumFrequency);
		 */
		 

	}
	
	public static void createReport (ArrayList<simpleSequence> sequences, ArrayList<simplePattern> simplePatterns, GraphicalUserInterface GUI, int minimumFrequency){
		
		for (int i = 0; i < simpleEvent.getEventLabel().size(); i++){
			System.out.println("      " + simpleEvent.getEventLabel().get(i).getDevice() + ", " + simpleEvent.getEventLabel().get(i).getAction() + " -->" + i); 
		}
		
		for (int i = 0; i < sequences.size(); i++){
			System.out.println("Sequence " + i);
			System.out.println("	basic actions " + sequences.get(i).getSequence().toString());
			System.out.println("	basic instances " + sequences.get(i).getInstances().toString());
			System.out.println(" 	extra actions " + sequences.get(i).getLongExtraActions().toString());
			System.out.println("	extra instances " + sequences.get(i).getShortExtraInstances().toString());
			writeTopology(simplePatterns.get(i));
		}
		
	}
	
	public static void writeTopology (simplePattern oneSimplePattern){
		
		for (int index = 0; index < oneSimplePattern.getTopologyNodes().size(); index++){
			System.out.println("        action " + oneSimplePattern.getTopologyNodes().get(index).getNode() + " type of node " + oneSimplePattern.getTopologyNodes().get(index).getTypeNode() + " components " + oneSimplePattern.getTopologyNodes().get(index).getComponentsNode());
			for (int i = 0; i < oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().size(); i++){
				//writer.println("          previous action " + oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i) + " count " + oneSimplePattern.getTopologyNodes().get(index).getPreviousActionsFrequency().get(i) + " instances " + oneSimplePattern.getTopologyNodes().get(index).getIndexSimpleEvents().get(i).toString() + " and previous " + oneSimplePattern.getTopologyNodes().get(index).getIndexSimpleEventsPrevious().get(i).toString());
				System.out.println("          previous action " + oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i) + " count " + oneSimplePattern.getTopologyNodes().get(index).getPreviousActionsFrequency().get(i));
				if (oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i).compareTo("start") != 0){
					for (int j = 0; j < oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).size(); j++){
						System.out.println("               time relation " + j + ": " + oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).get(j).getSimpleTimeRelation() + " particular instances: " + oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).get(j).getInstances().toString());
					}
				}				
			}
			for (int i = 0; i < oneSimplePattern.getTopologyNodes().get(index).getNextActions().size(); i++){
				//writer.println("          next action " + oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i) + " count " + oneSimplePattern.getTopologyNodes().get(index).getNextActionsFrequency().get(i) + " instances " + oneSimplePattern.getTopologyNodes().get(index).getIndexSimpleEventsOfNext().get(i).toString() + " and next " + oneSimplePattern.getTopologyNodes().get(index).getIndexSimpleEventsNextNext().get(i).toString());
				System.out.println("          next action " + oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i) + " count " + oneSimplePattern.getTopologyNodes().get(index).getNextActionsFrequency().get(i));
				if (oneSimplePattern.getTopologyNodes().get(index).getDisjunctionConditions().size()!=0){
					writeConditions (oneSimplePattern.getTopologyNodes().get(index).getDisjunctionConditions().get(i));
				}
			}
			System.out.println();
		}
		
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
	
	public static void writeConditions (ArrayList<simpleCompleteCondition> conditions){
		
		for (int i = 0; i < conditions.size(); i++){
			System.out.println("                 condition " + i);
			System.out.print("                       ");
			for (int j = 0; j < conditions.get(i).getCompleteCondition().size(); j++){
				if (conditions.get(i).getCompleteCondition().get(j)._class.compareTo("numeric")==0){
					writeNumericCondition ((simpleBasicConditionNumeric)conditions.get(i).getCompleteCondition().get(j));
				}
				else{
					writeNominalCondition ((simpleBasicConditionNominal)conditions.get(i).getCompleteCondition().get(j));
				}
			}
			System.out.println(" THEN " + conditions.get(i).getConsequent()+ "   ORDER (" + conditions.get(i).getOrder() + ")");
		}
	}
	
		public static void writeNumericCondition (simpleBasicConditionNumeric basicCondition){
			
			System.out.print(basicCondition.name + " " + basicCondition.symbol + " " + basicCondition.value + "   ");
		}
		
		public static void writeNominalCondition (simpleBasicConditionNominal basicCondition){
			
			System.out.print(basicCondition.name + " " + basicCondition.symbol + " " + basicCondition.value + "   ");
		}
	
	public static void main (String[] argv) throws Exception {
		
		LFPUBS sequences = new LFPUBS ();
		sequences.findOutFrequentSequences();	
	}

}



