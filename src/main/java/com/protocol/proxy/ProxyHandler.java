/**
 * 
 */
package com.protocol.proxy;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.common.ClassMsgAnnotation;
import com.common.methods;

/**
 * @author xizhonghuai
 * @date 2018��8��8��
 * @readme
 */

@ClassMsgAnnotation(mark = "���������", putStorageMethod = { "insertProxyRealtimeData",
		"updateProxyRealtimeData" }, dataModelClassName = "com.protocol.proxy.ProxyDataModel")
public class ProxyHandler extends IoHandlerAdapter {
	private Logger logger = Logger.getLogger(ProxyHandler.class);

	public void messageReceived(IoSession session, Object message) throws Exception {
		// Empty handler

		logger.info("�յ����ݣ�" + methods.toHexString(message.toString()));

		WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

		ProxyProcess proxyProcess = (ProxyProcess) ctx.getBean("ProxyProcess");
		proxyProcess.Controlmessage(session, message.toString());

	}

	@SuppressWarnings("deprecation")
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// Empty handler
		session.close();
		logger.info(session.getRemoteAddress() + "��ʱ�Ͽ�");
	}

	public void sessionOpened(IoSession session) throws Exception {
		// Empty handler
		logger.info(session.getRemoteAddress() + "����");
	}

	public void sessionClosed(IoSession session) throws Exception {
		// Empty handler
		logger.info(session.getRemoteAddress() + "�Ͽ�");
	}

}