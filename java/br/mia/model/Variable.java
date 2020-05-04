package br.mia.model;

public class Variable extends Parameter {

	private static final String PREFIX = "_";

	private int counter = 0;

	private QuantifiedFormula scope;

	private boolean idChanged = false;

	private String originalId;

	public Variable(String id) {
		super(id);
		originalId = id;
	}

	public Variable(Variable variable) {
		super(variable.id);
		this.scope = variable.scope;
		this.originalId = variable.originalId;
		this.idChanged = variable.idChanged;
		this.counter = variable.counter;
	}

	public void changeId() {
		if (idChanged) {
			super.id = PREFIX + originalId + counter++;
		} else {
			originalId = super.id;
			super.id = PREFIX + originalId + counter++;
			idChanged = true;
		}
	}

	public boolean hasIdChanged() {
		return idChanged;
	}

	public String getOriginalId() {
		return originalId;
	}

	public QuantifiedFormula getScope() {
		return scope;
	}

	protected void setScope(QuantifiedFormula scope) {
		this.scope = scope;
	}

	public boolean isFree() {
		return scope == null;
	}

	public Parameter clone() {
		return this;
	}

	public boolean equals(Parameter parameter) {
		if (parameter instanceof Variable) {
			Variable variable = (Variable) parameter;
			return id.equals(variable.id);
		}
		return false;
	}

	public String toString() {
		return id;
	}

	@Override
	public Parameter getReplace(Parameter out, Parameter in) {
		if (this.equals(in)) {
			if (out instanceof Constant) {
				return (Constant) out.clone();
			} else if (out instanceof Function) {
				return (Function) out.clone();
			} else {
				return (Variable) out.clone();
			}
		}
		return this.clone();
	}
}