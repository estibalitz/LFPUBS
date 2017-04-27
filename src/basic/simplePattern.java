package basic;

import java.util.ArrayList;

public class simplePattern {
	
	private ArrayList<simpleNode> topologyNodes;
	private double maximumFrequencyFromStartToEnd;
	
	//general conditions of the pattern
	
	public simplePattern (ArrayList<simpleNode> topologyNodes, double minimumFrequencyFromStartToEnd){
		this.topologyNodes = topologyNodes;
		this.maximumFrequencyFromStartToEnd = minimumFrequencyFromStartToEnd;
	}
	
	public simplePattern (){
		
	}
		
	public double getMaximumFrequencyFromStartToEnd() {
		return maximumFrequencyFromStartToEnd;
	}

	public void setMaximumFrequencyFromStartToEnd(
			double maximumFrequencyFromStartToEnd) {
		this.maximumFrequencyFromStartToEnd = maximumFrequencyFromStartToEnd;
	}

	public ArrayList<simpleNode> getTopologyNodes() {
		return topologyNodes;
	}

	public void setTopologyNodes(ArrayList<simpleNode> topologyNodes) {
		this.topologyNodes = topologyNodes;
	}

	public void setParticularNode (simpleNode node){
		this.topologyNodes.add(new simpleNode(node));
	}

}
