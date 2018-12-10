/**
 * 
 */
package com.protocol.safemon.sys;

/**
 * @author Administrator
 *
 */
public class Call {
	
	private Integer id;
	private float C_1baojing;
	private String C_jibie;
	private Integer quid;
	/**
	 * 
	 */
	public Call() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param c_1baojing
	 * @param c_jibie
	 * @param quid
	 */
	public Call(Integer id, float c_1baojing, String c_jibie, Integer quid) {
		super();
		this.id = id;
		C_1baojing = c_1baojing;
		C_jibie = c_jibie;
		this.quid = quid;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the c_1baojing
	 */
	public float getC_1baojing() {
		return C_1baojing;
	}
	/**
	 * @param c_1baojing the c_1baojing to set
	 */
	public void setC_1baojing(float c_1baojing) {
		C_1baojing = c_1baojing;
	}
	/**
	 * @return the c_jibie
	 */
	public String getC_jibie() {
		return C_jibie;
	}
	/**
	 * @param c_jibie the c_jibie to set
	 */
	public void setC_jibie(String c_jibie) {
		C_jibie = c_jibie;
	}
	/**
	 * @return the quid
	 */
	public Integer getQuid() {
		return quid;
	}
	/**
	 * @param quid the quid to set
	 */
	public void setQuid(Integer quid) {
		this.quid = quid;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Call [id=" + id + ", C_1baojing=" + C_1baojing + ", C_jibie=" + C_jibie + ", quid=" + quid + "]";
	}
	
	 
	
	
	

}
