package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.geom.Rectangle2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import basic.DataStructure;
import basic.simplePattern;
import basic.simpleNode;

import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JInternalFrame;

import org.jgraph.JGraph;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;

import splitSequences.simpleEvent;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;

import conditions.simpleBasicConditionNominal;
import conditions.simpleBasicConditionNumeric;
import conditions.simpleCompleteCondition;
import frequentSequences.simpleSequence;

import javax.swing.JTextArea;

public class Conditions extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final static String newline = "\n";  //  @jve:decl-index=0:
	
	private JPanel jPanelConditions = null;

	private JPanel jPanelSetParametersConditions = null;

	private JPanel jPanelShowConditions = null;

	private JPanel jPanelShowTopologyGraphConditions = null;

	private JLabel jLabelSetParametersDemandedFrequency = null;

	private JTextField jTextFieldSetParametersDemandedFrequency = null;

	private JButton jButtonSetParameterRestoreDefaultValues = null;

	private JLabel jLabelSetParametersDemandedMinimumConfidence = null;

	private JTextField jTextFieldSetParametersDemandedMinimumLevel = null;

	private JButton jButtonDiscoverConditions = null;

	private JLabel jLabelSelectSequence = null;

	private JComboBox jComboBoxSelectSequence = null;

	private JSlider jSliderSelectGranularity = null;

	private JInternalFrame jInternalFrameShowTopologyConditions = null;

	private JPanel jContentPaneShowTopologyConditions = null;
	
	private JScrollPane jScrollPaneShowTopologyGraphConditions = null;
	
	ArrayList<DefaultGraphCell> tempNodeCells = new ArrayList<DefaultGraphCell>();  //  @jve:decl-index=0:
	
	ArrayList<DefaultGraphCell> tempRelationCells = new ArrayList<DefaultGraphCell>();

	private JTextArea jTextAreaShowConditions = null;	
	
	private JScrollPane jScrollPaneShowConditions = null;


	/**
	 * This is the default constructor
	 */
	public Conditions() {
		//super();
	}
	
	public JPanel setJPanelConditions() {
		if (jPanelConditions == null) {
			jPanelConditions = new JPanel();
			jPanelConditions.setLayout(null);
			jPanelConditions.setSize(new Dimension(1172, 567));
			jPanelConditions.add(getJPanelSetParametersConditions(), null);
			jPanelConditions.add(getJPanelShowConditions(), null);
			jPanelConditions.add(getJPanelShowTopologyGraphConditions(), null);
		}
		return jPanelConditions;
	}

	/**
	 * This method initializes jPanelSetParametersConditions	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelSetParametersConditions() {
		if (jPanelSetParametersConditions == null) {
			jLabelSetParametersDemandedMinimumConfidence = new JLabel();
			jLabelSetParametersDemandedMinimumConfidence.setBounds(new Rectangle(13, 60, 328, 32));
			jLabelSetParametersDemandedMinimumConfidence.setText("Demanded Minimum Confidence  (>1)");
			jLabelSetParametersDemandedFrequency = new JLabel();
			jLabelSetParametersDemandedFrequency.setBounds(new Rectangle(15, 24, 327, 33));
			jLabelSetParametersDemandedFrequency.setText("Demanded Relation Percentage  (%)");
			jPanelSetParametersConditions = new JPanel();
			jPanelSetParametersConditions.setLayout(null);
			jPanelSetParametersConditions.setBounds(new Rectangle(15, 16, 508, 136));
			jPanelSetParametersConditions.setBorder(BorderFactory.createTitledBorder("Set Parameters for Discovering Conditions"));
			jPanelSetParametersConditions.add(jLabelSetParametersDemandedFrequency, null);
			jPanelSetParametersConditions.add(getJTextFieldSetParametersDemandedFrequency(), null);
			jPanelSetParametersConditions.add(getJButtonSetParameterRestoreDefaultValues(), null);
			jPanelSetParametersConditions.add(jLabelSetParametersDemandedMinimumConfidence, null);
			jPanelSetParametersConditions.add(getJTextFieldSetParametersDemandedMinimumLevel(), null);
			jPanelSetParametersConditions.add(getJButtonDiscoverConditions(), null);
		}
		return jPanelSetParametersConditions;
	}

	/**
	 * This method initializes jPanelShowConditions	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelShowConditions() {
		if (jPanelShowConditions == null) {
			jPanelShowConditions = new JPanel();
			jPanelShowConditions.setLayout(null);
			jPanelShowConditions.setBounds(new Rectangle(538, 17, 601, 135));
			jPanelShowConditions.setBorder(BorderFactory.createTitledBorder("Show Selected Conditions"));
			jPanelShowConditions.add(getJTextAreaShowConditions(), null);
			
		}
		return jPanelShowConditions;
	}

	/**
	 * This method initializes jPanelShowTopologyGraphConditions	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelShowTopologyGraphConditions() {
		if (jPanelShowTopologyGraphConditions == null) {
			jLabelSelectSequence = new JLabel();
			jLabelSelectSequence.setBounds(new Rectangle(13, 29, 119, 27));
			jLabelSelectSequence.setText("Select a Sequence");
			jPanelShowTopologyGraphConditions = new JPanel();
			jPanelShowTopologyGraphConditions.setLayout(null);
			jPanelShowTopologyGraphConditions.setBounds(new Rectangle(14, 163, 1127, 386));
			jPanelShowTopologyGraphConditions.setBorder(BorderFactory.createTitledBorder("Select Conditions to see"));
			jPanelShowTopologyGraphConditions.add(jLabelSelectSequence, null);
			jPanelShowTopologyGraphConditions.add(getJComboBoxSelectSequence(), null);
			//jPanelShowTopologyGraphConditions.add(getJSliderSelectGranularity(), null);
			jPanelShowTopologyGraphConditions.add(getJInternalFrameShowTopologyConditions(), null);
		}
		return jPanelShowTopologyGraphConditions;
	}

	/**
	 * This method initializes jTextFieldSetParametersDemandedFrequency	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldSetParametersDemandedFrequency() {
		if (jTextFieldSetParametersDemandedFrequency == null) {
			jTextFieldSetParametersDemandedFrequency = new JTextField();
			jTextFieldSetParametersDemandedFrequency.setBounds(new Rectangle(384, 25, 79, 32));
			jTextFieldSetParametersDemandedFrequency.setText("60");
		}
		return jTextFieldSetParametersDemandedFrequency;
	}

	/**
	 * This method initializes jButtonSetParameterRestoreDefaultValues	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonSetParameterRestoreDefaultValues() {
		if (jButtonSetParameterRestoreDefaultValues == null) {
			jButtonSetParameterRestoreDefaultValues = new JButton();
			jButtonSetParameterRestoreDefaultValues.setBounds(new Rectangle(85, 97, 171, 25));
			jButtonSetParameterRestoreDefaultValues.setText("Restore Default Values");
			jButtonSetParameterRestoreDefaultValues
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							jTextFieldSetParametersDemandedFrequency.setText("60");
							jTextFieldSetParametersDemandedMinimumLevel.setText("4");
						}
					});
		}
		return jButtonSetParameterRestoreDefaultValues;
	}

	/**
	 * This method initializes jTextFieldSetParametersDemandedMinimumLevel	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldSetParametersDemandedMinimumLevel() {
		if (jTextFieldSetParametersDemandedMinimumLevel == null) {
			jTextFieldSetParametersDemandedMinimumLevel = new JTextField();
			jTextFieldSetParametersDemandedMinimumLevel.setBounds(new Rectangle(384, 61, 79, 30));
			jTextFieldSetParametersDemandedMinimumLevel.setText("4");
		}
		return jTextFieldSetParametersDemandedMinimumLevel;
	}

	/**
	 * This method initializes jButtonDiscoverConditions	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonDiscoverConditions() {
		if (jButtonDiscoverConditions == null) {
			jButtonDiscoverConditions = new JButton();
			jButtonDiscoverConditions.setBounds(new Rectangle(279, 97, 183, 25));
			jButtonDiscoverConditions.setText("Discover Conditions");
			jButtonDiscoverConditions
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							Date d=new Date();
						    long s1,sTemp,s2;
						    d=new Date();
						    
							if (checkIsNumberFromXToY(jTextFieldSetParametersDemandedFrequency.getText(),0,100) && checkIsNumberFromXToY(jTextFieldSetParametersDemandedMinimumLevel.getText(),1,100)){
								DataStructure.getInstance().getConditionsDataStructure().setMinimumRelationPercentage(Integer.parseInt(jTextFieldSetParametersDemandedFrequency.getText()));
								DataStructure.getInstance().getConditionsDataStructure().setMinimumDemandedFrequencyForConditions(Integer.parseInt(jTextFieldSetParametersDemandedMinimumLevel.getText()));
								
								//initial Time
								s1=d.getTime();
								DataStructure.getInstance().getTopologyDataStructure().setNumFrequentSequences(DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size());
								
								for (int i = 0; i < DataStructure.getInstance().getTopologyDataStructure().getNumFrequentSequences(); i++){
									for (int j = 0; j < DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i).length; j++){
										ArrayList<Integer> listActions = new ArrayList<Integer>();
										listActions = DataStructure.getInstance().getConditionsDataStructure().getDisjunctionConditions().needDisjunctionConditions(DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j), DataStructure.getInstance().getConditionsDataStructure().getMinimumDemandedFrequencyForConditions(), DataStructure.getInstance().getConditionsDataStructure().getMinimumRelationPercentage());
										if (listActions.size() > 0){
											try{
												DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j).setDisjunctionConditions(DataStructure.getInstance().getConditionsDataStructure().getDisjunctionConditions().findOutDisjunctionConditions(DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j), DataStructure.getInstance().getSplitSequencesDataStructure().getSimpleEvents(), listActions));
												DataStructure.getInstance().getConditionsDataStructure().getDisjunctionConditions().findOutDisjunctionConditions(DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j), DataStructure.getInstance().getSplitSequencesDataStructure().getSimpleEvents(), listActions);
											}
											catch (Exception exc){
												System.err.println("Caught Exception: " + exc.getMessage());
											}
										}
									}
								}
								
								//Final Time
								d=new Date();
								sTemp=d.getTime();
								System.out.println("Execution time of <conditions> " + (sTemp - s1) + "  miliseconds");
								
								//initialize granularity slider
								int maximumGranularity = (int)DataStructure.getInstance().getSimplePatterns().get(0).getMaximumFrequencyFromStartToEnd();
								jPanelShowTopologyGraphConditions.add(getJSliderSelectGranularity(maximumGranularity), null);
								
								
								//initialize topology graph
								jScrollPaneShowTopologyGraphConditions = new JScrollPane(visualizeTopology (DataStructure.getInstance().getSimplePatterns().get(0), jSliderSelectGranularity.getValue()));
								jInternalFrameShowTopologyConditions.getContentPane().add(jScrollPaneShowTopologyGraphConditions);
								jInternalFrameShowTopologyConditions.setVisible(true);
								
								//initialize combo
								String[] tempFrequentSet = new String [DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size()];
								for (int i = 0; i < DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size(); i++){
									tempFrequentSet[i] = " Sequence " + i;
									jComboBoxSelectSequence.addItem(tempFrequentSet[i]);
								}
							}
							else{
								JOptionPane.showMessageDialog(jPanelConditions, "Please, insert valid values",
									    "Numeric warning",
									    JOptionPane.WARNING_MESSAGE);
							}
							createReport(DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences(), DataStructure.getInstance().getSimplePatterns());
							createReportLLFPUBS(DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences(), DataStructure.getInstance().getSimplePatterns());
							createMM(DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences(), DataStructure.getInstance().getSimplePatterns());
						}
					});
			
		}
		return jButtonDiscoverConditions;
	}
	
	private boolean checkIsNumberFromXToY (String text, int lowValue, int upValue){
		try{
			int tempValue = Integer.parseInt(text);
			if ((tempValue >= lowValue) && (tempValue <= upValue)){
				return true;
			}
			return false;
		}
		catch (NumberFormatException e){
			return false;
		}
	}

	/**
	 * This method initializes jComboBoxSelectSequence	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxSelectSequence() {
		if (jComboBoxSelectSequence == null) {
			jComboBoxSelectSequence = new JComboBox();
			jComboBoxSelectSequence.setBounds(new Rectangle(145, 29, 200, 27));
			jComboBoxSelectSequence.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//slider
					jPanelShowTopologyGraphConditions.remove(jSliderSelectGranularity);
					jSliderSelectGranularity = null;
					int maximumGranularity = (int)DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getMaximumFrequencyFromStartToEnd();
					jPanelShowTopologyGraphConditions.add(getJSliderSelectGranularity(maximumGranularity), null);
										
					//graph
					jInternalFrameShowTopologyConditions.getContentPane().remove(jScrollPaneShowTopologyGraphConditions);
					jScrollPaneShowTopologyGraphConditions = new JScrollPane (visualizeTopology (DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()), jSliderSelectGranularity.getValue()));
					jInternalFrameShowTopologyConditions.getContentPane().add(jScrollPaneShowTopologyGraphConditions);
					jInternalFrameShowTopologyConditions.updateUI();
				}
			});
		}
		return jComboBoxSelectSequence;
	}

	/**
	 * This method initializes jSliderSelectGranularity	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getJSliderSelectGranularity(int maximumGranularity) {
		if (jSliderSelectGranularity == null) {
			jSliderSelectGranularity = new JSlider(JSlider.HORIZONTAL,0,maximumGranularity,maximumGranularity/2);
			jSliderSelectGranularity.setBounds(new Rectangle(390, 23, 349, 43));
			jSliderSelectGranularity.setFont(new Font("Serif", Font.ITALIC, 12));
			jSliderSelectGranularity.setMajorTickSpacing(maximumGranularity/4);
			jSliderSelectGranularity.setMinorTickSpacing(1);
			jSliderSelectGranularity.setPaintTicks(true);
			jSliderSelectGranularity.setPaintLabels(true);
			jSliderSelectGranularity.setSnapToTicks(true);
			jSliderSelectGranularity
					.addChangeListener(new javax.swing.event.ChangeListener() {
						public void stateChanged(javax.swing.event.ChangeEvent e) {
							jInternalFrameShowTopologyConditions.getContentPane().remove(jScrollPaneShowTopologyGraphConditions);
							jScrollPaneShowTopologyGraphConditions = new JScrollPane (visualizeTopology (DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()), jSliderSelectGranularity.getValue()));
							jInternalFrameShowTopologyConditions.getContentPane().add(jScrollPaneShowTopologyGraphConditions);
							jInternalFrameShowTopologyConditions.updateUI();
						}
					});
		}
		return jSliderSelectGranularity;
	}

	/**
	 * This method initializes jInternalFrameShowTopologyConditions	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	private JInternalFrame getJInternalFrameShowTopologyConditions() {
		if (jInternalFrameShowTopologyConditions == null) {
			jInternalFrameShowTopologyConditions = new JInternalFrame();
			jInternalFrameShowTopologyConditions.setBounds(new Rectangle(14, 65, 1097, 310));
			jInternalFrameShowTopologyConditions.setContentPane(getJContentPaneShowTopologyConditions());
		}
		return jInternalFrameShowTopologyConditions;
	}

	/**
	 * This method initializes jContentPaneShowTopologyConditions	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPaneShowTopologyConditions() {
		if (jContentPaneShowTopologyConditions == null) {
			jContentPaneShowTopologyConditions = new JPanel();
			jContentPaneShowTopologyConditions.setLayout(new BorderLayout());
		}
		return jContentPaneShowTopologyConditions;
	}
	
	protected JGraph visualizeTopology (simplePattern simplePattern, int minimumFrequency){
		GraphModel model = new DefaultGraphModel();
		GraphLayoutCache view = new GraphLayoutCache(model, new DefaultCellViewFactory());
		JGraph graph = new JGraph(model, view);
		tempNodeCells.clear();
		tempRelationCells.clear();
		
		//set the TempNodeNameCells empty
		DataStructure.getInstance().getTimeRelationsDataStructure().setTempNodeNameCells(new ArrayList<String>());
				
					
		//nodes
		for (int i = 0; i < simplePattern.getTopologyNodes().size(); i++){
			tempNodeCells.add(initCell(findActionName(simplePattern.getTopologyNodes().get(i).getNode()),20,20));
			DataStructure.getInstance().getTimeRelationsDataStructure().getTempNodeNameCells().add(tempNodeCells.get(i).toString());
		}
		

		tempNodeCells.add(initCell("start",20,20));
		tempNodeCells.add(initCell("end",20,20));
		//relations
		for (int i = 0; i < simplePattern.getTopologyNodes().size(); i++){
			for (int j = 0; j < simplePattern.getTopologyNodes().get(i).getNextActions().size(); j++){
				if (simplePattern.getTopologyNodes().get(i).getNextActionsFrequency().get(j) > minimumFrequency){
					int find = findNode(simplePattern.getTopologyNodes().get(i).getNextActions().get(j),simplePattern);
					if (find == -1){
						tempRelationCells.add(initRelation(tempNodeCells.get(i), tempNodeCells.get(tempNodeCells.size()-1)));
					}
					else{
						tempRelationCells.add(initRelation(tempNodeCells.get(i), tempNodeCells.get(find)));
					}
				}
			}
			for (int j = 0; j < simplePattern.getTopologyNodes().get(i).getPreviousActions().size(); j++){
				if (simplePattern.getTopologyNodes().get(i).getPreviousActions().get(j).compareTo("start")==0){
					if (simplePattern.getTopologyNodes().get(i).getPreviousActionsFrequency().get(j) > minimumFrequency){
						tempRelationCells.add(initRelation(tempNodeCells.get(tempNodeCells.size()-2), tempNodeCells.get(i)));
					}
				}
			}
		}
		
		//include all them in cells[]
		DefaultGraphCell[] cells = new DefaultGraphCell[tempNodeCells.size()+tempRelationCells.size()];
		for (int i = 0; i < tempNodeCells.size(); i++){
			cells[i] = tempNodeCells.get(i);
		}
		for (int i = 0; i < tempRelationCells.size(); i++){
			cells[tempNodeCells.size()+i] = tempRelationCells.get(i);
		}
		
		//create the graphic
		graph.getGraphLayoutCache().insert(cells);
		Object[] roots = cells;
		JGraphFacade facade = new JGraphFacade(graph, roots); 
		JGraphHierarchicalLayout layout = new JGraphHierarchicalLayout();
		layout.setOrientation(SwingConstants.WEST);
		//JGraphTreeLayout layout = new JGraphTreeLayout();
		//JGraphSelfOrganizingOrganicLayout layout = new JGraphSelfOrganizingOrganicLayout();
		//JGraphOrganicLayout layout = new JGraphOrganicLayout();
		layout.run(facade); 
		Map nested = facade.createNestedMap(true, true); 
		graph.getGraphLayoutCache().edit(nested);
		//JFrame frame = new JFrame();
		//frame.getContentPane().add(new JScrollPane(graph));
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		//frame.setVisible(true);
		
		graph.addGraphSelectionListener(new org.jgraph.event.GraphSelectionListener() {
			public void valueChanged(GraphSelectionEvent e) {
				int indexCell = getIndexNodeCell(e.getCell().toString(), DataStructure.getInstance().getTimeRelationsDataStructure().getTempNodeNameCells());
				if (indexCell != -1){
					jTextAreaShowConditions.setText("");
					jTextAreaShowConditions.setLineWrap(true);
					jTextAreaShowConditions.setWrapStyleWord(true);
					jTextAreaShowConditions.append(newline);
					jTextAreaShowConditions.append("Node Name:  " + DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getNode());
					jTextAreaShowConditions.append(newline);
					jTextAreaShowConditions.append(newline);
					for (int i = 0; i < DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getNextActions().size(); i++){
						if ((DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getNextActionsFrequency().get(i) >= jSliderSelectGranularity.getValue()) && (DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getDisjunctionConditions().size() != 0)){
							jTextAreaShowConditions.append("  Next Action: " + DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getNextActions().get(i) +  " Frequency: " + DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getNextActionsFrequency().get(i));
							jTextAreaShowConditions.append(newline);
							writeConditions (DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getDisjunctionConditions().get(i), jTextAreaShowConditions);
							jTextAreaShowConditions.append(newline);
						}
					}
					
				}
			}
		});
		return graph;
	}
	
	public static void writeConditions (ArrayList<simpleCompleteCondition> conditions, JTextArea jTextAreaShowConditions){
		for (int i = 0; i < conditions.size(); i++){
			jTextAreaShowConditions.append("   Condition " + i);
			jTextAreaShowConditions.append(newline);
			for (int j = 0; j < conditions.get(i).getCompleteCondition().size(); j++){
				if (conditions.get(i).getCompleteCondition().get(j)._class.compareTo("numeric")==0){
					jTextAreaShowConditions.append("     " + ((simpleBasicConditionNumeric)conditions.get(i).getCompleteCondition().get(j)).name + " " + ((simpleBasicConditionNumeric)conditions.get(i).getCompleteCondition().get(j)).symbol + ((simpleBasicConditionNumeric)conditions.get(i).getCompleteCondition().get(j)).value);
				}
				else{
					jTextAreaShowConditions.append("     " + ((simpleBasicConditionNominal)conditions.get(i).getCompleteCondition().get(j)).name + " " + ((simpleBasicConditionNominal)conditions.get(i).getCompleteCondition().get(j)).symbol + ((simpleBasicConditionNominal)conditions.get(i).getCompleteCondition().get(j)).value);
				}
			}
			jTextAreaShowConditions.append(" THEN " + conditions.get(i).getConsequent()+ "   ORDER (" + conditions.get(i).getOrder() + ")");
			jTextAreaShowConditions.append(newline);
		}
	}
	
	
	public static DefaultGraphCell initCell (String name, int x1, int y1){
		DefaultGraphCell cell = new DefaultGraphCell(new String(name));
		GraphConstants.setBounds(cell.getAttributes(), new Rectangle2D.Double(x1,y1,60,30));
		GraphConstants.setBorderColor(cell.getAttributes(), Color.getHSBColor(0.63f,0.6464f,1.0f));
		GraphConstants.setBackground(cell.getAttributes(), Color.getHSBColor(0.644f, 0.3636f, 1.0f)); //to calculate http://home.comcast.net/~ed-abramson/14ColorTest/HSB-and-RGB-Colors.html
		GraphConstants.setOpaque(cell.getAttributes(), true);
		GraphConstants.setFont(cell.getAttributes(), GraphConstants.DEFAULTFONT.deriveFont(6));
		DefaultPort port = new DefaultPort();
		cell.add(port);
		
		
		return cell;
	}
	
	public static DefaultGraphCell initRelation (DefaultGraphCell cell0, DefaultGraphCell cell1){
		DefaultGraphCell relation = new DefaultGraphCell();
		DefaultEdge edge = new DefaultEdge();
		
		edge.setSource(cell0.getChildAt(0));
		edge.setTarget(cell1.getChildAt(0));
		relation = edge;
		int arrow = GraphConstants.ARROW_CLASSIC;
		GraphConstants.setLineEnd(edge.getAttributes(), arrow);
		GraphConstants.setEndFill(edge.getAttributes(), true);
		GraphConstants.setLineColor(edge.getAttributes(), Color.getHSBColor(0.63f,0.6464f,1.0f));
		GraphConstants.setLabelAlongEdge(edge.getAttributes(), true);
		
		return relation;
	}

	public static int findNode (String name, simplePattern simplePattern){
		
		if (name.compareTo("end")==0){
			return -1;
		}
		
		for (int i = 0; i < simplePattern.getTopologyNodes().size(); i++){
			if (name.compareTo(simplePattern.getTopologyNodes().get(i).getNode()) == 0){
				return i;
			}
		}
		return -2;
	}
	
	public static int getIndexNodeCell(String name, ArrayList<String>nameList){
		for (int i = 0; i < nameList.size(); i++){
			if (name != null){
				if (name.compareTo(nameList.get(i))== 0){
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * This method initializes jTextAreaShowConditions	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JScrollPane getJTextAreaShowConditions() {
		if (jTextAreaShowConditions == null) {
			jTextAreaShowConditions = new JTextArea();
			//jTextAreaShowConditions.setBounds(new Rectangle(9, 20, 581, 105));
			jScrollPaneShowConditions = new JScrollPane(jTextAreaShowConditions);
			jScrollPaneShowConditions.setBounds(new Rectangle(15, 20, 573, 103));
			jScrollPaneShowConditions.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jScrollPaneShowConditions.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		}
		return jScrollPaneShowConditions;
	}
	
public static void createReport (ArrayList<simpleSequence> sequences, ArrayList<simplePattern> simplePatterns){
	
	try{
		BufferedWriter bw = new BufferedWriter(new FileWriter("result\\result.txt"));
		PrintWriter writer = new PrintWriter(bw);
		
		writer.println();
		for (int i = 0; i < simpleEvent.getEventLabel().size(); i++){
			writer.println ("      " + simpleEvent.getEventLabel().get(i).getDevice() + ", " + simpleEvent.getEventLabel().get(i).getAction() + " -->" + i); 
		}
		
		writer.println();
		
		for (int i = 0; i < sequences.size(); i++){
			writer.println("Sequence " + i);
			writer.println("	Basic actions " + translateActionsToString(sequences.get(i).getSequence()).toString());
			writer.println("	Basic instances (" + sequences.get(i).getInstances().size() + "): " + sequences.get(i).getInstances().toString());
			writer.println(" 	Extra actions " + translateActionsToString(sequences.get(i).getLongExtraActions()).toString());
			writer.println("	Extra instances (" + sequences.get(i).getShortExtraInstances().size() + "): " +  sequences.get(i).getShortExtraInstances().toString());
			writeTopology(simplePatterns.get(i), writer);
		}
		writer.close();
		
	}
	catch(Exception error){
		
		System.out.println("Error Message: " + error.getMessage());
		
	}		
	}

public static void createReportLLFPUBS (ArrayList<simpleSequence> sequences, ArrayList<simplePattern> simplePatterns){
	
	try{
		BufferedWriter bw = new BufferedWriter(new FileWriter("result\\resultLLPUBS.txt"));
		PrintWriter writer = new PrintWriter(bw);
		
		
		writer.println();
		
		for (int i = 0; i < sequences.size(); i++){
			writer.println("Action Map " + i);
			writer.println();
			writer.println("(General Conditions)");
			writer.println();
			//writer.println("	Basic actions " + translateActionsToString(sequences.get(i).getSequence()).toString());
			//writer.println("	Basic instances (" + sequences.get(i).getInstances().size() + "): " + sequences.get(i).getInstances().toString());
			//writer.println(" 	Extra actions " + translateActionsToString(sequences.get(i).getLongExtraActions()).toString());
			//writer.println("	Extra instances (" + sequences.get(i).getShortExtraInstances().size() + "): " +  sequences.get(i).getShortExtraInstances().toString());
			writeTopologyLLFPUBS(simplePatterns.get(i), writer);
		}
		writer.close();
		
	}
	catch(Exception error){
		
		System.out.println("Error Message: " + error.getMessage());
		
	}		
	}
	
	public static void writeTopology (simplePattern oneSimplePattern, PrintWriter writer){
		
		for (int index = 0; index < oneSimplePattern.getTopologyNodes().size(); index++){
			//writer.println("        action " + oneSimplePattern.getTopologyNodes().get(index).getNode() + " type of node " + oneSimplePattern.getTopologyNodes().get(index).getTypeNode() + " components " + oneSimplePattern.getTopologyNodes().get(index).getComponentsNode());
			writer.println("        Action: " + translateCompositeActionsToString(oneSimplePattern.getTopologyNodes().get(index).getNode()) + "; Type of node: " + oneSimplePattern.getTopologyNodes().get(index).getTypeNode() + "; Components " + translateComponentsToString(oneSimplePattern.getTopologyNodes().get(index).getComponentsNode()));
			for (int i = 0; i < oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().size(); i++){
				//writer.println("          previous action " + oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i) + " count " + oneSimplePattern.getTopologyNodes().get(index).getPreviousActionsFrequency().get(i) + " instances " + oneSimplePattern.getTopologyNodes().get(index).getIndexSimpleEvents().get(i).toString() + " and previous " + oneSimplePattern.getTopologyNodes().get(index).getIndexSimpleEventsPrevious().get(i).toString());
				writer.println("          Previous action " + translateCompositeActionsToString(oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i)) + " count " + oneSimplePattern.getTopologyNodes().get(index).getPreviousActionsFrequency().get(i));
				if (oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i).compareTo("start") != 0){
					for (int j = 0; j < oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).size(); j++){
						writer.println("               Time relation " + j + ": " + oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).get(j).getSimpleTimeRelation() + " Particular instances " + "(" + oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).get(j).getInstances().size() + "/" + oneSimplePattern.getTopologyNodes().get(index).getPreviousActionsFrequency().get(i) + ") : " + oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).get(j).getInstances().toString());
					}
				}				
			}
			for (int i = 0; i < oneSimplePattern.getTopologyNodes().get(index).getNextActions().size(); i++){
				//writer.println("          next action " + oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i) + " count " + oneSimplePattern.getTopologyNodes().get(index).getNextActionsFrequency().get(i) + " instances " + oneSimplePattern.getTopologyNodes().get(index).getIndexSimpleEventsOfNext().get(i).toString() + " and next " + oneSimplePattern.getTopologyNodes().get(index).getIndexSimpleEventsNextNext().get(i).toString());
				writer.println("          Next action " + translateCompositeActionsToString(oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i)) + " count " + oneSimplePattern.getTopologyNodes().get(index).getNextActionsFrequency().get(i));
				if (oneSimplePattern.getTopologyNodes().get(index).getDisjunctionConditions().size()!=0){
					writeConditions (oneSimplePattern.getTopologyNodes().get(index).getDisjunctionConditions().get(i), writer);
				}
			}
			writer.println();
		}
	}
	
	public static void writeTopologyLLFPUBS (simplePattern oneSimplePattern, PrintWriter writer){
		
		int numActionPattern = 0;
		
		for (int index = 0; index < oneSimplePattern.getTopologyNodes().size(); index++){
			
			//writer.println("        Action: " + translateCompositeActionsToString(oneSimplePattern.getTopologyNodes().get(index).getNode()) + "; Type of node: " + oneSimplePattern.getTopologyNodes().get(index).getTypeNode() + "; Components " + translateComponentsToString(oneSimplePattern.getTopologyNodes().get(index).getComponentsNode()));
			for (int i = 0; i < oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().size(); i++){
				if (translateCompositeActionsToString(oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i)).compareTo("start") == 0){
					writer.println("(Action Pattern " + numActionPattern + ")");
					writer.println("ON occurs (start,--,t0) Frequency: " + oneSimplePattern.getTopologyNodes().get(index).getPreviousActionsFrequency().get(i));
					writer.println("IF context ()");
					writer.print("THEN do ");
					writeActionLLFPUBS (oneSimplePattern.getTopologyNodes().get(index), -1, writer);
					writer.println();
					numActionPattern++;
				}
				
				/*
				writer.println("          Previous action " + translateCompositeActionsToString(oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i)) + " count " + oneSimplePattern.getTopologyNodes().get(index).getPreviousActionsFrequency().get(i));
				if (oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i).compareTo("start") != 0){
					for (int j = 0; j < oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).size(); j++){
						writer.println("               Time relation " + j + ": " + oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).get(j).getSimpleTimeRelation() + " Particular instances " + "(" + oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).get(j).getInstances().size() + "/" + oneSimplePattern.getTopologyNodes().get(index).getPreviousActionsFrequency().get(i) + ") : " + oneSimplePattern.getTopologyNodes().get(index).getTimeRelations().get(i).get(j).getInstances().toString());
					}
				}
				*/				
			}
			
			for (int i = 0; i < oneSimplePattern.getTopologyNodes().get(index).getNextActions().size(); i++){
				writer.println("(Action Pattern " + numActionPattern + ")");
				writer.print("ON occurs ");
				writeEventLLFPUBS(oneSimplePattern.getTopologyNodes().get(index), writer, oneSimplePattern.getTopologyNodes().get(index).getNextActionsFrequency().get(i));
				writer.print("IF context (");
				if (oneSimplePattern.getTopologyNodes().get(index).getDisjunctionConditions().size()!=0){
					writeConditionsLLFPUBS (oneSimplePattern.getTopologyNodes().get(index).getDisjunctionConditions().get(i), writer);
				}
				writer.println(")");
				writer.print("THEN do ");
				if (oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i).compareTo("end")==0){
					writer.print("(--,end,t) when --");
				}
				else{
					double timeRelation;
					timeRelation = findTimeRelations (oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i),oneSimplePattern.getTopologyNodes().get(index).getNode(), oneSimplePattern.getTopologyNodes());
					
					writeActionLLFPUBS (getTopologyNode(oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i), oneSimplePattern.getTopologyNodes()),timeRelation,writer);
				}
				/*
				writer.println("          Next action " + translateCompositeActionsToString(oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i)) + " count " + oneSimplePattern.getTopologyNodes().get(index).getNextActionsFrequency().get(i));
				if (oneSimplePattern.getTopologyNodes().get(index).getDisjunctionConditions().size()!=0){
					writeConditions (oneSimplePattern.getTopologyNodes().get(index).getDisjunctionConditions().get(i), writer);
				}
				*/
				writer.println();
				numActionPattern++;
			}
			writer.println();
		}
	}	
	
	public static void writeConditions (ArrayList<simpleCompleteCondition> conditions, PrintWriter writer){
		
		for (int i = 0; i < conditions.size(); i++){
			writer.println("                 Condition " + i);
			writer.print("                    ");
			for (int j = 0; j < conditions.get(i).getCompleteCondition().size(); j++){
				if (conditions.get(i).getCompleteCondition().get(j)._class.compareTo("numeric")==0){
					writeNumericCondition ((simpleBasicConditionNumeric)conditions.get(i).getCompleteCondition().get(j), writer);
				}
				else{
					writeNominalCondition ((simpleBasicConditionNominal)conditions.get(i).getCompleteCondition().get(j), writer);
				}
			}
			writer.println(" THEN " + translateCompositeActionsToString(conditions.get(i).getConsequent()) + "   ORDER (" + conditions.get(i).getOrder() + ")");
		}
	}
	
		public static void writeNumericCondition (simpleBasicConditionNumeric basicCondition, PrintWriter writer){
			
			writer.print(basicCondition.name + " " + basicCondition.symbol + " " + basicCondition.value + "   ");
		}
		
		public static void writeNominalCondition (simpleBasicConditionNominal basicCondition, PrintWriter writer){
			
			writer.print(basicCondition.name + " " + basicCondition.symbol + " " + basicCondition.value + "   ");
		}
		
	public static ArrayList<String> translateActionsToString (ArrayList<Integer> actionsInteger){
		
		ArrayList<String> actionsString = new ArrayList<String> ();
		
		for (int i = 0; i < actionsInteger.size(); i++){
			String device = simpleEvent.getEventLabel().get(actionsInteger.get(i)).getDevice();
			String action = simpleEvent.getEventLabel().get(actionsInteger.get(i)).getAction();
			String tempAction = device + " "+ action;
			actionsString.add(tempAction);
		}
		
		return actionsString;
		
	}
	
	public static String translateCompositeActionsToString (String compositeActionsString){
		String actionsString = new String ();
		
		if ((compositeActionsString.compareTo("start")== 0) || (compositeActionsString.compareTo("end")== 0)){
			return compositeActionsString;
		}
		
		int pos = compositeActionsString.indexOf("_",0);
		String tempAction = compositeActionsString.substring(0, pos);
		
		if (tempAction.compareTo("cluster") != 0){
			String device = simpleEvent.getEventLabel().get(Integer.parseInt(tempAction)).getDevice();
			String action = simpleEvent.getEventLabel().get(Integer.parseInt(tempAction)).getAction();
			actionsString = device + " " + action + " (" + compositeActionsString.substring(pos + 1, compositeActionsString.length()) + ")";

		}
		else{
			actionsString = compositeActionsString;
		}
				
		return actionsString;		
	}
	
	public static String translateCompositeActionsToStringLLFPUBS (String compositeActionsString){
		String actionsString = new String ();
		
		if ((compositeActionsString.compareTo("start")== 0) || (compositeActionsString.compareTo("end")== 0)){
			return compositeActionsString;
		}
		
		int pos = compositeActionsString.indexOf("_",0);
		String tempAction = compositeActionsString.substring(0, pos);
		
		String device = simpleEvent.getEventLabel().get(Integer.parseInt(tempAction)).getDevice();
		String action = simpleEvent.getEventLabel().get(Integer.parseInt(tempAction)).getAction();
		actionsString = action + "," + device + " (" + compositeActionsString.substring(pos + 1, compositeActionsString.length()) + ")";
						
		return actionsString;		
	}
	
	public static ArrayList<String> translateComponentsToString (ArrayList<String> componentsString){
		
		ArrayList<String> stringsOfComponents = new ArrayList<String>();
		
		for (int i = 0; i < componentsString.size(); i++){
			String tempComponent = translateCompositeActionsToString(componentsString.get(i));
			if (isComponentWithinComponents(tempComponent, stringsOfComponents)){ //it exists previously
				
			}
			else{
				stringsOfComponents.add(tempComponent);
			}
			
		}
		
		return stringsOfComponents;
	}
	
		public static boolean isComponentWithinComponents(String tempComponent, ArrayList<String> stringsOfComponents){
			
			for (int i = 0; i < stringsOfComponents.size(); i++){
				if (tempComponent.compareTo(stringsOfComponents.get(i)) == 0){
					return true;
				}
			}
			
			return false;
			
		}
	
		private String findActionName(String name){
			String temp ="", last="";
			
			temp = name.substring(0, name.indexOf("_"));
			
			if (temp.compareTo("cluster") != 0){
				last = simpleEvent.getEventLabel().get(Integer.parseInt(temp)).getDevice() + " " + simpleEvent.getEventLabel().get(Integer.parseInt(temp)).getAction();
				return last;
			}
			
			
			return name;
		}
		
	public static void writeEventLLFPUBS (simpleNode simpleNode, PrintWriter writer, int frequency){
		
		if (simpleNode.getTypeNode().compareTo("cluster") != 0){
			writer.print("(simple,(" + translateCompositeActionsToStringLLFPUBS(simpleNode.getNode()) + ") , t0) Frequency: " + frequency);			
		}
		else{
			writer.print("(unordered,(");
			for (int i = 0; i < simpleNode.getComponentsNode().size()-1; i++){
				writer.print("(" + translateCompositeActionsToStringLLFPUBS(simpleNode.getComponentsNode().get(i)) + ") & ");				
			}
			writer.print("(" + translateCompositeActionsToStringLLFPUBS(simpleNode.getComponentsNode().get(simpleNode.getComponentsNode().size() - 1)) + ")), t0) Frequency: " + frequency);
		}
		writer.println();		
	}
	
	public static void writeConditionsLLFPUBS (ArrayList<simpleCompleteCondition> conditions, PrintWriter writer){
		
		for (int i = 0; i < conditions.size(); i++){
			writer.print("( ");
			for (int j = 0; j < conditions.get(i).getCompleteCondition().size(); j++){
				writer.print("(");
				if (conditions.get(i).getCompleteCondition().get(j)._class.compareTo("numeric")==0){
					writeNumericConditionLLFPUBS ((simpleBasicConditionNumeric)conditions.get(i).getCompleteCondition().get(j), writer);
				}
				else{
					writeNominalConditionLLFPUBS ((simpleBasicConditionNominal)conditions.get(i).getCompleteCondition().get(j), writer);
				}
				writer.print(")");
				if (j != conditions.get(i).getCompleteCondition().size()-1){
					writer.print(" AND ");
				}
			}
			writer.print(") (Priority: " + conditions.get(i).getOrder() + ")");
			if (i != conditions.size()-1){
				writer.print(" OR ");
			}
		}
	}
	
		public static void writeNumericConditionLLFPUBS (simpleBasicConditionNumeric basicCondition, PrintWriter writer){
			
			writer.print(basicCondition.name + "(" + basicCondition.symbol + "," + basicCondition.value + ")");

		}
		
		public static void writeNominalConditionLLFPUBS (simpleBasicConditionNominal basicCondition, PrintWriter writer){
			
			writer.print(basicCondition.name + "(" + basicCondition.symbol + "," + basicCondition.value + ")");
		}
		
	public static void writeActionLLFPUBS (simpleNode simpleNode, double timeRelation, PrintWriter writer){
		
		if (simpleNode.getTypeNode().compareTo("cluster") != 0){
			writer.print("(simple,(" + translateCompositeActionsToStringLLFPUBS(simpleNode.getNode()) + ") , t) when ");
		}
		else{
			writer.print("(unordered,(");
			for (int i = 0; i < simpleNode.getComponentsNode().size()-1; i++){
				writer.print("(" + translateCompositeActionsToStringLLFPUBS(simpleNode.getComponentsNode().get(i)) + ") & ");				
			}
			writer.print("(" + translateCompositeActionsToStringLLFPUBS(simpleNode.getComponentsNode().get(simpleNode.getComponentsNode().size() - 1)) + ")), t) when ");
		}
		
		if (timeRelation == -1){
			writer.print("--");
		}
		else if (timeRelation == -2){
			writer.print("t is after t0 ");
		}
		else{
			writer.print("t = t0 + " + timeRelation + " s.");
		}
		
		writer.println();		
	}
	
	public static simpleNode getTopologyNode (String str, ArrayList<simpleNode> topologyNodes){
				
		for (int i = 0; i < topologyNodes.size(); i++){
			if (topologyNodes.get(i).getNode().compareTo(str) == 0){
				return topologyNodes.get(i);
			}
		}
		return null;
	}
	
	public static double findTimeRelations (String strNextAction, String strPreviousAction, ArrayList<simpleNode> topologyNodes){
		
		double tempTimeRelation;
		ArrayList<Double> tempTimeRelations = new ArrayList<Double> ();
		simpleNode tempNextAction = new simpleNode (getTopologyNode(strNextAction,topologyNodes));
		simpleNode tempPreviousAction = new simpleNode (getTopologyNode(strPreviousAction,topologyNodes));
		
		for (int i = 0; i < tempNextAction.getPreviousActions().size(); i++){
			if (tempNextAction.getPreviousActions().get(i).compareTo(tempPreviousAction.getNode()) == 0){
				for (int j = 0; j < tempNextAction.getTimeRelations().get(i).size(); j++){
					tempTimeRelations.add(tempNextAction.getTimeRelations().get(i).get(j).getSimpleTimeRelation());
				}
			}
		}
		
		if (tempTimeRelations.size() > 0){
			tempTimeRelation = tempTimeRelations.get(0);
		}
		else{
			tempTimeRelation = -2;
		}
		
		return tempTimeRelation;		
	}
	
public static void createMM(ArrayList<simpleSequence> sequences, ArrayList<simplePattern> simplePatterns){
	
	try{
				
		deleteDirectory(new File("result\\markovmodels"));

		for (int i = 0; i < sequences.size(); i++){
			String str = "result\\markovmodels\\MM" + i + ".txt"; 
			BufferedWriter bw = new BufferedWriter(new FileWriter(str));
			PrintWriter writer = new PrintWriter(bw);
			writeMM(simplePatterns.get(i), writer);
			writer.close();
		}	
	}
	catch(Exception error){
		
		System.out.println("Error Message: " + error.getMessage());
		
	}	
	
}

	public static void writeMM(simplePattern oneSimplePattern, PrintWriter writer){
		
		int numNodes = oneSimplePattern.getTopologyNodes().size() + 2; //nodes + start + end
		writeNodesMM(oneSimplePattern,writer);
		int [][] maxTransition = new int [numNodes][numNodes];
		double [][] transitionMatrix = new double [numNodes][numNodes];
		
		//initialize maxTransition to 0
		for (int i = 0; i < numNodes; i++){
			for (int j = 0; j < numNodes; j++){
				maxTransition [i][j] = 0;
			}
		}
		
		for (int index = 0; index < oneSimplePattern.getTopologyNodes().size(); index++){
			for (int i = 0; i < oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().size(); i++){
				if (translateCompositeActionsToString(oneSimplePattern.getTopologyNodes().get(index).getPreviousActions().get(i)).compareTo("start") == 0){
					maxTransition [0] [index+1] = oneSimplePattern.getTopologyNodes().get(index).getPreviousActionsFrequency().get(i);
				}
			}
			for (int i = 0; i < oneSimplePattern.getTopologyNodes().get(index).getNextActions().size(); i++){
				if (oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i).compareTo("end")==0){
					//add to end
					maxTransition [index+1][numNodes-1] = oneSimplePattern.getTopologyNodes().get(index).getNextActionsFrequency().get(i);
				}
				else{
					int pos = findNodePosition (oneSimplePattern.getTopologyNodes().get(index).getNextActions().get(i),oneSimplePattern.getTopologyNodes());
					maxTransition [index+1][pos+1] = maxTransition[index+1][pos+1] + oneSimplePattern.getTopologyNodes().get(index).getNextActionsFrequency().get(i);
				}
			}
		}
		
		for (int i = 0; i < maxTransition.length; i++){
			int total = 0;
			for (int j = 0; j < maxTransition[i].length; j++){
				total = total + maxTransition[i][j];
			}
			for (int j = 0; j < maxTransition[i].length; j++){
				if (total != 0){
					transitionMatrix [i][j] = (double) maxTransition[i][j]/total;
				}
				else{
					transitionMatrix [i][j] = 0;
				}
			}
		}
		
		for (int i = 0; i < transitionMatrix.length; i++){
			for (int j = 0; j < transitionMatrix[i].length; j++){
				writer.print(transitionMatrix[i][j] + " ");
			}
			writer.println();
		}
		
	}
	
		public static int findNodePosition (String name, ArrayList<simpleNode> nodes){
					
			for (int i = 0; i < nodes.size(); i++){
				if (name.compareTo(nodes.get(i).getNode()) == 0){
					return i;
				}
			}
			
			return -1;
		}
		
		public static void deleteDirectory(File path) {
					
		    if( path.exists() ) {
		      File[] files = path.listFiles();
		      for(int i=0; i<files.length; i++) {
		         if(files[i].isDirectory()) {
		           deleteDirectory(files[i]);
		         }
		         else {
		           files[i].delete();
		         }
		      }
		    }
		}
		
		public static void writeNodesMM (simplePattern oneSimplePattern, PrintWriter writer){
			
			writer.print("start ");
			for (int i = 0; i < oneSimplePattern.getTopologyNodes().size(); i++){
				writer.print( writeSimpleNodeMM (oneSimplePattern.getTopologyNodes().get(i))+ " ");
			}
			writer.println("end");
		}
		
		public static String writeSimpleNodeMM (simpleNode simpleNode){
			
			if (simpleNode.getTypeNode().compareTo("cluster") != 0){
				return ("(simple,(" + translateCompositeActionsToStringLLFPUBS(simpleNode.getNode()) + ")");
			}
			else{
				String tempStr;
				tempStr = "(unordered,(";
				for (int i = 0; i < simpleNode.getComponentsNode().size()-1; i++){
					tempStr = tempStr + "(" + translateCompositeActionsToStringLLFPUBS(simpleNode.getComponentsNode().get(i)) + ") & ";				
				}
				tempStr = tempStr + "(" + translateCompositeActionsToStringLLFPUBS(simpleNode.getComponentsNode().get(simpleNode.getComponentsNode().size() - 1)) + ")";
				return tempStr;
			}		
		}

}
