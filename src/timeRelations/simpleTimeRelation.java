package timeRelations;

import java.util.ArrayList;
import java.io.*;
import splitSequences.*;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.Attribute;
import weka.filters.Filter;

public class simpleTimeRelation {
	
	private double timeRelation; //in the beginning the time relations will be doubles, then they can be extended defining ranges, ...
	private double upLimit;
	private double downLimit;
	private ArrayList<Integer> instances;
	
	public simpleTimeRelation(){
		
	}
	
	public simpleTimeRelation(double timeRelation,double upLimit, double downLimit, ArrayList<Integer> instances){
		this.timeRelation = timeRelation;
		this.upLimit = upLimit;
		this.downLimit = downLimit;
		this.instances = instances;
	}
	
	public simpleTimeRelation(double timeRelation,double upLimit, double downLimit){
		this.timeRelation = timeRelation;
		this.upLimit = upLimit;
		this.downLimit = downLimit;
		this.instances = new ArrayList<Integer>();
	}
	
	public simpleTimeRelation(double timeRelation,double upLimit, double downLimit, int instance){
		this.timeRelation = timeRelation;
		this.upLimit = upLimit;
		this.downLimit = downLimit;
		this.instances = new ArrayList<Integer>();
		this.instances.add(instance);
	}
	
	public void setSimpleTimeRelation (double timeRelation){
		this.timeRelation = timeRelation;
	}
	
	public double getSimpleTimeRelation (){
		return this.timeRelation;
	}
	
	public void setUpLimit (double upLimit){
		this.upLimit = upLimit;
	}
	
	public double getUpLimit (){
		return this.upLimit;
	}
	
	public void setDownLimit (double downLimit){
		this.upLimit = downLimit;
	}
	
	public double getDownLimit (){
		return this.downLimit;
	}
	
	public void setInstances (ArrayList<Integer> instances){
		this.instances = instances;
	}
	
	public void setParticularInstance (int instance){
		this.instances.add(instance);
	}
	
	public ArrayList<Integer> getInstances (){
		return this.instances;
	}
	
	public static ArrayList<Double> getTimeDistances (ArrayList<Integer> current, ArrayList<Integer> previous, ArrayList<simpleEvent> simpleEvents){
		ArrayList<Double> tempFinal = new ArrayList<Double> ();
		
		ArrayList<Double> tempCurrent = parseFileIntoSimpleEvents.getTimeOfSimpleEvent(current, simpleEvents);
		ArrayList<Double> tempPrevious = parseFileIntoSimpleEvents.getTimeOfSimpleEvent(previous, simpleEvents);
		
		for (int i = 0; i < tempCurrent.size(); i++){
			tempFinal.add(tempCurrent.get(i) - tempPrevious.get(i));
			//System.out.println("time in seconds " + tempFinal.get(i));
		}
		
		return tempFinal;
		
	}
	
	public static ArrayList<simpleTimeRelation> findOutTimeRelationsBasicAlgorithm (ArrayList<Double> timeDistances, double minimumFrequencyTimeRelation, double deviationAverageTimeRelation){
		ArrayList<simpleTimeRelation> tempTimeRelations = new ArrayList<simpleTimeRelation>();
		ArrayList<simpleTimeRelation> timeRelations = new ArrayList<simpleTimeRelation>();
		
		/*for (int i = 0; i < timeDistances.size(); i++){
			System.out.println("time distances " + timeDistances.get(i));
		}*/
		int initialRandomPosition = (int)(Math.random() * timeDistances.size());
		//System.out.println(" position " + initialRandomPosition + " size " + timeDistances.size());
		
		//create groups of time relations
		//from initial to < size
		for (int i = initialRandomPosition; i < timeDistances.size(); i++){
			createGroupTimeRelations (tempTimeRelations, timeDistances, deviationAverageTimeRelation, i);
		}
		//from 0 to < initial
		for (int i = 0; i < initialRandomPosition; i++){
			createGroupTimeRelations (tempTimeRelations, timeDistances, deviationAverageTimeRelation, i);
		}
		
		
		//create groups of time relations in tempTimeRelations
		/*for (int i = 0; i < timeDistances.size(); i++){
			if (tempTimeRelations.size() == 0){
				tempTimeRelations.add(new simpleTimeRelation (timeDistances.get(i), timeDistances.get(i)+(timeDistances.get(i)*deviationAverageTimeRelation), timeDistances.get(i)-(timeDistances.get(i)*deviationAverageTimeRelation), i));
			}
			else{
				int j = 0, ubicated = 0;
				while ((j < tempTimeRelations.size()) && (ubicated == 0)){
					if(((timeDistances.get(i) > tempTimeRelations.get(j).getDownLimit()) && (timeDistances.get(i) < tempTimeRelations.get(j).getUpLimit())) || ((tempTimeRelations.get(j).getSimpleTimeRelation() > timeDistances.get(i)-(timeDistances.get(i)*deviationAverageTimeRelation)) && (tempTimeRelations.get(j).getSimpleTimeRelation() < timeDistances.get(i)+(timeDistances.get(i)*deviationAverageTimeRelation)))){
						double newTimeRelation = (tempTimeRelations.get(j).getSimpleTimeRelation() * tempTimeRelations.size() + timeDistances.get(i)) / (tempTimeRelations.size()+1);
						tempTimeRelations.get(j).setSimpleTimeRelation(newTimeRelation);
						tempTimeRelations.get(j).setUpLimit(newTimeRelation+(newTimeRelation*deviationAverageTimeRelation));
						tempTimeRelations.get(j).setDownLimit(newTimeRelation-(newTimeRelation*deviationAverageTimeRelation));
						tempTimeRelations.get(j).setParticularInstance(i);
						ubicated = 1;
					}
					j++;
				}
				if (ubicated == 0){
					tempTimeRelations.add(new simpleTimeRelation (timeDistances.get(i), timeDistances.get(i)+(timeDistances.get(i)*deviationAverageTimeRelation), timeDistances.get(i)-(timeDistances.get(i)*deviationAverageTimeRelation), i));
				}
			}
		}*/
		
		//check if any of those groups has enough instances to consider it like interesting as copying it in timeRelations array
		double minimumInstances = timeDistances.size() * minimumFrequencyTimeRelation;
		//System.out.println("             minimum number of instances " + minimumInstances);
		for (int i = 0; i < tempTimeRelations.size(); i++){
			//System.out.println("time " + tempTimeRelations.get(i).getSimpleTimeRelation() + " up " + tempTimeRelations.get(i).getUpLimit() + " down " + tempTimeRelations.get(i).downLimit + " instances " + tempTimeRelations.get(i).getInstances().toString());
			if (tempTimeRelations.get(i).getInstances().size() > minimumInstances){
				//System.out.println("yess");
				timeRelations.add(tempTimeRelations.get(i));
			}
		}
		return timeRelations;
	}
	
	public static void createGroupTimeRelations (ArrayList<simpleTimeRelation> tempTimeRelations, ArrayList<Double> timeDistances, double deviationAverageTimeRelation, int i){
		if (tempTimeRelations.size() == 0){
			tempTimeRelations.add(new simpleTimeRelation (timeDistances.get(i), timeDistances.get(i)+(timeDistances.get(i)*deviationAverageTimeRelation), timeDistances.get(i)-(timeDistances.get(i)*deviationAverageTimeRelation), i));
		}
		else{
			int j = 0, ubicated = 0;
			while ((j < tempTimeRelations.size()) && (ubicated == 0)){
				if(((timeDistances.get(i) > tempTimeRelations.get(j).getDownLimit()) && (timeDistances.get(i) < tempTimeRelations.get(j).getUpLimit())) || ((tempTimeRelations.get(j).getSimpleTimeRelation() > timeDistances.get(i)-(timeDistances.get(i)*deviationAverageTimeRelation)) && (tempTimeRelations.get(j).getSimpleTimeRelation() < timeDistances.get(i)+(timeDistances.get(i)*deviationAverageTimeRelation)))){
					double newTimeRelation = (tempTimeRelations.get(j).getSimpleTimeRelation() * tempTimeRelations.size() + timeDistances.get(i)) / (tempTimeRelations.size()+1);
					tempTimeRelations.get(j).setSimpleTimeRelation(newTimeRelation);
					tempTimeRelations.get(j).setUpLimit(newTimeRelation+(newTimeRelation*deviationAverageTimeRelation));
					tempTimeRelations.get(j).setDownLimit(newTimeRelation-(newTimeRelation*deviationAverageTimeRelation));
					tempTimeRelations.get(j).setParticularInstance(i);
					ubicated = 1;
				}
				j++;
			}
			if (ubicated == 0){
				tempTimeRelations.add(new simpleTimeRelation (timeDistances.get(i), timeDistances.get(i)+(timeDistances.get(i)*deviationAverageTimeRelation), timeDistances.get(i)-(timeDistances.get(i)*deviationAverageTimeRelation), i));
			}
		}
		
	}
	
	public static ArrayList<simpleTimeRelation> findOutTimeRelationsEMAlgorithm (ArrayList<Double> timeDistances, double minimumFrequencyTimeRelation){
		ArrayList<simpleTimeRelation> finalTimeRelations = new ArrayList<simpleTimeRelation>();
		
		//get all time relations
		finalTimeRelations = runClustererProcess(timeDistances, minimumFrequencyTimeRelation);
				
		return finalTimeRelations;
	}
	
		public static ArrayList<simpleTimeRelation> runClustererProcess (ArrayList<Double> timeDistances, double minimumFrequencyTimeRelation){
			
			String[] options = new String[2];
			options[0] = "-I";        
			options[1] = "100";
			
			Instances       data;
			double[]        vals;
			
			FastVector atts = new FastVector ();
			atts.addElement(new Attribute("TimeDistances"));
			data = new Instances("Time Distances", atts, 0);
			vals = new double[data.numAttributes()];
			
			ArrayList<simpleTimeRelation> tempTimeRelations = new ArrayList<simpleTimeRelation> ();
			ArrayList<simpleTimeRelation> finalTimeRelations = new ArrayList<simpleTimeRelation> ();
			
			for (int i = 0; i < timeDistances.size(); i++){
				vals = new double[data.numAttributes()];
				vals[0] = timeDistances.get(i);
				data.add(new Instance(1.0, vals));
			}
			
			//System.out.println("Time Distances " + data);
			
			try{
				EM clusterer = new EM();
				clusterer.setOptions(options);     // set the options
				clusterer.buildClusterer(data);
				//System.out.println(clusterer.toString());
				
				ClusterEvaluation eval = new ClusterEvaluation();
				eval.setClusterer(clusterer);
				eval.evaluateClusterer(data);
				
				//System.out.println(eval.clusterResultsToString());
				double [][][] tempClusteringResults = clusterer.getClusterModelsNumericAtts();
				
				//create the tempTimeRelations
				for (int i = 0; i < tempClusteringResults.length; i++){//each i indicates a new time relation
					for (int j = 0; j < tempClusteringResults[i].length; j++){ //k = 0 --> mean; k = 1 --> deviation; k = 2 --> frequency
						tempTimeRelations.add(new simpleTimeRelation(tempClusteringResults[i][j][0], tempClusteringResults[i][j][0]+tempClusteringResults[i][j][1], tempClusteringResults[i][j][0]-tempClusteringResults[i][j][1]));
					}
				}
				
				//include the instances to tempTimeRelations
				ArrayList<Integer> tempClusterAction = eval.getClusterAction();
				for (int i = 0; i < tempClusterAction.size(); i++){
					tempTimeRelations.get(tempClusterAction.get(i)).setParticularInstance(i);
				}	
				
				//calculate the total number of actions
				int totalNumberActions = 0;
				for (int i = 0; i < tempTimeRelations.size(); i++){
					totalNumberActions =  totalNumberActions + tempTimeRelations.get(i).getInstances().size();
				}
			
				
				//get only frequent time relations
				for (int i = 0; i < tempTimeRelations.size(); i++){
					//System.out.println("size " + tempTimeRelations.get(i).getInstances().size() + " limit " + minimumFrequencyTimeRelation + " total " + totalNumberActions);
					if (tempTimeRelations.get(i).getInstances().size() > (totalNumberActions * minimumFrequencyTimeRelation)){
						finalTimeRelations.add(tempTimeRelations.get(i));
					}
				}
				
			}
			catch (IOException e){
				System.out.println("Error message " + e.getMessage());
			}
			catch (Exception e){
				System.out.println("Error message " + e.getMessage());
			}
			
			return finalTimeRelations;			
		}
		
		
	
}
