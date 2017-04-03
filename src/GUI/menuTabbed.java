package GUI;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;

import basic.DataStructure;

public class menuTabbed {

	private static final long serialVersionUID = 1L;

	/**
	 * This is the default constructor
	 */
	public menuTabbed() {
		//super();
	}

	
	public JTabbedPane getJTabbedPane() {
		
		JTabbedPane jTabbedPane0 = null;
		
		LoadingData jPanelLoadingData = new LoadingData();
		
		PreprocessingData jPanelPreprocessingData = new PreprocessingData();
		
		FrequentSets jPanelFrequentSets = new FrequentSets();
		
		Topology jPanelTopology = new Topology();
		
		TimeRelations jPanelTimeRelations = new TimeRelations();
		
		Conditions jPanelConditions = new Conditions();
		
		if (jTabbedPane0 == null) {
			
			jTabbedPane0 = new JTabbedPane();
			jTabbedPane0.setName("");
			jTabbedPane0.addTab("Loading Data", null, jPanelLoadingData.setJPanelLoadingData(jPanelPreprocessingData), null);
			jTabbedPane0.addTab("Preprocessing Data", null, jPanelPreprocessingData.setJPanelPreprocessingData(), null);
			jTabbedPane0.addTab("Frequent Sets", null, jPanelFrequentSets.setJPanelFrequentSets(), null);
			jTabbedPane0.addTab("Topology", null, jPanelTopology.setJPanelTopology(), null);
			jTabbedPane0.addTab("Time Relations", null, jPanelTimeRelations.setJPanelTimeRelations(), null);
			jTabbedPane0.addTab("Conditions", null, jPanelConditions.setJPanelConditions(), null);
			/*jTabbedPane0.addTab("Loading Data", null, getJPanel(), null);
			jTabbedPane0.addTab("Preprocessing Data", null, getJPanel1(), null);
			jTabbedPane0.setEnabledAt(1, false);
			jTabbedPane0.addTab("Frequent Sets", null, getJPanel2(), null);
			jTabbedPane0.setEnabledAt(2, false);
			jTabbedPane0.addTab("Topology", null, getJPanel3(), null);
			jTabbedPane0.setEnabledAt(3, false);
			jTabbedPane0.addTab("Time Relations", null, getJPanel4(), null);
			jTabbedPane0.setEnabledAt(4, false);
			jTabbedPane0.addTab("Conditions", null, getJPanel5(), null);
			jTabbedPane0.setEnabledAt(5, false);
			jTabbedPane0.addTab("Run", null, getJPanel6(), null);
			jTabbedPane0.setEnabledAt(6, false);
			jTabbedPane0.addTab("Visualize", null, getJPanel7(), null);
			jTabbedPane0.setEnabledAt(7, false);*/
		}
		return jTabbedPane0;	
	}


	public JMenuBar getJMenuBar(){
		
		JMenuBar menuBar= new JMenuBar();
		JMenu menu, submenu;
	    JRadioButtonMenuItem rbMenuItem;
	    JCheckBoxMenuItem cbMenuItem;
	    
	    //Build the first menu.
        menu = new JMenu("File");
        //menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        //a group of JMenuItems
        JMenuItem menuItem = new JMenuItem("Option 1", KeyEvent.VK_T);
        menu.add(menuItem);
                
        menuItem = new JMenuItem("Option 2");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Option 3");
        menu.add(menuItem);

        //a group of radio button menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();

        rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("Another one");
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        //a group of check box menu items
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        menu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("Another one");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        menu.add(cbMenuItem);

        //a submenu
        menu.addSeparator();
        submenu = new JMenu("A submenu");
        submenu.setMnemonic(KeyEvent.VK_S);

        submenu.add(menuItem);

        submenu.add(menuItem);
        menu.add(submenu);

        //Build second menu in the menu bar.
        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_N);
        menuBar.add(menu);
        menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
        
        return menuBar;
		
	}
	
}
