package com.acme.prime.eval.provider.v2;

import java.io.ObjectStreamClass;
import java.util.Hashtable;

import org.eclipse.ecf.core.util.IClassResolver;
import org.eclipse.ecf.osgi.services.remoteserviceadmin.DebugRemoteServiceAdminListener;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.remoteserviceadmin.RemoteServiceAdminListener;

import com.acme.prime.eval.api.Eval;
import com.acme.prime.eval.api.EvalExpression;
import com.fathzer.soft.javaluator.DoubleEvaluator;

@Component(immediate=true, 
property = { "service.exported.interfaces=*", 
		     "service.exported.configs=ecf.generic.server",
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
	private ServiceRegistration<IClassResolver> crReg;
	
	@Activate
	void activate(final BundleContext ctx) {
		IClassResolver cr = new IClassResolver() {
			@Override
			public Class<?> resolveClass(ObjectStreamClass desc) throws ClassNotFoundException {
				return ctx.getBundle().loadClass(desc.getName());
			}			
		};
		Hashtable<String, String> crProps = new Hashtable<String,String>();
		crProps.put("ch.ethz.iks.r_osgi.transport.http.classResolverProtocol", "http");
		crReg = ctx.registerService(IClassResolver.class, cr, crProps);
		reg = ctx.registerService(RemoteServiceAdminListener.class,new DebugRemoteServiceAdminListener(), null);
	}
	
	@Deactivate
	void deactivate() {
		if (crReg != null) {
			crReg.unregister();
			crReg = null;
		}
		if (reg != null) {
			reg.unregister();
			reg = null;
		}
	}

	@Override
	public double evalEx(EvalExpression expression) throws Exception {
		return eval(expression.getExpression());
	}
}