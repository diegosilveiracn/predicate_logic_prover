package br.mia.model;

public class Connective {

	public enum ConnectiveType {AND, OR, IMPLICATION, BICONDITIONAL};
	
	private ConnectiveType type;

	public static final Connective AND = new Connective(ConnectiveType.AND);
	
	public static final Connective OR = new Connective(ConnectiveType.OR);
	
	public static final Connective IMPLICATION = new Connective(ConnectiveType.IMPLICATION);
	
	public static final Connective BICONDITIONAL = new Connective(ConnectiveType.BICONDITIONAL);
	
	public Connective(ConnectiveType type) {
		this.type = type;
	}
	
	public Connective(Connective connective) {
		this.type = connective.type;
	}

	public ConnectiveType getType() {
		return type;
	}

	public boolean equals(Connective connective) {
		return this.type == connective.type;
	}
	
	public Connective clone() {
		return new Connective(this);
	}
	
	public String toString() {
		switch (type) {
		case AND:			return "&&";
		case OR:			return "||";
		case IMPLICATION:	return "->";
		case BICONDITIONAL:	return "<->";
		default:			return "ERROR";
		}
	}
	
}
