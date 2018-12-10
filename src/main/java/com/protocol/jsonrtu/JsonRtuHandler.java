/**
 * 
 */
package com.protocol.jsonrtu;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.common.ClassMsgAnnotation;
import com.sockettohttp.SocketConnHttp;

/**
 * @author XIZHONGHUAI
 *
 */
@ClassMsgAnnotation(mark = "简易RTUjson数据传输")
public class JsonRtuHandler extends IoHandlerAdapter {
	private static Logger logger = Logger.getLogger(JsonRtuHandler.class);

	public void messageReceived(IoSession session, Object message) throws Exception {
		// Empty handler

		// logger.info(message.toString());

		if ((message.toString().charAt(0) == '{')
				&& (message.toString().charAt(message.toString().length() - 1) == '}')) {

			JsonRTU jsonMsg = JSON.parseObject(message.toString(), new TypeReference<JsonRTU>() {
			});

		
			if (!jsonMsg.getId().equals(null)){
				  
				session.setAttribute("regId", jsonMsg.getId());
				
			}
			
			
		/*	if (jsonMsg.getType().equals("heart")) {
				session.setAttribute("regId", jsonMsg.getId());
			}*/

			logger.info(JSONObject.toJSONString(jsonMsg));

			JsonRTU jsonACK = new JsonRTU();

			jsonACK.setTime(jsonMsg.getTime());
			jsonACK.setId(jsonMsg.getId());
			jsonACK.setType("resp");

			String resultJson = JSONObject.toJSONString(jsonACK);

			session.write(resultJson);

		} else {

			if (session.getAttribute("httpMsg", null) == null) {
				StringBuffer httpMsgBuff = new StringBuffer();
				session.setAttribute("httpMsg", httpMsgBuff);
			}

			StringBuffer httpMsgBuffTemp = (StringBuffer) session.getAttribute("httpMsg");
			httpMsgBuffTemp.append(message.toString());
			session.setAttribute("httpMsg", httpMsgBuffTemp);

		}

	}

	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// Empty handler

		if (session.getAttribute("httpMsg", null) != null) {

			StringBuffer httpMsgBuff = (StringBuffer) session.getAttribute("httpMsg");

			SocketConnHttp sch = new SocketConnHttp("127.0.0.1", 8081, session);

			sch.write(httpMsgBuff.toString());

			System.out.println("数据总长度：" + httpMsgBuff.toString().length());

			session.removeAttribute("httpMsg");

		}

	}

	public void sessionOpened(IoSession session) throws Exception {
		// Empty handler
		logger.info(session.getRemoteAddress() + "连接");

	}

	public void sessionClosed(IoSession session) throws Exception {
		// Empty handler
	}

}
