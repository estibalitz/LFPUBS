package splitSequences;

import java.util.Calendar;
import java.util.ArrayList;

public class simpleEvent {
	
	protected int indexSimpleEvent;
	protected Calendar time;
	protected int event;
	protected int value;
	protected static ArrayList<eventLabel> labeledEvents = new ArrayList();
	protected static int indexLabeledEvent = 0;
	
	public simpleEvent(int indexSimpleEvent, Calendar time, int event, int value){
		this.indexSimpleEvent = indexSimpleEvent;
		this.time = time;
		this.event = event;
		this.value = value;				
	}
	
	public simpleEvent(simpleEvent s){
		this.indexSimpleEvent = s.indexSimpleEvent;
		this.time = s.time;
		this.event = s.event;
		this.value = s.value;
	}
	
	public Calendar getTime (){
		return this.time;
	}
	
	public int getEvent (){
		return this.event;
	}
	
	public String getDevice (){
		return this.labeledEvents.get(this.event).getDevice();
	}
	
	public String getAction (){
		return this.labeledEvents.get(this.event).getAction();
	}
	
	public int getValue (){
		return this.value;
	}
	
	public int getIndexSimpleEvent (){
		return this.indexSimpleEvent;
	}
	
	public static ArrayList<eventLabel> getEventLabel (){
		return labeledEvents;
	}
	
	public static int getIndexLabeledEvent(){
		return indexLabeledEvent;
	}
	
	public static void main (String[] argv) throws Exception {
		
	} 
	

}
