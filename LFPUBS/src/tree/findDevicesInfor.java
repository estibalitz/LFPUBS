package tree;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.joda.time.DateTime;

import javafx.util.Pair;
import splitSequences.fullSequences;
import splitSequences.simpleEvent;

public class findDevicesInfor {
	protected String action;
	LinkedHashMap<String, Average> Averages= new LinkedHashMap<String,Average>();
	public findDevicesInfor(){
	}
	
	//for each action find duration between the device being activated
	public LinkedHashMap<String,Double>findtimeEventAverage(ArrayList<fullSequences> sequence,ArrayList<Integer>sets){
		
		ArrayList<String>Duration=new ArrayList<String>();
		long secondsAction=0;
		ArrayList<Double>Mean=new ArrayList<Double>();
		ArrayList<String>ActionDevices=new ArrayList<String>();
		
		try{//hau aldau berra dao
		for(int i=0;i<simpleEvent.getEventLabel().size();i++){
			String Device=simpleEvent.getEventLabel().get(i).getDevice();
			String Action= simpleEvent.getEventLabel().get(i).getAction();
			if(Action.compareTo("ON")==0){
				action="ON";
			}
			else if(Action.compareTo("on")==0){
				action="on";
			}
			
			DescriptiveStatistics stats= new DescriptiveStatistics();
			for (int j=0; j<sequence.size();j++){
				for(int k=0; k<sequence.get(j).getEventsOfSequence().size();k++){
					if((Action==sequence.get(j).getEventsOfSequence().get(k).getAction())&& (Device==sequence.get(j).getEventsOfSequence().get(k).getDevice())){
						secondsAction=0;
						DateTime dt=new DateTime(sequence.get(j).getEventsOfSequence().get(k).getTime());
						secondsAction=Integer.valueOf(dt.getSecondOfDay());
						stats.addValue(secondsAction);
					
					
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
		
	
	
	//Save duration of each device with its name in an HashMap

	LinkedHashMap<String,Double>Average=new LinkedHashMap<String,Double>();
		
		for(int i=0; i<ActionDevices.size()-1;i++){
			for(int j=i+1;j<ActionDevices.size();j++){
				String Device=ActionDevices.get(i);
				if(Device.compareTo(ActionDevices.get(j))==0){
					double average=(double) Math.round(Math.abs(Mean.get(j)-Mean.get(i)));
					Device=Device+" "+action;
					Average.put(Device, average);
					}
				}
		}
		return Average;
		}
	
	public  LinkedHashMap<String,Double> findEventRep(ArrayList<fullSequences> sequence,ArrayList<Integer>sets){
		double rep=0;
		LinkedHashMap<String, Double>DeviceRepMax= new LinkedHashMap<String,Double>();
		
		//create all the possible Ids
		/*for(int i=0; i<simpleEvent.getEventLabel().size();i++){
			String Device= simpleEvent.getEventLabel().get(i).getDevice();
			String Action= simpleEvent.getEventLabel().get(i).getAction();
			String id= Device+" "+Action;
			DeviceRepMax.put(id,rep);
		}*/
		// Sum all the values
		for(int i=0;i<sets.size();i++){
			int	index=sets.get(i);
				
			for(int j=0; j<sequence.get(index).getEventsOfSequence().size();j++){
				String Device=sequence.get(index).getEventsOfSequence().get(j).getDevice();
				String Action=sequence.get(index).getEventsOfSequence().get(j).getAction();
				String Event= Device+" "+Action;
				if(DeviceRepMax.containsKey(Event)==true){
					DeviceRepMax.put(Event, DeviceRepMax.get(Event) + (1));
					}
				else{
					double repeat=0;
					DeviceRepMax.put(Event, repeat);
				}
				}
			}
		//Divide with the sequence quantities
		
		Iterator it=DeviceRepMax.keySet().iterator();
		while(it.hasNext()){
			String key=(String) it.next();
			Double val=(double)Math.round((DeviceRepMax.get(key).doubleValue())/sets.size());
			DeviceRepMax.put(key, val);
		}
		return DeviceRepMax;
		}
	//----------------------------------------------------------------------------------------------------------//
	/*
	public LinkedHashMap<String, Double> calculateAverage(ArrayList<fullSequences> sequence,ArrayList<Integer>sets){
		
		Date d=new Date();
		long s1=0;
		long sTemp=0;
		long s2=0;
	    d=new Date();
	    
		LinkedHashMap<String,Double>Average=new LinkedHashMap<String,Double>();
		long secondsAction=0;
		
		
		//Create all the possible Ids
		
		for(int i=0; i<simpleEvent.getEventLabel().size();i++){
		String Device= simpleEvent.getEventLabel().get(i).getDevice();
		String Action= simpleEvent.getEventLabel().get(i).getAction();
	
		if(Averages.containsKey(Device)==true){
			if(Averages.get(Device).getProperties().contains(Action)==false){
				Event e= new Event();
				e.action=Action;
				e.stats= new DescriptiveStatistics();
				Averages.get(Device).addProperties(e);
			}
			
		}
		else if(Averages.containsKey(Device)==false){
			Averages.put(Device, new Average(Device));
			Event e= new Event();
			e.action=Action;
			e.stats= new DescriptiveStatistics();
			Averages.get(Device).addProperties(e);
		}

		}
		//Calculate the average of each devices use
		for(int i=0;i<sets.size();i++){
			int	index=sets.get(i);
			for(int j=0; j<sequence.get(index).getEventsOfSequence().size();j++){
				secondsAction=0;
				String Device=sequence.get(index).getEventsOfSequence().get(j).getDevice();
				String Action=sequence.get(index).getEventsOfSequence().get(j).getAction();
				DateTime dt=new DateTime(sequence.get(index).getEventsOfSequence().get(j).getTime());
				secondsAction=Integer.valueOf(dt.getSecondOfDay());
				if(Averages.containsKey(Device)==true){
					for(int k=0;k<Averages.get(Device).getProperties().size();k++){
						if(Averages.get(Device).getProperties().get(k).getAction().compareTo(Action)==0){
						//Averages.get(Device).getProperties().get(k).getStats().addValue(secondsAction);
						Averages.get(Device).getProperties().get(k).setStats(secondsAction);
						//Averages.get(Device).getProperties().get(k).getStats().addValue(secondsAction);
						}
					}
				}
			}
		}
		
		//Restar valores de averageoff-averageon=averageuseOfDevice

		Iterator it=Averages.keySet().iterator();
		while(it.hasNext()){
			String key=(String) it.next();
			if(Averages.get(key).getProperties().size()==2){
				double average=(double) Math.round(Math.abs(Averages.get(key).getProperties().get(1).getStats().getMean()-Averages.get(key).getProperties().get(0).getStats().getMean()));
				String Event=Averages.get(key).getId()+""+Averages.get(key).getProperties().get(0).getAction();
				Average.put(Event, average);
			}
			else{
				System.out.println("Not average use well defined");
			}
			
		}
		
		d=new Date();
		sTemp=d.getTime();
		System.out.println("Execution time of <conditions> " + (sTemp - s1) + "  miliseconds");
		return Average;
	}
	*/
}



		
