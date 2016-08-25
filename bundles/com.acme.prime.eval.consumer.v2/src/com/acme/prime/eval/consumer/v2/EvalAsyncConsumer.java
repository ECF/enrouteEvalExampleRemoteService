package com.acme.prime.eval.consumer.v2;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;

import com.acme.prime.eval.api.EvalAsync;

@Component(immediate=true)
public class EvalAsyncConsumer {
	
	@Reference(policy=ReferencePolicy.DYNAMIC)
	void bindEval(EvalAsync svc) {
		System.out.println("EvalAsyncConsumer.v2.bindEval");
		try {
			svc.evalAsync("244+244").whenComplete((result,throwable) -> {
				if (throwable != null)
					throwable.printStackTrace();
				else System.out.println("EvalAsyncConsumer.v2 returns="+result);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			svc.evalBooleanAsync("true||false").whenComplete((result,throwable) -> {
				if (throwable != null)
					throwable.printStackTrace();
				else System.out.println("EvalAsyncConsumer.evalBoolean.v2 returns="+result);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void unbindEval(EvalAsync svc) {
		System.out.println("EvalAsyncConsumer.v2.unbindEval");
	}

}
