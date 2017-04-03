package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import basic.DataStructure;
import basic.simpleNode;
import basic.simplePattern;
import splitSequences.simpleEvent;
import splitSequences.fullSequences;
import timeRelations.simpleTimeRelation;
import frequentSequences.simpleSequence;
import javax.swing.JInternalFrame;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;
import org.jgraph.event.GraphSelectionListener;
import org.jgraph.event.GraphSelectionEvent;


import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class TimeRelations extends JFrame {
	
	private final static String newline = "\n";  //  @jve:decl-index=0:

	private static final long serialVersionUID = 1L;

	private JPanel jPanelTimeRelations = null;

	private JPanel jPanelSetParametersValues = null;

	private JLabel jLabelMinimumFrequency = null;

	private JTextField jTextFieldMinimumFrequency = null;

	private JLabel jLabelDeviation = null;

	private JTextField jTextFieldDeviation = null;

	private JButton jButtonDiscoverTimeRelations = null;

	private JPanel jPanelSelectTimeRelation = null;

	private JLabel jLabelSelectSequence = null;

	private JComboBox jComboBoxSelectSequence = null;

	private JSlider jSliderSelectTimeRelation = null;

	private JButton jButtonSetParameterRestoreDefaultValues = null;

	private JInternalFrame jInternalFrameShowTopologyGraphTimeRelation = null;

	private JPanel jContentPaneShowTopologyGraphTimeRelation = null;
	
	private JScrollPane jScrollPaneShowTopologyGraphTimeRelation = null;

	private JPanel jPanelShowTimeRelation = null;

	ArrayList<DefaultGraphCell> tempNodeCells = new ArrayList<DefaultGraphCell>();  //  @jve:decl-index=0:
	
	ArrayList<DefaultGraphCell> tempRelationCells = new ArrayList<DefaultGraphCell>();  //  @jve:decl-index=0:

	private JTextArea jTextAreaSeeSelectedTimeRelation = null;
	
	private JScrollPane jScrollPaneShowTimeRelations = null;

	private JRadioButtonMenuItem jRadioButtonMenuItemEMAlgorithm = null;

	private JRadioButtonMenuItem jRadioButtonMenuItemBasicAlgorithm = null;
	
	private ButtonGroup SelectAlgorithmButtonGroup = new ButtonGroup();  //  @jve:decl-index=0:

	/**
	 * This is the default constructor
	 */
	public TimeRelations() {
		//super();
	}

	
		public JPanel setJPanelTimeRelations() {
		if (jPanelTimeRelations == null) {
			jPanelTimeRelations = new JPanel();
			jPanelTimeRelations.setLayout(null);
			jPanelTimeRelations.setSize(new Dimension(1172, 567));
			jPanelTimeRelations.add(getJPanelSetParametersValues(), null);
			jPanelTimeRelations.add(getJPanelSelectTimeRelation(), null);
			jPanelTimeRelations.add(getJPanelShowTimeRelation(), null);
		}
		return jPanelTimeRelations;
	}


		/**
		 * This method initializes jPanelSetParametersValues	
		 * 	
		 * @return javax.swing.JPanel	
		 */
		private JPanel getJPanelSetParametersValues() {
			if (jPanelSetParametersValues == null) {
				jLabelDeviation = new JLabel();
				jLabelDeviation.setBounds(new Rectangle(13, 90, 328, 32));
				jLabelDeviation.setText("Allowed Deviation for Time Relations  (%)");
				jLabelDeviation.setVisible(false);
				jLabelMinimumFrequency = new JLabel();
				jLabelMinimumFrequency.setBounds(new Rectangle(14, 23, 350, 33));
				jLabelMinimumFrequency.setText("Demanded Minimum Confidence level for a Time Relation  (%)");
				jPanelSetParametersValues = new JPanel();
				jPanelSetParametersValues.setLayout(null);
				jPanelSetParametersValues.setBounds(new Rectangle(15, 16, 508, 158));
				jPanelSetParametersValues.add(jLabelMinimumFrequency, null);
				jPanelSetParametersValues.add(getJTextFieldMinimumFrequency(), null);
				jPanelSetParametersValues.add(jLabelDeviation, null);
				jPanelSetParametersValues.add(getJTextFieldDeviation(), null);
				jPanelSetParametersValues.add(getJButtonDiscoverTimeRelations(), null);
				jPanelSetParametersValues.setBorder(BorderFactory.createTitledBorder("Set Parameters for Discovering Time Relations"));
				jPanelSetParametersValues.add(getJButtonSetParameterRestoreDefaultValues(), null);
				jPanelSetParametersValues.add(getJRadioButtonMenuItemEMAlgorithm(), null);
				jPanelSetParametersValues.add(getJRadioButtonMenuItemBasicAlgorithm(), null);
				SelectAlgorithmButtonGroup.add(jRadioButtonMenuItemEMAlgorithm);
				SelectAlgorithmButtonGroup.add(jRadioButtonMenuItemBasicAlgorithm);
			}
			return jPanelSetParametersValues;
		}


		/**
		 * This method initializes jTextFieldMinimumFrequency	
		 * 	
		 * @return javax.swing.JTextField	
		 */
		private JTextField getJTextFieldMinimumFrequency() {
			if (jTextFieldMinimumFrequency == null) {
				jTextFieldMinimumFrequency = new JTextField();
				jTextFieldMinimumFrequency.setBounds(new Rectangle(383, 24, 79, 32));
				jTextFieldMinimumFrequency.setText("50");
			}
			return jTextFieldMinimumFrequency;
		}


		/**
		 * This method initializes jTextFieldDeviation	
		 * 	
		 * @return javax.swing.JTextField	
		 */
		private JTextField getJTextFieldDeviation() {
			if (jTextFieldDeviation == null) {
				jTextFieldDeviation = new JTextField();
				jTextFieldDeviation.setBounds(new Rectangle(384, 91, 79, 30));
				jTextFieldDeviation.setText("75");
				jTextFieldDeviation.setVisible(false);
			}
			return jTextFieldDeviation;
		}


		/**
		 * This method initializes jButtonDiscoverTimeRelations	
		 * 	
		 * @return javax.swing.JButton	
		 */
		private JButton getJButtonDiscoverTimeRelations() {
			if (jButtonDiscoverTimeRelations == null) {
				jButtonDiscoverTimeRelations = new JButton();
				jButtonDiscoverTimeRelations.setBounds(new Rectangle(278, 126, 183, 25));
				jButtonDiscoverTimeRelations.setText("Discover Time Relations");
				jButtonDiscoverTimeRelations
						.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(java.awt.event.ActionEvent e) {
								Date d=new Date();
							    long s1,sTemp,s2;
							    d=new Date();
							    
								if (checkIsNumberFromXToY(jTextFieldMinimumFrequency.getText(),0,100) && checkIsNumberFromXToY(jTextFieldDeviation.getText(),0,100)){
									DataStructure.getInstance().getTimeRelationsDataStructure().setMinimumFrequencyTimeRelation(Double.parseDouble(jTextFieldMinimumFrequency.getText()) / 100);
									DataStructure.getInstance().getTimeRelationsDataStructure().setDeviationAverageTimeRelation(Double.parseDouble(jTextFieldDeviation.getText()) / 100);
									//initial Time
									s1=d.getTime();
									DataStructure.getInstance().getTopologyDataStructure().setNumFrequentSequences(DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size());
									
									for (int i = 0; i < DataStructure.getInstance().getTopologyDataStructure().getNumFrequentSequences(); i++){
										//System.out.println("sequence number " + i);
										for (int j = 0; j < DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i).length; j++){
											//System.out.println("   activity " + j);
											for (int k = 0; k < DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i)[j].getPreviousActions().size(); k++){
												//System.out.println("      previous action " + k);
												if (DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i)[j].getPreviousActions().get(k).compareTo("start") != 0){
													ArrayList<Double> tempTimeDistances = DataStructure.getInstance().getTimeRelationsDataStructure().getSimpleTimeRelation().getTimeDistances(DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i)[j].getIndexSimpleEvents().get(k), DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i)[j].getIndexSimpleEventsPrevious().get(k), DataStructure.getInstance().getSplitSequencesDataStructure().getSimpleEvents());
													if (DataStructure.getInstance().getTimeRelationsDataStructure().getSelectedAlgorithm() == 0){ //EM algorithm
														DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j).setParticularTimeRelations(DataStructure.getInstance().getTimeRelationsDataStructure().getSimpleTimeRelation().findOutTimeRelationsEMAlgorithm(tempTimeDistances, DataStructure.getInstance().getTimeRelationsDataStructure().getMinimumFrequencyTimeRelation()));
														//System.out.println("       number of relations " + DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j).getTimeRelations().get(k).size());
													}
													else{ //basic algorithm
														DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j).setParticularTimeRelations(DataStructure.getInstance().getTimeRelationsDataStructure().getSimpleTimeRelation().findOutTimeRelationsBasicAlgorithm(tempTimeDistances, DataStructure.getInstance().getTimeRelationsDataStructure().getMinimumFrequencyTimeRelation(), DataStructure.getInstance().getTimeRelationsDataStructure().getDeviationAverageTimeRelation()));
													}
												}
												else{
													ArrayList<simpleTimeRelation> nullTimeRelation = new ArrayList<simpleTimeRelation>();
													DataStructure.getInstance().getSimplePatterns().get(i).getTopologyNodes().get(j).setParticularTimeRelations(nullTimeRelation);
												}
											}
										}
									}
									//Final Time
									d=new Date();
									sTemp=d.getTime();
									System.out.println("Execution time of <time relations> " + (sTemp - s1) + "  miliseconds");
									
									//initialize granularity slider
									int maximumGranularity = (int)DataStructure.getInstance().getSimplePatterns().get(0).getMaximumFrequencyFromStartToEnd();
									jPanelSelectTimeRelation.add(getJSliderSelectTimeRelation(maximumGranularity), null);
									
									
									//initialize topology graph
									jScrollPaneShowTopologyGraphTimeRelation = new JScrollPane(visualizeTopology (DataStructure.getInstance().getSimplePatterns().get(0), jSliderSelectTimeRelation.getValue()));
									jInternalFrameShowTopologyGraphTimeRelation.getContentPane().add(jScrollPaneShowTopologyGraphTimeRelation);
									jInternalFrameShowTopologyGraphTimeRelation.setVisible(true);
									
									//initialize combo
									String[] tempFrequentSet = new String [DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size()];
									for (int i = 0; i < DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size(); i++){
										tempFrequentSet[i] = " Sequence " + i;
										jComboBoxSelectSequence.addItem(tempFrequentSet[i]);
									}
								}
								else{
									JOptionPane.showMessageDialog(jPanelTimeRelations, "Please, insert numeric values (from 0 to 100)",
										    "Numeric warning",
										    JOptionPane.WARNING_MESSAGE);
								}	
								
							}
						});
			}
			return jButtonDiscoverTimeRelations;
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
		 * This method initializes jPanelSelectTimeRelation	
		 * 	
		 * @return javax.swing.JPanel	
		 */
		private JPanel getJPanelSelectTimeRelation() {
			if (jPanelSelectTimeRelation == null) {
				jLabelSelectSequence = new JLabel();
				jLabelSelectSequence.setBounds(new Rectangle(10, 25, 119, 27));
				jLabelSelectSequence.setText("Select a Sequence");
				jPanelSelectTimeRelation = new JPanel();
				jPanelSelectTimeRelation.setLayout(null);
				jPanelSelectTimeRelation.setBorder(BorderFactory.createTitledBorder("Select Time Relations to see"));
				jPanelSelectTimeRelation.setBounds(new Rectangle(14, 180, 1127, 379));
				jPanelSelectTimeRelation.add(jLabelSelectSequence, null);
				jPanelSelectTimeRelation.add(getJComboBoxSelectSequence(), null);
				//jPanelSelectTimeRelation.add(getJSliderSelectTimeRelation(), null);
				jPanelSelectTimeRelation.add(getJInternalFrameShowTopologyGraphTimeRelation(), null);
			}
			return jPanelSelectTimeRelation;
		}


		/**
		 * This method initializes jComboBoxSelectSequence	
		 * 	
		 * @return javax.swing.JComboBox	
		 */
		private JComboBox getJComboBoxSelectSequence() {
			if (jComboBoxSelectSequence == null) {
				jComboBoxSelectSequence = new JComboBox();
				jComboBoxSelectSequence.setBounds(new Rectangle(142, 25, 200, 27));
				jComboBoxSelectSequence.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						//slider
						jPanelSelectTimeRelation.remove(jSliderSelectTimeRelation);
						jSliderSelectTimeRelation = null;
						int maximumGranularity = (int)DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getMaximumFrequencyFromStartToEnd();
						jPanelSelectTimeRelation.add(getJSliderSelectTimeRelation(maximumGranularity), null);
						
						//graph
						jInternalFrameShowTopologyGraphTimeRelation.getContentPane().remove(jScrollPaneShowTopologyGraphTimeRelation);
						jScrollPaneShowTopologyGraphTimeRelation = new JScrollPane (visualizeTopology (DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()), jSliderSelectTimeRelation.getValue()));
						jInternalFrameShowTopologyGraphTimeRelation.getContentPane().add(jScrollPaneShowTopologyGraphTimeRelation);
						jInternalFrameShowTopologyGraphTimeRelation.updateUI();
					}
				});
			}
			return jComboBoxSelectSequence;
		}


		/**
		 * This method initializes jSliderSelectTimeRelation	
		 * 	
		 * @return javax.swing.JSlider	
		 */
		private JSlider getJSliderSelectTimeRelation(int maximumGranularity) {
			if (jSliderSelectTimeRelation == null) {
				jSliderSelectTimeRelation = new JSlider(JSlider.HORIZONTAL,0,maximumGranularity,maximumGranularity/2);
				jSliderSelectTimeRelation.setBounds(new Rectangle(387, 19, 349, 43));
				jSliderSelectTimeRelation.setFont(new Font("Serif", Font.ITALIC, 12));
				jSliderSelectTimeRelation.setMajorTickSpacing(maximumGranularity/4);
				jSliderSelectTimeRelation.setMinorTickSpacing(1);
				jSliderSelectTimeRelation.setPaintTicks(true);
				jSliderSelectTimeRelation.setPaintLabels(true);
				jSliderSelectTimeRelation.setSnapToTicks(true);
				jSliderSelectTimeRelation
						.addChangeListener(new javax.swing.event.ChangeListener() {
							public void stateChanged(javax.swing.event.ChangeEvent e) {
								jInternalFrameShowTopologyGraphTimeRelation.getContentPane().remove(jScrollPaneShowTopologyGraphTimeRelation);
								jScrollPaneShowTopologyGraphTimeRelation = new JScrollPane (visualizeTopology (DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()), jSliderSelectTimeRelation.getValue()));
								jInternalFrameShowTopologyGraphTimeRelation.getContentPane().add(jScrollPaneShowTopologyGraphTimeRelation);
								jInternalFrameShowTopologyGraphTimeRelation.updateUI();
							}
						});
			}
			return jSliderSelectTimeRelation;
		}


		/**
		 * This method initializes jButtonSetParameterRestoreDefaultValue	
		 * 	
		 * @return javax.swing.JButton	
		 */
		private JButton getJButtonSetParameterRestoreDefaultValues() {
			if (jButtonSetParameterRestoreDefaultValues == null) {
				jButtonSetParameterRestoreDefaultValues = new JButton();
				jButtonSetParameterRestoreDefaultValues.setBounds(new Rectangle(84, 126, 171, 25));
				jButtonSetParameterRestoreDefaultValues.setText("Restore Default Values");
				jButtonSetParameterRestoreDefaultValues
						.addActionListener(new java.awt.event.ActionListener() {
							public void actionPerformed(java.awt.event.ActionEvent e) {
								jTextFieldMinimumFrequency.setText("50");
								jTextFieldDeviation.setText("75");
							}
						});
			}
			return jButtonSetParameterRestoreDefaultValues;
		}


		/**
		 * This method initializes jInternalFrameShowTopologyGraphTimeRelation	
		 * 	
		 * @return javax.swing.JInternalFrame	
		 */
		private JInternalFrame getJInternalFrameShowTopologyGraphTimeRelation() {
			if (jInternalFrameShowTopologyGraphTimeRelation == null) {
				jInternalFrameShowTopologyGraphTimeRelation = new JInternalFrame();
				jInternalFrameShowTopologyGraphTimeRelation.setBounds(new Rectangle(14, 65, 1097, 310));
				jInternalFrameShowTopologyGraphTimeRelation.setContentPane(getJContentPaneShowTopologyGraphTimeRelation());
			}
			return jInternalFrameShowTopologyGraphTimeRelation;
		}


		/**
		 * This method initializes jContentPaneShowTopologyGraphTimeRelation	
		 * 	
		 * @return javax.swing.JPanel	
		 */
		private JPanel getJContentPaneShowTopologyGraphTimeRelation() {
			if (jContentPaneShowTopologyGraphTimeRelation == null) {
				jContentPaneShowTopologyGraphTimeRelation = new JPanel();
				jContentPaneShowTopologyGraphTimeRelation.setLayout(new BorderLayout());
			}
			return jContentPaneShowTopologyGraphTimeRelation;
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
						jTextAreaSeeSelectedTimeRelation.setText("");
						jTextAreaSeeSelectedTimeRelation.setLineWrap(true);
						jTextAreaSeeSelectedTimeRelation.setWrapStyleWord(true);
						jTextAreaSeeSelectedTimeRelation.append(newline);
						/*for (int i = 0; i < DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().size(); i++){
							System.out.print(DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(i).getNode());
						}
						System.out.println();*/
						jTextAreaSeeSelectedTimeRelation.append("Node Name:  " + DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getNode());
						jTextAreaSeeSelectedTimeRelation.append(newline);
						jTextAreaSeeSelectedTimeRelation.append(newline);
						for (int i = 0; i < DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getPreviousActions().size(); i++){
							if ((DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getPreviousActionsFrequency().get(i) >= jSliderSelectTimeRelation.getValue()) && (DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getPreviousActions().get(i).compareTo("start") != 0)){
								jTextAreaSeeSelectedTimeRelation.append("  Previous Action: " + DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getPreviousActions().get(i) +  " Frequency: " + DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getPreviousActionsFrequency().get(i));
								jTextAreaSeeSelectedTimeRelation.append(newline);
								if (DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getTimeRelations().get(i).size() > 0){
									for (int j = 0; j < DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getTimeRelations().get(i).size(); j++){
										jTextAreaSeeSelectedTimeRelation.append("  Time Relation " + j + ": " + DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getTimeRelations().get(i).get(j).getSimpleTimeRelation());
										jTextAreaSeeSelectedTimeRelation.append(newline);
										jTextAreaSeeSelectedTimeRelation.append("  Particular Instances: " + DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()).getTopologyNodes().get(indexCell).getTimeRelations().get(i).get(j).getInstances().toString());
										jTextAreaSeeSelectedTimeRelation.append(newline);
									}
								}
								else{
									jTextAreaSeeSelectedTimeRelation.append("There is no well-defined Time Relations");
								}
								jTextAreaSeeSelectedTimeRelation.append(newline);
							}
						}
						
					}
				}
			});
			return graph;
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
			//System.out.println("name " + name + " namelist " + nameList.toString());
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
		 * This method initializes jPanelShowTimeRelation	
		 * 	
		 * @return javax.swing.JPanel	
		 */
		private JPanel getJPanelShowTimeRelation() {
			if (jPanelShowTimeRelation == null) {
				jPanelShowTimeRelation = new JPanel();
				jPanelShowTimeRelation.setLayout(null);
				jPanelShowTimeRelation.setBounds(new Rectangle(538, 17, 601, 156));
				jPanelShowTimeRelation.setBorder(BorderFactory.createTitledBorder("Show Selected Time Relation"));
				jPanelShowTimeRelation.add(getJTextAreaSeeSelectedTimeRelation(), null);
			}
			return jPanelShowTimeRelation;
		}


		/**
		 * This method initializes jTextAreaSeeSelectedTimeRelation	
		 * 	
		 * @return javax.swing.JTextArea	
		 */
		private JScrollPane getJTextAreaSeeSelectedTimeRelation() {
			if (jTextAreaSeeSelectedTimeRelation == null) {
				jTextAreaSeeSelectedTimeRelation = new JTextArea();
				//jTextAreaSeeSelectedTimeRelation.setBounds(new Rectangle(15, 19, 573, 103));
				jScrollPaneShowTimeRelations = new JScrollPane(jTextAreaSeeSelectedTimeRelation);
				jScrollPaneShowTimeRelations.setBounds(new Rectangle(15, 20, 573, 127));
				jScrollPaneShowTimeRelations.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				jScrollPaneShowTimeRelations.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			}
			return jScrollPaneShowTimeRelations;
		}


		/**
		 * This method initializes jRadioButtonMenuItemEMAlgorithm	
		 * 	
		 * @return javax.swing.JRadioButtonMenuItem	
		 */
		private JRadioButtonMenuItem getJRadioButtonMenuItemEMAlgorithm() {
			if (jRadioButtonMenuItemEMAlgorithm == null) {
				jRadioButtonMenuItemEMAlgorithm = new JRadioButtonMenuItem();
				jRadioButtonMenuItemEMAlgorithm.setBounds(new Rectangle(15, 62, 151, 22));
				jRadioButtonMenuItemEMAlgorithm.setText("EM Algorithm");
				jRadioButtonMenuItemEMAlgorithm.setSelected(true);
				jRadioButtonMenuItemEMAlgorithm
						.addItemListener(new java.awt.event.ItemListener() {
							public void itemStateChanged(java.awt.event.ItemEvent e) {
								if (jRadioButtonMenuItemEMAlgorithm.isSelected()){
									jLabelDeviation.setVisible(false);
									jTextFieldDeviation.setVisible(false);
									DataStructure.getInstance().getTimeRelationsDataStructure().setSelctedAlgorithm(0);
								}
							}
						});
			}
			return jRadioButtonMenuItemEMAlgorithm;
		}


		/**
		 * This method initializes jRadioButtonMenuItemBasicAlgorithm	
		 * 	
		 * @return javax.swing.JRadioButtonMenuItem	
		 */
		private JRadioButtonMenuItem getJRadioButtonMenuItemBasicAlgorithm() {
			if (jRadioButtonMenuItemBasicAlgorithm == null) {
				jRadioButtonMenuItemBasicAlgorithm = new JRadioButtonMenuItem();
				jRadioButtonMenuItemBasicAlgorithm.setBounds(new Rectangle(181, 62, 162, 24));
				jRadioButtonMenuItemBasicAlgorithm.setText("Basic Algorithm");
				jRadioButtonMenuItemBasicAlgorithm
						.addItemListener(new java.awt.event.ItemListener() {
							public void itemStateChanged(java.awt.event.ItemEvent e) {
								if (jRadioButtonMenuItemBasicAlgorithm.isSelected()){
									jLabelDeviation.setVisible(true);
									jTextFieldDeviation.setVisible(true);
									DataStructure.getInstance().getTimeRelationsDataStructure().setSelctedAlgorithm(1);
								}
							}
						});
				
				
			}
			return jRadioButtonMenuItemBasicAlgorithm;
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
}
