/**
 * 
 */
package com.protocol.jsonrtu;

import java.util.Arrays;
 

/**
 * @author XIZHONGHUAI
 *
 */
public class JsonRTU {
	
 
	
	private Float rs4851_0;
	private Float rs4851_1;
	private Float rs4851_2;
	private Float rs4851_3;
	private Float rs4851_4;
	private Float rs4851_5;
	private Float rs4851_6;
	private Float rs4851_7;
	
	private Float rs4852_0;
	private Float rs4852_1;
	private Float rs4852_2;
	private Float rs4852_3;
	private Float rs4852_4;
	private Float rs4852_5;
	private Float rs4852_6;
	private Float rs4852_7;
	
	private Float rs2320;
	private Float rs2321;
	private Float rs2322;
	private Float rs2323;
	
	private Float ai0;
	private Float ai1;
	private Float ai2;
	private Float ai3;
	private Float ai4;
	private Float ai5;
	private Float ai6;
	private Float ai7;
	
	private Float di0;
	private Float di1;
	private Float di2;
	private Float di3;
	private Float di4;
	private Float di5;
	private Float di6;
	private Float di7;
	
	private Float doi0;
	private Float do1;
	private Float do2;
	private Float do3;
	private Float do4;
	private Float do5;
	private Float do6;
	private Float do7;
	
	
	private String id;
	private String time;
	private Float vbat;
	private Float svbat;
	private Float temp;
	private String[] sensors;
	private String type;
	

	
	/**
	 * 
	 */
	public JsonRTU() {
		super();
		// TODO Auto-generated constructor stub
	}



	/**
	 * @param rs4851_0
	 * @param rs4851_1
	 * @param rs4851_2
	 * @param rs4851_3
	 * @param rs4851_4
	 * @param rs4851_5
	 * @param rs4851_6
	 * @param rs4851_7
	 * @param rs4852_0
	 * @param rs4852_1
	 * @param rs4852_2
	 * @param rs4852_3
	 * @param rs4852_4
	 * @param rs4852_5
	 * @param rs4852_6
	 * @param rs4852_7
	 * @param rs2320
	 * @param rs2321
	 * @param rs2322
	 * @param rs2323
	 * @param ai0
	 * @param ai1
	 * @param ai2
	 * @param ai3
	 * @param ai4
	 * @param ai5
	 * @param ai6
	 * @param ai7
	 * @param di0
	 * @param di1
	 * @param di2
	 * @param di3
	 * @param di4
	 * @param di5
	 * @param di6
	 * @param di7
	 * @param doi0
	 * @param do1
	 * @param do2
	 * @param do3
	 * @param do4
	 * @param do5
	 * @param do6
	 * @param do7
	 * @param id
	 * @param time
	 * @param vbat
	 * @param svbat
	 * @param temp
	 * @param sensors
	 * @param type
	 */
	public JsonRTU(Float rs4851_0, Float rs4851_1, Float rs4851_2, Float rs4851_3, Float rs4851_4, Float rs4851_5,
			Float rs4851_6, Float rs4851_7, Float rs4852_0, Float rs4852_1, Float rs4852_2, Float rs4852_3,
			Float rs4852_4, Float rs4852_5, Float rs4852_6, Float rs4852_7, Float rs2320, Float rs2321, Float rs2322,
			Float rs2323, Float ai0, Float ai1, Float ai2, Float ai3, Float ai4, Float ai5, Float ai6, Float ai7,
			Float di0, Float di1, Float di2, Float di3, Float di4, Float di5, Float di6, Float di7, Float doi0,
			Float do1, Float do2, Float do3, Float do4, Float do5, Float do6, Float do7, String id, String time,
			Float vbat, Float svbat, Float temp, String[] sensors, String type) {
		super();
		this.rs4851_0 = rs4851_0;
		this.rs4851_1 = rs4851_1;
		this.rs4851_2 = rs4851_2;
		this.rs4851_3 = rs4851_3;
		this.rs4851_4 = rs4851_4;
		this.rs4851_5 = rs4851_5;
		this.rs4851_6 = rs4851_6;
		this.rs4851_7 = rs4851_7;
		this.rs4852_0 = rs4852_0;
		this.rs4852_1 = rs4852_1;
		this.rs4852_2 = rs4852_2;
		this.rs4852_3 = rs4852_3;
		this.rs4852_4 = rs4852_4;
		this.rs4852_5 = rs4852_5;
		this.rs4852_6 = rs4852_6;
		this.rs4852_7 = rs4852_7;
		this.rs2320 = rs2320;
		this.rs2321 = rs2321;
		this.rs2322 = rs2322;
		this.rs2323 = rs2323;
		this.ai0 = ai0;
		this.ai1 = ai1;
		this.ai2 = ai2;
		this.ai3 = ai3;
		this.ai4 = ai4;
		this.ai5 = ai5;
		this.ai6 = ai6;
		this.ai7 = ai7;
		this.di0 = di0;
		this.di1 = di1;
		this.di2 = di2;
		this.di3 = di3;
		this.di4 = di4;
		this.di5 = di5;
		this.di6 = di6;
		this.di7 = di7;
		this.doi0 = doi0;
		this.do1 = do1;
		this.do2 = do2;
		this.do3 = do3;
		this.do4 = do4;
		this.do5 = do5;
		this.do6 = do6;
		this.do7 = do7;
		this.id = id;
		this.time = time;
		this.vbat = vbat;
		this.svbat = svbat;
		this.temp = temp;
		this.sensors = sensors;
		this.type = type;
	}



	/**
	 * @return the rs4851_0
	 */
	public Float getRs4851_0() {
		return rs4851_0;
	}



	/**
	 * @param rs4851_0 the rs4851_0 to set
	 */
	public void setRs4851_0(Float rs4851_0) {
		this.rs4851_0 = rs4851_0;
	}



	/**
	 * @return the rs4851_1
	 */
	public Float getRs4851_1() {
		return rs4851_1;
	}



	/**
	 * @param rs4851_1 the rs4851_1 to set
	 */
	public void setRs4851_1(Float rs4851_1) {
		this.rs4851_1 = rs4851_1;
	}



	/**
	 * @return the rs4851_2
	 */
	public Float getRs4851_2() {
		return rs4851_2;
	}



	/**
	 * @param rs4851_2 the rs4851_2 to set
	 */
	public void setRs4851_2(Float rs4851_2) {
		this.rs4851_2 = rs4851_2;
	}



	/**
	 * @return the rs4851_3
	 */
	public Float getRs4851_3() {
		return rs4851_3;
	}



	/**
	 * @param rs4851_3 the rs4851_3 to set
	 */
	public void setRs4851_3(Float rs4851_3) {
		this.rs4851_3 = rs4851_3;
	}



	/**
	 * @return the rs4851_4
	 */
	public Float getRs4851_4() {
		return rs4851_4;
	}



	/**
	 * @param rs4851_4 the rs4851_4 to set
	 */
	public void setRs4851_4(Float rs4851_4) {
		this.rs4851_4 = rs4851_4;
	}



	/**
	 * @return the rs4851_5
	 */
	public Float getRs4851_5() {
		return rs4851_5;
	}



	/**
	 * @param rs4851_5 the rs4851_5 to set
	 */
	public void setRs4851_5(Float rs4851_5) {
		this.rs4851_5 = rs4851_5;
	}



	/**
	 * @return the rs4851_6
	 */
	public Float getRs4851_6() {
		return rs4851_6;
	}



	/**
	 * @param rs4851_6 the rs4851_6 to set
	 */
	public void setRs4851_6(Float rs4851_6) {
		this.rs4851_6 = rs4851_6;
	}



	/**
	 * @return the rs4851_7
	 */
	public Float getRs4851_7() {
		return rs4851_7;
	}



	/**
	 * @param rs4851_7 the rs4851_7 to set
	 */
	public void setRs4851_7(Float rs4851_7) {
		this.rs4851_7 = rs4851_7;
	}



	/**
	 * @return the rs4852_0
	 */
	public Float getRs4852_0() {
		return rs4852_0;
	}



	/**
	 * @param rs4852_0 the rs4852_0 to set
	 */
	public void setRs4852_0(Float rs4852_0) {
		this.rs4852_0 = rs4852_0;
	}



	/**
	 * @return the rs4852_1
	 */
	public Float getRs4852_1() {
		return rs4852_1;
	}



	/**
	 * @param rs4852_1 the rs4852_1 to set
	 */
	public void setRs4852_1(Float rs4852_1) {
		this.rs4852_1 = rs4852_1;
	}



	/**
	 * @return the rs4852_2
	 */
	public Float getRs4852_2() {
		return rs4852_2;
	}



	/**
	 * @param rs4852_2 the rs4852_2 to set
	 */
	public void setRs4852_2(Float rs4852_2) {
		this.rs4852_2 = rs4852_2;
	}



	/**
	 * @return the rs4852_3
	 */
	public Float getRs4852_3() {
		return rs4852_3;
	}



	/**
	 * @param rs4852_3 the rs4852_3 to set
	 */
	public void setRs4852_3(Float rs4852_3) {
		this.rs4852_3 = rs4852_3;
	}



	/**
	 * @return the rs4852_4
	 */
	public Float getRs4852_4() {
		return rs4852_4;
	}



	/**
	 * @param rs4852_4 the rs4852_4 to set
	 */
	public void setRs4852_4(Float rs4852_4) {
		this.rs4852_4 = rs4852_4;
	}



	/**
	 * @return the rs4852_5
	 */
	public Float getRs4852_5() {
		return rs4852_5;
	}



	/**
	 * @param rs4852_5 the rs4852_5 to set
	 */
	public void setRs4852_5(Float rs4852_5) {
		this.rs4852_5 = rs4852_5;
	}



	/**
	 * @return the rs4852_6
	 */
	public Float getRs4852_6() {
		return rs4852_6;
	}



	/**
	 * @param rs4852_6 the rs4852_6 to set
	 */
	public void setRs4852_6(Float rs4852_6) {
		this.rs4852_6 = rs4852_6;
	}



	/**
	 * @return the rs4852_7
	 */
	public Float getRs4852_7() {
		return rs4852_7;
	}



	/**
	 * @param rs4852_7 the rs4852_7 to set
	 */
	public void setRs4852_7(Float rs4852_7) {
		this.rs4852_7 = rs4852_7;
	}



	/**
	 * @return the rs2320
	 */
	public Float getRs2320() {
		return rs2320;
	}



	/**
	 * @param rs2320 the rs2320 to set
	 */
	public void setRs2320(Float rs2320) {
		this.rs2320 = rs2320;
	}



	/**
	 * @return the rs2321
	 */
	public Float getRs2321() {
		return rs2321;
	}



	/**
	 * @param rs2321 the rs2321 to set
	 */
	public void setRs2321(Float rs2321) {
		this.rs2321 = rs2321;
	}



	/**
	 * @return the rs2322
	 */
	public Float getRs2322() {
		return rs2322;
	}



	/**
	 * @param rs2322 the rs2322 to set
	 */
	public void setRs2322(Float rs2322) {
		this.rs2322 = rs2322;
	}



	/**
	 * @return the rs2323
	 */
	public Float getRs2323() {
		return rs2323;
	}



	/**
	 * @param rs2323 the rs2323 to set
	 */
	public void setRs2323(Float rs2323) {
		this.rs2323 = rs2323;
	}



	/**
	 * @return the ai0
	 */
	public Float getAi0() {
		return ai0;
	}



	/**
	 * @param ai0 the ai0 to set
	 */
	public void setAi0(Float ai0) {
		this.ai0 = ai0;
	}



	/**
	 * @return the ai1
	 */
	public Float getAi1() {
		return ai1;
	}



	/**
	 * @param ai1 the ai1 to set
	 */
	public void setAi1(Float ai1) {
		this.ai1 = ai1;
	}



	/**
	 * @return the ai2
	 */
	public Float getAi2() {
		return ai2;
	}



	/**
	 * @param ai2 the ai2 to set
	 */
	public void setAi2(Float ai2) {
		this.ai2 = ai2;
	}



	/**
	 * @return the ai3
	 */
	public Float getAi3() {
		return ai3;
	}



	/**
	 * @param ai3 the ai3 to set
	 */
	public void setAi3(Float ai3) {
		this.ai3 = ai3;
	}



	/**
	 * @return the ai4
	 */
	public Float getAi4() {
		return ai4;
	}



	/**
	 * @param ai4 the ai4 to set
	 */
	public void setAi4(Float ai4) {
		this.ai4 = ai4;
	}



	/**
	 * @return the ai5
	 */
	public Float getAi5() {
		return ai5;
	}



	/**
	 * @param ai5 the ai5 to set
	 */
	public void setAi5(Float ai5) {
		this.ai5 = ai5;
	}



	/**
	 * @return the ai6
	 */
	public Float getAi6() {
		return ai6;
	}



	/**
	 * @param ai6 the ai6 to set
	 */
	public void setAi6(Float ai6) {
		this.ai6 = ai6;
	}



	/**
	 * @return the ai7
	 */
	public Float getAi7() {
		return ai7;
	}



	/**
	 * @param ai7 the ai7 to set
	 */
	public void setAi7(Float ai7) {
		this.ai7 = ai7;
	}



	/**
	 * @return the di0
	 */
	public Float getDi0() {
		return di0;
	}



	/**
	 * @param di0 the di0 to set
	 */
	public void setDi0(Float di0) {
		this.di0 = di0;
	}



	/**
	 * @return the di1
	 */
	public Float getDi1() {
		return di1;
	}



	/**
	 * @param di1 the di1 to set
	 */
	public void setDi1(Float di1) {
		this.di1 = di1;
	}



	/**
	 * @return the di2
	 */
	public Float getDi2() {
		return di2;
	}



	/**
	 * @param di2 the di2 to set
	 */
	public void setDi2(Float di2) {
		this.di2 = di2;
	}



	/**
	 * @return the di3
	 */
	public Float getDi3() {
		return di3;
	}



	/**
	 * @param di3 the di3 to set
	 */
	public void setDi3(Float di3) {
		this.di3 = di3;
	}



	/**
	 * @return the di4
	 */
	public Float getDi4() {
		return di4;
	}



	/**
	 * @param di4 the di4 to set
	 */
	public void setDi4(Float di4) {
		this.di4 = di4;
	}



	/**
	 * @return the di5
	 */
	public Float getDi5() {
		return di5;
	}



	/**
	 * @param di5 the di5 to set
	 */
	public void setDi5(Float di5) {
		this.di5 = di5;
	}



	/**
	 * @return the di6
	 */
	public Float getDi6() {
		return di6;
	}



	/**
	 * @param di6 the di6 to set
	 */
	public void setDi6(Float di6) {
		this.di6 = di6;
	}



	/**
	 * @return the di7
	 */
	public Float getDi7() {
		return di7;
	}



	/**
	 * @param di7 the di7 to set
	 */
	public void setDi7(Float di7) {
		this.di7 = di7;
	}



	/**
	 * @return the doi0
	 */
	public Float getDoi0() {
		return doi0;
	}



	/**
	 * @param doi0 the doi0 to set
	 */
	public void setDoi0(Float doi0) {
		this.doi0 = doi0;
	}



	/**
	 * @return the do1
	 */
	public Float getDo1() {
		return do1;
	}



	/**
	 * @param do1 the do1 to set
	 */
	public void setDo1(Float do1) {
		this.do1 = do1;
	}



	/**
	 * @return the do2
	 */
	public Float getDo2() {
		return do2;
	}



	/**
	 * @param do2 the do2 to set
	 */
	public void setDo2(Float do2) {
		this.do2 = do2;
	}



	/**
	 * @return the do3
	 */
	public Float getDo3() {
		return do3;
	}



	/**
	 * @param do3 the do3 to set
	 */
	public void setDo3(Float do3) {
		this.do3 = do3;
	}



	/**
	 * @return the do4
	 */
	public Float getDo4() {
		return do4;
	}



	/**
	 * @param do4 the do4 to set
	 */
	public void setDo4(Float do4) {
		this.do4 = do4;
	}



	/**
	 * @return the do5
	 */
	public Float getDo5() {
		return do5;
	}



	/**
	 * @param do5 the do5 to set
	 */
	public void setDo5(Float do5) {
		this.do5 = do5;
	}



	/**
	 * @return the do6
	 */
	public Float getDo6() {
		return do6;
	}



	/**
	 * @param do6 the do6 to set
	 */
	public void setDo6(Float do6) {
		this.do6 = do6;
	}



	/**
	 * @return the do7
	 */
	public Float getDo7() {
		return do7;
	}



	/**
	 * @param do7 the do7 to set
	 */
	public void setDo7(Float do7) {
		this.do7 = do7;
	}



	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}



	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}



	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}



	/**
	 * @return the vbat
	 */
	public Float getVbat() {
		return vbat;
	}



	/**
	 * @param vbat the vbat to set
	 */
	public void setVbat(Float vbat) {
		this.vbat = vbat;
	}



	/**
	 * @return the svbat
	 */
	public Float getSvbat() {
		return svbat;
	}



	/**
	 * @param svbat the svbat to set
	 */
	public void setSvbat(Float svbat) {
		this.svbat = svbat;
	}



	/**
	 * @return the temp
	 */
	public Float getTemp() {
		return temp;
	}



	/**
	 * @param temp the temp to set
	 */
	public void setTemp(Float temp) {
		this.temp = temp;
	}



	/**
	 * @return the sensors
	 */
	public String[] getSensors() {
		return sensors;
	}



	/**
	 * @param sensors the sensors to set
	 */
	public void setSensors(String[] sensors) {
		this.sensors = sensors;
	}



	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}



	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JsonDataModel [rs4851_0=" + rs4851_0 + ", rs4851_1=" + rs4851_1 + ", rs4851_2=" + rs4851_2
				+ ", rs4851_3=" + rs4851_3 + ", rs4851_4=" + rs4851_4 + ", rs4851_5=" + rs4851_5 + ", rs4851_6="
				+ rs4851_6 + ", rs4851_7=" + rs4851_7 + ", rs4852_0=" + rs4852_0 + ", rs4852_1=" + rs4852_1
				+ ", rs4852_2=" + rs4852_2 + ", rs4852_3=" + rs4852_3 + ", rs4852_4=" + rs4852_4 + ", rs4852_5="
				+ rs4852_5 + ", rs4852_6=" + rs4852_6 + ", rs4852_7=" + rs4852_7 + ", rs2320=" + rs2320 + ", rs2321="
				+ rs2321 + ", rs2322=" + rs2322 + ", rs2323=" + rs2323 + ", ai0=" + ai0 + ", ai1=" + ai1 + ", ai2="
				+ ai2 + ", ai3=" + ai3 + ", ai4=" + ai4 + ", ai5=" + ai5 + ", ai6=" + ai6 + ", ai7=" + ai7 + ", di0="
				+ di0 + ", di1=" + di1 + ", di2=" + di2 + ", di3=" + di3 + ", di4=" + di4 + ", di5=" + di5 + ", di6="
				+ di6 + ", di7=" + di7 + ", doi0=" + doi0 + ", do1=" + do1 + ", do2=" + do2 + ", do3=" + do3 + ", do4="
				+ do4 + ", do5=" + do5 + ", do6=" + do6 + ", do7=" + do7 + ", id=" + id + ", time=" + time + ", vbat="
				+ vbat + ", svbat=" + svbat + ", temp=" + temp + ", sensors=" + Arrays.toString(sensors) + ", type="
				+ type + "]";
	}



 
	
	
	
	
	
	
	
	
 
	 
	 
	
	
	

}
