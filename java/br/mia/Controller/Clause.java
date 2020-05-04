package br.mia.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.mia.model.AtomicFormula;

public class Clause implements Comparable<Clause> {

	private List<AtomicFormula> literals = new ArrayList<AtomicFormula>();

	private ClauseSet factors;

	public Clause() {
		this.literals = new ArrayList<AtomicFormula>();
	}

	public Clause(Clause clause) {
		this.literals = new ArrayList<AtomicFormula>();
		for (AtomicFormula atomicFormula : clause.getLiterals()) {
			this.literals.add((AtomicFormula) atomicFormula.clone());
		}
	}

	public void addLiteral(AtomicFormula literal) {
		this.literals.add(literal);
	}

	public void addAll(List<AtomicFormula> literals) {
		this.literals.addAll(literals);
	}

	public boolean removeLiteral(AtomicFormula literal) {
		return this.literals.remove(literal);
	}

	public int compareTo(Clause arg0) {
		return 0;
	}

	public String toString() {
		return this.literals.toString();
	}

	public List<AtomicFormula> getLiterals() {
		return literals;
	}

	public void setAtomicFormulas(List<AtomicFormula> literals) {
		this.literals = literals;
	}

	public int numLiterals() {
		return this.literals.size();
	}

	public Clause clone() {
		return new Clause(this);
	}

	public boolean havaComplementarPair(Clause clause) {
		for (AtomicFormula first : this.literals) {
			for (AtomicFormula second : clause.getLiterals()) {
				if (first.complementarPair(second)) {
					return Boolean.TRUE;
				}
			}
		}
		return Boolean.FALSE;
	}

	public Clause removeComplementarPair(Clause clause) {

		Clause newClause = null;
		if (this.havaComplementarPair(clause)) {
			newClause = new Clause();
			List<AtomicFormula> firstList = new ArrayList<AtomicFormula>(this
					.getLiterals());
			List<AtomicFormula> secondList = new ArrayList<AtomicFormula>(
					clause.getLiterals());
			newClause.addAll(firstList);
			newClause.addAll(secondList);
			for (AtomicFormula firstElement : firstList) {
				for (AtomicFormula secondElement : secondList) {
					if (firstElement.getPredicate().equalsParams(
							secondElement.getPredicate())) {
						newClause.removeLiteral(firstElement);
						newClause.removeLiteral(secondElement);
					}
				}
			}
			return newClause;
		}
		return newClause;
	}

	public Clause substitute(Substitution substitution) {

		Clause clause = this.clone();

		for (AtomicFormula atomicFormula : clause.getLiterals())
			for (Iterator<InOut> element = substitution.iterator(); element
					.hasNext();) {
				InOut inOut = element.next();
				atomicFormula.swapParameters(inOut.getOut(), inOut.getIn());
			}
		return clause;
	}

	public void calculatorFactor() {

		this.factors = new ClauseSet();
		List<AtomicFormula> factorsAux = this.literals;

		for (int i = 0; i < this.literals.size(); i++) {
			for (int j = 1; j < this.literals.size() - 1; j++) {

				AtomicFormula firstAux = factorsAux.get(i);
				AtomicFormula secondAux = factorsAux.get(j);
				if (firstAux.equals(secondAux)) {

				}
			}
		}
	}

	public boolean isEmpty() {
		return this.literals.isEmpty();
	}
}