package com.acme.prime.eval.consumer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;

import com.acme.prime.eval.api.Eval;

@Component(immediate=true)
public class EvalConsumer {
	
	@Reference(policy=ReferencePolicy.DYNAMIC)
	void bindEval(Eval svc) {
		System.out.println("EvalConsumer.bindEval");
		try {
			System.out.println("EvalConsumer returns="+svc.eval("21+21"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void unbindEval(Eval svc) {
		System.out.println("EvalConsumer.unbindEval");
	}

}
