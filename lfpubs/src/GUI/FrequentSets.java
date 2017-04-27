package GUI;

import java.awt.Dimension;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import frequentSequences.simpleSequence;

import basic.DataStructure;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import splitSequences.simpleEvent;

public class FrequentSets extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final static String newline = "\n";  //  @jve:decl-index=0:

	private JPanel jPanelFrequentSets = null;

	private JPanel jPanelMinimumParameters = null;

	private JLabel jLabelMinimumConfidenceLevel = null;

	private JTextField jTextFieldDemandedMinimumConfidenceLevel = null;

	private JLabel jLabelMinimumLevelForExtraActions = null;

	private JTextField jTextFieldLevelForExtraActions = null;

	private JLabel jLabelMinimumLengthForExtraInstances = null;

	private JTextField jTextFieldLengthSimilarity = null;

	private JButton jButtonRestoreDefaultValues = null;

	private JButton jButtonFindOutFrequentSets = null;

	private JPanel jPanelShowFrequentSets = null;

	private JPanel jPanelSelectFrequentSet = null;

	private JPanel jPanelInformationSelectFrequentSet = null;

	private JLabel jLabelNumberFrequentSets = null;

	private JLabel jLabelSelectFrequentSet = null;

	private JComboBox jComboBoxSelectFrequentSet = null;

	private JTextArea jTextAreaShowInformationSelectedFrequentSet = null;

	/**
	 * This is the default constructor
	 */
	public FrequentSets() {
		//super();
	}
	
	public JPanel setJPanelFrequentSets() {
		if (jPanelFrequentSets == null) {
			jPanelFrequentSets = new JPanel();
			jPanelFrequentSets.setLayout(null);
			//jPanelPreprocessingData.setSize(new Dimension(687, 367));
			jPanelFrequentSets.setSize(new Dimension(1172, 567));
			jPanelFrequentSets.add(getJPanelMinimumParameters(), null);
			jPanelFrequentSets.add(getJPanelShowFrequentSets(), null);
		}
		return jPanelFrequentSets;
	}

	/**
	 * This method initializes jPanelMinimumParameters	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelMinimumParameters() {
		if (jPanelMinimumParameters == null) {
			jLabelMinimumLengthForExtraInstances = new JLabel();
			jLabelMinimumLengthForExtraInstances.setBounds(new Rectangle(32, 226, 362, 29));
			jLabelMinimumLengthForExtraInstances.setText("Demanded Minimum Length Similarity for Extra Instances  (%)");
			jLabelMinimumLevelForExtraActions = new JLabel();
			jLabelMinimumLevelForExtraActions.setBounds(new Rectangle(30, 125, 314, 29));
			//jLabelMinimumLevelForExtraActions.setText("Demanded Minimum Support");
			jLabelMinimumLevelForExtraActions.setText("Demanded Minimum Level for Extra Actions  (%)");
			jLabelMinimumConfidenceLevel = new JLabel();
			jLabelMinimumConfidenceLevel.setBounds(new Rectangle(29, 31, 318, 29));
			jLabelMinimumConfidenceLevel.setText("Demanded Minimum Confidence Level  (%)");
			jPanelMinimumParameters = new JPanel();
			jPanelMinimumParameters.setLayout(null);
			jPanelMinimumParameters.setBounds(new Rectangle(59, 44, 406, 484));
			jPanelMinimumParameters.setBorder(BorderFactory.createTitledBorder("Set Demanded Minimum Levels for Frequent Sets"));
			jPanelMinimumParameters.add(jLabelMinimumConfidenceLevel, null);
			jPanelMinimumParameters.add(getJTextFieldDemandedMinimumConfidenceLevel(), null);
			jPanelMinimumParameters.add(jLabelMinimumLevelForExtraActions, null);
			jPanelMinimumParameters.add(getJTextFieldLevelForExtraActions(), null);
			jPanelMinimumParameters.add(jLabelMinimumLengthForExtraInstances, null);
			jPanelMinimumParameters.add(getJTextFieldLengthSimilarity(), null);
			jPanelMinimumParameters.add(getJButtonRestoreDefaultValues(), null);
			jPanelMinimumParameters.add(getJButtonFindOutFrequentSets(), null);
		}
		return jPanelMinimumParameters;
	}

	/**
	 * This method initializes jTextFieldDemandedMinimumConfidenceLevel	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldDemandedMinimumConfidenceLevel() {
		if (jTextFieldDemandedMinimumConfidenceLevel == null) {
			jTextFieldDemandedMinimumConfidenceLevel = new JTextField();
			jTextFieldDemandedMinimumConfidenceLevel.setBounds(new Rectangle(29, 76, 48, 24));
			jTextFieldDemandedMinimumConfidenceLevel.setText("80");
		}
		return jTextFieldDemandedMinimumConfidenceLevel;
	}

	/**
	 * This method initializes jTextFieldLevelForExtraActions	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldLevelForExtraActions() {
		if (jTextFieldLevelForExtraActions == null) {
			jTextFieldLevelForExtraActions = new JTextField();
			jTextFieldLevelForExtraActions.setBounds(new Rectangle(31, 177, 48, 25));
			jTextFieldLevelForExtraActions.setText("80");
		}
		return jTextFieldLevelForExtraActions;
	}

	/**
	 * This method initializes jTextFieldLengthSimilarity	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldLengthSimilarity() {
		if (jTextFieldLengthSimilarity == null) {
			jTextFieldLengthSimilarity = new JTextField();
			jTextFieldLengthSimilarity.setBounds(new Rectangle(34, 273, 45, 26));
			jTextFieldLengthSimilarity.setText("75");
		}
		return jTextFieldLengthSimilarity;
	}

	/**
	 * This method initializes jButtonRestoreDefaultValues	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonRestoreDefaultValues() {
		if (jButtonRestoreDefaultValues == null) {
			jButtonRestoreDefaultValues = new JButton();
			jButtonRestoreDefaultValues.setBounds(new Rectangle(210, 313, 165, 28));
			jButtonRestoreDefaultValues.setText("Restore Default Values");
			jButtonRestoreDefaultValues
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							jTextFieldDemandedMinimumConfidenceLevel.setText("80");
							jTextFieldLevelForExtraActions.setText("80");
							jTextFieldLengthSimilarity.setText("75");
						}
					});
		}
		return jButtonRestoreDefaultValues;
	}

	/**
	 * This method initializes jButtonFindOutFrequentSets	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonFindOutFrequentSets() {
		
		if (jButtonFindOutFrequentSets == null) {
			jButtonFindOutFrequentSets = new JButton();
			jButtonFindOutFrequentSets.setBounds(new Rectangle(74, 391, 301, 27));
			jButtonFindOutFrequentSets.setText("Find Out Frequent Sequences");
			jButtonFindOutFrequentSets
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							
							Date d=new Date();
						    long s1,sTemp,s2;
						    d=new Date();
						    
							if (checkIsNumberFromXToY(jTextFieldDemandedMinimumConfidenceLevel.getText(),0,100) && checkIsNumberFromXToY(jTextFieldLevelForExtraActions.getText(),0,100) && checkIsNumberFromXToY(jTextFieldLengthSimilarity.getText(),0,100)){
								int initialMinSup = Integer.parseInt(jTextFieldDemandedMinimumConfidenceLevel.getText());
								int longerMinSup = Integer.parseInt(jTextFieldLevelForExtraActions.getText());
								double lengthSimilarity = Double.parseDouble(jTextFieldLengthSimilarity.getText()) / 100;
								s1=d.getTime();
								DataStructure.getInstance().getFrequentSequencesDataStructure().setAllFoundSequences(DataStructure.getInstance().getFrequentSequencesDataStructure().getFindSequences().findOutFrequentSequences(initialMinSup, longerMinSup, lengthSimilarity, DataStructure.getInstance().getSplitSequencesDataStructure().getAllRawSequences()));
								d=new Date();
								sTemp=d.getTime();
								System.out.println("Execution time of <find frequent sets> " + (sTemp - s1) + "  miliseconds");
								//DataStructure.getInstance().getFrequentSequencesDataStructure().setAllFoundSequences(createArtificialSequences());
								jLabelNumberFrequentSets.setText("Total number of Frequent Sets: " + DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size());
								String[] tempFrequentSet = new String [DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size()];
								for (int i = 0; i < DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size(); i++){
									tempFrequentSet[i] = " Frequent Set " + i;
									jComboBoxSelectFrequentSet.addItem(tempFrequentSet[i]);
								}
							}
							else{
								JOptionPane.showMessageDialog(jPanelFrequentSets, "Please, insert numeric values (from 0 to 100)",
									    "Numeric warning",
									    JOptionPane.WARNING_MESSAGE);
							}							
						}
					});
		}
		return jButtonFindOutFrequentSets;
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
	
	public static ArrayList<simpleSequence> createArtificialSequences(){
		ArrayList<simpleSequence> tempSequences = new ArrayList<simpleSequence>();
		simpleSequence tempSimpleSequence;
		
		int length = 19;
		int counter = 24;
		ArrayList<Integer> tempSequence = new ArrayList<Integer>();
		ArrayList<Integer> tempInstances = new ArrayList<Integer>();
		ArrayList<Integer> tempExtraActions = new ArrayList<Integer>();
		ArrayList<Integer> tempShorterInstances = new ArrayList<Integer>();
		
		tempSequence.add(0); tempSequence.add(1); tempSequence.add(2); tempSequence.add(3); tempSequence.add(4);
		tempSequence.add(5); tempSequence.add(7); tempSequence.add(8); tempSequence.add(9);	tempSequence.add(10); 
		tempSequence.add(11); tempSequence.add(12); tempSequence.add(13); tempSequence.add(15); tempSequence.add(17); 
		tempSequence.add(18); tempSequence.add(19); tempSequence.add(20); tempSequence.add(21);
		
		tempInstances.add(0); tempInstances.add(1); tempInstances.add(2); tempInstances.add(3); tempInstances.add(4);
		tempInstances.add(5); tempInstances.add(6); tempInstances.add(7); tempInstances.add(8); tempInstances.add(9);
		tempInstances.add(10); tempInstances.add(11); tempInstances.add(12); tempInstances.add(13); tempInstances.add(14);
		tempInstances.add(15); tempInstances.add(16); tempInstances.add(17); tempInstances.add(18); tempInstances.add(19);
		tempInstances.add(20); tempInstances.add(21); tempInstances.add(22); tempInstances.add(23);
		
		tempSimpleSequence = new simpleSequence(length, counter, tempSequence, tempInstances, tempExtraActions, tempShorterInstances);
		
		tempSequences.add(tempSimpleSequence);
		
		return tempSequences;

	}

	/**
	 * This method initializes jPanelShowFrequentSets	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelShowFrequentSets() {
		if (jPanelShowFrequentSets == null) {
			jPanelShowFrequentSets = new JPanel();
			jPanelShowFrequentSets.setLayout(null);
			jPanelShowFrequentSets.setBounds(new Rectangle(525, 45, 600, 481));
			jPanelShowFrequentSets.add(getJPanelSelectFrequentSet(), null);
			jPanelShowFrequentSets.add(getJPanelInformationSelectFrequentSet(), null);
			jPanelShowFrequentSets.setBorder(BorderFactory.createTitledBorder("Frequent Sets"));
		}
		return jPanelShowFrequentSets;
	}

	/**
	 * This method initializes jPanelSelectFrequentSet	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelSelectFrequentSet() {
		if (jPanelSelectFrequentSet == null) {
			jLabelSelectFrequentSet = new JLabel();
			jLabelSelectFrequentSet.setBounds(new Rectangle(21, 75, 161, 30));
			jLabelSelectFrequentSet.setText("Select a Frequent Set");
			jLabelNumberFrequentSets = new JLabel();
			jLabelNumberFrequentSets.setBounds(new Rectangle(20, 28, 262, 27));
			jPanelSelectFrequentSet = new JPanel();
			jPanelSelectFrequentSet.setLayout(null);
			jPanelSelectFrequentSet.setBounds(new Rectangle(29, 17, 542, 136));
			jPanelSelectFrequentSet.setBorder(BorderFactory.createTitledBorder("Select a Frequent Set"));
			jPanelSelectFrequentSet.add(jLabelNumberFrequentSets, null);
			jPanelSelectFrequentSet.add(jLabelSelectFrequentSet, null);
			jPanelSelectFrequentSet.add(getJComboBoxSelectFrequentSet(), null);
		}
		return jPanelSelectFrequentSet;
	}

	/**
	 * This method initializes jPanelInformationSelectFrequentSet	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelInformationSelectFrequentSet() {
		if (jPanelInformationSelectFrequentSet == null) {
			jPanelInformationSelectFrequentSet = new JPanel();
			jPanelInformationSelectFrequentSet.setLayout(null);
			jPanelInformationSelectFrequentSet.setBounds(new Rectangle(29, 171, 542, 284));
			jPanelInformationSelectFrequentSet.setBorder(BorderFactory.createTitledBorder("Selected Frequent Set's Information"));
			jPanelInformationSelectFrequentSet.add(getJTextAreaShowInformationSelectedFrequentSet(), null);
		}
		return jPanelInformationSelectFrequentSet;
	}

	/**
	 * This method initializes jComboBoxSelectFrequentSet	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxSelectFrequentSet() {
		if (jComboBoxSelectFrequentSet == null) {
			jComboBoxSelectFrequentSet = new JComboBox();
			jComboBoxSelectFrequentSet.setBounds(new Rectangle(195, 76, 159, 29));
			jComboBoxSelectFrequentSet
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							showInformationSelectedFrequentSet(DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences(), jComboBoxSelectFrequentSet.getSelectedIndex());
						}
					});
		}
		return jComboBoxSelectFrequentSet;
	}
	
	private void showInformationSelectedFrequentSet(ArrayList<simpleSequence> frequentSets, int index){
		jTextAreaShowInformationSelectedFrequentSet.setText("");
		jTextAreaShowInformationSelectedFrequentSet.setLineWrap(true);
		jTextAreaShowInformationSelectedFrequentSet.setWrapStyleWord(true);
		jTextAreaShowInformationSelectedFrequentSet.append(newline);
		jTextAreaShowInformationSelectedFrequentSet.append("Actions:");
		jTextAreaShowInformationSelectedFrequentSet.append(newline);
		jTextAreaShowInformationSelectedFrequentSet.append(newline);
		int numActions = frequentSets.get(index).getSequence().size() + frequentSets.get(index).getLongExtraActions().size();
		jTextAreaShowInformationSelectedFrequentSet.append("  Number of Actions: " + numActions);
		jTextAreaShowInformationSelectedFrequentSet.append(newline);
		jTextAreaShowInformationSelectedFrequentSet.append(newline);
		jTextAreaShowInformationSelectedFrequentSet.append("  Set of Actions: ");
		jTextAreaShowInformationSelectedFrequentSet.append(newline);
		jTextAreaShowInformationSelectedFrequentSet.append("    ");
		for (int i = 0; i < frequentSets.get(index).getSequence().size(); i++){
			//jTextAreaShowInformationSelectedFrequentSet.append(frequentSets.get(index).getSequence().get(i) + "   ");
			jTextAreaShowInformationSelectedFrequentSet.append(simpleEvent.getEventLabel().get(frequentSets.get(index).getSequence().get(i)).getDevice() + "   " + simpleEvent.getEventLabel().get(frequentSets.get(index).getSequence().get(i)).getAction() + ";  ");
		}
		for (int i = 0; i < frequentSets.get(index).getLongExtraActions().size(); i++){
			//jTextAreaShowInformationSelectedFrequentSet.append(frequentSets.get(index).getLongExtraActions().get(i) + "    ");
			jTextAreaShowInformationSelectedFrequentSet.append(simpleEvent.getEventLabel().get(frequentSets.get(index).getLongExtraActions().get(i)).getDevice() + "   " + simpleEvent.getEventLabel().get(frequentSets.get(index).getLongExtraActions().get(i)).getAction() + ";  " );
		}
		jTextAreaShowInformationSelectedFrequentSet.append(newline);
		jTextAreaShowInformationSelectedFrequentSet.append(newline);
		jTextAreaShowInformationSelectedFrequentSet.append("  Set of Sequences where the frequent set occurred: ");
		jTextAreaShowInformationSelectedFrequentSet.append(newline);
		jTextAreaShowInformationSelectedFrequentSet.append("    ");
		for (int i = 0; i < frequentSets.get(index).getInstances().size(); i++){
			jTextAreaShowInformationSelectedFrequentSet.append(frequentSets.get(index).getInstances().get(i) + "   ");
		}
		for (int i = 0; i < frequentSets.get(index).getShortExtraInstances().size(); i++){
			jTextAreaShowInformationSelectedFrequentSet.append(frequentSets.get(index).getShortExtraInstances().get(i) + "    ");
		}
		
	}

	/**
	 * This method initializes jTextAreaShowInformationSelectedFrequentSet	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextAreaShowInformationSelectedFrequentSet() {
		if (jTextAreaShowInformationSelectedFrequentSet == null) {
			jTextAreaShowInformationSelectedFrequentSet = new JTextArea();
			jTextAreaShowInformationSelectedFrequentSet.setBounds(new Rectangle(17, 29, 508, 243));
		}
		return jTextAreaShowInformationSelectedFrequentSet;
	}
	
	
}
