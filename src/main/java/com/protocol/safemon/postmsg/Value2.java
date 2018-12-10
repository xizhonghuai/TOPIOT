/**
 * 
 */
package com.protocol.safemon.postmsg;

/**
 * @author xizhonghuai
 * @date 2018Äê9ÔÂ7ÈÕ
 * @readme
 */
public class Value2 {

	 
	private Integer Status;
	 
	 
	private String Text;

	/**
	 * 
	 */
	public Value2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param status
	 * @param text
	 */
	public Value2(Integer status, String text) {
		super();
		Status = status;
		Text = text;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	@Override
	public String toString() {
		return "Value2 [Status=" + Status + ", Text=" + Text + "]";
	}

}
