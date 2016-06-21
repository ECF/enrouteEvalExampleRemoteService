package com.acme.prime.eval.provider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;

import com.acme.prime.eval.api.Eval;

@Component(immediate=true, 
property = { "service.exported.interfaces=*", 
		     "service.exported.configs=ecf.generic.server",
		     "ecf.exported.async.interfaces=*"})
public class EvalImpl implements Eval {
	Pattern EXPR = Pattern.compile( "\\s*(?<left>\\d+)\\s*(?<op>\\+|-)\\s*(?<right>\\d+)\\s*");
	
	@Override
	public double eval(String expression) throws Exception {
		System.out.println("Running eval('"+expression+"')");
		Matcher m = EXPR.matcher(expression);
		if ( !m.matches())
			throw new IllegalArgumentException("Invalid expression " + expression);
		
		double left = Double.valueOf( m.group("left"));
		double right = Double.valueOf( m.group("right"));
		switch( m.group("op")) {
		case "+": return left + right;
		case "-": return left - right;
		}
		return Double.NaN;
	}
}