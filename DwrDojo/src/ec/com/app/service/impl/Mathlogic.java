package ec.com.app.service.impl;

import ec.com.app.service.IMathLogic;

public class Mathlogic implements IMathLogic{

	@Override
	public String trasformValue(String val) {	
		System.out.println("transform: "+val);
		return val.toUpperCase();
	}
	
	

}
