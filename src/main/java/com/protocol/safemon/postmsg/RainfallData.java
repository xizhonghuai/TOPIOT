/**
 * 
 */
package com.protocol.safemon.postmsg;

/**
 * @author xizhonghuai
 * @date 2018Äê9ÔÂ7ÈÕ
 * @readme
 */
public class RainfallData {

	private String DeviceId;

	private String DataTime;

	private Value1 Value;

	/**
	 * 
	 */
	public RainfallData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param deviceId
	 * @param dataTime
	 * @param value
	 */
	public RainfallData(String deviceId, String dataTime, Value1 value) {
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

	public Value1 getValue() {
		return Value;
	}

	public void setValue(Value1 value) {
		Value = value;
	}

	@Override
	public String toString() {
		return "Waterlevel [DeviceId=" + DeviceId + ", DataTime=" + DataTime + ", Value=" + Value + "]";
	}

}
