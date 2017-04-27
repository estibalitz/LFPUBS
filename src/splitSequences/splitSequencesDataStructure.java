package splitSequences;

import java.util.ArrayList;

public class splitSequencesDataStructure {
	
	//	splitSequences
	int timeGap = Integer.MAX_VALUE;
	ArrayList<eventLabel> criticalEvents = new ArrayList<eventLabel>();
	parseFileIntoSimpleEvents fileToSimpleEvent = new parseFileIntoSimpleEvents();
	ArrayList<simpleEvent> simpleEvents = new ArrayList();
	splitSingleEventsIntoSequences eventsToSequences = new splitSingleEventsIntoSequences();
	ArrayList<fullSequences> allRawSequences = new ArrayList();
	
	public splitSequencesDataStructure(){
		
	}
	
	public int getTimeGap (){
		return timeGap;
	}
	
	public void setTimeGap (int timeGap){
		this.timeGap = timeGap;
	}
	
	public ArrayList<eventLabel> getCriticalEvents (){
		return criticalEvents;
	}
	
	public void setCriticalEvents(ArrayList<eventLabel> criticalEvents){
		this.criticalEvents = criticalEvents;
	}
	
	public parseFileIntoSimpleEvents getFileToSimpleEvent (){
		return fileToSimpleEvent;
	}
	
	public void setFileToSimpleEvent (parseFileIntoSimpleEvents fileToSimpleEvent){
		this.fileToSimpleEvent = fileToSimpleEvent;
	}
	
	public ArrayList<simpleEvent> getSimpleEvents (){
		return simpleEvents;
	}
	
	public void setSimpleEvents (ArrayList<simpleEvent> simpleEvents){
		this.simpleEvents = simpleEvents;
	}
	
	public splitSingleEventsIntoSequences getEventsToSequences (){
		return eventsToSequences;
	}
	
	public void setEventsToSequences (splitSingleEventsIntoSequences eventsToSequences){
		this.eventsToSequences = eventsToSequences;
	}
	
	public ArrayList<fullSequences> getAllRawSequences(){
		return allRawSequences;
	}
	
	public void setAllRawSequences (ArrayList<fullSequences> allRawSequences){
		this.allRawSequences = allRawSequences;
	}

}
