package splitSequences;

import java.util.ArrayList;

public class splitSingleEventsIntoSequences {
	
	public splitSingleEventsIntoSequences(){
		
	}
	
	public ArrayList<fullSequences> singleEventsToSequencesSequences (ArrayList<simpleEvent> simpleEvents, int timeGap, ArrayList<eventLabel> criticalEvents){
		
		ArrayList<fullSequences> tempFullSequences = new ArrayList<fullSequences>();
		
		int begin = 0, end = 0;
		//int timeGap = 10 * 60 * 1000; //10 minutes in milisecond
		int totalSequences = 0;
		
		//ArrayList<eventLabel> criticalEvents = new ArrayList<eventLabel>();
		//criticalEvents.add(new eventLabel ("outside","on"));
		//criticalEvents.add(new eventLabel ("bedroom","on"));
		
			
		for (int i = 0; i < simpleEvents.size(); i++){
			if (i != simpleEvents.size()-1){
				int nextEventIndex = i + 1;
				if ((isCritical(simpleEvents.get(i).getEvent(),criticalEvents)==1) ||((simpleEvents.get(i).getTime().getTimeInMillis() + timeGap) < simpleEvents.get(nextEventIndex).getTime().getTimeInMillis())){
				//if((simpleEvents.get(i).getTime().getTimeInMillis() + timeGap) < simpleEvents.get(nextEventIndex).getTime().getTimeInMillis()){
					tempFullSequences.add(new fullSequences(totalSequences));
					end = i;
					for (int j = begin; j <= end; j++){
						tempFullSequences.get(totalSequences).eventsOfSequence.add(new simpleEvent(simpleEvents.get(j)));
						//System.out.println(simpleEvents.get(j).getDevice());
					}
					//System.out.println();
					begin = end + 1;
					totalSequences++;
				}
			}
		}
		
		//to create the last sequence
		if (end != simpleEvents.size()-1){
			tempFullSequences.add(new fullSequences(totalSequences));
			for (int j = begin; j < simpleEvents.size(); j++){
				tempFullSequences.get(totalSequences).eventsOfSequence.add(new simpleEvent(simpleEvents.get(j)));
			}
		}
		//System.out.println("number of sequences " + tempFullSequences.size());
		
		return tempFullSequences;
		
	}
	
public ArrayList<fullSequences> singleEventsToSequencesOneToOne (ArrayList<simpleEvent> simpleEvents){
		
		ArrayList<fullSequences> tempFullSequences = new ArrayList<fullSequences>();
		int totalSequences = 0;
		
		/*int begin = 0, end = 0;
		int timeGap = 10 * 60 * 1000; //15 minutes hours in milisecond
		int totalSequences = 0;*/
		
		//ArrayList<eventLabel> criticalEvents = new ArrayList<eventLabel>();
		//criticalEvents.add(new eventLabel ("outside","on"));
		//criticalEvents.add(new eventLabel ("bedroom","on"));
		
		/*for (int i = 0; i < simpleEvents.size(); i++){
			if (i != simpleEvents.size()-1){
				int nextEventIndex = i + 1;
				//if ((isCritical(simpleEvents.get(i).getEvent(),criticalEvents)==1) && ((simpleEvents.get(i).getTime().getTimeInMillis() + timeGap) < simpleEvents.get(nextEventIndex).getTime().getTimeInMillis())){
				if((simpleEvents.get(i).getTime().getTimeInMillis() + timeGap) < simpleEvents.get(nextEventIndex).getTime().getTimeInMillis()){
					tempFullSequences.add(new fullSequences(totalSequences));
					end = i;
					for (int j = begin; j <= end; j++){
						tempFullSequences.get(totalSequences).eventsOfSequence.add(new simpleEvent(simpleEvents.get(j)));
						//System.out.println(simpleEvents.get(j).getDevice());
					}
					//System.out.println();
					begin = end + 1;
					totalSequences++;
				}
			}
		}*/
		
		for (int i = 0; i < simpleEvents.size()-1; i++){
			
			ArrayList<simpleEvent> tempEvents = new ArrayList<simpleEvent>();
			tempEvents.add(simpleEvents.get(i));
			tempEvents.add(simpleEvents.get(i+1));
			
			tempFullSequences.add(new fullSequences(totalSequences,tempEvents));
			totalSequences++;
		}
		
		//to create the last sequence
		/*if (end != simpleEvents.size()-1){
			tempFullSequences.add(new fullSequences(totalSequences));
			for (int j = begin; j < simpleEvents.size(); j++){
				tempFullSequences.get(totalSequences).eventsOfSequence.add(new simpleEvent(simpleEvents.get(j)));
			}
		}*/
		
		//System.out.println("number of sequences " + tempFullSequences.size());
		
		return tempFullSequences;
		
	}
	
	public int isCritical (int action, ArrayList<eventLabel> criticalEvents){
		
		ArrayList<Integer> criticalEventsIndex = new ArrayList<Integer>();
		
		for (int i = 0; i < criticalEvents.size(); i++){
			criticalEventsIndex.add(findIndexOfEvent(criticalEvents.get(i).device,criticalEvents.get(i).action,simpleEvent.getEventLabel()));
		}
		
		for (int i = 0; i < criticalEventsIndex.size(); i++){
			if (action == criticalEventsIndex.get(i)){
				return 1;
			}
		}
		
		return -1;	
		
	}
	
		public int findIndexOfEvent (String device, String action, ArrayList<eventLabel> labeledEvents){
			for (int i = 0; i < labeledEvents.size(); i++){
				if ((device.compareTo(labeledEvents.get(i).getDevice()) == 0) && (action.compareTo(labeledEvents.get(i).getAction()) == 0)){
					return i;
				}
			}
			return -1;
		}
}
