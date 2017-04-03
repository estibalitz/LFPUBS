package timeRelations;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.joda.time.*;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeFieldType;
import org.apache.commons.math3.stat.descriptive.*;

import splitSequences.fullSequences;
import splitSequences.*;
import frequentSequences.simpleSequence;
import splitSequences.simpleEvent;

public class findTimeActions {
	public findTimeActions(){

}
//for each action find duration between the device being activated
public  HashMap<String, String> findtimeActions(ArrayList<fullSequences>sequence){
	ArrayList<String>Duration=new ArrayList<String>();
	long secondsAction=0;
	ArrayList<Double>Mean=new ArrayList<Double>();
	ArrayList<String>ActionDevices=new ArrayList<String>();
	
	try{
	for(int i=0;i<simpleEvent.getEventLabel().size();i++){
		String Action= simpleEvent.getEventLabel().get(i).getAction();
		String Device=simpleEvent.getEventLabel().get(i).getDevice();
		DescriptiveStatistics stats= new DescriptiveStatistics();
		for (int j=0; j<sequence.size();j++){
			for(int k=0; k<sequence.get(j).getEventsOfSequence().size();k++){
				if((Action==sequence.get(j).getEventsOfSequence().get(k).getAction())&& (Device==sequence.get(j).getEventsOfSequence().get(k).getDevice())){
					secondsAction=0;
					DateTime dt=new DateTime(sequence.get(j).getEventsOfSequence().get(k).getTime());
					secondsAction=Integer.valueOf(dt.getSecondOfDay());
					stats.addValue(secondsAction);
					//System.out.println(secondsAction);
					
					
				}
			}
		}
		Mean.add(stats.getMean());
		ActionDevices.add(Device);
		}
	}
	catch(Exception error){
		System.out.println("Error Message: " + error.getMessage());
	}
		
	
	
	//Save duration of each device with its name in an ArrayList
	
	
	HashMap<String,String>Average=new HashMap<String,String>();
		
		for(int i=0; i<ActionDevices.size()-1;i++){
			for(int j=i+1;j<ActionDevices.size();j++){
				String Device=ActionDevices.get(i);
				if(Device.compareTo(ActionDevices.get(j))==0){
					String average=String.valueOf((int) Math.round(Math.abs(Mean.get(j)-Mean.get(i))));
					Average.put(Device, average);
					}
				}
			}
		return Average;
		}

}
