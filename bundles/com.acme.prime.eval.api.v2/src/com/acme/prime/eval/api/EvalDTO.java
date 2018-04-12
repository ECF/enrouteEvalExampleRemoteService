package com.acme.prime.eval.api;

import org.osgi.dto.DTO;

public class EvalDTO extends DTO {

	public String val;
	public EvalDTO eval;
	public double d;
	@Override
	public String toString() {
		return "EvalDTO [val=" + val + ", eval=" + eval + ", d=" + d + "]";
	}
	
}
