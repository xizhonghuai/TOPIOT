/**
 * 
 */
package com.protocol.safemon;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.common.ClassMsgAnnotation;
import com.common.methods;
import com.protocol.safemon.sys.CqswMethods;

/**
 * @author xizhonghuai
 * @date 2018��8��29��
 * @readme
 */
@ClassMsgAnnotation(mark = "��ȫ���ƽ̨(ˮ����+��·��ˮ)", putStorageMethod = {
		"updateCqswRealtimeData" }, dataModelClassName = "com.protocol.safemon.CqswDataModel")
public class CqswHandler extends IoHandlerAdapter {

	private Logger logger = Logger.getLogger(CqswHandler.class);

	public void messageReceived(IoSession session, Object message) throws Exception {

		try {

			String msg_temp = message.toString();
			String recmsg = (String) session.getAttribute("recmsg", "");

			if (msg_temp.contains("{") && (msg_temp.indexOf(0x7b) != msg_temp.length() - 1)) {

				if (recmsg.equals("")) {
					msg_temp = msg_temp.substring(msg_temp.indexOf(0x7b));
				}

			}

			recmsg = recmsg + msg_temp;

			char beginChar = recmsg.charAt(0);
			char endChar = recmsg.charAt(recmsg.length() - 1);
			if (((beginChar == 0x7b) && (endChar == 0x7b)) || ((beginChar == 0x28) && (endChar == 0x29))) {
				session.removeAttribute("recmsg");

				if (recmsg.length() < 1024) {
					logger.info("�յ����ݣ�" + methods.toHexString(recmsg));
				}

				int deviceType = CqswMethods.checkDeviceType(recmsg);
				if (deviceType == 1) {
					WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
					SwProcess swProcess = (SwProcess) ctx.getBean("SwProcess");
					swProcess.Controlmessage(session, recmsg);
				}
				if (deviceType == 2) {
					WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
					LedProcess roseeProcess = (LedProcess) ctx.getBean("LedProcess");
					roseeProcess.Controlmessage(session, message.toString());
				}

			} else {

				if (recmsg.indexOf(0x7b) == 0) {

					session.setAttribute("recmsg", recmsg);

					String regId = (String) session.getAttribute("regId", null);

					logger.info(regId + "��ӵ����棺" + methods.toHexString(recmsg));

				}

				if (((String) session.getAttribute("recmsg", "")).length() > 2048) {
					session.removeAttribute("recmsg");
				}

			}

			session.write("ack");

		} catch (Exception e) {
			// TODO: handle exception

			logger.info("�쳣��" + e.toString());
		}

	}

	public void sessionClosed(IoSession session) throws Exception {

		if (session.getAttribute("regId", null) != null) {
			logger.info(
					"�豸" + session.getAttribute("regId", null).toString() + session.getRemoteAddress() + "---------ע��");
		}

	}

	public void sessionOpened(IoSession session) throws Exception {
		// logger.info(session.getRemoteAddress().toString() + "����");

	}

	public void sessionCreated(IoSession session) throws Exception {
		// logger.info(session.getRemoteAddress().toString() + "��������.......");

	}

	public void messageSent(IoSession session, Object message) throws Exception {
		// logger.info("����˷�����Ϣ�ɹ�...");
	}

	@SuppressWarnings("deprecation")
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {

		if (session.getAttribute("regId", null) != null) {

			logger.info("�豸" + session.getAttribute("regId", null).toString() + session.getRemoteAddress()
					+ "---------��ʱ����������Ϣ��������ͷ�����");
		}
		session.close();
	}

	@SuppressWarnings("deprecation")
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// logger.error("����˷����쳣...", cause);
		session.close();
	}

}
