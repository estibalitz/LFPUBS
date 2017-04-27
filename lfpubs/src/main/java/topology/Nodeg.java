package topology;

import java.util.ArrayList;
import java.util.Comparator;

public class Nodeg extends Node implements Comparator<Object>
{
	double g;
	
	public Nodeg (){
		
	}

	public Nodeg(String name, ArrayList<nextNode> nextNodes, double g) {
		super();
		this.name = name;
		this.nextNodes = nextNodes;
		this.g = g;
	}
	
	public double getg()
	{
		return g;
	}

	public void setg(double g)
	{
		this.g = g;
	}
	public int compare(Object o1, Object o2)
	{
		Nodeg nodeg1 = ((Nodeg)o1);
		Nodeg nodeg2 = ((Nodeg)o2);
		
		return ((Nodeg)nodeg1).getName().compareTo(((Nodeg)nodeg2).getName());
	}
	public int compareTo(Nodeg nodeg1)
	{
		return this.getName().compareTo(nodeg1.getName());
	}
}

class MyCompare implements Comparator {
	  public final static int BY_G=0;
	  public final static int BY_NAME=1;
	  private int sortType=BY_NAME;

	  public void setSortType(int sortType) {
	    this.sortType=sortType;
	  }
	  public int compare(Object o1, Object o2) {
	    Nodeg p1=(Nodeg)o1;
	    Nodeg p2=(Nodeg)o2;
	    switch (sortType) {
	      case BY_G:
	    	if (p1.getg() > p2.getg())
	    		return 1;
	    	else if(p1.getg() < p2.getg())
	    		return -1;
	    	else
	    		return 0;
	      case BY_NAME:
	        return p1.getName().compareTo(p2.getName());
	      default:
	        return 0;  // huh ?
	    }
	  }
}

