/**
 * 
 */
package com.protocol.safemon.sys;

/**
 * @author Administrator
 *
 */
public class LED {

	private String LED_STCD;
	private String STCD;
	private Integer EA;
	private String remark;
	private String SIM;

	/**
	 * 
	 */
	public LED() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param lED_STCD
	 * @param sTCD
	 * @param eA
	 * @param fenqu
	 * @param remark
	 * @param sIM
	 */
	public LED(String lED_STCD, String sTCD, Integer eA, String remark, String sIM) {
		super();
		LED_STCD = lED_STCD;
		STCD = sTCD;
		EA = eA;
		this.remark = remark;
		SIM = sIM;
	}

	/**
	 * @return the lED_STCD
	 */
	public String getLED_STCD() {
		return LED_STCD;
	}

	/**
	 * @param lED_STCD
	 *            the lED_STCD to set
	 */
	public void setLED_STCD(String lED_STCD) {
		LED_STCD = lED_STCD;
	}

	/**
	 * @return the sTCD
	 */
	public String getSTCD() {
		return STCD;
	}

	/**
	 * @param sTCD
	 *            the sTCD to set
	 */
	public void setSTCD(String sTCD) {
		STCD = sTCD;
	}

	/**
	 * @return the eA
	 */
	public Integer getEA() {
		return EA;
	}

	/**
	 * @param eA
	 *            the eA to set
	 */
	public void setEA(Integer eA) {
		EA = eA;
	}


	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the sIM
	 */
	public String getSIM() {
		return SIM;
	}

	/**
	 * @param sIM
	 *            the sIM to set
	 */
	public void setSIM(String sIM) {
		SIM = sIM;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LED [LED_STCD=" + LED_STCD + ", STCD=" + STCD + ", EA=" + EA + ", remark=" + remark + ", SIM=" + SIM
				+ "]";
	}

	 
	
	

}
