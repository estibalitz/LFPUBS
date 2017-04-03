package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.joda.time.DateTime;

import splitSequences.fullSequences;
import splitSequences.simpleEvent;


public class Average {
	String id;
	public ArrayList<Event>properties;

public Average(String id){
	this();
	this.id=id;
}
public Average(){
	properties=new ArrayList<>();
	}
public Average(Average avg){
	this.id=avg.id;
	this.properties=avg.properties;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public ArrayList<Event> getProperties() {
	return properties;
}
public void addProperties(Event properties) {
	if(properties!=null){
		this.properties.add(properties);
	}
	
}

}

	