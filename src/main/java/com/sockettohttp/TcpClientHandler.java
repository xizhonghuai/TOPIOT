/**
 * 
 */
package com.sockettohttp;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @author xizhonghuai
 * @date 2018年7月12日
 * @readme
 */
public class TcpClientHandler extends IoHandlerAdapter {
//	private Logger logger = Logger.getLogger(TcpClientHandler.class);
	private IoSession eqSession;

	/**
	 * @param eqSession
	 */
	public TcpClientHandler(IoSession eqSession) {
		super();
		this.eqSession = eqSession;
		
		
	 
	}

	public void messageReceived(IoSession session, Object message) throws Exception {
		// Empty handler
		
		System.out.println("http服务器响应：");
		System.out.println(message.toString());

		if (eqSession != null) {

			eqSession.write(message.toString());

		}

	}

	@SuppressWarnings("deprecation")
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// Empty handler
		System.out.println("空闲断开http服务器");
		session.close();
		
	}

	public void sessionOpened(IoSession session) throws Exception {
		// Empty handler

	}

	public void sessionClosed(IoSession session) throws Exception {
		// Empty handler
	}
}
