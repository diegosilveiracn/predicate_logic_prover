// This abastract class represents any wff

package br.mia.model;

import java.util.List;

public abstract class WellFormedFormula {

	// Flag indicating whether this wff has a '~' (negation sign) in front of it
	protected boolean negated;
	
	// Default constructor
	public WellFormedFormula() {
	}
	
	// Constructor used to make clones.
	public WellFormedFormula(WellFormedFormula wff) {
		this.negated = wff.negated;
	}
	
	// Returns whether this wff has a '~' (negation sign) in front of it
	public boolean isNegated() {
		return negated;
	}

	// Sets whether this wff has a '~' (negation sign) in front of it
	public void setNegated(boolean negated) {
		this.negated = negated;
	}
	
	// Negates the wff. This method should internalize the '~' to the atomic formulas.
	// It effectivelly changes the structure of the wff.
	public abstract void negate();
	
	// Returns a wff which represents the negation of the current wff.
	// This method should not change the current wff.
	public WellFormedFormula getNegation() {
		WellFormedFormula negatedFormula = this.clone();
		negatedFormula.negate();
		return negatedFormula;
	}
	
	// Clone the wff. Changes in the new clone object should not affect the original cloned objet.
	public abstract WellFormedFormula clone();
	
	// Recursivelly checks whether the wff contains a certain variable
	public abstract boolean containsVariable(Variable variable);
	
	// Recursivelly retrieves the wff's free variables. A free variable is one not bounded to
	// any scope (QuantifiedFormula)
	public abstract List<Variable> getFreeVariables();
	
	// Returns the current wff with no free variables. The free variables will be binded to a
	// forall quantifier
	public WellFormedFormula bindFreeVariables() {
		List<Variable> freeVariableList = getFreeVariables();
		WellFormedFormula wff = this;
		for (Variable variable : freeVariableList)
			wff = new QuantifiedFormula(Quantifier.FORALL, variable, wff);
		return wff;
	}
	
	// TODO: is this necessary/correct?
	//public abstract void updateVariableRef(Variable variable);
	
	// Recursevelly takes out the paramter 'out' in the wff and swaps it by the parameter 'in'.
	public abstract void swapParameters(Parameter out, Parameter in);
	
	// Returns a wff which represents the current wff in prenex normal form.
	public abstract WellFormedFormula toPrenexNormalForm();

	// Returns a wff which represents the current wff in skolem normal form.
	public abstract WellFormedFormula toSkolemNormalForm();
	
	// Returns a wff which represents the current wff in conjunctive normal form.
	public abstract WellFormedFormula toConjunctiveNormalForm();
	
}
