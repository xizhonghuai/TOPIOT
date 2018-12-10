/**
 * 
 */
package com.protocol.wxy;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.application.SpringBeanUtils;
import com.common.ClassMsgAnnotation;
import com.common.TopiotService;
import com.core.mina.MinaSys;
import com.protocol.wxy.sys.Conf;
import com.protocol.wxy.sys.SysMethod;
import com.protocol.wxy.wxyLmcs.WXY;
import com.protocol.wxy.wxyLmcs.WXY.CmdType;

/**
 * @author Administrator
 *
 */
@ClassMsgAnnotation(mark = "Σ��Դ���Ԥ��ϵͳ���Է���")
public class WxyDebugHander extends IoHandlerAdapter {

	private static Logger logger = Logger.getLogger(WxyDebugHander.class);

	@SuppressWarnings("deprecation")
	public void messageReceived(IoSession session, Object message) throws Exception {

		String msg = message.toString();

		CmdType cmdt = WXY.UserGetChead(msg); // ȡ����Ϣ���

		if (cmdt == CmdType.cmdLogin) {

			try {

				int userL = msg.charAt(0); // �û�������
				int pssdL = msg.charAt(33);// ���кų���
				String user = msg.substring(1, userL + 1); // ȡ���û���
				String pssd = msg.substring(34, pssdL + 34); // ȡ�����к�
				Conf con = new Conf("wxy_userlist.properties");
				String str = con.GetKeyValue(user, "");

				if (str.length() > 8) {

					String p = str.substring(0, str.length() - 8);

					if (pssd.equals(p)) {

						String ACK = WXY.UserLoginACK();

						session.write(ACK); // ��¼�ɹ���Ӧ

						String par = str.substring(p.length()); // ȡ���û���Ȩ�豸��Χ

						session.setAttribute("regIdMin", Integer.parseInt(par.substring(0, 4)));
						session.setAttribute("regIdMax", Integer.parseInt(par.substring(4)));

					} else
						session.close();

				}

			} catch (Exception e) {
				logger.error("�쳣...." + e.toString());
			}

		} else {

			try {
				String CMD = WXY.UserDataPackDecode(msg);
				if (CMD.length() > 0) {
					int IDLen = CMD.charAt(0);
					String DeviceID = CMD.substring(1, IDLen + 1);
					String Para = CMD.substring(IDLen + 1);

					if (DeviceID.equals("####")) {

						TopiotService ts = (TopiotService) SpringBeanUtils.getBean("wxy");

						int OnlineCount = ts.getManagedSessions().size();

						String Upackdat = WXY.UserPackEncode("####", "p",
								SysMethod.Encode_GBK("�豸��������:") + String.valueOf(OnlineCount));
						session.write(Upackdat);

					} else {

						TopiotService ts = (TopiotService) SpringBeanUtils.getBean("wxy");

						char ord = Para.charAt(0);

						if (ord == 'r') {
							Para = "p" + Para.substring(1);
						} else if (ord == 'p') {
							Para = "q" + Para.substring(1);
						} else if (ord == 'q') {
							Para = "r" + Para.substring(1);
						}

						MinaSys.SendMsg(ts.getManagedSessions(), WXY.Addbracket(Para), DeviceID);

						logger.info(SysMethod.StrPlus("�ͻ������ݣ�", DeviceID, "----", WXY.Addbracket(Para)));

					}

				}

			} catch (Exception e) {
				logger.error("�쳣...." + e.toString());

			}
		}

	}

	public void sessionClosed(IoSession session) throws Exception {
		// logger.info("���ӶϿ�");

	}

	public void sessionOpened(IoSession session) throws Exception {
		// logger.info("�������ͻ������Ӵ�...");
		// Sys.AddSession(LmcsMain.UserList, session, null);
	}

	public void sessionCreated(IoSession session) throws Exception {
		// logger.info("�������ͻ��˴�������...");

	}

	public void messageSent(IoSession session, Object message) throws Exception {
		// logger.info("����˷�����Ϣ�ɹ�...");
	}

	@SuppressWarnings("deprecation")
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// logger.info("����˽������״̬...");
		session.close();
	}

	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// logger.error("����˷����쳣...", cause);
	}

}