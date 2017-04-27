package timeRelations;

import java.util.ArrayList;

public class timeRelationsDataStructure {
	
	//	TimeRelations
	double minimumFrequencyTimeRelation = 0.5;
	double deviationAverageTimeRelation = 0.75;
	int selectedAlgorithm = 0; // 0 --> EM Algorithm ; 1 --> basic Algorithm
	ArrayList<String> tempNodeNameCells = new ArrayList<String>();
	
	simpleTimeRelation timeRelation = new simpleTimeRelation();
	findTimeActions timeActions=new findTimeActions();
	
	public timeRelationsDataStructure (){
		
	}
	
	public double getMinimumFrequencyTimeRelation (){
		return minimumFrequencyTimeRelation;
	}
	
	public void setMinimumFrequencyTimeRelation (double minimumFrequencyTimeRelation){
		this.minimumFrequencyTimeRelation = minimumFrequencyTimeRelation;
	}
	
	public double getDeviationAverageTimeRelation (){
		return deviationAverageTimeRelation;
	}
	
	public void setDeviationAverageTimeRelation (double deviationAverageTimeRelation){
		this.deviationAverageTimeRelation = deviationAverageTimeRelation;
	}
	
	public int getSelectedAlgorithm (){
		return this.selectedAlgorithm;
	}
	
	public void setSelctedAlgorithm (int selectedAlgorithm){
		this.selectedAlgorithm = selectedAlgorithm;
	}
	
	public ArrayList<String> getTempNodeNameCells (){
		return tempNodeNameCells;
	}
	
	public void setTempNodeNameCells (ArrayList<String> tempNodeNameCells){
		this.tempNodeNameCells = tempNodeNameCells;
	}
	
	public simpleTimeRelation getSimpleTimeRelation (){
		return timeRelation;
	}
	
	public void setSimpleTimeRelation (simpleTimeRelation timeRelation){
		this.timeRelation = timeRelation;
	}
	

}
