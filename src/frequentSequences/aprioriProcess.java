package frequentSequences;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;


public class aprioriProcess {
	
	public static void main(String[] args){

	}
	
	public static ArrayList<simpleSequence> findFrequentSequences (ArrayList<Integer>[] instances, int numActions, int numInstances, int minSup, double lenSimilarity){
		ArrayList<simpleSequence> maximalSequences;
		ArrayList<simpleSequence> modifiedMaximalSequences = new ArrayList<simpleSequence>();
		
		aprioriProcess1 process1=new aprioriProcess1(instances,numActions,numInstances,minSup,lenSimilarity);
		maximalSequences = process1.getMaximalSequences();
		
		//System.out.println("  maximal " + maximalSequences.size());
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
//Class Name : aprioriProcess
//Purpose    : main processing class
//-------------------------------------------------------------
class aprioriProcess1 {

private final int HT=1; // state of tree node (hash table or
private final int IL=2; // itemset list)
int N; // total item #
int M; // total transaction #
Vector largeitemset=new Vector();
Vector candidate=new Vector();
int minsup;
String fullitemset;
ArrayList<simpleSequence> tempSequences = new ArrayList();
ArrayList<simpleSequence> sequences = new ArrayList();
int maxlength = 0;
public static double lengthSimilarity;


//-------------------------------------------------------------
//Class Name : candidateelement
//Purpose    : object that will be stored in Vector candidate
//         : include 2 item
//         : a hash tree and a candidate list
//-------------------------------------------------------------
class candidateelement {
hashtreenode htroot;
Vector candlist;
}


//-------------------------------------------------------------
//Class Name : hashtreenode
//Purpose    : node of hash tree
//-------------------------------------------------------------
class hashtreenode {
int nodeattr; //  IL or HT
int depth;
Hashtable ht;
Vector itemsetlist;

public void hashtreenode() {
  nodeattr=HT;
  ht=new Hashtable();
  itemsetlist=new Vector();
  depth=0;
}

public void hashtreenode(int i) {
  nodeattr=i;
  ht=new Hashtable();
  itemsetlist=new Vector();
  depth=0;
}
}  


//-------------------------------------------------------------
//Class Name : itemsetnode
//Purpose    : node of itemset
//-------------------------------------------------------------
class itemsetnode {
String itemset;
double counter;
ArrayList<Integer> instances;

public itemsetnode(String s1,int i1) {
  itemset=new String(s1);
  counter=i1;
  instances = new ArrayList<Integer>();
}

public itemsetnode() {
  itemset=new String();
  counter=0;
  instances = new ArrayList<Integer>();
}

public String toString() {
  String tmp=new String();
  tmp=tmp.concat(itemset);
  return tmp;
}
}


//-------------------------------------------------------------
//Method Name: parseitemset
//Purpose    : parse htn.itemset into array
//Parameter  : htn.itemset is a hashtreenode (when other method call this method,it is the root)
//     : length is the length of the string
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
//Method Name: printhashtree
//Purpose    : print the whole hash tree
//Parameter  : htn is a hashtreenode (when other method call this method,it is the root)
//         : transa : special transaction with all items occurr in it.
//         : a : recursive depth
//Return     : 
//-------------------------------------------------------------
public void printhashtree(hashtreenode htn,String transa,int a) {
if (htn.nodeattr == IL ) {
  System.out.println("Node is an itemset list");
  System.out.println("	depth :<"+htn.depth+">");
  System.out.println("	iteset:<"+htn.itemsetlist+">");
}
else { // HT
  System.out.println("Node is a hashtable");
  if (htn.ht==null)
    return;
  for (int b=a+1;b<=N;b++)
    if (htn.ht.containsKey(Integer.toString(getitemat(b,transa)))) {
      System.out.println("	key:<"+getitemat(b,transa));
      printhashtree((hashtreenode)htn.ht.get(Integer.toString(getitemat(b,transa))),transa,b);
    }
}
}


//-------------------------------------------------------------
//Method Name: getitemat
//Purpose    : get an item from an itemset
//         : get the total number of items of transaction file
//Parameter  : int i : i-th item ; itemset : string itemset
//Return     : int : the item at i-th in the itemset 
//-------------------------------------------------------------
public int getitemat(int i,String itemset) {

String str1=new String(itemset);
StringTokenizer st=new StringTokenizer(itemset);
int j;

if (i > st.countTokens())
  System.out.println("eRRor! in getitemat, !!!!");

for (j=1;j<=i;j++)
  str1=st.nextToken();

return(Integer.valueOf(str1).intValue());
}


//-------------------------------------------------------------
//Method Name: itesetsize
//Purpose    : get item number of an itemset
//Parameter  : itemset : string itemset
//Return     : int : the number of item of the itemset 
//-------------------------------------------------------------
public int itemsetsize(String itemset) {
StringTokenizer st=new StringTokenizer(itemset);
return st.countTokens();
}


//-------------------------------------------------------------
//Method Name: gensubset
//Purpose    : generate all subset given an itemset
//Parameter  : itemset
//Return     : a string contains all subset deliminated by ","
//         : e.g. "1 2,1 3,2 3" is subset of "1 2 3"
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
//Method Name: createcandidate
//Purpose    : generate candidate n-itemset
//Parameter  : int n : n-itemset
//Return     : Vector : candidate is stored in a Vector
//-------------------------------------------------------------
public Vector createcandidate(int n) { 

Vector tempcandlist=new Vector();
Vector ln_1=new Vector();
int i,j,length1;
String cand1=new String();
String cand2=new String();
String newcand=new String();

//System.out.println("Generating "+n+"-candidate item set ....");
if (n==1)
  for (i=1;i<=N;i++)
    tempcandlist.addElement(Integer.toString(i));
else {
  ln_1=(Vector)largeitemset.elementAt(n-2);
  length1=ln_1.size();
  for (i=0;i<length1;i++) {
    cand1=(String)ln_1.elementAt(i);
    for (j=i+1;j<length1;j++) {
      cand2=(String)ln_1.elementAt(j);
      newcand=new String();
      if (n==2) {
        newcand=cand1.concat(" ");
        newcand=newcand.concat(cand2);
        tempcandlist.addElement(newcand.trim());
      }
      else {
        int c,i1,i2;
        boolean same=true;

        for (c=1;c<=n-2;c++) {
          i1=getitemat(c,cand1);
          i2=getitemat(c,cand2);
          if ( i1!=i2 ) {
            same=false;
            break;
          }
          else {
            newcand=newcand.concat(" ");
            newcand=newcand.concat(Integer.toString(i1));
          }
        }
        if (same) {
          i1=getitemat(n-1,cand1);
          i2=getitemat(n-1,cand2);
          newcand=newcand.concat(" ");
          newcand=newcand.concat(Integer.toString(i1));
          newcand=newcand.concat(" ");
          newcand=newcand.concat(Integer.toString(i2));
          tempcandlist.addElement(newcand.trim());
        }
      } //end if n==2 else
    } //end for j
  } //end for i
} //end if n==1 else

if (n<=2) 
  return tempcandlist;

Vector newcandlist=new Vector();
for (int c=0; c<tempcandlist.size(); c++) {
  String c1=(String)tempcandlist.elementAt(c);
  String subset=gensubset(c1);
  StringTokenizer stsubset=new StringTokenizer(subset,",");
  boolean fake=false;
  while (stsubset.hasMoreTokens())
if (!ln_1.contains(stsubset.nextToken())) {
      fake=true;
  break;
    }
  if (!fake)
newcandlist.addElement(c1);
}

return newcandlist;

} //end public createcandidate(int n)


//-------------------------------------------------------------
//Method Name: createcandidatehashtre
//Purpose    : generate candidate hash tree
//Parameter  : int n : n-itemset
//Return     : hashtreenode : root of the hashtree
//-------------------------------------------------------------
public hashtreenode createcandidatehashtree(int n) {  

int i,len1;
hashtreenode htn=new hashtreenode();

//System.out.println("Generating candidate "+n+"-itemset hashtree ....");
if (n==1)
  htn.nodeattr=IL;
else
  htn.nodeattr=HT;

len1=((candidateelement)candidate.elementAt(n-1)).candlist.size();
for (i=1;i<=len1;i++) {
  String cand1=new String();
  cand1=(String)((candidateelement)candidate.elementAt(n-1)).candlist.elementAt(i-1);
  genhash(1,htn,cand1);
}

return htn;

} //end public createcandidatehashtree(int n)


//-------------------------------------------------------------
//Method Name: genhash
//Purpose    : called by createcandidatehashtree
//         : recursively generate hash tree node
//Parameter  : htnf is a hashtreenode (when other method call this method,it is the root)
//         : cand : candidate itemset string
//         : int i : recursive depth,from i-th item, recursive
//Return     : 
//-------------------------------------------------------------
public void genhash(int i, hashtreenode htnf, String cand) {

int n=itemsetsize(cand);
if (i==n) {
  htnf.nodeattr=IL;
  htnf.depth=n;
  itemsetnode isn=new itemsetnode(cand,0);
  if (htnf.itemsetlist==null)
    htnf.itemsetlist=new Vector();
  htnf.itemsetlist.addElement(isn);
}
else {
  if (htnf.ht==null) 
    htnf.ht=new Hashtable(HT);
  if (htnf.ht.containsKey(Integer.toString(getitemat(i,cand)))) {
    htnf=(hashtreenode)htnf.ht.get(Integer.toString(getitemat(i,cand)));
    genhash(i+1,htnf,cand);
  }
  else {
    hashtreenode htn=new hashtreenode();
    htnf.ht.put(Integer.toString(getitemat(i,cand)),htn);
    if (i==n-1) {
      htn.nodeattr=IL;
      //Vector isl=new Vector();
      //htn.itemsetlist=isl;
      genhash(i+1,htn,cand);
    }
    else {
      htn.nodeattr=HT;
      //Hashtable ht=new Hashtable();
      //htn.ht=ht;
      genhash(i+1,htn,cand);
    }
  }
}
} //end public void genhash(int i, hashtreenode htnf, String cand)


//-------------------------------------------------------------
//Method Name: createlargeitemset
//Purpose    : find all itemset which have their counters>=minsup
//Parameter  : int n : n-itemset
//Return     : 
//-------------------------------------------------------------
public void createlargeitemset(int n) {

Vector candlist=new Vector();
Vector lis=new Vector(); //large item set
hashtreenode htn=new hashtreenode();
int i;

//System.out.println("Generating "+n+"-large item set ....");
candlist=((candidateelement)candidate.elementAt(n-1)).candlist;
htn=((candidateelement)candidate.elementAt(n-1)).htroot;
  
getlargehash(0,htn,fullitemset,lis,n);

largeitemset.addElement(lis);

} // end public void createlargeitemset(int n)


//-------------------------------------------------------------
//Method Name: getlargehash
//Purpose    : recursively traverse candidate hash tree 
//         : to find all large itemset
//Parameter  : htnf is a hashtreenode (when other method call this method,it is the root)
//         : cand : candidate itemset string
//         : int i : recursive depth
//         : Vector lis : Vector that stores large itemsets
//Return     : 
//-------------------------------------------------------------
public void getlargehash(int i,hashtreenode htnf,String transa,Vector lis, int length) {

Vector tempvec=new Vector();
int j;

if (htnf.nodeattr==IL) {
  tempvec=htnf.itemsetlist;
  for (j=1;j<=tempvec.size();j++)
    if (((itemsetnode)tempvec.elementAt(j-1)).counter * 100 >= ((minsup * M))){
        lis.addElement( ((itemsetnode)tempvec.elementAt(j-1)).itemset );
        tempSequences.add(new simpleSequence(length,((itemsetnode)tempvec.elementAt(j-1)).counter,parseitemset(tempvec.elementAt(j-1).toString(),length),((itemsetnode)tempvec.elementAt(j-1)).instances));
    }
}
else {
  if (htnf.ht==null)
    return;
  for (int b=i+1;b<=N;b++)
    if (htnf.ht.containsKey(Integer.toString(getitemat(b,transa)))) 
      getlargehash(b,(hashtreenode)htnf.ht.get(Integer.toString(getitemat(b,transa))),transa,lis,length);
}
}


//-------------------------------------------------------------
//  Method Name: transatraverse
//  Purpose    : read each transaction, traverse hashtree, 
  //		               incrment approporiate itemset counter.
//  Parameter  : int n : n-itemset
//  Return     : 
//-------------------------------------------------------------
public void transatraverse(int n, ArrayList<Integer>[] instances) {

String oneline=new String();
int i=0,j=0,len=0;
String transa;
hashtreenode htn=new hashtreenode();
StringTokenizer st;
String str0;
int indexRead = 0;

//System.out.println("Traverse "+n+"-candidate hashtree ... ");
htn=((candidateelement)candidate.elementAt(n-1)).htroot;
while (indexRead < instances.length ) {
	  transa=new String();
  st=new StringTokenizer(oneline.trim());
j=0;
  while (j < N) {
  j++;
    i=instances[indexRead].get(j-1);
    if (i!=0) {
      transa=transa.concat(" ");
      transa=transa.concat(Integer.toString(j));
      len++;
    }
  } 
  transa=transa.trim();
  transatrahash(0,htn,transa,indexRead);
  indexRead++;
}
}


//-------------------------------------------------------------
//Method Name: transatrahash
//Purpose    : called by transatraverse
//         : recursively traverse hash tree
//Parameter  : htnf is a hashtreenode (when other method call this method,it is the root)
//         : cand : candidate itemset string
//         : int i : recursive depth,from i-th item, recursive
//Return     : 
//-------------------------------------------------------------
public void transatrahash(int i,hashtreenode htnf,String transa,int index) {
	
String stris=new String();
Vector itemsetlist=new Vector();
int j,lastpos,len,d;
itemsetnode tmpnode=new itemsetnode();

if (htnf.nodeattr==IL) {
  itemsetlist=(Vector)htnf.itemsetlist;
  len=itemsetlist.size();
  for (j=0;j<len;j++) {
	  tmpnode=(itemsetnode)itemsetlist.elementAt(j);
	  d=getitemat(htnf.depth,tmpnode.itemset);
	lastpos = findIntInString(d,transa);
    //lastpos=transa.indexOf(Integer.toString(d));
    if (lastpos!=-1){
        ((itemsetnode)(itemsetlist.elementAt(j))).counter++;
        ((itemsetnode)(itemsetlist.elementAt(j))).instances.add(index);
    }
  }
  return;
}
else{//HT
	for (int b=i+1;b<=itemsetsize(transa);b++){
  	  if (htnf.ht.containsKey(Integer.toString(getitemat(b,transa)))){
            transatrahash(i,(hashtreenode)htnf.ht.get(Integer.toString(getitemat(b,transa))),transa,index);
  	  }
    }
}   

} // public transatrahash(int ii,hashtreenode htnf,String transa)


public int findIntInString (int value, String transa){
	ArrayList<Integer> tempTransa = splitStringIntoInt (transa);
	for (int i = 0; i < tempTransa.size(); i++){
		if (value == tempTransa.get(i)){
			return i;
		}
	}
	return -1;
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
	
	tempTransa.add(Integer.parseInt(transa.substring(begin, transa.length())));
	
	return tempTransa;
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


//-------------------------------------------------------------
//Method Name: aprioriProcess()
//Purpose    : main processing method
//Parameters :
//Return     : 
//-------------------------------------------------------------
public aprioriProcess1(ArrayList<Integer>[] instances, int numActions, int numInstances, int minSup, double lenSimilarity) {

N = numActions;
M = numInstances;
minsup = minSup;
lengthSimilarity = lenSimilarity;
candidateelement cande;
int k=0;
Vector large=new Vector();
Date d=new Date();
long s1,s2;

System.out.println();
System.out.println("Algorithm apriori starting now.....");
System.out.println();

fullitemset=new String();
fullitemset=fullitemset.concat("1");
for (int i=2;i<=N;i++) {
  fullitemset=fullitemset.concat(" ");
  fullitemset=fullitemset.concat(Integer.toString(i));
}

System.out.println("initial ");
System.out.println(fullitemset);

d=new Date();
s1=d.getTime();

while (true) {
  k++;
  cande=new candidateelement();
  cande.candlist=createcandidate(k);

//System.out.println("C"+k+"("+k+"-candidate-itemset): "+cande.candlist);

  if (cande.candlist.isEmpty()){
	  //System.out.println("break");
	  break;
  }

  cande.htroot=null;
  candidate.addElement(cande);

  ((candidateelement)candidate.elementAt(k-1)).htroot=createcandidatehashtree(k);

System.out.println("Now reading transactions, increment counters of itemset");
  transatraverse(k,instances);

  createlargeitemset(k);
  //System.out.println("Frequent "+k+"-itemsets:");
  //System.out.println((Vector)(largeitemset.elementAt(k-1)));
}

hashtreenode htn=new hashtreenode();
htn=((candidateelement)candidate.elementAt(k-2)).htroot;

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
/*System.out.println();
System.out.println("Execution time is: "+((s2-s1)/1000) + " seconds.");*/


//System.out.println("End.");

}
}


