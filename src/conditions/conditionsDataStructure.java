package conditions;

public class conditionsDataStructure {
	
	//	condition
	findDisjunctionConditions disjunctionConditions = new findDisjunctionConditions();
	findGeneralConditions generalConditions=new findGeneralConditions();
	int minimumDemandedFrequencyForConditions = 4; //it must be > 1
	int minimumRelationPercentage = 60;
	
	public conditionsDataStructure(){
		
	}
	
	public findDisjunctionConditions getDisjunctionConditions (){
		return disjunctionConditions;
	}

	public void setDisjunctionConditions (findDisjunctionConditions disjunctionConditions){
		this.disjunctionConditions = disjunctionConditions;
	}
	
	public int getMinimumDemandedFrequencyForConditions (){
		return minimumDemandedFrequencyForConditions;
	}
	
	public void setMinimumDemandedFrequencyForConditions (int minimumDemandedFrequencyForConditions){
		this.minimumDemandedFrequencyForConditions = minimumDemandedFrequencyForConditions;
	}
	
	public int getMinimumRelationPercentage (){
		return minimumRelationPercentage;
	}
	
	public void setMinimumRelationPercentage (int minimumRelationPercentage){
		this.minimumRelationPercentage = minimumRelationPercentage;
	}
	public findGeneralConditions getGeneralConditions(){
		return generalConditions;
	}
	public void setGeneralConditions(findGeneralConditions generalConditions){
		this.generalConditions=generalConditions;
	}
	
	
	

}
