package com.acme.prime.eval.consumer.v2;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.osgi.framework.Version;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.acme.prime.eval.api.Eval;
import com.acme.prime.eval.api.EvalDTO;
import com.acme.prime.eval.api.EvalExpression;

@Component(immediate=true)
public class EvalConsumer {
	
	@Reference
	void bindEval(Eval svc) throws Exception {
		System.out.println("EvalConsumer.v2.bindEval");
		
		Version v = svc.getVersion("5.4.3.twoandone");
		System.out.println("getVersion="+v);
		
		Version[] vs = svc.getVersions("1.2.3.onetwothree","2.3.4.twothreefour","5.6.7.qualifier");
		System.out.println("getVersions="+Arrays.asList(vs));
		
		try {
			svc.eval("211+211").then(p -> {
				System.out.println("EvalConsumer.v2.eval returns="+p.getValue());
				return null;
			});
			
			svc.evalBoolean("true||false").whenComplete((r,t) -> {
				if (t != null)
					t.printStackTrace();
				else
					System.out.println("EvalConsumer.evalBoolean returns="+r);
			});
			
			CompletableFuture.runAsync(new Runnable() {
				@Override
				public void run() {
					Future<Double> f;
					try {
						f = svc.evalEx(new EvalExpression("222+222"));
						System.out.println("EvalConsumer.evalExpression returns="+f.get());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}});
			
			EvalDTO dto = svc.eval(new EvalExpression("444+222"));
			System.out.println("eval dto="+dto);
			
			dto = svc.getDTO();
			System.out.println("getDTO="+dto);
			
			svc.getDTOs().whenComplete((dtos,t) -> {
				if (t != null)
					t.printStackTrace();
				else
					System.out.println("getDTOs returns="+Arrays.asList(dtos));
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void unbindEval(Eval svc) {
		System.out.println("EvalConsumer.v2.unbindEval");
	}

}
