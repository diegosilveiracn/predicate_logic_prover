package br.mia.model;

import java.util.List;

public class ConnectiveFormula extends WellFormedFormula {

	private WellFormedFormula leftWff;

	private Connective connective;

	private WellFormedFormula rightWff;

	public ConnectiveFormula() {
		super();
	}

	public ConnectiveFormula(WellFormedFormula leftWff, Connective connective,
			WellFormedFormula rightWff) {
		setLeftWff(leftWff);
		setConnective(connective);
		setRightWff(rightWff);
	}

	public ConnectiveFormula(ConnectiveFormula connectiveFormula) {
		super(connectiveFormula);
		setLeftWff(connectiveFormula.leftWff);
		setConnective(connectiveFormula.connective);
		setRightWff(connectiveFormula.rightWff);
	}

	public Connective getConnective() {
		return connective;
	}

	public void setConnective(Connective connective) {
		this.connective = connective.clone();
	}

	public WellFormedFormula getLeftWff() {
		return leftWff;
	}

	public void setLeftWff(WellFormedFormula wff) {
		leftWff = wff.clone();
	}

	public WellFormedFormula getRightWff() {
		return rightWff;
	}

	public void setRightWff(WellFormedFormula wff) {
		rightWff = wff.clone();
	}

	@Override
	public void negate() {
		if (negated) {
			negated = Boolean.FALSE;
			return;
		}
		switch (connective.getType()) {
		case AND:
			leftWff.negate();
			connective = Connective.OR;
			rightWff.negate();
			break;
		case OR:
			leftWff.negate();
			connective = Connective.AND;
			rightWff.negate();
			break;
		case IMPLICATION:
			connective = Connective.AND;
			rightWff.negate();
			break;
		case BICONDITIONAL:
			ConnectiveFormula left = (ConnectiveFormula) this.clone();
			left.setConnective(Connective.OR);
			ConnectiveFormula right = (ConnectiveFormula) this.clone();
			right.setConnective(Connective.OR);
			right.leftWff.negate();
			right.rightWff.negate();
			this.leftWff = left;
			this.connective = Connective.AND;
			this.rightWff = right;
			break;
		}
	}

	@Override
	public WellFormedFormula clone() {
		return new ConnectiveFormula(this);
	}

	@Override
	public boolean containsVariable(Variable variable) {
		return leftWff.containsVariable(variable)
				|| rightWff.containsVariable(variable);
	}

	private void updateVariable(WellFormedFormula wff) {
		if (wff instanceof QuantifiedFormula) {
			QuantifiedFormula quantifiedFormula = (QuantifiedFormula) wff;
			Variable oldVariable = quantifiedFormula.getVariable();
			quantifiedFormula.swapParameters(oldVariable, new Variable(
					oldVariable));
		}
	}

	private ConnectiveFormula removeImplications() {
		switch (connective.getType()) {
		case IMPLICATION: {
			ConnectiveFormula connectiveFormula = (ConnectiveFormula) this
					.clone();
			connectiveFormula.leftWff.negate();
			connectiveFormula.setConnective(Connective.OR);
			return connectiveFormula;
		}
		case BICONDITIONAL: {
			ConnectiveFormula left = new ConnectiveFormula(this.leftWff,
					Connective.IMPLICATION, this.rightWff);
			ConnectiveFormula right = new ConnectiveFormula(this.rightWff,
					Connective.IMPLICATION, this.leftWff);

			updateVariable(right.leftWff);
			updateVariable(right.rightWff);

			ConnectiveFormula connectiveFormula = new ConnectiveFormula(left
					.removeImplications(), Connective.AND, right
					.removeImplications());
			return connectiveFormula;
		}
		default:
			return (ConnectiveFormula) this.clone();
		}
	}

	@Override
	public WellFormedFormula toPrenexNormalForm() {
		ConnectiveFormula connectiveFormula = (ConnectiveFormula) this.clone();
		if (connectiveFormula.negated) {
			connectiveFormula.negated = Boolean.FALSE;
			connectiveFormula.negate();
		}
		connectiveFormula = connectiveFormula.removeImplications();
		WellFormedFormula leftWff = connectiveFormula.leftWff
				.toPrenexNormalForm();
		WellFormedFormula rightWff = connectiveFormula.rightWff
				.toPrenexNormalForm();

		// Rules 3.1.a and 3.1.b
		if (leftWff instanceof QuantifiedFormula
				&& !(rightWff instanceof QuantifiedFormula)
				&& !rightWff.containsVariable(((QuantifiedFormula) leftWff)
						.getVariable())) {
			QuantifiedFormula leftQf = (QuantifiedFormula) leftWff;
			ConnectiveFormula wff = new ConnectiveFormula();
			wff.setLeftWff(leftQf.getWff());
			wff.setConnective(connectiveFormula.connective);
			wff.setRightWff(rightWff);
			leftQf.setWff(wff.toPrenexNormalForm());
			return leftQf;
		}
		if (rightWff instanceof QuantifiedFormula
				&& !(leftWff instanceof QuantifiedFormula)
				&& !leftWff.containsVariable(((QuantifiedFormula) rightWff)
						.getVariable())) {
			QuantifiedFormula rightQf = (QuantifiedFormula) rightWff;
			ConnectiveFormula wff = new ConnectiveFormula();
			wff.setLeftWff(leftWff);
			wff.setConnective(connectiveFormula.connective);
			wff.setRightWff(rightQf.getWff());
			rightQf.setWff(wff.toPrenexNormalForm());
			return rightQf;
		}

		// Rule 3.3a
		if (leftWff instanceof QuantifiedFormula
				&& ((QuantifiedFormula) leftWff).getQuantifier().equals(
						Quantifier.FORALL)
				&& rightWff instanceof QuantifiedFormula
				&& ((QuantifiedFormula) rightWff).getQuantifier().equals(
						Quantifier.FORALL)
				&& (((QuantifiedFormula) leftWff).getVariable()
						.equals(((QuantifiedFormula) rightWff).getVariable()))
				&& connectiveFormula.connective.equals(Connective.AND)) {
			ConnectiveFormula wff = new ConnectiveFormula();
			QuantifiedFormula leftQf = (QuantifiedFormula) leftWff;
			wff.setLeftWff(leftQf.getWff());
			Variable variable = leftQf.getVariable();
			wff.setConnective(connectiveFormula.connective);
			wff.setRightWff(((QuantifiedFormula) rightWff).getWff());
			// wff.rightWff.updateVariableRef(variable);
			wff.rightWff.swapParameters(((QuantifiedFormula) rightWff)
					.getVariable(), leftQf.getVariable());
			QuantifiedFormula qform = new QuantifiedFormula();
			qform.setQuantifier(Quantifier.FORALL);
			qform.setVariable(variable);
			qform.setWff(wff.toPrenexNormalForm());
			return qform;
		}

		// Rule 3.3b
		if (leftWff instanceof QuantifiedFormula
				&& ((QuantifiedFormula) leftWff).getQuantifier().equals(
						Quantifier.EXISTS)
				&& rightWff instanceof QuantifiedFormula
				&& ((QuantifiedFormula) rightWff).getQuantifier().equals(
						Quantifier.EXISTS)
				&& (((QuantifiedFormula) leftWff).getVariable()
						.equals(((QuantifiedFormula) rightWff).getVariable()))
				&& connectiveFormula.connective.equals(Connective.OR)) {
			ConnectiveFormula wff = new ConnectiveFormula();
			QuantifiedFormula leftQf = (QuantifiedFormula) leftWff;
			wff.setLeftWff(leftQf.getWff());
			Variable variable = leftQf.getVariable();
			wff.setConnective(connectiveFormula.connective);
			wff.setRightWff(((QuantifiedFormula) rightWff).getWff());
			// wff.rightWff.updateVariableRef(variable);
			QuantifiedFormula qform = new QuantifiedFormula();
			qform.setQuantifier(Quantifier.EXISTS);
			qform.setVariable(variable);
			qform.setWff(wff.toPrenexNormalForm());
			qform.getWff().swapParameters(((QuantifiedFormula) rightWff)
					.getVariable(), qform.getVariable());
			return qform;
		}

		// Rule 3.4a and Rule 3.4b
		if (leftWff instanceof QuantifiedFormula
				&& rightWff instanceof QuantifiedFormula) {
			QuantifiedFormula leftQf = (QuantifiedFormula) leftWff;
			QuantifiedFormula rightQf = (QuantifiedFormula) rightWff;
			Variable rightVariable = rightQf.getVariable();
			if (leftQf.getVariable().equals(rightVariable))
				rightVariable.changeId();
			ConnectiveFormula wff = new ConnectiveFormula();
			wff.setLeftWff(leftQf.getWff());
			wff.setConnective(connectiveFormula.connective);
			wff.setRightWff(rightQf.getWff());
			rightQf.setWff(wff.toPrenexNormalForm());
			leftQf.setWff(rightQf);
			return leftQf;
		}

		ConnectiveFormula prenexFormula = new ConnectiveFormula();
		prenexFormula.setLeftWff(leftWff);
		prenexFormula.setConnective(connectiveFormula.connective);
		prenexFormula.setRightWff(rightWff);
		return prenexFormula;
	}

	@Override
	public WellFormedFormula toSkolemNormalForm() {
		WellFormedFormula prenexFormula = this.toPrenexNormalForm();
		if (prenexFormula instanceof ConnectiveFormula) {
			ConnectiveFormula skolemFormula = (ConnectiveFormula) prenexFormula;
			skolemFormula.leftWff = skolemFormula.leftWff.toSkolemNormalForm();
			skolemFormula.rightWff = skolemFormula.rightWff
					.toSkolemNormalForm();
			return skolemFormula;
		} else
			return prenexFormula.toSkolemNormalForm();
	}

	private ConnectiveFormula DistributeOR() {
		if (!connective.equals(Connective.OR))
			return (ConnectiveFormula) this.clone();

		if (this.leftWff instanceof ConnectiveFormula
				&& ((ConnectiveFormula) this.leftWff).connective
						.equals(Connective.AND)) {
			ConnectiveFormula left = (ConnectiveFormula) this.leftWff.clone();
			ConnectiveFormula right = new ConnectiveFormula(left.rightWff,
					Connective.OR, this.rightWff);
			left.setConnective(Connective.OR);
			left.setRightWff(this.rightWff);
			return new ConnectiveFormula(left.DistributeOR(), Connective.AND,
					right.DistributeOR());
		} else if (this.rightWff instanceof ConnectiveFormula
				&& ((ConnectiveFormula) this.rightWff).connective
						.equals(Connective.AND)) {
			ConnectiveFormula right = (ConnectiveFormula) this.rightWff.clone();
			ConnectiveFormula left = new ConnectiveFormula(this.leftWff,
					Connective.OR, right.leftWff);
			right.setLeftWff(this.leftWff);
			right.setConnective(Connective.OR);
			return new ConnectiveFormula(left.DistributeOR(), Connective.AND,
					right.DistributeOR());
		}

		return (ConnectiveFormula) this.clone();
	}

	@Override
	public WellFormedFormula toConjunctiveNormalForm() {
		WellFormedFormula skolemFormula = this.toSkolemNormalForm();
		if (skolemFormula instanceof ConnectiveFormula) {
			ConnectiveFormula conjunctiveFormula = (ConnectiveFormula) skolemFormula;
			conjunctiveFormula.leftWff = conjunctiveFormula.leftWff
					.toConjunctiveNormalForm();
			conjunctiveFormula.rightWff = conjunctiveFormula.rightWff
					.toConjunctiveNormalForm();
			return conjunctiveFormula.DistributeOR();
		} else
			return skolemFormula.toConjunctiveNormalForm();
	}

	@Override
	public List<Variable> getFreeVariables() {
		List<Variable> freeVariableList = leftWff.getFreeVariables();
		freeVariableList.addAll(rightWff.getFreeVariables());
		return freeVariableList;
	}

	/*
	 * @Override public void updateVariableRef(Variable variable) {
	 * leftWff.updateVariableRef(variable);
	 * rightWff.updateVariableRef(variable); }
	 */

	@Override
	public void swapParameters(Parameter out, Parameter in) {
		leftWff.swapParameters(out, in);
		rightWff.swapParameters(out, in);
	}

	public String toString() {
		String str = "(" + leftWff + " " + connective + " " + rightWff + ")";
		return (negated ? "~" + str : str);
	}

}
