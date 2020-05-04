package br.mia.model;

public class Constant extends Parameter {

	private static int counter = 0;

	public Constant(String id) {
		super(id);
	}

	public Constant(Constant constant) {
		super(constant.id);
	}

	static Constant createSkolemConstant() {
		return new Constant("_Sk_C" + counter++);
	}

	public boolean equals(Parameter parameter) {
		if (parameter instanceof Constant) {
			Constant constant = (Constant) parameter;
			return id.equals(constant.id);
		}
		return false;
	}

	public Parameter clone() {
		return new Constant(this);
	}

	public String toString() {
		return id;
	}

	@Override
	public Parameter getReplace(Parameter out, Parameter in) {
		return this.clone();
	}
}
