/**
 * 
 */
package com.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xizhonghuai
 * @date 2018年7月10日
 * @readme 处理器类注解信息
 */
public class HandlerClassInfo {

	private String mark;
	private String version;
	private String[] putStorageMethod;
	private String dataModelClassName;
	private String[] dataModelProperty;

	/**
	 * 
	 */
	public HandlerClassInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param mark
	 * @param version
	 * @param putStorageMethod
	 * @param dataModelClassName
	 * @param dataModelProperty
	 */
	public HandlerClassInfo(String mark, String version, String[] putStorageMethod, String dataModelClassName,
			String[] dataModelProperty) {
		super();
		this.mark = mark;
		this.version = version;
		this.putStorageMethod = putStorageMethod;
		this.dataModelClassName = dataModelClassName;
		this.dataModelProperty = dataModelProperty;
	}

	/**
	 * @return the mark
	 */
	public String getMark() {
		return mark;
	}

	/**
	 * @param mark
	 *            the mark to set
	 */
	public void setMark(String mark) {
		this.mark = mark;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the putStorageMethod
	 */
	public String[] getPutStorageMethod() {
		return putStorageMethod;
	}

	/**
	 * @param putStorageMethod
	 *            the putStorageMethod to set
	 */
	public void setPutStorageMethod(String[] putStorageMethod) {
		this.putStorageMethod = putStorageMethod;
	}

	/**
	 * @return the dataModelClassName
	 */
	public String getDataModelClassName() {
		return dataModelClassName;
	}

	/**
	 * @param dataModelClassName
	 *            the dataModelClassName to set
	 */
	public void setDataModelClassName(String dataModelClassName) {
		this.dataModelClassName = dataModelClassName;

		List<String> fieldList = new ArrayList<String>();

		try {
			fieldList = methods.getDataModelClassFields(this.dataModelClassName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] fieldArray = new String[fieldList.size()];

		fieldList.toArray(fieldArray);

		this.dataModelProperty = fieldArray;

	}

	/**
	 * @return the dataModelProperty
	 */

	public String[] getDataModelProperty() {

		return dataModelProperty;
	}

	/**
	 * @param dataModelProperty
	 *            the dataModelProperty to set
	 */
	public void setDataModelProperty(String[] dataModelProperty) {
		//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HandlerClassInfo [mark=" + mark + ", version=" + version + ", putStorageMethod="
				+ Arrays.toString(putStorageMethod) + ", dataModelClassName=" + dataModelClassName
				+ ", dataModelProperty=" + Arrays.toString(dataModelProperty) + "]";
	}

}
