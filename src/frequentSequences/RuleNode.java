package frequentSequences;
/* -------------------------------------------------------------------------- */
/*                                                                            */
/*                      ASSOCIATION RULE DATA MINING                          */
/*                                                                            */
/*                            Frans Coenen                                    */
/*                                                                            */
/*                        Tuesday 14 Match 2006                               */
/*                                                                            */
/*                    Department of Computer Science                          */
/*                     The University of Liverpool                            */
/*                                                                            */ 
/* -------------------------------------------------------------------------- */

//package lucsKDD_ARM;

/** Class for storing linked list of ARs or CARs as appropriate. Used to 
be defined as an inner class in AssocRuleMining class.
@author Frans Coenen
@version 14 March 2006 */

public class RuleNode {

    /* ------ FIELDS ------ */
    
    /** Antecedent of AR. */
    public short[] antecedent;
    
    /** Consequent of AR. */
    public short[] consequent;
    
    /** The confidence value associate with the rule represented by this
    node. */
    public double confidenceForRule=0.0;
	
    /** Link to next node */
    public RuleNode next = null;

    /* ------ CONSTRUCTOR ------ */
	
    /** Three argument constructor
    @param ante the antecedent (LHS) of the AR.
    @param cons the consequent (RHS) of the AR.
    @param confValue the associated confidence value. */
	
    public RuleNode(short[] ante, short[]cons, double confValue) {
	antecedent        = ante;
	consequent        = cons;
	confidenceForRule = confValue;
	}

    /* ------ METHODS ------ */

    /* NONE */
    
    }

