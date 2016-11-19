package com.acme.prime.eval.api;
/**
 * A service that evaluates an expression and returns the result
 */
public interface Eval {
	/**
	 * Evaluate an expression and return the result.
	 */
	double eval(String expression) throws Exception;
	
	boolean evalBoolean(String booleanExpression) throws Exception;
	
	double evalEx(EvalExpression expression) throws Exception;

}