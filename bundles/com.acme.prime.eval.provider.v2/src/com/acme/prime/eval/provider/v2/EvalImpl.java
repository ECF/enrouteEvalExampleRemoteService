package com.acme.prime.eval.provider.v2;

import org.eclipse.ecf.osgi.services.remoteserviceadmin.DebugRemoteServiceAdminListener;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.remoteserviceadmin.RemoteServiceAdminListener;

import com.acme.prime.eval.api.Eval;
import com.fathzer.soft.javaluator.DoubleEvaluator;

@Component(immediate=true, 
property = { "service.exported.interfaces=*", 
		     "service.exported.configs=ecf.r_osgi.peer.ws",
		     "ecf.exported.async.interfaces=*"})
public class EvalImpl implements Eval {
	
	private final DoubleEvaluator evaluator;
	private final SimpleBooleanEvaluator booleanEvaluator;
	
	public EvalImpl() {
		evaluator = new DoubleEvaluator();
		booleanEvaluator = new SimpleBooleanEvaluator();
	}
	
	@Override
	public double eval(String expression) throws Exception {
		System.out.println("Running eval('"+expression+"')");
		return evaluator.evaluate(expression);
	}
	
	@Override
	public boolean evalBoolean(String booleanExpression) throws Exception {
		System.out.println("Running evalBoolean('"+booleanExpression+"')");
		return booleanEvaluator.evaluate(booleanExpression);
	}
	
	private ServiceRegistration<RemoteServiceAdminListener> reg;
	
	@Activate
	void activate(BundleContext ctx) {
		reg = ctx.registerService(RemoteServiceAdminListener.class,new DebugRemoteServiceAdminListener(), null);
	}
	
	@Deactivate
	void deactivate() {
		if (reg != null) {
			reg.unregister();
			reg = null;
		}
	}
}