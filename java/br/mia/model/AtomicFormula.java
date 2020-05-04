package br.mia.model;

import java.util.List;

public class AtomicFormula extends WellFormedFormula {

	private Predicate predicate;

	public AtomicFormula() {
		super();
	}

	public AtomicFormula(Predicate predicate) {
		setPredicate(predicate);
	}

	public AtomicFormula(AtomicFormula atomicFormula) {
		super(atomicFormula);
		setPredicate(atomicFormula.predicate);
	}

	public Predicate getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate predicate) {
		this.predicate = predicate.clone();
	}

	@Override
	public void negate() {
		this.negated = !this.negated;
	}

	@Override
	public WellFormedFormula clone() {
		return new AtomicFormula(this);
	}

	@Override
	public boolean containsVariable(Variable variable) {
		return predicate.containsVariable(variable);
	}

	@Override
	public List<Variable> getFreeVariables() {
		return predicate.getFreeVariables();
	}

	@Override
	public WellFormedFormula toPrenexNormalForm() {
		return clone();
	}

	@Override
	public WellFormedFormula toSkolemNormalForm() {
		return clone();
	}

	@Override
	public WellFormedFormula toConjunctiveNormalForm() {
		return clone();
	}

	/*
	 * @Override public void updateVariableRef(Variable variable) {
	 * predicate.updateVariableRef(variable); }
	 */

	@Override
	public void swapParameters(Parameter out, Parameter in) {
		predicate.swapParameters(out, in);
	}

	public String toString() {
		return "("
				+ (negated ? "~" + predicate.toString() : predicate.toString())
				+ ")";
	}

	public boolean equals(AtomicFormula atomicFormula) {
		return this.predicate.equals(atomicFormula.getPredicate());
	}

	public boolean complementarPair(AtomicFormula atomicFormula) {
		return this.equals(atomicFormula)
				&& (this.isNegated() ^ atomicFormula.isNegated());
	}
}