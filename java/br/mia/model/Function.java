package br.mia.model;

public class Function extends Parameter {

	private int arity;

	private Parameter[] parameters;

	private static int counter = 0;

	private void init(int arity) {
		this.arity = arity;
		parameters = new Parameter[arity];
	}

	public Function(String id, int arity) {
		super(id);
		init(arity);
	}

	public Function(Function function) {
		super(function.id);
		init(function.arity);
		for (int i = 0; i < arity; i++)
			this.parameters[i] = function.parameters[i].clone();
	}

	public int getArity() {
		return arity;
	}

	public boolean setParameter(Parameter parameter, int index) {
		if (index >= arity)
			return false;
		parameters[index] = parameter;
		return true;
	}

	static Function createSkolemFunction(int arity) {
		return new Function("_Sk_f" + counter++, arity);
	}

	public Parameter clone() {
		return new Function(this);
	}

	public boolean equals(Parameter parameter) {
		if (parameter instanceof Function) {
			Function function = (Function) parameter;
			return id.equals(function.id);
		}
		return false;
	}

	public String toString() {
		StringBuffer params = new StringBuffer();
		for (int i = 0; i < arity - 1; i++)
			params.append(parameters[i].toString() + ", ");
		params.append(parameters[arity - 1].toString());
		return id + "(" + params + ")";
	}

	@Override
	public Parameter getReplace(Parameter out, Parameter in) {
		for (int i = 0; i < this.getArity(); i++) {
			this.parameters[i] = this.parameters[i].getReplace(out, in);
		}
		return this.clone();
	}

	public Parameter[] getParameters() {
		return parameters;
	}
}