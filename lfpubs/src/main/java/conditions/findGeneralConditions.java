package conditions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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


public class findGeneralConditions {

	public findGeneralConditions(){
	}
	
	
	//for each sequence find the context information needed
		public String needDayOfWeek(ArrayList<fullSequences> sequence,ArrayList<Integer>sets){
			ArrayList<String> dayOfWeek= new ArrayList<String>();
			
			for(int i=0;i<sets.size();i++){
			int	index=sets.get(i);
			
				for(int j=0; j<sequence.get(index).getEventsOfSequence().size();j++){
					DateTime dt=new DateTime(sequence.get(index).getEventsOfSequence().get(j).getTime()).toDateTime(DateTimeZone.forID("CET"));
					String a0=dt.dayOfWeek().getAsText();
					
						 int a=0;
						 
						 	if((j==0)&&(i==0)){
								dayOfWeek.add(a0);
							}
							
							for(int k=0; k<dayOfWeek.size();k++){
								
								if(a<dayOfWeek.size()){
									if (a0!=dayOfWeek.get(k)){
										a=a+1;
									}
								}
								if(a==dayOfWeek.size()){
									dayOfWeek.add(a0);
									
								}
								
							}
				}
			}
			dayOfWeek=sortDaysOfWeek( dayOfWeek);
				
			String dayOFweek=String.join(",", dayOfWeek);
			return dayOFweek;	
			}

		private ArrayList<String> sortDaysOfWeek(ArrayList<String> dayOfWeek) {
			ArrayList<String> Days=new ArrayList<String>();
		
		String[] daysofWeek={"Monday", "Tuesday", "Wednesday","Thursday","Friday", "Saturday", "Sunday"};
		for(int i=0;i<daysofWeek.length;i++){
		if(dayOfWeek.contains(daysofWeek[i])==true){
			Days.add(daysofWeek[i]);
		}
		}
		return Days;
	}


		public String needAverageTime(ArrayList<fullSequences> sequence,ArrayList<Integer>sets){
			//Variable initialization
			long secondsIni=0;
			DescriptiveStatistics start=new DescriptiveStatistics();
			DescriptiveStatistics end=new DescriptiveStatistics();
			DescriptiveStatistics Kettle=new DescriptiveStatistics();
				for(int i=0;i<sets.size();i++){	
					int	index=sets.get(i);
					for(int j=0;j<sequence.get(index).getEventsOfSequence().size();j++){
						secondsIni=0;
						DateTime a= new DateTime(sequence.get(index).getEventsOfSequence().get(j).getTime());
						secondsIni=Integer.valueOf(a.getSecondOfDay());
						if(j==0){
							start.addValue(secondsIni);
						}
						else if( (sequence.get(index).getEventsOfSequence().get(j).getDevice().compareTo("Kettle")==0)&&(sequence.get(index).getEventsOfSequence().get(j).getAction().compareTo("ON")==0)){
							Kettle.addValue(secondsIni);
						}
						else if(j==sequence.get(index).getEventsOfSequence().size()-1){
							end.addValue(secondsIni);
						}
						
					}
				}
			//Compute new mean and standard deviation
				double meannewstart=start.getMean();
				double meannewend=end.getMean();
				double standarDeviationstart=start.getStandardDeviation();
				double standarDeviationend=end.getStandardDeviation();
				double startnew=meannewstart-standarDeviationstart;
				double endnew=meannewend+standarDeviationend;
				//Convert data to hh:mm:ss format

				long starthh=(long)startnew/60/60;
				long startmm=((long)startnew/60)%60;
				long startss=((long)startnew%60);
				String ini=String.format("%02d:%02d:%02d", starthh,startmm,startss);
			//Kettle
				double meanKettle=Kettle.getMean();
				String Kettles= String.format("%02d:%02d:%02d",(long)(meanKettle/60/60), (long) ((meanKettle/60)%60),(long)(meanKettle%60));
				
				
				
				
				
				
				long endhh=(long)endnew/60/60;
				long endmm=((long)endnew/60)%60;
				long endss=((long)endnew%60);
				String finish=String.format("%02d:%02d:%02d",endhh,endmm,endss);
				
			String TimeOFday=new StringBuilder().append(ini).append("T").append(finish).toString();
			//System.out.println(TimeOFday);
			return TimeOFday;
			
			
		}
}
					
				
		
			


