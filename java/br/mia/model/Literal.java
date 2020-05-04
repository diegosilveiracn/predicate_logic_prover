package br.mia.model;

public class Literal {

	private Predicate predicate;

	public Literal() {

	}

	public Literal(Predicate predicate) {
		this.predicate = predicate;
	}

	public Predicate getPredicate() {
		return predicate;
	}

	public void setPredicate(Predicate predicate) {
		this.predicate = predicate;
	}
	
	public String toString() {
		return predicate.toString();
	}
	
}