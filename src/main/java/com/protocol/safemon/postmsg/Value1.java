/**
 * 
 */
package com.protocol.safemon.postmsg;

/**
 * @author xizhonghuai
 * @date 2018Äê9ÔÂ7ÈÕ
 * @readme
 */
public class Value1 {

	private String Unit;
	private Float Value;

	/**
	 * 
	 */
	public Value1() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param unit
	 * @param value
	 */
	public Value1(String unit, Float value) {
		super();
		Unit = unit;
		Value = value;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public Float getValue() {
		return Value;
	}

	public void setValue(Float value) {
		Value = value;
	}

	@Override
	public String toString() {
		return "Value1 [Unit=" + Unit + ", Value=" + Value + "]";
	}

}
