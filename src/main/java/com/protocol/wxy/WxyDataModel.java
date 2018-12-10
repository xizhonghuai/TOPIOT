/**
 * 
 */
package com.protocol.wxy;

import java.util.Date;

import com.common.DataModelFieldAnnotation;

/**
 * @author XIZHONGHUAI
 *
 */


 


public class WxyDataModel {

	@DataModelFieldAnnotation(mark = "序号", dataType = "String")
	private Integer Realtime_ID;
	@DataModelFieldAnnotation(mark = "设备唯一标识", dataType = "String")
	private String shebeibianhao;
	@DataModelFieldAnnotation(mark = "实时数据1", dataType = "Float")
	private Float Realtime_V1;
	@DataModelFieldAnnotation(mark = "实时数据2", dataType = "Float")
	private Float Realtime_V2;
	@DataModelFieldAnnotation(mark = "实时数据3", dataType = "Float")
	private Float Realtime_V3;
	@DataModelFieldAnnotation(mark = "数据上报日期", dataType = "Date")
	private Date Realtime_Time;

	/**
	 * 
	 */
	public WxyDataModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRealtime_ID() {
		return Realtime_ID;
	}

	public void setRealtime_ID(Integer realtime_ID) {
		Realtime_ID = realtime_ID;
	}

	public String getShebeibianhao() {
		return shebeibianhao;
	}

	public void setShebeibianhao(String shebeibianhao) {
		this.shebeibianhao = shebeibianhao;
	}

	public Float getRealtime_V1() {
		return Realtime_V1;
	}

	public void setRealtime_V1(Float realtime_V1) {
		Realtime_V1 = realtime_V1;
	}

	public Float getRealtime_V2() {
		return Realtime_V2;
	}

	public void setRealtime_V2(Float realtime_V2) {
		Realtime_V2 = realtime_V2;
	}

	public Float getRealtime_V3() {
		return Realtime_V3;
	}

	public void setRealtime_V3(Float realtime_V3) {
		Realtime_V3 = realtime_V3;
	}

	public Date getRealtime_Time() {
		return Realtime_Time;
	}

	public void setRealtime_Time(Date realtime_Time) {
		Realtime_Time = realtime_Time;
	}

	@Override
	public String toString() {
		return "WxyDataModel [Realtime_ID=" + Realtime_ID + ", shebeibianhao=" + shebeibianhao + ", Realtime_V1="
				+ Realtime_V1 + ", Realtime_V2=" + Realtime_V2 + ", Realtime_V3=" + Realtime_V3 + ", Realtime_Time="
				+ Realtime_Time + "]";
	}

	 
 

}
