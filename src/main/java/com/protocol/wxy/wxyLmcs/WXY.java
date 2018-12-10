package com.protocol.wxy.wxyLmcs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.mina.core.session.IoSession;

/**
 * ��վ������λ
 *
 */
public class WXY {

	@SuppressWarnings("unused")
	private static boolean gbflag = false;

	public static enum CmdType {
		/********************* ��Ҫ�������������񽻻������� ********************/
		cmdLogin, // ��¼
		cmdLogout, // ע��
		cmdOnlineEq, // ������������豸��
		cmdSvrmsg, // ��������������Ϣ
		/*----------��Ҫ����������ݿ���񽻻�������--------*/
		cmdAddUser, // �¼��û�
		cmdDelUser, // ɾ���û�
		cmdUpdateUser, // �����û�
		cmdAddEq, // �����豸
		cmdDelEq, // ɾ���豸
		cmdUpdateEq, // �����豸
		/********************* ��Ҫ���豸���������� *******************/
		cmdSetTime, // ����ʱ�� 'a'
		cmdSetIp, // ����ip 'b'
		cmdSetPort, // ���ö˿� 'c'
		cmdSetEqId, // �����豸�� 'd'
		cmdSetSpace, // ���ÿ��Ƽ�� 'e'
		cmdSetOnOffTime, // �趨���ص�ʱ�� 'f'
		cmdTurnOnOff, // ǿ��ǿ�� 'g'
		cmdReset, // ��λ 'h'
		cmdEnableBranch, // ���ÿ��� 'i'
		cmdEnableExpach, // ������չ֧· 'j'
		cmdSetExpachBrNo, // ������չ֧·��Ӧ���� 'k'
		cmdSetExpachThreshold, // ������չ֧·��ֵ 'l'
		cmdGetStatus, // ��ѯ����״̬ 'm'
		cmdGetMainParam, // ��ѯ����ѹ���� 'n'
		cmdGetExpachParam, // ��ѯ��չ���� 'o'
		cmdSetMainthreshold, // ��������ѹ������ֵ 'q'
		cmdCmdFail, // ����ִ��ʧ�� 'r'
		cmdEqHeartbeat, // �豸�������͵�������Ϣ 'p' Ŀǰ����Ϊ���ص�״̬��Ϣ
		cmdSetHeartbeatSpace, // �����豸������� 't'
		cmdParamAlarm, // ��������ѹ���� 'u'
		cmdOpenAlarm, // �Ž����� 'v'
		cmdSetWeekend, // ������ĩ 'w'
		cmdSwitchAlarm, // ���ر��� 'x'
		cmdDisableAlarm, // ���ÿ��ر��� 'y'
		cmdLineAlarm, // ��·���ϱ��� 'z'

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
	 * �ͻ��˵�¼��Ӧ
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
	 * �����ͻ������ݰ�
	 * 
	 * @param msg
	 * @return
	 */
	public static String UserDataPackDecode(String msg) {

		StringBuilder rmsg = new StringBuilder("");

		if ((msg.length() == 288) && (msg.charAt(9) == 0x67)) {

			int IDLen = msg.charAt(21);// ȡ�豸�ų���

			String ID = msg.substring(22, IDLen + 22);// ȡ�豸��

			char cHead = msg.charAt(32);// ȡ��������

			int Head = cHead + 87; // ӳ�䵽ASCII,ȡ�����������ַ�

			if ((Head >= 115) && (Head <= 122)) {
				Head = Head + 1;
			}

			if (Head == 123) {
				Head = Head - 58;
			}

			if (Head >= 124) {
				Head = Head - 57;
			}

			int ParaLen = msg.charAt(33);// ȡ�������ݳ���

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
	 * �����������ݰ�
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
	 * �ַ���ƴ��
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
	 * ���ͻ����������ݰ�
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
	 * �������
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
	 * ��ȡ��ĩ
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
	 * ��ʽ��ϵͳʱ��yyMMWWddHHmmss
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
