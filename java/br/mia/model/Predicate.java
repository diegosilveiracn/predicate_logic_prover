package br.mia.model;

import java.util.LinkedList;
import java.util.List;

public class Predicate {

	String id;

	private int arity;

	protected Parameter[] parameters;

	private void init(String id, int arity) {
		this.id = new String(id);
		this.arity = arity;
		parameters = new Parameter[arity];
	}

	public Predicate(String id, int arity) {
		init(id, arity);
	}

	public Predicate(Predicate predicate) {
		init(predicate.id, predicate.arity);
		for (int i = 0; i < arity; i++)
			this.parameters[i] = predicate.parameters[i].clone();
	}

	public int getArity() {
		return arity;
	}

	public String getId() {
		return id;
	}

	public Parameter getParameter(int index) {
		if (index >= arity)
			return null;
		return parameters[index];
	}

	public Parameter[] getParemeters() {
		return this.parameters;
	}

	public boolean setParameter(Parameter parameter, int index) {
		if (index >= arity)
			return false;
		parameters[index] = parameter;
		return true;
	}

	public boolean containsVariable(Variable variable) {
		for (int i = 0; i < arity; i++) {
			if (parameters[i].equals(variable))
				return true;
		}
		return false;
	}

	public List<Variable> getFreeVariables() {
		List<Variable> freeVariableList = new LinkedList<Variable>();
		for (int i = 0; i < arity; i++) {
			if (parameters[i] instanceof Variable) {
				Variable variable = (Variable) parameters[i];
				if (variable.isFree())
					freeVariableList.add(variable);
			}
		}
		return freeVariableList;
	}

	public boolean equals(Predicate predicate) {
		return id.equals(predicate.id);
	}

	public Predicate clone() {
		return new Predicate(this);
	}

	/*
	 * public void updateVariableRef(Variable variable) { for (int i = 0; i <
	 * arity; i++) { if (parameters[i].equals(variable)) parameters[i] =
	 * variable; } }
	 */

	public void swapParameters(Parameter out, Parameter in) {
		for (int i = 0; i < arity; i++)
			if (parameters[i].equals(out))
				parameters[i] = in;
	}

	public String toString() {
		StringBuffer params = new StringBuffer();
		for (int i = 0; i < arity - 1; i++)
			params.append(parameters[i].toString() + ", ");
		params.append(parameters[arity - 1].toString());
		return id + "(" + params + ")";
	}

	public boolean equalsParams(Predicate predicate) {

		boolean iqual = Boolean.TRUE;
		if (this.parameters.length == predicate.getArity())
			for (int i = 0; i < this.parameters.length; i++) {
				if (!this.parameters[i].equals(predicate.getParameter(i))) {
					iqual = Boolean.FALSE;
					break;
				}
			}
		else
			iqual = Boolean.FALSE;

		return iqual;
	}

	public static Predicate createEqualPredicate() {
		return new EqualPredicate();
	}

}

class EqualPredicate extends Predicate {

	public EqualPredicate() {
		super("=", 2);
	}

	protected EqualPredicate(EqualPredicate predicate) {
		super(predicate);
	}

	public Predicate clone() {
		return new EqualPredicate(this);
	}

	public String toString() {
		return "(" + getParameter(0) + " = " + getParameter(1) + ")";
	}
}
