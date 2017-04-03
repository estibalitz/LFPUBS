package topology;

public class nextNode {
	
	String name;
	int frequency;
	
	public nextNode(String name, int frequency) {
		super();
		this.name = name;
		this.frequency = frequency;
	}

	public nextNode() {
		super();
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}

