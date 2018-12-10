/**
 * 
 */
package com.protocol.safemon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.application.SpringBeanUtils;
import com.common.TopiotService;
import com.common.methods;
import com.core.mina.MinaSys;
import com.protocol.safemon.sys.CqswMethods;

/**
 * @author XIZHONGHUAI
 *
 */
@ServerEndpoint("/wsdebug")
public class debugHandler {

	private static Logger logger = Logger.getLogger(debugHandler.class);
	public static List<Session> websocketSession = new ArrayList<Session>();

	private Session session;

	@OnMessage
	public void onMessage(String message, Session session) throws IOException {

		try {

			if (message.toString().length() <= 32) {
				return;
			}
 
			String header = message.toString().substring(0, 32);
			String serviceId = header.substring(0, 16).trim();
			String regId = header.substring(16).trim();
			String data = message.toString().substring(32);

			String deviceType = data.substring(0, 1);
			data = data.substring(1);

			TopiotService ts = (TopiotService) SpringBeanUtils.getBean(serviceId);
			if (ts.isDebugflag()) {
				IoSession eqSSession = MinaSys.regIdToSession(ts.getManagedSessions(), regId);
				if (eqSSession != null) {

					if (deviceType.equals("L")) {
						eqSSession.write(methods.encodeGBK("("+data+")"));
					}

					if (deviceType.equals("J")) {
						if (data.equals("T")) {
							eqSSession.write(CqswMethods.calibrationTime(regId));
						} else {

							eqSSession.write(CqswMethods.paramsConfig(regId, data));
						}
					}
					
					 session.getBasicRemote().sendText("debugsendok");
					
					
					
				} else {
					session.getBasicRemote().sendText(regId + "*error:" + regId + "�豸��������״̬");
				}
			} else {
				session.getBasicRemote().sendText(regId + "*error:" + serviceId + "����δ����");
			}

		} catch (Exception e) {
		 
			 session.getBasicRemote().sendText("debugsenderr");
			 
			logger.info(e.toString());

		}

	}

	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		websocketSession.add(session);
	}

	@OnClose
	public void onClose() {
		websocketSession.remove(session);
	}

	@OnError
	public void onError(Session session, Throwable error) {

		error.printStackTrace();
	}

	/**
	 * Ⱥ����Ϣ���������
	 * 
	 * @param message
	 * @throws IOException
	 */
	public static void sendMessage(String message) throws IOException {
		// Ⱥ����Ϣ
		for (int i = 0; i < websocketSession.size(); i++) {
			websocketSession.get(i).getBasicRemote().sendText(message);

		}
	}

}
