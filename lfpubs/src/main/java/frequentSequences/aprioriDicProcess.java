package frequentSequences;

import java.io.*;
import java.util.*;

//-------------------------------------------------------------
//  Class Name : dic
//  Purpose    : main program class
//-------------------------------------------------------------
public class aprioriDicProcess {

  public static void main(String[] args) throws IOException {

  }
  
  public static ArrayList<simpleSequence> findFrequentSequences (ArrayList<Integer>[] instances, int numActions, int numInstances, int minSup, double lenSimilarity){
		ArrayList<simpleSequence> maximalSequences;
		ArrayList<simpleSequence> modifiedMaximalSequences = new ArrayList<simpleSequence>();
		
		aprioriDicProcess1 process1=new aprioriDicProcess1(instances,numActions,numInstances,minSup,lenSimilarity);
		maximalSequences = process1.getMaximalSequences();
		
		for (int i = 0; i < maximalSequences.size(); i++){
			modifiedMaximalSequences.add(maximalSequences.get(i));
			//sequence
			ArrayList<Integer> tempSequence = new ArrayList<Integer>();
			for (int j = 0; j < maximalSequences.get(i).getSequence().size(); j++){
				tempSequence.add(maximalSequences.get(i).getSequence().get(j) - 1); //because the enumeration of original actions and frequent sequences' actions is +1
			}
			modifiedMaximalSequences.get(i).setSequence(tempSequence);
			//extra actions
			ArrayList<Integer> tempExtraActions = new ArrayList<Integer>();
			for (int j = 0; j < maximalSequences.get(i).getLongExtraActions().size(); j++){
				tempExtraActions.add(maximalSequences.get(i).getLongExtraActions().get(j) - 1);				
			}
			modifiedMaximalSequences.get(i).setLongExtraActions(tempExtraActions);
		}
		
		return modifiedMaximalSequences;
	}
  
}


//-------------------------------------------------------------
//  Class Name : dicProcess
//  Purpose    : main processing class
//-------------------------------------------------------------
class aprioriDicProcess1 {

  private final int DC=1; // four states of tree node
  private final int DS=2;
  private final int SC=3;
  private final int SS=4;
  int N; // total item #
  int M; // total transaction #
  int stepm; // step increment
  int tid; // current line # of transaction
  int k; // current processing k-itemset
  int setnum; // item # in current transaction
  int minsup;
  hashtreenode root;
  String DSset,DCset,SCset,SSset;
  ArrayList<simpleSequence> tempSequences = new ArrayList();
  ArrayList<simpleSequence> sequences = new ArrayList();
  static int tindex = 0;
  int maxlength = 0;
  public static double lengthSimilarity;



//-------------------------------------------------------------
//  Class Name : hashtreenode
//  Purpose    : object of node of hash tree
//-------------------------------------------------------------
  class hashtreenode {

    int state;         //  should be one of (DC,DS,SC,SS)
    String itemset;    //  itemset that this node stores
    int counter;       //  counte the number of occurrence in transactions
    int starting;      //  transaction id when this node starts to be counted
    int startingk;     //  k's value when this node starts to be counted
                       //  k : the number of <stepm>s that have been passed through
    boolean needcounting;  //  if this node should be counted later on
    Hashtable ht;      //  hash table stores this node's son-nodes
    String transactions =""; //set of transactions

    // constructor 1
    public hashtreenode(int state,String itemset,int counter,int starting,int startingk) {
      this.state=state;
      if (itemset==null)
        this.itemset=new String();
      else
        this.itemset=new String(itemset);
      this.counter=counter;
      this.starting=starting;
      this.startingk=startingk;
      needcounting=true;
      ht=new Hashtable();
    }

    
    // constructor 2
    public hashtreenode() {
      this.state=DC;
      this.itemset=new String();
      this.counter=0;
      this.starting=0;
      this.startingk=0;
      needcounting=true;
      ht=new Hashtable();
    }
  }  
  
//-------------------------------------------------------------
//Class Name : tempsequence
//Purpose    : represent temporal sequences
//-------------------------------------------------------------
class tempsequence {

  int length;         //  lenght of the sequence
  int counter;		  // number of instances of that sequence
  int [] sequence;	  // the sequence itself
  String instances;   // the instances of that sequence
  
  // constructor 1
  public tempsequence(int length, int counter, int[] sequence) {
    this.length = length;
    this.counter = counter;
    this.sequence = sequence;
  }
  
//constructor 2
  public tempsequence(int length) {
    this.length = length;
    sequence = new int [length];
  }
    
//constructor 3
  public tempsequence(int length, int counter) {
    this.length = length;
    sequence = new int [length];
    this.counter = counter;
  }
  
//constructor 4
  public tempsequence(tempsequence tsequence){
	  this.counter = tsequence.counter;
	  this.length = tsequence.length;
	  this.sequence = tsequence.sequence;
	  this.instances = tsequence.instances;
  }
  
//constructor 5
  public tempsequence(int length, int counter, String transactions) {
	    this.length = length;
	    sequence = new int [length];
	    this.counter = counter;
	    this.instances = transactions;
	  }

  
}  
 
//-------------------------------------------------------------
//  Method Name: getitemat
//  Purpose    : get an item from an itemset
//             : get the total number of items of transaction file
//  Parameter  : int i : i-th item ; itemset : string itemset
//  Return     : int : the item at i-th in the itemset 
//-------------------------------------------------------------
  public int getitemat(int i,String itemset) {

    String str1=new String(itemset);

    int pos1,pos2;
    pos1=0;
    pos2=0;
    for (int a=1;a<i;a++) {
      pos1=itemset.indexOf(" ",pos1);
      pos1++;
    }    
    pos2=itemset.indexOf(" ",pos1+1);
    if (pos2==-1) 
      pos2=itemset.length();
    str1=itemset.substring(pos1,pos2);

    return(Integer.valueOf(str1.trim()).intValue());

  }


//-------------------------------------------------------------
//  Method Name: itesetsize
//  Purpose    : get item number of an itemset
//  Parameter  : itemset : string itemset
//  Return     : int : the number of item of the itemset 
//-------------------------------------------------------------
  public int itemsetsize(String itemset) {

    StringTokenizer st=new StringTokenizer(itemset);

    return st.countTokens();

  }


//-------------------------------------------------------------
//  Method Name: dashfound
//  Purpose    : check the hashtree to see if there exsits a dashed node
//             : if no dashed circle or dashed squire exsits in hash tree
//             : program will end
//  Parameter  : htn : hash tree root 
//  Return     : boolean : if a dashed node found, return true, otherwise, false
//-------------------------------------------------------------
  boolean dashfound(hashtreenode htn) {
  
    if (htn.state==DS || htn.state==DC)
      return true;
    for (Enumeration e=htn.ht.elements();e.hasMoreElements(); )
      if (dashfound((hashtreenode)e.nextElement()))
        return true;
    return false;
  }

  
//-------------------------------------------------------------
//Method Name: printhashtree
//Purpose    : print the whole hash tree
//Parameter  : htn is a hashtreenode (when other method call this method,it is the root)
//           : transa : special transaction with all items occurr in it.
//           : a : recursive depth
//Return     : 
//-------------------------------------------------------------
public int printhashtreecount(hashtreenode htn,String transa,int a,int index) {
  String state=new String();
 
  switch (htn.state) {
    case DC: 
	break;
    case DS: 
	break;
    case SC: 
	break;
    case SS: 
    	index++;
	break;
  }
  

  if (htn.ht==null)
    return index;
  for (int b=a+1;b<=itemsetsize(transa);b++)
    if (htn.ht.containsKey(Integer.toString(getitemat(b,transa))))
     index = printhashtreecount((hashtreenode)htn.ht.get(Integer.toString(getitemat(b,transa))),transa,b,index);
  
  return index;

}


//-------------------------------------------------------------
//  Method Name: printhashtree
//  Purpose    : print the whole hash tree
//  Parameter  : htn is a hashtreenode (when other method call this method,it is the root)
//             : transa : special transaction with all items occurr in it.
//             : a : recursive depth
//  Return     : 
//-------------------------------------------------------------
  public void printhashtree(hashtreenode htn,String transa,int a) {
    String state=new String();
    switch (htn.state) {
      case DC: 
	state="DC" ; 
	DCset=DCset.concat(htn.itemset);
	DCset=DCset.concat(",");
//	DCset=DCset.concat("[");
//	DCset=DCset.concat(htn.itemset);
//	DCset=DCset.concat("]");
	break;
      case DS: 
	state="DS" ; 
	DSset=DSset.concat(htn.itemset);
	DSset=DSset.concat(",");
//	DSset=DSset.concat("[");
//	DSset=DSset.concat(htn.itemset);
//	DSset=DSset.concat("]");
	break;
      case SC: 
	state="SC" ; 
	SCset=SCset.concat(htn.itemset);
	SCset=SCset.concat(",");
//	SCset=SCset.concat("[");
//	SCset=SCset.concat(htn.itemset);
//	SCset=SCset.concat("]");
	break;
      case SS: 
	state="SS" ; 
	SSset=SSset.concat(htn.itemset);
	SSset=SSset.concat(",");
//	SSset=SSset.concat("[");
//	SSset=SSset.concat(htn.itemset);
//	SSset=SSset.concat("]");
	break;
    }
    /*System.out.print("Itemset:<"+htn.itemset+">");
    System.out.print(" state:<"+state+">");
    System.out.print(" counter:<"+htn.counter+">");
    System.out.print(" starting:<"+htn.starting+">");
    System.out.print(" startingk:<"+htn.startingk+">");
    System.out.print(" needcounting:<"+htn.needcounting+">");
    System.out.println(" transactions:< "+htn.transactions+">");*/

    if (htn.ht==null)
      return;
    for (int b=a+1;b<=itemsetsize(transa);b++)
      if (htn.ht.containsKey(Integer.toString(getitemat(b,transa))))
        printhashtree((hashtreenode)htn.ht.get(Integer.toString(getitemat(b,transa))),transa,b);

  }
  
//-------------------------------------------------------------
//Method Name: sequencelength
//Purpose    : discover the length of a sequence
//Parameter  : the string that represents the sequence
//Return     : length of the sequence 
//-------------------------------------------------------------
  
  public int sequencelength (String str){
	  int length = 0;
	  int found =0,pos =0;
	  
	  while (found != -1){
		  length++;
		  found = str.indexOf(" ",pos);
		  pos = found +1;
	  }
	  
	  
	  return length;
  }
  
//-------------------------------------------------------------
//Method Name: parseitemset
//Purpose    : parse htn.itemset into array
//Parameter  : htn.itemset is a hashtreenode (when other method call this method,it is the root)
//           : length is the length of the string
//Return     : int [] 
//-------------------------------------------------------------
  
  public ArrayList<Integer> parseitemset (String str, int length){
	  ArrayList<Integer> tempstr = new ArrayList<Integer>();
	  int begin = 0, end = 0;
	  int number;

	  if (length > maxlength){
	  	  maxlength = length;
	    }

	  for (int i = 0; i < length-1; i++){
	  	end = str.indexOf(" ",begin);
	  	number = Integer.parseInt(str.substring(begin, end));
	  	tempstr.add(number);
	  	begin = end + 1;
	  }
	  tempstr.add(Integer.parseInt(str.substring(begin, str.length())));
	  return tempstr;
  }
  
//-------------------------------------------------------------
//Method Name: printhashtreetempsequence
//Purpose    : print the whole hash tree into tempsequence
//Parameter  : htn is a hashtreenode (when other method call this method,it is the root)
//           : transa : special transaction with all items occurr in it.
//           : a : recursive depth
//Return     : set of temp sequence
//-------------------------------------------------------------
public void printhashtreetempsequence(hashtreenode htn,String transa,int a, int length) {
  String state=new String();
  int slength = 0;
    
  
  switch (htn.state) {
    case DC: 
	break;
    case DS: 
	break;
    case SC: 
	break;
    case SS:
    	if (tindex != 0){
    		slength = sequencelength (htn.itemset);
    		tempSequences.add(new simpleSequence(slength,(double)htn.counter,parseitemset(htn.itemset,slength),splitStringIntoInt(htn.transactions)));
        	tindex ++;
    	}
    	else tindex++;
    	
	break;
  }
  
  //if (htn.ht==null)
    //return;
  for (int b=a+1;b<=itemsetsize(transa);b++)
    if (htn.ht.containsKey(Integer.toString(getitemat(b,transa))))
      printhashtreetempsequence((hashtreenode)htn.ht.get(Integer.toString(getitemat(b,transa))),transa,b,0);
  
  //return tsequence;

}



//-------------------------------------------------------------
//  Method Name: transatrahashtree
//  Purpose    : recursive transaction traversal through hash tree 
//  Parameter  : htn is a hashtreenode (when other method call this method,it is the root)
//             : transa : transaction
//             : a : recursive depth,only traversal itemset_part after a-th item
//             : e.g. tran="2 3 4 5", a=2, then, only "4 5" need traversal.
//  Return     : 
//-------------------------------------------------------------
  public void transatrahashtree(String transa,hashtreenode htn,int a,int tid) {

    if (htn.needcounting){
        htn.counter++;
        htn.transactions = htn.transactions.concat(Integer.toString(tid-1)); //this is the other change from the original version
        htn.transactions = htn.transactions.concat(" ");
    }
    	
    if (htn.ht==null)
      return;
    else
      for (int b=a+1;b<=itemsetsize(transa);b++)
        if (htn.ht.containsKey(Integer.toString(getitemat(b,transa))))
          transatrahashtree(transa,(hashtreenode)htn.ht.get(Integer.toString(getitemat(b,transa))),b,tid);

  }


//-------------------------------------------------------------
//  Method Name: checkcountedall
//  Purpose    : travese hashtree to chech if an itemset has been counted 
//             : through all transactions, stop counting it.
//  Parameter  : htn is a hashtreenode (when other method call this method,it is the root)
//             : transa : transaction
//             : startfrom : recursive depth
//  Return     : 
//-------------------------------------------------------------
  public void checkcountedall(hashtreenode htn,String transa,int startfrom) { 

    if ( htn.starting==tid && k!=htn.startingk ) {
      if (htn.state==DS) {
        htn.state=SS;
      }
      else if (htn.state==DC) {
        htn.state=SC;
      }
      htn.needcounting=false;
    }

    if (htn.ht==null || htn.ht.isEmpty())
      return;

    for (int c=startfrom+1;c<=N;c++)
      if (htn.ht.containsKey(Integer.toString(getitemat(c,transa))))
        checkcountedall((hashtreenode)htn.ht.get(Integer.toString(getitemat(c,transa))),transa,c);

  }


//-------------------------------------------------------------
//  Method Name: checkcounter
//  Purpose    : ( DC==>DS )
//             : travese hashtree to chech if an itemset node is stated DC and 
//             : it's counter>=minsup, then change its state to DS 
//  Parameter  : htn is a hashtreenode (when other method call this method,it is the root)
//             : transa : transaction
//             : startfrom : recursive depth
//  Return     : 
//-------------------------------------------------------------
  public void checkcounter(hashtreenode htn,String transa,int startfrom) { 

    if (htn.state==DC && ((htn.counter*100) >= (minsup*M)))
      htn.state=DS;

    if ( htn.ht.isEmpty())
      return;

    for (int c=startfrom+1;c<=N;c++)
      if (htn.ht.containsKey(Integer.toString(getitemat(c,transa)))) {
        checkcounter((hashtreenode)htn.ht.get(Integer.toString(getitemat(c,transa))),transa,c);
      }
  }


//-------------------------------------------------------------
//  Method Name: checkhashtree
//  Purpose    : traversal hashtree 
//             : for each of DS node's superset
//             : if all subset of it are SS/SC/DS
//             : generate new hashtreenode, and link it to its father
//             : e.g. tran="2 3 4 5", a=2, then, only "4 5" need traversal.
//  Parameter  : htn is a hashtreenode (when other method call this method,it is the root)
//             : transa : transaction
//             : transa: special transaction that includes all items
//             : startfrom : recursive depth, only check itemset_part after startfrom-th item
//  Return     : 
//-------------------------------------------------------------
  public void checkhashtree(hashtreenode htn,String transa,int startfrom) { 

    String superset=new String();
    String subset=new String();
    StringTokenizer stsuperset,stsubset;
    boolean dcfound;
 
    if ( htn.state==DS ) {
      superset=gensuperset(htn.itemset);
      if (superset!=null) {
        stsuperset=new StringTokenizer(superset,",");
        while (stsuperset.hasMoreTokens()) {
          String superset1=new String(stsuperset.nextToken());
          if (htn.ht.containsKey(Integer.toString(getitemat(itemsetsize(superset1),superset1))))
            continue ;
          subset=gensubset(superset1);
          stsubset=new StringTokenizer(subset,",");
          dcfound=false;
          while (stsubset.hasMoreTokens())
            if (circlefound(root,stsubset.nextToken(),0)) {
              dcfound=true;
              break;
            }
          if (!dcfound) {
            hashtreenode tmphtn=new hashtreenode(DC,superset1,0,tid,k);
            htn.ht.put(Integer.toString(getitemat(itemsetsize(superset1),superset1)),tmphtn);
          }
        }
      }
    }

    if (htn.ht==null || htn.ht.isEmpty())
      return;

    for (int c=startfrom+1;c<=N;c++)
      if (htn.ht.containsKey(Integer.toString(getitemat(c,transa))))
        checkhashtree((hashtreenode)htn.ht.get(Integer.toString(getitemat(c,transa))),transa,c);

  } //end public void checkhashtree(hashtreenode htn,String spetransa,int startfrom)


//-------------------------------------------------------------
//  Method Name: circlefound
//  Purpose    : called by checkhashtree
//  Parameter  : htn is a hashtreenode (when other method call this method,it is the root)
//             : transa : transaction
//             : transa: special transaction that includes all items
//             : startfrom : recursive depth, only check itemset_part after startfrom-th item
//  Return     : 
//-------------------------------------------------------------
  public boolean circlefound(hashtreenode htn,String itemset,int startfrom) {
    
    if (htn.state==DC || htn.state==SC)
      return true;

    for (int c=startfrom+1;c<=itemsetsize(itemset);c++)
      if (htn.ht.containsKey(Integer.toString(getitemat(c,itemset))))
        if (circlefound((hashtreenode)htn.ht.get(Integer.toString(getitemat(c,itemset))),itemset,c))
          return true;

    return false;
  }


//-------------------------------------------------------------
//  Method Name: gensubset
//  Purpose    : generate all subset given an itemset 
//  Parameter  : itemset
//  Return     : a string contains all subset deliminated by ","
//             : e.g. "1 2,1 3,2 3" is subset of "1 2 3"
//-------------------------------------------------------------
  public String gensubset(String itemset) {  

    int len=itemsetsize(itemset);
    int i,j;
    String str1;
    String str2=new String();
    String str3=new String();
    
    if (len==1)
      return null;
    for (i=1;i<=len;i++) {
      StringTokenizer st=new StringTokenizer(itemset);
      str1=new String();
      for (j=1;j<i;j++) {
	str1=str1.concat(st.nextToken());
	str1=str1.concat(" ");
      }
      str2=st.nextToken();
      for (j=i+1;j<=len;j++) {
	str1=str1.concat(st.nextToken());
	str1=str1.concat(" ");
      }
      if (i!=1)
        str3=str3.concat(",");
      str3=str3.concat(str1.trim());
    } 

    return str3;

  } //end public String gensubset(String itemset)


//-------------------------------------------------------------
//  Method Name: gensuperset
//  Purpose    : generate all superset given an itemset 
//  Parameter  : itemset
//  Return     : a string contains all superset deliminated by ","
//             : e.g. "1 2,1 3,1 4" is superset of "1"
//-------------------------------------------------------------
  public String gensuperset(String itemset) {
    
    String str1,str2;
    int c;
    int i1=itemset.lastIndexOf(" ");

    if (i1==-1)
      str1=new String(itemset);
    else
      str1=new String(itemset.substring(i1+1));
    c=Integer.valueOf(str1).intValue();
    if (c==N)
      return null;
    else  {
      str2=new String();
      for (int b=c+1;b<N;b++) {
        str2=str2.concat(itemset);
        str2=str2.concat(" ");
        str2=str2.concat(Integer.toString(b));
        str2=str2.concat(",");
      }
      str2=str2.concat(itemset);
      str2=str2.concat(" ");
      str2=str2.concat(Integer.toString(N));
    }
    return str2;
  }
  
//-------------------------------------------------------------
//Method Name: getmaximalsequences
//Purpose    : get the maximal sequences 
//Parameter  : set of sequences
//Return     : maximal sequences
//-------------------------------------------------------------
public ArrayList<simpleSequence> getmaximalsequences(ArrayList<simpleSequence> tsequence, int numbersequences) {

ArrayList<simpleSequence> maximalsequences = new ArrayList();
int [] maximal = new int[numbersequences];
int indexlength = maxlength,numbermaximalsequences=0, indexmaximal=0;

for (int i = 0; i < numbersequences; i++){
	maximal[i]=1;
}

for (int k = maxlength; k > 1; k--){
	for (int i = 0; i < numbersequences; i++){
		if ((tsequence.get(i).length == k) && (maximal[i] !=0)){
			for (int j = 0; j < numbersequences; j++){
				if ((tsequence.get(j).length < tsequence.get(i).length) && (maximal[j]!= 0)){
					maximal[j] = checksequences (tsequence.get(i),tsequence.get(j));
				}
			}
			if (maximal[i]==1){
				maximalsequences.add(new simpleSequence(tsequence.get(i)));
			}
		}
	}
}

/*for (int i = 0; i < maximal.length; i++){
	if (maximal[i]==1){
		numbermaximalsequences++;
	}
}


for (int i = 0; i < numbersequences; i++){
	if (maximal[i]==1){
		maximalsequences.add(new tempsequence(tsequence.get(i)));
		indexmaximal++;
	}
}*/

return maximalsequences;
}

//-------------------------------------------------------------
//Method Name: checksequences
//Purpose    : check whether a sequence is into another one
//Parameter  : two sequences
//Return     : int value that indicates whether a sequence is into another one
//-------------------------------------------------------------
public int checksequences(simpleSequence slarge, simpleSequence sshort) {

int largeindex = 0, shortindex = 0;
boolean shorton = true, found;

/*System.out.print("slarge ");
for (int i =0; i < slarge.sequence.length; i++){
	System.out.print (slarge.sequence[i] + " ");
}
System.out.println();*/


/*System.out.print("sshort ");
for (int i =0; i < sshort.sequence.length; i++){
	System.out.print (sshort.sequence[i] + " ");
}
System.out.println();*/
	
while ((shortindex < sshort.length) && (shorton)){
	found = false;
	while ((!found)&&(largeindex < slarge.length)){
		if (sshort.sequence.get(shortindex)==slarge.sequence.get(largeindex)){
			found = true;
			//System.out.println ("found " + sshort.sequence[shortindex]);
		}
		else{
			largeindex++;
		}
	}
	if (found == false){
		//System.out.println("not found");
		shorton = false;
	}
	shortindex++;
	largeindex = 0;
}

if (shorton == true){
	if (((sshort.counter - slarge.counter) * 100)>=(minsup*M)){//it is within the bigger sequence but (short counter - large counter) has enough instances to consider it as interesting
		//System.out.println("ooooo");
		return 1;
	}
	else{
		if ((sshort.length > (slarge.length * lengthSimilarity)) && (sshort.counter > slarge.counter)){ //in this case the instances of the short sequence (which are not included in the large sequence) will be included as well
			ArrayList<Integer> differentInstances = findDifferentInstances (slarge.instances,sshort.instances);
			for (int i = 0; i < differentInstances.size(); i++){
				if (findInstance(differentInstances.get(i),slarge.shortExtraInstances) == 0){
					slarge.shortExtraInstances.add(differentInstances.get(i));
				}
			}
			return 0; //short instances are added but it is not considered as frequent
		}
		else{
			return 0;
		}
		
	}
	/*else{
		return 0;
	}*/
}
else{
	//System.out.println("not found return");
	return 1;
}

}

//-------------------------------------------------------------
//Method Name: findDifferentInstances(ArrayList<Integer>, ArrayList<Integer>)
//Purpose    : find the extra instances contained by the shorter sequence
//Parameters : two arraylist
//Return     : arrayList which contains the extra instances
//-------------------------------------------------------------
public ArrayList<Integer> findDifferentInstances (ArrayList<Integer> largeSequence, ArrayList<Integer> shortSequence){

ArrayList<Integer> differentInstances = new ArrayList<Integer>();
	
for (int i = 0; i < shortSequence.size(); i++){
	if ((findInstance(shortSequence.get(i),largeSequence)) == 0){
		differentInstances.add(shortSequence.get(i));
	}
}
	
return differentInstances;
}

//-------------------------------------------------------------
//Method Name: findInstance(int, ArrayList<Integer>)
//Purpose    : find if an instance is within a set of instances
//Parameters : instances, set of instances
//Return     : it is within --> 1, it is not-->0
//-------------------------------------------------------------
public int findInstance (int instance, ArrayList<Integer> instances){

for (int i = 0; i < instances.size(); i++){
	if (instance == instances.get(i)){
		return 1;
	}
}
return 0;
}

//-------------------------------------------------------------
//Method Name: getMaximalSequences
//Purpose    : return the maximal sequences
//Parameters : 
//Return     : maximalSequences
//-------------------------------------------------------------
public ArrayList<simpleSequence> getMaximalSequences (){
	return sequences;
}

public ArrayList<Integer> splitStringIntoInt (String transa){
	ArrayList<Integer> tempTransa = new ArrayList<Integer>();
	int begin = 0, pos = 0, end = 0;
	
	pos = transa.indexOf(" ",0);
	
	while (pos != -1){
		if (pos != -1){
			end = pos;
			tempTransa.add(Integer.parseInt(transa.substring(begin, end)));
			begin = end + 1;
		}
		pos = transa.indexOf(" ",pos + 1);
	}
		
	//tempTransa.add(Integer.parseInt(transa.substring(begin, transa.length()))); //in aprioriDicProcess there is a " " at the end, that's why it is not necessary this last part
	
	return tempTransa;
}



//-------------------------------------------------------------
//  Method Name: dicProcess()
//  Purpose    : main processing method
//  Parameters :
//  Return     : 
//-------------------------------------------------------------
  public aprioriDicProcess1(ArrayList<Integer>[] instances, int numActions, int numInstances, int minSup, double lenSimilarity) {

    String fullitemset=new String();
    String transa=new String();
    int j;
    String str0;
    String oneline=new String();
    StringTokenizer st;
    int lineprocessed;
    boolean qiaole=false;
    Date d=new Date();
    long s1,s2;
    String ss1,ss2;
    int numRead=0, length, ins = 0, len = 0;
    
    

    N = numActions;
    M = numInstances;
    minsup = minSup;
    stepm = 4;  //it is a random value
    lengthSimilarity = lenSimilarity;

    // generating 1-itemset in root.
    root=new hashtreenode(SS,null,0,1,0);
    for (int i=1;i<=N;i++) {
      String str=new String(Integer.toString(i));
      hashtreenode htn=new hashtreenode(DC,str,0,1,0);
      if (root.ht==null) {
        Hashtable ht=new Hashtable();
        root.ht=ht;
      }
      root.ht.put(str,htn);
    }

    fullitemset=fullitemset.concat("1");
    for (int i=2;i<=N;i++) {
      fullitemset=fullitemset.concat(" ");
      fullitemset=fullitemset.concat(Integer.toString(i));
    }

    k=0;
    tid=1;
    lineprocessed=0;

    d=new Date();
    s1=d.getTime();
    
    //System.out.print("Processing step M number: ");

    while (true) {
      k++;
      //System.out.print(k+"..");
      // if no dashed item exsits, program ends.
      if (!dashfound(root))
        break;
      lineprocessed=0;
      while (true) {
        //oneline=data_in.readLine();
        numRead++;
        if (numRead > M) {
        	numRead=0;
        	if (qiaole) {
        		//oneline=data_in.readLine();
        		tid=1;
        	}
        	else {
        		tid=1;
        		break;
        	}
        }
        st=new StringTokenizer(oneline.trim());
        j=0;
        str0=new String();
        transa=new String();
        while (j < N) {
        	  j++;
        	    ins=instances[tid-1].get(j-1);
        	    if (ins!=0) {
        	      transa=transa.concat(" ");
        	      transa=transa.concat(Integer.toString(j));
        	      len++;
        	    }
        } 
        transa=transa.trim();
        transatrahashtree(transa,root,0,tid);
        lineprocessed++;
        tid++;
        if (lineprocessed>=stepm && tid>M)
            qiaole=true;
        else
	    qiaole=false;

        if (tid>M) 
          tid=1;

        if (lineprocessed>=stepm)
            break;
      }

//System.out.println("Now checking counter to find those whose counter>minsup...");
      checkcounter(root,fullitemset,0);
//System.out.println("Now checking hashtree to find those whose superset should be generated...");
      checkhashtree(root,fullitemset,0);
//System.out.println("Now checking those count all through and not need counting any more...");
      checkcountedall(root,fullitemset,0);

    }

    DSset=new String();
    DCset=new String();
    SCset=new String();
    SSset=new String();
    printhashtree(root,fullitemset,0);
    length = printhashtreecount(root,fullitemset,0,0);
    printhashtreetempsequence(root,fullitemset,0,length);
    
    /*for (int i = 0; i < tempSequences.size(); i++){
    	System.out.println("length " + tempSequences.get(i).length + " counter " + tempSequences.get(i).counter);
    	System.out.println("      sequence " + tempSequences.get(i).sequence);
    	System.out.println("      instances " + tempSequences.get(i).instances);
    }*/
    
    sequences = getmaximalsequences (tempSequences,tempSequences.size());
    
    /*System.out.println("maximal length " + sequences.size());
    for (int i = 0; i < sequences.size(); i++){
    	System.out.println("length " + sequences.get(i).length);
    	System.out.println("counter " + sequences.get(i).counter);
    	System.out.println("sequence " + sequences.get(i).sequence);
    	System.out.println("instances " + sequences.get(i).instances);
    	System.out.println("extra instances of shorter sequences " + sequences.get(i).shortExtraInstances);
    	System.out.println();
    }*/
    
    d=new Date();
    s2=d.getTime();
    //System.out.println("Execution time is: "+(s2-s1)/1000+" seconds.");

//System.out.println("End.");

  }
}
