package GUI;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

import splitSequences.simpleEvent;

import basic.DataStructure;
import java.awt.Dimension;

public class LoadingData extends JFrame{

	private static final long serialVersionUID = 1L;

	JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="10,434"
	private JLabel jLabel = null;
	private JButton jButton = null;
    private JFileChooser fc = null;
	private JTextField jTextField = null;
	static private final String newline = "\n";
	private JButton acceptButton = null;
	private JProgressBar jProgressBar = null;
	private File file;
	private ButtonGroup SequencesVSOneToOne = new ButtonGroup();
	
	private JRadioButtonMenuItem jRadioButtonMenuItemOneToOneRelations = null;

	private JRadioButtonMenuItem jRadioButtonMenuItemCompleteSequences = null;

	/**
	 * This is the default constructor
	 */
	public LoadingData() {
		//super();
	}

	

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel setJPanelLoadingData(PreprocessingData jPanelPreprocessingData) {
				
		if (jPanel == null) {
			JLabel jLabel = new JLabel(new ImageIcon("images/logo1.png"));
			jLabel.setBounds(new Rectangle(46, 71, 1065, 184));
			jLabel.setText("");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(jLabel, null);
			jPanel.add(getJButton(), null);
			jPanel.add(getJTextField(), null);
			jPanel.add(getAcceptButton(jPanelPreprocessingData), null);
			jPanel.add(getJProgressBar(), null);
			jPanel.add(getJRadioButtonMenuItemOneToOneRelations(), null);
			jPanel.add(getJRadioButtonMenuItemCompleteSequences(), null);
			SequencesVSOneToOne.add(getJRadioButtonMenuItemOneToOneRelations());
			SequencesVSOneToOne.add(getJRadioButtonMenuItemCompleteSequences());
		}
		return jPanel;
	}
	
	private JButton getJButton() {
		if (jButton == null) {
			fc = new JFileChooser();
			jButton = new JButton("Open a File...",new ImageIcon("images/open.gif"));
			jButton.setBounds(new Rectangle(649, 300, 152, 30));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int returnVal = fc.showOpenDialog(LoadingData.this);

		            if (returnVal == JFileChooser.APPROVE_OPTION) {
		                file = fc.getSelectedFile();
		                //This is where a real application would open the file.
		                jTextField.setText("Opening: " + file.getName() + "." + newline);
		                acceptButton.setEnabled(true);
		            } else {
		                jTextField.setText("Open command cancelled by user." + newline);
		                acceptButton.setEnabled(false);
		            }
		            jTextField.setCaretPosition(jTextField.getDocument().getLength());
					// TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButton;
	}
	
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(347, 300, 300, 31));
		}
		return jTextField;
	}
	
	private JButton getAcceptButton(PreprocessingData jPanelPreprocessingData) {
		if (acceptButton == null) {
			acceptButton = new JButton("Load");
			acceptButton.setBounds(new Rectangle(497, 346, 152, 30));
			acceptButton.setEnabled(false);
			acceptButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e)  {
					jProgressBar.setVisible(true);
					jProgressBar.setStringPainted(true);
					jProgressBar.setValue(100);
					jProgressBar.setString("100%");
					DataStructure.getInstance().setNameFile(fc.getSelectedFile().getPath());
					try{
						if(jRadioButtonMenuItemOneToOneRelations.isSelected()){
							DataStructure.getInstance().setSequencesVSOneToOne(0);
							
						}
						else{
							DataStructure.getInstance().setSequencesVSOneToOne(1);
						}
						DataStructure.getInstance().getSplitSequencesDataStructure().setSimpleEvents(DataStructure.getInstance().getSplitSequencesDataStructure().getFileToSimpleEvent().parseIntoSimpleEvents(DataStructure.getInstance().getNameFile()));
					}
					catch (ParseException parseException){
						System.err.println("Caught parseException: " 
		                        + parseException.getMessage());
					}
					catch (IOException ioException){
						System.err.println("Caught IOException: " 
		                        + ioException.getMessage());
					}
					
				}
			});
		}
		return acceptButton;
	}
	
	private JProgressBar getJProgressBar() {
		if (jProgressBar == null) {
			jProgressBar = new JProgressBar();
			jProgressBar.setBounds(new Rectangle(397, 386, 348, 17));
			jProgressBar.setVisible(false);
		}
		return jProgressBar;
	}
	
	/**
	 * This method initializes jRadioButtonMenuItemOneToOneRelations	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRadioButtonMenuItemOneToOneRelations() {
		if (jRadioButtonMenuItemOneToOneRelations == null) {
			jRadioButtonMenuItemOneToOneRelations = new JRadioButtonMenuItem();
			jRadioButtonMenuItemOneToOneRelations.setBounds(new Rectangle(401, 423, 214, 26));
			jRadioButtonMenuItemOneToOneRelations.setText("Pairwise Approach");
			//jRadioButtonMenuItemOneToOneRelations.setSelected(true);
		}
		return jRadioButtonMenuItemOneToOneRelations;
	}



	/**
	 * This method initializes jRadioButtonMenuItemCompleteSequences	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	private JRadioButtonMenuItem getJRadioButtonMenuItemCompleteSequences() {
		if (jRadioButtonMenuItemCompleteSequences == null) {
			jRadioButtonMenuItemCompleteSequences = new JRadioButtonMenuItem();
			jRadioButtonMenuItemCompleteSequences.setBounds(new Rectangle(611, 422, 234, 28));
			jRadioButtonMenuItemCompleteSequences.setText("Action Map Approach");
			jRadioButtonMenuItemCompleteSequences.setSelected(true);
		}
		return jRadioButtonMenuItemCompleteSequences;
	}

}
