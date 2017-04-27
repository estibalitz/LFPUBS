package tree;
import java.util.ArrayList;
import java.util.List;
import tree.Link;

public class Node{
	String id;
	String type;
	String pos;
	int rep=0;
	int maxrep=0;
	public ArrayList<Link> edge;
	public ArrayList<String>components;
	
	
	public Node(String id) {
		this();
		this.id=id;
	}
	
	public Node(){
		edge = new ArrayList<>();
		rep=0;
		maxrep=0;
		type="";
		pos="";
		components=new ArrayList<>();
	}


	public Node (Node node){
		this.id=node.id;
		this.edge=node.edge;
		this.rep=node.rep;
		this.maxrep=node.maxrep;
		this.type=node.type;
		this.pos=node.pos;
	}
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id=id;
	}
	public ArrayList<Link> getEdge(){
		return edge;
	}
	public void addEdge(Link edge){
		if(edge!=null)
			this.edge.add(edge);
	}
	public Node getNode(){
		return this;
	}
	public int getRep(){
		return rep;
	}
	public void setRep(int rep){
		this.rep=rep;
	}
	public int getMaxRep(){
		return maxrep;
	}
	public void setMaxRep(int maxrep){
		this.maxrep=maxrep;
	}
	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<String> getComponents() {
		return components;
	}

	public void addComponent(String components) {
		if(components!=null){
		this.components.add(components);
		}
	}
}
