package splitSequences;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class parseFileIntoSimpleEvents {
	
	
	
	public parseFileIntoSimpleEvents(){
		
	}
	
	public ArrayList<simpleEvent> parseIntoSimpleEvents (String file) throws IOException,ParseException {
		
		ArrayList<simpleEvent> tempSimpleEvents = new ArrayList();
		
		
		BufferedReader br = new BufferedReader(new FileReader(file));
  		String str = br.readLine();
  		int indexSimpleEvent = 0;
  		
  		while (str!=null){
	  		//System.out.println("sentence " + str);
	  		Calendar day = getDay(str);
			String device = getDevice(str);
			String action = getAction(str);
			int value = getValue(str);
			//System.out.println("time " + day.getTime() + " device " + device + " action " + action + " value " + value);
  			//check if the device+action is already in the eventLabel, if so create a new simpleEvent with that eventLabel, if not create a new simpleEvent and a new eventLabel
			int index = findDeviceAction (device, action, simpleEvent.getEventLabel());
			if (index != -1){
				tempSimpleEvents.add(new simpleEvent(indexSimpleEvent,day,index,value));
				indexSimpleEvent++;
			}
			else{
				simpleEvent.labeledEvents.add(new eventLabel(device,action));
				tempSimpleEvents.add(new simpleEvent(indexSimpleEvent,day,simpleEvent.getIndexLabeledEvent(),value));
				indexSimpleEvent++;
				simpleEvent.indexLabeledEvent++;
			}
			
			str = br.readLine();
  		}
		
		
		return tempSimpleEvents;		
	}
	
		public Calendar getDay(String str){
			int fin = str.indexOf(",",0);
			String day = str.substring(0,fin);
			try{
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				Date d = sdf.parse(day);
				Calendar cal = Calendar.getInstance();
				cal.setTime(d);
				return cal;
			}
			catch(ParseException e){
				return null;
			}
		}
		
		public String getDevice (String str){
			int pos = str.indexOf(",",0);
			int fin = str.indexOf(",",pos+1);
			String device = str.substring(pos+1,fin);
			
			return device;			
		}
		
		public String getAction (String str){
			int pos = str.indexOf(",",0);
			pos = str.indexOf(",", pos+1);
			int fin = str.indexOf(",",pos+1);
			String action = str.substring(pos+1,fin);
			
			return action;
		}
		
		public int getValue (String str){
			int pos = str.indexOf(",",0);
			pos = str.indexOf(",", pos+1);
			pos = str.indexOf(",", pos+1);
			int value = Integer.parseInt(str.substring(pos+1,str.length()));
			
			return value; 
		}

		public int findDeviceAction (String device, String action, ArrayList<eventLabel> labeledEvents){
			
			for (int i = 0; i < labeledEvents.size(); i++){
				if ((device.compareTo(labeledEvents.get(i).getDevice()) == 0) && (action.compareTo(labeledEvents.get(i).getAction()) == 0)){
					return i;
				}
			}
			return -1;
		}
		
		public static ArrayList<Double> getTimeOfSimpleEvent (ArrayList<Integer> index, ArrayList<simpleEvent> events){
			ArrayList<Double> tempTimes = new ArrayList<Double>();
			
			for (int i = 0; i < index.size(); i++){
				tempTimes.add((double)(events.get(index.get(i)).getTime().getTimeInMillis()/1000)); //in seconds
			}
			
			return tempTimes;
		}
}
