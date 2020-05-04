package br.mia.model;

import java.util.List;

public class QuantifiedFormula extends WellFormedFormula {

	private Quantifier quantifier;

	private Variable variable;

	private WellFormedFormula wff;

	public QuantifiedFormula() {
		super();
	}

	public QuantifiedFormula(Quantifier quantifier, Variable variable, WellFormedFormula wff) {
		setQuantifier(quantifier);
		setVariable(variable);
		setWff(wff);
	}
	
	public QuantifiedFormula(QuantifiedFormula quantifiedFormula) {
		super(quantifiedFormula);
		setQuantifier(quantifiedFormula.quantifier);
		setVariable(quantifiedFormula.variable);
		setWff(quantifiedFormula.wff);
	}

	public Quantifier getQuantifier() {
		return quantifier;
	}

	public void setQuantifier(Quantifier quantifier) {
		this.quantifier = quantifier.clone();
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
		this.variable.setScope(this);
	}
	
	public boolean hasVariableIdChanged() {
		return variable.hasIdChanged();
	}

	public WellFormedFormula getWff() {
		return wff;
	}

	public void setWff(WellFormedFormula wff) {
		this.wff = wff.clone();
	}

	@Override
	public void negate() {
		if (negated) {
			negated = Boolean.FALSE;
			return;
		}
		quantifier.changeQuantifier();
		wff.negate();
	}

	@Override
	public WellFormedFormula clone() {
		QuantifiedFormula clone = new QuantifiedFormula(this);
		//clone.updateVariableRef(clone.variable);
		clone.swapParameters(this.variable, new Variable(this.variable));
		return clone;
	}

	@Override
	public boolean containsVariable(Variable variable) {
		if (this.variable.equals(variable))
			return true;
		return this.wff.containsVariable(variable);
	}

	@Override
	public WellFormedFormula toPrenexNormalForm() {
		QuantifiedFormula prenexFormula = new QuantifiedFormula(this);
		if (prenexFormula.negated) {
			prenexFormula.negated = false;
			prenexFormula.negate();
		}
		prenexFormula.wff = prenexFormula.wff.toPrenexNormalForm();
		return prenexFormula;
	}

	@Override
	public WellFormedFormula toSkolemNormalForm() {
		QuantifiedFormula prenexFormula = (QuantifiedFormula) toPrenexNormalForm();
		if (prenexFormula.quantifier.equals(Quantifier.FORALL)) {
			ScopeManager.pushScope(prenexFormula);
			prenexFormula.wff = prenexFormula.wff.toSkolemNormalForm();
			ScopeManager.popScope();
			return prenexFormula;
		} else {
			List<Variable> variables = ScopeManager.getAllVariables();
			Parameter parameter;
			if (variables.isEmpty()) {
				parameter = Constant.createSkolemConstant();
			} else {
				Function function = Function.createSkolemFunction(variables.size());
				int index = 0;
				for (Variable variable : variables)
					function.setParameter(variable, index++);
				parameter = function;
			}
			WellFormedFormula skolemFormula = prenexFormula.wff;
			skolemFormula.swapParameters(this.variable, parameter);
			return skolemFormula.toSkolemNormalForm();
		}
	}
	
	@Override
	public WellFormedFormula toConjunctiveNormalForm() {
		WellFormedFormula skolemFormula = this.toSkolemNormalForm();
		if (skolemFormula instanceof QuantifiedFormula) {
			QuantifiedFormula conjunctiveFormula = (QuantifiedFormula) skolemFormula;
			conjunctiveFormula.wff = conjunctiveFormula.wff.toConjunctiveNormalForm();
			return conjunctiveFormula;
		} else
			return skolemFormula.toConjunctiveNormalForm();
	}

	@Override
	public List<Variable> getFreeVariables() {
		return wff.getFreeVariables();
	}
	
	@Override
	public void swapParameters(Parameter out, Parameter in) {
		if (in instanceof Variable && variable.equals(out))
			variable = (Variable) in;
		wff.swapParameters(out, in);
	}
	
	/*@Override
	public void updateVariableRef(Variable variable) {
		wff.updateVariableRef(variable);
	}*/

	public String toString() {
		String str = "(" + quantifier + " " + variable.getId() + " " + wff + ")";
		return (negated ? "~" + str : str);
	}

}
