package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.Rectangle;
import java.awt.GridBagLayout;
import java.awt.geom.Rectangle2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import basic.DataStructure;
import basic.simpleNode;
import basic.simplePattern;
import tree.Node;
import tree.Link;

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
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;


import splitSequences.simpleEvent;
import splitSequences.fullSequences;
import splitSequences.eventLabel;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.hierarchical.JGraphHierarchicalLayout;

import conditions.simpleBasicConditionNominal;
import conditions.simpleBasicConditionNumeric;
import conditions.simpleCompleteCondition;
import frequentSequences.simpleSequence;

import javafx.util.Pair;
import tree.TreeDataStructure;


public class Automate extends JFrame {

	private static final long serialVersionUID = 1L;

	private final static String newline = "\n";
	
	private int recursivity = 0;

	private JPanel jPanelAutomate=null;
	
	private JPanel jPanelGetInformation=null;
	
	private JPanel jPanelInputDevices=null;
	
	private JPanel jPanelShowAutomation=null;
	
	private JPanel jContentPanePathsTopology = null;
	
	private JPanel jPanelPatterns=null;
	
	private JPanel jPanelPatternsEvents=null;
	
	private JPanel jPanelPatternsProperties=null;
	
	private JTable jTableDirectedGraph=null; 
	
	private JScrollPane jScrollPaneTable=null;
	
	private JScrollPane jScrollPanePathProperties=null;
	
	private JTextArea jTextAreaPathsProperties=null;
	
	private JInternalFrame jInternalFrameTable=null;
	
	private JPanel jContentPaneTable=null;
	
	private JButton jButtonSeeDevices=null;
	
	private JButton jButtonSavePatterns=null;
	
	private JButton jButtonAddDevices=null;
	
	private JButton jButtonDelete=null;
	
	private JButton jButtonAutomate=null;
	
	private JList jListDevices=null;
	
	private JScrollPane listsScrollerDevices=null;
	
	private JList jListSelectedDevices=null;
	
	private JScrollPane listsScrollSelectedDevices=null;
	
	private JLabel jLabelSelectSequence=null;
	
	private JLabel jLabelSeeList=null;
	
	private JLabel jLabelSelectedDevices=null;
	
	private JScrollPane  jScrollPathsTopology=null;
	
	private JInternalFrame jInternalFramePathsTopology = null;
	
	private JComboBox jComboBoxSelectSequence = null;
	
	private JSlider jSliderSelectGranularity = null;

	protected double minduration;
	
	protected ArrayList<eventLabel>tempDevices=new ArrayList<eventLabel>();
	
	protected ArrayList<String>listSelectedDevices= new ArrayList<String>();
	
	protected static LinkedHashMap<String,Double>Average=new LinkedHashMap<String,Double>();
	
	ArrayList<ImmutableTriple<Double,Integer,ArrayList<Node>>> failingPaths=new ArrayList<ImmutableTriple<Double,Integer,ArrayList<Node>>>();
	
	ArrayList<ImmutableTriple<Double,Integer,ArrayList<Node>>> FinalfailingPaths=new ArrayList<ImmutableTriple<Double,Integer,ArrayList<Node>>>();
	
	ArrayList<Node>currentPath=new ArrayList<Node>();
	
	ArrayList<Node>currentPath2=new ArrayList<Node>();
	
	ArrayList<DefaultGraphCell> tempNodeCells = new ArrayList<DefaultGraphCell>();  //  @jve:decl-index=0:
	
	ArrayList<DefaultGraphCell> tempRelationCells = new ArrayList<DefaultGraphCell>();
	
	int numbAction=0;
	protected static LinkedHashMap<String, Double> checkRepMaxDevices= new LinkedHashMap<String,Double>();

	public Automate() {
		// TODO Auto-generated constructor stub
	}
	
	//Initialize the panel and separate everything in three parts
	public JPanel setJPanelAutomate() {
		if (jPanelAutomate == null) {
			jPanelAutomate = new JPanel();
			jPanelAutomate.setLayout(null);
			jPanelAutomate.setSize(new Dimension(1172,567));
			jPanelAutomate.add(getJPanelGetInformation(),null);
			jPanelAutomate.add(getJPanelShowAutomation(),null);
			jPanelAutomate.add(getJPanelPatterns(),null);
		}
		return jPanelAutomate;
	}

	private JPanel getJPanelGetInformation(){
		if(jPanelGetInformation==null){
			GridBagConstraints gridBagConstrains= new GridBagConstraints();
			gridBagConstrains.gridx=0;
			gridBagConstrains.gridy=0;
			jLabelSelectSequence=new JLabel();
			jLabelSelectSequence.setBounds(new Rectangle(13,25,119,27));
			jLabelSelectSequence.setText("Select a Sequence");
			jPanelGetInformation= new JPanel();
			jPanelGetInformation.setLayout(null);
			jPanelGetInformation.setBounds(new Rectangle (74,16,685,315));
			jPanelGetInformation.add(getJPanelInputDevices(), null);
			jPanelGetInformation.setBorder(BorderFactory.createTitledBorder("Set Devices to Automate"));
			jPanelGetInformation.add(getJButtonAutomate(), null);
			jPanelGetInformation.add(getJComboBoxSelectSequence(),null);
			jPanelGetInformation.add(jLabelSelectSequence,null);
			jPanelGetInformation.add(getJButtonAddDevices(), null);
			jPanelGetInformation.add(getJButtonSeeDevices(), null);
			jPanelGetInformation.add(getJButtonDelete(), null);
		
		}
		return jPanelGetInformation;
	}
	private JComboBox getJComboBoxSelectSequence() {
		if (jComboBoxSelectSequence == null) {
			jComboBoxSelectSequence = new JComboBox();
			jComboBoxSelectSequence.setBounds(new Rectangle(145, 25, 200, 27));
			jComboBoxSelectSequence.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
	
				}
			});
		}
		return jComboBoxSelectSequence;
	}

	private JPanel getJPanelInputDevices(){
		if(jPanelInputDevices==null){
			jLabelSeeList= new JLabel();
			jLabelSeeList.setBounds(new Rectangle(100,160,119,27));
			jLabelSeeList.setText("Devices");
			jLabelSelectedDevices= new JLabel();
			jLabelSelectedDevices.setBounds(new Rectangle(430,160,119,27));
			jLabelSelectedDevices.setText("Selected Devices");
			jPanelInputDevices=new JPanel();
			jPanelInputDevices.setLayout(null);
			jPanelInputDevices.setBounds(new Rectangle(29,75,625,200));
			jPanelInputDevices.setBorder(BorderFactory.createTitledBorder("Show Devices to Automate"));
			jPanelInputDevices.add(jLabelSeeList,null);
			jPanelInputDevices.add(jLabelSelectedDevices,null);
			jPanelInputDevices.add(getJListDevices(), null);
			jPanelInputDevices.add(getJListSelectedDevices(), null);
			
		}
		return jPanelInputDevices;

	}
	private JScrollPane getJListDevices(){
		if(jListDevices==null){
			jListDevices= new JList();
			jListDevices.setLayoutOrientation(JList.VERTICAL);
			jListDevices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listsScrollerDevices= new JScrollPane(jListDevices);
			listsScrollerDevices.setBounds(new Rectangle(19,30,250,135));
		}
		return listsScrollerDevices;
	}
	private JScrollPane getJListSelectedDevices(){
		if(jListSelectedDevices==null){
			jListSelectedDevices=new JList();
			jListSelectedDevices.setLayoutOrientation(JList.VERTICAL);
			listsScrollSelectedDevices=new JScrollPane(jListSelectedDevices);
			listsScrollSelectedDevices.setBounds(new Rectangle(360,30,250,135));
		}
		return listsScrollSelectedDevices;
	}
	private JButton getJButtonDelete(){
		if(jButtonDelete==null){
			jButtonDelete=new JButton();
			jButtonDelete.setBounds(new Rectangle(550,280,104,24));
			jButtonDelete.setText("Delete");
			jButtonDelete.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//Remove all the selected Devices from the list
					String[] listDevices= new String [DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().get(jComboBoxSelectSequence.getSelectedIndex()).getSequence().size()];
					for(int i = 0; i < DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().get(jComboBoxSelectSequence.getSelectedIndex()).getSequence().size(); i++){
						listDevices[i]=null;
					}
				jListSelectedDevices.setListData(listDevices);
				listSelectedDevices.clear();
				
					//Remove all the events of the graph
				jInternalFramePathsTopology.getContentPane().remove(jScrollPathsTopology);
				jInternalFramePathsTopology.updateUI();
				jInternalFrameTable.getContentPane().remove(jScrollPaneTable);
				jInternalFrameTable.updateUI();
	
				//Remove all the properties of the paths in the JTextArea
				jTextAreaPathsProperties.setText(null);
				}
				});
			}

			return jButtonDelete;
	}
	
	private JButton getJButtonSeeDevices(){
		if(jButtonSeeDevices==null){
			jButtonSeeDevices=new JButton();
			jButtonSeeDevices.setBounds(new Rectangle(50,280,104,24));
			jButtonSeeDevices.setText("See Devices");
			jButtonSeeDevices.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//Initialize combo box
					if(jComboBoxSelectSequence.getItemCount()==0){
					String[] tempFrequentSet = new String [DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size()];
					for (int i = 0; i < DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().size(); i++){
						tempFrequentSet[i] = " Sequence " + i;
						jComboBoxSelectSequence.addItem(tempFrequentSet[i]);
						}
					}
					String[] listDevices= new String [DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().get(jComboBoxSelectSequence.getSelectedIndex()).getSequence().size()];
					for (int i = 0; i < DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().get(jComboBoxSelectSequence.getSelectedIndex()).getSequence().size(); i++){
						String Device=simpleEvent.getEventLabel().get(DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().get(jComboBoxSelectSequence.getSelectedIndex()).getSequence().get(i)).getDevice();
						String Action=simpleEvent.getEventLabel().get(DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().get(jComboBoxSelectSequence.getSelectedIndex()).getSequence().get(i)).getAction();
						if((Action.compareTo("ON")==0)||(Action.compareTo("on")==0)){
							String Event= Device+" "+ Action;
							listDevices [i]=Event;
						}
					}
					jListDevices.setListData(listDevices);
					}
				});
				
			}
			return jButtonSeeDevices;
			}
	
	private JButton getJButtonAddDevices(){
		if(jButtonAddDevices==null){
			jButtonAddDevices= new JButton();
			jButtonAddDevices.setBounds(new Rectangle(200,280,104,24));
			jButtonAddDevices.setText("Add..");
			jButtonAddDevices.addActionListener(new java.awt.event.ActionListener(){
			
				public void actionPerformed(java.awt.event.ActionEvent e){
					String tempNameDevice=simpleEvent.getEventLabel().get(DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().get(jComboBoxSelectSequence.getSelectedIndex()).getSequence().get(jListDevices.getSelectedIndex())).getDevice();
					String tempAction=simpleEvent.getEventLabel().get(DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences().get(jComboBoxSelectSequence.getSelectedIndex()).getSequence().get(jListDevices.getSelectedIndex())).getAction();
					tempDevices.add(new eventLabel(tempNameDevice,tempAction));
					listSelectedDevices.add(tempNameDevice+" "+tempAction);
					String []tempListSelectedDevices= new String [listSelectedDevices.size()];
					for(int i=0; i<listSelectedDevices.size();i++){
						tempListSelectedDevices[i]=listSelectedDevices.get(i);
					}
					jListSelectedDevices.setListData(tempListSelectedDevices);
						}
					});
				}
		return jButtonAddDevices;
			
			}

	private JPanel getJPanelShowAutomation() {
		if (jPanelShowAutomation == null) {
			jPanelShowAutomation = new JPanel();
			jPanelShowAutomation.setLayout(null);
			jPanelShowAutomation.setBounds(new Rectangle(74, 350, 685, 300));
			jPanelShowAutomation.setBorder(BorderFactory.createTitledBorder("Show Data"));
			jPanelShowAutomation.add(getJInternalFramePathsTopology(),null);
			
		}
		return jPanelShowAutomation;
	}	
	
	private JButton getJButtonAutomate(){
		if(jButtonAutomate==null){
			jButtonAutomate= new JButton();
			jButtonAutomate.setBounds(new Rectangle(350,280,160,24));
			jButtonAutomate.setText("Automate Devices");
			jButtonAutomate.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e){
					//Initialize time
					Date d=new Date();
				    long s1,sTemp,s2;
				    d=new Date();
				    s1=d.getTime();
					//Initialize granularity slicer
					int maximumGranularity = (int)DataStructure.getInstance().getSimplePatterns().get(0).getMaximumFrequencyFromStartToEnd();
					jPanelShowAutomation.add(getJSliderSelectGranularity(maximumGranularity), null);
					//Initialize the calculus of all the variables
					checkRepMaxDevices(DataStructure.getInstance().getSplitSequencesDataStructure().getAllRawSequences(),jComboBoxSelectSequence.getSelectedIndex(),DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences());
					//Initialize the structure in which all the data will be analyzed
					jScrollPaneTable= new JScrollPane(createStructure(DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()), jSliderSelectGranularity.getValue(),listSelectedDevices));
					jInternalFrameTable.getContentPane().add(jScrollPaneTable);
					jInternalFrameTable.setVisible(true);
					//Initialize the Final paths graph
					jScrollPathsTopology= new JScrollPane(visualizePaths());
					jInternalFramePathsTopology.getContentPane().add(jScrollPathsTopology);
					jInternalFramePathsTopology.setVisible(true);
					//Final Time
					d=new Date();
					sTemp=d.getTime();
					System.out.println("Execution time of <Automation> " + (sTemp - s1) + "  miliseconds");
					
				}
		});
	}
	return jButtonAutomate;
}
	private JSlider getJSliderSelectGranularity(int maximumGranularity) {
		if (jSliderSelectGranularity == null) {
			jSliderSelectGranularity = new JSlider(JSlider.HORIZONTAL,0,maximumGranularity,maximumGranularity/2);
			jSliderSelectGranularity.setBounds(new Rectangle(150, 23, 349, 43));
			jSliderSelectGranularity.setFont(new Font("Serif", Font.ITALIC, 12));
			jSliderSelectGranularity.setMajorTickSpacing(maximumGranularity/4);
			jSliderSelectGranularity.setMinorTickSpacing(1);
			jSliderSelectGranularity.setPaintTicks(true);
			jSliderSelectGranularity.setPaintLabels(true);
			jSliderSelectGranularity.setSnapToTicks(true);
			jSliderSelectGranularity
					.addChangeListener(new javax.swing.event.ChangeListener() {
						public void stateChanged(javax.swing.event.ChangeEvent e) {
							jInternalFrameTable.getContentPane().remove(jScrollPaneTable);
							jScrollPaneTable= new JScrollPane (createStructure(DataStructure.getInstance().getSimplePatterns().get(jComboBoxSelectSequence.getSelectedIndex()), jSliderSelectGranularity.getValue(),listSelectedDevices));
							jInternalFrameTable.getContentPane().add(jScrollPaneTable);
							jInternalFrameTable.updateUI();
							
							jInternalFramePathsTopology.getContentPane().remove(jScrollPathsTopology);
							jScrollPathsTopology= new JScrollPane(visualizePaths());
							jInternalFramePathsTopology.getContentPane().add(jScrollPathsTopology );
							jInternalFramePathsTopology.updateUI();
							//jTableDirectedGraph=new JTable(initTable(Nodes))
						
						}
					});
		}
		return jSliderSelectGranularity;
	}
	
	private JInternalFrame getJInternalFramePathsTopology() {
		if (jInternalFramePathsTopology == null) {
			jInternalFramePathsTopology = new JInternalFrame();
			jInternalFramePathsTopology.setBounds(new Rectangle(14, 65, 650, 200));
			jInternalFramePathsTopology.setContentPane(getJContentPanePathsTopology());
		}
		return jInternalFramePathsTopology;
	}
	
	private JPanel getJContentPanePathsTopology() {
		if (jContentPanePathsTopology == null) {
			jContentPanePathsTopology = new JPanel();
			jContentPanePathsTopology.setLayout(new BorderLayout());
		}
		return jContentPanePathsTopology;
	}
	
	private JPanel getJPanelPatterns(){
		if(jPanelPatterns==null){
		jPanelPatterns = new JPanel();
		jPanelPatterns.setLayout(null);
		jPanelPatterns.setBounds(new Rectangle(800, 16, 635, 635));
		jPanelPatterns.setBorder(BorderFactory.createTitledBorder("Show Patterns"));
		jPanelPatterns.add(getJPanelPatternsEvents(),null);
		jPanelPatterns.add(getJPanelPatternsProperties(),null);
		jPanelPatterns.add(getJButtonSavePatterns(),null);
		}
		return  jPanelPatterns;
	}
	private JPanel getJPanelPatternsEvents(){
		if(jPanelPatternsEvents==null){
		jPanelPatternsEvents = new JPanel();
		jPanelPatternsEvents.setLayout(null);
		jPanelPatternsEvents.setBounds(new Rectangle(20, 16, 550, 280));
		jPanelPatternsEvents.setBorder(BorderFactory.createTitledBorder("Directed Graphs Connections"));
		jPanelPatternsEvents.add(getJInternalFrameTable(), null);
		}
		return jPanelPatternsEvents;
	}
	private JPanel getJPanelPatternsProperties(){
		if(jPanelPatternsProperties==null){
			jPanelPatternsProperties = new JPanel();
			jPanelPatternsProperties.setLayout(null);
			jPanelPatternsProperties.setBounds(new Rectangle(20, 315, 550, 280));
			jPanelPatternsProperties.setBorder(BorderFactory.createTitledBorder("Paths Properties"));
			jPanelPatternsProperties.add(getJTextAreaPathsProperties(),null);
			}
		return jPanelPatternsProperties;
	}
	private JScrollPane getJTextAreaPathsProperties(){
		if(jTextAreaPathsProperties==null){
			jTextAreaPathsProperties= new JTextArea();
			jScrollPanePathProperties= new JScrollPane(jTextAreaPathsProperties);
			jScrollPanePathProperties.setBounds(new Rectangle(15,20,500,250));
			jScrollPanePathProperties.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jScrollPanePathProperties.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		}
		return jScrollPanePathProperties;
	}

	private JButton getJButtonSavePatterns(){
		if(jButtonSavePatterns==null){
			jButtonSavePatterns= new JButton();
			jButtonSavePatterns.setBounds(new Rectangle(300,600,200,24));
			jButtonSavePatterns.setText("Save Patterns To Automate");
			jButtonSavePatterns.addActionListener(new java.awt.event.ActionListener(){
				public void actionPerformed(java.awt.event.ActionEvent e){
				try{
					numbAction=0;
					BufferedWriter bw = new BufferedWriter(new FileWriter("resultReasoner0.txt"));
					PrintWriter writer = new PrintWriter(bw);
					writeGeneralConditions(DataStructure.getInstance().getSplitSequencesDataStructure().getAllRawSequences(),jComboBoxSelectSequence.getSelectedIndex(),DataStructure.getInstance().getFrequentSequencesDataStructure().getAllFoundSequences(), writer);
					for(int i=0; i<FinalfailingPaths.size();i++){
					FinalfailingPaths.get(i).getRight().get(FinalfailingPaths.get(i).getRight().size()-1).setRep(0);
					writePattern(FinalfailingPaths.get(i).getRight().get(FinalfailingPaths.get(i).getRight().size()-1), writer);
					writeLastPattern(FinalfailingPaths.get(i), writer, numbAction,i);
					}
					writer.close();
				}
				catch(Exception error){	
					System.out.println("Error Message: " + error.getMessage()+ "Thread at: "+Thread.currentThread().getStackTrace()[2].getLineNumber());
				}
				}
			});
		}
		return jButtonSavePatterns;
	}
	

	private JTable getJTableDirectedGraph(){
		if(jTableDirectedGraph==null){
			jTableDirectedGraph= new JTable();
			jTableDirectedGraph.setLayout(null);
			jScrollPaneTable= new JScrollPane(jTableDirectedGraph);
			jScrollPaneTable.setBounds(new Rectangle(30,50,50,175));
			jScrollPaneTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jScrollPaneTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
						
		}
		return jTableDirectedGraph;
	}
	private JInternalFrame getJInternalFrameTable(){
		if (jInternalFrameTable == null) {
			jInternalFrameTable = new JInternalFrame();
			jInternalFrameTable.setBounds(new Rectangle(14, 65, 500, 200));
			jInternalFrameTable.setContentPane(getJContentPaneTable());
		}
		return jInternalFrameTable;
	}
	private JPanel getJContentPaneTable() {
		if (jContentPaneTable == null) {
			jContentPaneTable = new JPanel();
			jContentPaneTable.setLayout(new BorderLayout());
		}
		return jContentPaneTable;
	}

	
	//1.First of all, calculate how many times each even can occur in each sequence
	public  static void checkRepMaxDevices(ArrayList<fullSequences> allsequences,int index,ArrayList<simpleSequence> frequentSets){
		ArrayList<Integer>sequences=new ArrayList();
		for (int i = 0; i < frequentSets.get(index).getInstances().size(); i++){
			int seq=frequentSets.get(index).getInstances().get(i);
			sequences.add(seq);
		}
		for (int i = 0; i < frequentSets.get(index).getShortExtraInstances().size(); i++){
			int seq=frequentSets.get(index).getShortExtraInstances().get(i);
			sequences.add(seq);
		}
		checkRepMaxDevices=DataStructure.getInstance().getTreeDataStructure().getDevicesInfor().findEventRep(allsequences,sequences);
		Average=DataStructure.getInstance().getTreeDataStructure().getDevicesInfor().findtimeEventAverage(allsequences, sequences);
	}
	

	//2. Create the structure in which all the information will be saved and manipulated
	
	protected JTable createStructure (simplePattern simplePattern, int minimumFrequency, ArrayList<String>listSelectedDevices){
		
		//clear data from every variable
		failingPaths.clear();
		currentPath.clear();
		currentPath2.clear();
		FinalfailingPaths.clear();
		minduration=0;
		
		//Create a HashMap to save the id and node of each Topology Node
		LinkedHashMap<String, Node> Nodes= new LinkedHashMap<String,Node>();
		
		//Create ArrayList to save all the nodes that have not next ones these will be the roots of the graphs
		ArrayList<Node>root=new ArrayList<Node>();
		
		//Structure to save the possible paths
		ArrayList<Integer>Paths=new ArrayList<Integer>();
		
		
		// Create nodes and Set each parameter to each node/event
		
		for (int i = 0; i < simplePattern.getTopologyNodes().size(); i++) {
			String name=findActionName(simplePattern.getTopologyNodes().get(i).getNode());
			String id=simplePattern.getTopologyNodes().get(i).getNode();
			Nodes.put(id, new Node(id));
				if(checkRepMaxDevices.containsKey(name)==true){
					int repMax=(int)checkRepMaxDevices.get(name).doubleValue();
					Nodes.get(id).setMaxRep(1);
					Nodes.get(id).setType("simple");
				}
				else if((checkRepMaxDevices.containsKey(name)==false)&&(name.compareTo("start")!=0)&&(name.compareTo("end")!=0)){
					//System.out.println(name+"It is a cluster");
					int find=findNode(id, simplePattern);
					int cluster_rep=0;
					int a=0;
					int cluster_rep_max=0;
					for(int j=0;j<simplePattern.getTopologyNodes().get(find).getComponentsNode().size()-1;j++){
						String Event=decapsulationCluster(simplePattern.getTopologyNodes().get(find).getComponentsNode().get(j));
						cluster_rep_max= (int)checkRepMaxDevices.get(Event).doubleValue();
						cluster_rep+=cluster_rep_max;
						String id_clus=simplePattern.getTopologyNodes().get(find).getComponentsNode().get(j);
						a=a+1;
						Nodes.get(id).addComponent(id_clus);
					}
					
					//int repMax=(int)Math.round(cluster_rep/a);
					Nodes.get(id).setMaxRep(cluster_rep);
					Nodes.get(id).setType("cluster");
				}

		}
		Nodes.put("start", new Node("start"));
		Nodes.put("end", new Node("end"));
		
		
		// Create relations
		for (int i =0;i<simplePattern.getTopologyNodes().size(); i++) {
				for (int j = 0; j < simplePattern.getTopologyNodes().get(i).getPreviousActions().size(); j++) {
						if (simplePattern.getTopologyNodes().get(i).getPreviousActionsFrequency().get(j) > minimumFrequency){
							if(simplePattern.getTopologyNodes().get(i).getPreviousActions().get(j).compareTo("start")!=0){
								//when the time relation is well defined
								if(simplePattern.getTopologyNodes().get(i).getTimeRelations().get(j).size()>0){
									for(int k=0;k<simplePattern.getTopologyNodes().get(i).getTimeRelations().get(j).size();k++){
										Double time= ((double)Math.round(simplePattern.getTopologyNodes().get(i).getTimeRelations().get(j).get(k).getSimpleTimeRelation()));
										Link l=new Link();
										l.previousNode=Nodes.get(simplePattern.getTopologyNodes().get(i).getPreviousActions().get(j));
										l.timeRelation=time;
										l.frequency=simplePattern.getTopologyNodes().get(i).getPreviousActionsFrequency().get(j);
										Nodes.get(simplePattern.getTopologyNodes().get(i).getNode()).addEdge(l);
										}
									}
								else{
									//when the time relations is not well defined
									Double time=(double)-2;
									Link l=new Link();
									l.previousNode=Nodes.get(simplePattern.getTopologyNodes().get(i).getPreviousActions().get(j));
									l.timeRelation=time;
									l.frequency=simplePattern.getTopologyNodes().get(i).getPreviousActionsFrequency().get(j);
									Nodes.get(simplePattern.getTopologyNodes().get(i).getNode()).addEdge(l);

									}
							}
							if(simplePattern.getTopologyNodes().get(i).getPreviousActions().get(j).compareTo("start")==0){
								Link l= new Link();
								l.frequency=simplePattern.getTopologyNodes().get(i).getPreviousActionsFrequency().get(j);
								l.timeRelation=-1;
								l.previousNode=Nodes.get("start");
								Nodes.get(simplePattern.getTopologyNodes().get(i).getNode()).addEdge(l);
								
									}
								}
							}
		}
		Nodes=createConditions (Nodes, simplePattern,minimumFrequency);
		root=checkSelectedDevices(root, Nodes, simplePattern);
		Paths=createRoots(root,simplePattern);
		filterPaths(Paths);
		jTableDirectedGraph=initTable(Nodes);
		
		return jTableDirectedGraph;
												
	
	}

//2.5. Write all the connections conditions in the new structure links
	
	public  LinkedHashMap<String, Node> createConditions(LinkedHashMap<String, Node> Nodes, simplePattern simplePattern,int minimumFrequency){
		for(int i=0; i<simplePattern.getTopologyNodes().size(); i++){
			for(int j =0; j<simplePattern.getTopologyNodes().get(i).getNextActions().size();j++){
				if(simplePattern.getTopologyNodes().get(i).getNextActionsFrequency().get(j)>minimumFrequency){
					if(simplePattern.getTopologyNodes().get(i).getNextActions().get(j).compareTo("end")!=0){
				int find=findEdgeCondition(Nodes.get(simplePattern.getTopologyNodes().get(i).getNextActions().get(j)), Nodes.get(simplePattern.getTopologyNodes().get(i).getNode()));
				Link l=Nodes.get(simplePattern.getTopologyNodes().get(i).getNextActions().get(j)).getEdge().get(find);
				if(simplePattern.getTopologyNodes().get(i).getDisjunctionConditions().size()!=0){
					ArrayList<ArrayList<simpleCompleteCondition>> disjunctionConditions =simplePattern.getTopologyNodes().get(i).getDisjunctionConditions();
					for(int z=0;z<disjunctionConditions.size();z++){
						for(int s=0;s<disjunctionConditions.get(z).size();s++){
						if(disjunctionConditions.get(z).get(s).consequent.compareTo(simplePattern.getTopologyNodes().get(i).getNextActions().get(j))==0){
							Nodes.get(simplePattern.getTopologyNodes().get(i).getNextActions().get(j)).getEdge().get(find).setDisjunctionConditions(disjunctionConditions.get(z));
						}
						}
						}
					}
				}
			}
		}
		}
		return Nodes;
		
	}
	//3. Create the table with all the possible connections(depending in the granularity of the system)
	private JTable initTable(LinkedHashMap<String, Node> Nodes){
		jTableDirectedGraph= new JTable();
		DefaultTableModel dtm=new DefaultTableModel(0,0);
		String header[]= new String[]{"Node name", "Previous Act", "Time Relation", "Frequency", "MaxRep"};
		dtm.setColumnIdentifiers(header);
		jTableDirectedGraph.setModel(dtm);
		String name,previous,time,freq, rep;
		 name=previous=time=freq=rep="";
		Iterator it=Nodes.keySet().iterator();
		while(it.hasNext()){
			String id= (String) it.next();
			for(int i=0; i<Nodes.get(id).getEdge().size();i++){
				name= findActionName(Nodes.get(id).getId());
				previous=Nodes.get(id).getEdge().get(i).getPreviousNode().getId();
				if(previous.compareTo("start")!=0){
				 previous=findActionName(previous);
				}
				
				time= String.valueOf(Nodes.get(id).getEdge().get(i).getTimeRelation());
				freq=String.valueOf(Nodes.get(id).getEdge().get(i).getFrequency());
				rep=String.valueOf(Nodes.get(id).getMaxRep());
				dtm.addRow(new Object[]{name,previous,time,freq,rep});
			}
		}
		jTableDirectedGraph.setModel(dtm);
		return jTableDirectedGraph;	
	}

	//4. Check which actions have been selected by the user and define as the root of the trees created
	public ArrayList<Node> checkSelectedDevices(ArrayList<Node>root,LinkedHashMap<String, Node> Nodes,simplePattern simplePattern){
		for(int i=0;i<simplePattern.getTopologyNodes().size();i++){
			String name=findActionName(simplePattern.getTopologyNodes().get(i).getNode());
			if((listSelectedDevices.contains(name)==true)&&(simplePattern.getTopologyNodes().get(i).getTypeNode().compareTo("simple")==0)){
				root.add(Nodes.get(simplePattern.getTopologyNodes().get(i).getNode()).getNode());
			}
			else if((simplePattern.getTopologyNodes().get(i).getTypeNode().compareTo("cluster")==0)&&(simplePattern.getTopologyNodes().get(i).getComponentsNode().size()>0)){
				for(int k=0; k<simplePattern.getTopologyNodes().get(i).getComponentsNode().size();k++){
					String Event=decapsulationCluster(simplePattern.getTopologyNodes().get(i).getComponentsNode().get(k));
					if(listSelectedDevices.contains(Event)==true){
						root.add(Nodes.get(simplePattern.getTopologyNodes().get(i).getNode()));
						
								}
							}
						}
					}
		
		return root;
	}
				
	//5. Define minimum duration for each path of the tree depending in the root of the tree
	public double SetMinimumDuration(String id){
		if(Average.containsKey(id)==true){
			double time=Average.get(id).doubleValue();
			return time;
		}
		else {
			System.out.println("Time not well defined");
			return -1;
		}
			}
	
	//6.Create the trees 
	public ArrayList<Integer> createRoots (ArrayList<Node>root,simplePattern simplePattern){
		
		ArrayList<Integer>Paths=new ArrayList<Integer>();
		ArrayList<ImmutableTriple<Double,Integer,ArrayList<Node>>> failingPathsClone=new ArrayList<ImmutableTriple<Double,Integer,ArrayList<Node>>>();
	
		
		for(int j=0; j<root.size();j++){
			boolean result;
			Node node=root.get(j);
			double dur=0;
			int freq=0;
			int a=0;
			int start=0;
			int end=0;
			String id=listSelectedDevices.get(j);
			String []names=id.split(" ");
			String device=names[0];
			String action=names[1];
			if(action.compareTo("on")==0){
				action="off";
			}
			else{
				action="OFF";
			}
			String name=device+" "+action;
			minduration=SetMinimumDuration(id);
			//System.out.println(minduration);
			failingPathsClone=(ArrayList<ImmutableTriple<Double, Integer, ArrayList<Node>>>) failingPaths.clone();
			start=failingPathsClone.size();
			//Analyze all the possible paths
			try {
			result=checkduration(node, dur,freq,name);
			}
			catch(StackOverflowError e){
			
			}
		
			//Check which paths have the highest Total Frequency
			end=failingPaths.size();
			//Integer position=MaxFrequencyPath(start,end);
			//Paths.add(position);
			Integer pos=MinimumEventPath(start, end);
			Paths.add(pos);
		}
		return Paths;
	}
	
	//7. Check every possible path taking into account the relation between the nodes, the minimum duration of the path and the total frequency of the path
	public boolean checkduration(Node node, double dur, int freq,String name) throws StackOverflowError{
		boolean outcome=true;
		recursivity = recursivity+1;
		currentPath.add(node);
		node.setRep(node.getRep()+1);
		if((dur>minduration)&&(node.getRep()<=node.getMaxRep())&&(findActionName(node.getId()).compareTo(name)!=0)){
			currentPath2=(ArrayList<Node>) currentPath.clone();
			failingPaths.add(new ImmutableTriple<Double,Integer, ArrayList<Node>> (dur,freq,currentPath2));	
			currentPath.remove(node);
			return false;
		}
		 if ((node.getRep()>node.getMaxRep())||(findActionName(node.getId()).compareTo(name)==0)){
			return false;
		}
		 
		
		
		for(int i=0;i<node.getEdge().size();i++){
			if(!checkduration(node.getEdge().get(i).previousNode,dur+node.getEdge().get(i).timeRelation, freq+node.getEdge().get(i).frequency,name)){
				outcome=false;	
			}

		}
		currentPath.remove(node);
		node.setRep(node.getRep()-1);
		return outcome;

	 }
	
	//8.1 Calculate the position in which the maximum frequency of each root is saved 
	public Integer MaxFrequencyPath(int start, int end){
		int max=0;
		int pos=0;
		int value_int;
	for(int i=start; i<end;i++){
		value_int = failingPaths.get(i).getMiddle().intValue();
		if(value_int>max){
			max=failingPaths.get(i).getMiddle();
			pos=i;
			
		}
	}
	return pos;
	}
	//8.2 Calculate the minimum events related to the sequence in the path and closest to the duration
	public Integer MinimumEventPath(int start, int end){
		int pos=0;
		int a=0;
		int result=end-start;
		for(int i=start; i<end; i++){
			//if(failingPaths.get(i).getRight().get(failingPaths.get(i).getRight().size()-1).getId().compareTo("0_0")!=0){
				if(a==0){
				 a=i;
				}
				if(result==1){
					pos=i;
				}
				else{
				int sizes=failingPaths.get(a).getRight().size();
				double dur=failingPaths.get(a).getLeft().doubleValue();
				double dur_now=failingPaths.get(i).getLeft().doubleValue();
				int size_now=failingPaths.get(i).getRight().size();
				//if((dur_now<dur)&&(size_now<sizes)){
				if((size_now<=sizes)){
				dur=dur_now;
				sizes=size_now;
				pos=i;
			 }
		 }
			}
		
		return pos;
	}
	
	// 9.1 Save those paths that have the highest total frequency of each root-> and only those will be displayed in the GUI
	public void filterPaths(ArrayList<Integer>Paths){
		if((Paths.size()>0)==true){
		for(int i=0;i<Paths.size();i++){
			int val=Paths.get(i);
			FinalfailingPaths.add(failingPaths.get(val));
			}
		}
		else{
			System.out.println("no paths avaiable");
		}
	}
	
	//10. Create the graph of FinalPaths
	protected JGraph visualizePaths(){
		GraphModel model= new DefaultGraphModel();
		GraphLayoutCache view = new GraphLayoutCache(model, new DefaultCellViewFactory());
		JGraph graph = new JGraph(model, view);
		tempNodeCells.clear();
		tempRelationCells.clear();
		//Create all the cells needed to create all the paths
		for(int i=0; i<FinalfailingPaths.size();i++){
			if(FinalfailingPaths.get(i).getRight().size()>1){
			for(int j=0; j<FinalfailingPaths.get(i).getRight().size();j++){
				tempNodeCells.add(initCell(findActionName(FinalfailingPaths.get(i).getRight().get(j).getId()),20,20));
			}
			tempNodeCells.add(initCell("end",20,20));
		}
		}
		//Create all the relations to connect the cells
		for(int i=0; i<tempNodeCells.size();i++){
			int k=i+1;
			if(tempNodeCells.get(i).getUserObject().toString().compareTo("end")!=0){
			tempRelationCells.add(initRelation(tempNodeCells.get(i),tempNodeCells.get(k)));
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
			layout.run(facade); 
			Map nested = facade.createNestedMap(true, true); 
			graph.getGraphLayoutCache().edit(nested);
		
		
		graph.addGraphSelectionListener(new org.jgraph.event.GraphSelectionListener() {
			public void valueChanged(GraphSelectionEvent e) {
				jTextAreaPathsProperties.setText("");
				jTextAreaPathsProperties.setLineWrap(true);
				jTextAreaPathsProperties.setWrapStyleWord(true);
				jTextAreaPathsProperties.append(newline);
				if(FinalfailingPaths.size()>0){
				for(int i=0; i<FinalfailingPaths.size();i++){
					jTextAreaPathsProperties.append(listSelectedDevices.get(i)+" Automation");
					jTextAreaPathsProperties.append(newline);
					jTextAreaPathsProperties.append(newline);
					jTextAreaPathsProperties.append(" Duration of the Path:" +FinalfailingPaths.get(i).getLeft().doubleValue());
					jTextAreaPathsProperties.append(newline);
					jTextAreaPathsProperties.append(" Total-Frequency of the Path: "+ FinalfailingPaths.get(i).getMiddle().intValue());
					jTextAreaPathsProperties.append(newline);
					jTextAreaPathsProperties.append("--------------------------------------------------------------");
					jTextAreaPathsProperties.append(newline);
				}
				}
				else{
					jTextAreaPathsProperties.append("No Paths were discovered");
				}
			}
		});
		return graph;
		
	}
	//11.Save the created patterns in an external file to import to the Bridge(all the knowledge of the program)
	
	public void writeGeneralConditions( ArrayList<fullSequences> allsequences,int index,ArrayList<simpleSequence> frequentSets, PrintWriter writer){

			String dayOfWeek= new String();
			String timeOfDay=new String();
				writer.println();
				writer.println("Action Map 0");
				writer.println();
				writer.println("(General Conditions)");
				writer.println();
				ArrayList<Integer>sequencess=new ArrayList();
				
				for(int j=0;j<frequentSets.get(index).getInstances().size();j++){
					int seq=frequentSets.get(index).getInstances().get(j);
					sequencess.add(seq);
				}
				for(int j=0;j<frequentSets.get(index).getShortExtraInstances().size();j++){
					int seq=frequentSets.get(index).getShortExtraInstances().get(j);
					sequencess.add(seq);
				}
				dayOfWeek=DataStructure.getInstance().getConditionsDataStructure().getGeneralConditions().needDayOfWeek(allsequences,sequencess);
				timeOfDay=DataStructure.getInstance().getConditionsDataStructure().getGeneralConditions().needAverageTime(allsequences,sequencess);
				String[] TimeOfDay=timeOfDay.split("T");
				writer.println(" context (DayOfWeek (=,"+dayOfWeek+"))& context (TimeOfDay(>,"+TimeOfDay[0]+")) & context (TimeOfDay(<,"+TimeOfDay[1]+"))");
				writer.println();
			}
	//12. Write the patterns to introduce to the Reasoner, recursive algorithm that goes through every node in the FinalfailingPaths array

	public boolean writePattern (Node node, PrintWriter writer){
		boolean outcome=true;
		if((node.getId().compareTo("start")==0)||(node.getRep()>=1)){
			return false;
		}
		else{
			node.setRep(0);
			//System.out.println(node.getId());
			try{
			writeTopology(node, writer);
			node.setRep(node.getRep()+1);
			}
			catch(StackOverflowError e){

			}
		}
		for(int i=0; i<node.getEdge().size(); i++){
			if(!writePattern(node.getEdge().get(i).previousNode, writer)){
			outcome=false;
			}
		}
	return outcome;
	
	}
	//13. Write a pattern with each node, taking into account all the possible previous actions or events
	public void writeTopology(Node node, PrintWriter writer){
		
		int z=0;
		
		for(int i=0; i<node.getEdge().size();i++){
			writer.println("(Action Pattern " + numbAction+")");
			writer.print("ON occurs ");
			writeEventLFPUBS(node.getEdge().get(i), writer);
			writer.print("IF context(");
			if(node.getEdge().get(i).getDisjunctionConditions().size()!=0){
				for(int j=0; j<node.getEdge().get(i).getDisjunctionConditions().size();j++){
				writeConditionsLFPUBS(node.getEdge().get(i).getDisjunctionConditions(), writer);
				}
			}
			writer.println(")");
			writer.print("THEN do ");
			if(node.getId().compareTo("start")==0){
				writer.print("(--,end,t) when --");
			}
			else{
				double timeRelation=node.getEdge().get(i).getTimeRelation();
				writeActionLFPUBS(node.getNode(), timeRelation, writer);
			}
			writer.println();
			numbAction++;
			}
		}
			

	// Extra methods to transform the data//
	
	//1. FindNode
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
	
	//2. FindActionName
	private String findActionName(String name) {
		String temp = "", last = "";

		temp = name.substring(0, name.indexOf("_"));

		if (temp.compareTo("cluster") != 0) {
			last = simpleEvent.getEventLabel().get(Integer.parseInt(temp)).getDevice() + " "+ simpleEvent.getEventLabel().get(Integer.parseInt(temp)).getAction();
			return last;
		}

		return name;
	}
	
	//3.DecapsulationCluster
	public static String decapsulationCluster(String compositeActionsString){
		String actionsString = new String ();

		int pos = compositeActionsString.indexOf("_",0);
		String tempAction = compositeActionsString.substring(0, pos);
		
		String device = simpleEvent.getEventLabel().get(Integer.parseInt(tempAction)).getDevice();
		String action = simpleEvent.getEventLabel().get(Integer.parseInt(tempAction)).getAction();
		actionsString= device+" "+action;
						
		return actionsString;		
	}
	
	//4. findAverageTimeDevice
	public double findAverageTimeDevice (String id, simplePattern simplePattern){
		if(Average.containsKey(id)==true){
			double time=Average.get(id).doubleValue();
			return time;
		}
		else if((Average.containsKey(id)==false)&&(id!="start")&&(id!="end")){
		int find=findNode(id, simplePattern);
		for(int i=0; i<simplePattern.getTopologyNodes().get(find).getComponentsNode().size();i++){
			String Event=decapsulationCluster(simplePattern.getTopologyNodes().get(find).getComponentsNode().get(i));
			if(id.compareTo(Event)==0){
				double time=Average.get(Event).doubleValue();
				return time;
						}
			
					}
				}
		return -1;
			}
	//5. Create cells
	public static DefaultGraphCell initCell (String name, int x1, int y1){
		DefaultGraphCell cell = new DefaultGraphCell(new String(name));
		GraphConstants.setBounds(cell.getAttributes(), new Rectangle2D.Double(x1,y1,80,30));
		GraphConstants.setBorderColor(cell.getAttributes(), Color.getHSBColor(0.63f,0.6464f,1.0f));
		GraphConstants.setBackground(cell.getAttributes(), Color.getHSBColor(0.644f, 0.3636f, 1.0f)); //to calculate http://home.comcast.net/~ed-abramson/14ColorTest/HSB-and-RGB-Colors.html
		GraphConstants.setOpaque(cell.getAttributes(), true);
		GraphConstants.setFont(cell.getAttributes(), GraphConstants.DEFAULTFONT.deriveFont(6));
		DefaultPort port = new DefaultPort();
		cell.add(port);
		
		return cell;
	}
	//6. Create Relations
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
	//7. Write Topology LLFPUBS
	/*public void writeTopologyLFPUBS(ImmutableTriple<Double,Integer,ArrayList<Node>> FinalfailingPaths, PrintWriter writer, int number){
		int numActionPattern = 0;
		
		for(int i=0; i<FinalfailingPaths.getRight().size();i++){		
			int a=i+1;
			if(a!=FinalfailingPaths.getRight().size()){
			Node next=FinalfailingPaths.getRight().get(a).getNode();
			int find=findEdge( FinalfailingPaths.getRight().get(i).getEdge(),next.getId());
			writer.println("(Action Pattern " + i + ")");
			writer.print("ON occurs ");
			//writeEventLFPUBS(FinalfailingPaths.getRight().get(i).getEdge().get(find),FinalfailingPaths.getRight().get(a).getNode(), writer);
			writer.print("IF context (");
			if(FinalfailingPaths.getRight().get(i).getEdge().get(find).getDisjunctionConditions().size()!=0){
			writeConditionsLFPUBS(FinalfailingPaths.getRight().get(i).getEdge().get(find).getDisjunctionConditions().get(0), writer);
			}
			writer.println(")");
			writer.print("THEN do ");
			if (FinalfailingPaths.getRight().get(i).getId().compareTo("end")==0){
				writer.print("(--,end,t) when --");
			}
			else{
				double timeRelation;
				timeRelation =FinalfailingPaths.getRight().get(i).getEdge().get(find).getTimeRelation();
				writeActionLFPUBS (FinalfailingPaths.getRight().get(i).getNode(),timeRelation,writer);
				}
			writer.println();
			
			}
			
		}
		writer.println();
		//writeLastPattern(FinalfailingPaths,writer, number);
	}
	*/
	//7. The last Patterns is the one needed to automate a device, as no direct connection is between those two events, a pattern is created
	public void writeLastPattern(ImmutableTriple<Double,Integer,ArrayList<Node>> FinalfailingPaths,PrintWriter writer, int number, int count){
		writer.println("(Action Pattern " +number+")");
		writer.print("ON occurs ");
		if(FinalfailingPaths.getRight().get(FinalfailingPaths.getRight().size()-1).getType().compareTo("cluster")!=0){
			writer.print("(simple,("+translateCompositeActionsToStringLFPUBS(FinalfailingPaths.getRight().get(FinalfailingPaths.getRight().size()-1).getId())+" ), t0) Frequency: 0");
		}
		else{
			writer.print("(unordered,(");
			for(int i=0; i<FinalfailingPaths.getRight().get(FinalfailingPaths.getRight().size()-1).getComponents().size()-1;i++){
				writer.print("(" +translateCompositeActionsToStringLFPUBS(FinalfailingPaths.getRight().get(FinalfailingPaths.getRight().size()-1).getComponents().get(i))+ ")&");
			}
			writer.print("(" + translateCompositeActionsToStringLFPUBS(FinalfailingPaths.getRight().get(FinalfailingPaths.getRight().size()-1).getComponents().get(FinalfailingPaths.getRight().get(FinalfailingPaths.getRight().size()-1).getComponents().size()-1))+ "),t0) Frequency: 0");
		}
		writer.println();
		writer.print("IF context (");
		//There will be no conditions, because no direct relation has been detected
		writer.println(")");
		writer.print("THEN do ");
		if(number!=listSelectedDevices.size()){
			String id=listSelectedDevices.get(count);
			double mindurations=SetMinimumDuration(id);
			double timeRelation=FinalfailingPaths.getLeft().doubleValue()-mindurations;
			writeActionLFPUBS(FinalfailingPaths.getRight().get(0).getNode(), timeRelation, writer);
			writer.println();
		}

	}
	//8. FindEdge LFPUBS
	public int findEdge( ArrayList<Link> edge, String next){
		for(int i=0; i< edge.size();i++){
			if(edge.get(i).getPreviousNode().getId().compareTo(next)==0){
				return i;
			}
		}
		return 0;
	}
	//9. Write Events LFPUBS
	public void writeEventLFPUBS( Link edge, PrintWriter writer){
		Node previousNode= edge.getPreviousNode();
		if(translateCompositeActionsToStringLFPUBS(previousNode.getId()).compareTo("start")==0){
			writer.print("start,--,t0) Frequency: "+edge.getFrequency());
			
		}
		else{
		if(previousNode.getType().compareTo("cluster")!=0){
			writer.print("(simple,("+translateCompositeActionsToStringLFPUBS(previousNode.getId())+"), t0) Frequency: "+edge.getFrequency());
		}
		else{
			writer.print("(unordered,(");
			for(int i=0; i<edge.getPreviousNode().getComponents().size()-1;i++){
				writer.print("(" +translateCompositeActionsToStringLFPUBS(edge.getPreviousNode().getComponents().get(i))+")&");
			}
			writer.print("(" + translateCompositeActionsToStringLFPUBS(previousNode.getComponents().get(previousNode.getComponents().size()-1))+"),t0) Frequency:" +edge.getFrequency());
		}
		}
		writer.println();
		
	}
	//10. Write Conditions LFPUBS
	public void writeConditionsLFPUBS(ArrayList<simpleCompleteCondition> conditions, PrintWriter writer){
		
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
	//11.write Numeric Conditions LFPUBS
	public static void writeNumericConditionLLFPUBS (simpleBasicConditionNumeric basicCondition, PrintWriter writer){
		
		writer.print(basicCondition.name + "(" + basicCondition.symbol + "," + basicCondition.value + ")");

	}
	//12.Write NominalConditions LFPUBS
	public static void writeNominalConditionLLFPUBS (simpleBasicConditionNominal basicCondition, PrintWriter writer){
		
		writer.print(basicCondition.name + "(" + basicCondition.symbol + "," + basicCondition.value + ")");
	}	
	//13. Write Action LFPUBS
	public void writeActionLFPUBS(Node actualNode,double timeRelation, PrintWriter writer){
		if(actualNode.getType().compareTo("cluster")!=0){
			writer.print("(simple,("+translateCompositeActionsToStringLFPUBS(actualNode.getId())+"), t) when: ");
		}
		else{
			writer.print("(unordered,(");
			for(int i=0; i<actualNode.getComponents().size()-1;i++){
				writer.print("(" +translateCompositeActionsToStringLFPUBS(actualNode.getComponents().get(i))+")&");
			}
			writer.print("(" + translateCompositeActionsToStringLFPUBS(actualNode.getComponents().get(actualNode.getComponents().size()-1))+"), t) when: ");
		}
		if(timeRelation==-1){
			writer.print("--");
		}
		else if(timeRelation==-2){
			writer.print("t is after t0");
		}
		else{
			writer.print("t = t0 + "+timeRelation+" s. ");
		}
		writer.println();
	}
	//14. Split node Name to LFPUBS pattern structure
	public String translateCompositeActionsToStringLFPUBS(String compositeActionsString){
		String actionsString = new String ();
		if((compositeActionsString.compareTo("start")==0)||(compositeActionsString.compareTo("end")==0)){
			return compositeActionsString;
		}
		
		int pos = compositeActionsString.indexOf("_",0);
		String tempAction = compositeActionsString.substring(0, pos);
		
		if (tempAction.compareTo("cluster") != 0){
			String device = simpleEvent.getEventLabel().get(Integer.parseInt(tempAction)).getDevice();
			String action = simpleEvent.getEventLabel().get(Integer.parseInt(tempAction)).getAction();
			actionsString = action + "," + device + " (" + compositeActionsString.substring(pos + 1, compositeActionsString.length()) + ")";

		}
		else{
			actionsString = compositeActionsString;
		}
				
		
		
	return actionsString;
	}
	//15. Decapsule the clusters
	public static String decapsulationClusterLFPUBS(String compositeActionsString){
		String actionsString = new String ();

		int pos = compositeActionsString.indexOf("_",0);
		String tempAction = compositeActionsString.substring(0, pos);
		
		String device = simpleEvent.getEventLabel().get(Integer.parseInt(tempAction)).getDevice();
		String action = simpleEvent.getEventLabel().get(Integer.parseInt(tempAction)).getAction();
		actionsString = action + "," + device + " (" + compositeActionsString.substring(pos + 1, compositeActionsString.length()) + ")";
						
		return actionsString;		
	}
	//16. Find Position of each node
	
	private String findPos(String compositeActionsString) {
		if ((compositeActionsString.compareTo("start")== 0) || (compositeActionsString.compareTo("end")== 0)){
			return compositeActionsString;
		}
		
		int pos = compositeActionsString.indexOf("_",0);
		String tempAction = compositeActionsString.substring(0, pos);
		
		String position=compositeActionsString.substring(pos + 1, compositeActionsString.length()) + ")";
						
		return position;		
	}
	//17. Find the edge/link that satisfies actual nose and next node links properties
	public int findEdgeCondition(Node nextNode, Node actualNode){
	for(int i=0; i<nextNode.getEdge().size();i++){
		if(nextNode.getEdge().get(i).getPreviousNode().getId().compareTo(actualNode.getId())==0){
			return i;
		}
	}
	return 0;
	}
	
	
	
}
	
