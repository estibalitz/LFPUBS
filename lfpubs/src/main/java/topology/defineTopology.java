package topology;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

import splitSequences.fullSequences;
import frequentSequences.simpleSequence;
import basic.DataStructure;

public class defineTopology {
	
	static double minimumAbsoluteOriginFrequency;
	static double minimumAbsoluteDestinyFrequency;
	static double minimumBalancedRelationFrequency;
	int numSequences;
	
	
	
	public defineTopology(){
		
	}
	
	public topologyStructure2[] defineTopologyOfSequence(simpleSequence sequence, ArrayList<fullSequences> allRawSequences, double minimumAbsoluteOriginFrequency,double minimumAbsoluteDestinyFrequency,double minimumBalancedRelationFrequency,int numberPreviousNextActions, int howDefineCluster, int considerPreviousClusters) throws Exception {
		
		int numSequences = sequence.getInstances().size() + sequence.getShortExtraInstances().size();
		ArrayList<Integer> [] initialSequences;
		ArrayList<Integer> [] initialSequencesIndexSimpleEvents = new ArrayList[numSequences];
		ArrayList<String> [] initialStringSequences;
		this.minimumAbsoluteOriginFrequency = minimumAbsoluteOriginFrequency;
		this.minimumAbsoluteDestinyFrequency = minimumAbsoluteDestinyFrequency;
		this.minimumBalancedRelationFrequency = minimumBalancedRelationFrequency;
		this.numSequences = sequence.getLength();
		
		
		defineActionsClusters defineClusters = new defineActionsClusters ();
		clusteredSequence [] clusteredSequences = new clusteredSequence[numSequences];
		topologyStructure1[] structure1;
		topologyStructure1_2[] structure1_2;
		topologyStructure2[] structure2;
		
		initialSequences = parseInitialSequences(sequence,allRawSequences,numSequences,initialSequencesIndexSimpleEvents);
		initialStringSequences = parseInitialSequencesIntoStrings(initialSequences);
		
		//visualize the frequent actions in different instances
		/*for (int i = 0; i < initialStringSequences.length; i++){
			System.out.println(" initial sequence " + i);
			for (int j = 0; j < initialStringSequences[i].size(); j++){
				System.out.print(initialStringSequences[i].get(j) + " (" + initialSequencesIndexSimpleEvents[i].get(j) + ") ");
			}
			System.out.println();
		}*/
		
		clusteredSequences = defineClusters.setActionsClusters(initialStringSequences, numberPreviousNextActions, howDefineCluster, considerPreviousClusters);
		
		//visualize the frequent actions in different instances
		/*for (int i = 0; i < initialStringSequences.length; i++){
			System.out.println(" initial sequence " + i);
			for (int j = 0; j < initialStringSequences[i].size(); j++){
				System.out.print(clusteredSequences[i].getSimpleSequence().get(j)+ "_" + clusteredSequences[i].getSimpleSequenceCluster().get(j) + " (" + initialSequencesIndexSimpleEvents[i].get(j) + ") ");
			}
			System.out.println();
		}*/
		
		topologyStructure1.setInitialTopologyStructure1(clusteredSequences);
		structure1 = topologyStructure1.setTopologyStructure1(clusteredSequences,initialSequencesIndexSimpleEvents);
		structure1_2 = topologyStructure1_2.setTopologyStructure1_2(structure1);
		structure2 = topologyStructure2.setTopologyStructure2(structure1,structure1_2);
		
		/*System.out.println("print ");
		for (int i = 0; i < structure2.length; i++){
			System.out.println("node " + structure2[i].getNode());
			for (int j = 0; j < structure2[i].getNextActions().size(); j++){
				System.out.println("next " + structure2[i].getNextActions().get(j) + " frequency " + structure2[i].getNextActionsFrequency().get(j));
			}
		}*/
		
		return structure2;
		
	}
	
	public double getMaximumFrequencyFromStartToEnd (topologyStructure2[] tempStructure2){
				
		//find the maximum frequency which allows to get "end" from "start"
		ArrayList<Node> nodes = new ArrayList<Node>();
		ArrayList<nextNode> nextNodes = new ArrayList<nextNode>();
		
		//the "start" node
		for (int i = 0; i < tempStructure2.length; i++){
			for (int j = 0; j < tempStructure2[i].getPreviousActions().size(); j++){
				if (tempStructure2[i].getPreviousActions().get(j).compareTo("start")==0){
					nextNodes.add(new nextNode(tempStructure2[i].getNode(),tempStructure2[i].getPreviousActionsFrequency().get(j)));
				}
			}
		}
		nodes.add(new Node("start",nextNodes));
		
		//the rest of the nodes			
		for (int i = 0; i < tempStructure2.length; i++){
			nextNodes = new ArrayList<nextNode>();
			for (int j = 0; j < tempStructure2[i].getNextActions().size(); j++){
				nextNodes.add(new nextNode(tempStructure2[i].getNextActions().get(j), tempStructure2[i].getNextActionsFrequency().get(j)));
			}
			nodes.add(new Node(tempStructure2[i].getNode(), nextNodes));
		}
			
		/*for (int i = 0; i < nodes.size(); i++){
			System.out.println("Node: " + nodes.get(i).getName());
			for (int j = 0; j < nodes.get(i).getNextNodes().size(); j++){
				System.out.println("  NextNode: " + nodes.get(i).getNextNodes().get(j).getName() + ", Frequency: " + nodes.get(i).getNextNodes().get(j).getFrequency());
			}
		}*/
		
		
		return calc_best_astar(nodes);
				
	}
	
	public static double calc_best_astar (ArrayList<Node> nodes){
		
		
		// generate the list of weights from nodes
		TreeSet<Integer> node_values = new TreeSet<Integer>();
		
		for (int i = 0; i < nodes.size(); i++){
			//System.out.println("Node: " + nodes.get(i).getName());
			for (int j = 0; j < nodes.get(i).getNextNodes().size(); j++){
				node_values.add(nodes.get(i).getNextNodes().get(j).getFrequency());
			}
		}
		
		
		// Escribimos en forma descendente
		Iterator <Integer>i = node_values.descendingIterator();
		
		
		while(i.hasNext())
		{
			double weight = i.next();
	
			if(astar(nodes,weight))
			{
			    return weight;
			}
		}
		
		return 0.0;				
	}
	
		//////////////////////////////////////////////////////////////////
		//			 Astar algorithm for the graph taking into account the min weight
		//////////////////////////////////////////////////////////////////			/
		public static boolean astar(ArrayList<Node> nodes, double weight)
		{
			Nodeg node_start = new Nodeg("start",buscar_array_list("start",nodes),0.0);
			nextNode next_nodej = null;
			
			ArrayList<Nodeg> open = new ArrayList<Nodeg>();   // still to develop nodes
			ArrayList<Nodeg> closed = new ArrayList<Nodeg>(); // repeated nodes
			
			// create a node that contains a value g
			open.add(node_start); // add node start  into open queue
			
			MyCompare mc_g=new MyCompare();
			mc_g.setSortType(MyCompare.BY_G);
			
			while(!open.isEmpty())
			{
				// Sort the open list
				Collections.sort(open,mc_g);
				// extract the first node from open queue
				Nodeg node_open=open.remove(0);
				// put in into the closed queue
				closed.add(node_open);
				double g = node_open.getg();
				
				// get the connected nodes from the node
				for (int j = 0; j < node_open.getNextNodes().size(); j++){
					next_nodej=node_open.getNextNodes().get(j);
					
					if((next_nodej.getName().compareTo("end") == 0) && (next_nodej.getFrequency() >= weight))
					{
						return true;
					}
					
					double frequency_j = next_nodej.getFrequency();
					// Consider this node if its value is only >= weight
					if (frequency_j >= weight)
					{
						// from this node create a node looking into nodes 
						// to put it into the open list if not repeated
						double new_g = g + frequency_j;
						Nodeg nodeg_nodej = new Nodeg(next_nodej.getName(), 
								                      buscar_array_list(next_nodej.getName(),nodes),new_g);
						
						/* No funciona
						boolean cond_open =  open.contains(nodeg_nodej);
						boolean cond_closed = closed.contains(nodeg_nodej); */
						
						// Cheque a ver si nodeg_node_j esta en open
						boolean cond_open = esta_en_lista(nodeg_nodej,open);
						boolean cond_closed = esta_en_lista(nodeg_nodej,closed);
						
							
						if ((cond_open == false) && (cond_closed == false))
						{
							open.add(nodeg_nodej);
						}					
					}			
				}
			}
			
			return false;
		}
		
			///////////////////////////////////////////////////////////////////////////////
			public static boolean esta_en_lista(Nodeg nodeg_nodej,ArrayList<Nodeg>lista)
			{
				for (int i = 0; i < lista.size(); i++){
					if (lista.get(i).getName().compareTo(nodeg_nodej.getName()) == 0)return true;
				}
				return false;
			}
			///////////////////////////////////////////////////////////////////////////////
			public static ArrayList<nextNode> buscar_array_list(String name, ArrayList<Node> nodes)
			{
				for (int i = 0; i < nodes.size(); i++){
					if(nodes.get(i).getName().compareTo(name) == 0) 
						return nodes.get(i).getNextNodes();
				}
				
				return null;
			}
	
	public ArrayList<Integer>[] parseInitialSequences (simpleSequence sequence, ArrayList<fullSequences> allRawSequences, int numSequences, ArrayList<Integer>[] initialSequencesIndexSimpleEvents){
		
		ArrayList<Integer> [] initialSequences = new ArrayList[numSequences];
		
		for (int i = 0; i < sequence.getInstances().size(); i++){
			initialSequencesIndexSimpleEvents[i] = new ArrayList<Integer>();
			initialSequences[i] = parseSimpleInitialSequence(sequence, allRawSequences.get(sequence.getInstances().get(i)),initialSequencesIndexSimpleEvents[i]);
		}
		for (int i = sequence.getInstances().size(); i < numSequences; i++){
			initialSequencesIndexSimpleEvents[i] = new ArrayList<Integer>();
			initialSequences[i] = parseSimpleInitialSequence(sequence, allRawSequences.get(sequence.getShortExtraInstances().get(i-sequence.getInstances().size())),initialSequencesIndexSimpleEvents[i]);
		}
			
		return initialSequences;
		
	}
	
		public ArrayList<Integer> parseSimpleInitialSequence (simpleSequence sequence, fullSequences simpleEvents,ArrayList<Integer> initialSequencesIndexSimpleEvents){
			
			ArrayList<Integer> eventSequence = new ArrayList<Integer>();
			
			for (int i = 0; i < simpleEvents.getEventsOfSequence().size(); i++){
				if (isInterestingAction(simpleEvents.getEventsOfSequence().get(i).getEvent(), sequence) != -1){
					eventSequence.add(simpleEvents.getEventsOfSequence().get(i).getEvent());
					initialSequencesIndexSimpleEvents.add(simpleEvents.getEventsOfSequence().get(i).getIndexSimpleEvent());
				}
			}
			
			return eventSequence;
		}
		
			public int isInterestingAction (int action, simpleSequence sequence){
				for (int i = 0; i < sequence.getSequence().size(); i++){
					if (action == (sequence.getSequence().get(i))){ 
						return i;
					}
				}
				for (int i = 0; i < sequence.getLongExtraActions().size(); i++){
					if (action == (sequence.getLongExtraActions().get(i))){
						return i;
					}
				}
				return -1;
			}


	public ArrayList<String> [] parseInitialSequencesIntoStrings(ArrayList<Integer>[] initialSequences){
		
		ArrayList<String> [] tempInitialSequences = new ArrayList[initialSequences.length];
		
		for (int i = 0; i < initialSequences.length; i++){
			tempInitialSequences[i] = new ArrayList<String>();
			for (int j = 0; j < initialSequences[i].size(); j++){
				tempInitialSequences[i].add(Integer.toString(initialSequences[i].get(j)));
			}
		}		
		return tempInitialSequences;		
	}
	
	public void setNumSequences (int numSequences){
		this.numSequences = numSequences;
	}
	
	public int getNumSequences (){
		return this.numSequences;
	}
	
	public static double getminimumAbsoluteOriginFrequency (){
		return minimumAbsoluteOriginFrequency;
	}
	
	public static double getminimumAbsoluteDestinyFrequency (){
		return minimumAbsoluteDestinyFrequency;
	}
	
	public static double getminimumBalancedRelationFrequency (){
		return minimumBalancedRelationFrequency;
	}
}
