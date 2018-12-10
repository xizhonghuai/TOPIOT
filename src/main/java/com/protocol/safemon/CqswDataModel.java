/**
 * 
 */
package com.protocol.safemon;

import java.util.Date;

import com.common.DataModelFieldAnnotation;

/**
 * @author xizhonghuai
 * @date 2018年8月29日
 * @readme
 */
public class CqswDataModel {

	@DataModelFieldAnnotation(mark = "序号", dataType = "String")
	private Integer id;
	@DataModelFieldAnnotation(mark = "设备唯一标识", dataType = "String")
	private String regId;
	@DataModelFieldAnnotation(mark = "水位", dataType = "float")
	private float waterlevel;
	@DataModelFieldAnnotation(mark = "雨量", dataType = "float")
	private float rainfall;
	@DataModelFieldAnnotation(mark = "太阳能板电压", dataType = "float")
	private float voltage1;
	@DataModelFieldAnnotation(mark = "蓄电池电压", dataType = "float")
	private float voltage2;
	@DataModelFieldAnnotation(mark = "led1显示内容", dataType = "String")
	private String ledText1;
	@DataModelFieldAnnotation(mark = "led2显示内容", dataType = "String")
	private String ledText2;
	@DataModelFieldAnnotation(mark = "led1状态", dataType = "String")
	private String ledStatus1;
	@DataModelFieldAnnotation(mark = "led2状态", dataType = "String")
	private String ledStatus2;
	@DataModelFieldAnnotation(mark = "照片1地址", dataType = "String")
	private String pic1;
	@DataModelFieldAnnotation(mark = "照片2地址", dataType = "String")
	private String pic2;
	@DataModelFieldAnnotation(mark = "数据上报日期", dataType = "Date")
	private Date tm;

	/**
	 * 
	 */
	public CqswDataModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param regId
	 * @param waterlevel
	 * @param rainfall
	 * @param voltage1
	 * @param voltage2
	 * @param ledText1
	 * @param ledText2
	 * @param ledStatus1
	 * @param ledStatus2
	 * @param pic1
	 * @param pic2
	 * @param tm
	 */
	public CqswDataModel(Integer id, String regId, float waterlevel, float rainfall, float voltage1, float voltage2,
			String ledText1, String ledText2, String ledStatus1, String ledStatus2, String pic1, String pic2, Date tm) {
		super();
		this.id = id;
		this.regId = regId;
		this.waterlevel = waterlevel;
		this.rainfall = rainfall;
		this.voltage1 = voltage1;
		this.voltage2 = voltage2;
		this.ledText1 = ledText1;
		this.ledText2 = ledText2;
		this.ledStatus1 = ledStatus1;
		this.ledStatus2 = ledStatus2;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.tm = tm;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public float getWaterlevel() {
		return waterlevel;
	}

	public void setWaterlevel(float waterlevel) {
		this.waterlevel = waterlevel;
	}

	public float getRainfall() {
		return rainfall;
	}

	public void setRainfall(float rainfall) {
		this.rainfall = rainfall;
	}

	public float getVoltage1() {
		return voltage1;
	}

	public void setVoltage1(float voltage1) {
		this.voltage1 = voltage1;
	}

	public float getVoltage2() {
		return voltage2;
	}

	public void setVoltage2(float voltage2) {
		this.voltage2 = voltage2;
	}

	public String getLedText1() {
		return ledText1;
	}

	public void setLedText1(String ledText1) {
		this.ledText1 = ledText1;
	}

	public String getLedText2() {
		return ledText2;
	}

	public void setLedText2(String ledText2) {
		this.ledText2 = ledText2;
	}

	public String getLedStatus1() {
		return ledStatus1;
	}

	public void setLedStatus1(String ledStatus1) {
		this.ledStatus1 = ledStatus1;
	}

	public String getLedStatus2() {
		return ledStatus2;
	}

	public void setLedStatus2(String ledStatus2) {
		this.ledStatus2 = ledStatus2;
	}

	public String getPic1() {
		return pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	public Date getTm() {
		return tm;
	}

	public void setTm(Date tm) {
		this.tm = tm;
	}

	@Override
	public String toString() {
		return "CqswDataModel [id=" + id + ", regId=" + regId + ", waterlevel=" + waterlevel + ", rainfall=" + rainfall
				+ ", voltage1=" + voltage1 + ", voltage2=" + voltage2 + ", ledText1=" + ledText1 + ", ledText2="
				+ ledText2 + ", ledStatus1=" + ledStatus1 + ", ledStatus2=" + ledStatus2 + ", pic1=" + pic1 + ", pic2="
				+ pic2 + ", tm=" + tm + "]";
	}
	
	
	
	

}
