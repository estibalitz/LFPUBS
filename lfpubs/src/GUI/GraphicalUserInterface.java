package GUI;

import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;

import basic.DataStructure;

public class GraphicalUserInterface extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public void run() {
		GraphicalUserInterface thisClass = new GraphicalUserInterface();
		thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		thisClass.setVisible(true);
	}

	/**
	 * This is the default constructor
	 */
	public GraphicalUserInterface() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(1169, 643);
		menuTabbed menuTabbedOptions = new menuTabbed();
		this.setJMenuBar(menuTabbedOptions.getJMenuBar());
		this.setContentPane(menuTabbedOptions.getJTabbedPane());
		this.setTitle("Learning Frequent Patterns of User Behaviour System");
	}

	
}  //  @jve:decl-index=0:visual-constraint="8,6"
