package com.acme.prime.eval.consumer.v2;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;

import com.acme.prime.eval.api.Eval;
import com.acme.prime.eval.api.EvalExpression;

@Component(immediate=true)
public class EvalConsumer {
	
	@Reference(policy=ReferencePolicy.DYNAMIC)
	void bindEval(Eval svc) {
		System.out.println("EvalConsumer.v2.bindEval");
		try {
			System.out.println("EvalConsumer.v2.eval returns="+svc.eval("211+211"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println("EvalConsumer.evalBoolean returns="+svc.evalBoolean("true||false"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println("EvalConsumer.evalExpression returns="+svc.evalEx(new EvalExpression("222+222")));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	void unbindEval(Eval svc) {
		System.out.println("EvalConsumer.v2.unbindEval");
	}

}
