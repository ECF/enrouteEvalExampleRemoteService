package com.acme.prime.eval.api;

import java.io.Serializable;

public class EvalExpression implements Serializable {

	private static final long serialVersionUID = 8680701897459202555L;

	private String ex;
	
	public EvalExpression(String ex) {
		this.ex = ex;
	}
	
	public String getExpression() {
		return this.ex;
	}
}
