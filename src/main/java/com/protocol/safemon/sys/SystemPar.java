/**
 * 
 */
package com.protocol.safemon.sys;

/**
 * @author Administrator
 *
 */
public class SystemPar {
	
	private float LEDTh_on;
	private float LEDTh_off;
	/**
	 * 
	 */
	public SystemPar() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param lEDTh_on
	 * @param lEDTh_off
	 */
	public SystemPar(float lEDTh_on, float lEDTh_off) {
		super();
		LEDTh_on = lEDTh_on;
		LEDTh_off = lEDTh_off;
	}
	/**
	 * @return the lEDTh_on
	 */
	public float getLEDTh_on() {
		return LEDTh_on;
	}
	/**
	 * @param lEDTh_on the lEDTh_on to set
	 */
	public void setLEDTh_on(float lEDTh_on) {
		LEDTh_on = lEDTh_on;
	}
	/**
	 * @return the lEDTh_off
	 */
	public float getLEDTh_off() {
		return LEDTh_off;
	}
	/**
	 * @param lEDTh_off the lEDTh_off to set
	 */
	public void setLEDTh_off(float lEDTh_off) {
		LEDTh_off = lEDTh_off;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SystemPar [LEDTh_on=" + LEDTh_on + ", LEDTh_off=" + LEDTh_off + "]";
	}
	
	
	
	
	
	

}
