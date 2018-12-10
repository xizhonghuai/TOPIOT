/**
 * 
 */
package com.protocol.wxy;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.common.ClassMsgAnnotation;

/**
 * @author Administrator
 *
 */

@ClassMsgAnnotation(mark = "危险源监控预警系统")
public class WxyHandler extends IoHandlerAdapter {

	private static Logger logger = Logger.getLogger(WxyHandler.class);

 

	public void messageReceived(IoSession session, Object message) throws Exception {
		String msgtemp = message.toString();

		if ((msgtemp.charAt(0) == 'A') && (msgtemp.length() >= 15)) {
			msgtemp = msgtemp.substring(8, 15); // AT+CCID //
												// (s4003)(z546489155646899)
		}
		try {
			WxyProcess.Controlmessage(session, msgtemp);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("异常...." + e.toString());
		}
	}

	public void sessionClosed(IoSession session) throws Exception {

		if (session.getAttribute("regId", null) != null) {
			logger.info(
					"设备" + session.getAttribute("regId", null).toString() + session.getRemoteAddress() + "---------注销");
		}

	}

	public void sessionOpened(IoSession session) throws Exception {
		// logger.info(session.getRemoteAddress().toString() + "连接");

	}

	public void sessionCreated(IoSession session) throws Exception {
		// logger.info(session.getRemoteAddress().toString() + "正在连接.......");

	}

	public void messageSent(IoSession session, Object message) throws Exception {
		// logger.info("服务端发送信息成功...");
	}

	@SuppressWarnings("deprecation")
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {

		if (session.getAttribute("regId", null) != null) {

			logger.info("设备" + session.getAttribute("regId", null).toString() + session.getRemoteAddress()
					+ "---------长时间无心跳信息，服务端释放连接");
		}
		session.close();
	}

	@SuppressWarnings("deprecation")
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// logger.error("服务端发送异常...", cause);
		session.close();
	}

}
