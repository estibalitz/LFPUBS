package frequentSequences;

import java.util.ArrayList;

public class simpleSequence {
	
	int length;         //  lenght of the sequence
	double counter;		  // number of instances of that sequence
	ArrayList<Integer> sequence;	  // the sequence itself
	ArrayList<Integer> instances;   // the instances of that sequence
	ArrayList<Integer> shortExtraInstances;
	ArrayList<Integer> longExtraActions;
	  
	  //constructor
	  public simpleSequence (int length, double counter, ArrayList<Integer> sequence, ArrayList<Integer> instances){
		  this.length = length;
		  this.counter = counter;
		  this.instances = instances;
		  this.sequence = sequence;
		  this.shortExtraInstances = new ArrayList<Integer>();
		  this.longExtraActions = new ArrayList<Integer>();
	  }
	  
	//constructor
	  public simpleSequence (int length, double counter, ArrayList<Integer> sequence, ArrayList<Integer> instances, ArrayList<Integer> shortExtraInstances, ArrayList<Integer> longExtraActions){
		  this.length = length;
		  this.counter = counter;
		  this.instances = instances;
		  this.sequence = sequence;
		  this.shortExtraInstances = shortExtraInstances;
		  this.longExtraActions = longExtraActions;
	  }
	  
	  //constructor
	  public simpleSequence (simpleSequence sequence){
		  this.length = sequence.length;
		  this.counter = sequence.counter;
		  this.instances = sequence.instances;
		  this.sequence = sequence.sequence;
		  this.shortExtraInstances = sequence.shortExtraInstances;
		  this.longExtraActions = sequence.longExtraActions;
	  }
	  
	  //constructor
	  public simpleSequence (){
		  this.shortExtraInstances = new ArrayList<Integer>();
		  this.longExtraActions = new ArrayList<Integer>();
	  }
	  
	  public int getLength (){
		  return length;
	  }
	  
	  public double getCounter (){
		  return counter;
	  }
	  
	  public ArrayList<Integer> getSequence(){
		  return sequence;
	  }
	  
	  public ArrayList<Integer> getInstances (){
		  return instances;
	  }
	  
	  public ArrayList<Integer> getShortExtraInstances(){
		  return shortExtraInstances;
	  }
	  
	  public ArrayList<Integer> getLongExtraActions(){
		  return longExtraActions;
	  }
	  
	  public void setLength (int length){
		  this.length = length;
	  }
	  
	  public void setCounter (double counter){
		  this.counter = counter;
	  }
	  
	  public void setSequence(ArrayList<Integer> sequence){
		  this.sequence = sequence;
	  }
	  
	  public void setInstances (ArrayList<Integer> instances){
		  this.instances = instances;
	  }
	  
	  public void setShortExtraInstances(ArrayList<Integer> shortExtraInstances){
		  this.shortExtraInstances = shortExtraInstances;
	  }
	  
	  public void setLongExtraActions(ArrayList<Integer> longExtraActions){
		  this.longExtraActions = longExtraActions;
	  }
	  
}
