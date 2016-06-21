package com.acme.prime.eval.consumer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;

import com.acme.prime.eval.api.EvalAsync;

@Component(immediate=true)
public class EvalAsyncConsumer {
	
	@Reference(policy=ReferencePolicy.DYNAMIC)
	void bindEval(EvalAsync svc) {
		System.out.println("EvalAsyncConsumer.bindEval");
		try {
			svc.evalAsync("24+24").whenComplete((result,throwable) -> {
				if (throwable != null)
					throwable.printStackTrace();
				else System.out.println("EvalAsyncConsumer returns="+result);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void unbindEval(EvalAsync svc) {
		System.out.println("EvalAsyncConsumer.unbindEval");
	}

}
