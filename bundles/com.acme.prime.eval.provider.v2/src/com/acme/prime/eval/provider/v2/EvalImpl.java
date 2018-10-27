package com.acme.prime.eval.provider.v2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;

import org.osgi.framework.Version;
import org.osgi.service.component.annotations.Component;
import org.osgi.util.promise.Deferred;
import org.osgi.util.promise.Promise;

import com.acme.prime.eval.api.Eval;
import com.acme.prime.eval.api.EvalDTO;
import com.acme.prime.eval.api.EvalExpression;
import com.fathzer.soft.javaluator.DoubleEvaluator;

//"ecf.r_osgi.peer.id=r-osgi://127.0.0.1",

@Component(immediate=true, property = { "service.exported.interfaces=*", "service.exported.intents=osgi.basic", "service.exported.intents=osgi.async"})
public class EvalImpl implements Eval {
	
	private final DoubleEvaluator evaluator;
	private final SimpleBooleanEvaluator booleanEvaluator;
	
	public EvalImpl() {
		evaluator = new DoubleEvaluator();
		booleanEvaluator = new SimpleBooleanEvaluator();
	}
	
	@Override
	public Promise<Double> eval(String expression) throws Exception {
		Deferred<Double> d = new Deferred<Double>();
		d.resolve(evaluator.evaluate(expression));
		return d.getPromise();
	}
	
	@Override
	public CompletionStage<Boolean> evalBoolean(String booleanExpression) throws Exception {
		CompletableFuture<Boolean> cf = new CompletableFuture<Boolean>();
		cf.complete(booleanEvaluator.evaluate(booleanExpression));
		return cf;
	}
	
	@Override
	public Future<Double> evalEx(EvalExpression expression) throws Exception {
		CompletableFuture<Double> cf = new CompletableFuture<Double>();
		cf.complete(evaluator.evaluate(expression.getExpression()));
		return cf;
	}

	@Override
	public Version getVersion(String ver) throws Exception {
		return Version.valueOf(ver);
	}

	@Override
	public Version[] getVersions(String... versionStrs) throws Exception {
		List<Version> results = new ArrayList<Version>(versionStrs.length);
		for(String v: versionStrs)
			results.add(Version.valueOf(v));
		return results.toArray(new Version[results.size()]);
	}

	@Override
	public EvalDTO getDTO() throws Exception {
		EvalDTO d = new EvalDTO();
		d.val = "foo";
		d.d = 20;
		d.eval = null;
		return d;
	}

	@Override
	public CompletableFuture<EvalDTO[]> getDTOs() throws Exception {
		CompletableFuture<EvalDTO[]> cf = new CompletableFuture<EvalDTO[]>();
		EvalDTO d = new EvalDTO();
		d.val = "foo";
		d.d = 20;
		d.eval = new EvalDTO();
		cf.complete(new EvalDTO[] { d, d });
		return cf;
	}

	@Override
	public EvalDTO eval(EvalExpression expression) throws Exception {
		double result = evaluator.evaluate(expression.getExpression());
		EvalDTO d = new EvalDTO();
		d.d = result;
		d.val = expression.getExpression();
		return d;
	}
	
}
