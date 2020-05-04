package br.mia.Controller;

import java.util.List;

import br.mia.form.data.SystemProver;
import br.mia.model.AtomicFormula;
import br.mia.model.Constant;
import br.mia.model.Function;
import br.mia.model.Parameter;
import br.mia.model.Variable;

public class UnificationAlgorithm {

	public static ClauseSet execute(Clause base, Clause proposition) {

		ClauseSet clauseSet = new ClauseSet();

		List<AtomicFormula> atomicBase = base.getLiterals();
		List<AtomicFormula> atomicProposition = proposition.getLiterals();

		for (AtomicFormula predicateBase : atomicBase) {
			for (AtomicFormula predicateProposition : atomicProposition) {

				if (predicateBase.complementarPair(predicateProposition)
						&& predicateBase.getPredicate().getArity() == predicateProposition
								.getPredicate().getArity()) {

					AtomicFormula pBase = (AtomicFormula) predicateBase.clone();
					AtomicFormula pProposition = (AtomicFormula) predicateProposition
							.clone();

					try {
						Substitution substitution = getUnification(pBase
								.getPredicate().getParemeters(), pProposition
								.getPredicate().getParemeters());
						{
							Clause clause = base.clone();
							clause.substitute(substitution);
							if (!clauseSet.contains(clause))
								clauseSet.add(clause);
						}
						{
							Clause clause = proposition.clone();
							clause.substitute(substitution);
							if (!clauseSet.contains(clause))
								clauseSet.add(clause);
						}
					} catch (Exception e) {
						break;
					}
				}
			}
		}
		SystemProver.out.println("\tUnification Clauses: " + clauseSet);
		return clauseSet;
	}

	public static Substitution getUnification(Parameter[] firstParameters,
			Parameter[] secondParameters) {

		Substitution substitution = new Substitution();

		if (firstParameters.length != secondParameters.length)
			return substitution;

		int arity = firstParameters.length;

		for (int i = 0; i < arity; i++) {

			if (firstParameters[i] instanceof Variable) {
				if (firstParameters[i].equals(secondParameters[i])
						&& secondParameters[i] instanceof Variable)
					continue;
				Parameter aux = firstParameters[i];
				substitution.addSubstitution(firstParameters[i],
						secondParameters[i]);
				for (int j = 0; j < arity; j++)
					firstParameters[j] = firstParameters[j].getReplace(
							secondParameters[i], aux);
			} else if (firstParameters[i] instanceof Constant
					&& secondParameters[i] instanceof Variable) {
				Parameter aux = secondParameters[i];
				substitution.addSubstitution(firstParameters[i],
						secondParameters[i]);
				for (int j = 0; j < arity; j++)
					secondParameters[j] = secondParameters[j].getReplace(
							firstParameters[i], aux);
			} else if (firstParameters[i] instanceof Function
					&& secondParameters[i] instanceof Variable) {
				Parameter aux = secondParameters[i];
				for (int j = 0; j < arity; j++)
					secondParameters[j] = secondParameters[j].getReplace(
							firstParameters[i], aux);
			} else if (firstParameters[i] instanceof Function
					&& secondParameters[i] instanceof Function) {

				if (((Function) firstParameters[i]).getArity() == ((Function) secondParameters[i])
						.getArity()) {
					getUnification(((Function) secondParameters[i])
							.getParameters(), ((Function) firstParameters[i])
							.getParameters());
				} else {
					substitution = new Substitution();
					break;
				}
			} else {
				substitution = new Substitution();
				break;
			}
		}
		return substitution;
	}
}