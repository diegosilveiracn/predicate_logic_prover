package br.mia.model;

public abstract class Parameter {

	protected String id;

	public Parameter(String id) {
		this.id = new String(id);
	}

	public abstract Parameter clone();

	public abstract boolean equals(Parameter parameter);

	public abstract Parameter getReplace(Parameter out, Parameter in);

	public String getId() {
		return id;
	}
}