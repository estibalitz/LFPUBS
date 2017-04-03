package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.Font;
import java.awt.font.*;

import javax.swing.JButton;

import basic.DataStructure;
import basic.simpleNode;
import basic.simplePattern;
import splitSequences.simpleEvent;
import topology.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import javax.swing.JComboBox;

import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.DefaultPort;
import org.jgraph.graph.GraphConstants;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.graph.GraphModel;
import org.jgraph.*;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;

import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import javax.swing.JSlider;

import topology.Nodeg;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

public class Topology extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jPanelTopology = null;

	private JPanel jPanelDefineParameters = null;

	private JPanel jPanelParametersForMakingGroups = null;

	private JPanel jPanelParameterForDefiningNatureActions = null;

	private JLabel jLabelMinimumAbsoluteOriginFrequency = null;

	private JTextField jTextFieldMinimumAbsoluteOriginFrequency = null;

	private JLabel jLabelMinimumAbsoluteDestinyFrequency = null;

	private JTextField jTextFieldMinimumAbsoluteDestinyFrequency = null;

	private JLabel jLabelMinimumBalancedRelationFrequency = null;

	private JTextField jTextFieldMinimumBalancedRelationFrequency = null;

	private JLabel jLabelNumberPreviousNextActions = null;

	private JButton jButtonSetParametersMakingGroupsRestoreDefaultValues = null;

	private JRadioButtonMenuItem jRadioButtonMenuItemPreviousNextOne = null;

	private JRadioButtonMenuItem jRadioButtonMenuItemPreviousNextTwo = null;

	private JRadioButtonMenuItem jRadioButtonMenuItemPreviousNextThree = null;

	private JRadioButtonMenuItem jRadioButtonMenuItemPreviousNextFour = null;
	
	private ButtonGroup PreviousNextButtonGroup = new ButtonGroup();  //  @jve:decl-index=0:

	private JLabel jLabelDefinitionNumberClusters = null;

	private JRadioButtonMenuItem jRadioButtonMenuItemAutomaticDefinitionNumberClusters = null;

	private JRadioButtonMenuItem jRadioButtonMenuItemMaximumNumberActions = null;

	private JRadioButtonMenuItem jRadioButtonMenuItemMeanNumberClusters = null;
	
	private ButtonGroup NumberClustersButtonGroup = new ButtonGroup();  //  @jve:decl-index=0:

	private JLabel jLabelConsiderNaturePreviousActions = null;

	private JRadioButtonMenuItem jRadioButtonMenuItemConsiderNaturePreviousActionsYes = null;

	private JRadioButtonMenuItem jRadioButtonMenuItemConsiderNaturePreviousActionsNo = null;
	
	private ButtonGroup ConsiderNaturePreviousActionsButtonGroup = new ButtonGroup();  //  @jve:decl-index=0:

	private JButton jButtonNatureActionsRestoreDefaultValues = null;

	private JButton jButtonDiscoverTopology = null;

	private JPanel jPanelShowTopology = null;

	private JLabel jLabelSelectSequence = null;

	private JComboBox jComboBoxSelectASequence = null;
	
	private JGraph jGraphShowTopology = null;

	private JInternalFrame jInternalFrameShowTopologyGraph = null;

	private JPanel jContentPaneShowTopologyGraph = null;

	private JSlider jSliderSelectTopologyGranularity = null;

	private JLabel jLabelSelectTopologyGranularity = null;
	
	private JScrollPane jScrollPaneShowTopologyGraph = null;

	/**
	 * This is the default constructor
	 */
	public Topology() {
		//super();
	}

	public JPanel setJPanelTopology() {
		if (jPanelTopology == null) {
			jPanelTopology = new JPanel();
			jPanelTopology.setLayout(null);
			jPanelTopology.setSize(new Dimension(1172, 567));
			jPanelTopology.add(getJPanelDefineParameters(), null);
			jPanelTopology.add(getJPanelShowTopology(), null);
		}
		return jPanelTopology;
	}

	/**
	 * This method initializes jPanelDefineParameters	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelDefineParameters() {
		if (jPanelDefineParameters == null) {
			jPanelDefineParameters = new JPanel();
			jPanelDefineParameters.setLayout(null);
			jPanelDefineParameters.setBounds(new Rectangle(31, 9, 481, 539));
			jPanelDefineParameters.add(getJPanelParametersForMakingGroups(), null);
			jPanelDefineParameters.add(getJPanelParameterForDefiningNatureActions(), null);
			jPanelDefineParameters.setBorder(BorderFactory.createTitledBorder("Set Parameters for Topology Discovering"));
			jPanelDefineParameters.add(getJButtonDiscoverTopology(), null);
		}
		return jPanelDefineParameters;
	}

	/**
	 * This method initializes jPanelParametersForMakingGroups	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelParametersForMakingGroups() {
		if (jPanelParametersForMakingGroups == null) {
			jLabelMinimumBalancedRelationFrequency = new JLabel();
			jLabelMinimumBalancedRelationFrequency.setBounds(new Rectangle(25, 114, 248, 29));
			jLabelMinimumBalancedRelationFrequency.setText("Demanded Minimum Balanced Level  (%)");
			jLabelMinimumAbsoluteDestinyFrequency = new JLabel();
			jLabelMinimumAbsoluteDestinyFrequency.setBounds(new Rectangle(26, 68, 248, 28));
			jLabelMinimumAbsoluteDestinyFrequency.setText("Demanded Minimum Level for Destiny  (%)");
			jLabelMinimumAbsoluteOriginFrequency = new JLabel();
			jLabelMinimumAbsoluteOriginFrequency.setBounds(new Rectangle(26, 22, 248, 29));
			jLabelMinimumAbsoluteOriginFrequency.setText("Demanded Minimum Level for Origin  (%)");
			jPanelParametersForMakingGroups = new JPanel();
			jPanelParametersForMakingGroups.setLayout(null);
			jPanelParametersForMakingGroups.setBounds(new Rectangle(24, 25, 433, 183));
			jPanelParametersForMakingGroups.add(jLabelMinimumAbsoluteOriginFrequency, null);
			jPanelParametersForMakingGroups.add(getJTextFieldMinimumAbsoluteOriginFrequency(), null);
			jPanelParametersForMakingGroups.add(jLabelMinimumAbsoluteDestinyFrequency, null);
			jPanelParametersForMakingGroups.add(getJTextFieldMinimumAbsoluteDestinyFrequency(), null);
			jPanelParametersForMakingGroups.add(jLabelMinimumBalancedRelationFrequency, null);
			jPanelParametersForMakingGroups.add(getJTextFieldMinimumBalancedRelationFrequency(), null);
			jPanelParametersForMakingGroups.setBorder(BorderFactory.createTitledBorder("Unordered Subsets of Actions"));
			jPanelParametersForMakingGroups.add(getJButtonSetParametersMakingGroupsRestoreDefaultValues(), null);
		}
		return jPanelParametersForMakingGroups;
	}
	
	//double minimumAbsoluteOriginFrequency = 0.25;
	//double minimumAbsoluteDestinyFrequency = 0.25;
	//double minimumBalancedRelationFrequency = 0.5;

	/**
	 * This method initializes jPanelParameterForDefiningNatureActions	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelParameterForDefiningNatureActions() {
		if (jPanelParameterForDefiningNatureActions == null) {
			jLabelConsiderNaturePreviousActions = new JLabel();
			jLabelConsiderNaturePreviousActions.setBounds(new Rectangle(28, 190, 331, 30));
			jLabelConsiderNaturePreviousActions.setText("Consider Nature of Previous Actions");
			jLabelDefinitionNumberClusters = new JLabel();
			jLabelDefinitionNumberClusters.setBounds(new Rectangle(28, 89, 322, 30));
			jLabelDefinitionNumberClusters.setText("Initial Number of Clusters");
			jLabelNumberPreviousNextActions = new JLabel();
			jLabelNumberPreviousNextActions.setBounds(new Rectangle(28, 23, 325, 32));
			jLabelNumberPreviousNextActions.setText("Number of Previous and Next Actions to consider");
			jPanelParameterForDefiningNatureActions = new JPanel();
			jPanelParameterForDefiningNatureActions.setLayout(null);
			jPanelParameterForDefiningNatureActions.setBounds(new Rectangle(24, 217, 432, 279));
			jPanelParameterForDefiningNatureActions.setBorder(BorderFactory.createTitledBorder("Nature of Repetitive Actions"));
			jPanelParameterForDefiningNatureActions.add(jLabelNumberPreviousNextActions, null);			
			jPanelParameterForDefiningNatureActions.add(getJRadioButtonMenuItemPreviousNextOne(), null);
			jPanelParameterForDefiningNatureActions.add(getJRadioButtonMenuItemPreviousNextTwo(), null);
			jPanelParameterForDefiningNatureActions.add(getJRadioButtonMenuItemPreviousNextThree(), null);
			jPanelParameterForDefiningNatureActions.add(getJRadioButtonMenuItemPreviousNextFour(), null);
			jPanelParameterForDefiningNatureActions.add(jLabelDefinitionNumberClusters, null);
			jPanelParameterForDefiningNatureActions.add(getJRadioButtonMenuItemAutomaticDefinitionNumberClusters(), null);
			jPanelParameterForDefiningNatureActions.add(getJRadioButtonMenuItemMaximumNumberActions(), null);
			jPanelParameterForDefiningNatureActions.add(getJRadioButtonMenuItemMeanNumberClusters(), null);
			jPanelParameterForDefiningNatureActions.add(jLabelConsiderNaturePreviousActions, null);
			jPanelParameterForDefiningNatureActions.add(getJRadioButtonMenuItemConsiderNaturePreviousActionsYes(), null);
			jPanelParameterForDefiningNatureActions.add(getJRadioButtonMenuItemConsiderNaturePreviousActionsNo(), null);
			jPanelParameterForDefiningNatureActions.add(getJButtonNatureActionsRestoreDefaultValues(), null);
			PreviousNextButtonGroup.add(getJRadioButtonMenuItemPreviousNextOne());
			PreviousNextButtonGroup.add(getJRadioButtonMenuItemPreviousNextTwo());
			PreviousNextButtonGroup.add(getJRadioButtonMenuItemPreviousNextThree());
			PreviousNextButtonGroup.add(getJRadioButtonMenuItemPreviousNextFour());
			NumberClustersButtonGroup.add(getJRadioButtonMenuItemAutomaticDefinitionNumberClusters());
			NumberClustersButtonGroup.add(getJRadioButtonMenuItemMaximumNumberActions());
			NumberClustersButtonGroup.add(getJRadioButtonMenuItemMeanNumberClusters());
			ConsiderNaturePreviousActionsButtonGroup.add(getJRadioButtonMenuItemConsiderNaturePreviousActionsYes());
			ConsiderNaturePreviousActionsButtonGroup.add(getJRadioButtonMenuItemConsiderNaturePreviousActionsNo());
		}
		return jPanelParameterForDefiningNatureActions;
	}

	/**
	 * This method initializes jTextFieldMinimumAbsoluteOriginFrequency	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldMinimumAbsoluteOriginFrequency() {
		if (jTextFieldMinimumAbsoluteOriginFrequency == null) {
			jTextFieldMinimumAbsoluteOriginFrequency = new JTextField();
			jTextFieldMinimumAbsoluteOriginFrequency.setBounds(new Rectangle(286, 21, 67, 31));
			jTextFieldMinimumAbsoluteOriginFrequency.setText("25");
		}
		return jTextFieldMinimumAbsoluteOriginFrequency;
	}

	/**
	 * This method initializes jTextFieldMinimumAbsoluteDestinyFrequency	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldMinimumAbsoluteDestinyFrequency() {
		if (jTextFieldMinimumAbsoluteDestinyFrequency == null) {
			jTextFieldMinimumAbsoluteDestinyFrequency = new JTextField();
			jTextFieldMinimumAbsoluteDestinyFrequency.setBounds(new Rectangle(285, 67, 68, 30));
			jTextFieldMinimumAbsoluteDestinyFrequency.setText("25");
		}
		return jTextFieldMinimumAbsoluteDestinyFrequency;
	}

	/**
	 * This method initializes jTextFieldMinimumBalancedRelationFrequency	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldMinimumBalancedRelationFrequency() {
		if (jTextFieldMinimumBalancedRelationFrequency == null) {
			jTextFieldMinimumBalancedRelationFrequency = new JTextField();
			jTextFieldMinimumBalancedRelationFrequency.setBounds(new Rectangle(286, 114, 62, 30));
			jTextFieldMinimumBalancedRelationFrequency.setText("50");
		}
		return jTextFieldMinimumBalancedRelationFrequency;
	}

	/**
	 * This method initializes jButtonSetParametersMakingGroupsRestoreDefaultValues	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonSetParametersMakingGroupsRestoreDefaultValues() {
		if (jButtonSetParametersMakingGroupsRestoreDefaultValues == null) {
			jButtonSetParametersMakingGroupsRestoreDefaultValues = new JButton();
			jButtonSetParametersMakingGroupsRestoreDefaultValues.setBounds(new Rectangle(226, 150, 181, 22));
			jButtonSetParametersMakingGroupsRestoreDefaultValues.setText("Restore Default Values");
			jButtonSetParametersMakingGroupsRestoreDefaultValues
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							jTextFieldMinimumAbsoluteOriginFrequency.setText("25");
							jTextFieldMinimumAbsoluteDestinyFrequency.setText("25");
							jTextFieldMinimumBalancedRelationFrequency.setText("50");
						}
					});
		}
		return jButtonSetParametersMakingGroupsRestoreDefaultValues;
	}

	/**
	 * This method initializes jRadioButtonMenuItemPreviousNextOne	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRadioButtonMenuItemPreviousNextOne() {
		if (jRadioButtonMenuItemPreviousNextOne == null) {
			jRadioButtonMenuItemPreviousNextOne = new JRadioButtonMenuItem();
			jRadioButtonMenuItemPreviousNextOne.setBounds(new Rectangle(30, 54, 60, 30));
			jRadioButtonMenuItemPreviousNextOne.setText(" 1");
		}
		return jRadioButtonMenuItemPreviousNextOne;
	}

	/**
	 * This method initializes jRadioButtonMenuItemPreviousNextTwo	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRadioButtonMenuItemPreviousNextTwo() {
		if (jRadioButtonMenuItemPreviousNextTwo == null) {
			jRadioButtonMenuItemPreviousNextTwo = new JRadioButtonMenuItem();
			jRadioButtonMenuItemPreviousNextTwo.setBounds(new Rectangle(108, 54, 60, 30));
			jRadioButtonMenuItemPreviousNextTwo.setText(" 2");
		}
		return jRadioButtonMenuItemPreviousNextTwo;
	}

	/**
	 * This method initializes jRadioButtonMenuItemPreviousNextThree	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRadioButtonMenuItemPreviousNextThree() {
		if (jRadioButtonMenuItemPreviousNextThree == null) {
			jRadioButtonMenuItemPreviousNextThree = new JRadioButtonMenuItem();
			jRadioButtonMenuItemPreviousNextThree.setBounds(new Rectangle(176, 54, 60, 30));
			jRadioButtonMenuItemPreviousNextThree.setText(" 3");
		}
		return jRadioButtonMenuItemPreviousNextThree;
	}

	/**
	 * This method initializes jRadioButtonMenuItemPreviousNextFour	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRadioButtonMenuItemPreviousNextFour() {
		if (jRadioButtonMenuItemPreviousNextFour == null) {
			jRadioButtonMenuItemPreviousNextFour = new JRadioButtonMenuItem();
			jRadioButtonMenuItemPreviousNextFour.setBounds(new Rectangle(244, 54, 60, 30));
			jRadioButtonMenuItemPreviousNextFour.setText(" 4");
			jRadioButtonMenuItemPreviousNextFour.setSelected(true);
		}
		return jRadioButtonMenuItemPreviousNextFour;
	}

	/**
	 * This method initializes jRadioButtonMenuItemAutomaticDefinitionNumberClusters	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRadioButtonMenuItemAutomaticDefinitionNumberClusters() {
		if (jRadioButtonMenuItemAutomaticDefinitionNumberClusters == null) {
			jRadioButtonMenuItemAutomaticDefinitionNumberClusters = new JRadioButtonMenuItem();
			jRadioButtonMenuItemAutomaticDefinitionNumberClusters.setBounds(new Rectangle(30, 118, 340, 25));
			jRadioButtonMenuItemAutomaticDefinitionNumberClusters.setText("Automatic Definition of Necessary Number of Clusters");
		}
		return jRadioButtonMenuItemAutomaticDefinitionNumberClusters;
	}

	/**
	 * This method initializes jRadioButtonMenuItemMaximumNumberActions	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRadioButtonMenuItemMaximumNumberActions() {
		if (jRadioButtonMenuItemMaximumNumberActions == null) {
			jRadioButtonMenuItemMaximumNumberActions = new JRadioButtonMenuItem();
			jRadioButtonMenuItemMaximumNumberActions.setBounds(new Rectangle(30, 137, 340, 25));
			jRadioButtonMenuItemMaximumNumberActions.setText("Maximum Number of Actions");
		}
		return jRadioButtonMenuItemMaximumNumberActions;
	}

	/**
	 * This method initializes jRadioButtonMenuItemMeanNumberClusters	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRadioButtonMenuItemMeanNumberClusters() {
		if (jRadioButtonMenuItemMeanNumberClusters == null) {
			jRadioButtonMenuItemMeanNumberClusters = new JRadioButtonMenuItem();
			jRadioButtonMenuItemMeanNumberClusters.setBounds(new Rectangle(30, 157, 340, 25));
			jRadioButtonMenuItemMeanNumberClusters.setText("Average Number of Actions");
			jRadioButtonMenuItemMeanNumberClusters.setSelected(true);
		}
		return jRadioButtonMenuItemMeanNumberClusters;
	}

	/**
	 * This method initializes jRadioButtonMenuItemConsiderNaturePreviousActionsYes	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRadioButtonMenuItemConsiderNaturePreviousActionsYes() {
		if (jRadioButtonMenuItemConsiderNaturePreviousActionsYes == null) {
			jRadioButtonMenuItemConsiderNaturePreviousActionsYes = new JRadioButtonMenuItem();
			jRadioButtonMenuItemConsiderNaturePreviousActionsYes.setBounds(new Rectangle(28, 218, 90, 25));
			jRadioButtonMenuItemConsiderNaturePreviousActionsYes.setText(" Yes");
			jRadioButtonMenuItemConsiderNaturePreviousActionsYes.setSelected(true);
		}
		return jRadioButtonMenuItemConsiderNaturePreviousActionsYes;
	}

	/**
	 * This method initializes jRadioButtonMenuItemConsiderNaturePreviousActionsNo	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRadioButtonMenuItemConsiderNaturePreviousActionsNo() {
		if (jRadioButtonMenuItemConsiderNaturePreviousActionsNo == null) {
			jRadioButtonMenuItemConsiderNaturePreviousActionsNo = new JRadioButtonMenuItem();
			jRadioButtonMenuItemConsiderNaturePreviousActionsNo.setBounds(new Rectangle(114, 218, 90, 25));
			jRadioButtonMenuItemConsiderNaturePreviousActionsNo.setText(" No");
		}
		return jRadioButtonMenuItemConsiderNaturePreviousActionsNo;
	}

	/**
	 * This method initializes jButtonNatureActionsRestoreDefaultValues	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonNatureActionsRestoreDefaultValues() {
		if (jButtonNatureActionsRestoreDefaultValues == null) {
			jButtonNatureActionsRestoreDefaultValues = new JButton();
			jButtonNatureActionsRestoreDefaultValues.setBounds(new Rectangle(224, 240, 180, 22));
			jButtonNatureActionsRestoreDefaultValues.setText("Restore Default Values");
			jButtonNatureActionsRestoreDefaultValues
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							jRadioButtonMenuItemPreviousNextFour.setSelected(true);
							jRadioButtonMenuItemMeanNumberClusters.setSelected(true);
							jRadioButtonMenuItemConsiderNaturePreviousActionsYes.setSelected(true);
						}
					});
		}
		return jButtonNatureActionsRestoreDefaultValues;
	}

	/**
	 * This method initializes jButtonDiscoverTopology	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonDiscoverTopology() {
		if (jButtonDiscoverTopology == null) {
			jButtonDiscoverTopology = new JButton();
			jButtonDiscoverTopology.setBounds(new Rectangle(241, 504, 201, 26));
			jButtonDiscoverTopology.setText("Discover Topology");
			jButtonDiscoverTopology.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Date d=new Date();
				    long s1,sTemp,s2;
				    d=new Date();
				    
					if (checkIsNumberFromXToY(jTextFieldMinimumAbsoluteOriginFrequency.getText(),0,100) && checkIsNumberFromXToY(jTextFieldMinimumAbsoluteDestinyFrequency.getText(),0,100) && checkIsNumberFromXToY(jTextFieldMinimumBalancedRelationFrequency.getText(),0,100)){
						DataStructure.getInstance().getTopologyDataStructure().setMinimumAbsoluteOriginFrequency(Double.parseDouble(jTextFieldMinimumAbsoluteOriginFrequency.getText()) / 100);
						DataStructure.getInstance().getTopologyDataStructure().setMinimumAbsoluteDestinyFrequency(Double.parseDouble(jTextFieldMinimumAbsoluteDestinyFrequency.getText()) / 100);
						DataStructure.getInstance().getTopologyDataStructure().setMinimumBalancedRelationFrequency(Double.parseDouble(jTextFieldMinimumBalancedRelationFrequency.getText()) / 100);
						//Number of Previous & Next Actions to consider
						if (jRadioButtonMenuItemPreviousNextOne.isSelected()){
							DataStructure.getInstance().getTopologyDataStructure().setNumberPreviousNextActions(1);
						}
						else if (jRadioButtonMenuItemPreviousNextTwo.isSelected()){
							DataStructure.getInstance().getTopologyDataStructure().setNumberPreviousNextActions(2);
						}
						else if (jRadioButtonMenuItemPreviousNextThree.isSelected()){
							DataStructure.getInstance().getTopologyDataStructure().setNumberPreviousNextActions(3);
						}
						else if (jRadioButtonMenuItemPreviousNextFour.isSelected()){
							DataStructure.getInstance().getTopologyDataStructure().setNumberPreviousNextActions(4);
						}
						
						//How to define the clusters
						if (jRadioButtonMenuItemAutomaticDefinitionNumberClusters.isSelected()){
							DataStructure.getInstance().getTopologyDataStructure().setHowDefineCluster(0);
						}
						else if (jRadioButtonMenuItemMaximumNumberActions.isSelected()){
							DataStructure.getInstance().getTopologyDataStructure().setHowDefineCluster(1);
						}
						else if (jRadioButtonMenuItemMeanNumberClusters.isSelected()){
							DataStructure.getInstance().getTopologyDataStructure().setHowDefineCluster(2);
						}
						
						//Consider nature of previous actions
						if (jRadioButtonMenuItemConsiderNaturePreviousActionsYes.isSelected()){
							DataStructure.getInstance().getTopologyDataStructure().setConsiderPreviousClusters(1);
						}
						else if (jRadioButtonMenuItemConsiderNaturePreviousActionsNo.isSelected()){
							DataStructure.getInstance().getTopologyDataStructure().setConsiderPreviousClusters(0);
						}
						
						//discover topology
						
						//Initial Time
						s1=d.getTime();
						DataStructure.getInstance().getTopologyDataStructure().setNumFrequentSequences(DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size());
						
						try{
							for (int i = 0; i < DataStructure.getInstance().getTopologyDataStructure().getNumFrequentSequences(); i++){
								DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().add(DataStructure.getInstance().getTopologyDataStructure().getTopology().defineTopologyOfSequence(DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().get(i), DataStructure.getInstance().getSplitSequencesDataStructure().getAllRawSequences(), DataStructure.getInstance().getTopologyDataStructure().getMinimumAbsoluteOriginFrequency(), DataStructure.getInstance().getTopologyDataStructure().getMinimumAbsoluteDestinyFrequency(), DataStructure.getInstance().getTopologyDataStructure().getMinimumBalancedRelationFrequency(), DataStructure.getInstance().getTopologyDataStructure().getNumberPreviousNextActions(), DataStructure.getInstance().getTopologyDataStructure().getHowDefineCluster(), DataStructure.getInstance().getTopologyDataStructure().getConsiderPreviousClusters()));
								//translate to simplePattern structure
								ArrayList<simpleNode> tempNodes = new ArrayList<simpleNode>();
								for (int j = 0; j < DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i).length; j++){
									tempNodes.add(new simpleNode(DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i)[j]));
								}
								double maximumValueFromStartToEnd = DataStructure.getInstance().getTopologyDataStructure().getTopology().getMaximumFrequencyFromStartToEnd(DataStructure.getInstance().getTopologyDataStructure().getTopologyAllSequences().get(i));
								DataStructure.getInstance().getSimplePatterns().add(new simplePattern(tempNodes,maximumValueFromStartToEnd));
							}
						}
						catch (Exception Exception){
							System.err.println("Caught Exception: " 
			                        + Exception.getMessage());
						}
						//Final Time
						d=new Date();
						sTemp=d.getTime();
						System.out.println("Execution time of <find topology> " + (sTemp - s1) + "  miliseconds");
						
						
						//initialize granularity slider
						int maximumGranularity = (int)DataStructure.getInstance().getSimplePatterns().get(0).getMaximumFrequencyFromStartToEnd();
						jPanelShowTopology.add(getJSliderSelectTopologyGranularity(maximumGranularity), null);
												
						//initialize topology graph
						jScrollPaneShowTopologyGraph = new JScrollPane(visualizeTopology (DataStructure.getInstance().getSimplePatterns().get(0), jSliderSelectTopologyGranularity.getValue()));
						jInternalFrameShowTopologyGraph.getContentPane().add(jScrollPaneShowTopologyGraph);
						jInternalFrameShowTopologyGraph.setVisible(true);
						
						//initialize combo
						String[] tempFrequentSet = new String [DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size()];
						for (int i = 0; i < DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size(); i++){
							tempFrequentSet[i] = " Sequence " + i;
							jComboBoxSelectASequence.addItem(tempFrequentSet[i]);
						}
					}
					else{
						JOptionPane.showMessageDialog(jPanelTopology, "Please, insert numeric values (from 0 to 100)",
							    "Numeric warning",
							    JOptionPane.WARNING_MESSAGE);
					}										
				}
			});
		}
		return jButtonDiscoverTopology;
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
	 * This method initializes jPanelShowTopology	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelShowTopology() {
		if (jPanelShowTopology == null) {
			jLabelSelectTopologyGranularity = new JLabel();
			jLabelSelectTopologyGranularity.setBounds(new Rectangle(25, 70, 239, 32));
			jLabelSelectTopologyGranularity.setText("Select Granularity for the Topology");
			jLabelSelectSequence = new JLabel();
			jLabelSelectSequence.setBounds(new Rectangle(29, 30, 151, 27));
			jLabelSelectSequence.setText("Select a Sequence");
			jPanelShowTopology = new JPanel();
			jPanelShowTopology.setLayout(null);
			jPanelShowTopology.setBounds(new Rectangle(526, 13, 615, 534));
			jPanelShowTopology.add(jLabelSelectSequence, null);
			jPanelShowTopology.add(getJComboBoxSelectASequence(), null);
			jPanelShowTopology.setBorder(BorderFactory.createTitledBorder("Topology Representation"));
			jPanelShowTopology.add(getJInternalFrameShowTopologyGraph(), null);
			//jPanelShowTopology.add(getJSliderSelectTopologyGranularity(), null);
			jPanelShowTopology.add(jLabelSelectTopologyGranularity, null);
		}
		return jPanelShowTopology;
	}

	/**
	 * This method initializes jComboBoxSelectASequence	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxSelectASequence() {
		if (jComboBoxSelectASequence == null) {
			jComboBoxSelectASequence = new JComboBox();
			jComboBoxSelectASequence.setBounds(new Rectangle(196, 31, 178, 26));
			jComboBoxSelectASequence.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//slider
					jPanelShowTopology.remove(jSliderSelectTopologyGranularity);
					jSliderSelectTopologyGranularity = null;
					int maximumGranularity = (int)DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectASequence.getSelectedIndex()).getMaximumFrequencyFromStartToEnd();
					jPanelShowTopology.add(getJSliderSelectTopologyGranularity(maximumGranularity), null);
					
					//graph
					jInternalFrameShowTopologyGraph.getContentPane().remove(jScrollPaneShowTopologyGraph);
					jScrollPaneShowTopologyGraph = new JScrollPane (visualizeTopology (DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectASequence.getSelectedIndex()), jSliderSelectTopologyGranularity.getValue()));
					jInternalFrameShowTopologyGraph.getContentPane().add(jScrollPaneShowTopologyGraph);
					jInternalFrameShowTopologyGraph.updateUI();
				}
			});
		}
		return jComboBoxSelectASequence;
	}


	
	protected JGraph visualizeTopology (simplePattern simplePattern, int minimumFrequency){
		GraphModel model = new DefaultGraphModel();
		GraphLayoutCache view = new GraphLayoutCache(model, new DefaultCellViewFactory());
		JGraph graph = new JGraph(model, view);
		ArrayList<DefaultGraphCell> tempNodeCells = new ArrayList<DefaultGraphCell>();
		ArrayList<DefaultGraphCell> tempRelationCells = new ArrayList<DefaultGraphCell>();
		
		//nodes
		for (int i = 0; i < simplePattern.getTopologyNodes().size(); i++){
			tempNodeCells.add(initCell(findActionName(simplePattern.getTopologyNodes().get(i).getNode()),20,20));
		}
		tempNodeCells.add(initCell("start",20,20));
		tempNodeCells.add(initCell("end",20,20));
		
		//visualize on the screen
		/*for (int i = 0; i < simplePattern.getTopologyNodes().size(); i++){
			System.out.println("Node: " + simplePattern.getTopologyNodes().get(i).getNode());
			for (int j = 0; j < simplePattern.getTopologyNodes().get(i).getNextActions().size(); j++){
				System.out.println("    NextNode: " + simplePattern.getTopologyNodes().get(i).getNextActions().get(j) + ", Frequency: " + simplePattern.getTopologyNodes().get(i).getNextActionsFrequency().get(j));
			}
		}*/
		
		//relations
		for (int i = 0; i < simplePattern.getTopologyNodes().size(); i++){
			for (int j = 0; j < simplePattern.getTopologyNodes().get(i).getNextActions().size(); j++){
				if (simplePattern.getTopologyNodes().get(i).getNextActionsFrequency().get(j) > minimumFrequency){
					int find = findNode(simplePattern.getTopologyNodes().get(i).getNextActions().get(j),simplePattern);
					if (find == -1){
						tempRelationCells.add(initRelation(tempNodeCells.get(i), tempNodeCells.get(tempNodeCells.size()-1), Integer.toString(simplePattern.getTopologyNodes().get(i).getNextActionsFrequency().get(j))));
					}
					else{
						tempRelationCells.add(initRelation(tempNodeCells.get(i), tempNodeCells.get(find), Integer.toString(simplePattern.getTopologyNodes().get(i).getNextActionsFrequency().get(j))));
					}
				}
			}
			for (int j = 0; j < simplePattern.getTopologyNodes().get(i).getPreviousActions().size(); j++){
				if (simplePattern.getTopologyNodes().get(i).getPreviousActions().get(j).compareTo("start")==0){
					if (simplePattern.getTopologyNodes().get(i).getPreviousActionsFrequency().get(j) > minimumFrequency){
						tempRelationCells.add(initRelation(tempNodeCells.get(tempNodeCells.size()-2), tempNodeCells.get(i), Integer.toString(simplePattern.getTopologyNodes().get(i).getPreviousActionsFrequency().get(j))));
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
		return graph;
	}
	
	public static DefaultGraphCell initCell (String name, int x1, int y1){
		DefaultGraphCell cell = new DefaultGraphCell(new String(name));
		GraphConstants.setBounds(cell.getAttributes(), new Rectangle2D.Double(x1,y1,90,30));
		GraphConstants.setBorderColor(cell.getAttributes(), Color.getHSBColor(0.63f,0.6464f,1.0f));
		GraphConstants.setBackground(cell.getAttributes(), Color.getHSBColor(0.644f, 0.3636f, 1.0f)); //to calculate http://home.comcast.net/~ed-abramson/14ColorTest/HSB-and-RGB-Colors.html
		GraphConstants.setOpaque(cell.getAttributes(), true);
		GraphConstants.setFont(cell.getAttributes(), GraphConstants.DEFAULTFONT.deriveFont(6));
		DefaultPort port = new DefaultPort();
		cell.add(port);
		
		return cell;
	}
	
	public static DefaultGraphCell initRelation (DefaultGraphCell cell0, DefaultGraphCell cell1, String label){
		DefaultGraphCell relation = new DefaultGraphCell();
		DefaultEdge edge = new DefaultEdge(new String(label));
		
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

	/**
	 * This method initializes jInternalFrameShowTopologyGraph	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	private JInternalFrame getJInternalFrameShowTopologyGraph() {
		if (jInternalFrameShowTopologyGraph == null) {
			jInternalFrameShowTopologyGraph = new JInternalFrame();
			jInternalFrameShowTopologyGraph.setBounds(new Rectangle(10, 122, 594, 405));
			jInternalFrameShowTopologyGraph.setContentPane(getJContentPaneShowTopologyGraph());
		}
		return jInternalFrameShowTopologyGraph;
	}

	/**
	 * This method initializes jContentPaneShowTopologyGraph	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPaneShowTopologyGraph() {
		if (jContentPaneShowTopologyGraph == null) {
			jContentPaneShowTopologyGraph = new JPanel();
			jContentPaneShowTopologyGraph.setLayout(new BorderLayout());
		}
		return jContentPaneShowTopologyGraph;
	}

	/**
	 * This method initializes jSliderSelectTopologyGranularity	
	 * 	
	 * @return javax.swing.JSlider	
	 */
	private JSlider getJSliderSelectTopologyGranularity(int maximumGranularity) {
		if (jSliderSelectTopologyGranularity == null) {
			jSliderSelectTopologyGranularity = new JSlider(JSlider.HORIZONTAL,0,maximumGranularity,maximumGranularity/2);
			jSliderSelectTopologyGranularity.setBounds(new Rectangle(270, 71, 326, 42));
			jSliderSelectTopologyGranularity.setFont(new Font("Serif", Font.ITALIC, 12));
			jSliderSelectTopologyGranularity.setMajorTickSpacing(maximumGranularity/4);
			jSliderSelectTopologyGranularity.setMinorTickSpacing(1);
			jSliderSelectTopologyGranularity.setPaintTicks(true);
			jSliderSelectTopologyGranularity.setPaintLabels(true);
			jSliderSelectTopologyGranularity.setSnapToTicks(true);
			jSliderSelectTopologyGranularity
					.addChangeListener(new javax.swing.event.ChangeListener() {
						public void stateChanged(javax.swing.event.ChangeEvent e) {
							jInternalFrameShowTopologyGraph.getContentPane().remove(jScrollPaneShowTopologyGraph);
							jScrollPaneShowTopologyGraph = new JScrollPane (visualizeTopology (DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectASequence.getSelectedIndex()), jSliderSelectTopologyGranularity.getValue()));
							jInternalFrameShowTopologyGraph.getContentPane().add(jScrollPaneShowTopologyGraph);
							jInternalFrameShowTopologyGraph.updateUI();
						}
					});
		}
		return jSliderSelectTopologyGranularity;
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
