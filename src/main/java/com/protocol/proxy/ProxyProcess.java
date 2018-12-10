/**
 * 
 */
package com.protocol.proxy;

import java.util.Date;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.protocol.database.PutStorageDao;

/**
 * @author xizhonghuai
 * @date 2018Äê8ÔÂ8ÈÕ
 * @readme
 */
@Component("ProxyProcess")
public class ProxyProcess {

	@Autowired
	private PutStorageDao putStorageDao;

	public void Controlmessage(IoSession session, String msg) {

		String regId = (String) session.getAttribute("regId", null);

		if ((msg.length() > 2) && (msg.charAt(0) == '(') && (msg.charAt(msg.length() - 1) == ')')) {

			session.setAttribute("regId", msg.substring(1, msg.length() - 1));
			
			session.write("reg ok");

			return;

		}

		try {

			ProxyDataModel proxyDataModel = new ProxyDataModel();

			proxyDataModel.setRegId(regId);

			proxyDataModel.setTm(new Date());

			proxyDataModel.setValue(msg);

			putStorageDao.insertProxyRealtimeData(proxyDataModel);

			putStorageDao.updateProxyRealtimeData(proxyDataModel);

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		session.write("ok");
		

	}

}
