/**
 * 
 */
package com.debug;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.application.SpringBeanUtils;
import com.common.TopiotService;
import com.common.methods;
import com.core.mina.MinaSys;

/**
 * @author XIZHONGHUAI
 *
 */
public class TcpHandler extends IoHandlerAdapter {

	private static Logger logger = Logger.getLogger(TcpHandler.class);

	public void messageReceived(IoSession session, Object message) throws Exception {
		// Empty handler
		try {

			if (message.toString().length() <= 32) {
				return;
			}

			String header = message.toString().substring(0, 32);
			String serviceId = header.substring(0, 16).trim();
			String regId = header.substring(16).trim();
			String data = message.toString().substring(32);

			TopiotService ts = (TopiotService) SpringBeanUtils.getBean(serviceId);
			if (ts.isDebugflag()) {

				IoSession eqSSession = MinaSys.regIdToSession(ts.getManagedSessions(), regId);
				if (eqSSession != null) {
					eqSSession.write(methods.encodeGBK(data));
				} else {
					session.write("error:" + regId + ",设备处于离线状态");
				}
			} else {
				session.write("error:" + serviceId + ",服务未启动");
			}

		} catch (Exception e) {
			// TODO: handle exception

			session.write("error:" + e.toString());
			logger.info(e.toString());

		}

	}

	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// Empty handler
	}

}
