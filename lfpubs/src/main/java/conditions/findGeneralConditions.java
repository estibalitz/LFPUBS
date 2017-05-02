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
			DescriptiveStatistics stats= new DescriptiveStatistics();
				for(int i=0;i<sets.size();i++){	
					int	index=sets.get(i);
					for(int j=0;j<sequence.get(index).getEventsOfSequence().size();j++){
						secondsIni=0;
						DateTime a= new DateTime(sequence.get(index).getEventsOfSequence().get(j).getTime());
						secondsIni=Integer.valueOf(a.getSecondOfDay());
						stats.addValue(secondsIni);	
					}
				}
			
			//Compute mean and standard deviation
			double mean=stats.getMean();
			double standarDeviation=stats.getStandardDeviation();
			double start=mean-standarDeviation;
			double end=mean+standarDeviation;
			//System.out.println("media:" +mean+ "   "+"standard:" + standarDeviation);
			
			double[] data=new double[]{mean,standarDeviation,start,end};

			//Convert data to hh:mm:ss format

			long Starthh=(long)start/60/60;
			long Startmm=((long)start/60)%60;
			long Startss=((long)start%60);
			String initialization=String.format("%02d:%02d:%02d", Starthh,Startmm,Startss);
			
			long Endhh=(long)end/60/60;
			long Endmm=((long)end/60)%60;
			long Endss=((long)end%60);
			String finishing=String.format("%02d:%02d:%02d",Endhh,Endmm,Endss);
			
			String TimeOfDay= new StringBuilder().append(initialization).append("T").append(finishing).toString();
			return TimeOfDay;
			
			
		}
}
					
				
		
			


