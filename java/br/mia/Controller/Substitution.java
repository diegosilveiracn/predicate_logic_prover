package br.mia.Controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.mia.model.Parameter;

public class Substitution {

	private List<Parameter> inList;

	private List<Parameter> outList;

	public Substitution() {
		this.inList = new ArrayList<Parameter>();
		this.outList = new ArrayList<Parameter>();
	}

	public void addSubstitution(Parameter in, Parameter out) {
		inList.add(in);
		outList.add(out);
	}

	public Iterator<InOut> iterator() {
		return new SubstitutionIterator(inList, outList);
	}
}

class SubstitutionIterator implements Iterator<InOut> {

	private Iterator<Parameter> iterIn;

	private Iterator<Parameter> iterOut;

	public SubstitutionIterator(List<Parameter> inList, List<Parameter> outList) {
		iterIn = inList.iterator();
		iterOut = outList.iterator();
	}

	public boolean hasNext() {
		return iterIn.hasNext();
	}

	public InOut next() {
		InOut inout = new InOut(iterIn.next(), iterOut.next());
		return inout;
	}

	public void remove() {
	}
}