package br.mia.Controller;

import java.util.LinkedList;
import java.util.List;

import br.mia.form.data.SystemProver;
import br.mia.model.WellFormedFormula;

public class KnowledgeBase {

	List<WellFormedFormula> baseWffs = new LinkedList<WellFormedFormula>();

	ClauseSet baseClauses = new ClauseSet();

	public KnowledgeBase() {
	}

	public KnowledgeBase(List<WellFormedFormula> baseWffs) {
		setBaseWffs(baseWffs);
	}

	public List<WellFormedFormula> getBaseWffs() {
		return baseWffs;
	}

	public void setBaseWffs(List<WellFormedFormula> baseWffs) {
		this.baseClauses.clear();
		this.baseWffs.clear();
		for (WellFormedFormula wff : baseWffs)
			addWff(wff);
	}

	public void addWff(WellFormedFormula wff) {
		SystemProver.out.println("\tAdding " + wff.toString());
		baseWffs.add(wff);
		WellFormedFormula conjunctiveFormula = wff.bindFreeVariables()
				.toConjunctiveNormalForm();
		SystemProver.out.println("\tCNF: " + conjunctiveFormula.toString());
		ClauseSet wffClauses = ClauseExtractor.extract(conjunctiveFormula);
		SystemProver.out.println("\tResulting clauses: " + wffClauses);
		baseClauses.addAll(wffClauses);
	}

	public ClauseSet getBaseClauses() {
		return baseClauses;
	}

}