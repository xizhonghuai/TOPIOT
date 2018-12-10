/**
 * 
 */
package com.common;

/**
 * @author xizhonghuai
 * @date 2018Äê7ÔÂ13ÈÕ
 * @readme
 */
public class ResultDataSet {

	private Integer error_code;
	private String reason;
	private Object result;

	/**
	 * 
	 */
	public ResultDataSet() {
		super();
		// TODO Auto-generated constructor stub

		this.result = "NULL";
		this.reason = "success";
	}

	/**
	 * @param error_code
	 * @param reason
	 * @param result
	 */
	public ResultDataSet(Integer error_code, String reason, Object result) {
		super();
		this.error_code = error_code;
		this.reason = reason;
		this.result = result;
	}

	/**
	 * @return the error_code
	 */
	public Integer getError_code() {
		return error_code;
	}

	/**
	 * @param error_code
	 *            the error_code to set
	 */
	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(Object result) {

		if (result != null) {
			this.result = result;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResultDataSet [error_code=" + error_code + ", reason=" + reason + ", result=" + result + "]";
	}

}
