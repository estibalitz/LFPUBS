package GUI;

import java.util.ArrayList;
import splitSequences.eventLabel;
import splitSequences.fullSequences;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Component;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ListSelectionModel;

import basic.DataStructure;
import splitSequences.simpleEvent;
import java.awt.Dimension;
import javax.swing.JEditorPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;

public class PreprocessingData extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private final static String newline = "\n";  //  @jve:decl-index=0:

	private JPanel jPanelPreprocessingData = null;  //  @jve:decl-index=0:visual-constraint="10,54"

	private JPanel jPanelGetInformation = null;

	private JPanel jPanel = null;

	private JPanel jPanelTimeGap = null;

	private JPanel jPanelCriticalEvents = null;

	private JLabel jLabelTimeGap = null;

	private JTextArea jTextAreaTimeGapHour = null;

	private JLabel jLabelTimeGapHours = null;

	private JLabel jLabelTimeGapMinutes = null;

	private JLabel jLabelTimeGapSeconds = null;

	private JTextArea jTextAreaTimeGapMinutes = null;

	private JTextArea jTextAreaTimeGapSeconds = null;

	private JButton jButtonTimeGapAdd = null;

	private JList jListCriticalEventsListActions = null;
	
	private JScrollPane listScrollerCriticalEventsListActions = null;

	private JButton jButtonCriticalEventsAdd = null;

	private JList jListCriticalEventsSelectedActions = null;
	
	private JScrollPane listScrollerCriticalEventsSelectedActions = null;

	private JButton jButtonCriticalEventsSeeActions = null;
	
	protected ArrayList<eventLabel> tempCriticalEvents = new ArrayList<eventLabel>();  //  @jve:decl-index=0:
	
	protected ArrayList<String> listSelectedActions = new ArrayList<String>();  //  @jve:decl-index=0:

	private JButton jButtonPreprocessData = null;

	private JPanel jPanelShowInformation = null;

	private JPanel jPanelShowInformationSelectSequence = null;

	private JPanel jPanelShowInformationShowSequence = null;

	private JLabel jLabelShowInformationSelectSequenceTotalSequences = null;

	private JLabel jLabelCurrentTimeGap = null;

	private JComboBox jComboBoxShowInformationSelectSequence = null;

	private JLabel jLabelShowInformationSelectSequence = null;

	private JTextArea jTextAreaShowInformationSelectedSequence = null;

	/**
	 * This is the default constructor
	 */
	public PreprocessingData() {
		//super();
	}

	
	public JPanel setJPanelPreprocessingData() {
		if (jPanelPreprocessingData == null) {
			jPanelPreprocessingData = new JPanel();
			jPanelPreprocessingData.setLayout(null);
			jPanelPreprocessingData.setSize(new Dimension(1172, 567));
			jPanelPreprocessingData.add(getJPanelGetInformation(), null);
			jPanelPreprocessingData.add(getJPanelShowInformation(), null);
		}
		return jPanelPreprocessingData;
	}


	/**
	 * This method initializes jPanelGetInformation	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelGetInformation() {
		if (jPanelGetInformation == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			jPanelGetInformation = new JPanel();
			jPanelGetInformation.setLayout(null);
			jPanelGetInformation.setBounds(new Rectangle(74, 16, 359, 541));
			jPanelGetInformation.add(getJPanelTimeGap(), null);
			jPanelGetInformation.add(getJPanelCriticalEvents(), null);
			jPanelGetInformation.setBorder(BorderFactory.createTitledBorder("Set conditions to split data into sequences"));
			jPanelGetInformation.add(getJButtonPreprocessData(), null);
		}
		return jPanelGetInformation;
	}


	/**
	 * This method initializes jPanelTimeGap	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelTimeGap() {
		if (jPanelTimeGap == null) {
			jLabelCurrentTimeGap = new JLabel();
			jLabelCurrentTimeGap.setBounds(new Rectangle(11, 105, 169, 22));
			jLabelTimeGapSeconds = new JLabel();
			jLabelTimeGapSeconds.setBounds(new Rectangle(227, 66, 50, 25));
			jLabelTimeGapSeconds.setText("seconds");
			jLabelTimeGapMinutes = new JLabel();
			jLabelTimeGapMinutes.setBounds(new Rectangle(139, 66, 50, 25));
			jLabelTimeGapMinutes.setText("minutes");
			jLabelTimeGapHours = new JLabel();
			jLabelTimeGapHours.setBounds(new Rectangle(50, 66, 50, 25));
			jLabelTimeGapHours.setText("hours");
			jLabelTimeGap = new JLabel();
			jLabelTimeGap.setBounds(new Rectangle(22, 26, 256, 24));
			jLabelTimeGap.setText("Time gap between any actions");
			jPanelTimeGap = new JPanel();
			jPanelTimeGap.setLayout(null);
			jPanelTimeGap.setBounds(new Rectangle(27, 21, 309, 139));
			jPanelTimeGap.add(jLabelTimeGap, null);
			jPanelTimeGap.setBorder(BorderFactory.createTitledBorder("Time Gap"));
			jPanelTimeGap.add(getJTextAreaTimeGapHour(), null);
			jPanelTimeGap.add(jLabelTimeGapHours, null);
			jPanelTimeGap.add(jLabelTimeGapMinutes, null);
			jPanelTimeGap.add(jLabelTimeGapSeconds, null);
			jPanelTimeGap.add(getJTextAreaTimeGapMinutes(), null);
			jPanelTimeGap.add(getJTextAreaTimeGapSeconds(), null);
			jPanelTimeGap.add(getJButtonTimeGapAdd(), null);
			jPanelTimeGap.add(jLabelCurrentTimeGap, null);
		}
		return jPanelTimeGap;
	}


	/**
	 * This method initializes jPanelCriticalEvents	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelCriticalEvents() {
		if (jPanelCriticalEvents == null) {
			jPanelCriticalEvents = new JPanel();
			jPanelCriticalEvents.setLayout(null);
			jPanelCriticalEvents.setBounds(new Rectangle(29, 173, 306, 318));
			jPanelCriticalEvents.setBorder(BorderFactory.createTitledBorder("Critical Events"));
			jPanelCriticalEvents.add(getJListCriticalEventsListActions(), null);
			jPanelCriticalEvents.add(getJButtonCriticalEventsAdd(), null);
			jPanelCriticalEvents.add(getJListCriticalEventsSelectedActions(), null);
			jPanelCriticalEvents.add(getJButtonCriticalEventsSeeActions(), null);
		}
		return jPanelCriticalEvents;
	}


	/**
	 * This method initializes jTextAreaTimeGapHour	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextAreaTimeGapHour() {
		if (jTextAreaTimeGapHour == null) {
			jTextAreaTimeGapHour = new JTextArea();
			jTextAreaTimeGapHour.setBounds(new Rectangle(12, 66, 35, 25));
			jTextAreaTimeGapHour.setText("0");
		}
		return jTextAreaTimeGapHour;
	}


	/**
	 * This method initializes jTextAreaTimeGapMinutes	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextAreaTimeGapMinutes() {
		if (jTextAreaTimeGapMinutes == null) {
			jTextAreaTimeGapMinutes = new JTextArea();
			jTextAreaTimeGapMinutes.setBounds(new Rectangle(102, 66, 35, 25));
			jTextAreaTimeGapMinutes.setText("0");
		}
		return jTextAreaTimeGapMinutes;
	}


	/**
	 * This method initializes jTextAreaTimeGapSeconds	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextAreaTimeGapSeconds() {
		if (jTextAreaTimeGapSeconds == null) {
			jTextAreaTimeGapSeconds = new JTextArea();
			jTextAreaTimeGapSeconds.setBounds(new Rectangle(191, 66, 35, 25));
			jTextAreaTimeGapSeconds.setText("0");			
		}
		return jTextAreaTimeGapSeconds;
	}


	/**
	 * This method initializes jButtonTimeGapAdd	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonTimeGapAdd() {
		if (jButtonTimeGapAdd == null) {
			jButtonTimeGapAdd = new JButton();
			jButtonTimeGapAdd.setBounds(new Rectangle(189, 104, 91, 24));
			jButtonTimeGapAdd.setText("Add...");
			jButtonTimeGapAdd.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (checkIsNumber(jTextAreaTimeGapHour.getText()) && checkIsNumber(jTextAreaTimeGapMinutes.getText()) && checkIsNumber(jTextAreaTimeGapSeconds.getText())){
						int tempTimeGap = ((Integer.parseInt(jTextAreaTimeGapHour.getText()) * 60 * 60) + (Integer.parseInt(jTextAreaTimeGapMinutes.getText()) * 60) + Integer.parseInt(jTextAreaTimeGapSeconds.getText())) * 1000;
						DataStructure.getInstance().getSplitSequencesDataStructure().setTimeGap(tempTimeGap);
						jLabelCurrentTimeGap.setText("Time Gap: " + jTextAreaTimeGapHour.getText() + " h " + jTextAreaTimeGapMinutes.getText() + " m " + jTextAreaTimeGapSeconds.getText() + " s");
						//System.out.println("timeGap " + DataStructure.getInstance().getSplitSequencesDataStructure().getTimeGap());
					}
					else{						
						JOptionPane.showMessageDialog(jPanelPreprocessingData, "Please, insert numeric values",
							    "Numeric warning",
							    JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		}
		return jButtonTimeGapAdd;
	}
	
	private boolean checkIsNumber(String text){
		try{
			Integer.parseInt(text);
			return true;
		}
		catch (NumberFormatException e){
			return false;
		}
	}


	/**
	 * This method initializes jListCriticalEventsListActions	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JScrollPane getJListCriticalEventsListActions() {
		if (jListCriticalEventsListActions == null) {
			jListCriticalEventsListActions = new JList();
			jListCriticalEventsListActions.setLayoutOrientation(JList.VERTICAL);
			jListCriticalEventsListActions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			//jListCriticalEventsListActions.setBounds(new Rectangle(15, 29, 271, 138));
			listScrollerCriticalEventsListActions = new JScrollPane(jListCriticalEventsListActions);
			listScrollerCriticalEventsListActions.setBounds(new Rectangle(15, 20, 271, 138));

		}
		return listScrollerCriticalEventsListActions;
	}
	
	
	/**
	 * This method initializes jButtonCriticalEventsAdd	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonCriticalEventsAdd() {
		if (jButtonCriticalEventsAdd == null) {
			jButtonCriticalEventsAdd = new JButton();
			jButtonCriticalEventsAdd.setBounds(new Rectangle(193, 168, 90, 24));
			jButtonCriticalEventsAdd.setText("Add...");
			jButtonCriticalEventsAdd.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("selected value" + jListCriticalEventsListActions.getSelectedIndex());
					String tempNameDevice = simpleEvent.getEventLabel().get(jListCriticalEventsListActions.getSelectedIndex()).getDevice();
					String tempAction = simpleEvent.getEventLabel().get(jListCriticalEventsListActions.getSelectedIndex()).getAction();
					DataStructure.getInstance().getSplitSequencesDataStructure().getCriticalEvents().add(new eventLabel (tempNameDevice, tempAction));
					tempCriticalEvents.add(new eventLabel (tempNameDevice, tempAction));
					listSelectedActions.add(tempNameDevice + ", " + tempAction);
					String [] tempListSelectedActions = new String [listSelectedActions.size()];
					for (int i = 0; i < listSelectedActions.size(); i++){
						tempListSelectedActions[i] = listSelectedActions.get(i);
					}
					jListCriticalEventsSelectedActions.setListData(tempListSelectedActions);
				}
			});
		}
		return jButtonCriticalEventsAdd;
	}


	/**
	 * This method initializes jListCriticalEventsSelectedActions	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JScrollPane getJListCriticalEventsSelectedActions() {
		if (jListCriticalEventsSelectedActions == null) {
			jListCriticalEventsSelectedActions = new JList();
			jListCriticalEventsSelectedActions.setLayoutOrientation(JList.VERTICAL);
			//jListCriticalEventsSelectedActions.setBounds(new Rectangle(20, 223, 264, 104));
			listScrollerCriticalEventsSelectedActions = new JScrollPane(jListCriticalEventsSelectedActions);
			listScrollerCriticalEventsSelectedActions.setBounds(new Rectangle(17, 197, 267, 104));
		}
		return listScrollerCriticalEventsSelectedActions;
	}


	/**
	 * This method initializes jButtonCriticalEventsSeeActions	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonCriticalEventsSeeActions() {
		if (jButtonCriticalEventsSeeActions == null) {
			jButtonCriticalEventsSeeActions = new JButton();
			jButtonCriticalEventsSeeActions.setBounds(new Rectangle(78, 167, 104, 24));
			jButtonCriticalEventsSeeActions.setText("See Actions");
			jButtonCriticalEventsSeeActions
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							String[] listActions = new String[simpleEvent.getEventLabel().size()];
							//System.out.println("size " + simpleEvent.getEventLabel().size());
							for (int i = 0; i < simpleEvent.getEventLabel().size(); i++){
								//System.out.println("Device " + simpleEvent.getEventLabel().get(i).getDevice());
								listActions[i] = simpleEvent.getEventLabel().get(i).getDevice() + ", " + simpleEvent.getEventLabel().get(i).getAction();
								jListCriticalEventsListActions.setListData(listActions);
							}
							
						}
					});
		}
		return jButtonCriticalEventsSeeActions;
	}


	/**
	 * This method initializes jButtonPreprocessData	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonPreprocessData() {
		if (jButtonPreprocessData == null) {
			jButtonPreprocessData = new JButton();
			jButtonPreprocessData.setBounds(new Rectangle(165, 499, 167, 29));
			jButtonPreprocessData.setText("Preprocess Data");
			jButtonPreprocessData.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (DataStructure.getInstance().getSequencesVSOneToOne() == 0){
						DataStructure.getInstance().getSplitSequencesDataStructure().setAllRawSequences(DataStructure.getInstance().getSplitSequencesDataStructure().getEventsToSequences().singleEventsToSequencesOneToOne(DataStructure.getInstance().getSplitSequencesDataStructure().getSimpleEvents()));
					}
					else{
						DataStructure.getInstance().getSplitSequencesDataStructure().setAllRawSequences(DataStructure.getInstance().getSplitSequencesDataStructure().getEventsToSequences().singleEventsToSequencesSequences(DataStructure.getInstance().getSplitSequencesDataStructure().getSimpleEvents(), DataStructure.getInstance().getSplitSequencesDataStructure().getTimeGap(), DataStructure.getInstance().getSplitSequencesDataStructure().getCriticalEvents()));
					}
					
										
					//DataStructure.getInstance().getSplitSequencesDataStructure().setAllRawSequences(DataStructure.getInstance().getSplitSequencesDataStructure().getEventsToSequences().singleEventsToSequences(DataStructure.getInstance().getSplitSequencesDataStructure().getSimpleEvents(), DataStructure.getInstance().getSplitSequencesDataStructure().getTimeGap(), DataStructure.getInstance().getSplitSequencesDataStructure().getCriticalEvents()));
					jLabelShowInformationSelectSequenceTotalSequences.setText("Total number of sequences: " + DataStructure.getInstance().getSplitSequencesDataStructure().getAllRawSequences().size());
					//System.out.println("size sequences " + DataStructure.getInstance().getSplitSequencesDataStructure().getAllRawSequences().size());
					String[] tempSplitSequences = new String [DataStructure.getInstance().getSplitSequencesDataStructure().getAllRawSequences().size()];
					for (int i = 0; i < DataStructure.getInstance().getSplitSequencesDataStructure().getAllRawSequences().size(); i++){
						tempSplitSequences[i] = " Sequence " + i;
						jComboBoxShowInformationSelectSequence.addItem(tempSplitSequences[i]);
					}
					/*for (int i = 0; i < DataStructure.getInstance().getSplitSequencesDataStructure().getAllRawSequences().size(); i++){
						for (int j = 0; j < DataStructure.getInstance().getSplitSequencesDataStructure().getAllRawSequences().get(i).getEventsOfSequence().size(); j++){
							System.out.print(DataStructure.getInstance().getSplitSequencesDataStructure().getAllRawSequences().get(i).getEventsOfSequence().get(j).getEvent() + " ");
						}
						System.out.println();
					}*/
				}
			});
		}
		return jButtonPreprocessData;
	}


	/**
	 * This method initializes jPanelShowInformation	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelShowInformation() {
		if (jPanelShowInformation == null) {
			jPanelShowInformation = new JPanel();
			jPanelShowInformation.setLayout(null);
			jPanelShowInformation.setBounds(new Rectangle(569, 15, 512, 541));
			jPanelShowInformation.setBorder(BorderFactory.createTitledBorder("Split Sequences"));
			jPanelShowInformation.add(getJPanelShowInformationSelectSequence(), null);
			jPanelShowInformation.add(getJPanelShowInformationShowSequence(), null);
		}
		return jPanelShowInformation;
	}


	/**
	 * This method initializes jPanelShowInformationSelectSequence	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelShowInformationSelectSequence() {
		if (jPanelShowInformationSelectSequence == null) {
			jLabelShowInformationSelectSequence = new JLabel();
			jLabelShowInformationSelectSequence.setBounds(new Rectangle(17, 79, 121, 24));
			jLabelShowInformationSelectSequence.setText("Select a sequence");
			jLabelShowInformationSelectSequenceTotalSequences = new JLabel();
			jLabelShowInformationSelectSequenceTotalSequences.setBounds(new Rectangle(16, 25, 175, 25));
			//jLabelShowInformationSelectSequenceTotalSequences.setText("Total Number of Sequences: ");
			jPanelShowInformationSelectSequence = new JPanel();
			jPanelShowInformationSelectSequence.setLayout(null);
			jPanelShowInformationSelectSequence.setBounds(new Rectangle(16, 16, 449, 140));
			jPanelShowInformationSelectSequence.setBorder(BorderFactory.createTitledBorder(null, "Select a Sequence", TitledBorder.LEADING, TitledBorder.TOP, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			jPanelShowInformationSelectSequence.add(jLabelShowInformationSelectSequenceTotalSequences, null);
			jPanelShowInformationSelectSequence.add(getJComboBoxShowInformationSelectSequence(), null);
			jPanelShowInformationSelectSequence.add(jLabelShowInformationSelectSequence, null);
		}
		return jPanelShowInformationSelectSequence;
	}


	/**
	 * This method initializes jPanelShowInformationShowSequence	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanelShowInformationShowSequence() {
		if (jPanelShowInformationShowSequence == null) {
			jPanelShowInformationShowSequence = new JPanel();
			jPanelShowInformationShowSequence.setLayout(null);
			jPanelShowInformationShowSequence.setBounds(new Rectangle(15, 176, 450, 315));
			jPanelShowInformationShowSequence.setBorder(BorderFactory.createTitledBorder("Seleced Sequence's information"));
			jPanelShowInformationShowSequence.add(getJTextAreaShowInformationSelectedSequence(), null);
		}
		return jPanelShowInformationShowSequence;
	}


	/**
	 * This method initializes jComboBoxShowInformationSelectSequence	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJComboBoxShowInformationSelectSequence() {
		if (jComboBoxShowInformationSelectSequence == null) {
			jComboBoxShowInformationSelectSequence = new JComboBox();
			jComboBoxShowInformationSelectSequence.setBounds(new Rectangle(155, 79, 199, 26));
			jComboBoxShowInformationSelectSequence
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							showInformationSelectedSequence(DataStructure.getInstance().getSplitSequencesDataStructure().getAllRawSequences().get(jComboBoxShowInformationSelectSequence.getSelectedIndex()), jComboBoxShowInformationSelectSequence.getSelectedIndex());
						}
					});
			
		}
		return jComboBoxShowInformationSelectSequence;
	}


	/**
	 * This method initializes jTextAreaShowInformationSelectedSequence	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJTextAreaShowInformationSelectedSequence() {
		if (jTextAreaShowInformationSelectedSequence == null) {
			jTextAreaShowInformationSelectedSequence = new JTextArea();
			jTextAreaShowInformationSelectedSequence.setBounds(new Rectangle(15, 31, 411, 270));
		}
		return jTextAreaShowInformationSelectedSequence;
	}


	
	private void showInformationSelectedSequence (fullSequences sequence, int numSequence){
		jTextAreaShowInformationSelectedSequence.setText("");
		jTextAreaShowInformationSelectedSequence.setWrapStyleWord(true);
		jTextAreaShowInformationSelectedSequence.append(newline);
		jTextAreaShowInformationSelectedSequence.append("Sequence " + numSequence + newline);
		jTextAreaShowInformationSelectedSequence.append(newline);
		jTextAreaShowInformationSelectedSequence.append(newline);
		jTextAreaShowInformationSelectedSequence.append("Number of actions in this sequence:  " + sequence.getEventsOfSequence().size());
		jTextAreaShowInformationSelectedSequence.append(newline);
		jTextAreaShowInformationSelectedSequence.append(newline);
		jTextAreaShowInformationSelectedSequence.append("Starting Action:");
		jTextAreaShowInformationSelectedSequence.append(newline);
		jTextAreaShowInformationSelectedSequence.append("  Index: " + sequence.getEventsOfSequence().get(0).getIndexSimpleEvent());
		jTextAreaShowInformationSelectedSequence.append(newline);
		jTextAreaShowInformationSelectedSequence.append("  Time: " + sequence.getEventsOfSequence().get(0).getTime().getTime().toString());
		jTextAreaShowInformationSelectedSequence.append(newline);
		jTextAreaShowInformationSelectedSequence.append("  Action: " + sequence.getEventsOfSequence().get(0).getDevice() + " , " + sequence.getEventsOfSequence().get(0).getAction());
		jTextAreaShowInformationSelectedSequence.append(newline);
		jTextAreaShowInformationSelectedSequence.append(newline);
		jTextAreaShowInformationSelectedSequence.append("Last Action:");
		jTextAreaShowInformationSelectedSequence.append(newline);
		jTextAreaShowInformationSelectedSequence.append("  Index: " + sequence.getEventsOfSequence().get(sequence.getEventsOfSequence().size()-1).getIndexSimpleEvent());
		jTextAreaShowInformationSelectedSequence.append(newline);
		jTextAreaShowInformationSelectedSequence.append("  Time: " + sequence.getEventsOfSequence().get(sequence.getEventsOfSequence().size()-1).getTime().getTime().toString());
		jTextAreaShowInformationSelectedSequence.append(newline);
		jTextAreaShowInformationSelectedSequence.append("  Action: " + sequence.getEventsOfSequence().get(sequence.getEventsOfSequence().size()-1).getDevice() + " , " + sequence.getEventsOfSequence().get(sequence.getEventsOfSequence().size()-1).getAction());		
	}

	

}
