/**
 * 
 */
package com.debug;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;

import com.application.SpringBeanUtils;
import com.common.TopiotService;
import com.common.methods;
import com.core.mina.MinaSys;

/**
 * @author XIZHONGHUAI
 *
 */
public class DebugFilter extends IoFilterAdapter {

	private String serviceId;

	/**
	 * @param serviceId
	 */
	public DebugFilter(String serviceId) {
		super();
		this.serviceId = serviceId;
	}

	public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
		nextFilter.messageReceived(session, message);

		String msg = "";
		
		String regId = (String) session.getAttribute("regId", null);
		
		if (regId == null){
			regId = "no regId";
		}

		msg = methods.putSpace(serviceId) + methods.putSpace(regId) + message.toString();

		TopiotService ts = (TopiotService) SpringBeanUtils.getBean("debug");

		MinaSys.SendMsg(ts.getManagedSessions(), msg); // 转发至tcp客户端

		WsHandler.sendMessage(msg);// 转发至web

	}
}
