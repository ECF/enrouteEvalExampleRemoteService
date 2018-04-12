package com.acme.prime.eval.api;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;
import org.osgi.framework.Version;
import org.osgi.util.promise.Promise;

/**
 * A service that evaluates an expression and returns the result
 */
public interface Eval {
	/**
	 * Evaluate an expression and return the result.
	 */
	Promise<Double> eval(String expression) throws Exception;
	
	CompletionStage<Boolean> evalBoolean(String booleanExpression) throws Exception;
	
	Future<Double> evalEx(EvalExpression expression) throws Exception;

	Version getVersion(String vString) throws Exception;
	
	Version[] getVersions(String... versionStrs) throws Exception;
	
	EvalDTO eval(EvalExpression expression) throws Exception;
	
	EvalDTO getDTO() throws Exception;
	
	CompletableFuture<EvalDTO[]> getDTOs() throws Exception;
}