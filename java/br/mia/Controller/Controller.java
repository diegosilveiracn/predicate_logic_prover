package br.mia.Controller;

import java.io.StringReader;
import java.util.List;

import br.mia.form.data.SystemProver;
import br.mia.model.QuantifiedFormula;
import br.mia.model.WellFormedFormula;
import br.mia.parser.ParseException;
import br.mia.parser.Parser;
import br.mia.parser.SimpleNode;
import br.mia.parser.WffListBuilder;

public class Controller {

	private Parser parser;

	private WffListBuilder wffListBuilder = new WffListBuilder();

	private KnowledgeBase knowledgeBase = new KnowledgeBase();

	private WellFormedFormula currentProposition;

	// private ResolutionAlgorithm resolution = new ResolutionAlgorithm();

	private ClauseSet propositionClauses;

	public Controller() {
	}

	public List<WellFormedFormula> parse(String code) throws ParseException {
		parser = new Parser(new StringReader(code));
		SimpleNode root = parser.start();
		return wffListBuilder.buildWffList(root);
	}

	public void createKnowledgeBase(List<WellFormedFormula> wffList) {
		knowledgeBase.setBaseWffs(wffList);
	}

	public void setCurrentProposition(WellFormedFormula wff) {
		this.currentProposition = wff;
		SystemProver.out.println("\tCurrent Proposition: "
				+ this.currentProposition.toString());
		this.currentProposition.negate();
		SystemProver.out.println("\tProposition Negation: "
				+ this.currentProposition.toString());
		this.propositionClauses = ClauseExtractor.extract(currentProposition);
		SystemProver.out.println("\tProposition Clauses: "
				+ this.propositionClauses.toString());
	}

	public boolean proveCurrentProposition() {
		return ResolutionAlgorithm.execute(knowledgeBase.getBaseClauses(),
				ClauseExtractor.extract(this.currentProposition));
	}
}