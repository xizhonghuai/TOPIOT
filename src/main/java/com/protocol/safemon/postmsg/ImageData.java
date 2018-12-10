/**
 * 
 */
package com.protocol.safemon.postmsg;

/**
 *@author xizhonghuai
 *@date 2018Äê9ÔÂ7ÈÕ
 *@readme 
 */
public class ImageData {
	
	private String DeviceId;

	private String DataTime;

	private String Value;

	/**
	 * 
	 */
	public ImageData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param deviceId
	 * @param dataTime
	 * @param value
	 */
	public ImageData(String deviceId, String dataTime, String value) {
		super();
		DeviceId = deviceId;
		DataTime = dataTime;
		Value = value;
	}

	public String getDeviceId() {
		return DeviceId;
	}

	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}

	public String getDataTime() {
		return DataTime;
	}

	public void setDataTime(String dataTime) {
		DataTime = dataTime;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	@Override
	public String toString() {
		return "ImageData [DeviceId=" + DeviceId + ", DataTime=" + DataTime + ", Value=" + Value + "]";
	}
	
	
	
	

}
