package topology;

import java.util.ArrayList;

public class Node {
	
	String name;
	ArrayList<nextNode> nextNodes;
	
	public Node (){
		
	}

	public Node(String name, ArrayList<nextNode> nextNodes) {
		super();
		this.name = name;
		this.nextNodes = nextNodes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<nextNode> getNextNodes() {
		return nextNodes;
	}

	public void setNextNodes(ArrayList<nextNode> nextNodes) {
		this.nextNodes = nextNodes;
	}

	

}
