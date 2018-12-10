/**
 * 
 */
package com.protocol.test;

import java.util.Date;

import com.common.DataModelFieldAnnotation;

/**
 * @author XIZHONGHUAI
 *
 */
public class TestDataModel {

	@DataModelFieldAnnotation(mark = "���", dataType = "String")
	private Integer id;
	@DataModelFieldAnnotation(mark = "�豸Ψһ��ʶ", dataType = "String")
	private String regId;
	@DataModelFieldAnnotation(mark = "ʵʱ����", dataType = "Float")
	private Float value;
	@DataModelFieldAnnotation(mark = "�����ϱ�����", dataType = "Date")
	private Date tm;

	/**
	 * 
	 */
	public TestDataModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param regId
	 * @param value
	 * @param tm
	 */
	public TestDataModel(Integer id, String regId, Float value, Date tm) {
		super();
		this.id = id;
		this.regId = regId;
		this.value = value;
		this.tm = tm;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the value
	 */
	public Float getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Float value) {
		this.value = value;
	}

	/**
	 * @return the tm
	 */
	public Date getTm() {
		return tm;
	}

	/**
	 * @param tm
	 *            the tm to set
	 */
	public void setTm(Date tm) {
		this.tm = tm;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return "TestDataModel [id=" + id + ", regId=" + regId + ", value=" + value + ", tm=" + tm + "]";
	}

}
