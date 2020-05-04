package br.mia.Controller;

import java.util.ArrayList;
import java.util.List;

import br.mia.model.AtomicFormula;
import br.mia.model.Connective;
import br.mia.model.ConnectiveFormula;
import br.mia.model.QuantifiedFormula;
import br.mia.model.WellFormedFormula;

public class ClauseExtractor {

	public static ClauseSet extract(WellFormedFormula wff) {

		ClauseSet listClause = new ClauseSet();
		WellFormedFormula initialWff = wff;

		while (initialWff instanceof QuantifiedFormula) {
			initialWff = ((QuantifiedFormula) initialWff).getWff();
		}

		if (initialWff instanceof AtomicFormula) {
			Clause clause = new Clause();
			clause.addLiteral((AtomicFormula) initialWff);
			listClause.add(clause);
			return listClause;
		} else {
			listClause.addAll(getRecurseClause(initialWff));
		}

		return listClause;
	}

	private static List<Clause> getRecurseClause(WellFormedFormula wff) {

		List<Clause> listClause = new ArrayList<Clause>();

		WellFormedFormula right = ((ConnectiveFormula) wff).getRightWff();
		WellFormedFormula left = ((ConnectiveFormula) wff).getLeftWff();

		if (((ConnectiveFormula) wff).getConnective().equals(Connective.AND)) {

			if (right instanceof AtomicFormula) {
				Clause clause = new Clause();
				clause.addLiteral((AtomicFormula) right);
				listClause.add(clause);
			} else {
				listClause.addAll(getRecurseClause(right));
			}

			if (left instanceof AtomicFormula) {
				Clause clause = new Clause();
				clause.addLiteral((AtomicFormula) left);
				listClause.add(clause);
			} else {
				listClause.addAll(getRecurseClause(left));
			}
		}

		if (((ConnectiveFormula) wff).getConnective().equals(Connective.OR)) {

			Clause clause = new Clause();

			if (right instanceof AtomicFormula) {
				clause.addLiteral((AtomicFormula) right);
			} else {
				for (Clause clauseAux : getRecurseClause(right)) {
					clause.addAll(clauseAux.getLiterals());
				}
			}

			if (left instanceof AtomicFormula) {
				clause.addLiteral((AtomicFormula) left);
			} else {
				for (Clause clauseAux : getRecurseClause(left)) {
					clause.addAll(clauseAux.getLiterals());
				}
			}

			listClause.add(clause);
		}

		return listClause;
	}
}
