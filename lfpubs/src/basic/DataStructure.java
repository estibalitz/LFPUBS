package basic;

import java.util.ArrayList;


import splitSequences.eventLabel;
import splitSequences.splitSequencesDataStructure;
import topology.topologyDataStructure;
import frequentSequences.frequentSequencesDataStructure;
import timeRelations.timeRelationsDataStructure;
import conditions.conditionsDataStructure;
import tree.TreeDataStructure;

public class DataStructure {
	
	//file
	private static DataStructure _instance;
	private String nameFile = new String();
	private String nameResultFile =  new String();
	
	//	Sequences vs One to One
	int sequencesVSOneToOne = 1; //0 --> One to One
							     //1 --> Sequences
	
	//simplePattern
	ArrayList<simplePattern> simplePatterns = new ArrayList<simplePattern>();
	
	//splitSequences
	splitSequencesDataStructure splitSequences = new splitSequencesDataStructure(); 
	
	//frequentSequences
	frequentSequencesDataStructure frequentSequences = new frequentSequencesDataStructure();
	
	//topology
	topologyDataStructure topology = new topologyDataStructure();
		
	//timeRelations
	timeRelationsDataStructure timeRelations = new timeRelationsDataStructure();

	//conditions
	conditionsDataStructure conditions = new conditionsDataStructure();
	//Tree
	TreeDataStructure tree=new TreeDataStructure();
	
	private DataStructure(){}
	public static DataStructure getInstance()
	{
		if(_instance==null) _instance=new DataStructure();
		return _instance;
	}
	
	public String getNameFile (){
		return nameFile;
	}
	
	public void setNameFile (String nameFile){
		this.nameFile = nameFile;
	}
	
	public String getResultNameFile (){
		return nameResultFile;
	}
	
	public void setNameResultFile (String nameResultFile){
		this.nameResultFile = nameResultFile;
	}
	
	public int getSequencesVSOneToOne(){
		return sequencesVSOneToOne;
	}
	
	public void setSequencesVSOneToOne(int sequencesVSOneToOne){
		this.sequencesVSOneToOne = sequencesVSOneToOne;
	}
	
	public ArrayList<simplePattern> getSimplePatterns (){
		return simplePatterns;
	}
	
	public void setSimplePatterns (ArrayList<simplePattern> simplePatterns){
		this.simplePatterns = simplePatterns;
	}
	
	public splitSequencesDataStructure getSplitSequencesDataStructure (){
		return splitSequences;
	}
	
	public frequentSequencesDataStructure getFrequentSequencesDataStructure (){
		return frequentSequences;
	}
	
	public topologyDataStructure getTopologyDataStructure (){
		return topology;
	}
	
	public timeRelationsDataStructure getTimeRelationsDataStructure (){
		return timeRelations;
	}
	
	public conditionsDataStructure getConditionsDataStructure (){
		return conditions;
	}
	
	public TreeDataStructure getTreeDataStructure(){
		return tree;
	}
		
}
