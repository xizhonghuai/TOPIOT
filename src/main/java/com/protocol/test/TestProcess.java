/**
 * 
 */
package com.protocol.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.protocol.database.PutStorageDao;

/**
 * @author xizhonghuai
 * @date 2018Äê7ÔÂ10ÈÕ
 * @readme
 */
@Component("TestProcess")
public class TestProcess {

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(TestProcess.class);
	@Autowired
	private PutStorageDao putStorageDao;

	public void Controlmessage(IoSession session, String recmsg) {

		String regId = (String) session.getAttribute("regId", null);

		System.out.println("REG=" + regId);

		System.out.println(session.getRemoteAddress().toString());

		String msg[] = recmsg.split("_");

		if (regId == null) {
			session.setAttribute("regId", msg[0]);
		} else {

			if (!msg[0].equals(regId)) {
				session.setAttribute("regId", msg[0]);
			}

		}
		
		
		session.write("test");

		TestDataModel testDataModel = new TestDataModel();

		testDataModel.setRegId(msg[0]);
		testDataModel.setValue(Float.parseFloat(msg[1]));
		testDataModel.setTm(new Date());

		putStorageDao.insertTestRealtimeData(testDataModel);
		putStorageDao.updateTestRealtimeData(testDataModel);

	}

}
