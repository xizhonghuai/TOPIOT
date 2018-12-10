/**
 * 
 */
package com.protocol.safemon.sys;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author xizhonghuai
 * @date 2018年8月29日
 * @readme
 */
public class CqswMethods {

	public static  String IMAGE_TEMP = "";
	public static  String IMAGE = "";
	public static  String POSTURL = "";
	public static  String IMAGESERVERURL = "";

	/**
	 * @date 2018年8月29日
	 * @readme 判断数据报类型
	 * @param msg
	 * @return 1、水文 2、屏 0、无效
	 */
	public static int checkDeviceType(String msg) {

		if (msg == null) {
			return 0;
		}
		if (msg.length() < 3) {
			return 0;
		}
		if ((msg.charAt(0) == 0x7b) && (msg.charAt(msg.length() - 1) == 0x7b)) {

			if (msg.length() > 16) {

				return 1;
			} else {
				return 0;
			}

		}

		if ((msg.charAt(0) == 0x28) && (msg.charAt(msg.length() - 1) == 0x29)) {
			return 2;
		}
		return 0;
	}

	/**
	 * 报警级别查询
	 * 
	 * @param waterlevel
	 * @param fenqu
	 * @return
	 */
	public static String getCallmsg(float waterlevel, int fenqu) {
		String callmsg = "正常通行";
		float callvalue = 0;
		List<Call> calltemp = ParamsInit.callList;
		List<Call> call = new ArrayList<Call>();
		List<Float> C_1baojing = new ArrayList<Float>();

		for (int i = 0; i < calltemp.size(); i++) {
			if (calltemp.get(i).getQuid().intValue() == fenqu) {
				call.add(calltemp.get(i));
			}
		}

		for (int m = 0; m < call.size(); m++) {
			C_1baojing.add(call.get(m).getC_1baojing());
		}
		Collections.sort(C_1baojing);
		for (int m = 0; m < C_1baojing.size(); m++) {
			if (waterlevel >= C_1baojing.get(m).floatValue()) {
				callvalue = C_1baojing.get(m).floatValue();
			}
		}

		for (int m = 0; m < call.size(); m++) {
			float c = call.get(m).getC_1baojing();
			if (c == callvalue) {
				callmsg = call.get(m).getC_jibie();
				break;
			}
		}
		return callmsg;
	}

	/**
	 * @date 2018年9月3日
	 * @readme CRC16
	 * @param data
	 * @return
	 */
	public static short calcCrc16(byte[] data) {

		int crc = 0xffff;
		int dxs = 0xa001;
		int hibyte;
		int sbit;
		for (int i = 0; i < data.length; i++) {
			hibyte = crc >> 8;
			crc = hibyte ^ data[i];

			for (int j = 0; j < 8; j++) {
				sbit = crc & 0x0001;
				crc = crc >> 1;
				if (sbit == 1)
					crc ^= dxs;
			}
		}
		return (short) (crc & 0xffff);
	}

	/**
	 * @date 2018年9月3日
	 * @readme 校时
	 * @return
	 */
	public static String calibrationTime(String regId) {
		// 117B8900203133363637363036313432443230313830393033313230303030457B

		// 7B 89 00 22 30 30 30 30 30 30 30 30 33 33 30 44 32 30 31 38 30 39 30
		// 33 31 32 31 39 31 38 45 0D 0A 7B
		// msg:='D20180903120000E';
		SimpleDateFormat dt = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String dateTime = "D" + dt.format(date) + "E";
		char[] dat1 = new char[4];
		dat1[0] = 0x7b;
		dat1[1] = 0x89;
		dat1[2] = 0x00;
		dat1[3] = 0x20;
		char[] dat2 = new char[1];
		dat2[0] = 0x7b;
		return (new String(dat1) + regId + dateTime + new String(dat2));

	}

	/**
	 * @date 2018年9月3日
	 * @readme 参数配置
	 * @param regId
	 * @return
	 */
	public static String paramsConfig(String regId, String params) {

		 

		char[] dat1 = new char[4];
		dat1[0] = 0x7b;
		dat1[1] = 0x89;
		dat1[2] = 0x00;
		dat1[3] = 0x1f;
		char[] dat2 = new char[1];
		dat2[0] = 0x7b;

		return (new String(dat1) +regId+ params + new String(dat2));

	}

}
