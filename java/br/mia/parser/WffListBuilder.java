package br.mia.parser;

import java.util.LinkedList;
import java.util.List;

import br.mia.model.AtomicFormula;
import br.mia.model.Connective;
import br.mia.model.ConnectiveFormula;
import br.mia.model.Constant;
import br.mia.model.Function;
import br.mia.model.Parameter;
import br.mia.model.Predicate;
import br.mia.model.QuantifiedFormula;
import br.mia.model.Quantifier;
import br.mia.model.ScopeManager;
import br.mia.model.WellFormedFormula;

public class WffListBuilder {

	public WffListBuilder() {
	}

	public List<WellFormedFormula> buildWffList(Node root) {
		List<WellFormedFormula> wffList = new LinkedList<WellFormedFormula>();
		traverseTree(root, wffList);
		return wffList;
	}

	private void traverseTree(Node node, List<WellFormedFormula> wffList) {
		if (((SimpleNode) node).id == ParserTreeConstants.JJTWFF) {
			wffList.add(buildWff(node));
		} else {
			int numChildren = node.jjtGetNumChildren();
			for (int i = 0; i < numChildren; i++)
				traverseTree(node.jjtGetChild(i), wffList);
		}
	}

	private WellFormedFormula buildWff(Node wffNode) {
		Token token = null;
		SimpleNode firstChild = (SimpleNode) wffNode.jjtGetChild(0);
		switch (wffNode.jjtGetNumChildren()) {
		case 1: {
			AtomicFormula atomicFormula = new AtomicFormula();
			atomicFormula.setPredicate(buildPredicate(firstChild));
			return atomicFormula; }
		case 2:
			WellFormedFormula wff = buildWff(wffNode.jjtGetChild(1));
			wff.setNegated(true);
			return wff;
		case 3:
			switch (firstChild.id) {
			case ParserTreeConstants.JJTQUANTIFIER:
				token = firstChild.getToken();
				QuantifiedFormula quantifiedFormula = new QuantifiedFormula();
				ScopeManager.pushScope(quantifiedFormula);
				switch (token.kind) {
				case ParserConstants.FORALL:
					quantifiedFormula.setQuantifier(Quantifier.FORALL);
					break;
				case ParserConstants.EXISTS:
					quantifiedFormula.setQuantifier(Quantifier.EXISTS);
					break;
				}
				token = ((SimpleNode) wffNode.jjtGetChild(1)).getToken();
				ScopeManager.createVariable(token.image);
				quantifiedFormula.setWff(buildWff(wffNode.jjtGetChild(2)));
				ScopeManager.popScope();
				return quantifiedFormula;
			case ParserTreeConstants.JJTPRED: {
				ConnectiveFormula connectiveFormula = new ConnectiveFormula();
				token = ((SimpleNode) wffNode.jjtGetChild(1)).getToken();
				switch (token.kind) {
				case ParserConstants.AND:
					connectiveFormula.setConnective(Connective.AND);
					break;
				case ParserConstants.OR:
					connectiveFormula.setConnective(Connective.OR);
					break;
				case ParserConstants.IMPLICATION:
					connectiveFormula.setConnective(Connective.IMPLICATION);
					break;
				case ParserConstants.BICONDITIONAL:
					connectiveFormula.setConnective(Connective.BICONDITIONAL);
					break;
				}
				AtomicFormula atomicFormula = new AtomicFormula();
				atomicFormula.setPredicate(buildPredicate(firstChild));
				connectiveFormula.setLeftWff(atomicFormula);
				connectiveFormula.setRightWff(buildWff(wffNode.jjtGetChild(2)));
				return connectiveFormula; }
			case ParserTreeConstants.JJTWFF: {
				ConnectiveFormula connectiveFormula = new ConnectiveFormula();
				token = ((SimpleNode) wffNode.jjtGetChild(1)).getToken();
				switch (token.kind) {
				case ParserConstants.AND:
					connectiveFormula.setConnective(Connective.AND);
					break;
				case ParserConstants.OR:
					connectiveFormula.setConnective(Connective.OR);
					break;
				case ParserConstants.IMPLICATION:
					connectiveFormula.setConnective(Connective.IMPLICATION);
					break;
				case ParserConstants.BICONDITIONAL:
					connectiveFormula.setConnective(Connective.BICONDITIONAL);
					break;
				default:
					break;
				}
				connectiveFormula.setLeftWff(buildWff(firstChild));
				connectiveFormula.setRightWff(buildWff(wffNode.jjtGetChild(2)));
				return connectiveFormula; }
			}
			default:
				return null;
		}
	}

	private Predicate buildPredicate(Node predNode) {
		SimpleNode firstChild = (SimpleNode) predNode.jjtGetChild(0);
		switch (firstChild.id) {
		case ParserTreeConstants.JJTPARAMLIST:
			List<Parameter> paramList = buildParameterList(predNode
					.jjtGetChild(0));
			Token token = ((SimpleNode) predNode).getToken();
			Predicate predicate = new Predicate(token.image, paramList.size());
			int index = 0;
			for (Parameter parameter : paramList)
				predicate.setParameter(parameter, index++);
			return predicate;			
		case ParserTreeConstants.JJTPARAM:
			Predicate equal = Predicate.createEqualPredicate();
			equal.setParameter(buildParameter(firstChild), 0);
			equal.setParameter(buildParameter(predNode.jjtGetChild(1)), 1);
			return equal;
		default:
			return null;
		}
	}
	
	private List<Parameter> buildParameterList(Node paramListNode) {
		List<Parameter> parameterList = new LinkedList<Parameter>();
		int numChild = paramListNode.jjtGetNumChildren();
		for (int i = 0; i < numChild; i++) {
			Parameter parameter = buildParameter(paramListNode.jjtGetChild(i));
			parameterList.add(parameter);
		}
		return parameterList;
	}

	private Parameter buildParameter(Node paramNode) {
		SimpleNode paramSimpleNode = (SimpleNode) paramNode.jjtGetChild(0);
		Token token = paramSimpleNode.getToken();
		switch (paramSimpleNode.id) {
		case ParserTreeConstants.JJTVAR:
			return ScopeManager.searchVariable(token.image);
		case ParserTreeConstants.JJT_CONST:
			return new Constant(token.image);
		case ParserTreeConstants.JJTFUNC:
			List<Parameter> paramList = buildParameterList(paramSimpleNode
					.jjtGetChild(0));
			Function function = new Function(token.image, paramList.size());
			int index = 0;
			for (Parameter param : paramList)
				function.setParameter(param, index++);
			return function;
		}
		return null;
	}

}
