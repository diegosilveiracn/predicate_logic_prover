package br.mia.model;

import java.util.LinkedList;
import java.util.List;

public class ScopeManager {

	// Stack of active scopes. Variables should be searched for only in these
	// scopes
	private static MyStack<QuantifiedFormula> scopeStack = new MyStack<QuantifiedFormula>();

	// Flag to indicate whether the variable of the top scope has changed.
	// private static boolean idChanged;

	// Stores the original id of changed variables.
	// private static String originalId;

	// Pushs a new scope onto the top of the scope stack
	public static void pushScope(QuantifiedFormula scope) {
		scopeStack.push(scope);
	}

	// Pops the top of the scope stack
	public static QuantifiedFormula popScope() {
		return scopeStack.pop();
	}

	// Searches the variable in the current scope stack.
	public static Variable searchVariable(String variableId) {
		for (QuantifiedFormula scope : scopeStack) {
			Variable currentVariable = scope.getVariable();
			if ((currentVariable.hasIdChanged() && currentVariable
					.getOriginalId().equals(variableId))
					|| currentVariable.getId().equals(variableId))
				return currentVariable;
		}
		return new Variable(variableId);
	}

	// Creates a new variable for the scope in the top of the stack
	public static void createVariable(String variableId) {
		QuantifiedFormula topScope = scopeStack.peek();
		Variable variable = new Variable(variableId);
		if (containsVariable(variable))
			variable.changeId();
		topScope.setVariable(variable);
	}

	// Checks weather the variable exists in the current scope stack
	public static boolean containsVariable(Variable variable) {
		for (QuantifiedFormula scope : scopeStack) {
			Variable scopeVarible = scope.getVariable();
			if (scopeVarible != null && variable.equals(scopeVarible))
				return true;
		}
		return false;
	}

	// Returns all the variables in the stack scope.
	// This is used to transform the propositions in Skolem Normal Form
	public static List<Variable> getAllVariables() {
		LinkedList<Variable> variableList = new LinkedList<Variable>();
		for (QuantifiedFormula scope : scopeStack)
			variableList.addFirst(scope.getVariable());
		return variableList;
	}

}