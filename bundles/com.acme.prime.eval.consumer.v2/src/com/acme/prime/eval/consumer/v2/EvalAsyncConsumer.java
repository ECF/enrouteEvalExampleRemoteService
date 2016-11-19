package com.acme.prime.eval.consumer.v2;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;

import com.acme.prime.eval.api.EvalAsync;
import com.acme.prime.eval.api.EvalExpression;

@Component(immediate=true)
public class EvalAsyncConsumer {
	
	@Reference(policy = ReferencePolicy.DYNAMIC)
	void bindEval(EvalAsync svc) {
		System.out.println("EvalAsyncConsumer.v2.bindEval");
		svc.evalAsync("244+244").whenComplete((result, throwable) -> {
			if (throwable != null)
				throwable.printStackTrace();
			else
				System.out.println("EvalAsyncConsumer.v2 returns=" + result);
		});
		svc.evalBooleanAsync("true||false").whenComplete((result, throwable) -> {
			if (throwable != null)
				throwable.printStackTrace();
			else
				System.out.println("EvalAsyncConsumer.evalBoolean.v2 returns=" + result);
		});
		svc.evalExAsync(new EvalExpression("288+288")).whenComplete((result, throwable) -> {
			if (throwable != null)
				throwable.printStackTrace();
			else
				System.out.println("EvalAsyncConsumer.v2 expression returns=" + result);
		});

	}
	
	void unbindEval(EvalAsync svc) {
		System.out.println("EvalAsyncConsumer.v2.unbindEval");
	}

}
