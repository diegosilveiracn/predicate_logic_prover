package br.mia.Controller;

import br.mia.model.Parameter;

public class InOut {

	private Parameter in;

	private Parameter out;

	public InOut(Parameter in, Parameter out) {
		this.in = in;
		this.out = out;
	}

	public Parameter getIn() {
		return this.in;
	}

	public Parameter getOut() {
		return this.out;
	}
}