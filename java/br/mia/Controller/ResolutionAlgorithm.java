package br.mia.Controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import br.mia.form.data.SystemProver;

public class ResolutionAlgorithm {

public static boolean execute(ClauseSet firstParam, ClauseSet secondParam) {

		List<Clause> list = new ArrayList<Clause>();
		for (Clause clause : firstParam) {
			list.add(clause);
		}

		Queue<Clause> queue = new LinkedList<Clause>();
		for (Clause clause : secondParam) {
			queue.add(clause);
		}

		while (!queue.isEmpty()) {

			Clause clause = queue.poll();
			for (Clause clauseElement : list) {

				ClauseSet clauseSet = UnificationAlgorithm.execute(clause,
						clauseElement);
				for (Clause clauseUnification : clauseSet) {
					queue.add(clauseUnification);
				}

				Clause aux = clause.removeComplementarPair(clauseElement);
				SystemProver.out.println("\tResolution Clauses: " + clause + clauseElement);
				
				if (aux == null)
					continue;
				else if (aux.isEmpty())
					return Boolean.TRUE;
				else
					queue.add(aux);
			}
			list.add(clause);
		}
		return Boolean.FALSE;
	}}