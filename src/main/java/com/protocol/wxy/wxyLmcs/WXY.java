package com.protocol.wxy.wxyLmcs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.mina.core.session.IoSession;

/**
 * 测站编码四位
 *
 */
public class WXY {

	@SuppressWarnings("unused")
	private static boolean gbflag = false;

	public static enum CmdType {
		/********************* 需要与服务器事务服务交互的命令 ********************/
		cmdLogin, // 登录
		cmdLogout, // 注销
		cmdOnlineEq, // 获得所有在线设备号
		cmdSvrmsg, // 服务器的其他消息
		/*----------需要与服务器数据库服务交互的命令--------*/
		cmdAddUser, // 新加用户
		cmdDelUser, // 删除用户
		cmdUpdateUser, // 更新用户
		cmdAddEq, // 加入设备
		cmdDelEq, // 删除设备
		cmdUpdateEq, // 更新设备
		/********************* 需要与设备交互的命令 *******************/
		cmdSetTime, // 设置时间 'a'
		cmdSetIp, // 设置ip 'b'
		cmdSetPort, // 设置端口 'c'
		cmdSetEqId, // 设置设备号 'd'
		cmdSetSpace, // 设置开灯间隔 'e'
		cmdSetOnOffTime, // 设定开关灯时间 'f'
		cmdTurnOnOff, // 强开强关 'g'
		cmdReset, // 复位 'h'
		cmdEnableBranch, // 启用开关 'i'
		cmdEnableExpach, // 启用扩展支路 'j'
		cmdSetExpachBrNo, // 设置扩展支路对应开关 'k'
		cmdSetExpachThreshold, // 设置扩展支路阈值 'l'
		cmdGetStatus, // 查询开关状态 'm'
		cmdGetMainParam, // 查询主电压电流 'n'
		cmdGetExpachParam, // 查询扩展电流 'o'
		cmdSetMainthreshold, // 设置主电压电流阈值 'q'
		cmdCmdFail, // 命令执行失败 'r'
		cmdEqHeartbeat, // 设备主动发送的心跳信息 'p' 目前定义为开关的状态信息
		cmdSetHeartbeatSpace, // 设置设备心跳间隔 't'
		cmdParamAlarm, // 主电流电压报警 'u'
		cmdOpenAlarm, // 门禁报警 'v'
		cmdSetWeekend, // 设置周末 'w'
		cmdSwitchAlarm, // 开关报警 'x'
		cmdDisableAlarm, // 禁用开关报警 'y'
		cmdLineAlarm, // 线路故障报警 'z'

		cmdT1, // 'A'
		cmdT2, // 'B'
		cmdT3, // 'C'
		cmdT4, // 'D'
		cmdT5, // 'E'
		cmdT6, // 'F'
		cmdT7, // 'G'
		cmdT8, // 'H'
		cmdT9, // 'I'
		cmdT10, // 'J'
		cmdT11, // 'K'
		cmdT12, // 'L'
		cmdT13, // 'M'
		cmdT14, // 'N'
		cmdT15, // 'O'
		cmdT16, // 'P'
		cmdT17, // 'Q'
		cmdT18, // 'R'
		cmdT19, // 'S'
		cmdT20, // 'T'
		cmdT21, // 'U'
		cmdT22, // 'V'
		cmdT23, // 'W'
		cmdT24, // 'X'
		cmdT25, // 'Y'
		cmdT26 // 'Z'

	}

	/**
	 * @param msg
	 * @return
	 */
	public static CmdType UserGetChead(String msg) {
		char cHead = (msg.charAt(32));
		int Head = cHead;
		return CmdType.values()[Head];
	}

	/**
	 * 客户端登录响应
	 * 
	 * @return
	 */
	public static String UserLoginACK() {

		char[] ack = new char[516];
		ack[1] = 0x07;
		ack[2] = 0x73;
		ack[3] = 0x75;
		ack[4] = 0x63;
		ack[5] = 0x63;
		ack[6] = 0x65;
		ack[7] = 0x73;
		ack[8] = 0x73;

		return new String(ack);

	}

	/**
	 * 解析客户端数据包
	 * 
	 * @param msg
	 * @return
	 */
	public static String UserDataPackDecode(String msg) {

		StringBuilder rmsg = new StringBuilder("");

		if ((msg.length() == 288) && (msg.charAt(9) == 0x67)) {

			int IDLen = msg.charAt(21);// 取设备号长度

			String ID = msg.substring(22, IDLen + 22);// 取设备号

			char cHead = msg.charAt(32);// 取命令类型

			int Head = cHead + 87; // 映射到ASCII,取得命令类型字符

			if ((Head >= 115) && (Head <= 122)) {
				Head = Head + 1;
			}

			if (Head == 123) {
				Head = Head - 58;
			}

			if (Head >= 124) {
				Head = Head - 57;
			}

			int ParaLen = msg.charAt(33);// 取参数数据长度

			rmsg.append((char) IDLen);

			rmsg.append(ID);

			rmsg.append((char) Head);

			if (ParaLen > 0) {
				rmsg.append(msg.substring(34, ParaLen + 34));
			}
		}

		return rmsg.toString();

	}

	/**
	 * 生成在线数据包
	 * 
	 * @param msg
	 * @return
	 */
	public static String OnlineEqPackEncode(String ID) {

		char[] Eqs = new char[516];

		Eqs[0] = 0x02;
		Eqs[1] = 4;
		Eqs[2] = ID.charAt(0);
		Eqs[3] = ID.charAt(1);
		Eqs[4] = ID.charAt(2);
		Eqs[5] = ID.charAt(3);

		Eqs[512] = 0x02;

		return new String(Eqs);
	}

	/**
	 * 字符串拼接
	 * 
	 * @param Strs
	 * @return
	 */
	private static String StrPlus(String... Strs) {

		StringBuilder Builder = new StringBuilder("");

		for (String str : Strs)
			Builder.append(str);

		return Builder.toString();
	}

	/**
	 * 至客户端命令数据包
	 * 
	 * @param msg
	 * @return
	 */
	public static String UserPackEncode(String ID, String chead, String msg) {

		char[] dat = new char[516];

		String data = StrPlus(ID, msg);

		if (chead.equals("p")) {
			dat[0] = 27;
		} else {
			// 35 --A
			dat[0] = (char) (chead.charAt(0) - 87);

			if ((chead.charAt(0) >= 65) && (chead.charAt(0) <= 90)) {

				dat[0] = (char) (chead.charAt(0) - 30);
			}

		}

		dat[1] = (char) (data.length());

		for (int i = 0; i < data.length(); i++) {
			dat[2 + i] = data.charAt(i);
		}

		return new String(dat);

	}

	/**
	 * 添加括号
	 * 
	 * @param Cmd
	 */
	public static String Addbracket(String Cmd) {
		StringBuilder Builder = new StringBuilder("(");
		Builder.append(Cmd);
		Builder.append(")");
		return Builder.toString();
	}

	/**
	 * 获取周末
	 * 
	 * @return
	 */
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "00", "01", "02", "03", "04", "05", "06" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 格式化系统时间yyMMWWddHHmmss
	 * 
	 * @return
	 */
	public static String FormatSystemDate() {

		SimpleDateFormat dt = new SimpleDateFormat("yyMMddHHmmss");

		Date date = new Date();

		StringBuilder Builder = new StringBuilder(dt.format(date));

		Builder.insert(4, getWeekOfDate(date));

		return Builder.toString();

	}

	public static void qpProcess(IoSession session, String Detail) {

		char K = Detail.charAt(9);
		float value = Float.parseFloat(Detail.substring(2, 6));

		if ((value > 3) && (K != '3')) {
			session.write(WXY.Addbracket("k11"));
		}

		if ((value < 3) && (K == '3')) {
			session.write(WXY.Addbracket("k0"));
		}

	}

}
