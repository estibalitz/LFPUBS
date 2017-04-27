package splitSequences;

import java.util.ArrayList;

public class fullSequences {
	
	static int totalSequences = 0;
	int indexFullSequence;
	ArrayList<simpleEvent> eventsOfSequence = new ArrayList();
	
	public fullSequences (int index, ArrayList<simpleEvent> sequence){
		this.indexFullSequence = index;
		this.eventsOfSequence = sequence;
	}
	
	public fullSequences (int index){
		this.indexFullSequence = index;
	}
	
	public int getIndex (){
		return this.indexFullSequence;
	}
	
	public ArrayList<simpleEvent> getEventsOfSequence (){
		return this.eventsOfSequence;
	}

}
