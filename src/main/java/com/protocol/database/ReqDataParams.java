/**
 * 
 */
package com.protocol.database;

import java.util.Date;

/**
 * @author xizhonghuai
 * @date 2018年7月20日
 * @readme 查询数据参数
 */
public class ReqDataParams {

	private String handler;
	private String regId;
	private Date beginDate;
	private Date endDate;

	/**
	 * 
	 */
	public ReqDataParams() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param handler
	 * @param regId
	 * @param beginDate
	 * @param endDate
	 */
	public ReqDataParams(String handler, String regId, Date beginDate, Date endDate) {
		super();
		this.handler = handler;
		this.regId = regId;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	/**
	 * @return the handler
	 */
	public String getHandler() {
		return handler;
	}

	/**
	 * @param handler
	 *            the handler to set
	 */
	public void setHandler(String handler) {
		this.handler = handler;
	}

	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}

	/**
	 * @param regId
	 *            the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}

	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate
	 *            the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ReqDataParams [handler=" + handler + ", regId=" + regId + ", beginDate=" + beginDate + ", endDate="
				+ endDate + "]";
	}

}
