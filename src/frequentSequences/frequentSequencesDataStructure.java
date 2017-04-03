package frequentSequences;

import java.util.ArrayList;

public class frequentSequencesDataStructure {
	
//	frequentSequences
	int initialMinSup = 80; //in percentage
	int longerMinSup = 80;
	double lengthSimilarity = 0.75;
	findFrequentSequences findSequences = new findFrequentSequences();
	ArrayList<simpleSequence> allFoundSequences = new ArrayList();
	ArrayList<Integer> ruledOutInstances = new ArrayList<Integer>();
	
	public frequentSequencesDataStructure(){
		
	}
	
	public int getInitialMinSup (){
		return initialMinSup;
	}
	
	public void setInitialMinSup (int initialMinSup){
		this.initialMinSup = initialMinSup;
	}
	
	public int getLongerMinSup (){
		return longerMinSup;
	}
	
	public void setLongerMinSup (int longerMinSup){
		this.longerMinSup = longerMinSup;
	}
	
	public double getLengthSimilarity (){
		return lengthSimilarity;
	}
	
	public void setLengthSimilarity (double lengthSimilarity){
		this.lengthSimilarity = lengthSimilarity;
	}
	
	public findFrequentSequences getFindSequences (){
		return findSequences;
	}
	
	public void setFindSequences (findFrequentSequences findSequences){
		this.findSequences = findSequences;
	}
	
	public ArrayList<simpleSequence> getAllFoundSequences (){
		return allFoundSequences;
	}
	
	public void setAllFoundSequences (ArrayList<simpleSequence> allFoundSequences){
		this.allFoundSequences = allFoundSequences;
	}
	
	public ArrayList<Integer> getRuledOutInstances (){
		return ruledOutInstances;
	}
	
	public void setRuledOutInstances (ArrayList<Integer> ruledOutInstances){
		this.ruledOutInstances = ruledOutInstances;
	}

}
