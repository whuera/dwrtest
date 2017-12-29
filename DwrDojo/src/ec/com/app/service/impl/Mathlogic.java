package ec.com.app.service.impl;

import ec.com.app.service.IMathLogic;

// TODO: Auto-generated Javadoc
/**
 * The Class Mathlogic.
 */
public class Mathlogic implements IMathLogic{

	/* (non-Javadoc)
	 * @see ec.com.app.service.IMathLogic#trasformValue(java.lang.String)
	 */
	@Override
	public String trasformValue(String val) {	
		System.out.println("transform: "+val);
		return val.toUpperCase();
	}
	
	

}
