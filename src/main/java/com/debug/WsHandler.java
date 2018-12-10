/**
 * 
 */
package com.debug;

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

/**
 * @author XIZHONGHUAI
 *
 */
@ServerEndpoint("/ws")
public class WsHandler {

	private static Logger logger = Logger.getLogger(WsHandler.class);
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

			TopiotService ts = (TopiotService) SpringBeanUtils.getBean(serviceId);
			if (ts.isDebugflag()) {

				IoSession eqSSession = MinaSys.regIdToSession(ts.getManagedSessions(), regId);
				if (eqSSession != null) {
					eqSSession.write(methods.encodeGBK(data));
				} else {
					session.getBasicRemote().sendText("error:" + regId + ",设备处于离线状态");
				}
			} else {
				session.getBasicRemote().sendText("error:" + serviceId + ",服务未启动");
			}

		} catch (Exception e) {
			// TODO: handle exception
			session.getBasicRemote().sendText("error:" + e.toString());
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
	 * 群发消息至浏览器端
	 * 
	 * @param message
	 * @throws IOException
	 */
	public static void sendMessage(String message) throws IOException {
		// 群发消息
		for (int i = 0; i < websocketSession.size(); i++) {
			websocketSession.get(i).getBasicRemote().sendText(message);

		}
	}

}
