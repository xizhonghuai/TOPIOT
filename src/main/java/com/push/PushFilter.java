/**
 * 
 */
package com.push;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;

import com.application.SpringBeanUtils;
import com.common.TopiotService;
import com.common.methods;

/**
 * @author xizhonghuai
 * @date 2018Äê7ÔÂ23ÈÕ
 * @readme
 */
public class PushFilter extends IoFilterAdapter {

	private String serviceId;

	/**
	 * @param serviceId
	 */
	public PushFilter(String serviceId) {
		super();
		this.serviceId = serviceId;
	}

	public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
		nextFilter.messageReceived(session, message);

		String msg = "";

		String regId = (String) session.getAttribute("regId", null);

		if (regId == null) {
			regId = "no regId";
		}

		msg = methods.putSpace(serviceId) + methods.putSpace(regId) + message.toString();

		TopiotService ts = (TopiotService) SpringBeanUtils.getBean(serviceId);

		new PushService(ts.getPushUrl(), msg).start();

	}
}