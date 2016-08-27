package com.acme.prime.eval.api;

import java.util.concurrent.CompletableFuture;

public interface EvalAsync {
	/**
	 * Evaluate an expression and return the result.
	 */
	CompletableFuture<Double> evalAsync(String expression);

	CompletableFuture<Boolean> evalBooleanAsync(String booleanExpression);
	
}
