/**
* 
*/
package com.protocol.test;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.common.ClassMsgAnnotation;
import com.common.methods;

/**
 * @author XIZHONGHUAI
 *
 */

@SuppressWarnings("unused")
@ClassMsgAnnotation(mark = "测试", putStorageMethod = { "insertTestRealtimeData",
		"updateTestRealtimeData" }, dataModelClassName = "com.protocol.test.TestDataModel")
public class TestHandler extends IoHandlerAdapter {
	private Logger logger = Logger.getLogger(TestHandler.class);


	public void messageReceived(IoSession session, Object message) throws Exception {
		// Empty handler

		logger.info("收到数据：" + methods.toHexString(message.toString()));

		WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

		TestProcess testProcess = (TestProcess) ctx.getBean("TestProcess");
		testProcess.Controlmessage(session, message.toString());

	}

	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// Empty handler
	}

	public void sessionOpened(IoSession session) throws Exception {
		// Empty handler
		logger.info(session.getRemoteAddress() + "连接");
	}

	public void sessionClosed(IoSession session) throws Exception {
		// Empty handler
	}

 

}
