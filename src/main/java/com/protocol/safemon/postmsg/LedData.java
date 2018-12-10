/**
 * 
 */
package com.protocol.safemon.postmsg;

/**
 * @author xizhonghuai
 * @date 2018Äê9ÔÂ7ÈÕ
 * @readme
 */
public class LedData {

	private String DeviceId;

	private String DataTime;

	private Value2 Value;

	/**
	 * 
	 */
	public LedData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param deviceId
	 * @param dataTime
	 * @param value
	 */
	public LedData(String deviceId, String dataTime, Value2 value) {
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

	public Value2 getValue() {
		return Value;
	}

	public void setValue(Value2 value) {
		Value = value;
	}

	@Override
	public String toString() {
		return "LedData [DeviceId=" + DeviceId + ", DataTime=" + DataTime + ", Value=" + Value + "]";
	}

}
