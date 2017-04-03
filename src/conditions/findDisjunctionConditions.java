package conditions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import weka.classifiers.rules.JRip;
import weka.classifiers.rules.RuleStats;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import basic.simpleNode;
import splitSequences.*;

public class findDisjunctionConditions {
	
	public findDisjunctionConditions(){
		
	}
	
	//for each class it will detect the set of conditions
	/*public ArrayList<summarizedConditions> findOutDisjunctionConditions (){
		
	}*/
	
	public ArrayList<Integer> needDisjunctionConditions (simpleNode node, int minimumDemandedFrequencyForConditions, int minimumPercentage){
		//it analyzes if that node needs disjunction conditions
		int total = 0;
		double averageActionNum = 0;
		ArrayList<Integer> listActions = new ArrayList<Integer>();
		ArrayList<Integer> emptyListActions = new ArrayList<Integer>();
		ArrayList<Integer> tempRemoveActions = new ArrayList<Integer>();
		ArrayList<Integer> finalListActions = new ArrayList<Integer>();
		
		if (node.getNextActions().size() < 2){
			return emptyListActions;
		}
		
		//System.out.println("node " + node.getNode() + " size next actions " + node.getNextActions().size());
		
		for (int i = 0; i < node.getNextActions().size(); i++){
			//System.out.println("index " + node.getIndexSimpleEventsOfNext().get(i).size());
			if (node.getIndexSimpleEventsOfNext().get(i).size() >= minimumDemandedFrequencyForConditions){
				total = total + node.getIndexSimpleEventsOfNext().get(i).size();
				listActions.add(i);
			}
			/*if (node.getIndexSimpleEventsOfNext().get(i).size() > max){
				max = node.getIndexSimpleEventsOfNext().get(i).size();
			}*/
		}
		
		if (listActions.size() < 2){ //there are less than 2 next actions that have more actions than the demanded minimum frequency
			return emptyListActions;
		}
		
		averageActionNum = ((double) total / (double) listActions.size());
		
		//System.out.println("average " + averageActionNum);
		
		for (int i = 0; i < listActions.size(); i++){
			//System.out.println("value " + (double)node.getIndexSimpleEventsOfNext().get(listActions.get(i)).size());
			//System.out.println("demanded " + ((averageActionNum * minimumPercentage)/100));
			if((double)node.getIndexSimpleEventsOfNext().get(listActions.get(i)).size() < ((averageActionNum * minimumPercentage)/100)){
				//System.out.println("the following has been removed " + i);
				tempRemoveActions.add(i);
			}
			else{
				//System.out.println("it is not necessary");
			}
		}
		
		//System.out.println("array previous " + listActions.toString());
		//System.out.println("to delete " + tempRemoveActions.toString());
		
		for (int i = 0; i < listActions.size(); i++){
			if (findActionInListDeletedActions (i,tempRemoveActions)){
				//System.out.println("found");
			}
			else{
				//System.out.println("no found");
				finalListActions.add(listActions.get(i));
			}
		}
		
		//System.out.println("array next " + finalListActions.toString());
		
		
		if (finalListActions.size() < 2){
			return emptyListActions;
		}
		
		return finalListActions;
	}
	
		public boolean findActionInListDeletedActions (int action, ArrayList<Integer> listDeletedActions){
			
			for (int i = 0; i < listDeletedActions.size(); i++){
				if (listDeletedActions.get(i) == action){
					return true;
				}
			}
			return false;
			
		}
	
	public ArrayList<ArrayList<simpleCompleteCondition>> findOutDisjunctionConditions (simpleNode node, ArrayList<simpleEvent> simpleEvents, ArrayList<Integer> listActions) throws Exception {
		Instances data;
		ArrayList<simpleCompleteCondition> completeDisjunctionConditions = new ArrayList<simpleCompleteCondition>();
		ArrayList<ArrayList<simpleCompleteCondition>> finalCompleteDisjunctionConditions = new ArrayList<ArrayList<simpleCompleteCondition>>();
		
		data = createTableForDisjunctionConditions (node, simpleEvents, listActions);
		completeDisjunctionConditions = findOutDisjunctionConditions(data);
		finalCompleteDisjunctionConditions = putInGroupsConditions (node, completeDisjunctionConditions);
		
		return finalCompleteDisjunctionConditions;
		
	}
	
		public Instances createTableForDisjunctionConditions (simpleNode node, ArrayList<simpleEvent> simpleEvents, ArrayList<Integer> listActions) throws Exception {
			double time = 0, day = 0;
			//double [] temp = new double[S.num_S_devices];
	
			BufferedWriter bw = new BufferedWriter(new FileWriter("test\\test1.arff"));
			PrintWriter writer = new PrintWriter(bw);
			
			writer.println("@relation on_device");
			writer.println();
			writer.println("@attribute time real");
			writer.println("@attribute day_of_week {0.0,1.0,2.0,3.0,4.0,5.0,6.0}");
			/*for (int i = 0; i < S.num_S_devices; i++){
				writer.println("@attribute " + S_Devices[i].get_name() + " real");
			}*/
			writer.print("@attribute class {");
			for (int i = 0; i < listActions.size()-1; i++){
				writer.print(node.getNextActions().get(listActions.get(i)).toString() + ", ");
				//System.out.print ("   node  " + listActions.get(i) + " value "+ node.getNextActions().get(listActions.get(i)).toString());
			}
			writer.println(node.getNextActions().get(listActions.get(listActions.size()-1)).toString() + "}");
			//System.out.println("   node " + listActions.get(listActions.size()-1) + " value "+ node.getNextActions().get(listActions.get(listActions.size()-1)).toString());
			writer.println();
			writer.println("@data");
			
			for (int i = 0; i < listActions.size(); i++){
				//System.out.println("nodes " + node.getNextActions().get(i).toString() + " number of instances " + node.getIndexSimpleEventsOfNext().get(i).size());
				for (int j = 0; j < node.getIndexSimpleEventsOfNext().get(listActions.get(i)).size(); j++){
					time = simpleEvents.get(node.getIndexSimpleEventsOfNext().get(listActions.get(i)).get(j)).getTime().getTime().getHours() * 60 * 60 + simpleEvents.get(node.getIndexSimpleEventsOfNext().get(listActions.get(i)).get(j)).getTime().getTime().getMinutes() * 60 + simpleEvents.get(node.getIndexSimpleEventsOfNext().get(listActions.get(i)).get(j)).getTime().getTime().getSeconds();
					day = simpleEvents.get(node.getIndexSimpleEventsOfNext().get(listActions.get(i)).get(j)).getTime().getTime().getDay();
					writer.print(time + "," + day + ",");
					/*for (int j = 0; j < S.num_S_devices; j++){
						writer.print(temp[j] + ",");
					}*/
					writer.println(node.getNextActions().get(listActions.get(i)).toString());
				}
			}
					
			writer.close();
			
			DataSource source = new DataSource("test\\test1.arff");
			Instances data = source.getDataSet();
			
			return data;
		}

		public ArrayList<simpleCompleteCondition> findOutDisjunctionConditions (Instances data) throws Exception {
			
			FastVector m_RulesetStats, m_Antds;
			Attribute m_Class, att;
			String className;
			
		
			if (data.classIndex() == -1)
			    data.setClassIndex(data.numAttributes() - 1);
			JRip jr = new JRip();
			jr.buildClassifier(data);
			//System.out.println(jr.toString());
			m_RulesetStats = jr.getRuleStats_complet();
			
			m_Class = jr.getm_Class();
			/*RuleStats rs_rules = (RuleStats)m_RulesetStats.elementAt(0);
			FastVector rules_rules = rs_rules.getRuleset();*/
			
			/*System.out.println("size " + m_RulesetStats.size());
			int numberRules = 0;
			for (int i = 0; i < m_RulesetStats.size(); i++){
				RuleStats rs = (RuleStats)m_RulesetStats.elementAt(i);
				FastVector rules = rs.getRuleset();
				for (int k = 0; k < rules.size(); k++){
					System.out.println("rules " + numberRules + " class " + m_Class.value((int)jr.getm_Consequent(rules,k)));
					for (int l = 0; l < jr.getm_Antds(rules, k).size(); l++){
						System.out.println("kk " + jr.getvalue_Antds(jr.getm_Antds(rules, k), l));
					}
					numberRules++;
				}
			}*/
			
			ArrayList<simpleCompleteCondition> simpleConditions = new ArrayList<simpleCompleteCondition>();
			int numRules = 0;
			for (int i = 0; i < m_RulesetStats.size(); i++){
				RuleStats rs = (RuleStats)m_RulesetStats.elementAt(i);
				FastVector rules = rs.getRuleset();
				for (int j = 0; j < rules.size(); j++){
					ArrayList<simpleBasicCondition> tempSimpleCondition = new ArrayList<simpleBasicCondition>();
					m_Antds = jr.getm_Antds(rules, j);
					if (m_Antds.size() > 0){
						for (int k = 0; k < m_Antds.size() - 1; k++){
							className = jr.getclassm_Antds(m_Antds, j);
							att = jr.getAttr_Antds(m_Antds, k);
							if (className.compareTo("NumericAntd") == 0){
								  String symbol = ((int)jr.getvalue_Antds(m_Antds, k) == 0) ? "<=" : ">=";
								  double splitPoint = jr.getsplitPoint(m_Antds, k);
								  tempSimpleCondition.add(new simpleBasicConditionNumeric(att.name(), symbol, splitPoint));
								  //System.out.println("numeric " + att.name() + " " + symbol + " value " + splitPoint);
							 }
							else{
								tempSimpleCondition.add(new simpleBasicConditionNominal(att.name(), "=", att.value((int)jr.getvalue_Antds(m_Antds,k))));
								//System.out.println("nominal " + att.name() + " = " + att.value((int)jr.getvalue_Antds(m_Antds,k)));

								}
						}
						className = jr.getclasslastm_Antds(m_Antds);
						att = jr.getlastAttr_Antds(m_Antds);
						int last_position = m_Antds.size() - 1;
						if (className.compareTo("NumericAntd") == 0){
							String symbol = ((int)jr.getlastvalue_Antds(m_Antds) == 0) ? "<=" : ">=";
							double splitPoint = jr.getlastsplitPoint(m_Antds);
							  tempSimpleCondition.add(new simpleBasicConditionNumeric(att.name(), symbol, splitPoint));
							  //System.out.println("numeric " + att.name() + " " + symbol + " value " + splitPoint);
						}
						else{
							tempSimpleCondition.add(new simpleBasicConditionNominal(att.name(), "=", att.value((int)jr.getvalue_Antds(m_Antds,j))));
							//System.out.println("nominal " + att.name() + " = " + att.value((int)jr.getvalue_Antds(m_Antds,j)));
						}
					}
					if (tempSimpleCondition.size() != 0){
						simpleConditions.add(new simpleCompleteCondition (tempSimpleCondition, m_Class.value((int)jr.getm_Consequent(rules,j)),numRules));
						numRules++;
					}
					else{
						simpleConditions.add(new simpleCompleteCondition (tempSimpleCondition, m_Class.value((int)jr.getm_Consequent(rules,j)),-1)); //else
					}
				}
			}
			
			return simpleConditions;

		}

		public ArrayList<ArrayList<simpleCompleteCondition>> putInGroupsConditions (simpleNode node, ArrayList<simpleCompleteCondition> simpleConditions){
			
			ArrayList<ArrayList<simpleCompleteCondition>> tempFinalConditions = new ArrayList<ArrayList<simpleCompleteCondition>>();
			ArrayList<ArrayList<simpleCompleteCondition>> finalConditions = new ArrayList<ArrayList<simpleCompleteCondition>>();
			
			int indexGroup = 0;
			for (int i = 0; i < simpleConditions.size(); i++){
				int tempIsIn = isIn(simpleConditions.get(i).getConsequent(), tempFinalConditions);
				if (tempIsIn != -1){
					tempFinalConditions.get(tempIsIn).add(new simpleCompleteCondition(simpleConditions.get(i)));
					//System.out.println("group " + tempIsIn + " condition " + simpleConditions.get(i).getConsequent());
				}
				else{
					tempFinalConditions.add(new ArrayList<simpleCompleteCondition>());
					tempFinalConditions.get(indexGroup).add(new simpleCompleteCondition(simpleConditions.get(i)));
					//System.out.println("group " + indexGroup + " condition " + simpleConditions.get(i).getConsequent());
					indexGroup++;
				}
			}
			
			for (int i = 0; i < node.getNextActions().size(); i++){
				//System.out.println("group " + node.getNextActions().get(i));
				finalConditions.add(new ArrayList<simpleCompleteCondition>());
				int tempIsIn = isIn(node.getNextActions().get(i),tempFinalConditions);
				if (tempIsIn != -1) {
					for (int j = 0; j < tempFinalConditions.get(tempIsIn).size(); j++){
						finalConditions.get(i).add(tempFinalConditions.get(tempIsIn).get(j));
						//System.out.println("        event " + tempFinalConditions.get(tempIsIn).get(j).getConsequent() + " order " + tempFinalConditions.get(tempIsIn).get(j).getOrder());
					}
				}
			}
			
			return finalConditions;		
			
		}
		
			public int isIn(String consequent, ArrayList<ArrayList<simpleCompleteCondition>> tempFinalConditions){
				int temp = 0;
				
				for (int i = 0; i < tempFinalConditions.size(); i++){
					if (consequent.compareTo(tempFinalConditions.get(i).get(0).getConsequent())==0){
						return i;
					}
				}
				return -1;
			}
		
}
