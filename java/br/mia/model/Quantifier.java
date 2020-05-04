package br.mia.model;

public class Quantifier {

	public enum QuantifierType {
		FORALL, EXISTS
	};

	public static Quantifier FORALL = new Quantifier(QuantifierType.FORALL);
	
	public static Quantifier EXISTS = new Quantifier(QuantifierType.EXISTS);
	
	private QuantifierType type;

	public Quantifier(QuantifierType type) {
		this.type = type;
	}
	
	public Quantifier(Quantifier quantifier) {
		this.type = quantifier.type;
	}

	public QuantifierType getType() {
		return type;
	}

	public void changeQuantifier() {
		type = (type == QuantifierType.EXISTS ? QuantifierType.FORALL
				: QuantifierType.EXISTS);
	}
	
	public boolean equals(Quantifier quantifier) {
		return this.type == quantifier.type;
	}

	public Quantifier clone() {
		return new Quantifier(this);
	}
	
	public String toString() {
		switch (type) {
		case FORALL:
			return "forall";
		case EXISTS:
			return "exists";
		default:
			return "ERROR";
		}
	}

}
