package topology;


import java.util.ArrayList;

public class clusteredSequence {
	
	private int numActions; //a,b,c,d,a,f,e --> 7
	
	private ArrayList<String> simpleSequence = new ArrayList<String>(); //a,b,c,d,a,f,e
	
	private ArrayList<Integer> simpleSequenceCluster = new ArrayList<Integer>(); //0,0,0,0,2,0,0
	
	private ArrayList<Integer> codeSequence = new ArrayList<Integer>(); //hashCode of a_0,b_0,...
	
	private static int numGeneralActions = defineActionsClusters.getNumDifferentActions();
	
	private static String [] names = defineActionsClusters.getNames();
	
	private static int [] clusters = defineActionsClusters.getClusters();
	
	public clusteredSequence(clusteredSequence sequence) {
	    
	    numActions = sequence.simpleSequence.size();
	    simpleSequence = sequence.simpleSequence;
	    simpleSequenceCluster = sequence.simpleSequenceCluster;
	    clusteredSequenceInit();
	}
	
	public clusteredSequence (ArrayList<String> sequence, ArrayList<Integer> clusters){
		numActions = sequence.size();
		simpleSequence = sequence;
		simpleSequenceCluster = clusters;
	    clusteredSequenceInit();
	}
	
	public void setNumActions (int num){
		numActions = num;
	}
	
	public void setSimpleSequence (ArrayList<String> sequence){
		simpleSequence = sequence;
	}
	
	public void setSimpleSequenceCluster (ArrayList<Integer> clusters){
		simpleSequenceCluster = clusters;
	}
	
	public int getNumActions (){
		return numActions;
	}
	
	public ArrayList<String> getSimpleSequence (){
		return simpleSequence;
	}
	
	public ArrayList<Integer> getSimpleSequenceCluster (){
		return simpleSequenceCluster;
	}
	
	public ArrayList<Integer> getCodeSequence (){
		return codeSequence;
	}
	
	public void clusteredSequenceInit(){
		
		for (int i = 0; i < numActions; i++){
			String str = simpleSequence.get(i) + "_" + simpleSequenceCluster.get(i);
			codeSequence.add(str.hashCode());
		}
	}
	
	public static void main (String[] argv){
		
	}
}

