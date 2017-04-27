package tree;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Event {

	public String action;
	public DescriptiveStatistics stats=null;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public DescriptiveStatistics getStats() {
		return stats;
	}
	public void setStats(double val) {
		stats.addValue(val);
	}

}